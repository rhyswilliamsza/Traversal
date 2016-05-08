package Blocks;

/**
 * Created by Rhys Williams
 * www.rhyswilliams.co.za
 * me@rhyswilliams.co.za
 */
public class Mover extends Block {
    public static int VERTICAL_MOVER = 0;
    public static int HORIZONTAL_MOVER = 1;

    public Mover(int type, int direction) {
        super(getPieceChar(type, direction), getReactTrigger(type), new int[]{direction}, END_GAME);
        this.setCanWrapX(true);
        this.setCanWrapY(true);
    }

    private static int[] getReactTrigger(int type) {
        if (type == VERTICAL_MOVER) {
            return new int[]{MOVES_UP, MOVES_DOWN};
        }

        if (type == HORIZONTAL_MOVER) {
            return new int[]{MOVES_LEFT, MOVES_RIGHT};
        }

        return null;
    }

    private static String getPieceChar(int type, int direction) {
        if (type == VERTICAL_MOVER) {
            if (direction == MOVES_UP) {
                return "U";
            }
            if (direction == MOVES_DOWN) {
                return "D";
            }
            if (direction == MOVES_RIGHT) {
                return "R";
            }
            if (direction == MOVES_LEFT) {
                return "L";
            }
        }

        if (type == HORIZONTAL_MOVER) {
            if (direction == MOVES_UP) {
                return "u";
            }
            if (direction == MOVES_DOWN) {
                return "d";
            }
            if (direction == MOVES_RIGHT) {
                return "r";
            }
            if (direction == MOVES_LEFT) {
                return "l";
            }
        }

        return null;
    }
}
