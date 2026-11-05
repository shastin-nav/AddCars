package Vista;

import Controlador.PagoController;
import javax.swing.*;
import java.awt.*;

// Usamos JDialog para que sea una ventana modal
public class PagoView extends JDialog {

    private JButton btnEfectivo;
    private JButton btnTarjeta;
    private PagoController controller;

    public PagoView(Frame owner) {
        // 'true' hace que el diálogo sea modal (bloquea la ventana anterior)
        super(owner, "Seleccionar Método de Pago", true);
        initComponents();
        setSize(400, 200); // Tamaño de ventana ajustado
        setLocationRelativeTo(owner); // Centrar sobre la ventana padre
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    // Método para que el controlador se asigne a sí mismo
    public void setController(PagoController controller) {
        this.controller = controller;
        this.btnEfectivo.addActionListener(controller);
        this.btnTarjeta.addActionListener(controller);
    }

    private void initComponents() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));

        btnEfectivo = new JButton("Pago Efectivo");
        btnTarjeta = new JButton("Tarjeta de Crédito");

        Font buttonFont = new Font("SansSerif", Font.BOLD, 14);
        btnEfectivo.setFont(buttonFont);
        btnTarjeta.setFont(buttonFont);

        // Tamaño de botones ajustado para que el texto quepa
        btnEfectivo.setPreferredSize(new Dimension(180, 40));
        btnTarjeta.setPreferredSize(new Dimension(180, 40));

        add(btnEfectivo);
        add(btnTarjeta);
    }

    // Getters para que el controlador sepa qué botón se presionó
    public JButton getBtnEfectivo() {
        return btnEfectivo;
    }

    public JButton getBtnTarjeta() {
        return btnTarjeta;
    }

    // Método simple para mostrar mensajes
    public void mostrarMensaje(String mensaje, String titulo, int messageType) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, messageType);
    }

    // Método para cerrar la ventana
    public void cerrar() {
        this.dispose();
    }
}