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

    /**
     * Adds UI component to the desired field
     * @param comp the component to be added
     */
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

    /**
     * Checks if the field contains any components
     * @return the existance of a component on the field
     */
    public boolean isEmptyField(){
        return this.ui.getComponents().length == 0;
    }

    /**
     * Sets the opacity of the current field (used for the flashlight effect)
     * @param opacity the opacity desired to be set to
     */
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
