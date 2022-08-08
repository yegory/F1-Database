package ui;

import database.DatabaseConnectionHandler;
import database.DirectorHandler;
import model.Director;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class DeleteFrame extends JFrame implements ActionListener {
    private JButton deleteButton;
    private JTextField resultsIDField;
    private static final int DELETE_RESULTS_WIDTH = 300;
    private static final int DELETE_RESULTS_HEIGHT = 90;
    private static final int TEXT_FIELD_WIDTH = 100;

    public DeleteFrame() {
        super("Delete Result");
        setSize(DELETE_RESULTS_WIDTH, DELETE_RESULTS_HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        initializePanel();
        setVisible(true);
    }

    private void initializePanel() {
        JLabel resultsIDLabel = new JLabel("resultsID:");
        resultsIDField = new JTextField(TEXT_FIELD_WIDTH);
        deleteButton = new JButton("Delete");

        JPanel panel = new JPanel();
        GridLayout gl = new GridLayout(0, 2);
        panel.setLayout(gl);
        this.setContentPane(panel);

        panel.add(resultsIDLabel);
        panel.add(resultsIDField);

        panel.add(new JLabel(""));
        panel.add(deleteButton);

        resultsIDField.requestFocus();
        deleteButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DatabaseConnectionHandler dbh = DatabaseConnectionHandler.getHandler();
        // ResultsHandler rh = dbh.getResultsHandler();
        int resultsID = Integer.parseInt(resultsIDField.getText());
        // rh.deleteResults(new Result(resultsID, firstName, lastName));
        this.dispose();
    }
}
