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

    public boolean movesUp(int triggerKey) {
        if (triggerKey == MOVES_UP) {
            return true;
        }
        return false;
    }

    public boolean movesDown(int triggerKey) {
        if (triggerKey == MOVES_DOWN) {
            return true;
        }
        return false;
    }

    public boolean movesRight(int triggerKey) {
        if (triggerKey == MOVES_RIGHT) {
            return true;
        }
        return false;
    }

    public boolean movesLeft(int triggerKey) {
        if (triggerKey == MOVES_LEFT) {
            return true;
        }
        return false;
    }
}
