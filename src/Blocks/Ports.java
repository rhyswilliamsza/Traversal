package Blocks;

/**
 * Created by Rhys Williams
 * www.rhyswilliams.co.za
 * me@rhyswilliams.co.za
 */
public class Ports extends Block {
    public static int CLOSED = 0;
    public static int OPEN = 1;

    public Ports(int status) {
        super(getPieceChar(status), new int[]{}, new int[]{}, Block.PASSOVER);
    }

    private static String getPieceChar(int status) {
        if (status == CLOSED) {
            return "p";
        } else {
            return "P";
        }
    }
}
