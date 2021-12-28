package com.company;

import javax.swing.*;
import java.awt.*;

public class Sprite extends JPanel {
    protected String assetPath;
    public Sprite(String assetPath){
        this.assetPath = assetPath;
        initGraphics();
    }
    public Sprite(){
        initGraphics();
    }

    /**
     * Initializes the basic Sprite qualities
     */
    private void initGraphics(){
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(FieldPanel.squareW, FieldPanel.squareH));
        this.setOpaque(false);
    }
}
