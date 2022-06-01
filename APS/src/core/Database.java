package core;

import java.sql.*;  
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    public static Connection conn = null;
    
    public static void startConn(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Database.conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/aps", "root", "231118");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ResultSet getRS(String query){
        Database.closeConn();
        Database.startConn();
        ResultSet rs = null;
        try {
            Statement st = Database.conn.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return rs;
    }
    
    public static void addRecord(String table, Object[] data, String[] columns){
        Database.closeConn();
        Database.startConn();
        String col = "(";
        String val = "(";
        for(int i = 0; i < columns.length; i++){
            if(i == columns.length - 1){
                col = col + columns[i] +")";
                continue;
            }
            col = col + columns[i]+",";
        }
        for(int i = 0; i < data.length; i++){
            if(i == data.length - 1){
                val = val + "?)";
                continue;
            }
            val = val + "?,";
        }
        String query = "insert into "+table+col+" values"+val;
        try {
            PreparedStatement p = Database.conn.prepareStatement(query);
            for(int i=1; i<=data.length; i++){
                p.setObject(i, data[i-1]);
            }
            p.execute();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
    
    public static void updateRecord(String query) throws SQLException{
        Database.closeConn();
        Database.startConn();
        PreparedStatement p = Database.conn.prepareStatement(query);
        p.execute();
    }
    
    public static ResultSet loginAuth(String table, String[] data){
        Database.closeConn();
        Database.startConn();
        ResultSet rs = null;
        try {
            String query = "select * from " + table + " where noTelp=?"+ " and pwd=?";
            PreparedStatement ps = Database.conn.prepareStatement(query);
            ps.setObject(1, data[0]);
            ps.setObject(2, data[1]);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return rs;
    }
    
    public static void closeConn(){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.getMessage();
            }
        }
    }
}
