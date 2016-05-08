package Blocks;

import javax.swing.*;

/**
 * This file was created by Rhys Williams,
 * www.rhyswilliams.co.za
 * me@rhyswilliams.co.za
 */
public class Block {
    //Static actionWhenTouched Variables
    public final static int PASSOVER = 0;
    public final static int END_GAME = 1;
    public final static int WIN_GAME = 2;

    //Static moveDirection Variables
    public final static int MOVES_UP = 0;
    public final static int MOVES_RIGHT = 1;
    public final static int MOVES_DOWN = 2;
    public final static int MOVES_LEFT = 3;

    //Variables used by the class
    private int moveDirection[];
    private int reactTrigger[];
    private int actionWhenTouched;
    private JLabel icon;
    private String blockType;
    private boolean justMoved = false;
    private boolean canWrapX = false;
    private boolean canWrapY = false;

    /**
     * @param blockType
     * @param reactTrigger
     * @param moveDirection
     * @param actionWhenTouched
     */
    public Block(String blockType, int[] reactTrigger, int[] moveDirection, int actionWhenTouched) {
        //Assign params to variables
        this.blockType = blockType;
        this.actionWhenTouched = actionWhenTouched;
        this.reactTrigger = reactTrigger;
        this.moveDirection = moveDirection;

        //Generate Icon
        generateIcon();
    }

    public void setJustMoved(boolean justMoved) {
        this.justMoved = justMoved;
    }

    public boolean hasJustMoved() {
        return this.justMoved;
    }

    public boolean canWrapX() {
        return canWrapX;
    }

    public boolean canWrapY() {
        return canWrapY;
    }

    public void setCanWrapX(boolean canWrapX) {
        this.canWrapX = canWrapX;
    }

    public void setCanWrapY(boolean canWrapY) {
        this.canWrapY = canWrapY;
    }

    public String getBlockType() {
        return blockType;
    }

    public int getActionWhenTouched() {
        return actionWhenTouched;
    }

    private void generateIcon() {
        icon = new JLabel();
        try {
            if (blockType.toLowerCase().equals(blockType)) {
                icon.setIcon(new ImageIcon(this.getClass().getResource("/Blocks/Images/tvl_" + blockType + ".png")));
            } else {
                icon.setIcon(new ImageIcon(this.getClass().getResource("/Blocks/Images/tvl_" + blockType.toLowerCase() + "_caps.png")));
            }
        } catch (Exception e) {
            System.out.println("The image file for " + blockType + " is missing.");
        }
    }

    public JLabel getIcon() {
        return icon;
    }

    private boolean checkIfShouldReact(int triggerKey) {
        for (int i = 0; i < reactTrigger.length; i++) {
            if (reactTrigger[i] == triggerKey) {
                return true;
            }
        }
        return false;
    }

    public boolean movesUp(int triggerKey) {
        if (checkIfShouldReact(triggerKey)) {
            for (int i = 0; i < moveDirection.length; i++) {
                if (moveDirection[i] == MOVES_UP) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean movesDown(int triggerKey) {
        if (checkIfShouldReact(triggerKey)) {
            for (int i = 0; i < moveDirection.length; i++) {
                if (moveDirection[i] == MOVES_DOWN) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean movesRight(int triggerKey) {
        if (checkIfShouldReact(triggerKey)) {
            for (int i = 0; i < moveDirection.length; i++) {
                if (moveDirection[i] == MOVES_RIGHT) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean movesLeft(int triggerKey) {
        if (checkIfShouldReact(triggerKey)) {
            for (int i = 0; i < moveDirection.length; i++) {
                if (moveDirection[i] == MOVES_LEFT) {
                    return true;
                }
            }
        }
        return false;
    }

}