/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import Entidades.Producto;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ekaro
 */
//Implementación del acceso a datos
/*
Esta es la clase más técnica del proyecto. Es la única en todo el sistema que toca los archivos .txt.
Implementa cada método de IProductoDAO usando BufferedReader y BufferedWriter para leer y escribir.
 */
public class ProductoDAO implements IProductoDAO {

    private static final String ARCHIVO_PRODUCTOS = "data/productos.txt";
    private static final String ARCHIVO_CONTADOR  = "data/contador.txt";
    private static final String SEPARADOR         = "|";

    // ─── Métodos auxiliares privados 

    private List<Producto> leerTodos() {
        List<Producto> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO_PRODUCTOS);
        if (!archivo.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    String[] partes = linea.split("\\" + SEPARADOR);
                    int id         = Integer.parseInt(partes[0]);
                    String nombre  = partes[1];
                    int cantidad   = Integer.parseInt(partes[2]);
                    double precio  = Double.parseDouble(partes[3]);
                    lista.add(new Producto(id, nombre, cantidad, precio));
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo productos: " + e.getMessage());
        }
        return lista;
    }

    private void escribirTodos(List<Producto> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_PRODUCTOS, false))) {
            for (Producto p : lista) {
                bw.write(p.getId() + SEPARADOR +
                         p.getNombre() + SEPARADOR +
                         p.getCantidad() + SEPARADOR +
                         p.getPrecio());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error escribiendo productos: " + e.getMessage());
        }
    }

    private int generarId() {
        int ultimo = 0;
        File archivo = new File(ARCHIVO_CONTADOR);

        if (archivo.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea = br.readLine();
                if (linea != null) ultimo = Integer.parseInt(linea.trim());
            } catch (IOException e) {
                System.out.println("Error leyendo contador: " + e.getMessage());
            }
        }

        int nuevo = ultimo + 1;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, false))) {
            bw.write(String.valueOf(nuevo));
        } catch (IOException e) {
            System.out.println("Error actualizando contador: " + e.getMessage());
        }

        return nuevo;
    }

    // ─── Métodos públicos de la interfaz ───

    @Override
    public void agregar(Producto producto) {
        int nuevoId = generarId();
        producto.setId(nuevoId);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_PRODUCTOS, true))) {
            bw.write(producto.getId() + SEPARADOR +
                     producto.getNombre() + SEPARADOR +
                     producto.getCantidad() + SEPARADOR +
                     producto.getPrecio());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error agregando producto: " + e.getMessage());
        }
    }

    @Override
    public List<Producto> obtenerTodos() {
        return leerTodos();
    }

    @Override
    public Producto obtenerPorId(int id) {
        for (Producto p : leerTodos()) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    @Override
    public boolean actualizar(Producto producto) {
        List<Producto> lista = leerTodos();
        boolean encontrado = false;

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == producto.getId()) {
                lista.set(i, producto);
                encontrado = true;
                break;
            }
        }

        if (encontrado) escribirTodos(lista);
        return encontrado;
    }

    @Override
    public boolean eliminar(int id) {
        List<Producto> lista = leerTodos();
        boolean encontrado = lista.removeIf(p -> p.getId() == id);

        if (encontrado) escribirTodos(lista);
        return encontrado;
    }
}