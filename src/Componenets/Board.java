package Componenets;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This file was created by Rhys Williams,
 * www.rhyswilliams.co.za
 * me@rhyswilliams.co.za
 */
public class Board {
    private String name;
    private int width, height;
    private String[][] layout;

    public Board(String boardPath) {
        try {
            Scanner boardScan = new Scanner(new File(boardPath));
            name = boardScan.nextLine();
            Scanner sizeScan = new Scanner(boardScan.nextLine()).useDelimiter(" ");
            width = sizeScan.nextInt();
            height = sizeScan.nextInt();
            sizeScan.close();
            layout = new String[width][height];

            for (int heightI = 0; heightI < height; heightI++) {
                String temp = boardScan.nextLine();
                for (int widthI = 0; widthI < width; widthI++) {
                    layout[heightI][widthI] = "" + temp.charAt(widthI);
                }
            }

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; )
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String[][] getLayout() {
        return layout;
    }

    public void setLayout(String[][] layout) {
        this.layout = layout;
    }
}
