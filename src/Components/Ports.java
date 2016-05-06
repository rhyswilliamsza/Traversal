package Components;

/**
 * Created by Rhys Williams
 * www.rhyswilliams.co.za
 * me@rhyswilliams.co.za
 */
public class Ports extends Block {
    public static int CLOSED = 0;
    public static int OPEN = 1;

    public Ports(int open) {
        super("k");
        this.blockType = "t";
    }
}
