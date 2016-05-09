package Blocks;

/**
 * Created by Rhys Williams
 * www.rhyswilliams.co.za
 * me@rhyswilliams.co.za
 */
public class Key extends Block {
    public static int AVAILABLE = 0;
    public static int UNAVAILABLE = 1;
    int available = AVAILABLE;

    public Key() {
        super("k", new int[]{}, new int[]{}, PASSOVER);
    }

    @Override
    public boolean getAvailable() {
        return available == AVAILABLE;
    }

    @Override
    public void playerTouched() {
        available = UNAVAILABLE;
        this.setBlockType("K");
    }
}
