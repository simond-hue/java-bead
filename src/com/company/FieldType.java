package com.company;

public enum FieldType {
    Wall(0, "./assets/wall.png"),
    Path(1, "./assets/path.png");

    public final int value;
    public final String path;
    private FieldType(int value, String path){
        this.value = value;
        this.path = path;
    }
}
