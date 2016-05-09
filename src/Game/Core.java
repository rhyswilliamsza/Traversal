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

    public void printBoard() {
        for (int yPos = 0; yPos < board.length; yPos++) {
            System.out.println();
            for (int xPos = 0; xPos < board[yPos].length; xPos++) {
                ArrayList<Block> currentCell = board[yPos][xPos];
                System.out.print(board[yPos][xPos].get(currentCell.size() - 1).getBlockType());
            }
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

    private void playerRules () {

    }

    private void makeMove(int[] source, int[] target) {
        int sourceY = source[0];
        int sourceX = source[1];
        int sourceZ = source[2];

        int targetY = target[0];
        int targetX = target[1];

        if (!board[sourceY][sourceX].get(sourceZ).hasJustMoved()) {
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

            //If there are no blocks in the old cell, create an empty block.
            if (board[sourceY][sourceX].size() == 0) {
                board[sourceY][sourceX].add(Factories.makeBlock("."));
            }

        }
    }

    private void moveRequested(int triggerKey) {
        for (int yPos = 0; yPos < board.length; yPos++) {
            for (int xPos = 0; xPos < board[yPos].length; xPos++) {
                for (int zPos = board[yPos][xPos].size() -1; zPos >= 0; zPos--) {
                    Block currentBlock = board[yPos][xPos].get(zPos);
                    if (!currentBlock.hasJustMoved()) {

                        if (currentBlock.movesUp(triggerKey)) {
                            makeMove(new int[]{yPos, xPos, zPos}, new int[]{yPos - 1, xPos});
                        }
                        if (currentBlock.movesDown(triggerKey)) {
                            makeMove(new int[]{yPos, xPos, zPos}, new int[]{yPos + 1, xPos});
                        }
                        if (currentBlock.movesLeft(triggerKey)) {
                            makeMove(new int[]{yPos, xPos, zPos}, new int[]{yPos, xPos - 1});
                        }
                        if (currentBlock.movesRight(triggerKey)) {
                            makeMove(new int[]{yPos, xPos, zPos}, new int[]{yPos, xPos + 1});
                        }
                    }
                }
            }
        }
        resetJustMovedStatus();
    }

    private void exit() {
        System.exit(0);
    }
}
