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
    int type;
    int status;

    public Switch(int status, int type) {
        super(getBlockChar(status, type), getTrigger(type), new int[]{}, getActionWhenPlayerTouch(status));
        this.type = type;
        this.status = status;
    }

    private static int[] getTrigger(int direction) {
        if (direction == VERTICAL_SWITCH) {
            return new int[]{MOVES_UP, MOVES_DOWN};
        }

        if (direction == HORIZONTAL_SWITCH) {
            return new int[]{MOVES_LEFT, MOVES_RIGHT};
        }
        return new int[]{};
    }

    private static String getBlockChar(int status, int type) {
        if (type == VERTICAL_SWITCH) {
            if (status == OPEN_SWITCH) {
                return "V";
            }

            if (status == CLOSED_SWITCH) {
                return "v";
            }
        }

        if (type == HORIZONTAL_SWITCH) {
            if (status == OPEN_SWITCH) {
                return "H";
            }

            if (status == CLOSED_SWITCH) {
                return "h";
            }
        }

        return null;
    }

    private static int getActionWhenPlayerTouch(int status) {
        if (status == OPEN_SWITCH) {
            return Block.PASSOVER;
        }

        if (status == CLOSED_SWITCH) {
            return Block.END_GAME;
        }

        return PASSOVER;
    }

    @Override
    public void moveMade(int triggerKey) {
        if (checkIfTriggered(triggerKey)) {
            if (status == OPEN_SWITCH) {
                setClosedSwitch();
            } else if (status == CLOSED_SWITCH) {
                setOpenSwitch();
            }
        }
    }

    public void setOpenSwitch() {
        this.status = OPEN_SWITCH;
        this.setBlockType(getBlockChar(OPEN_SWITCH, type));
        this.setActionWhenPlayerTouch(Block.PASSOVER);
    }

    public void setClosedSwitch() {
        this.status = CLOSED_SWITCH;
        this.setBlockType(getBlockChar(CLOSED_SWITCH, type));
        this.setActionWhenPlayerTouch(Block.END_GAME);
    }
}
