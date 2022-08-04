package ui.HomePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeWindow implements ActionListener {
    private static HomeWindow homeWindow;
    private Frame frame;
    private JComboBox comboBox;

    String[] modelClasses = {"Select table", "Sponsor", "Athlete", "Car", "Event"};
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

        comboBox = new JComboBox(modelClasses);
        comboBox.addActionListener(this);
        JButton view = new JButton("view");
        JButton add = new JButton("add");
        JButton remove = new JButton("remove");

        buttonPanel.add(comboBox);
        buttonPanel.add(view);
        buttonPanel.add(add);
        buttonPanel.add(remove);
        frame.setVisible(true);
    }

    public static HomeWindow getInstance() {
        if (homeWindow == null) {
            homeWindow = new HomeWindow();
        }
        return homeWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==comboBox) {
            if(comboBox.getSelectedItem() != "Select table") {
                comboBox.removeItem("Select table");
            }
        }
    }
}
