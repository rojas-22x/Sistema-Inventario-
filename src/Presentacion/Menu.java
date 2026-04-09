package Presentacion;

import AccesoDatos.IProductoDAO;
import AccesoDatos.ProductoDAO;
import Entidades.Producto;
import Servicio.IProductoServicio;
import Servicio.ProductoServicio;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ekaro
 */
public class Menu {

    private IProductoServicio servicio;
    private Scanner scanner;

    // Constructor: aquí se ensamblan todas las capas
    public Menu() {
        IProductoDAO dao = new ProductoDAO();
        this.servicio = new ProductoServicio(dao);
        this.scanner = new Scanner(System.in);
    }

    // ─── Punto de entrada ────
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.mostrarMenu();
    }

    // ─── Método principal ──
    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n----- SISTEMA DE INVENTARIO -----");
            System.out.println("1. Registrar producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Buscar producto por ID");
            System.out.println("4. Actualizar producto");
            System.out.println("5. Eliminar producto");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarProducto();
                    break;
                case 2:
                    listarProductos();
                    break;
                case 3:
                    buscarProducto();
                    break;
                case 4:
                    actualizarProducto();
                    break;
                case 5:
                    eliminarProducto();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opcion no valida.");
                    break;
            }
        } while (opcion != 0);
    }

    // ─── Métodos privados por opción ────
    private void registrarProducto() {
        System.out.println("\n--- Registrar Producto ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Cantidad: ");
        int cantidad = scanner.nextInt();

        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        scanner.nextLine();

        Producto producto = new Producto(nombre, cantidad, precio);
        servicio.registrarProducto(producto);
    }

    private void listarProductos() {
        System.out.println("\n--- Lista de Productos ---");
        List<Producto> lista = servicio.listarProductos();

        if (lista.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            for (Producto p : lista) {
                System.out.println(p);
            }
        }
    }

    private void buscarProducto() {
        System.out.println("\n--- Buscar Producto ---");
        System.out.print("Ingrese el ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Producto producto = servicio.buscarProductoPorId(id);
        if (producto != null) {
            System.out.println("Producto encontrado: " + producto);
        } else {
            System.out.println("No se encontro un producto con ese ID.");
        }
    }

    private void actualizarProducto() {
        System.out.println("\n--- Actualizar Producto ---");
        System.out.print("Ingrese el ID del producto a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Producto existente = servicio.buscarProductoPorId(id);
        if (existente == null) {
            System.out.println("No se encontro un producto con ese ID.");
            return;
        }

        System.out.print("Nuevo nombre (" + existente.getNombre() + "): ");
        String nombre = scanner.nextLine();

        System.out.print("Nueva cantidad (" + existente.getCantidad() + "): ");
        int cantidad = scanner.nextInt();

        System.out.print("Nuevo precio (" + existente.getPrecio() + "): ");
        double precio = scanner.nextDouble();
        scanner.nextLine();

        Producto actualizado = new Producto(id, nombre, cantidad, precio);
        boolean resultado = servicio.actualizarProducto(actualizado);
        if (resultado) {
            System.out.println("Producto actualizado correctamente.");
        }
    }

    private void eliminarProducto() {
        System.out.println("\n--- Eliminar Producto ---");
        System.out.print("Ingrese el ID del producto a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean resultado = servicio.eliminarProducto(id);
        if (resultado) {
            System.out.println("Producto eliminado correctamente.");
        }
    }
}
