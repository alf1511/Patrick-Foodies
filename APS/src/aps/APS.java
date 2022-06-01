package aps;

import Controllers.CustomerController;
import Controllers.OrderController;
import Controllers.TenantController;
import Models.Customer;
import Models.Order;
import Models.Tenant;
import core.Database;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class APS {


    public static void main(String[] args) throws SQLException{
        Database.closeConn();
        Database.startConn(); 
        
        OrderController c = new OrderController();
//        c.updateMejaTidakTersedia(5);
        c.updateMejaTersedia(5);
    }
    
}
