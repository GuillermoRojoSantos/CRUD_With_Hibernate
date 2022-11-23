/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crudhibernate;

import controller.ComandaDAOHib;
import controller.ProductoDAOHib;
import java.time.LocalDate;
import java.util.Scanner;
/**
 * @author AlejandroMarínBermúd
 * @author GuillermoRojoSantos
 */
public class Main {

    public static void main(String[] args) {
        int option = 0;
        var sc = new Scanner(System.in);
        ComandaDAOHib cbh = new ComandaDAOHib();
        ProductoDAOHib pdh = new ProductoDAOHib();
        //two uniques instances of ComandaDAOHib and ProductoDAOHib for better performance and cleaner code
        String currentDate=String.valueOf(LocalDate.now());
        //currentDate is taken from system's current date with class LocalDate to later be used in methods needing
        //a date entry
        while (option != 7) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("----------------------------------------------------");
            System.out.println("Bienvenido a la aplicación de pedidos");
            System.out.println("By Alejandro Marín Bermúdez & Guillermo Rojo Santos");
            System.out.println("----------------------------------------------------");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Crear pedido");
            System.out.println("2. Eliminar pedido");
            System.out.println("3. Marcar pedido como entregado");
            System.out.println("4. Mostrar pedidos marcados para hoy");
            System.out.println("5. Mostrar productos");
            System.out.println("6. Mostrar todos los pedidos");
            System.out.println("7. Salir");
            option = sc.nextInt();
            switch (option) {
                case 1 -> {cbh.newPedido(currentDate);sc.nextLine();}
                case 2 -> {cbh.delPedido(); sc.nextLine();}
                case 3 -> {cbh.markPedido(); sc.nextLine();}
                case 4 -> {cbh.pedidoList(currentDate); sc.nextLine();}
                case 5 -> {pdh.Menu(); sc.nextLine();}
                case 6 -> {cbh.getAllPedidos(); sc.nextLine();}
                case 7 -> {
                    System.out.println("Gracias por usar nuestra aplicación");
                    System.out.println("Saliendo...");
                }
                default -> {
                    System.out.printf("Opción %d no válida%n",option);
                    sc.nextLine();
                }
            }
        }
    }

}
