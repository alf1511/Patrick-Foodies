/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import core.Database;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Order {
    
    public void addOrder(Object[] data){
        String table = "ordersrev";
        String[] col = {"idCustomer", "meja", "totalHarga", "tanggal"};
        Database.addRecord(table, data, col);
    }
    
    public void addTenantOrders(Object[] data){
        String table = "tenantordersrev";
        String[] col = {"idTenant", "idOrder"};
        Database.addRecord(table, data, col);
    }
    
    public ResultSet seatsAvailable(){
        String query = "select idMeja from meja where status = 'tersedia'";
        return Database.getRS(query);
    }
    
    public int getNewestRecord(){
        int res = -1;
        try {
            String query = "select * from ordersrev ORDER BY id DESC LIMIT 1";
            ResultSet a = Database.getRS(query);
            while(a.next()){
                res = a.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public void OrderMenu(Object[] data){
        String table = "carts";
        String[] col = {"idOrder", "idMenu", "jumlah"};
        Database.addRecord(table, data, col);
    }
    
    public ResultSet menuOrderRestaurant(Object noTelp, int idOrder){
        String query = "select t.namaMenu from \n" +
                       "(\n" +
                       "select z.noTelp, x.idOrder, y.namaMenu from carts x\n" +
                       "join menu y on x.idMenu = y.idMenu\n" +
                       "join tenants z on y.idTenant = z.id\n" +
                       ") as t\n" +
                       "where t.noTelp = '"+noTelp+"' and t.idOrder = "+idOrder;
        return Database.getRS(query);
    }
    
    public ResultSet restaurantOrder(int id){
        String query = "select z.namaTenant, z.noTelp from\n" +
                       "(\n" +
                       "select * from tenantordersrev x\n" +
                       "join tenants y on x.idTenant = y.id\n" +
                       ") as z\n" +
                       "where z.idOrder = " + id;
        return Database.getRS(query);
    }
    
    public void StatusRejected(int idOrder){
        String query = "update ordersrev \n" +
                       "set status = 'Exit' where id = "+idOrder;
        try {
            Database.updateRecord(query);
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void StatusAccepted(int idOrder){
        String query = "update ordersrev \n" +
                       "set status = 'Berhasil' where id = "+idOrder;
        try {
            Database.updateRecord(query);
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void StatusDone(int idOrder){
        String query = "update ordersrev \n" +
                       "set status = 'Done' where id = "+idOrder;
        try {
            Database.updateRecord(query);
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int CountTenantInvolved(int idOrder){
        int res = -1;
        String query = "select count(idOrder) as x from tenantordersrev\n" +
                "where idOrder= "+idOrder;
        try {
            ResultSet a = Database.getRS(query);
            while(a.next()){
                res = a.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public int CheckConf(int idOrder){
        int res = -1;
        String query = "select count(idOrder) as x from tenantordersrev\n" +
                       "where idOrder = "+idOrder+" and status = 'Terima'";
        try {
            ResultSet a = Database.getRS(query);
            while(a.next()){
                res = a.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public int GetIdCustomer(int idOrder){
        int res = -1;
        String query = "select idCustomer from ordersrev\n" +
                       "where id = "+idOrder;
        try {
            ResultSet a = Database.getRS(query);
            while(a.next()){
                res = a.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public int GetTotalHarga(int idOrder){
        int res = -1;
        String query = "select totalHarga from ordersrev\n" +
                       "where id = "+idOrder;
        try {
            ResultSet a = Database.getRS(query);
            while(a.next()){
                res = a.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public int GetMejaOrder(int idOrder){
        int res = -1;
        String query = "select meja from ordersrev where id = "+idOrder;
        try {
            ResultSet a = Database.getRS(query);
            while(a.next()){
                res = a.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public void MejaAv(String keterangan, int idMeja){
        String query = "update meja \n" +
                       "set status = '"+keterangan+"' where idMeja = "+idMeja;
        try {
            Database.updateRecord(query);
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}
