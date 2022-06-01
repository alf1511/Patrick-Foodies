/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Order;
import java.sql.*;

public class OrderController {
    
    Order o = new Order();
    
    public void makeOrder(Object[] data){
        this.o.addOrder(data);
    }
    
    public void makeTenantOrders(Object[] data){
        this.o.addTenantOrders(data);
    }
    
    public ResultSet seats(){
        return this.o.seatsAvailable();
    }
    
    public int newestOrder(){
        return this.o.getNewestRecord();
    }
    
    public void addOrderMenu(Object[] data){
        this.o.OrderMenu(data);
    }
    
    public ResultSet orderRestaurant(int id){
        return o.restaurantOrder(id);
    }
    
    public ResultSet RestaurantOrderMenu(Object noTlp, int idOrder){
        return o.menuOrderRestaurant(noTlp, idOrder);
    }
    
    public void statusRejected(int idOrder){
        this.o.StatusRejected(idOrder);
    }
    
    public void statusAccepted(int idOrder){
        this.o.StatusAccepted(idOrder);
    }
    
    public int countTenantInvolved(int idOrder){
        return this.o.CountTenantInvolved(idOrder);
    }
    
    public int checkConf(int idOrder){
        return this.o.CheckConf(idOrder);
    }
    
    public int getIdCustomer(int idOrder){
        return this.o.GetIdCustomer(idOrder);
    }
    
    public int getTotalHarga(int idOrder){
        return this.o.GetTotalHarga(idOrder);
    }
    
    public int getMejaOrder(int idOrder){
        return this.o.GetMejaOrder(idOrder);
    }
    
    public void updateMejaTersedia(int idMeja){
        this.o.MejaAv("tersedia", idMeja);
    }
    
    public void updateMejaTidakTersedia(int idMeja){
        this.o.MejaAv("tidak tersedia", idMeja);
    }
    
    public void orderDone(int idOrder){
        this.o.StatusDone(idOrder);
        updateMejaTersedia(getMejaOrder(idOrder));
    }
}
