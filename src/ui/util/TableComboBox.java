package ui.util;

import database.DatabaseConnectionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class TableComboBox extends JComboBox implements ActionListener {

    String[] modelClasses = {"Select table", "Athlete", "Car", "CarModel", "Director", "Sponsor",  "Event"};
    JComboBox comboBox;
    Table table;
    public TableComboBox(Table table, JPanel home) {
        this.table = table;
        comboBox = new JComboBox(modelClasses);
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

            if(comboBox.getSelectedItem() == "Director") {
                ArrayList<String> s = new ArrayList<>() {{
                    add("directorID");
                    add("firstName");
                    add("lastName");
                }};
                Object[][] result = DatabaseConnectionHandler.getHandler().project("Director", s);

                int numberOfElementsInArray = result[0].length; // number of rows in result

                // A is column names
                Object[][] A = Arrays.copyOfRange(result, 0, 1);

                // B is row data
                Object [][] B = Arrays.copyOfRange(result, 1, numberOfElementsInArray - 1);
                print2DArray(B);


                table.deleteTable();
                table.addRows(B);
                table.getTable().getColumnModel().getColumn(0).setHeaderValue(A[0][0].toString());
                table.fireTableCellUpdated(0,0);
                table.getTable().repaint();
                table.getTable().requestFocus();
            }
        }
    }
    private void print2DArray(Object[][] outputData) {
        for (int i = 0; i < outputData.length; i++) {
            for (int j = 0; j < outputData[0].length; j++) {
                if (outputData[i][j] != null) {
                    System.out.print(outputData[i][j].toString() + " ");
                } else {
                    System.out.println("null");
                }
            }
            System.out.println("");
        }
    }
}
