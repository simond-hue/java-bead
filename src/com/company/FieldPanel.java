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

    private String assetPath;
    public FieldPanel(FieldType type){
        super();
        this.assetPath = type.path;
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(FieldPanel.squareW, FieldPanel.squareH));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage img = null;
        try{
            img = ImageIO.read(new File(this.assetPath));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        g.drawImage(img, 0, 0, null);
        System.out.print(getComponents().length);
        for(Component sprite : this.getComponents()){
            ((Sprite)sprite).draw(g);
        }
    }
}
