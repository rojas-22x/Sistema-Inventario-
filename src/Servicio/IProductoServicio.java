/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Servicio;

import Entidades.Producto;
import java.util.List;

/**
 *
 * @author ekaro
 */

public interface IProductoServicio {
    void registrarProducto(Producto producto);
    
    List<Producto> listarProductos();

    Producto buscarProductoPorId(int id);

    boolean actualizarProducto(Producto producto);

    boolean eliminarProducto(int id);
}
