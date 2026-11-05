package Controlador;

import Modelo.VehiculoDAO;
import Modelo.VehiculoDetalle;
import Vista.ModVehiculoView;
import Vista.MenuView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeSet;

public class ModVehiculoController implements ActionListener {

    private final ModVehiculoView view;
    private final VehiculoDAO model;
    private final String idVehiculoOriginal;
    private final String usuarioRol;

    public ModVehiculoController(ModVehiculoView view, VehiculoDAO model, String idVehiculo, String rol) {
        this.view = view;
        this.model = model;
        this.idVehiculoOriginal = idVehiculo;
        this.usuarioRol = rol;
        this.view.setController(this); //asigna controlador a la vista
        if (cargarDatosIniciales()) { //si se cargan correctamente los datos
             this.view.setVisible(true); //muestra la ventana
        } else {
             System.err.println("No se mostrará ModVehiculoView debido a error en carga inicial."); //error de carga
        }
    }

    private boolean cargarDatosIniciales() {
        try {
            TreeSet<String> sucursales = model.getSucursalesDisponibles(); //obtiene sucursales
            TreeSet<String> categorias = model.getCategoriasDisponibles(); //obtiene categorias
            if (sucursales == null || categorias == null) {
                 view.mostrarError("Error crítico: No se pudieron obtener las listas de sucursales o categorías del DAO.");
            }
             view.poblarComboBoxes(sucursales, categorias); //carga combos en la vista
            VehiculoDetalle vehiculoActual = model.getVehiculoDetalle(idVehiculoOriginal); //obtiene vehiculo por id
            if (vehiculoActual != null) {
                view.setDatosVehiculo(vehiculoActual); //establece datos en los campos
                return true;
            } else {
                view.mostrarError("Error: No se encontró el vehículo con ID: " + idVehiculoOriginal + "\nLa ventana de modificación se cerrará.");
                view.cerrar();
                volverAlMenu(); //regresa al menu
                return false;
            }
        } catch (Exception e) {
            view.mostrarError("Ocurrió un error inesperado al cargar los datos de modificación:\n" + e.getMessage()); //error de carga
            view.cerrar();
            volverAlMenu();
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getBtnGuardar()) {
            guardarCambios(); //accion boton guardar
        } else if (e.getSource() == view.getBtnCancelar()) {
            cancelar(); //accion boton cancelar
        }
    }

    private void guardarCambios() {
        String marcaModelo = view.getMarcaModelo();
        String precioStr = view.getPrecioDiaStr();
        String anioStr = view.getAnioStr();
        String sucursalNombre = view.getSelectedSucursal();
        String categoriaNombre = view.getSelectedCategoria();
        String descripcion = view.getDescripcion();
        String imagenUrl = view.getImagenUrl();

        if (marcaModelo.isEmpty() || precioStr.isEmpty() || anioStr.isEmpty()) {
            view.mostrarError("Los campos 'Marca y Modelo', 'Precio por Día' y 'Año' son obligatorios.");
            return;
        }
        if (sucursalNombre == null || categoriaNombre == null) {
             view.mostrarError("Debe seleccionar una 'Sucursal' y una 'Categoría' válidas.");
             return;
        }

        int precioDia, anio;
        try {
            precioDia = Integer.parseInt(precioStr);
            if (precioDia <= 0) {
                view.mostrarError("El 'Precio por Día' debe ser un número mayor que cero.");
                return;
            }
        } catch (NumberFormatException ex) {
            view.mostrarError("El 'Precio por Día' debe ser un número entero válido.");
            return;
        }
         try {
            anio = Integer.parseInt(anioStr);
             int currentYear = java.time.Year.now().getValue();
             if (anio < 1980 || anio > currentYear + 1) {
                 view.mostrarError("El 'Año' debe ser razonable (ej. entre 1980 y " + (currentYear + 1) + ").");
                 return;
             }
        } catch (NumberFormatException ex) {
            view.mostrarError("El 'Año' debe ser un número entero válido.");
            return;
        }

        String idModelo = model.obtenerIdModeloDesdeNombreCompleto(marcaModelo); //busca id modelo
        String idSucursal = model.obtenerIdSucursalPorNombre(sucursalNombre); //busca id sucursal
        String idCategoria = model.obtenerIdCategoriaPorNombre(categoriaNombre); //busca id categoria

         if (idModelo == null || idSucursal == null || idCategoria == null) {
            view.mostrarError("Error al obtener IDs internos antes de guardar.");
            return;
         }

        VehiculoDetalle vehiculoModificado = new VehiculoDetalle(
            idVehiculoOriginal, marcaModelo, precioDia, sucursalNombre,
            descripcion, imagenUrl, anio,
            idCategoria, idModelo, idSucursal
        ); //crea objeto con datos actualizados
        vehiculoModificado.setCategoriaNombre(categoriaNombre);

        boolean exito = false;
        try {
             exito = model.actualizarVehiculo(vehiculoModificado); //actualiza datos en bd
        } catch (Exception ex) {
             view.mostrarError("Ocurrió un error inesperado al guardar los cambios en BDD.");
             return;
        }

        if (exito) {
            view.mostrarExito("Vehículo con ID '" + idVehiculoOriginal + "' actualizado correctamente."); //confirmacion
            view.cerrar();
            volverAlMenu();
        } else {
            view.mostrarError("No se pudo actualizar el vehículo en la base de datos."); //fallo en la actualizacion
        }
    }

    private void cancelar() {
        view.cerrar(); //cierra la vista sin guardar
        volverAlMenu(); //vuelve al menu principal
    }

    private void volverAlMenu() {
        MenuView menuView = new MenuView(this.usuarioRol);
        new MenuController(menuView, this.model);
        menuView.setVisible(true); //muestra menu principal
    }
}
