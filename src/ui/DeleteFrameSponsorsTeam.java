package ui;

import database.DatabaseConnectionHandler;
import database.DirectorHandler;
import database.ResultsHandler;
import model.Director;
import model.Results;
import model.SponsorsTeam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class DeleteFrameSponsorsTeam extends JFrame implements ActionListener {
    private JButton deleteButton;
    private JTextField sponsorIDField;
    private JTextField teamIDField;
    private static final int DELETE_RESULTS_WIDTH = 300;
    private static final int DELETE_RESULTS_HEIGHT = 90;
    private static final int TEXT_FIELD_WIDTH = 100;

    public DeleteFrameSponsorsTeam() {
        super("Delete sponsors team");
        setSize(DELETE_RESULTS_WIDTH, DELETE_RESULTS_HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        initializePanel();
        setVisible(true);
    }

    private void initializePanel() {
        JLabel sponsorIDLabel = new JLabel("sponsorID:");
        JLabel teamIDLabel = new JLabel("teamID:");
        sponsorIDField = new JTextField(TEXT_FIELD_WIDTH);
        teamIDField = new JTextField(TEXT_FIELD_WIDTH);
        deleteButton = new JButton("Delete");

        JPanel panel = new JPanel();
        GridLayout gl = new GridLayout(0, 2);
        panel.setLayout(gl);
        this.setContentPane(panel);

        panel.add(sponsorIDLabel);
        panel.add(sponsorIDField);
        panel.add(teamIDLabel);
        panel.add(teamIDField);

        panel.add(new JLabel(""));
        panel.add(deleteButton);

        sponsorIDField.requestFocus();
        deleteButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DatabaseConnectionHandler dbh = DatabaseConnectionHandler.getHandler();
        ResultsHandler rh = dbh.getResultsHandler();
        int sponsorID = Integer.parseInt(sponsorIDField.getText());
        int teamID = Integer.parseInt(teamIDField.getText());
        rh.deleteSponsorsTeam(new SponsorsTeam(sponsorID, teamID, 0));
        this.dispose();
    }
}
