# Manual Tecnico - Sistema de Inventario

## 1. Explicacion de la arquitectura por capas

El proyecto sigue una arquitectura por capas simple, separada en paquetes:

- `Presentacion`
- `Servicio`
- `AccesoDatos`
- `Entidades`

### Capa de Presentacion

Responsable de la interaccion con el usuario por consola. Muestra el menu, solicita datos, captura entradas y presenta resultados.

Clase principal:

- [Menu.java](C:\Users\ekaro\OneDrive\Documentos\NetBeansProjects\Progra II\Sistema Inventario\src\Presentacion\Menu.java)

### Capa de Servicio

Contiene la logica de negocio y las validaciones. Actua como intermediaria entre la presentacion y el acceso a datos.

Clases:

- [IProductoServicio.java](C:\Users\ekaro\OneDrive\Documentos\NetBeansProjects\Progra II\Sistema Inventario\src\Servicio\IProductoServicio.java)
- [ProductoServicio.java](C:\Users\ekaro\OneDrive\Documentos\NetBeansProjects\Progra II\Sistema Inventario\src\Servicio\ProductoServicio.java)

### Capa de Acceso a Datos

Se encarga de persistir y recuperar la informacion desde archivos `.txt`. Implementa las operaciones CRUD sobre productos.

Clases:

- [IProductoDAO.java](C:\Users\ekaro\OneDrive\Documentos\NetBeansProjects\Progra II\Sistema Inventario\src\AccesoDatos\IProductoDAO.java)
- [ProductoDAO.java](C:\Users\ekaro\OneDrive\Documentos\NetBeansProjects\Progra II\Sistema Inventario\src\AccesoDatos\ProductoDAO.java)

### Capa de Entidades

Define las estructuras de datos del dominio.

Clase:

- [Producto.java](C:\Users\ekaro\OneDrive\Documentos\NetBeansProjects\Progra II\Sistema Inventario\src\Entidades\Producto.java)

## 2. Responsabilidad de cada clase

### `Entidades.Producto`

Modelo de datos del sistema. Representa un producto con:

- `id`
- `nombre`
- `cantidad`
- `precio`

Tambien contiene:

- Constructores
- Getters y setters
- Metodo `toString()` para mostrar la informacion en consola

### `AccesoDatos.IProductoDAO`

Define el contrato de persistencia de productos. Establece las operaciones:

- `agregar`
- `obtenerTodos`
- `obtenerPorId`
- `actualizar`
- `eliminar`

### `AccesoDatos.ProductoDAO`

Implementa `IProductoDAO`. Es la unica clase que accede directamente al sistema de archivos.

Responsabilidades:

- Leer todos los productos desde `data/productos.txt`
- Escribir toda la lista de productos
- Generar IDs consecutivos usando `data/contador.txt`
- Buscar por ID
- Actualizar registros
- Eliminar registros

Metodos privados importantes:

- `leerTodos()`
- `escribirTodos(List<Producto>)`
- `generarId()`

### `Servicio.IProductoServicio`

Define el contrato de la capa de negocio. Expone las operaciones que la presentacion puede utilizar.

### `Servicio.ProductoServicio`

Implementa `IProductoServicio` y funciona como puente entre `Menu` y `ProductoDAO`.

Responsabilidades:

- Validar nombre, cantidad, precio e ID
- Delegar las operaciones al DAO
- Informar errores funcionales al usuario mediante mensajes en consola

### `Presentacion.Menu`

Es el punto de entrada del sistema y la clase que ensambla las capas.

Responsabilidades:

- Crear la instancia de `ProductoDAO`
- Inyectarla en `ProductoServicio`
- Mostrar el menu principal
- Capturar datos con `Scanner`
- Ejecutar cada opcion del CRUD

## 3. Flujo del sistema

### Flujo general

