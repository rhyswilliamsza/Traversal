package Game;

import Blocks.*;

/**
 * Created by Rhys Williams
 * www.rhyswilliams.co.za
 * me@rhyswilliams.co.za
 */
public class Factories {

    /**
     * Return a block object using the blockCode, as provided in the instructions.
     *
     * @param blockCode Code of block, as provided in the instructions.
     * @return The block object corresponding to the blockCode
     */
    public static Block makeBlock(String blockCode) {
        switch (blockCode.charAt(0)) {
            case 's':
            case 'S': {
                return new Player();
            }
            case 't':
            case 'T': {
                return new Target();
            }
            case 'x':
            case 'X': {
                return new Wall();
            }
            case 'u': {
                return new Mover(Mover.HORIZONTAL_MOVER, Mover.MOVES_UP);
            }
            case 'd': {
                return new Mover(Mover.HORIZONTAL_MOVER, Mover.MOVES_DOWN);
            }
            case 'l': {
                return new Mover(Mover.HORIZONTAL_MOVER, Mover.MOVES_LEFT);
            }
            case 'r': {
                return new Mover(Mover.HORIZONTAL_MOVER, Mover.MOVES_RIGHT);
            }
            case 'U': {
                return new Mover(Mover.VERTICAL_MOVER, Mover.MOVES_UP);
            }
            case 'D': {
                return new Mover(Mover.VERTICAL_MOVER, Mover.MOVES_DOWN);
            }
            case 'L': {
                return new Mover(Mover.VERTICAL_MOVER, Mover.MOVES_LEFT);
            }
            case 'R': {
                return new Mover(Mover.VERTICAL_MOVER, Mover.MOVES_RIGHT);
            }
            case 'h': {
                return new Switch(Switch.CLOSED_SWITCH, Switch.HORIZONTAL_SWITCH);
            }
            case 'H': {
                return new Switch(Switch.OPEN_SWITCH, Switch.HORIZONTAL_SWITCH);
            }
            case 'V': {
                return new Switch(Switch.OPEN_SWITCH, Switch.VERTICAL_SWITCH);
            }
            case 'v': {
                return new Switch(Switch.CLOSED_SWITCH, Switch.VERTICAL_SWITCH);
            }
            case 'k':
            case 'K': {
                return new Key();
            }
            case 'p': {
                return new Ports(Ports.CLOSED);
            }
            case 'P': {
                return new Ports(Ports.OPEN);
            }
            default: {
                return new Block(".", new int[]{}, new int[]{}, Block.PASSOVER);
            }
        }
    }
}
