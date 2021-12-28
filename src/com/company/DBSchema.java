package com.company;

public class DBSchema {
    private int ID;
    private String NAME;
    private int LEVEL;

    public DBSchema(int ID, String NAME, int LEVEL){
        this.ID = ID;
        this.NAME = NAME;
        this.LEVEL = LEVEL;
    }

    public int getID() {
        return ID;
    }

    public String getNAME() {
        return NAME;
    }

    public int getLEVEL() {
        return LEVEL;
    }

    @Override
    public String toString() {
        return String.format(this.NAME + " " + this.LEVEL + ".-ig jutott");
    }
}
