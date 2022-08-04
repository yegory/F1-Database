package ui;

import ui.util.*;
import ui.util.Button;
import ui.util.Frame;

import javax.swing.*;
import java.awt.*;

public class HomeWindow {
    private static HomeWindow homeWindow;
    private Frame frame;
    private JComboBox comboBox;


    private HomeWindow() {
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

        String[] modelClasses = {"Select table", "Athlete", "Car", "CarModel", "Director", "Sponsor",  "Event"};
        new TableComboBox(buttonPanel, modelClasses);

        new Button(buttonPanel, "view");
        new Button(buttonPanel, "add");
        new Button(buttonPanel, "remove");

        JPanel tablePanel = new JPanel();

        tablePanel.setBackground(new Color(204, 218, 255));
        frame.add(tablePanel);
        tablePanel.setBounds(0,50, frame.getWidth(),frame.getHeight()-50);



        String[] columnNames = {"A", "B", "C"};
        Object[][] data = {
                {"Moni", "adsad", 2},
                {"Jhon", "ewrewr", 4},
                {"Max", "zxczxc", 6},
                {"Moni", "adsad", 2}};

        new Table(tablePanel, columnNames, data, frame.getWidth()-10, frame.getHeight() - 50);

        frame.setVisible(true);
    }

    public static HomeWindow getInstance() {
        if (homeWindow == null) {
            homeWindow = new HomeWindow();
        }
        return homeWindow;
    }
}
