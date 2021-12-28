package com.company;

import javax.swing.Timer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    public static final int playerActionPerSec = 8;
    public static final int dragonMovePerSec = 1;
    public static final int fieldAmount = 32;
    public static final int maxLayer = 9;

    public static int dragonOpacity;
    public static final int fieldOpacity = 255;

    public static DBHelper database = new DBHelper();

    private Labyrinth labyrinth;
    public static int layer;

    private MovementActionListener playerAction;
    private DragonMovementListener dragonMovementListener;
    private Timer newFrameTimer;
    private Timer dragonMovementTimer;

    private Player player;
    private Dragon dragon;

    private GUI gui;

    private boolean gameOver;

    public Game(GUI gui){
        dragonOpacity = 0;
        layer = 0;
        this.gui = gui;
        this.labyrinth = new Labyrinth(layer);
        this.player = new Player();
        labyrinth.printActor(player);
        gui.addComponent(labyrinth.getUi());
        dragon = new Dragon();
        this.initDragon();
        labyrinth.printActor(dragon);
        this.playerAction = new MovementActionListener(this);
        gui.setMovementListener(playerAction);
        newFrameTimer = new Timer(1000 / this.playerActionPerSec, playerAction);
        labyrinth.setVisibility(this.player);
        dragonMovementListener = new DragonMovementListener(this);
        dragonMovementTimer = new Timer(1000 / Game.dragonMovePerSec, dragonMovementListener);
        newFrameTimer.start();
        dragonMovementTimer.start();
    }

    /**
     * Initializes the Dragon Actor
     */
    public void initDragon(){
        int[] spawnLocation = labyrinth.getRandomSpawnLocation();
        int spawnX = spawnLocation[0];
        int spawnY = spawnLocation[1];
        this.dragon.setLocation(spawnX,spawnY);
        Random r = new Random();
        this.dragon.setDirection(null);
        ArrayList<Directions> directions = labyrinth.getPossibleDirections(spawnX,spawnY);
        dragon.setDirection(directions.get(r.nextInt(directions.size())));
    }

    /**
     * Creates a new Labyrinth based on the current layer
     */
    public void createNewFloor() {
        this.playerAction.setPaused(true);
        this.dragonMovementListener.setPaused(true);
        this.layer++;
        if(this.layer == this.maxLayer){
            this.gui.openInsertWindow("Gratulálok! Végigvitted a játékot!");
            return;
        }
        this.player.reset();
        this.labyrinth = new Labyrinth(this.layer);
        this.initDragon();
        this.dragonMovementListener.setLabyrinth(this.labyrinth);
        labyrinth.printActor(dragon);
        labyrinth.printActor(player);
        gui.addComponent(labyrinth.getUi());
        labyrinth.setVisibility(this.player);
        this.playerAction.setLabyrinth(this.labyrinth);
        this.dragonMovementListener.setPaused(false);
        this.playerAction.setPaused(false);
    }

    /**
     * Checks if the player is dead
     */
    public void checkForDeath(){
        double distance = Labyrinth.Distance(dragon.getX(),dragon.getY(),player.getX(),player.getY());
        System.out.println(distance);
        if(distance == 1){
            this.gameOver = true;
            this.playerAction.setPaused(true);
            this.dragonMovementListener.setPaused(true);
            this.gui.openInsertWindow("Meghaltál!");
        }
    }

    public Player getPlayer() {
        return player;
    }

    public Dragon getDragon() {
        return dragon;
    }

    public Labyrinth getLabyrinth() {
        return labyrinth;
    }

    public boolean isGameOver(){
        return this.gameOver;
    }
}
