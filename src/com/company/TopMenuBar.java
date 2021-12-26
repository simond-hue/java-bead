package com.company;

import javax.swing.*;

public class TopMenuBar extends JMenuBar {
    public TopMenuBar(){
        super();
        initializeMenuBar();
    }

    private void initializeMenuBar() {
        initializeMenuItems();
    }

    private void initializeMenuItems() {
        JMenu gameMenu = new JMenu("Game");
        this.add(gameMenu);

        NewGameMenu newGame = new NewGameMenu();
        gameMenu.add(newGame);

        ExitMenu exit = new ExitMenu();
        gameMenu.add(exit);
    }
}
