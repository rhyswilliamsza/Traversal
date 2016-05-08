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

    public GraphicMode(String path) {
        super(path);
        generateKeyListener();
        generateFrame();
        generateGamePanel();
        draw();
    }

    private void generateFrame() {
        gameFrame = new JFrame();
        gameFrame.addKeyListener(listener);
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameFrame.setResizable(false);
    }

    private void generateGamePanel() {
        gamePanel = new JPanel(new GridBagLayout());
    }

    private void generateKeyListener() {
        listener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                moveKeyRequested("" + e.getKeyChar());
                draw();
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
    }

    public void draw() {
        gamePanel.removeAll();
        gameFrame.setTitle("Rhys Williams | " + boardTitle);
        GridBagConstraints c = new GridBagConstraints();
        for (int height = 0; height < board.length; height++) {
            for (int width = 0; width < board[height].length; width++) {
                c.gridy = height;
                c.gridx = width;
                gamePanel.add(board[height][width].get(board[height][width].size() - 1).getIcon(), c);
            }
        }
        gameFrame.add(gamePanel);
        gameFrame.pack();
        gameFrame.setVisible(true);
        gameFrame.repaint();
    }
}
