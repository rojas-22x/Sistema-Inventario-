/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package AccesoDatos;

import Entidades.Producto;
import java.util.List;

/**
 *
 * @author ekaro
 */
//Capa de acceso a Datos
/*
Esta interfaz define el contrato de persistencia: todo lo que cualquier clase de acceso a datos
debe ser capaz de hacer con el archivo. Es el "qué" del DAO, sin importar si 
mañana cambias de .txt a una base de datos real.
 */
public interface IProductoDAO  {
    void agregar(Producto producto);

    List<Producto> obtenerTodos();
    //obtenerTodos() lee línea por línea el archivo productos.txt,
    //convierte cada línea en un objeto Producto usando el constructor completo y los acumula en una List.

    Producto obtenerPorId(int id);

    boolean actualizar(Producto producto);

    boolean eliminar(int id);
}
