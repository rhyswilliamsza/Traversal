package Blocks;

/**
 * Created by Rhys Williams
 * www.rhyswilliams.co.za
 * me@rhyswilliams.co.za
 */
public class Player extends Block {
    public Player() {
        super("s", new int[]{MOVES_DOWN, MOVES_LEFT, MOVES_RIGHT, MOVES_UP}, new int[]{MOVES_DOWN, MOVES_LEFT, MOVES_RIGHT, MOVES_UP}, Block.PASSOVER);
        this.setCanWrapY(true);
    }

    public boolean getMovesUp(int triggerKey) {
        return triggerKey == MOVES_UP;
    }

    public boolean getMovesDown(int triggerKey) {
        return triggerKey == MOVES_DOWN;
    }

    public boolean getMovesRight(int triggerKey) {
        return triggerKey == MOVES_RIGHT;
    }

    public boolean getMovesLeft(int triggerKey) {
        return triggerKey == MOVES_LEFT;
    }
}
