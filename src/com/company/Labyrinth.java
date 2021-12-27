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

    public void printActor(Actor p){
        fields[p.getX()][p.getY()].addComponent(p.getUi());
    }

    public LabyrinthPanel getUi() {
        return ui;
    }

    public int[] getRandomSpawnLocation(){
        Random r = new Random();
        ArrayList<Field> paths = this.filterPaths();
        int randomIndex = r.nextInt(paths.size());
        return paths.get(randomIndex).getLocation();
    }

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

    public static double Distance(int fromX, int fromY, int toX, int toY){
        int xDistance = Math.abs(fromX-toX);
        int yDistance = Math.abs(fromY-toY);
        double distance = Math.sqrt(Math.pow(xDistance,2)+Math.pow(yDistance,2));
        return distance;
    }

    public Field getFieldAt(int x, int y) throws IndexOutOfBoundsException{
        return this.fields[x][y];
    }
    public void refresh(){
        this.ui.validate();
        this.ui.repaint();
    }

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
