# CHANGELOG

## v1.0

- Creacion de la estructura base del proyecto en Java.
- Separacion inicial por paquetes: `Presentacion`, `Servicio`, `AccesoDatos` y `Entidades`.
- Definicion de la entidad `Producto`.
- Configuracion base del proyecto en NetBeans.

## v1.1

- Implementacion del CRUD completo de productos.
- Registro de productos con asignacion automatica de ID.
- Listado de productos almacenados.
- Busqueda de productos por ID.
- Actualizacion de productos existentes.
- Eliminacion de productos por ID.
- Persistencia de informacion en archivos `.txt`.

## v1.2

- Incorporacion de validaciones de negocio en la capa de servicio.
- Mejora en la separacion por capas mediante interfaces `IProductoDAO` e `IProductoServicio`.
- Documentacion del sistema en Markdown:
  - Manual de usuario
  - Manual tecnico
  - Diagrama de clases UML
- Ajuste de la clase principal del proyecto a `Presentacion.Menu`.
- Preparacion de archivos para entrega y publicacion en GitHub.
