package Components;

/**
 * Created by Rhys Williams
 * www.rhyswilliams.co.za
 * me@rhyswilliams.co.za
 */
public class Mover extends Block {
    public static int MOVES_UP = 0;
    public static int MOVES_RIGHT = 1;
    public static int MOVES_DOWN = 2;
    public static int MOVES_LEFT = 3;
    public static int VERTICAL_MOVER = 0;
    public static int HORIZONTAL_MOVER = 1;

    public Mover(int type, int direction) {
        super("s");
        this.blockType = "s";
    }
}
