package Blocks;

import javax.swing.*;

/**
 * This file was created by Rhys Williams,
 * www.rhyswilliams.co.za
 * me@rhyswilliams.co.za
 */

//todo Try simplify class

public class Block {
    //Static actionWhenPlayerTouch Variables
    public final static int PASSOVER = 0;
    public final static int END_GAME = 1;
    public final static int WIN_GAME = 2;

    //Static moveDirection and blockTrigger Variables
    public final static int MOVES_UP = 0;
    public final static int MOVES_RIGHT = 1;
    public final static int MOVES_DOWN = 2;
    public final static int MOVES_LEFT = 3;

    //Variables used by the class
    protected int moveDirection[];
    protected int blockTrigger[];
    protected int actionWhenPlayerTouch;
    protected boolean available;
    protected JLabel icon;
    protected String blockType;
    protected boolean justMoved = false;
    protected boolean canWrapX = false;
    protected boolean canWrapY = false;

    /**
     * @param blockType
     * @param blockTrigger
     * @param moveDirection
     * @param actionWhenPlayerTouch
     */
    public Block(String blockType, int[] blockTrigger, int[] moveDirection, int actionWhenPlayerTouch) {
        //Assign params to variables
        this.blockType = blockType;
        this.actionWhenPlayerTouch = actionWhenPlayerTouch;
        this.blockTrigger = blockTrigger;
        this.moveDirection = moveDirection;
    }

    public boolean getJustMoved() {
        return this.justMoved;
    }

    public void setJustMoved(boolean justMoved) {
        this.justMoved = justMoved;
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

    public JLabel getIcon() {
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
        return icon;
    }

    public boolean getAvailable() {
        return this.available;
    }

    protected boolean checkIfTriggered(int triggerKey) {
        for (int i = 0; i < blockTrigger.length; i++) {
            if (blockTrigger[i] == triggerKey) {
                return true;
            }
        }
        return false;
    }

    /**
     * Override this method for use by a child.
     *
     * @param move
     */
    public void moveMade(int move) {
        //Overwrite this method in children for action
    }

    /**
     * Override this method for use by a child.
     */
    public void playerTouched() {
        //Overwrite this method in children for action
    }

    /**
     * Override this method for use by a child.
     */
    public void changeRequested() {
        //Overwrite this method in children for action
    }

    public boolean getMovesUp(int triggerKey) {
        if (checkIfTriggered(triggerKey)) {
            for (int i = 0; i < moveDirection.length; i++) {
                if (moveDirection[i] == MOVES_UP) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean getMovesDown(int triggerKey) {
        if (checkIfTriggered(triggerKey)) {
            for (int i = 0; i < moveDirection.length; i++) {
                if (moveDirection[i] == MOVES_DOWN) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean getMovesRight(int triggerKey) {
        if (checkIfTriggered(triggerKey)) {
            for (int i = 0; i < moveDirection.length; i++) {
                if (moveDirection[i] == MOVES_RIGHT) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean getMovesLeft(int triggerKey) {
        if (checkIfTriggered(triggerKey)) {
            for (int i = 0; i < moveDirection.length; i++) {
                if (moveDirection[i] == MOVES_LEFT) {
                    return true;
                }
            }
        }
        return false;
    }

}