package Controlador;

import Vista.PagoView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Desktop;
import java.net.URI;

public class PagoController implements ActionListener {

    private final PagoView view;

    public PagoController(PagoView view) {
        this.view = view;
        this.view.setController(this); //conecta vista con controlador
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource(); //detecta boton presionado
        if (source == view.getBtnEfectivo()) {
            procesarPagoEfectivo(); //accion pago en efectivo
        } else if (source == view.getBtnTarjeta()) {
            procesarPagoTarjeta(); //accion pago con tarjeta
        }
    }

    private void procesarPagoEfectivo() {
        view.mostrarMensaje("Pago en efectivo registrado exitosamente.", "Pago Exitoso", JOptionPane.INFORMATION_MESSAGE); //confirma pago efectivo
        view.cerrar(); //cierra ventana
    }

    private void procesarPagoTarjeta() {
        try {
            URI webpayUri = new URI("https://www.transbank.cl/"); //direccion web simulada
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(webpayUri); //abre navegador
                view.mostrarMensaje("Redirigiendo a la pasarela de pago...\n(Espere la confirmación del pago en la web).", "Pago con Tarjeta", JOptionPane.INFORMATION_MESSAGE);
                view.mostrarMensaje("Pago con tarjeta procesado exitosamente.", "Pago Exitoso", JOptionPane.INFORMATION_MESSAGE); //confirmacion simulada
            } else {
                view.mostrarMensaje("No se pudo abrir el navegador para el pago.", "Error", JOptionPane.ERROR_MESSAGE); //error al abrir navegador
            }
        } catch (Exception ex) {
            view.mostrarMensaje("Error al intentar redirigir: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); //error de redireccion
        } finally {
            view.cerrar(); //cierra ventana al finalizar
        }
    }
}
