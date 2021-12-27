package com.company;

import javax.swing.*;
import java.awt.*;

public class Field {
    private FieldPanel ui;
    private FieldType type;

    private int x;
    private int y;
    public Field(int type, int x, int y){
        this.type = FieldType.values()[type];
        this.ui = new FieldPanel(this.type);
        this.x = x;
        this.y = y;
    }
    public void addComponent(JComponent comp){
        this.ui.add(comp);
        this.ui.validate();
        this.ui.repaint();
    }

    public int[] getLocation(){
        return new int[]{this.x, this.y};
    }

    public FieldType getType(){
        return this.type;
    }

    public FieldPanel getUi(){
        return this.ui;
    }

    public void setOpacity(int opacity){
        this.ui.setBackground(new Color(0,0,0,opacity));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
