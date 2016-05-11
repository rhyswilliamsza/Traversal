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
    JFrame splashFrame;
    KeyListener listener;

    /**
     * Instantiate the graphics mode.
     *
     * @param path The path to the board file.
     */
    public GraphicMode(String path) {
        //Call to super
        super(path, GRAPHICMODE);

        //Generate Splash Screen
        generateSplash();

        //Generate key listener
        generateKeyListener();

        //Generate frame for interface
        generateGameFrame();

        //Generate game panel
        generateGamePanel();

        //Close Splash Screen
        closeSplash();

        //Draw jFrame and show on jPanel
        drawGameFrame();
    }

    private void generateSplash() {
        //Code to generate splash screen frame
        splashFrame = new JFrame();
        splashFrame.setUndecorated(true);

        //Generate splash screen panel
        JPanel splashPanel = new JPanel();
        JLabel image = new JLabel();
        image.setIcon(new ImageIcon(this.getClass().getResource("/Blocks/Images/SplashScreen.png")));
        splashPanel.add(image);
        splashPanel.setPreferredSize(new Dimension(400, 250));
        splashPanel.setBackground(new Color(17, 94, 122));

        //Add panel to frame and display
        splashFrame.add(splashPanel);
        splashFrame.pack();
        splashFrame.setLocationRelativeTo(null);
        splashFrame.setVisible(true);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void closeSplash() {
        splashFrame.setVisible(false);
    }

    /**
     * Generate the board JFrame
     */
    private void generateGameFrame() {
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
                drawGameFrame();
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
    public void drawGameFrame() {
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
