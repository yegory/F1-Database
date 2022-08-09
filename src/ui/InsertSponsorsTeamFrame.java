package ui;

import database.DatabaseConnectionHandler;
import database.DirectorHandler;
import model.SponsorsTeam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertSponsorsTeamFrame extends JFrame implements ActionListener {
    private JButton insertButton;

    private JTextField sponsorIDField;
    private JTextField teamIDField;
    private JTextField dealValueField;
    private static final int INSERT_WIDTH = 300;
    private static final int INSERT_HEIGHT = 120;
    private static final int TEXT_FIELD_WIDTH = 100;

    public InsertSponsorsTeamFrame() {
        super("Insert - Sponsor sponsors Team");
        setSize(INSERT_WIDTH, INSERT_HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        initializePanel();
        setVisible(true);
    }

    private void initializePanel() {
        JLabel sponsorIDLabel = new JLabel("Sponsor ID:");
        JLabel teamIDLabel = new JLabel("Team ID:");
        JLabel dealLabel = new JLabel("Deal value:");
        sponsorIDField = new JTextField(TEXT_FIELD_WIDTH);
        teamIDField = new JTextField(TEXT_FIELD_WIDTH);
        dealValueField = new JTextField(TEXT_FIELD_WIDTH);
        insertButton = new JButton("Insert");

        JPanel panel = new JPanel();
        GridLayout gl = new GridLayout(0, 2);
        panel.setLayout(gl);
        this.setContentPane(panel);

        panel.add(sponsorIDLabel);
        panel.add(sponsorIDField);
        panel.add(teamIDLabel);
        panel.add(teamIDField);
        panel.add(dealLabel);
        panel.add(dealValueField);
        panel.add(new JLabel(""));
        panel.add(insertButton);

        sponsorIDLabel.requestFocus();
        insertButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DatabaseConnectionHandler dbh = DatabaseConnectionHandler.getHandler();
        DirectorHandler dh = dbh.getDirectorHandler();
        int sponsorID = Integer.parseInt(sponsorIDField.getText());
        int teamID = Integer.parseInt(teamIDField.getText());
        int dealValue = Integer.parseInt(dealValueField.getText());
        dh.insertSponsorsTeam(new SponsorsTeam(sponsorID, teamID, dealValue));
        this.dispose();
    }
}
