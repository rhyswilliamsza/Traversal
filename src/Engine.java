import Game.LineMode;

/**
 * This file was created by Rhys Williams,
 * www.rhyswilliams.co.za
 * me@rhyswilliams.co.za
 */
public class Engine {
    public static void main (String[] args) {
        if (args.length == 1) {
            Game.GraphicMode game = new Game.GraphicMode(args[0]);
        } else if (args.length == 2) {
            LineMode game = new LineMode(args[0], args[1]);
        }
    }
}