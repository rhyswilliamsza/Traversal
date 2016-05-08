package Blocks;

/**
 * Created by Rhys Williams
 * www.rhyswilliams.co.za
 * me@rhyswilliams.co.za
 */
public class Switch extends Block {

    public static int VERTICAL_SWITCH = 0;
    public static int HORIZONTAL_SWITCH = 1;
    public static int OPEN_SWITCH = 0;
    public static int CLOSED_SWITCH = 1;

    public Switch(int status, int direction) {
        super("t", new int[]{}, new int[]{}, PASSOVER);
    }
}
