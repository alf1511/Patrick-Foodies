package Controllers;

import Models.Customer;
import java.sql.*;

public class CustomerController {
    
    Customer c = new Customer();
    
    public ResultSet Customers(){
        return c.Customers();
    }
    
    public void addCustomer(Object[] data){
        this.c.addCustomer(data);
    }
    
    public ResultSet CustomerAuth(String[] data){
        return this.c.CustomerAuth(data);
    }
    
    public void TopUp(int id, double nominal){
        this.c.topUp(id, nominal);
    }
    
    public int getSaldo(int id) throws SQLException{
        return this.c.getSaldo(id);
    }
    
    public Object[] newestOrderCust(int idCustomer) throws SQLException{
        Object[] result = new Object[6];
        ResultSet a = c.leastOrder(idCustomer);
        while(a.next()){
            for(int i = 0; i < 6; i++){
                result[i] = a.getObject(i+1);
            }
        }
        return result;
    }
    
    public void updateSaldo(int id,int totalHarga){
        this.c.UpdateSaldo(id,totalHarga);
    }
}
