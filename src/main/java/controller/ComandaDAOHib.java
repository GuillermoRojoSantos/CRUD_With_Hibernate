/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Comanda;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * @author AlejandroMarínBermúd
 * @author GuillermoRojoSantos
 */
public class ComandaDAOHib implements ComandaDAO {


    @Override
    public void newPedido(String currentDate) {
        var sc = new Scanner(System.in);
        var c = new Comanda();
        ProductoDAOHib prod = new ProductoDAOHib();
        prod.Menu();
        System.out.println();
        System.out.println("Introduce el nombre del alumno");
        c.setAlumno(sc.nextLine());
        System.out.println("Usar fecha actual?(y/n)");
        var answer = sc.next().toLowerCase();
        if (answer.equals("y")) {
            c.setFecha(currentDate);
        } else {
            System.out.println("Introduce la fecha");
            c.setFecha(sc.nextLine());
        }
        System.out.println("Introduzca el id del producto");
        int id = sc.nextInt();//this next is so the nextInt gets ''correctly closed''
        c.setProducto(prod.getNombreProducto(id));
        c.setPrecio(prod.getPrecio(id));
        c.setEstado("PENDIENTE");
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();
            s.save(c);
            t.commit();
        }
    }

    @Override
    public void delPedido() {
        getAllPedidos();
        var sci = new Scanner(System.in);
        System.out.println("Introduzca la id del pedido que desea borrar");
        int idDel = sci.nextInt();
        System.out.println("Borrando Pedido...");
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();
            Comanda paraborrar = s.get(Comanda.class, idDel);
            System.out.println(paraborrar);
            s.delete(paraborrar);
            t.commit();
        }
    }

    @Override
    public void markPedido() {
        var sci = new Scanner(System.in);
        pedidoMarcadoList();
        System.out.println();
        System.out.println("Introduzca la id de la comanda a marcar como entregada:");
        int id = sci.nextInt();
        System.out.println("Marcando pedido...");
        try (var s = HibernateUtil.getSessionFactory().openSession()) {
            Query query = s.createQuery("from Comanda where id = :ID");
            query.setParameter("ID", id);
            var comanda = (Comanda) query.getSingleResult();
            Transaction t = s.beginTransaction();
            comanda.setEstado("ENTREGADO");
            s.update(comanda);
            t.commit();
        }
    }

    @Override
    public void pedidoList(String currentDate) {
        var sci = new Scanner(System.in);
        System.out.println("Usar fecha actual? (y/n)");
        //here user can either use system's current date or a custom date
        var answer = sci.next().toLowerCase();
        if (answer.equals("n")) {
            System.out.println("Introduceel dia de hoy");
            String day = sci.next();
            System.out.println("Introduce el mes");
            String month = sci.next();
            System.out.println("Introduce el año");
            String year = sci.next();
            try (Session s = HibernateUtil.getSessionFactory().openSession()) {
                Query consulta = s.createQuery("from Comanda where Estado = :estado and Fecha =:fecha");
                consulta.setParameter("estado", "Pendiente");
                consulta.setParameter("fecha", year + "-" + month + "-" + day);
                var listado = (ArrayList<Comanda>) consulta.list();
                var df = new DecimalFormat("#.##");
                listado.forEach((co) -> {
                    System.out.printf("Id: %d || Alumno: %s || Producto: %s || Fecha: %s || Precio: %s || Estado: %s%n",
                            co.getId(), co.getAlumno(), co.getProducto(), co.getFecha(), df.format(co.getPrecio()),
                            co.getEstado());
                    ;
                });
            }
        } else {
            try (Session s = HibernateUtil.getSessionFactory().openSession()) {
                Query consulta = s.createQuery("from Comanda where Estado = :estado and Fecha =:fecha");
                consulta.setParameter("estado", "Pendiente");
                consulta.setParameter("fecha", currentDate);
                var listado = (ArrayList<Comanda>) consulta.list();
                var df = new DecimalFormat("#.##");
                listado.forEach((co) -> {
                    System.out.printf("Id: %d || Alumno: %s || Producto: %s || Fecha: %s || Precio: %s || Estado: %s%n",
                            co.getId(), co.getAlumno(), co.getProducto(), co.getFecha(), df.format(co.getPrecio()),
                            co.getEstado());
                    ;
                });
            }
        }
    }

    @Override
    public void pedidoMarcadoList() {
        try (var s = HibernateUtil.getSessionFactory().openSession()) {
            Query query = s.createQuery("from Comanda where Estado= :state");
            query.setParameter("state", "PENDIENTE");
            var listado = (ArrayList<Comanda>) query.list();
            var df = new DecimalFormat("#.##");
            listado.forEach((co) -> {
                System.out.printf("Id: %d || Alumno: %s || Producto: %s || Fecha: %s || Precio: %s || Estado: %s%n",
                        co.getId(), co.getAlumno(), co.getProducto(), co.getFecha(), df.format(co.getPrecio()),
                        co.getEstado());
                ;
            });
        }
    }

    @Override
    public void getAllPedidos() {
        try (var sf = HibernateUtil.getSessionFactory().openSession()) {
            Query query = sf.createQuery("from Comanda");
            var listado = (ArrayList<Comanda>) query.list();
            var df = new DecimalFormat("#.##");
            listado.forEach((co) -> {
                System.out.printf("Id: %d || Alumno: %s || Producto: %s || Fecha: %s || Precio: %s || Estado: %s%n",
                        co.getId(), co.getAlumno(), co.getProducto(), co.getFecha(), df.format(co.getPrecio()),
                        co.getEstado());
                ;
            });
        }
    }


}
