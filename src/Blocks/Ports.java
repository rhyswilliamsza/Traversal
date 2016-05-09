package Blocks;

/**
 * Created by Rhys Williams
 * www.rhyswilliams.co.za
 * me@rhyswilliams.co.za
 */
public class Ports extends Block {
    public static int CLOSED = 0;
    public static int OPEN = 1;
    int status;

    public Ports(int status) {
        super(getBlockChar(status), new int[]{}, new int[]{}, getActionWhenPlayerTouch(status));
        this.status = status;
    }

    private static String getBlockChar(int status) {
        if (status == CLOSED) {
            return "p";
        } else {
            return "P";
        }
    }

    private static int getActionWhenPlayerTouch(int status) {
        if (status == OPEN) {
            return Block.PASSOVER;
        }

        if (status == CLOSED) {
            return Block.END_GAME;
        }

        return PASSOVER;
    }

    @Override
    public void changeRequested () {
        if (status == OPEN) {
            setCLOSED();
        } else if (status == CLOSED) {
            setOPEN();
        }
    }

    private void setOPEN() {
        status = OPEN;
        setBlockType("P");
        this.setActionWhenPlayerTouch(PASSOVER);
    }

    private void setCLOSED() {
        status = CLOSED;
        setBlockType("p");
        this.setActionWhenPlayerTouch(END_GAME);
    }
}
