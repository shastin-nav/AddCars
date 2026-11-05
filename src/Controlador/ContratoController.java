package Controlador;

import Modelo.Contrato;
import Modelo.VehiculoDAO;
import Modelo.VehiculoDetalle;
import Vista.ContratoView;
import Vista.MenuView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContratoController implements ActionListener {

    private final ContratoView view;
    private final VehiculoDAO model;
    private final String idVehiculo;
    private final String usuarioRol;

    public ContratoController(ContratoView view, VehiculoDAO model, String idVehiculo, String usuarioRol) {
        this.view = view;
        this.model = model;
        this.idVehiculo = idVehiculo;
        this.usuarioRol = usuarioRol;
        this.view.setController(this); //conecta vista con controlador
        cargarYMostrarDetalles(); //carga datos del vehiculo y contrato
    }

    private void cargarYMostrarDetalles() {
        VehiculoDetalle detalle = model.getVehiculoDetalle(idVehiculo); //obtiene detalle del vehiculo
        Contrato contrato = model.getContratoPorVehiculoId(idVehiculo); //obtiene contrato segun id
        view.mostrarDetalles(detalle, contrato); //muestra informacion en la vista
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getBtnVolver()) { //accion boton volver
            view.cerrar();
            volverAlMenu(); //abre menu principal
        }
    }

    private void volverAlMenu() {
        MenuView menuView = new MenuView(this.usuarioRol);
        new MenuController(menuView, this.model); //crea controlador del menu
        menuView.setVisible(true);
    }
}
