package ui.util;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Table extends JTable {

    public Table(JPanel parent, String[] columnNames, Object[][] data, int width, int height) {
        JTable table = new JTable(data, columnNames);
        JScrollPane tableSP = new JScrollPane(table);
        tableSP.setPreferredSize(new Dimension(width, height));

        parent.add(tableSP);
    }
}

