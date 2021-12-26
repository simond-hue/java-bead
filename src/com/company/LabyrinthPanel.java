package com.company;

import javax.swing.*;
import java.awt.*;

public class LabyrinthPanel extends JPanel {
    public LabyrinthPanel() {
        super();
        this.setLayout(new GridLayout(Game.fieldAmount,Game.fieldAmount));
    }
}
