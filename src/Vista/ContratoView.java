package Vista;

import Modelo.Contrato;
import Modelo.VehiculoDetalle;
import java.awt.*;
import javax.swing.*;

public class ContratoView extends JFrame {

    private JTextArea txtDetallesVehiculo;
    private JTextArea txtCondicionesContrato;
    private JButton btnVolver;

    public ContratoView() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Detalles de Contrato");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel panelHeader = new JPanel();
        panelHeader.setBackground(Color.BLACK);
        panelHeader.setPreferredSize(new Dimension(800, 80));
        panelHeader.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 15));
        JLabel lblLogo = new JLabel("AddCars");
        lblLogo.setForeground(Color.WHITE);
        lblLogo.setFont(new Font("Arial", Font.BOLD, 30));
        panelHeader.add(lblLogo);
        add(panelHeader, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel(new GridBagLayout());
        panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;

        JPanel panelIzquierdo = new JPanel(new BorderLayout());
        panelIzquierdo.setBorder(BorderFactory.createTitledBorder("Detalles del Vehículo"));
        txtDetallesVehiculo = new JTextArea();
        txtDetallesVehiculo.setEditable(false);
        txtDetallesVehiculo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        panelIzquierdo.add(new JScrollPane(txtDetallesVehiculo), BorderLayout.CENTER);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.35;
        panelCentral.add(panelIzquierdo, gbc);

        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.setBorder(BorderFactory.createTitledBorder("Condiciones y Requerimientos"));
        txtCondicionesContrato = new JTextArea();
        txtCondicionesContrato.setEditable(false);
        txtCondicionesContrato.setFont(new Font("SansSerif", Font.PLAIN, 14));
        panelDerecho.add(new JScrollPane(txtCondicionesContrato), BorderLayout.CENTER);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.65;
        panelCentral.add(panelDerecho, gbc);

        add(panelCentral, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnVolver = new JButton("Volver");
        btnVolver.setBackground(Color.WHITE);
        btnVolver.setForeground(Color.BLACK);
        panelBotones.add(btnVolver);
        add(panelBotones, BorderLayout.SOUTH);
    }

    public void mostrarDetalles(VehiculoDetalle detalle, Contrato contrato) {
        if (detalle != null) {
            String infoVehiculo = "ID Vehículo: " + detalle.getIdVehiculo() + "\n\n" +
                                  "Modelo: " + detalle.getMarcaModelo() + "\n" +
                                  "Año: " + detalle.getAnio() + "\n" +
                                  "Categoría: " + detalle.getCategoriaNombre() + "\n\n" +
                                  "Precio por Día: $" + String.format("%,d", detalle.getPrecioDia()) + "\n\n" +
                                  "Sucursal: " + detalle.getSucursal();
            txtDetallesVehiculo.setText(infoVehiculo);
            txtDetallesVehiculo.setCaretPosition(0);
        }

        if (contrato != null) {
            txtCondicionesContrato.setText(contrato.getCondiciones());
            txtCondicionesContrato.setCaretPosition(0);
        } else {
            txtCondicionesContrato.setText("Este vehículo no tiene un contrato asociado.");
        }
    }
    
    public JButton getBtnVolver() { return btnVolver; }
    public void setController(Controlador.ContratoController controller) {
        btnVolver.addActionListener(controller);
    }
    public void cerrar() { dispose(); }
}