/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author AlejandroMarínBermúd
 */
@Entity
@Table (name="bocadillos")
public class Producto {
        
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private int id;
    private String Nombre;
    private Float Precio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public float getPrecio() {
        return Precio;
    }

    public void setPrecio(Float Precio) {
        this.Precio = Precio;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", Nombre=" + Nombre + ", Precio=" + Precio + '}';
    }
    
    
}
