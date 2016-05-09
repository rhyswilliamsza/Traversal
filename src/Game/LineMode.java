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
public class LineMode extends Core {
    public LineMode(String path, String moves) {
        super(path);
        try {
            Scanner moveScan = new Scanner(new File(moves)).useDelimiter("");
            while (moveScan.hasNext()) {
                String move = moveScan.next();
                if (move.equals("x")) {
                    outputAndExit("");
                }
                requestMove(move, LINEMODE);
            }
            moveScan.close();
            outputAndExit("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void outputAndExit(String optionalMessage) {
        if (!optionalMessage.isEmpty()) {
            System.out.println(optionalMessage);
        }
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
        exit();
    }
}
