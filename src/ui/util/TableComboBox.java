package ui.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableComboBox extends JComboBox implements ActionListener {

    JComboBox comboBox;

    public TableComboBox(JPanel home, String[] list) {
        comboBox = new JComboBox(list);
        comboBox.setPreferredSize(new Dimension(150, 50));
        comboBox.addActionListener(this);
        comboBox.addActionListener(this);
        home.add(comboBox);
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
