package Vista;

import Controlador.GestionContratosController;
import Modelo.Contrato;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * Vista (JFrame) para la Gestión de Contratos por parte del Administrador.
 */
public class GestionContratosView extends JFrame {
    private JComboBox<String> cmbFiltroEstado;
    private JTable tblContratos;
    private DefaultTableModel tableModel;
    private JButton btnModificarEstado;
    private JButton btnVerDetalle;
    private JButton btnVolver;

    public GestionContratosView() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Gestión de Contratos (Administrador)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.WHITE);

        // Panel Superior (Filtro)
        JPanel panelFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFiltro.setBackground(Color.WHITE);
        panelFiltro.add(new JLabel("Filtrar por estado:"));
        // Opciones de filtro según lo solicitado
        cmbFiltroEstado = new JComboBox<>(new String[]{"Todos", "Activo", "Finalizado"});
        panelFiltro.add(cmbFiltroEstado);
        panel.add(panelFiltro, BorderLayout.NORTH);

        // Panel Central (Tabla)
        String[] columnNames = {"ID Contrato", "DNI Cliente", "Patente", "Estado", "Fecha Inicio", "Fecha Fin"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabla no editable directamente
            }
        };
        tblContratos = new JTable(tableModel);
        tblContratos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblContratos.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(tblContratos);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel Inferior (Botones)
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setBackground(Color.WHITE);
        btnModificarEstado = new JButton("Cambiar Estado (Activo/Finalizado)");
        btnVerDetalle = new JButton("Ver Detalle Completo");
        btnVolver = new JButton("Volver al Menú");
        
        panelBotones.add(btnModificarEstado);
        panelBotones.add(btnVerDetalle);
        panelBotones.add(btnVolver);
        panel.add(panelBotones, BorderLayout.SOUTH);

        add(panel);
    }

    /**
     * Asigna el controlador a los componentes interactivos.
     */
    public void setController(GestionContratosController controller) {
        cmbFiltroEstado.addActionListener(controller);
        btnModificarEstado.addActionListener(controller);
        btnVerDetalle.addActionListener(controller);
        btnVolver.addActionListener(controller);
    }

    /**
     * Llena la tabla con la lista de contratos recibida.
     */
    public void poblarTabla(List<Contrato> contratos) {
        tableModel.setRowCount(0); // Limpiar tabla
        if (contratos != null) {
            for (Contrato c : contratos) {
                Object[] row = {
                    c.getIdContrato(),
                    c.getDni(),
                    c.getPatente(),
                    c.getEstadoContrato(),
                    c.getFechaInicio(),
                    c.getFechaFin()
                };
                tableModel.addRow(row);
            }
        }
    }
    
    // --- Getters para el Controlador ---
    public JComboBox<String> getCmbFiltroEstado() { return cmbFiltroEstado; }
    public JButton getBtnModificarEstado() { return btnModificarEstado; }
    public JButton getBtnVerDetalle() { return btnVerDetalle; }
    public JButton getBtnVolver() { return btnVolver; }
    public JTable getTblContratos() { return tblContratos; }
    public DefaultTableModel getTableModel() { return tableModel; }

    /**
     * Obtiene el ID del contrato de la fila seleccionada en la tabla.
     * @return El ID del contrato (Col 0) o null si no hay selección.
     */
    public String getSelectedContratoId() {
        int selectedRow = tblContratos.getSelectedRow();
        if (selectedRow == -1) {
            return null;
        }
        return (String) tableModel.getValueAt(selectedRow, 0); 
    }

    /**
     * Obtiene el Estado del contrato de la fila seleccionada en la tabla.
     * @return El Estado (Col 3) o null si no hay selección.
     */
    public String getSelectedContratoEstado() {
         int selectedRow = tblContratos.getSelectedRow();
        if (selectedRow == -1) {
            return null;
        }
        return (String) tableModel.getValueAt(selectedRow, 3);
    }
    
    /**
     * Muestra los detalles completos de un contrato en un JOptionPane.
     */
    public void mostrarDetalleContrato(Contrato c) {
         if (c == null) {
             mostrarMensaje("No se pudo cargar el detalle del contrato.");
             return;
         }
         
         // Usamos un JTextArea dentro de un JScrollPane para mostrar texto largo
         JTextArea textArea = new JTextArea(20, 45);
         textArea.setEditable(false);
         textArea.setLineWrap(true);
         textArea.setWrapStyleWord(true);
         
         StringBuilder sb = new StringBuilder();
         sb.append("--- Detalle del Contrato ---\n\n");
         sb.append("ID Contrato: \t").append(c.getIdContrato()).append("\n");
         sb.append("Estado: \t").append(c.getEstadoContrato()).append("\n\n");
         sb.append("Cliente DNI: \t").append(c.getDni()).append("\n");
         sb.append("Cliente Teléfono: \t").append(c.getTelefono()).append("\n\n");
         sb.append("Vehículo Patente: \t").append(c.getPatente()).append("\n");
         sb.append("Vehículo Modelo ID: \t").append(c.getIdModelo()).append("\n\n");
         sb.append("Fecha Inicio: \t").append(c.getFechaInicio()).append("\n");
         sb.append("Fecha Fin: \t").append(c.getFechaFin()).append("\n\n");
         sb.append("Condiciones:\n").append(c.getCondiciones()).append("\n");
         
         textArea.setText(sb.toString());
         textArea.setCaretPosition(0); // Mostrar desde el inicio
         
         JScrollPane scrollPane = new JScrollPane(textArea);
         JOptionPane.showMessageDialog(this, scrollPane, "Detalle Contrato " + c.getIdContrato(), JOptionPane.INFORMATION_MESSAGE);
    }

    // --- Métodos de utilidad para el Controlador ---
    public void mostrarMensaje(String msg) { 
        JOptionPane.showMessageDialog(this, msg); 
    }
    
    public int mostrarConfirmacion(String msg, String title) {
        return JOptionPane.showConfirmDialog(this, msg, title, JOptionPane.YES_NO_OPTION);
    }
    
    public void cerrar() { 
        this.dispose(); 
    }
}