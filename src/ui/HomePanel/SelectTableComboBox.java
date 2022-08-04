package ui.HomePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectTableComboBox extends JComboBox implements ActionListener {

    JComboBox comboBox;


    public SelectTableComboBox(String[] list) {
        comboBox = new JComboBox(list);
        comboBox.setPreferredSize(new Dimension(100, 50));
        comboBox.addActionListener(this);
        comboBox.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==comboBox) {
            System.out.println(comboBox.getSelectedItem());
        }
    }
}
