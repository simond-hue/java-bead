package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
                Field f = new Field(map[i][j]);
                this.ui.add(f.getUi());
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
                    this.fields[lineIndex][i] = new Field(map[lineIndex][i]);
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

    public void printPlayer(Player p){
        System.out.println();
        fields[p.getX()][p.getY()].addComponent(p.getUi());
    }

    public LabyrinthPanel getUi() {
        return ui;
    }
}
