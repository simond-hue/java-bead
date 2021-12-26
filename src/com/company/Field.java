package com.company;

import javax.swing.*;
import java.awt.*;

public class Field {
    private FieldPanel ui;
    private FieldType type;
    public Field(int type){
        this.type = FieldType.values()[type];
        this.ui = new FieldPanel(this.type);
    }
    public void addComponent(JComponent comp){
        this.ui.add(comp);
        this.ui.validate();
        this.ui.repaint();
    }
    public FieldPanel getUi(){
        return this.ui;
    }
}
