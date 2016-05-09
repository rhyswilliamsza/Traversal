package Game;

import Blocks.Block;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * This file was created by Rhys Williams,
 * www.rhyswilliams.co.za
 * me@rhyswilliams.co.za
 */
public class Core {
    protected ArrayList<Block>[][] board;
    protected String boardTitle;

    public Core(String boardPath) {
        GenerateBoard(boardPath);
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
                moveRequest(Block.MOVES_LEFT);
                break;
            case "l":
                moveRequest(Block.MOVES_RIGHT);
                break;
            case "j":
                moveRequest(Block.MOVES_DOWN);
                break;
            case "k":
                moveRequest(Block.MOVES_UP);
                break;
            case "q":
                exit();
                break;
        }
    }

    private void moveRequest(int triggerKey) {
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
                            justMoved();
                        }
                        if (currentBlock.getMovesDown(triggerKey)) {
                            doMove(new int[]{yPos, xPos, zPos}, new int[]{yPos + 1, xPos});
                            justMoved();
                        }
                        if (currentBlock.getMovesLeft(triggerKey)) {
                            doMove(new int[]{yPos, xPos, zPos}, new int[]{yPos, xPos - 1});
                            justMoved();
                        }
                        if (currentBlock.getMovesRight(triggerKey)) {
                            doMove(new int[]{yPos, xPos, zPos}, new int[]{yPos, xPos + 1});
                            justMoved();
                        }
                    }
                }
            }
        }
        //Reset the just moved status after processing has completed.
        resetJustMovedStatus();
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
                System.out.println("YOU WON!");
            }

            if (board[playerCellCoords[0]][playerCellCoords[1]].get(i).getActionWhenPlayerTouch() == Block.END_GAME) {
                System.out.println("YOU LOSE!");
                exit();
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


    private void exit() {
        System.exit(0);
    }
}
