package Game;

import Blocks.Block;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This file was created by Rhys Williams,
 * www.rhyswilliams.co.za
 * me@rhyswilliams.co.za
 */
public class Core {
    //Static mode Variables
    public static final int GRAPHICMODE = 0;
    public static final int LINEMODE = 1;

    //Set global variables
    protected ArrayList<Block>[][] board;
    protected String boardTitle;
    protected int mode;

    /**
     * Instantiate the core class
     *
     * @param boardPath Path to the board file
     * @param mode      Mode: GRAPHICMODE or LINEMODE
     */
    public Core(String boardPath, int mode) {
        GenerateBoard(boardPath);
        this.mode = mode;
    }

    /**
     * Generate the playing board using the provided text file.
     * This method also adds each element to an array of Blocks
     *
     * @param boardPath
     */
    private void GenerateBoard(String boardPath) {
        try {
            //Create scanner of board file, and assign the first line to the boardTitle
            Scanner boardScan = new Scanner(new File(boardPath));
            boardTitle = boardScan.nextLine();

            //Scan the dimensions of the board into their respective variables
            Scanner boardDimensionsScan = new Scanner(boardScan.nextLine());
            int ySize = boardDimensionsScan.nextInt();
            int xSize = boardDimensionsScan.nextInt();
            boardDimensionsScan.close();

            //Create the board array
            board = new ArrayList[ySize][xSize];

            for (int i = 0; i < ySize; i++) {
                Scanner line = new Scanner(boardScan.nextLine()).useDelimiter("");
                for (int j = 0; j < xSize; j++) {
                    board[i][j] = new ArrayList<Block>();
                    board[i][j].add(Factories.makeBlock(line.next()));
                }
                line.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Request a move using the parametrized key
     *
     * @param key A letter from the keyboard
     */
    public void requestMove(String key) {
        switch (key.charAt(0)) {
            case 'h':
                manageMove(Block.MOVES_LEFT);
                break;
            case 'l':
                manageMove(Block.MOVES_RIGHT);
                break;
            case 'j':
                manageMove(Block.MOVES_DOWN);
                break;
            case 'k':
                manageMove(Block.MOVES_UP);
                break;
            case 'q': {
                if (mode == GRAPHICMODE) {
                    outputAndExit("");
                }
            }
        }
    }

    /**
     * Lets each block object know that a move was requested.
     * Perform block move if needed.
     *
     * @param triggerKey
     */
    private void manageMove(int triggerKey) {

        boolean doesMove = true;

        //Check that the player is not going off the board left or right
        int coords[] = findPlayerCellCoords();
        int y = coords[0];
        int x = coords[1];
        if (x == 0 && triggerKey == Block.MOVES_LEFT) {
            doesMove = false;
        }
        if (x >= board[y].length - 1 && triggerKey == Block.MOVES_RIGHT) {
            doesMove = false;
        }


        //If the player is not going off left or right, do the move.
        if (doesMove) {
            boolean moved = false;
            for (int yPos = 0; yPos < board.length; yPos++) {
                for (int xPos = 0; xPos < board[yPos].length; xPos++) {
                    for (int zPos = board[yPos][xPos].size() - 1; zPos >= 0; zPos--) {
                        Block currentBlock = board[yPos][xPos].get(zPos);
                        if (!currentBlock.getJustMoved()) {

                            //Let each block know that a move of triggerKey was made
                            board[yPos][xPos].get(zPos).moveMade(triggerKey);

                            //Check which move was made and run the doMove method
                            if (currentBlock.getMovesUp(triggerKey)) {
                                doMove(new int[]{yPos, xPos, zPos}, new int[]{yPos - 1, xPos});
                                moved = true;
                            }
                            if (currentBlock.getMovesDown(triggerKey)) {
                                doMove(new int[]{yPos, xPos, zPos}, new int[]{yPos + 1, xPos});
                                moved = true;
                            }
                            if (currentBlock.getMovesLeft(triggerKey)) {
                                doMove(new int[]{yPos, xPos, zPos}, new int[]{yPos, xPos - 1});
                                moved = true;
                            }
                            if (currentBlock.getMovesRight(triggerKey)) {
                                doMove(new int[]{yPos, xPos, zPos}, new int[]{yPos, xPos + 1});
                                moved = true;
                            }
                        }
                    }
                }
            }

            //Do actions if 'moved' is true
            if (moved) {
                moveMade();
                resetJustMovedStatus();
            }
        }
    }

    /**
     * Do block move, called by manageMove.
     *
     * @param source Coordinates of source block in int[]{y,x}
     * @param target Coordinates of target block in int[]{y,x}
     */
    private void doMove(int[] source, int[] target) {
        //Get the source block co-ords
        int sourceY = source[0];
        int sourceX = source[1];
        int sourceZ = source[2];

        //Get the target block co-ords
        int targetY = target[0];
        int targetX = target[1];

        //Check if the block has just moved
        if (!board[sourceY][sourceX].get(sourceZ).getJustMoved()) {

            //Create new block object from object in original cell
            Block sourceBlock = board[sourceY][sourceX].get(sourceZ);
            sourceBlock.setJustMoved(true);

            //Check if block needs to wrap around X.
            if (targetX < 0) {
                if (sourceBlock.getCanWrapX()) {
                    targetX = board[sourceY].length - 1;
                } else {
                    targetX = sourceX;
                }
            }
            if (targetX >= board[sourceY].length) {
                if (sourceBlock.getCanWrapX()) {
                    targetX = 0;
                } else {
                    targetX = sourceX;
                }
            }

            //Check if block needs to wrap around Y
            if (targetY < 0) {
                if (sourceBlock.getCanWrapY()) {
                    targetY = board.length - 1;
                } else {
                    targetY = sourceY;
                }
            }
            if (targetY >= board.length) {
                if (sourceBlock.getCanWrapY()) {
                    targetY = 0;
                } else {
                    targetY = sourceY;
                }
            }

            //Add source block to target
            board[targetY][targetX].add(sourceBlock);

            //Remove block from source
            board[sourceY][sourceX].remove(sourceZ);

            //If there are no blocks in the source cell, create an empty block.
            if (board[sourceY][sourceX].size() == 0) {
                board[sourceY][sourceX].add(Factories.makeBlock("."));
            }
        }
    }

    /**
     * Reset just moved status for all blocks.
     */
    private void resetJustMovedStatus() {
        for (int yPos = 0; yPos < board.length; yPos++) {
            for (int xPos = 0; xPos < board[yPos].length; xPos++) {
                for (int zPos = 0; zPos < board[yPos][xPos].size(); zPos++) {
                    board[yPos][xPos].get(zPos).setJustMoved(false);
                }
            }
        }
    }

    /**
     * Rules to apply to blocks if a move has just been made.
     */
    private void moveMade() {

        /**
         * Do actions for each block in the same cell as the player
         * Informs blocks in the same cell that the player has moved onto them.
         */
        //Get Player Cell
        int[] playerCellCoords = findPlayerCellCoords();

        //Loop for all blocks in the same cell as the player
        for (int i = 0; i < board[playerCellCoords[0]][playerCellCoords[1]].size(); i++) {

            //Check if the player has moved onto a key
            if (board[playerCellCoords[0]][playerCellCoords[1]].get(i).getBlockType().toLowerCase().equals("k")) {
                if (board[playerCellCoords[0]][playerCellCoords[1]].get(i).getEnabled()) {
                    for (int yPos = 0; yPos < board.length; yPos++) {
                        for (int xPos = 0; xPos < board[yPos].length; xPos++) {
                            for (int zPos = board[yPos][xPos].size() - 1; zPos >= 0; zPos--) {
                                if (board[yPos][xPos].get(i).getBlockType().toLowerCase().equals("p")) {
                                    board[yPos][xPos].get(i).changeRequested();
                                }
                            }
                        }
                    }
                }
            }


            //Check if the player has touched a block to WIN_GAME
            if (board[playerCellCoords[0]][playerCellCoords[1]].get(i).getActionWhenPlayerTouch() == Block.WIN_GAME) {
                outputAndExit("You won!");
            }

            //Check if the player has touched a block to END_GAME
            if (board[playerCellCoords[0]][playerCellCoords[1]].get(i).getActionWhenPlayerTouch() == Block.END_GAME) {
                outputAndExit("You lost!");
            }

            //Let the block know that the player has touched it.
            board[playerCellCoords[0]][playerCellCoords[1]].get(i).playerTouched();

        }
    }

    /**
     * Return the coordinates of the player cell in the form int[]{y,x}
     *
     * @return int[]{y,x}
     */
    private int[] findPlayerCellCoords() {
        for (int yPos = 0; yPos < board.length; yPos++) {
            for (int xPos = 0; xPos < board[yPos].length; xPos++) {
                for (int zPos = board[yPos][xPos].size() - 1; zPos >= 0; zPos--) {
                    if (board[yPos][xPos].get(zPos).getBlockType().toLowerCase().equals("s")) {
                        return new int[]{yPos, xPos};
                    }
                }
            }
        }
        return null;
    }

    /**
     * Output the optional message and exit the program.
     * @param optionalMessage Add a message if you want it to appear on te top
     */
    public void outputAndExit(String optionalMessage) {

        if (!optionalMessage.isEmpty()) {
            System.out.println(optionalMessage);
        }

        //If the program is in LINEMODE, output the board.
        if (mode == LINEMODE) {
            for (int yPos = 0; yPos < board.length; yPos++) {
                for (int xPos = 0; xPos < board[yPos].length; xPos++) {
                    ArrayList<Block> currentCell = board[yPos][xPos];
                    String currBlock = board[yPos][xPos].get(currentCell.size() - 1).getBlockType();
                    switch (currBlock.charAt(0)) {
                        case 'T':
                        case 't':
                            currBlock = "t";
                            break;
                        case 'X':
                        case 'x':
                            currBlock = "x";
                            break;
                        case 'K':
                        case 'k':
                            currBlock = "k";
                            break;
                        case 'H':
                        case 'V':
                            currBlock = "S";
                            break;
                        case 'h':
                        case 'v':
                            currBlock = "s";
                            break;
                        case 'u':
                        case 'd':
                        case 'l':
                        case 'r':
                        case 'U':
                        case 'D':
                        case 'L':
                        case 'R':
                            currBlock = "m";
                            break;
                        case 'S':
                        case 's':
                            currBlock = "Y";
                            break;
                    }
                    System.out.print(currBlock);
                }
                System.out.println();
            }
        }
        System.exit(0);
    }
}
