package Vista; // Updated package

import Controlador.MenuController;
import Modelo.Vehiculo;
import java.awt.*;
import javax.swing.*;
import java.util.List;
import java.util.TreeSet;

/**
 * Vista (JFrame) para el Menú Principal. Muestra filtros y lista de vehículos.
 */
public class MenuView extends JFrame {
    // Componentes de la UI
    private JComboBox<String> cmbAnio, cmbCategoria, cmbPrecio, cmbSucursal;
    private JButton btnCerrarSesion;
    private JButton btnAccionVehiculo; // Botón para Registrar Arriendo (R01) o Arrendar (R02 - si se implementa)
    private JButton btnModificarVehiculo; // Botón para Modificar Vehículo (ADM)
    private JButton btnGestionarContratos; // *** NUEVO BOTÓN PARA ADM ***
    private JList<Vehiculo> listaResultados;
    private DefaultListModel<Vehiculo> listModel;

    // Datos
    private String usuarioRol; // Guarda el rol del usuario logueado ('ADM', 'R01', 'R02')

    /**
     * Constructor de la vista del menú.
     * @param rol El rol del usuario que inició sesión.
     */
    public MenuView(String rol) {
        this.usuarioRol = rol; // Almacena el rol
        initComponents(); // Inicializa y configura los componentes gráficos
    }

    /**
     * Asigna el controlador a los componentes interactivos de esta vista.
     * @param controller El MenuController responsable de manejar eventos.
     */
    public void setController(MenuController controller) {
        // Conectar listeners a los ComboBox de filtros
        this.cmbAnio.addActionListener(controller);
        this.cmbCategoria.addActionListener(controller);
        this.cmbPrecio.addActionListener(controller);
        this.cmbSucursal.addActionListener(controller);
        // Conectar listener al botón de cerrar sesión
        this.btnCerrarSesion.addActionListener(controller);
        // Conectar listener al botón de acción principal (si existe)
        if (this.btnAccionVehiculo != null) {
            this.btnAccionVehiculo.addActionListener(controller);
        }
        // Conectar listener al botón de modificar (si existe y es visible)
        if (this.btnModificarVehiculo != null) {
             this.btnModificarVehiculo.addActionListener(controller);
        }
        // Conectar listener al nuevo botón de contratos (si existe)
        if (this.btnGestionarContratos != null) {
            this.btnGestionarContratos.addActionListener(controller);
        }
    }

    /**
     * Construye la interfaz gráfica del menú.
     */
    private void initComponents() {
        // Configuración básica de la ventana
        setTitle("AddCars - Menú Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la app al cerrar esta ventana
        setSize(900, 600); // Tamaño fijo
        setLocationRelativeTo(null); // Centrar en pantalla
        setResizable(false); // No permitir redimensionar

        // Panel principal con BorderLayout
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        // --- Header (Norte) ---
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30)); // Márgenes
        JLabel lblLogo = new JLabel("AddCars");
        lblLogo.setFont(new Font("SansSerif", Font.BOLD, 28));
        header.add(lblLogo, BorderLayout.WEST); // Logo a la izquierda
        btnCerrarSesion = new JButton("Cerrar sesión");
        // Estilo del botón Cerrar Sesión
        btnCerrarSesion.setFocusPainted(false);
        btnCerrarSesion.setContentAreaFilled(false);
        btnCerrarSesion.setBorderPainted(false);
        btnCerrarSesion.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnCerrarSesion.setForeground(Color.DARK_GRAY); // Color más suave
        btnCerrarSesion.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cursor de mano
        header.add(btnCerrarSesion, BorderLayout.EAST); // Botón a la derecha
        panel.add(header, BorderLayout.NORTH);

