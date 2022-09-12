/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Eder
 */
public class Categorias {
    
    int cat_id;
    String Cat_nombre;
    String fecha_creacion;
    
    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }
    
    public String getCat_nombre() {
        return Cat_nombre;
    }

    public void setCat_nombre(String Cat_nombre) {
        this.Cat_nombre = Cat_nombre;
    }
    
    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
    
}
