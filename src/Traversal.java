/**
 * This file was created by Rhys Williams,
 * www.rhyswilliams.co.za
 * me@rhyswilliams.co.za
 */
public class Traversal {
    /**
     * Main Method launches the app:
     * Graphic Mode if one argument is supplied,
     * Line Mode if two are supplied.
     *
     * @param args
     */
    public static void main(String[] args) {
        if (args.length == 1) {
            new Game.GraphicMode(args[0]);
        } else if (args.length == 2) {
            new Game.LineMode(args[0], args[1]);
        }

    }
}