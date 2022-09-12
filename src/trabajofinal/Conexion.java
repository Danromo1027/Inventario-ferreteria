/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajofinal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author usuario1
 */
public class Conexion {
    
    public static Connection getConexion() throws ClassNotFoundException, SQLException{
        //Parametros de conexión
        String driver= "com.mysql.cj.jdbc.Driver";
       
        Connection conexion=null;
        
        try{
            Class.forName(driver);
            conexion= DriverManager.getConnection(Propiedades.URL, Propiedades.USERNAME, Propiedades.PASSWORD);
            if(conexion!=null){
                System.out.println("***Conexión Exitosa***");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return conexion;
    
    }
    
    
}
