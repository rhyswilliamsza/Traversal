package Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This file was created by Rhys Williams,
 * www.rhyswilliams.co.za
 * me@rhyswilliams.co.za
 */
public class LineMode extends Core {
    /**
     * Instantiate the LINEMODE version of the game
     *
     * @param boardPath Path of the board file
     * @param movesPath Path of the moves file
     */
    public LineMode(String boardPath, String movesPath) {
        //Instantiate the super core
        super(boardPath, LINEMODE);

        //Scan in the entire moves file and request moves from core for each key
        try {
            Scanner moveScan = new Scanner(new File(movesPath)).useDelimiter("");
            while (moveScan.hasNext()) {
                String move = moveScan.next();
                if (move.equals("q")) {
                    outputAndExit("Incorrect move");
                }
                if (move.equals("x")) {
                    outputAndExit("");
                }
                requestMove(move);
            }
            moveScan.close();

            //Call output and call if not needed
            outputAndExit("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


}
