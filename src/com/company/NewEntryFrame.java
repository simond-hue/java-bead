package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class NewEntryFrame extends JFrame {
    private JTextField field;
    public static final int sizeW = 200;
    public static final int sizeH = 70;
    public NewEntryFrame(){
        super();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        JPanel jp = new JPanel();
        jp.setPreferredSize(new Dimension(sizeW, sizeH));
        JLabel label = new JLabel("Név:");
        label.setPreferredSize(new Dimension(30,30));
        jp.add(label);
        this.field = new JTextField();
        field.setPreferredSize(new Dimension(100,30));
        jp.add(field);
        JButton addbutton = new JButton("Hozzáadás");
        addbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Game.database.addEntry(field.getText(), Game.layer);
                }
                catch (SQLException err){
                    showMessage("Hiba történt a beszúrás során!");
                    System.out.println(err.getMessage());
                }
                NewEntryFrame.super.dispose();
            }
        });
        jp.add(addbutton);
        this.add(jp);
        this.pack();
    }

    /**
     * Shows the desired string in a message box
     * @param string
     */
    public void showMessage(String string){ JOptionPane.showMessageDialog(this, string); }
}
