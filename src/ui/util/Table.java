package ui.util;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Table extends DefaultTableModel {

    private JTable table;
    private DefaultTableModel dtm;

    public Table(JPanel parent, Object[] columnNames, Object[][] data, int width, int height) {
        dtm = new DefaultTableModel(columnNames, 0);
        table = new JTable(dtm);
        addRows(data);
        table.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        table.setGridColor(new Color(224, 224, 224));
        JScrollPane tableSP = new JScrollPane(table);
        tableSP.setPreferredSize(new Dimension(width, height));
        parent.add(tableSP);
    }

    public void deleteTable() {
        dtm.setRowCount(0);
    }

    public void addRows(Object[][] data) {
        for (Object[] o: data) {
            dtm.addRow(o);
        }
    }

    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getDtm() {
        return dtm;
    }
}

