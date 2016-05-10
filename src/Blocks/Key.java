package Blocks;

/**
 * Created by Rhys Williams
 * www.rhyswilliams.co.za
 * me@rhyswilliams.co.za
 */
public class Key extends Block {

    public Key() {
        super("k", new int[]{}, new int[]{}, PASSOVER);
        setEnabled(true);
    }

    @Override
    public boolean getEnabled() {
        return enabled;
    }

    @Override
    public void playerTouched() {
        setEnabled(false);
        this.setBlockType("K");
    }
}
