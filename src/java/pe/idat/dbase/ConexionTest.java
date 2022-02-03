/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.idat.dbase;
import java.sql.SQLException;
import pe.idat.dbase.ConexionDb;
/**
 *
 * @author User
 */
public class ConexionTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ConexionDb.MySQL();
        System.err.print("Conexion ok");
    }
    
}
