package com.company;

public class Player {
    private int x = 0;
    private int y = Game.fieldAmount-1;
    private PlayerPanel ui;

    public Player(){
        this.ui = new PlayerPanel();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public PlayerPanel getUi(){
        return this.ui;
    }
}
