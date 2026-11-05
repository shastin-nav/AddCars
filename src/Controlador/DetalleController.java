package Controlador;

import Modelo.Mantenimiento;
import Modelo.Seguro;
import Modelo.VehiculoDAO;
import Modelo.VehiculoDetalle;
import Vista.ContratoView;
import Vista.DetalleView;
import Vista.MenuView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import Vista.PagoView;
import Controlador.PagoController;

public class DetalleController implements ActionListener {

    private final DetalleView view;
    private final VehiculoDAO model;
    private final String idVehiculo;
    private final String usuarioRol;

    private VehiculoDetalle vehiculoActual;

    public DetalleController(DetalleView view, VehiculoDAO model, String idVehiculo, String usuarioRol) {
        this.view = view;
        this.model = model;
        this.idVehiculo = idVehiculo;
        this.usuarioRol = usuarioRol;
        this.view.setController(this); //asigna el controlador a la vista
        cargarDetallesIniciales(); //carga datos al iniciar
    }

    private void cargarDetallesIniciales() {
        this.vehiculoActual = model.getVehiculoDetalle(idVehiculo); //obtiene detalle del vehiculo
        if (this.vehiculoActual != null) {
            view.mostrarDetallesVehiculo(this.vehiculoActual); //muestra datos si existen
        } else {
            view.mostrarMensaje("Error al cargar los detalles del vehículo."); //mensaje de error
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource(); //identifica el origen del evento

        if (source == view.getBtnAccionContrato()) {
            System.out.println("DetalleController: Botón 'Registrar Contrato' presionado.");
            ContratoView contratoView = new ContratoView(); //abre ventana de contrato
            new ContratoController(contratoView, model, idVehiculo, usuarioRol);
            contratoView.setVisible(true);
            view.cerrar(); //cierra la vista actual

        } else if (source == view.getBtnSeguro()) {
            if (vehiculoActual != null) {
                view.mostrarInfoSeguro(model.getSeguro(vehiculoActual.getIdVehiculo())); //muestra seguro
            }

        } else if (source == view.getBtnMantenimiento()) {
            if (vehiculoActual != null) {
                view.mostrarMantenimientos(model.getMantenimientos(vehiculoActual.getIdVehiculo())); //muestra mantenimientos
            }

        } else if (source == view.getBtnVolver()) {
            view.cerrar();
            volverAlMenu(); //vuelve al menu principal

        } else if (source == view.getBtnPagar()) {
            String rutCliente = JOptionPane.showInputDialog(view,
                    "Ingrese el RUT del cliente para el pago:", "Confirmar Cliente", JOptionPane.PLAIN_MESSAGE); //solicita rut
            if (rutCliente != null && !rutCliente.trim().isEmpty()) {
                PagoView pagoView = new PagoView(this.view); //crea vista de pago
                new PagoController(pagoView); //crea controlador de pago
                pagoView.setVisible(true); //muestra ventana de pago
            }
        }
    }

    private void volverAlMenu() {
        MenuView menuView = new MenuView(this.usuarioRol);
        new MenuController(menuView, this.model);
        menuView.setVisible(true); //muestra menu principal
    }
}
