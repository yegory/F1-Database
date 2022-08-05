package ui;

import controller.F1_Manager;
import ui.util.*;
import ui.util.Button;
import ui.util.Frame;

import javax.swing.*;
import java.awt.*;

public class HomeWindow {

    private Frame frame;
    private JComboBox comboBox;

    F1_Manager f1_manager = null;

    public HomeWindow (F1_Manager f1_manager) {
        this.f1_manager = f1_manager;

        frame = new Frame("Main Frame");
        frame.getContentPane().setBackground(new Color(175, 175, 175));
        frame.centreFrame();
        frame.setLayout(null);
        frame.setResizable(false);


        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(0,0, frame.getWidth(),50);
        buttonPanel.setBackground(Color.gray);
        buttonPanel.setLayout(new FlowLayout());
        frame.add(buttonPanel);


        JPanel tablePanel = new JPanel();

        tablePanel.setBackground(new Color(204, 218, 255));
        frame.add(tablePanel);
        tablePanel.setBounds(0,50, frame.getWidth(),frame.getHeight() - 50);


        String[] columnNames = {"A", "B", "C", "D"};
        Object[][] data = {
                {"Moni", "adsad", 2},
                {"Jhon", "ewrewr", 4},
                {"Max", "zxczxc", 6},
                {"Moni", "adsad", 2}};

        Table table = new Table(tablePanel, columnNames, data, frame.getWidth()-10, frame.getHeight() - 50);

        TableComboBox combo = new TableComboBox(table, buttonPanel);
        new Button(buttonPanel, "add");
        new Button(buttonPanel, "remove");
        frame.setVisible(true);
    }
}
