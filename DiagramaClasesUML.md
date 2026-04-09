# Diagrama de Clases UML - Sistema de Inventario

## Vista general

El siguiente diagrama representa las capas reales del proyecto, sus relaciones y las interfaces implementadas.

```mermaid
classDiagram
    namespace Presentacion {
        class Menu {
            -IProductoServicio servicio
            -Scanner scanner
            +Menu()
            +main(String[] args)
            +mostrarMenu()
            -registrarProducto()
            -listarProductos()
            -buscarProducto()
            -actualizarProducto()
            -eliminarProducto()
        }
    }

    namespace Servicio {
        class IProductoServicio {
            <<interface>>
            +registrarProducto(Producto producto)
            +listarProductos() List~Producto~
            +buscarProductoPorId(int id) Producto
            +actualizarProducto(Producto producto) boolean
            +eliminarProducto(int id) boolean
        }

        class ProductoServicio {
            -IProductoDAO productoDAO
            +ProductoServicio(IProductoDAO productoDAO)
            +registrarProducto(Producto producto)
            +listarProductos() List~Producto~
            +buscarProductoPorId(int id) Producto
            +actualizarProducto(Producto producto) boolean
            +eliminarProducto(int id) boolean
        }
    }

    namespace AccesoDatos {
        class IProductoDAO {
            <<interface>>
            +agregar(Producto producto)
            +obtenerTodos() List~Producto~
            +obtenerPorId(int id) Producto
            +actualizar(Producto producto) boolean
            +eliminar(int id) boolean
        }

        class ProductoDAO {
            -String ARCHIVO_PRODUCTOS
            -String ARCHIVO_CONTADOR
            -String SEPARADOR
            -leerTodos() List~Producto~
            -escribirTodos(List~Producto~ lista)
            -generarId() int
            +agregar(Producto producto)
            +obtenerTodos() List~Producto~
            +obtenerPorId(int id) Producto
            +actualizar(Producto producto) boolean
            +eliminar(int id) boolean
        }
    }

    namespace Entidades {
        class Producto {
            -int id
            -String nombre
            -int cantidad
            -double precio
            +Producto(int id, String nombre, int cantidad, double precio)
            +Producto(String nombre, int cantidad, double precio)
            +getId() int
            +getNombre() String
            +getCantidad() int
            +getPrecio() double
            +setId(int id)
            +setNombre(String nombre)
            +setCantidad(int cantidad)
            +setPrecio(double precio)
            +toString() String
        }
    }

    Menu --> IProductoServicio : usa
    ProductoServicio ..|> IProductoServicio : implementa
    ProductoServicio --> IProductoDAO : depende de
    ProductoDAO ..|> IProductoDAO : implementa
    ProductoServicio --> Producto : valida y procesa
    ProductoDAO --> Producto : persiste
    Menu --> Producto : crea y muestra
```

## Lectura del diagrama por capas

### Capa de Presentacion

- `Menu` interactua con el usuario.
- Depende de la interfaz `IProductoServicio`, no de una implementacion concreta.

### Capa de Servicio

- `ProductoServicio` implementa la logica de negocio.
- Depende de `IProductoDAO` para acceder a los datos.

### Capa de Acceso a Datos

- `ProductoDAO` implementa el contrato `IProductoDAO`.
- Administra la lectura y escritura en archivos de texto.

### Capa de Entidades

- `Producto` es la entidad compartida entre las demas capas.

## Relaciones correctas identificadas

- `Menu` usa `IProductoServicio`.
- `ProductoServicio` implementa `IProductoServicio`.
- `ProductoServicio` usa `IProductoDAO`.
- `ProductoDAO` implementa `IProductoDAO`.
- `Producto` es usado por presentacion, servicio y acceso a datos como objeto de transferencia.

## Interfaces

En el proyecto si aplican interfaces y son parte importante del diseno:

- `IProductoServicio`
- `IProductoDAO`

Estas interfaces ayudan a desacoplar las capas y hacen que la implementacion concreta pueda cambiar con menor impacto.

## Uso de IA (obligatorio documentar)

### Estado actual en el codigo

Despues de revisar el proyecto, no se identificaron clases, interfaces, servicios externos ni librerias relacionadas con inteligencia artificial dentro de la implementacion actual.

Eso significa que:

- La IA no participa en el registro de productos.
- La IA no participa en busquedas o recomendaciones.
- La IA no interviene en la persistencia de datos.

### Interpretacion arquitectonica

En el UML del sistema no se agrego una clase de IA porque no existe una dependencia real de ese tipo en el codigo fuente. Agregarla al diagrama produciria una representacion incorrecta de la arquitectura actual.

### Forma correcta de documentarlo

La manera correcta de cumplir con este requisito es dejar constancia explicita de que:

- El sistema actualmente no integra modulos de IA.
- El uso de IA, si existio durante el desarrollo, fue externo al sistema y no forma parte de su ejecucion.

## Archivos fuente relacionados

- [Menu.java](C:\Users\ekaro\OneDrive\Documentos\NetBeansProjects\Progra II\Sistema Inventario\src\Presentacion\Menu.java)
- [ProductoServicio.java](C:\Users\ekaro\OneDrive\Documentos\NetBeansProjects\Progra II\Sistema Inventario\src\Servicio\ProductoServicio.java)
- [IProductoServicio.java](C:\Users\ekaro\OneDrive\Documentos\NetBeansProjects\Progra II\Sistema Inventario\src\Servicio\IProductoServicio.java)
- [ProductoDAO.java](C:\Users\ekaro\OneDrive\Documentos\NetBeansProjects\Progra II\Sistema Inventario\src\AccesoDatos\ProductoDAO.java)
- [IProductoDAO.java](C:\Users\ekaro\OneDrive\Documentos\NetBeansProjects\Progra II\Sistema Inventario\src\AccesoDatos\IProductoDAO.java)
- [Producto.java](C:\Users\ekaro\OneDrive\Documentos\NetBeansProjects\Progra II\Sistema Inventario\src\Entidades\Producto.java)
