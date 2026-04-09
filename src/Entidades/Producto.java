/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author ekaro
 */
//Esta clase es el objeto de datos puro del sistema. No tiene lógica de negocio, no accede a archivos, 
//no imprime nada. Su único rol es transportar datos entre las capas.
public class Producto {
    // Atributos

    private int id;
    private String nombre;
    private int cantidad;
    private double precio;

    // Constructor completo
    public Producto(int id, String nombre, int cantidad, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    // Constructor sin ID (para registrar un producto nuevo)
    public Producto(String nombre, int cantidad, double precio) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // toString para mostrar en consola
    @Override
    public String toString() {
        return "ID: " + id
                + " | Nombre: " + nombre
                + " | Cantidad: " + cantidad
                + " | Precio: $" + precio;
    }
}
