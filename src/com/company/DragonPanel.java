package com.company;

import java.awt.*;

public class DragonPanel extends Sprite{
    public DragonPanel(){
        super();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(255,0,0,Game.dragonOpacity));
        g.fillOval(0,0,25,25);
    }
}
