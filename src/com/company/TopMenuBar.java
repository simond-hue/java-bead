package com.company;

import javax.swing.*;

public class TopMenuBar extends JMenuBar {
    public TopMenuBar(){
        super();
        initializeMenuBar();
    }

    /**
     * Initializes the menu
     */
    private void initializeMenuBar() {
        initializeMenuItems();
    }

    /**
     * Initializes the menu items
     */
    private void initializeMenuItems() {
        JMenu gameMenu = new JMenu("Game");
        this.add(gameMenu);

        JMenu leaderboard = new JMenu("Leaderboards");
        this.add(leaderboard);

        LeaderboardMenu leaderboardItem = new LeaderboardMenu();
        leaderboard.add(leaderboardItem);

        NewGameMenu newGame = new NewGameMenu();
        gameMenu.add(newGame);

        ExitMenu exit = new ExitMenu();
        gameMenu.add(exit);
    }
}
