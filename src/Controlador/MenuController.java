package Controlador; // Updated package

import Modelo.Vehiculo;
import Modelo.VehiculoDAO;
import Vista.DetalleView;
import Vista.MenuView;
import Vista.ModVehiculoView;
import Controlador.DetalleController; 
import Controlador.ModVehiculoController; 
import Main.MainApp;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.TreeSet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import Vista.GestionContratosView;
import Controlador.GestionContratosController;

public class MenuController implements ActionListener {
    private final MenuView view;
    private final VehiculoDAO model;

    public MenuController(MenuView view, VehiculoDAO model) {
        this.view = view;
        this.model = model;
        this.view.setController(this); //asigna controlador a la vista
        cargarDatosFiltros(); //carga filtros iniciales
        actualizarVehiculos(); //muestra lista inicial
    }

    private void cargarDatosFiltros() {
        try {
            TreeSet<String> anios = model.getAniosDisponibles();
            TreeSet<String> categorias = model.getCategoriasDisponibles();
            TreeSet<String> sucursales = model.getSucursalesDisponibles();
            view.poblarFiltros(anios, categorias, sucursales); //llena los combos con datos
        } catch (Exception e) {
            view.mostrarMensaje("Error al cargar los filtros desde la base de datos: " + e.getMessage()); //error al cargar datos
            e.printStackTrace();
        }
    }

    private void actualizarVehiculos() {
        String anio = null, cat = null, precio = null, suc = null;
        try {
            Object selectedAnio = view.getCmbAnio().getSelectedItem();
            if (selectedAnio != null && !selectedAnio.equals("Año")) anio = (String) selectedAnio;
            Object selectedCat = view.getCmbCategoria().getSelectedItem();
            if (selectedCat != null && !selectedCat.equals("Categoría")) cat = (String) selectedCat;
            Object selectedPrecio = view.getCmbPrecio().getSelectedItem();
            if (selectedPrecio != null && !selectedPrecio.equals("Rango Precio")) precio = (String) selectedPrecio;
            Object selectedSuc = view.getCmbSucursal().getSelectedItem();
            if (selectedSuc != null && !selectedSuc.equals("Sucursal")) suc = (String) selectedSuc;

            List<Vehiculo> vehiculos = model.buscarVehiculos(anio, cat, precio, suc); //consulta vehiculos segun filtros
            view.actualizarLista(vehiculos); //actualiza tabla o lista de vehiculos
        } catch (Exception e) {
             view.mostrarMensaje("Error al buscar o actualizar la lista de vehículos: " + e.getMessage());
             e.printStackTrace();
             view.actualizarLista(null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource(); //identifica el componente que genero el evento

        if (source instanceof JComboBox) {
            actualizarVehiculos(); //actualiza lista si cambia algun filtro

        } else if (source == view.getBtnCerrarSesion()) {
            int confirm = JOptionPane.showConfirmDialog(view, "¿Estás seguro de que quieres cerrar sesión?", "Confirmar Cierre de Sesión", JOptionPane.YES_NO_OPTION); //confirma cierre sesion
            if (confirm == JOptionPane.YES_OPTION) {
                view.cerrar();
                MainApp.mostrarLogin(); //vuelve al login
            }

        } else if (source == view.getBtnAccionVehiculo()) { 
            Vehiculo vehiculoSeleccionado = view.getVehiculoSeleccionado(); //obtiene vehiculo seleccionado
            if (vehiculoSeleccionado == null || vehiculoSeleccionado.getIdVehiculo() == null || vehiculoSeleccionado.getIdVehiculo().trim().isEmpty() || vehiculoSeleccionado.getMarcaModelo().equals("No se encontraron vehículos")) {
                view.mostrarMensaje("Por favor, seleccione un vehículo válido de la lista para continuar.");
                return;
            }
            view.cerrar();
            DetalleView detalleView = new DetalleView(view.getUsuarioRol());
            new DetalleController(detalleView, model, vehiculoSeleccionado.getIdVehiculo(), view.getUsuarioRol()); //abre vista de detalles
            detalleView.setVisible(true);

        } else if (source == view.getBtnModificarVehiculo()) { 
            Vehiculo vehiculoSeleccionado = view.getVehiculoSeleccionado(); //obtiene vehiculo a modificar
            if (vehiculoSeleccionado == null || vehiculoSeleccionado.getIdVehiculo() == null || vehiculoSeleccionado.getIdVehiculo().trim().isEmpty() || vehiculoSeleccionado.getMarcaModelo().equals("No se encontraron vehículos")) {
                view.mostrarMensaje("Por favor, seleccione un vehículo válido de la lista para modificar.");
                return;
            }
            try {
                view.cerrar();
                ModVehiculoView modView = new ModVehiculoView();
                new ModVehiculoController(modView, model, vehiculoSeleccionado.getIdVehiculo(), view.getUsuarioRol()); //abre ventana de modificacion
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado al intentar abrir la ventana de modificación.\nConsulte la consola.", "Error Fatal", JOptionPane.ERROR_MESSAGE);
                MainApp.mostrarLogin();
            }

        } else if (source == view.getBtnGestionarContratos()) { 
            view.cerrar();
            GestionContratosView gestionView = new GestionContratosView();
            new GestionContratosController(gestionView, model, view.getUsuarioRol()); //abre gestion de contratos
            gestionView.setVisible(true);

        } else {
             System.out.println("MenuController: Evento no manejado de fuente: " + source); //accion no identificada
        }
    }
}
