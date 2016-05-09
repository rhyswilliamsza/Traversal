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
        super(getBlockChar(status, type), getMoveTrigger(type), new int[]{}, PASSOVER);
        this.type = type;
        this.status = status;
    }

    private static int[] getMoveTrigger(int direction) {
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

    @Override
    public void moveMade(int triggerKey) {
        if (checkIfReact(triggerKey)) {
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
        this.setActionWhenPlayerTouch(Block.END_GAME);
    }

    public void setClosedSwitch() {
        this.status = CLOSED_SWITCH;
        this.setBlockType(getBlockChar(CLOSED_SWITCH, type));
        this.setActionWhenPlayerTouch(Block.PASSOVER);
    }
}
