package ui.util;

import controller.F1_Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button extends JButton implements ActionListener {

    private JButton button;
    public Button(JPanel parent, String text) {
        button = new JButton(text);
        parent.add(button);
        button.addActionListener(this);
        setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if(e.getSource()==button) {
//            F1_Manager.show
//        }
    }
}
