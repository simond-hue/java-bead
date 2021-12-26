package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGameMenu extends JMenuItem {
    public NewGameMenu(){
        super();
        this.setText("New Game");
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.engine.startNewGame();
            }
        });
    }
}
