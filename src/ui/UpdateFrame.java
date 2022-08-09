package ui;

import database.DatabaseConnectionHandler;
import database.DirectorHandler;
import model.Director;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateFrame extends JFrame implements ActionListener {
    private JButton updateButton;
    private JTextField directorIDField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private static final int INSERT_DIRECTOR_WIDTH = 300;
    private static final int INSERT_DIRECTOR_HEIGHT = 120;
    private static final int TEXT_FIELD_WIDTH = 100;

    public UpdateFrame() {
        super("Update Director");
        setSize(INSERT_DIRECTOR_WIDTH, INSERT_DIRECTOR_HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        initializePanel();
        setVisible(true);
    }

    private void initializePanel() {
        JLabel directorIDLabel = new JLabel("directorID:");
        JLabel firstNameLabel = new JLabel("firstName:");
        JLabel lastNameLabel = new JLabel("lastName:");
        directorIDField = new JTextField(TEXT_FIELD_WIDTH);
        firstNameField = new JTextField(TEXT_FIELD_WIDTH);
        lastNameField = new JTextField(TEXT_FIELD_WIDTH);
        updateButton = new JButton("Update");

        JPanel panel = new JPanel();
        GridLayout gl = new GridLayout(0, 2);
        panel.setLayout(gl);
        this.setContentPane(panel);

        panel.add(directorIDLabel);
        panel.add(directorIDField);
        panel.add(firstNameLabel);
        panel.add(firstNameField);
        panel.add(lastNameLabel);
        panel.add(lastNameField);
        panel.add(new JLabel(""));
        panel.add(updateButton);

        directorIDField.requestFocus();
        updateButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DatabaseConnectionHandler dbh = DatabaseConnectionHandler.getHandler();
        DirectorHandler dh = dbh.getDirectorHandler();
        int directorID = Integer.parseInt(directorIDField.getText());
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        dh.updateDirector(new Director(directorID, firstName, lastName));
        this.dispose();
    }
}
