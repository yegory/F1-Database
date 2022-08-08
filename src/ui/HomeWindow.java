package ui;

import controller.F1_Manager;
import ui.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeWindow implements ActionListener {

    private JFrame HomeFrame;
    private JFrame DirectorInsertion;
    private JPanel buttonPanel;     // Panel 1
    private JPanel checkboxPanel;   // Panel 2
    private JPanel criteriaPanel;   // Panel 3
    private JPanel tablePanel;      // Panel 4

    private Table table;
    private TableComboBox tableComboBox;
    private JButton insertButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton applyButton;
    private JButton otherFunctionsButton;
    private JTextField criteriaField;

    F1_Manager f1_manager = null;

    public HomeWindow (F1_Manager f1_manager) {
        this.f1_manager = f1_manager;

        HomeFrame = new JFrame("Main Frame");
        HomeFrame.setSize(1000,750);
        HomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HomeFrame.getContentPane().setBackground(new Color(175, 175, 175));
        HomeFrame.setLocationRelativeTo(null);
        HomeFrame.setLayout(null);
        HomeFrame.setResizable(false);

        setupButtonPanel();     // top
        setupCheckboxPanel();   // middle-top
        setupCriteriaPanel();   // middle-bottom
        setUpTablePanel();      // bottom
        setUpTable();


        HomeFrame.setVisible(true);
    }



    // Panel 1 that contains the Combo Box + Insert + Delete Buttons
    private void setupButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setBounds(0,0, HomeFrame.getWidth(),50);
        buttonPanel.setBackground(Color.gray);
        buttonPanel.setLayout(new FlowLayout());
        HomeFrame.add(buttonPanel);
    }

    // Panel 2 that contains the Checkboxes to allow selection on columns
    private void setupCheckboxPanel() {
        checkboxPanel = new JPanel();
        checkboxPanel.setBounds(0,50, HomeFrame.getWidth(),40);
        checkboxPanel.setBackground(Color.lightGray);
        checkboxPanel.setLayout(new FlowLayout());
        HomeFrame.add(checkboxPanel);
    }

    // Panel 3 to contain the table
    private void setupCriteriaPanel() {
        criteriaPanel = new JPanel();
        criteriaPanel.setBounds(0,90, HomeFrame.getWidth(),40);
        criteriaPanel.setBackground(new Color(134, 173, 163, 255));
        criteriaPanel.setLayout(new FlowLayout());
        HomeFrame.add(criteriaPanel);
    }


    // Panel 4 to contain the table
    private void setUpTablePanel() {
        tablePanel = new JPanel();
        tablePanel.setBackground(new Color(204, 218, 255));
        tablePanel.setBounds(0,130, HomeFrame.getWidth(),HomeFrame.getHeight() - 130);
        HomeFrame.add(tablePanel);
    }

    // Create empty table and place inside Panel 3
    private void setUpTable() {
        String[] columnNames = {}; Object[][] data = {};
        table = new Table(tablePanel, columnNames, data, HomeFrame.getWidth()-10, tablePanel.getHeight() - 40);

        // Add Combo Buttons and buttons to Panel 1
        JLabel label = new JLabel("Selected table:");
        buttonPanel.add(label);
        tableComboBox = new TableComboBox(table, buttonPanel, checkboxPanel);
        insertButton = new JButton("Insert operation");
        insertButton.addActionListener(this);

        criteriaField = new JTextField("");
        criteriaField.setPreferredSize(new Dimension(250, 30));

        deleteButton = new JButton("Remove operation");
        deleteButton.addActionListener(this);

        otherFunctionsButton = new JButton(("Other functions"));
        otherFunctionsButton.addActionListener(this);

        applyButton = new JButton("Apply selection criteria");
        applyButton.addActionListener(this);

        updateButton = new JButton("Update operation");
        updateButton.addActionListener(this);

        buttonPanel.add(insertButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(otherFunctionsButton);
        criteriaPanel.add(criteriaField);
        criteriaPanel.add(applyButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ((e.getActionCommand() =="Insert operation") &&
                (tableComboBox.comboBox.getSelectedItem().toString() == "Directors")) {
            new InsertFrame();
        } else if ((e.getActionCommand() == "Remove operation") &&
                (tableComboBox.comboBox.getSelectedItem().toString() == "Results")){
            new DeleteFrame();
        } else if ((e.getActionCommand() == "Update operation") &&
                (tableComboBox.comboBox.getSelectedItem().toString() == "Directors")){
            new UpdateFrame();
        } else if (e.getActionCommand() == "Other functions") {
            new FunctionsFrame();
        }
        else if (e.getActionCommand() == "Apply selection criteria") {
            tableComboBox.handleTable(
                    tableComboBox.DetermineTable(tableComboBox.comboBox.getSelectedItem().toString()),
                    tableComboBox.selectedColumns,
                    criteriaField.getText());
        }
    }
}
