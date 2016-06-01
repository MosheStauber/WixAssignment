
import com.mysql.jdbc.Connection;
import database.MySQLAccess;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author moshe
 */
public class TestDB {
    public static void main(String[] args) throws Exception{
        MySQLAccess dao = new MySQLAccess();
        System.out.println(dao.addItem("This is being addeded"));
        dao.deleteItem(3);
        
    }
}
