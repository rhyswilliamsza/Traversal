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

    public void moveKeyRequested(String key) {
        switch (key) {
            case "h":
                moveRequested(Block.MOVES_LEFT);
                break;
            case "l":
                moveRequested(Block.MOVES_RIGHT);
                break;
            case "j":
                moveRequested(Block.MOVES_DOWN);
                break;
            case "k":
                moveRequested(Block.MOVES_UP);
                break;
            case "x":
                exit();
                break;
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

    private void makeMove(int[] source, int[] target) {
        int sourceY = source[0];
        int sourceX = source[1];
        int sourceZ = source[2];

        int targetY = target[0];
        int targetX = target[1];

        if (!board[sourceX][sourceY].get(sourceZ).hasJustMoved()) {
            //Check for wrap around
            if (targetX < 0) {

            }
        }
    }

    private void moveRequested(int triggerKey) {
        for (int yPos = 0; yPos < board.length; yPos++) {
            for (int xPos = 0; xPos < board[yPos].length; xPos++) {
                ArrayList<Block> currentCell = board[yPos][xPos];
                for (int zPos = 0; zPos < currentCell.size(); zPos++) {
                    Block currentBlock = currentCell.get(zPos);

                    if (!currentBlock.hasJustMoved()) {

                        if (currentBlock.movesUp(triggerKey)) {
                            makeMove(new int[]{yPos, xPos, zPos}, new int[]{yPos + 1, xPos});
                        }

                        if (currentBlock.movesDown(triggerKey)) {
                            makeMove(new int[]{yPos, xPos, zPos}, new int[]{yPos - 1, xPos});
                        }

                        if (currentBlock.movesLeft(triggerKey)) {
                            makeMove(new int[]{yPos, xPos, zPos}, new int[]{yPos, xPos - 1});
                        }

                        if (currentBlock.movesRight(triggerKey)) {
                            makeMove(new int[]{yPos, xPos, zPos}, new int[]{yPos + 1, xPos + 1});
                        }
                    }

                    if (!board[yPos][xPos].get(zPos).hasJustMoved()) {
                        //Get existing block to move
                        Block existingBlock = board[yPos][xPos].get(zPos);

                        //Set new position information.
                        int newYPos = yPos + 1;
                        int newXPos = xPos;

                        //Check if bounds are reached, otherwise wrap around.


                        //Create new block object from object in original cell
                        Block newBlock = board[yPos][xPos].get(zPos);
                        newBlock.setJustMoved(true);

                        //Add that block object to target cell
                        board[newYPos][newXPos].add(newBlock);
                        board[yPos][xPos].remove(zPos);

                        //If there are no blocks in the old cell, create an empty block.
                        if (board[yPos][xPos].size() == 0) {
                            board[yPos][xPos].add(Factories.makeBlock("."));
                        }
                    }
                }
            }
        }
    }

}

    private void exit() {
        System.exit(0);
    }
}
