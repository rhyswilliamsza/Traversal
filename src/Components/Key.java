package Components;

/**
 * Created by Rhys Williams
 * www.rhyswilliams.co.za
 * me@rhyswilliams.co.za
 */
public class Key extends Block {
    public static int AVAILABLE = 0;
    public static int UNAVAILABLE = 1;

    public Key() {
        super("k");
        this.blockType = "t";
    }
}
