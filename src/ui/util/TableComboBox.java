package ui.util;

import controller.F1_Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableComboBox extends JComboBox implements ActionListener {

    JComboBox comboBox;
    F1_Manager f1_manager;
    public TableComboBox(F1_Manager f1_manager, JPanel home, String[] list) {
        this.f1_manager = f1_manager;
        comboBox = new JComboBox(list);
        comboBox.setPreferredSize(new Dimension(150, 50));
        comboBox.addActionListener(this);
        home.add(comboBox);
    }

    public Object getComboSelection() {
        return comboBox.getSelectedItem();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==comboBox) {
            if(comboBox.getSelectedItem() != "Select table") {
                comboBox.removeItem("Select table");
            }

            if(comboBox.getSelectedItem() == "Athlete") {

            }
        }
    }
}
