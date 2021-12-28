package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class LeaderboardMenu extends JMenuItem {
    private JFrame main;
    public LeaderboardMenu(){
        super();
        this.main = (JFrame) this.getTopLevelAncestor();
        this.setText("Top 10");
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printPlayers();
            }
        });
    }

    /**
     * Prints the top ten players to a message box
     */
    private void printPlayers(){
        ArrayList<DBSchema> result = null;
        try{
            result = Game.database.listTop();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(this.main, "Hiba történt a lekérdezés során!");
        }
        if(result != null){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("A top 10 játékos:\n");
            for (DBSchema current: result) {
                stringBuilder.append(current.toString() + "\n");
            }
            JOptionPane.showMessageDialog(this.main, stringBuilder.toString());
        }

    }
}
