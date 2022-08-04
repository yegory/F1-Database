package ui.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button extends JButton implements ActionListener {

    public Button(JPanel parent, String text) {
        JButton button = new JButton(text);
        parent.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
