# AddCars

Programa de escritorio en Java para arrendar vehículos. Permite ver los autos disponibles, filtrarlos, revisar el detalle de cada uno, modificarlos y llevar los contratos.

Lo hicimos con Java Swing y MySQL, y el código sigue el patrón MVC (Modelo, Vista, Controlador).

## Requisitos

- JDK 24 o superior
- NetBeans
- XAMPP (trae MySQL y phpMyAdmin)

## Pasos para ejecutarlo

1. Abrir XAMPP y encender Apache y MySQL.
2. Entrar a `http://localhost/phpmyadmin` y crear una base de datos vacía llamada `addcars`.
3. Dentro de `addcars`, ir a Importar y cargar el archivo `addcars.sql` del repositorio.
4. En NetBeans, abrir el proyecto desde File > Open Project.
5. Ejecutarlo con Run (F6).

El programa se conecta con el usuario `root` sin contraseña. Si tu MySQL usa otros datos, hay que cambiarlos en `src/Conexion/ConexionBD.java`.

## Usuarios de prueba

| Usuario | Contraseña | Rol |
|---|---|---|
| ana.lopez | adminpass123 | Administrador |
| carlos.vera | empleadopass456 | Empleado |

Los clientes no pueden entrar al programa.

## Roles

El administrador puede ver y modificar vehículos y gestionar los contratos. El empleado puede ver los vehículos y registrar arriendos y pagos.

## Estructura del proyecto

```
src/
  Conexion/     conexión a la base de datos
  Modelo/       clases y acceso a datos (DAO)
  Vista/        ventanas de la aplicación
  Controlador/  lógica que une la vista con el modelo
  Imagenes/     fotos de los vehículos
  Main/         clase principal (MainApp)
```
