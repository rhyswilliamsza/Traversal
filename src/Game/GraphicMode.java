package Game;

import javax.swing.*;
import java.awt.*;

/**
 * This file was created by Rhys Williams,
 * www.rhyswilliams.co.za
 * me@rhyswilliams.co.za
 */
public class GraphicMode extends Core {
    JFrame frame;
    JPanel game;

    public GraphicMode(String path) {
        super(path);
        frame = new JFrame();
        game = new JPanel(new GridBagLayout());
        game.setBackground(Color.LIGHT_GRAY);
        draw();
    }

    public void draw() {
        frame.remove(game);
        frame.setTitle("Rhys Williams | " + boardTitle);
        GridBagConstraints c = new GridBagConstraints();
        for (int height = 0; height < board.length; height++) {
            for (int width = 0; width < board[height].length; width++) {
                c.gridy = height;
                c.gridx = width;
                game.add(board[height][width].getIcon(), c);
            }
        }
        frame.add(game);
        frame.pack();
        frame.setVisible(true);
    }

    public void moveLeft() {
        super.requestMove("h");
        draw();
    }
}
