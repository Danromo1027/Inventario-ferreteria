/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import static trabajofinal.Conexion.getConexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Eder
 */
public class Consultas {
    
    public static Connection con;

    public Consultas() throws ClassNotFoundException, SQLException {
        con = getConexion();
    }
    
    
    public ArrayList<Categorias> getCategorias(){
        Statement st;
        ResultSet rs; //ResultSet es la clase que permite recibir la respuesta de la BD
        ArrayList<Categorias> listaCategorias = new ArrayList<>();
        String sql = "SELECT * FROM categorias";
        try{
            st= con.createStatement();
            rs= st.executeQuery(sql);
            while(rs.next()){
                Categorias cat = new Categorias();
                cat.setCat_id(rs.getInt("cat_id"));
                cat.setCat_nombre(rs.getString("nombre"));
                cat.setFecha_creacion(rs.getString("fecha_creacion"));
                listaCategorias.add(cat);
            }
        }catch(SQLException e ){
            e.printStackTrace();
        }
           
       return listaCategorias;
        
    }
    
    public ArrayList<Proveedor> getProveedores(){
        Statement st;
        ResultSet rs; //ResultSet es la clase que permite recibir la respuesta de la BD
        ArrayList<Proveedor> listaProveedores = new ArrayList<>();
        String sql = "SELECT * FROM proveedor";
        try{
            st= con.createStatement();
            rs= st.executeQuery(sql);
          
            while(rs.next()){
                Proveedor Prov = new Proveedor();
                Prov.setProv_id(rs.getInt("prov_id"));
                Prov.setProv_nombre(rs.getString("prov_nombre"));
                Prov.setProv_telefono(rs.getString("prov_telefono"));
                listaProveedores.add(Prov);
            }
        }catch(SQLException e ){
            e.printStackTrace();
        }
           
       return listaProveedores;
        
    }
    
    public ArrayList<Productos> getProductos(){
        Statement st;
        ResultSet rs; //ResultSet es la clase que permite recibir la respuesta de la BD
        ArrayList<Productos> listaProductos = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try{
            st= con.createStatement();
            rs= st.executeQuery(sql);
            
            while(rs.next()){
                Productos  Pro = new Productos();
                Pro.setPro_id(rs.getInt("pro_id"));
                Pro.setPro_name(rs.getString("pro_name"));
                Pro.setPro_precio(rs.getInt("pro_precio"));
                Pro.setPro_marca(rs.getString("pro_marca"));
                Pro.setPro_cantidad(rs.getInt("pro_cantidad"));
                Pro.setPro_categoria(rs.getInt("pro_categoria"));
                Pro.setPro_proveedor(rs.getInt("pro_proveedor"));
                
                listaProductos.add(Pro);
            }
        }catch(SQLException e ){
            e.printStackTrace();
        }
           
        return listaProductos;
        
    }
    
}
