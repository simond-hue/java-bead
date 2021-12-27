package com.company;

import javax.swing.*;

public class GUI {
    private JFrame mainWindow;
    private TopMenuBar topMenuBar;
    public GUI(){
        this.mainWindow = new JFrame("Labyrinth");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setResizable(false);
        this.generateMenu();
        mainWindow.setVisible(true);
    }

    public void addComponent(JComponent comp){
        this.mainWindow.add(comp);
        this.mainWindow.pack();
        this.mainWindow.revalidate();
    }

    private void generateMenu(){
        this.topMenuBar = new TopMenuBar();
        this.mainWindow.setJMenuBar(this.topMenuBar);
    }

    public void empty(){
        this.mainWindow.getContentPane().removeAll();
    }

    public void setMovementListener(MovementActionListener x){
        this.mainWindow.addKeyListener(x);
    }

    public void showMessage(String string){
        JOptionPane.showMessageDialog(this.mainWindow, string);
    }
}
