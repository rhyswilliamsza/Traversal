package Game;

import Components.Block;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This file was created by Rhys Williams,
 * www.rhyswilliams.co.za
 * me@rhyswilliams.co.za
 */
public class Core {
    Block[][][] board;
    String boardTitle;

    public Core(String boardPath) {
        GenerateBoard(boardPath);
    }

    private void GenerateBoard(String boardPath) {
        try {
            Scanner boardScan = new Scanner(new File(boardPath));
            boardTitle = boardScan.nextLine();
            Scanner boardDimensionsScan = new Scanner(boardScan.nextLine());
            int height = boardDimensionsScan.nextInt();
            int width = boardDimensionsScan.nextInt();
            boardDimensionsScan.close();
            board = new Block[height][width][3];

            for (int i = 0; i < height; i++) {
                Scanner line = new Scanner(boardScan.nextLine()).useDelimiter("");
                for (int j = 0; j < width; j++) {
                    board[i][j][0] = Factories.makeBlock(line.next());
                }
                line.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void requestMove(String key) {
        System.out.println(key);
        switch (key) {
            case "h":
                moveLeft();
                break;
            case "l":
                moveRight();
                break;
            case "j":
                moveDown();
                break;
            case "k":
                moveUp();
                break;
            case "x":
                exit();
                break;
        }
    }

    private void moveLeft() {
        for (int height = 0; height < board.length; height++) {
            for (int width = 0; width < board[height].length; width++) {

            }
        }
    }

    private void moveRight() {
        for (int height = 0; height < board.length; height++) {
            for (int width = 0; width < board[height].length; width++) {

            }
        }
    }

    private void moveDown() {
        for (int height = 0; height < board.length; height++) {
            for (int width = 0; width < board[height].length; width++) {

            }
        }
    }

    private void moveUp() {
        for (int height = 0; height < board.length; height++) {
            for (int width = 0; width < board[height].length; width++) {

            }
        }
    }

    private void exit() {
        System.exit(0);
    }
}
