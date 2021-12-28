package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FieldPanel extends JPanel {
    public static int squareW = 25;
    public static int squareH = 25;
    private BufferedImage img;

    private String assetPath;
    public FieldPanel(FieldType type){
        super();
        this.assetPath = type.path;
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(FieldPanel.squareW, FieldPanel.squareH));
        this.setBackground(new Color(0,0,0,255));
        try{
            this.img = ImageIO.read(new File(this.assetPath));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Resets the opacity of the field to the default value
     */
    public void reset(){
        this.setBackground(new Color(0,0,0,Game.fieldOpacity));
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(this.img, 0, 0, this);
        super.paintComponent(g);
    }
}
