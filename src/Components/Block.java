package Components;

import javax.swing.*;

/**
 * This file was created by Rhys Williams,
 * www.rhyswilliams.co.za
 * me@rhyswilliams.co.za
 */
public class Block {
    JLabel icon = new JLabel();
    String blockType;

    public Block(String type) {
        blockType = type;
        try {
            icon.setIcon(new ImageIcon(this.getClass().getResource("/Components/Images/tvl_" + blockType + ".png")));
        } catch (Exception e) {
            System.out.println("An Image File is Missing");
        }
    }

    public String getBlockType() {
        return blockType;
    }

    public JLabel getIcon() {
        return icon;
    }
}
