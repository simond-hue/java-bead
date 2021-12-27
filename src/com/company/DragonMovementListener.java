package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class DragonMovementListener implements ActionListener {
    private Dragon dragon;
    private Labyrinth labyrinth;
    private Game game;
    private boolean paused = false;
    public DragonMovementListener(Game game) {
        super();
        this.game = game;
        this.dragon = game.getDragon();
        this.labyrinth = game.getLabyrinth();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(this.paused && this.game.isGameOver()) return;
        Field destination = null;
        if(this.dragon.getDirection() == Directions.LEFT){
            try{
                destination = this.labyrinth.getFieldAt(dragon.getX(), dragon.getY()-1);
            }
            catch (IndexOutOfBoundsException err){}
        }
        if(this.dragon.getDirection() == Directions.UP){
            try{
                destination = this.labyrinth.getFieldAt(dragon.getX()-1, dragon.getY());
            }
            catch (IndexOutOfBoundsException err){}
        }
        if(this.dragon.getDirection() == Directions.DOWN){
            try{
                destination = this.labyrinth.getFieldAt(dragon.getX()+1, dragon.getY());
            }
            catch (IndexOutOfBoundsException err){}
        }
        if(this.dragon.getDirection() == Directions.RIGHT){
            try{
                destination = this.labyrinth.getFieldAt(dragon.getX(), dragon.getY()+1);
            }
            catch (IndexOutOfBoundsException err){}
        }
        if(destination != null && destination.getType() != FieldType.Wall){
            this.dragon.move(destination);
        }
        else{
            ArrayList<Directions> directions = this.labyrinth.getPossibleDirections(this.dragon.getX(),this.dragon.getY());
            Random r = new Random();
            this.dragon.setDirection(directions.get(r.nextInt(directions.size())));
        }
    }
    public void setLabyrinth(Labyrinth labyrinth) {
        this.labyrinth = labyrinth;
    }
    public void setPaused(boolean value){ this.paused = value; }
}
