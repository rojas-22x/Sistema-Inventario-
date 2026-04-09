package Servicio;

import AccesoDatos.IProductoDAO;
import Entidades.Producto;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ekaro
 */
/*
Esta clase es el puente entre la presentación y el DAO. Su responsabilidad es 
validar los datos antes de persistirlos y traducir las operaciones del negocio en llamadas al DAO.
*/
    public class ProductoServicio implements IProductoServicio {

    private IProductoDAO productoDAO;

    // Constructor: recibe el DAO por inyección
    public ProductoServicio(IProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    // ─── Métodos públicos de la interfaz ───────────────────────────────────

    @Override
    public void registrarProducto(Producto producto) {
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            System.out.println("Error: el nombre del producto no puede estar vacío.");
            return;
        }
        if (producto.getCantidad() < 0) {
            System.out.println("Error: la cantidad no puede ser negativa.");
            return;
        }
        if (producto.getPrecio() <= 0) {
            System.out.println("Error: el precio debe ser mayor a cero.");
            return;
        }
        productoDAO.agregar(producto);
        System.out.println("Producto registrado correctamente.");
    }

    @Override
    public List<Producto> listarProductos() {
        return productoDAO.obtenerTodos();
    }

    @Override
    public Producto buscarProductoPorId(int id) {
        if (id <= 0) {
            System.out.println("Error: el ID debe ser mayor a cero.");
            return null;
        }
        return productoDAO.obtenerPorId(id);
    }

    @Override
    public boolean actualizarProducto(Producto producto) {
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            System.out.println("Error: el nombre del producto no puede estar vacío.");
            return false;
        }
        if (producto.getCantidad() < 0) {
            System.out.println("Error: la cantidad no puede ser negativa.");
            return false;
        }
        if (producto.getPrecio() <= 0) {
            System.out.println("Error: el precio debe ser mayor a cero.");
            return false;
        }
        boolean resultado = productoDAO.actualizar(producto);
        if (!resultado) {
            System.out.println("Error: no se encontró un producto con ese ID.");
        }
        return resultado;
    }

    @Override
    public boolean eliminarProducto(int id) {
        if (id <= 0) {
            System.out.println("Error: el ID debe ser mayor a cero.");
            return false;
        }
        boolean resultado = productoDAO.eliminar(id);
        if (!resultado) {
            System.out.println("Error: no se encontró un producto con ese ID.");
        }
        return resultado;
    }
}
