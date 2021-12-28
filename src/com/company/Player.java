package com.company;

public class Player extends Actor{
    public Player(){
        super(Game.fieldAmount-1, 0);
        this.ui = new PlayerPanel();
    }

    /**
     * Resets the player to the starting point
     */
    public void reset(){
        this.x = Game.fieldAmount-1;
        this.y = 0;
        this.direction = null;
    }
}
