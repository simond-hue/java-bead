package com.company;

import java.awt.*;

public class PlayerPanel extends Sprite {
    public PlayerPanel(){
        super();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.CYAN);
        g.fillOval(0,0,25,25);
    }
}
