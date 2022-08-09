package ui;

import database.DatabaseConnectionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinFrame extends JFrame implements ActionListener {
    private JButton aggWithGroupByButton;
    private JButton aggWithHavingButton;
    private JButton nestedAggButton;
    private JButton divisionButton;
    private JButton joinButton;
    private JComboBox<String> tableAComboBox;
    private JComboBox<String> tableBComboBox;
    private JTextField criteriaField;
    private JTable table;

    private String[] tables = {"Select table", "Director", "Athlete", "Team",  "Car", "CarModel", "Event", "PitCrew",
            "Results", "LapTime", "Track", "TrackZipCode", "Operate", "ResultsScoring", "Sponsor",
            "SponsorsEvent", "SponsorsTeam", "Practice", "SeasonRace", "Exhibition", "DriveIn"};
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 500;

    public JoinFrame() {
        super("Join Tables");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        initializePanel();
        setVisible(true);
    }

    private void initializePanel() {
        aggWithGroupByButton = new JButton("Group");
        aggWithHavingButton = new JButton("Having");
        nestedAggButton = new JButton("Nested");
        divisionButton = new JButton("Division");
        joinButton = new JButton("Join");

        JPanel fieldPanel = new JPanel();
        JLabel tableALabel = new JLabel("Table A:");
        JLabel tableBLabel = new JLabel("  Table B:");
        JLabel criteriaLabel = new JLabel("  Criteria: ");
        tableAComboBox = new JComboBox<>(tables);
        tableBComboBox = new JComboBox<>(tables);
        criteriaField = new JTextField("",15);

        fieldPanel.add(tableALabel);
        fieldPanel.add(tableAComboBox);
        fieldPanel.add(tableBLabel);
        fieldPanel.add(tableBComboBox);
        fieldPanel.add(criteriaLabel);
        fieldPanel.add(criteriaField);
        fieldPanel.add(joinButton);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        this.add(fieldPanel, BorderLayout.PAGE_START);
        this.add(scrollPane, BorderLayout.CENTER);

        joinButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        joinButtonPress();
    }

    private void joinButtonPress() {
        DatabaseConnectionHandler dbh = DatabaseConnectionHandler.getHandler();
        String tableA = tableAComboBox.getItemAt(tableAComboBox.getSelectedIndex());
        String tableB = tableBComboBox.getItemAt(tableBComboBox.getSelectedIndex());
        String criteria = criteriaField.getText();
        Object[][] data = dbh.join(tableA, tableB, criteria);
        processTable(data);
    }


    // take the queried data and load it into the table
    private void processTable(Object[][] data) {
        Object[][] newData = processArrayForData(data);
        String[] columnNames = processArrayForNames(data);

        DefaultTableModel dtm = new DefaultTableModel(newData, columnNames);
        table.setModel(dtm);
        table.repaint();
    }

    // get first row of array for column names
    private String[] processArrayForNames(Object[][] data) {
        int dataWidth = data[0].length;

        String[] columnNames = new String[dataWidth];

        for (int j = 0; j < dataWidth; j++) {
             columnNames[j] = data[0][j].toString();
        }

        return columnNames;
    }

    // load everything except the first row into an object array
    private Object[][] processArrayForData(Object[][] data) {
        int dataWidth = data[0].length;
        int dataHeight = data.length;

        Object[][] newData = new Object[dataHeight - 1][dataWidth];

        for (int i = 1; i < dataHeight; i++) {
            for (int j = 0; j < dataWidth; j++) {
                newData[i - 1][j] = data[i][j];
            }
        }

        return newData;
    }
}
