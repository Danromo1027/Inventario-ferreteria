/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Eder
 */
public class Productos {
    int pro_id;
    String pro_name;
    int pro_precio;
    String pro_marca;
    int pro_cantidad;
    int pro_categoria;
    int pro_proveedor;

    public int getPro_id() {
        return pro_id;
    }

    public int getPro_cantidad() {
        return pro_cantidad;
    }

    public void setPro_cantidad(int pro_cantidad) {
        this.pro_cantidad = pro_cantidad;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public int getPro_precio() {
        return pro_precio;
    }

    public void setPro_precio(int pro_precio) {
        this.pro_precio = pro_precio;
    }

    public String getPro_marca() {
        return pro_marca;
    }

    public void setPro_marca(String pro_marca) {
        this.pro_marca = pro_marca;
    }

    public int getPro_categoria() {
        return pro_categoria;
    }

    public void setPro_categoria(int pro_categoria) {
        this.pro_categoria = pro_categoria;
    }

    public int getPro_proveedor() {
        return pro_proveedor;
    }

    public void setPro_proveedor(int pro_proveedor) {
        this.pro_proveedor = pro_proveedor;
    }
}
