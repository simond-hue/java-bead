package com.company;

import javax.swing.*;
import java.awt.*;

public abstract class Sprite extends JPanel {
    protected String assetPath;
    public Sprite(String assetPath){
        this.assetPath = assetPath;
    }
    public abstract void draw(Graphics g);
}
