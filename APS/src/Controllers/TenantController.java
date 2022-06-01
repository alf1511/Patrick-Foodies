/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Tenant;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TenantController {
    
    Tenant t = new Tenant();
    
    public ResultSet getTenants(){
        return this.t.Tenants();
    }
    
    public ResultSet getTenantMenu(String no_telp){
        return t.tenantsMenu(no_telp);
    }
    
    public int getTenantId(String noTelp){
        return this.t.getId(noTelp);
    }
    
    public int getIdMenu(String noTelp, String namaMenu){
        return this.t.getMenuId(noTelp, namaMenu);
    }
    
    public ResultSet TenantAuth(String[] data){
        return t.TenantAuth(data);
    }
    
    public void menuAdd(Object[] data){
        this.t.addMenu(data);
    }
    
    public ResultSet incomingOrders(int idTenant){
        return this.t.IncomingOrder(idTenant);
    }
    
    public ResultSet menuOfAnOrder(int idTenant, int idOrder){
        return this.t.MenuOfAnOrder(idTenant, idOrder);
    }
    
    public void rejectOrder(int idOrder) throws SQLException{
        this.t.RejectOrder(idOrder);
    }
    
    public void acceptOrder(int idOrder, int idTenant) throws SQLException{
        this.t.AcceptOrder(idOrder, idTenant);
    }
}
