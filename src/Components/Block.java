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
            enabledIcon.setIcon(new ImageIcon(this.getClass().getResource("/Components/Images/" + blockType + "_enabled.png")));
            try {
                disabledIcon.setIcon(new ImageIcon(this.getClass().getResource("/Components/Images/" + blockType + "_disabled.png")));
            } catch (Exception e) {
                disabledIcon.setIcon(new ImageIcon(this.getClass().getResource("/Components/Images/" + blockType + "_enabled.png")));
            }
        } catch (Exception e) {
            System.out.println("An Image File is Missing");
        }
    }

    public void setEnabled() {
        enabled = true;
    }

    public void setDisabled() {
        enabled = false;
    }

    public String getBlockType() {
        return blockType;
    }

    public JLabel getIcon() {
        if (enabled) {
            return enabledIcon;
        } else {
            return disabledIcon;
        }
    }
}