        // --- Panel de Filtros (Centro) ---
        JPanel filtros = new JPanel();
        filtros.setBackground(Color.WHITE);
        filtros.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 15)); // Flujo centrado con espacios
        // Crear ComboBoxes
        cmbAnio = new JComboBox<>();
        cmbCategoria = new JComboBox<>();
        cmbPrecio = new JComboBox<>();
        cmbSucursal = new JComboBox<>();
        // Estilo común para los ComboBoxes
        JComboBox[] combos = {cmbAnio, cmbCategoria, cmbPrecio, cmbSucursal};
        for (JComboBox combo : combos) {
            combo.setPreferredSize(new Dimension(150, 35)); // Tamaño
            combo.setBackground(new Color(240, 240, 240)); // Fondo gris claro
            combo.setForeground(Color.BLACK); // Texto negro
            combo.setFont(new Font("SansSerif", Font.PLAIN, 14));
            filtros.add(combo); // Añadir al panel de filtros
        }
        panel.add(filtros, BorderLayout.CENTER);

        // --- Panel Sur (Lista y Botones de Acción) ---
        JPanel panelSur = new JPanel(new BorderLayout(10, 10)); // Layout y espacios
        panelSur.setBackground(Color.WHITE);
        panelSur.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 30, 20, 30), // Margen exterior
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1))); // Borde interior

        // Lista de resultados
        listModel = new DefaultListModel<>();
        listaResultados = new JList<>(listModel);
        listaResultados.setFont(new Font("Monospaced", Font.PLAIN, 13)); // Fuente monoespaciada para alinear
        listaResultados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Solo seleccionar uno
        JScrollPane scrollPaneResultados = new JScrollPane(listaResultados); // Panel con scroll
        scrollPaneResultados.setPreferredSize(new Dimension(800, 300)); // Tamaño preferido
        panelSur.add(scrollPaneResultados, BorderLayout.CENTER); // Lista al centro del panel sur

        // Panel para los botones inferiores (Registrar/Modificar/Gestionar)
        JPanel panelBotonesAccion = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Centrados
        panelBotonesAccion.setBackground(Color.WHITE); // Fondo blanco

        // Botón "Registrar Arriendo" (Visible solo para Empleado 'R01')
        if ("R01".equals(this.usuarioRol)) {
            btnAccionVehiculo = new JButton("Registrar Arriendo");
            btnAccionVehiculo.setFont(new Font("SansSerif", Font.BOLD, 14));
            btnAccionVehiculo.setBackground(Color.WHITE);
            btnAccionVehiculo.setForeground(Color.BLACK);
            btnAccionVehiculo.setFocusPainted(false);
            btnAccionVehiculo.setPreferredSize(new Dimension(200, 40));
            panelBotonesAccion.add(btnAccionVehiculo); // Añadir al panel de botones
        }

        // Botones "Modificar Vehículo" y "Gestionar Contratos" (Visible solo para Administrador 'ADM')
        if ("ADM".equals(this.usuarioRol)) {
             btnModificarVehiculo = new JButton("Modificar Vehículo");
             btnModificarVehiculo.setFont(new Font("SansSerif", Font.BOLD, 14));
             btnModificarVehiculo.setBackground(Color.WHITE); // Color diferente
             btnModificarVehiculo.setForeground(Color.BLACK);
             btnModificarVehiculo.setFocusPainted(false);
             btnModificarVehiculo.setPreferredSize(new Dimension(200, 40));
             panelBotonesAccion.add(btnModificarVehiculo); // Añadir al panel de botones
             
             // --- NUEVO BOTÓN AÑADIDO ---
             btnGestionarContratos = new JButton("Gestionar Contratos");
             btnGestionarContratos.setFont(new Font("SansSerif", Font.BOLD, 14));
             btnGestionarContratos.setBackground(Color.WHITE); 
             btnGestionarContratos.setForeground(Color.BLACK);
             btnGestionarContratos.setFocusPainted(false);
             btnGestionarContratos.setPreferredSize(new Dimension(200, 40));
             panelBotonesAccion.add(btnGestionarContratos); // Añadir al panel
        }
        
        // Si hay botones en el panel, añadirlo al panelSur
        if (panelBotonesAccion.getComponentCount() > 0) {
            panelSur.add(panelBotonesAccion, BorderLayout.SOUTH);
        }

        panel.add(panelSur, BorderLayout.SOUTH); // Añadir panelSur al panel principal
        add(panel); // Añadir el panel principal al JFrame
    }

    // --- Getters para que el Controller acceda a los componentes ---
    public JComboBox<String> getCmbAnio() { return cmbAnio; }
    public JComboBox<String> getCmbCategoria() { return cmbCategoria; }
    public JComboBox<String> getCmbPrecio() { return cmbPrecio; }
    public JComboBox<String> getCmbSucursal() { return cmbSucursal; }
    public JButton getBtnCerrarSesion() { return btnCerrarSesion; }
    public JButton getBtnAccionVehiculo() { return btnAccionVehiculo; } // Puede ser null si no es R01
    public JButton getBtnModificarVehiculo() { return btnModificarVehiculo; } // Puede ser null si no es ADM
    public JButton getBtnGestionarContratos() { return btnGestionarContratos; } // *** NUEVO GETTER ***
    public Vehiculo getVehiculoSeleccionado() { return listaResultados.getSelectedValue(); }
    public String getUsuarioRol() { return usuarioRol; }

    // --- Métodos para que el Controller actualice la Vista ---

    /**
     * Llena los ComboBox de filtros con datos obtenidos del DAO.
     */
    public void poblarFiltros(TreeSet<String> anios, TreeSet<String> categorias, TreeSet<String> sucursales) {
        // Limpiar items previos para evitar duplicados si se llama de nuevo
        cmbAnio.removeAllItems();
        cmbCategoria.removeAllItems();
        cmbSucursal.removeAllItems();
        cmbPrecio.removeAllItems();

        // Añadir opción por defecto y luego los datos
        cmbAnio.addItem("Año"); // Opción default
        if (anios != null) { for (String s : anios) cmbAnio.addItem(s); }

        cmbCategoria.addItem("Categoría"); // Opción default
        if (categorias != null) { for (String s : categorias) cmbCategoria.addItem(s); }

        cmbSucursal.addItem("Sucursal"); // Opción default
        if (sucursales != null) { for (String s : sucursales) cmbSucursal.addItem(s); }

        // Añadir opciones fijas de precio
        cmbPrecio.addItem("Rango Precio"); // Opción default
        cmbPrecio.addItem("$20.000 - $40.000");
        cmbPrecio.addItem("$40.001 - $70.000");
        cmbPrecio.addItem("$70.001+");
    }

    /**
     * Actualiza la JList con la lista de vehículos recibida.
     */
    public void actualizarLista(List<Vehiculo> vehiculos) {
        listModel.clear(); // Limpiar la lista actual
        if (vehiculos == null || vehiculos.isEmpty()) {
            // Mostrar un mensaje indicativo en la lista si no hay resultados
            // Crear un Vehiculo "placeholder" es una opción
             listModel.addElement(new Vehiculo(" ", "No se encontraron vehículos", 0, " "));
        } else {
            // Añadir cada vehículo a la lista
            for (Vehiculo v : vehiculos) {
                listModel.addElement(v); // Usa el toString() de Vehiculo
            }
        }
        // Asegurar que la lista se repinte correctamente
        listaResultados.repaint();
        listaResultados.revalidate();
    }

    /**
     * Muestra un mensaje emergente (JOptionPane).
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    /**
     * Cierra (descarta) esta ventana.
     */
    public void cerrar() {
        this.dispose(); // Libera los recursos de la ventana
    }
}