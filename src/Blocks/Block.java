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

    //Static moveDirection and blockTrigger Variables
    public final static int MOVES_UP = 0;
    public final static int MOVES_RIGHT = 1;
    public final static int MOVES_DOWN = 2;
    public final static int MOVES_LEFT = 3;

    //Variables used by the class
    protected int moveDirection[];
    protected int blockTrigger[];
    protected int actionWhenPlayerTouch;
    protected JLabel icon;
    protected String blockType;
    protected boolean enabled = true;
    protected boolean justMoved = false;
    protected boolean canWrapX = false;
    protected boolean canWrapY = false;

    /**
     * Instantiate the block object
     *
     * @param blockType             Type of block, as specified in the instructions
     * @param blockTrigger          Trigger array for when the block needs to react
     * @param moveDirection         Direction array of movement, if any.
     * @param actionWhenPlayerTouch Action performed when object is touched with player.
     */
    public Block(String blockType, int[] blockTrigger, int[] moveDirection, int actionWhenPlayerTouch) {
        //Assign params to variables
        this.blockType = blockType;
        this.actionWhenPlayerTouch = actionWhenPlayerTouch;
        this.blockTrigger = blockTrigger;
        this.moveDirection = moveDirection;
    }

    /**
     * Get the just moved status
     *
     * @return justMoved boolean
     */
    public boolean getJustMoved() {
        return this.justMoved;
    }

    /**
     * Set the just moved status
     *
     * @param justMoved boolean of justMoved
     */
    public void setJustMoved(boolean justMoved) {
        this.justMoved = justMoved;
    }

    /**
     * Check if the block can wrap around X
     *
     * @return boolean
     */
    public boolean getCanWrapX() {
        return canWrapX;
    }

    /**
     * Set wrap around X status
     *
     * @param canWrapX boolean
     */
    public void setCanWrapX(boolean canWrapX) {
        this.canWrapX = canWrapX;
    }

    /**
     * Check if the block can wrap around Y
     *
     * @return boolean
     */
    public boolean getCanWrapY() {
        return canWrapY;
    }

    /**
     * Set wrap around Y status
     *
     * @param canWrapY boolean
     */
    public void setCanWrapY(boolean canWrapY) {
        this.canWrapY = canWrapY;
    }

    /**
     * Get block type as String
     *
     * @return String of blockType, as stated in the instructions.
     */
    public String getBlockType() {
        return blockType;
    }

    /**
     * Set block type of String
     *
     * @param blockType String of blockType, as stated in the instructions
     */
    public void setBlockType(String blockType) {
        this.blockType = blockType;
    }

    /**
     * Return action of what happens when the player touches the block
     *
     * @return actionWhenPlayerTouch action
     */
    public int getActionWhenPlayerTouch() {
        return actionWhenPlayerTouch;
    }

    /**
     * Set action of what happens when the player touches the block
     *
     * @param actionWhenPlayerTouch actionWhenPlayerTouch action
     */
    public void setActionWhenPlayerTouch(int actionWhenPlayerTouch) {
        this.actionWhenPlayerTouch = actionWhenPlayerTouch;
    }

    /**
     * Get icon from Images and return as JLabel
     *
     * @return jLabel of Icon
     */
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

    /**
     * Check if the block is enabled
     *
     * @return boolean of enabled status
     */
    public boolean getEnabled() {
        return this.enabled;
    }

    /**
     * Set if the block is enabled
     *
     * @param enabled boolean of enabled status
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Check if block is triggered by the specified key
     *
     * @param triggerKey Trigger key
     * @return boolean
     */
    protected boolean isTriggered(int triggerKey) {
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

    /**
     * Get if the block moves up when the triggerKey is pressed
     *
     * @param triggerKey Trigger key
     * @return boolean
     */
    public boolean getMovesUp(int triggerKey) {
        if (isTriggered(triggerKey)) {
            for (int i = 0; i < moveDirection.length; i++) {
                if (moveDirection[i] == MOVES_UP) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Get if the block moves down when the triggerKey is pressed
     *
     * @param triggerKey Trigger key
     * @return boolean
     */
    public boolean getMovesDown(int triggerKey) {
        if (isTriggered(triggerKey)) {
            for (int i = 0; i < moveDirection.length; i++) {
                if (moveDirection[i] == MOVES_DOWN) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Get if the block moves right when the triggerKey is pressed
     *
     * @param triggerKey Trigger key
     * @return boolean
     */
    public boolean getMovesRight(int triggerKey) {
        if (isTriggered(triggerKey)) {
            for (int i = 0; i < moveDirection.length; i++) {
                if (moveDirection[i] == MOVES_RIGHT) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Get if the block moves left when the triggerKey is pressed
     *
     * @param triggerKey Trigger key
     * @return boolean
     */
    public boolean getMovesLeft(int triggerKey) {
        if (isTriggered(triggerKey)) {
            for (int i = 0; i < moveDirection.length; i++) {
                if (moveDirection[i] == MOVES_LEFT) {
                    return true;
                }
            }
        }
        return false;
    }

}