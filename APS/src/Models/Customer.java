package Models;

import core.Database;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Customer {
    
    public ResultSet Customers(){
        String query = "select * from customers";
        return Database.getRS(query);
    }
    
    public void addCustomer(Object[] data){
        String table = "customers";
        String[] col = {"nama", "noTelp", "pwd", "username"};
        Database.addRecord(table, data, col);
    }
    
    public ResultSet leastOrder(int idCustomer){
        String query = "select * from \n" +
                       "(select * from ordersrev where idCustomer = "+idCustomer+") as z\n" +
                       "ORDER BY z.id DESC LIMIT 1;";
        return Database.getRS(query);
    }
    
    public ResultSet CustomerAuth(String[] data){
        return Database.loginAuth("customers", data);
    }
    
    public void topUp(int id, double nominal){
        Database.closeConn();
        Database.startConn();
        String query = "update customers set saldo = saldo+ ? where id=?";
        try {
            PreparedStatement p = Database.conn.prepareStatement(query);
            p.setObject(1, nominal);
            p.setObject(2, id);
            p.execute();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
    
    public int getSaldo(int id) throws SQLException{
        Database.closeConn();
        Database.startConn();
        int res = -1;
        try {
            String query = "select saldo from customers" + " where id=?";
            PreparedStatement ps = Database.conn.prepareStatement(query);
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                res = (int)rs.getObject(1);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return res;
    }
    
    public void UpdateSaldo(int id, int totalHarga){
        String query = "update customers set saldo = saldo - "+totalHarga+"\n" +
                       "where id= " + id;
        try {
            Database.updateRecord(query);
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
