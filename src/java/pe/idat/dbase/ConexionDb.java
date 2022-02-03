/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.idat.dbase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class ConexionDb {
    public static Connection MySQL() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String  url="jdbc:mysql://localhost:3306/db_example?serverTimezone=UTC";
        return DriverManager.getConnection(url,"root","15847205");
    
    }


}
