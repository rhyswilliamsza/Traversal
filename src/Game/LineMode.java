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
    public LineMode(String path, String moves) {
        super(path, LINEMODE);
        try {
            Scanner moveScan = new Scanner(new File(moves)).useDelimiter("");
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
            outputAndExit("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


}
