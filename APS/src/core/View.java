/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import Views.CustomerView;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author ASUS
 */
public class View extends javax.swing.JFrame {
    
    public static void showData(ResultSet data, javax.swing.JTable t){
        try {
            ResultSetMetaData mData = data.getMetaData();
            DefaultTableModel table = (DefaultTableModel)t.getModel();
            for(int i=0; i<mData.getColumnCount();i++){
                table.addColumn(mData.getColumnName(i+1));
            }
            while(data.next()){
                ArrayList<Object> arr = new ArrayList<>();
                for(int i=0; i<mData.getColumnCount();i++){
                    arr.add(data.getObject(i+1));
                }
                Object[] row = arr.toArray();
                table.addRow(row);    
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void refreshTable(javax.swing.JTable t){
        DefaultTableModel dm = (DefaultTableModel)t.getModel();
        int rowCount = dm.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            dm.removeRow(i);
        }
        dm.setColumnCount(0);
    }
}
