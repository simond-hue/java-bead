package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PlayerPanel extends Sprite {
    public PlayerPanel(){
        super("./assets/player.png");
        this.setLayout(new FlowLayout());
        this.setSize(new Dimension(FieldPanel.squareW, FieldPanel.squareH));
    }

    @Override
    public void draw(Graphics g) {
        super.paintComponent(g);
        BufferedImage img = null;
        try{
            img = ImageIO.read(new File(this.assetPath));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        g.drawImage(img,0,0,null);
    }
}
