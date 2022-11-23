/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author AlejandroMarínBermúd
 */

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table (name="comanda")
public class Comanda {
    
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column (name="id")
    private int id;
    
    private String Alumno;
    private String Producto;
    private String Fecha;
    private Float Precio;
    private String Estado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlumno() {
        return Alumno;
    }

    public void setAlumno(String Alumno) {
        this.Alumno = Alumno;
    }

    public String getProducto() {
        return Producto;
    }

    public void setProducto(String Producto) {
        this.Producto = Producto;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public Float getPrecio() {
        return Precio;
    }

    public void setPrecio(Float Precio) {
        this.Precio = Precio;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    @Override
    public String toString() {
        return "comanda{" + "id=" + id + ", Alumno=" + Alumno + ", Producto=" + Producto + ", Fecha=" + Fecha + ", Precio=" + Precio + ", Estado=" + Estado + '}';
    }
    
}
