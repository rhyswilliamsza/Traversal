package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This file was created by Rhys Williams,
 * www.rhyswilliams.co.za
 * me@rhyswilliams.co.za
 */
public class GraphicMode extends Core {
    JFrame gameFrame;
    JPanel gamePanel;
    KeyListener listener;

    /**
     * Instantiate the graphics mode.
     *
     * @param path The path to the board file.
     */
    public GraphicMode(String path) {
        //Call to super
        super(path, GRAPHICMODE);

        //Generate key listener
        generateKeyListener();

        //Generate frame for interface
        generateFrame();

        //Generate game panel
        generateGamePanel();

        //Draw jFrame and show on jPanel
        draw();
    }

    /**
     * Generate the board JFrame
     */
    private void generateFrame() {
        gameFrame = new JFrame();
        gameFrame.addKeyListener(listener);
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameFrame.setResizable(false);
        gameFrame.setTitle("Rhys Williams | " + boardTitle);
    }

    /**
     * Generate the gamePanel with GridBagLayour
     */
    private void generateGamePanel() {
        gamePanel = new JPanel(new GridBagLayout());
    }

    /**
     * Generate the keylistener for keyboard presses
     */
    private void generateKeyListener() {
        listener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //Do nothing
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //Request move from core
                requestMove("" + e.getKeyChar());

                //Draw the changes
                draw();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //Do nothing
            }
        };
    }

    /**
     * Draw the array as the board
     */
    public void draw() {
        //Remove all existing elements to prevent duplicates
        gamePanel.removeAll();

        GridBagConstraints c = new GridBagConstraints();
        for (int height = 0; height < board.length; height++) {
            for (int width = 0; width < board[height].length; width++) {
                c.gridy = height;
                c.gridx = width;
                gamePanel.add(board[height][width].get(board[height][width].size() - 1).getIcon(), c);
            }
        }
        //Set the background of the panel
        gamePanel.setBackground(new Color(37, 116, 169));

        //Set properties of the JFrame
        gameFrame.add(gamePanel);
        gameFrame.repaint();
        gameFrame.pack();
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
    }
}
