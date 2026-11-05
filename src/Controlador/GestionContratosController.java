package Controlador;

import Modelo.Contrato;
import Modelo.VehiculoDAO;
import Vista.GestionContratosView;
import Vista.MenuView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class GestionContratosController implements ActionListener {

    private final GestionContratosView view;
    private final VehiculoDAO model;
    private final String usuarioRol;
    private List<Contrato> listaContratosActual; //guarda contratos cargados para su uso

    public GestionContratosController(GestionContratosView view, VehiculoDAO model, String usuarioRol) {
        this.view = view;
        this.model = model;
        this.usuarioRol = usuarioRol;
        this.view.setController(this);
        actualizarTablaContratos(); //carga inicial de datos en tabla
    }

    private void actualizarTablaContratos() {
        String filtro = (String) view.getCmbFiltroEstado().getSelectedItem(); //obtiene estado seleccionado
        if (filtro == null) {
            filtro = "Todos"; //valor por defecto
        }
        this.listaContratosActual = model.getContratos(filtro); //consulta contratos filtrados
        view.poblarTabla(this.listaContratosActual); //muestra contratos en la tabla
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource(); //detecta el componente que genero la accion

        if (source == view.getCmbFiltroEstado()) {
            actualizarTablaContratos(); //actualiza lista segun filtro

        } else if (source == view.getBtnModificarEstado()) {
            modificarEstadoContrato(); //cambia estado del contrato

        } else if (source == view.getBtnVerDetalle()) {
            verDetalleContrato(); //muestra detalle del contrato

        } else if (source == view.getBtnVolver()) {
            view.cerrar();
            volverAlMenu(); //retorna al menu principal
        }
    }

    private void verDetalleContrato() {
        String idContrato = view.getSelectedContratoId(); //obtiene id seleccionado
        if (idContrato == null) {
            view.mostrarMensaje("Por favor, seleccione un contrato de la tabla.");
            return;
        }
        Contrato contratoSeleccionado = null;
        for (Contrato c : listaContratosActual) { //busca contrato en lista
            if (c.getIdContrato().equals(idContrato)) {
                contratoSeleccionado = c;
                break;
            }
        }
        if (contratoSeleccionado != null) {
            view.mostrarDetalleContrato(contratoSeleccionado); //muestra datos
        } else {
            view.mostrarMensaje("Error: No se encontró el contrato seleccionado."); //error si no coincide
        }
    }

    private void modificarEstadoContrato() {
        String idContrato = view.getSelectedContratoId();
        String estadoActual = view.getSelectedContratoEstado(); //obtiene estado actual
        if (idContrato == null) {
            view.mostrarMensaje("Por favor, seleccione un contrato de la tabla para modificar.");
            return;
        }

        String nuevoEstado;
        if ("Activo".equals(estadoActual)) {
            nuevoEstado = "Finalizado"; //cambia a finalizado
        } else if ("Finalizado".equals(estadoActual)) {
            nuevoEstado = "Activo"; //reactiva contrato
        } else {
            view.mostrarMensaje("Solo se pueden modificar contratos con estado 'Activo' o 'Finalizado'.");
            return;
        }

        int confirm = view.mostrarConfirmacion(
            "¿Desea cambiar el estado del contrato " + idContrato + "\n" +
            "de '" + estadoActual + "' a '" + nuevoEstado + "'?",
            "Confirmar Cambio de Estado"
        ); //confirma cambio de estado

        if (confirm == JOptionPane.YES_OPTION) {
            boolean exito = model.updateContractStatus(idContrato, nuevoEstado); //actualiza en bd
            if (exito) {
                view.mostrarMensaje("Estado actualizado correctamente.");
                actualizarTablaContratos(); //recarga tabla
            } else {
                view.mostrarMensaje("Error: No se pudo actualizar el estado en la base de datos.");
            }
        }
    }

    private void volverAlMenu() {
        MenuView menuView = new MenuView(this.usuarioRol);
        new MenuController(menuView, this.model);
        menuView.setVisible(true); //abre menu principal
    }
}
