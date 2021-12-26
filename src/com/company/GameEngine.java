package com.company;

public class GameEngine {
    private GUI gui;
    private Game game;

    public GameEngine(){
        this.gui = new GUI();
        this.game = new Game(this.gui);
    }

    public void startNewGame(){
        this.gui.empty();
        this.game = new Game(this.gui);
    }
}
