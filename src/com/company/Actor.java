package com.company;

import java.awt.*;

public class Actor {
    protected int x;
    protected int y;
    protected Sprite ui;
    protected Directions direction;

    public Actor(int x, int y){
        this.x = x;
        this.y = y;
    }
    public Actor(){}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Sprite getUi(){
        return this.ui;
    }
    /**
     * @param destination
     * Moves the Actor to the destination Field
     */
    public void move(Field destination) {
        if(!destination.isEmptyField()) return;
        this.x = destination.getX();
        this.y = destination.getY();
        Container parent = this.ui.getParent();
        parent.removeAll();
        parent.revalidate();
        parent.repaint();
        destination.addComponent(this.ui);
    }

    public Directions getDirection(){
        return this.direction;
    }
    public void setDirection(Directions direction){
        this.direction = direction;
    }

    /**
     * @param x The X position of the Actor
     * @param y The Y position of the Actor
     * Sets the Actor's location
     */
    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }
}
