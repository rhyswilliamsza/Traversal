package Blocks;

import javax.swing.*;

/**
 * This file was created by Rhys Williams,
 * www.rhyswilliams.co.za
 * me@rhyswilliams.co.za
 */
public class Block {
    //Static actionWhenPlayerTouch Variables
    public final static int PASSOVER = 0;
    public final static int END_GAME = 1;
    public final static int WIN_GAME = 2;

    //Static moveDirection and moveTrigger Variables
    public final static int MOVES_UP = 0;
    public final static int MOVES_RIGHT = 1;
    public final static int MOVES_DOWN = 2;
    public final static int MOVES_LEFT = 3;

    //Variables used by the class
    protected int moveDirection[];
    protected int moveTrigger[];
    protected int actionWhenPlayerTouch;
    protected JLabel icon;
    protected String blockType;
    protected boolean justMoved = false;
    protected boolean canWrapX = false;
    protected boolean canWrapY = false;

    /**
     * @param blockType
     * @param moveTrigger
     * @param moveDirection
     * @param actionWhenPlayerTouch
     */
    public Block(String blockType, int[] moveTrigger, int[] moveDirection, int actionWhenPlayerTouch) {
        //Assign params to variables
        this.blockType = blockType;
        this.actionWhenPlayerTouch = actionWhenPlayerTouch;
        this.moveTrigger = moveTrigger;
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

    public void setBlockType(String blockType) {
        this.blockType = blockType;
    }

    public int getActionWhenPlayerTouch() {
        return actionWhenPlayerTouch;
    }

    public void setActionWhenPlayerTouch(int actionWhenPlayerTouch) {
        this.actionWhenPlayerTouch = actionWhenPlayerTouch;
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
        generateIcon();
        return icon;
    }

    protected boolean checkIfReact(int triggerKey) {
        for (int i = 0; i < moveTrigger.length; i++) {
            if (moveTrigger[i] == triggerKey) {
                return true;
            }
        }
        return false;
    }

    public boolean movesUp(int triggerKey) {
        if (checkIfReact(triggerKey)) {
            for (int i = 0; i < moveDirection.length; i++) {
                if (moveDirection[i] == MOVES_UP) {
                    return true;
                }
            }
        }
        return false;
    }

    public void moveMade(int move) {
        //Overwrite this method in children for action
    }

    public boolean movesDown(int triggerKey) {
        if (checkIfReact(triggerKey)) {
            for (int i = 0; i < moveDirection.length; i++) {
                if (moveDirection[i] == MOVES_DOWN) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean movesRight(int triggerKey) {
        if (checkIfReact(triggerKey)) {
            for (int i = 0; i < moveDirection.length; i++) {
                if (moveDirection[i] == MOVES_RIGHT) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean movesLeft(int triggerKey) {
        if (checkIfReact(triggerKey)) {
            for (int i = 0; i < moveDirection.length; i++) {
                if (moveDirection[i] == MOVES_LEFT) {
                    return true;
                }
            }
        }
        return false;
    }

}