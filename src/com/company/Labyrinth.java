package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Labyrinth {
    private Field[][] fields;
    private LabyrinthPanel ui;
    private int[][] map;

    public Labyrinth(int layer){
        int amount = Game.fieldAmount;
        this.fields = new Field[amount][amount];
        this.ui = new LabyrinthPanel();
        this.generateFields(String.format("./maps/map" + layer + ".txt"));
    }

    /**
     * Generates a new Labyrinth based on the layer
     * @param filename the map file name
     */
    public void generateFields(String filename){
        readFromFile(filename);
        int amount = Game.fieldAmount;
        for(int i = 0; i < amount; i++){
            for(int j = 0; j < amount; j++){
                this.fields[i][j] = new Field(map[i][j],i,j);
                this.ui.add(this.fields[i][j].getUi());
            }
        }
    }

    /**
     * Reads the data from the map file
     * @param filename the map file name
     */
    private void readFromFile(String filename){
        this.map = new int[Game.fieldAmount][Game.fieldAmount];
        try (BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line = br.readLine();
            int lineIndex = 0;
            while (line != null) {
                for(int i = 0; i < Game.fieldAmount; i++){
                    map[lineIndex][i] = Integer.parseInt(line.charAt(i) + "");
                }
                line = br.readLine();
                lineIndex++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints the actor on the desired field
     * @param p the Actor to be printed
     */
    public void printActor(Actor p){
        fields[p.getX()][p.getY()].addComponent(p.getUi());
    }

    public LabyrinthPanel getUi() {
        return ui;
    }

    /**
     * Gets a random spawn location for the dragon to be spawned at (spawn is restricted to only paths and minimum distance to the player)
     * @return the X and Y to spawn the dragon
     */
    public int[] getRandomSpawnLocation(){
        Random r = new Random();
        ArrayList<Field> paths = this.filterPaths();
        int randomIndex = r.nextInt(paths.size());
        return paths.get(randomIndex).getLocation();
    }

    /**
     * Filters all the paths from the Labyrinth
     * @return List of paths
     */
    public ArrayList<Field> filterPaths(){
        ArrayList<Field> paths = new ArrayList<Field>();
        for(int i = 0; i < Game.fieldAmount; i++){
            for(int j = 0; j < Game.fieldAmount; j++){
                if(this.fields[i][j].getType() == FieldType.Path && Labyrinth.Distance(Game.fieldAmount-1, 0, i, j) > 14){
                    paths.add(this.fields[i][j]);
                }
            }
        }
        return paths;
    }

    /**
     * Gets the distance between two object in the Labyrinth
     * @param fromX X position of the first Field
     * @param fromY Y position of the first Field
     * @param toX X position of the second Field
     * @param toY Y position of the second Field
     * @return the distance between two object
     */
    public static double Distance(int fromX, int fromY, int toX, int toY){
        int xDistance = Math.abs(fromX-toX);
        int yDistance = Math.abs(fromY-toY);
        double distance = Math.sqrt(Math.pow(xDistance,2)+Math.pow(yDistance,2));
        return distance;
    }

    /**
     * Returns the field at a specific location
     * @param x X position of the Field
     * @param y Y position of the Field
     * @return the desired Field
     * @throws IndexOutOfBoundsException
     */
    public Field getFieldAt(int x, int y) throws IndexOutOfBoundsException{
        return this.fields[x][y];
    }

    /**
     * Gets the possible directions to be moved to
     * @param x X position of the current location
     * @param y Y position of the current location
     * @return list of possible Directions
     */
    public ArrayList<Directions> getPossibleDirections(int x, int y) {
        ArrayList<Directions> directions = new ArrayList<Directions>();
        try {
            if (this.fields[x + 1][y].getType() == FieldType.Path) {
                directions.add(Directions.DOWN);
            }
        } catch (IndexOutOfBoundsException e) {
        }
        try{
            if (this.fields[x - 1][y].getType() == FieldType.Path) {
                directions.add(Directions.UP);
            }
        }catch (IndexOutOfBoundsException e){}
        try{
            if(this.fields[x][y-1].getType() == FieldType.Path){
                directions.add(Directions.LEFT);
            }
        }catch (IndexOutOfBoundsException e){}
        try{
            if(this.fields[x][y+1].getType() == FieldType.Path){
                directions.add(Directions.RIGHT);
            }
        } catch (IndexOutOfBoundsException e){}
        return directions;
    }

    /**
     * Sets the visibility of the player
     * @param p player
     */
    public void setVisibility(Player p){
        int x = p.getX();
        int y = p.getY();
        for(int i = x-4; i < x+5; i++){
            for(int j = y-4; j < y+5; j++){
                Field current = null;
                try{
                    current = fields[i][j];
                }
                catch (IndexOutOfBoundsException e){}
                if(current != null){
                    if(i==x-4 || j==y-4 || j == y+4 || i == x+4){
                        current.getUi().reset();
                        current.getUi().repaint();
                    }
                    else{
                        int distance = (int)Math.floor(Distance(x,y,current.getX(),current.getY()));
                        int opacity = Game.fieldOpacity*distance/4;
                        current.setOpacity(opacity);
                    }
                }
            }
        }
    }
}
