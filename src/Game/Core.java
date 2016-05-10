package Game;

import Blocks.Block;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//todo Add comments to everything

/**
 * This file was created by Rhys Williams,
 * www.rhyswilliams.co.za
 * me@rhyswilliams.co.za
 */
public class Core {
    public static final int GRAPHICMODE = 0;
    public static final int LINEMODE = 1;
    public int mode;
    protected ArrayList<Block>[][] board;
    protected String boardTitle;

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
                    board[i][j] = new ArrayList<>(Collections.singletonList(Factories.makeBlock(line.next())));
                }
                line.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void requestMove(String key) {
        switch (key) {
            case "h":
                manageMove(Block.MOVES_LEFT);
                break;
            case "l":
                manageMove(Block.MOVES_RIGHT);
                break;
            case "j":
                manageMove(Block.MOVES_DOWN);
                break;
            case "k":
                manageMove(Block.MOVES_UP);
                break;
            case "q": {
                if (mode == GRAPHICMODE) {
                    outputAndExit("");
                }
            }
        }
    }

    private void manageMove(int triggerKey) {

        boolean doesMove = true;
        /**
         * Check that the player is not going off the board left or right
         */
        int coords[] = findPlayerCellCoords();
        int y = coords[0];
        int x = coords[1];
        if (x == 0 && triggerKey == Block.MOVES_LEFT) {
            doesMove = false;
        }
        if (x >= board[y].length - 1 && triggerKey == Block.MOVES_RIGHT) {
            doesMove = false;
        }

        /**
         * If the player is not going off left or right, do the move.
         */
        if (doesMove) {
            boolean moved = false;
            for (int yPos = 0; yPos < board.length; yPos++) {
                for (int xPos = 0; xPos < board[yPos].length; xPos++) {
                    for (int zPos = board[yPos][xPos].size() - 1; zPos >= 0; zPos--) {
                        Block currentBlock = board[yPos][xPos].get(zPos);
                        if (!currentBlock.getJustMoved()) {

                            //Let block know that a move of triggerKey was made
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

            //Do actions if any pieces were moved
            if (moved) {
                justMoved();
                resetJustMovedStatus();
            }
        }
    }

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
                if (sourceBlock.canWrapX()) {
                    targetX = board[sourceY].length - 1;
                } else {
                    targetX = sourceX;
                }
            }
            if (targetX >= board[sourceY].length) {
                if (sourceBlock.canWrapX()) {
                    targetX = 0;
                } else {
                    targetX = sourceX;
                }
            }

            //Check if block needs to wrap around Y
            if (targetY < 0) {
                if (sourceBlock.canWrapY()) {
                    targetY = board.length - 1;
                } else {
                    targetY = sourceY;
                }
            }
            if (targetY >= board.length) {
                if (sourceBlock.canWrapY()) {
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

    private void resetJustMovedStatus() {
        for (int yPos = 0; yPos < board.length; yPos++) {
            for (int xPos = 0; xPos < board[yPos].length; xPos++) {
                for (int zPos = 0; zPos < board[yPos][xPos].size(); zPos++) {
                    board[yPos][xPos].get(zPos).setJustMoved(false);
                }
            }
        }
    }

    private void justMoved() {

        /**
         * Do actions for the player cell.
         * Check for loss/win and informs blocks in the same cell that the player has moved onto them.
         */
        int[] playerCellCoords = findPlayerCellCoords();
        for (int i = 0; i < board[playerCellCoords[0]][playerCellCoords[1]].size(); i++) {

            if (board[playerCellCoords[0]][playerCellCoords[1]].get(i).getBlockType().toLowerCase().equals("k")) {
                if (board[playerCellCoords[0]][playerCellCoords[1]].get(i).getAvailable()) {
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

            if (board[playerCellCoords[0]][playerCellCoords[1]].get(i).getActionWhenPlayerTouch() == Block.WIN_GAME) {
                outputAndExit("You won!");
            }

            if (board[playerCellCoords[0]][playerCellCoords[1]].get(i).getActionWhenPlayerTouch() == Block.END_GAME) {
                outputAndExit("You lost!");
            }

            //Let the block know that the player has touched the block.
            board[playerCellCoords[0]][playerCellCoords[1]].get(i).playerTouched();
        }
    }

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

    public void outputAndExit(String optionalMessage) {
        if (!optionalMessage.isEmpty()) {
            System.out.println(optionalMessage);
        }

        //todo Work out 'invalid moves'
        if (mode == LINEMODE) {
            for (int yPos = 0; yPos < board.length; yPos++) {
                for (int xPos = 0; xPos < board[yPos].length; xPos++) {
                    ArrayList<Block> currentCell = board[yPos][xPos];
                    String currBlock = board[yPos][xPos].get(currentCell.size() - 1).getBlockType();
                    switch (currBlock) {
                        case "T":
                        case "t":
                            currBlock = "t";
                            break;
                        case "X":
                        case "x":
                            currBlock = "x";
                            break;
                        case "K":
                        case "k":
                            currBlock = "k";
                            break;
                        case "H":
                        case "V":
                            currBlock = "S";
                            break;
                        case "h":
                        case "v":
                            currBlock = "s";
                            break;
                        case "u":
                        case "d":
                        case "l":
                        case "r":
                        case "U":
                        case "D":
                        case "L":
                        case "R":
                            currBlock = "m";
                            break;
                        case "S":
                        case "s":
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
