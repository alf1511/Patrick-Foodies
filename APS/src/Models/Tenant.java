/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import core.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class Tenant {
    
    public ResultSet Tenants(){
        String query = "select namaTenant, noTelp from tenants";
        return Database.getRS(query);
    }
    
    public void addMenu(Object[] data){
        String table = "menu";
        String[] col = {"idTenant", "namaMenu", "harga"};
        Database.addRecord(table, data, col);
    }
    
    public ResultSet IncomingOrder(int idTenant){
        String query = "select idOrder, status from tenantordersrev\n" +
                       "where idTenant = " + idTenant + " and status = 'Terima / Tolak'";
        return Database.getRS(query);
    }
    
    public ResultSet MenuOfAnOrder(int idTenant, int idOrder){
        String query = "select z.namaMenu FROM\n" +
                       "(\n" +
                       "select x.*, y.idTenant, y.namaMenu from carts x\n" +
                       "join menu y on y.idMenu=x.idMenu\n" +
                       ")as z \n" +
                       "where z.idTenant = "+idTenant+" and z.idOrder = "+idOrder;
        return Database.getRS(query);
    }
    
    public int getId(String noTelp){
        int res = -1;
        try {
            String query = "select id from tenants where noTelp = " + noTelp;
            ResultSet a = Database.getRS(query);
            while(a.next()){ 
                res = a.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tenant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public ResultSet TenantAuth(String[] data){
        return Database.loginAuth("tenants", data);
    }
    
    public ResultSet tenantsMenu(String no_telp){
        String query = "select z.namaMenu, z.harga from(\n" +
                       "    select y.noTelp, x.namaMenu, x.harga \n" +
                       "    from menu x\n" +
                       "    join tenants y on y.id = x.idTenant\n" +
                       ")as z\n" +
                       "where z.noTelp = " + no_telp;
        return Database.getRS(query);
    }
    
    public int getMenuId(String no_telp, String nama_menu){
        int res = -1;
        try {
            String query = "select z.idMenu from\n" +
                    "(\n" +
                    "    select * from\n" +
                    "    menu x\n" +
                    "    join tenants y on x.idTenant = y.id\n" +
                    ") as z where z.noTelp = " + "'"+ no_telp + "'"+" and z.namaMenu = "+"'"+nama_menu+"'";
            ResultSet a = Database.getRS(query);
            while(a.next()){
                res = a.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tenant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public void RejectOrder(int idOrder) throws SQLException{
        String query = "update tenantordersrev\n" +
                       "set status = 'Tolak' where idOrder = "+idOrder;
        Database.updateRecord(query);
    }
    
    public void AcceptOrder(int idOrder, int idTenant) throws SQLException{
        String query = "update tenantordersrev\n" +
                       "set status = 'Terima' where idOrder = "+idOrder+" and idTenant= "+idTenant;
        Database.updateRecord(query);
    }
    
}
