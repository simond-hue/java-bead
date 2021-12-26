package com.company;

public class Game {
    public static int fieldAmount = 32;
    private Labyrinth labyrinth;
    private int layer = 0;

    private Player player;

    public Game(GUI gui){
        this.labyrinth = new Labyrinth(layer);
        gui.addComponent(labyrinth.getUi());
        this.player = new Player();
        labyrinth.printPlayer(player);
    }
}
