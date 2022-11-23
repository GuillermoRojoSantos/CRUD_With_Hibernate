/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.text.DecimalFormat;
import java.util.ArrayList;

import model.Producto;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 * @author AlejandroMarínBermúd
 */
public class ProductoDAOHib implements ProductoDAO {

    @Override
    public void Menu() {
        ArrayList<Producto> listado;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query consulta = s.createQuery("from Producto");
            listado = (ArrayList<Producto>) consulta.list();
            var df = new DecimalFormat("#.##");
            listado.forEach((co) -> {
                System.out.printf("Id: %d || Nombre: %s || Precio: %s€%n", co.getId(), co.getNombre(),
                        df.format(co.getPrecio()));
            });
        }
    }

    @Override
    public float getPrecio(int id) {
        try (var s = HibernateUtil.getSessionFactory().openSession()) {
            Query query = s.createQuery("from Producto where id = :ID");
            query.setParameter("ID", id);
            Producto prod = (Producto) query.getSingleResult();
            return prod.getPrecio();
        }

    }

    @Override
    public String getNombreProducto(int id) {
        try (var s = HibernateUtil.getSessionFactory().openSession()) {
            Query query = s.createQuery("from Producto where id = :ID");
            query.setParameter("ID", id);
            Producto prod = (Producto) query.getSingleResult();
            return prod.getNombre();
        }
    }
}