1. El usuario ejecuta `Presentacion.Menu`.
2. `Menu` crea un `ProductoDAO`.
3. `Menu` crea un `ProductoServicio` usando el DAO.
4. El usuario selecciona una opcion del menu.
5. `Menu` recopila datos y llama a la capa de servicio.
6. `ProductoServicio` valida la informacion.
7. Si los datos son correctos, `ProductoServicio` llama a `ProductoDAO`.
8. `ProductoDAO` lee o escribe en los archivos `.txt`.
9. El resultado vuelve a `ProductoServicio`.
10. `Menu` muestra el resultado al usuario.

### Flujo de registro de producto

1. El usuario selecciona la opcion `1`.
2. `Menu.registrarProducto()` solicita nombre, cantidad y precio.
3. Se crea un objeto `Producto` sin ID.
4. `ProductoServicio.registrarProducto()` valida los datos.
5. `ProductoDAO.agregar()` genera un nuevo ID.
6. `ProductoDAO` guarda el producto en `data/productos.txt`.

### Flujo de actualizacion

1. El usuario ingresa el ID.
2. `Menu` consulta primero si el producto existe.
3. Si existe, solicita nuevos datos.
4. `ProductoServicio.actualizarProducto()` valida.
5. `ProductoDAO.actualizar()` reemplaza el objeto en la lista y reescribe el archivo.

### Flujo de eliminacion

1. El usuario ingresa el ID.
2. `ProductoServicio.eliminarProducto()` valida el ID.
3. `ProductoDAO.eliminar()` quita el producto de la lista.
4. Si encontro el producto, vuelve a escribir el archivo sin ese registro.

## 4. Decisiones tecnicas tomadas

### Uso de arquitectura por capas

Se separo el sistema en presentacion, negocio, acceso a datos y entidades para mantener una estructura clara y facilitar el mantenimiento.

### Uso de interfaces

Se utilizaron interfaces en servicio y acceso a datos:

- `IProductoServicio`
- `IProductoDAO`

Esto reduce el acoplamiento y permite cambiar implementaciones sin afectar toda la aplicacion.

### Persistencia en archivos de texto

Se eligio almacenar la informacion en `.txt` para simplificar el proyecto y evitar dependencias de base de datos. Esta decision es adecuada para fines academicos y sistemas pequenos.

### Generacion manual de IDs

Se utiliza `contador.txt` para llevar el ultimo ID asignado y garantizar consecutividad en nuevos registros.

### Validacion centralizada en la capa de servicio

Las reglas de negocio no se dejaron en la interfaz de usuario. Esto permite reutilizar validaciones aunque en el futuro cambie la forma de entrada de datos.

### Ensamblado manual de dependencias

La clase `Menu` crea directamente `ProductoDAO` y `ProductoServicio`. No se utiliza un framework de inyeccion de dependencias, lo cual mantiene el proyecto sencillo.

### Observaciones tecnicas relevantes

- La ruta usada por `ProductoDAO` es relativa: `data/productos.txt` y `data/contador.txt`.
- En el proyecto los archivos originales estan en `src/data`, por lo que la ejecucion depende del directorio de trabajo.
- `main.class` aparece vacio en `nbproject/project.properties`, asi que en algunos entornos sera necesario seleccionar manualmente `Presentacion.Menu` como clase principal.
- No se encontraron pruebas automaticas en la carpeta `test`.

## Uso de IA en el proyecto

Tras revisar las clases del sistema, no se encontro integracion funcional de inteligencia artificial dentro del codigo fuente. Es decir:

- No hay consumo de APIs de IA.
- No hay modelos predictivos.
- No hay asistentes automaticos dentro del sistema.

Por lo tanto, el uso de IA no forma parte de la arquitectura en tiempo de ejecucion del proyecto actual.

Si se desea documentar el uso de IA en el contexto del desarrollo, puede indicarse que herramientas de IA pueden haberse usado como apoyo para:

- Redaccion de documentacion
- Revision de estructura del codigo
- Apoyo conceptual para el UML

Sin embargo, eso no cambia la arquitectura real del software entregado.
