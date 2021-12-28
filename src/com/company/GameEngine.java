package com.company;

public class GameEngine {
    private GUI gui;
    private Game game;

    public static final int FPS = 60;

    public GameEngine(){
        this.gui = new GUI();
        this.game = new Game(this.gui);
    }

    /**
     * Starts a new Game
     */
    public void startNewGame(){
        this.gui.empty();
        this.game = new Game(this.gui);
    }
}
