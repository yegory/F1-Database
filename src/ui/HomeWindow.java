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
    private JPanel buttonPanel;     // Panel 1
    private JPanel checkboxPanel;   // Panel 2
    private JPanel tablePanel;      // Panel 3

    private Table table;
    private TableComboBox combobox;
    private Button insertButton;
    private Button deleteButton;
    F1_Manager f1_manager = null;

    public HomeWindow (F1_Manager f1_manager) {
        this.f1_manager = f1_manager;

        frame = new Frame("Main Frame");
        frame.getContentPane().setBackground(new Color(175, 175, 175));
        frame.centreFrame();
        frame.setLayout(null);
        frame.setResizable(false);

        setupButtonPanel();     // top
        setupCheckboxPanel();   // middle
        setUpTablePanel();      // bottom
        setUpTable();


        frame.setVisible(true);
    }



    // Panel 1 that contains the Combo Box + Insert + Delete Buttons
    private void setupButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setBounds(0,0, frame.getWidth(),50);
        buttonPanel.setBackground(Color.gray);
        buttonPanel.setLayout(new FlowLayout());
        frame.add(buttonPanel);
    }

    // Panel 2 that contains the Checkboxes to allow selection on columns
    private void setupCheckboxPanel() {
        checkboxPanel = new JPanel();
        checkboxPanel.setBounds(0,50, frame.getWidth(),40);
        checkboxPanel.setBackground(Color.lightGray);
        checkboxPanel.setLayout(new FlowLayout());
        frame.add(checkboxPanel);
    }

    // Panel 3 to contain the table
    private void setUpTablePanel() {
        tablePanel = new JPanel();
        tablePanel.setBackground(new Color(204, 218, 255));
        tablePanel.setBounds(0,90, frame.getWidth(),frame.getHeight() - 90);
        frame.add(tablePanel);
    }

    // Create empty table and place inside Panel 3
    private void setUpTable() {
        String[] columnNames = {}; Object[][] data = {};
        table = new Table(tablePanel, columnNames, data, frame.getWidth()-10, tablePanel.getHeight() - 40);

        // Add Combo Buttons and buttons to Panel 1
        combobox = new TableComboBox(table, buttonPanel, checkboxPanel);
        insertButton = new Button(buttonPanel, "Insert operation");
        deleteButton = new Button(buttonPanel, "Remove operation");
    }
}
