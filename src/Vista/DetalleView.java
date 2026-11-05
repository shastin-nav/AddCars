package Vista;

import Controlador.DetalleController;
import Modelo.Mantenimiento;
import Modelo.Seguro;
import Modelo.VehiculoDetalle;
import java.awt.*;
import javax.swing.*;
import java.net.URL;
import java.util.List;

public class DetalleView extends JFrame {

    private JLabel lblVehiculoNombre, lblVehiculoPrecio;
    private JTextArea txtDescripcion;
    private JButton btnSeguro, btnMantenimiento, btnVolver, btnAccionContrato, btnPagar; // Declaración correcta
    private JLabel lblImagenVehiculo;
    private JPanel panelImagen;
    private String usuarioRol;

    public DetalleView(String usuarioRol) {
        this.usuarioRol = usuarioRol;
        initComponents();
    }

    private void initComponents() {
        setTitle("Detalle del Vehículo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panelPrincipal = new JPanel(new BorderLayout(15, 15));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelPrincipal.setBackground(Color.WHITE);

        panelImagen = new JPanel(new BorderLayout());
        panelImagen.setBackground(Color.LIGHT_GRAY);
        lblImagenVehiculo = new JLabel();
        lblImagenVehiculo.setHorizontalAlignment(JLabel.CENTER);
        lblImagenVehiculo.setVerticalAlignment(JLabel.CENTER);
        panelImagen.setPreferredSize(new Dimension(350, 300));
        panelImagen.add(lblImagenVehiculo, BorderLayout.CENTER);
        panelPrincipal.add(panelImagen, BorderLayout.WEST);

        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setBackground(Color.WHITE);

        lblVehiculoNombre = new JLabel("Nombre Vehículo...");
        lblVehiculoNombre.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblVehiculoNombre.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblVehiculoPrecio = new JLabel("Precio...");
        lblVehiculoPrecio.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblVehiculoPrecio.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtDescripcion = new JTextArea("Descripción...");
        txtDescripcion.setEditable(false);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtDescripcion.setBackground(new Color(245, 245, 245));
        JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);
        scrollDescripcion.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollDescripcion.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));

        JPanel panelBotonesInfo = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelBotonesInfo.setBackground(Color.WHITE);
        panelBotonesInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnSeguro = new JButton("Ver Seguro");
        btnMantenimiento = new JButton("Ver Mantenimientos");
        panelBotonesInfo.add(btnSeguro);
        panelBotonesInfo.add(btnMantenimiento);

        panelInfo.add(lblVehiculoNombre);
        panelInfo.add(Box.createVerticalStrut(10));
        panelInfo.add(lblVehiculoPrecio);
        panelInfo.add(Box.createVerticalStrut(15));
        panelInfo.add(new JLabel("Descripción:"));
        panelInfo.add(Box.createVerticalStrut(5));
        panelInfo.add(scrollDescripcion);
        panelInfo.add(Box.createVerticalStrut(15));
        panelInfo.add(panelBotonesInfo);
        panelInfo.add(Box.createVerticalGlue());

        panelPrincipal.add(panelInfo, BorderLayout.CENTER);

        // --- INICIO DE LA CORRECCIÓN DE BOTONES INFERIORES ---
        
        JPanel panelBotonesInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotonesInferior.setBackground(Color.WHITE);
        
        // 1. Inicializar todos los botones
        btnVolver = new JButton("Volver al Menú");
        btnPagar = new JButton("Pagar"); // Inicializar btnPagar aquí

        // 2. Configurar y añadir btnAccionContrato (si aplica)
        btnAccionContrato = null;
        if (usuarioRol != null && usuarioRol.equals("R01")) {
            btnAccionContrato = new JButton("Registrar Contrato");
            btnAccionContrato.setBackground(Color.WHITE);
            btnAccionContrato.setForeground(Color.BLACK);
            
            panelBotonesInferior.add(btnAccionContrato); // Añadir al panel
        }
        
        // 3. Añadir los otros botones al panel
        panelBotonesInferior.add(btnVolver);
        panelBotonesInferior.add(btnPagar); // Añadir btnPagar al panel

        // 4. Añadir el panel inferior al panel principal
        panelPrincipal.add(panelBotonesInferior, BorderLayout.SOUTH);

        // 5. Eliminar las líneas incorrectas que estaban aquí
        // (No más btnPagar.setBounds ni add(btnPagar))

        // --- FIN DE LA CORRECCIÓN ---

        add(panelPrincipal); // Añadir el panel principal al JFrame
    }

    public void setController(DetalleController controller) {
        btnSeguro.addActionListener(controller);
        btnMantenimiento.addActionListener(controller);
        btnVolver.addActionListener(controller);
        btnPagar.addActionListener(controller); // <-- 6. AÑADIR EL LISTENER
        
        if (btnAccionContrato != null) {
            btnAccionContrato.addActionListener(controller);
        }
    }

    public void mostrarDetallesVehiculo(VehiculoDetalle detalle) {
        if (detalle == null) {
            lblVehiculoNombre.setText("Error al cargar");
            lblVehiculoPrecio.setText("");
            txtDescripcion.setText("No se pudieron obtener los detalles del vehículo.");
            lblImagenVehiculo.setIcon(null);
            lblImagenVehiculo.setText("Imagen no disponible");
            return;
        }

        lblVehiculoNombre.setText(detalle.getMarcaModelo());
        lblVehiculoPrecio.setText(String.format("Precio por día: $%,d", detalle.getPrecioDia()));
        txtDescripcion.setText(detalle.getDescripcion() != null ? detalle.getDescripcion() : "Sin descripción disponible.");
        txtDescripcion.setCaretPosition(0);

        String rutaImagen = detalle.getImagenUrl();
        ImageIcon icono = null;
        if (rutaImagen != null && !rutaImagen.trim().isEmpty()) {
            // Lógica original corregida: usa la ruta tal como viene de la BD
            String rutaRecurso = "/" + rutaImagen.trim().replace("\\", "/");
            System.out.println("Cargando imagen desde: " + rutaRecurso);
            URL urlImagen = getClass().getResource(rutaRecurso);
            
            if (urlImagen != null) {
                icono = new ImageIcon(urlImagen);
                if (icono.getIconWidth() > 0) {
                    Image img = icono.getImage();
                    int anchoMax = panelImagen.getPreferredSize().width - 20;
                    int altoMax = panelImagen.getPreferredSize().height - 20;
                    double ratio = Math.min((double) anchoMax / img.getWidth(null), (double) altoMax / img.getHeight(null));
                    if (ratio < 1.0) {
                        int nuevoAncho = (int) (img.getWidth(null) * ratio);
                        int nuevoAlto = (int) (img.getHeight(null) * ratio);
                        Image imgEscalada = img.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
                        icono = new ImageIcon(imgEscalada);
                    }
                }
            } else {
                System.err.println("Error: No se encontró el recurso de imagen en classpath: " + rutaRecurso);
            }
        }
        
        lblImagenVehiculo.setIcon(icono);
        if (icono == null) {
            lblImagenVehiculo.setText("<html><center>Imagen no<br>disponible</center></html>");
        } else {
            lblImagenVehiculo.setText(null);
        }
        panelImagen.revalidate();
        panelImagen.repaint();
    }
    
    public JButton getBtnSeguro() { return btnSeguro; }
    public JButton getBtnMantenimiento() { return btnMantenimiento; }
    public JButton getBtnVolver() { return btnVolver; }
    public JButton getBtnAccionContrato() { return btnAccionContrato; }
    public JButton getBtnPagar() { return btnPagar; } // (Este ya lo tenías, está correcto)

    public void mostrarInfoSeguro(Seguro s) {
        if (s == null) {
            JOptionPane.showMessageDialog(this, "Este vehículo no tiene seguro registrado.", "Info Seguro", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this, "Compañía: " + s.getCompania() + "\nCobertura: " + s.getCobertura(), "Información del Seguro", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarMantenimientos(List<Mantenimiento> lista) {
        if (lista == null || lista.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Este vehículo no tiene mantenimientos registrados.", "Info Mantenimiento", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        StringBuilder info = new StringBuilder("--- Últimos Mantenimientos ---\n\n");
        for (Mantenimiento m : lista) {
            info.append("Fecha: ").append(m.getFecha()).append("\n");
            info.append("Tipo: ").append(m.getTipo()).append("\n");
            info.append("Detalle: ").append(m.getDetalle()).append("\n");
            info.append("----------------------------\n");
        }
        JTextArea textArea = new JTextArea(info.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(350, 200));
        JOptionPane.showMessageDialog(this, scrollPane, "Historial de Mantenimiento", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void mostrarMensaje(String msg) { JOptionPane.showMessageDialog(this, msg); }
    public void cerrar() { this.dispose(); }
}