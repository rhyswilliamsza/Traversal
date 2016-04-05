package Components;

import javax.swing.*;

/**
 * This file was created by Rhys Williams,
 * www.rhyswilliams.co.za
 * me@rhyswilliams.co.za
 */
public class Block {
    JLabel enabledIcon = new JLabel();
    JLabel disabledIcon = new JLabel();
    String blockType;
    boolean enabled = true;

    public Block(String type) {
        blockType = type;
        try {
            enabledIcon.setIcon(new ImageIcon(this.getClass().getResource("/Components/Images/tvl_" + blockType + ".png")));
            try {
                disabledIcon.setIcon(new ImageIcon(this.getClass().getResource("/Components/Images/tvl_" + blockType + "_disabled.png")));
            } catch (Exception e) {
                disabledIcon.setIcon(new ImageIcon(this.getClass().getResource("/Components/Images/tvl_" + blockType + ".png")));
            }
        } catch (Exception e) {
            System.out.println("An Image File is Missing");
        }
    }

    public boolean moveLeft() {
        switch (blockType) {
            default: {
                return false;
            }
        }
    }

    public boolean moveRight() {
        switch (blockType) {
            case "s":
                return true;
            default: {
                return false;
            }
        }
    }

    public boolean moveDown() {
        switch (blockType) {
            default: {
                return false;
            }
        }
    }

    public boolean moveUp() {
        switch (blockType) {
            default: {
                return false;
            }
        }
    }

    public JLabel getIcon() {
        if (enabled) {
            return enabledIcon;
        } else {
            return disabledIcon;
        }
    }
}
