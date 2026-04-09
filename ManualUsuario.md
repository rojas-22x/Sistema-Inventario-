# Manual de Usuario - Sistema de Inventario

## 1. Descripcion del sistema

El Sistema de Inventario es una aplicacion de consola desarrollada en Java para administrar productos. Permite registrar, listar, buscar, actualizar y eliminar productos desde un menu interactivo.

Cada producto contiene la siguiente informacion:

- ID
- Nombre
- Cantidad
- Precio

La informacion se almacena en archivos de texto para mantener los datos entre ejecuciones.

## 2. Como ejecutar

### Opcion 1: Desde NetBeans

1. Abrir el proyecto `Sistema Inventario` en NetBeans.
2. Ejecutar la clase principal [Menu.java](C:\Users\ekaro\OneDrive\Documentos\NetBeansProjects\Progra II\Sistema Inventario\src\Presentacion\Menu.java).
3. Si NetBeans solicita una clase principal, seleccionar `Presentacion.Menu`.

### Opcion 2: Desde consola con Java

Requisitos:

- Tener instalado JDK 21 o compatible.
- Ejecutar los comandos desde la carpeta raiz del proyecto.

Compilar:

```powershell
javac -d out src\Entidades\Producto.java src\AccesoDatos\IProductoDAO.java src\AccesoDatos\ProductoDAO.java src\Servicio\IProductoServicio.java src\Servicio\ProductoServicio.java src\Presentacion\Menu.java
```

Ejecutar:

```powershell
java -cp out Presentacion.Menu
```

### Archivos de datos usados por el sistema

El sistema trabaja con estos archivos:

- `data/productos.txt`
- `data/contador.txt`

En el codigo fuente existen copias en `src/data`, pero la clase DAO usa la ruta relativa `data/...`. Por eso, al ejecutar el sistema debe existir la carpeta `data` en el directorio de trabajo desde donde se lanza la aplicacion.

## 3. Explicacion del menu

Al iniciar, el sistema muestra este menu principal:

```text
----- SISTEMA DE INVENTARIO -----
1. Registrar producto
2. Listar productos
3. Buscar producto por ID
4. Actualizar producto
5. Eliminar producto
0. Salir
```

### Opcion 1. Registrar producto

Solicita:

- Nombre
- Cantidad
- Precio

Luego registra el producto y le asigna un ID automatico.

### Opcion 2. Listar productos

Muestra todos los productos almacenados. Si no hay registros, informa que no existen productos.

### Opcion 3. Buscar producto por ID

Solicita un ID y muestra el producto encontrado. Si no existe, se indica al usuario.

### Opcion 4. Actualizar producto

Solicita el ID del producto, busca el registro y permite ingresar:

- Nuevo nombre
- Nueva cantidad
- Nuevo precio

Si el ID no existe, el sistema no realiza cambios.

### Opcion 5. Eliminar producto

Solicita el ID del producto y elimina el registro si existe.

### Opcion 0. Salir

Finaliza la ejecucion del programa.

## 4. Ejemplos de uso

### Ejemplo 1: Registrar un producto

```text
Seleccione una opcion: 1
Nombre: Teclado
Cantidad: 5
Precio: 12500
Producto registrado correctamente.
```

### Ejemplo 2: Listar productos

```text
Seleccione una opcion: 2
--- Lista de Productos ---
ID: 1 | Nombre: Teclado | Cantidad: 5 | Precio: $12500.0
```

### Ejemplo 3: Buscar un producto por ID

```text
Seleccione una opcion: 3
Ingrese el ID: 1
Producto encontrado: ID: 1 | Nombre: Teclado | Cantidad: 5 | Precio: $12500.0
```

### Ejemplo 4: Actualizar un producto

```text
Seleccione una opcion: 4
Ingrese el ID del producto a actualizar: 1
Nuevo nombre (Teclado): Teclado mecanico
Nueva cantidad (5): 8
Nuevo precio (12500.0): 18000
Producto actualizado correctamente.
```

### Ejemplo 5: Eliminar un producto

```text
Seleccione una opcion: 5
Ingrese el ID del producto a eliminar: 1
Producto eliminado correctamente.
```

## Validaciones visibles para el usuario

La capa de servicio valida:

- Que el nombre no este vacio.
- Que la cantidad no sea negativa.
- Que el precio sea mayor que cero.
- Que el ID sea mayor que cero.

Si alguna validacion falla, el sistema muestra un mensaje de error y no ejecuta la operacion.
