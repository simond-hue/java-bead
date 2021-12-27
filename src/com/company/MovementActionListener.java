package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MovementActionListener implements KeyListener, ActionListener{
    private Player player;
    private Labyrinth labyrinth;
    private Integer pressed;
    private Game game;
    private Field destination = null;
    private boolean paused;
    public MovementActionListener(Game game){
        super();
        this.player = game.getPlayer();
        this.labyrinth = game.getLabyrinth();
        this.game = game;
    }
    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        this.pressed = e.getKeyCode();
        if(this.pressed == KeyEvent.VK_A){
            try{
                this.destination = this.labyrinth.getFieldAt(player.getX(), player.getY()-1);
            }
            catch (IndexOutOfBoundsException err){}
            if(this.destination != null && this.destination.getType() == FieldType.Path){
                this.player.setDirection(Directions.LEFT);
            }
        }
        if(this.pressed == KeyEvent.VK_W){
            try{
                this.destination = this.labyrinth.getFieldAt(player.getX()-1, player.getY());
            }
            catch (IndexOutOfBoundsException err){}
            if(this.destination != null && this.destination.getType() == FieldType.Path){
                this.player.setDirection(Directions.UP);
            }
        }
        if(this.pressed == KeyEvent.VK_S){
            try{
                this.destination = this.labyrinth.getFieldAt(player.getX()+1, player.getY());
            }
            catch (IndexOutOfBoundsException err){}
            if(this.destination != null && this.destination.getType() == FieldType.Path){
                this.player.setDirection(Directions.DOWN);
            }
        }
        if(this.pressed == KeyEvent.VK_D){
            try{
                this.destination = this.labyrinth.getFieldAt(player.getX(), player.getY()+1);
            }
            catch (IndexOutOfBoundsException err){}
            if(this.destination != null && this.destination.getType() == FieldType.Path){
                this.player.setDirection(Directions.RIGHT);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(this.paused && this.game.isGameOver()){ return; }
        this.game.checkForDeath();
        if(this.player.getX() == 0 && this.player.getY() == Game.fieldAmount-1){
            this.game.createNewFloor();
            this.destination = null;
        }
        if(this.pressed == null || this.player.getDirection() == null) { return; }
        if(destination != null && destination.getType() != FieldType.Wall){
            this.player.move(destination);
            double distance = Labyrinth.Distance(this.player.getX(),this.player.getY(),this.game.getDragon().getX(),this.game.getDragon().getY());
            this.labyrinth.setVisibility(this.player);
            if(distance < 4){
                Game.dragonOpacity = 255/(int)Math.floor(distance);
                this.game.getDragon().getUi().repaint();
            }
            else{
                Game.dragonOpacity = 0;
            }
        }
    }

    public void setPaused(boolean value){
        this.paused = value;
    }
    public void setLabyrinth(Labyrinth labyrinth) {
        this.labyrinth = labyrinth;
    }
}
