package ui;

import controller.F1_Manager;
import database.DatabaseConnectionHandler;
import ui.util.*;
import ui.util.Button;
import ui.util.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

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

//    private Button joinButton;
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
//        joinButton = new Button(buttonPanel, "Join operation");

        JButton button = new JButton("Join operation");
        buttonPanel.add(button);
        button.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                // Create a method named "createFrame()", and set up an new frame there
                // Call createFrame()
                joinFrame();
            }
        });

    }

    public static void joinFrame() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                String[] modelClasses = {"Select table", "Directors", "Athletes", "Teams",  "Cars", "Car Models", "Events", "Pit Crew", "Results",
                        "Lap Times", "Tracks", "Track zip codes", "Drive In", "Results scoring", "Sponsor sponsors Team", "Sponsors",
                        "Sponsor sponsors Event", "Sponsor sponsors Team", "Practices", "Season races", "Exhibitions", "Driver operates"};
                JFrame frame = new JFrame("Join Operation");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                try{
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.setOpaque(true);
                panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                JPanel inputpanel = new JPanel();
                inputpanel.setLayout(new FlowLayout());
                JTextField input = new JTextField(20);
                JButton button = new JButton("Enter");
                inputpanel.add(input);
                JComboBox comboBox = new JComboBox(modelClasses);
                JComboBox comboBox2 = new JComboBox(modelClasses);
                panel.add(comboBox);
                panel.add(comboBox2);
                inputpanel.add(button);
                panel.add(inputpanel);
                frame.getContentPane().add(BorderLayout.CENTER, panel);
                frame.pack();
                frame.setBounds(600, 600, 1200, 600);
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
                frame.setResizable(false);

                JPanel tablePanel = new JPanel();
                tablePanel.setBackground(new Color(255, 207, 162));
                tablePanel.setBounds(0,90, frame.getWidth(),frame.getHeight() - 90);
                panel.add(tablePanel);
                String[] columnNames = {}; Object[][] data = {};
                Table table = new Table(tablePanel, columnNames, data, frame.getWidth()-10, tablePanel.getHeight() - 40);
                TableBox tableBox = new TableBox(table);
                tableBox.displayView("Director", "PitCrew");
                panel.add(tableBox);
                input.requestFocus();

            }
        });
    }
}
