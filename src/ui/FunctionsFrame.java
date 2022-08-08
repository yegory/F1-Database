package ui;

import database.DatabaseConnectionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FunctionsFrame extends JFrame implements ActionListener {
    private JButton aggWithGroupByButton;
    private JButton aggWithHavingButton;
    private JButton nestedAggButton;
    private JButton divisionButton;
    private JTable table;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;

    public FunctionsFrame() {
        super("Specific Functions");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);
        initializePanel();
        setVisible(true);
    }

    private void initializePanel() {
        aggWithGroupByButton = new JButton("Group");
        aggWithHavingButton = new JButton("Having");
        nestedAggButton = new JButton("Nested");
        divisionButton = new JButton("Division");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(aggWithGroupByButton);
        buttonPanel.add(aggWithHavingButton);
        buttonPanel.add(nestedAggButton);
        buttonPanel.add(divisionButton);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        this.add(buttonPanel, BorderLayout.PAGE_START);
        this.add(scrollPane, BorderLayout.CENTER);

        aggWithGroupByButton.addActionListener(this);
        aggWithHavingButton.addActionListener(this);
        nestedAggButton.addActionListener(this);
        divisionButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Group") {
            groupButtonPress();
        } else if (e.getActionCommand() == "Having") {
            havingButtonPress();
        } else if (e.getActionCommand() == "Nested") {
            nestedButtonPress();
        } else if (e.getActionCommand() == "Division") {
            divisionButtonPress();
        }
    }

    private void divisionButtonPress() {
        DatabaseConnectionHandler dbh = DatabaseConnectionHandler.getHandler();
        Object[][] data = dbh.division();
        processTable(data);
    }

    private void nestedButtonPress() {
        DatabaseConnectionHandler dbh = DatabaseConnectionHandler.getHandler();
        Object[][] data = dbh.nestedAgg();
        processTable(data);
    }

    private void havingButtonPress() {
        DatabaseConnectionHandler dbh = DatabaseConnectionHandler.getHandler();
        Object[][] data = dbh.aggWithHaving();
        processTable(data);
    }

    private void groupButtonPress() {
        DatabaseConnectionHandler dbh = DatabaseConnectionHandler.getHandler();
        Object[][] data = dbh.aggByGroup();
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
