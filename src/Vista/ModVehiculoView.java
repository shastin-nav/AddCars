/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Controlador.ModVehiculoController;
import Modelo.VehiculoDetalle;
import javax.swing.*;
import java.awt.*;
import java.net.URL; // *** Import NECESARIO para URL ***
import java.awt.Image; // *** Import NECESARIO para Image ***
import java.util.TreeSet;

/**
 * Vista (JFrame) para modificar los datos de un vehículo.
 * Incluye visualización de imagen actual.
 */
public class ModVehiculoView extends JFrame {

    // Componentes de la UI
    private JTextField txtMarcaModelo, txtPrecioDia, txtImagenUrl, txtAnio;
    private JTextArea txtDescripcion;
    private JComboBox<String> cmbSucursal, cmbCategoria;
    private JButton btnGuardar, btnCancelar;
    private JLabel lblVehiculoId;
    // JLabel para imagen actual
    private JLabel lblImagenActual;
    private JPanel panelImagen; // Panel para contener la imagen

    private ModVehiculoController controller;

    public ModVehiculoView() {
        initComponents();
    }

    public void setController(ModVehiculoController controller) {
        this.controller = controller;
        this.btnGuardar.addActionListener(controller);
        this.btnCancelar.addActionListener(controller);
    }

    private void initComponents() {
        setTitle("Modificar Vehículo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(750, 600); // Ancho ajustado para imagen
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout(15, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // --- Panel Izquierdo (Imagen Actual) ---
        panelImagen = new JPanel(new BorderLayout());
        panelImagen.setBackground(Color.LIGHT_GRAY);
        lblImagenActual = new JLabel("<html><center>Imagen<br>Actual</center></html>"); // Placeholder
        lblImagenActual.setHorizontalAlignment(JLabel.CENTER);
        lblImagenActual.setVerticalAlignment(JLabel.CENTER);
        panelImagen.setPreferredSize(new Dimension(300, 250)); // Tamaño para la imagen
        panelImagen.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panelImagen.add(lblImagenActual, BorderLayout.CENTER);
        JPanel panelContenedorImagen = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Panel extra para centrar
        panelContenedorImagen.add(panelImagen);
        panelPrincipal.add(panelContenedorImagen, BorderLayout.WEST); // Panel con imagen a la izquierda

        // --- Panel Derecho (Datos) ---
        JPanel panelDatos = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Fila 0: ID
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.2; // Etiqueta más ancha
        panelDatos.add(new JLabel("ID Vehículo:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.8;
        lblVehiculoId = new JLabel("...");
        lblVehiculoId.setFont(new Font("SansSerif", Font.BOLD, 12));
        panelDatos.add(lblVehiculoId, gbc);

        // Fila 1: Marca/Modelo
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.2;
        panelDatos.add(new JLabel("Marca y Modelo:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.8;
        txtMarcaModelo = new JTextField();
        panelDatos.add(txtMarcaModelo, gbc);

        // Fila 2: Precio
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.2;
        panelDatos.add(new JLabel("Precio por Día ($):"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.8;
        txtPrecioDia = new JTextField();
        panelDatos.add(txtPrecioDia, gbc);

        // Fila 3: Año
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0.2;
        panelDatos.add(new JLabel("Año:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.8;
        txtAnio = new JTextField();
        panelDatos.add(txtAnio, gbc);

         // Fila 4: Sucursal
        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0.2;
        panelDatos.add(new JLabel("Sucursal:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.8;
        cmbSucursal = new JComboBox<>();
        panelDatos.add(cmbSucursal, gbc);

        // Fila 5: Categoría
        gbc.gridx = 0; gbc.gridy = 5; gbc.weightx = 0.2;
        panelDatos.add(new JLabel("Categoría:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.8;
        cmbCategoria = new JComboBox<>();
        panelDatos.add(cmbCategoria, gbc);

        // Fila 6: Descripción
        gbc.gridx = 0; gbc.gridy = 6; gbc.weightx = 0.2; gbc.anchor = GridBagConstraints.NORTHWEST;
        panelDatos.add(new JLabel("Descripción:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.8; gbc.weighty = 1.0; gbc.fill = GridBagConstraints.BOTH;
        txtDescripcion = new JTextArea(4, 15); // Tamaño ajustado
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        JScrollPane scrollDesc = new JScrollPane(txtDescripcion);
        panelDatos.add(scrollDesc, gbc);
        gbc.weighty = 0; gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 7: URL Imagen (Editable)
        gbc.gridx = 0; gbc.gridy = 7; gbc.weightx = 0.2; // *** CORREGIDO: weightx ***
        gbc.anchor = GridBagConstraints.WEST;
        panelDatos.add(new JLabel("URL Imagen:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.8;
        txtImagenUrl = new JTextField();
        txtImagenUrl.setToolTipText("Ej: Imagenes/nombre_archivo.jpg (Relativo a src)");
        panelDatos.add(txtImagenUrl, gbc);

        // Añadir panel de datos al centro/derecha
        panelPrincipal.add(panelDatos, BorderLayout.CENTER);

        // Panel de botones (Sur)
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.setBackground(Color.WHITE);
        btnGuardar.setForeground(Color.BLACK);
        btnCancelar = new JButton("Cancelar");
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal); // Añadir panel principal al JFrame
    }

    /**
     * Llena los campos y muestra la imagen actual. Llamar DESPUÉS de poblarComboBoxes.
     */
    public void setDatosVehiculo(VehiculoDetalle v) {
        if (v != null) {
            System.out.println("ModVehiculoView: setDatosVehiculo llamado para ID: " + v.getIdVehiculo());
            lblVehiculoId.setText(v.getIdVehiculo());
            txtMarcaModelo.setText(v.getMarcaModelo());
            txtPrecioDia.setText(String.valueOf(v.getPrecioDia()));
            txtAnio.setText(String.valueOf(v.getAnio()));
            txtDescripcion.setText(v.getDescripcion() != null ? v.getDescripcion() : "");
            txtImagenUrl.setText(v.getImagenUrl() != null ? v.getImagenUrl() : ""); // Campo editable URL

            // Preseleccionar Combos
            String sucursalActual = v.getSucursal();
            String categoriaActual = v.getCategoriaNombre();
            if (sucursalActual != null) {
                cmbSucursal.setSelectedItem(sucursalActual);
                 if (!sucursalActual.equals(cmbSucursal.getSelectedItem())) {
                     System.err.println("Advertencia: No se pudo preseleccionar sucursal '" + sucursalActual + "'");
                 }
            } else { cmbSucursal.setSelectedIndex(0); }
            if (categoriaActual != null) {
                 cmbCategoria.setSelectedItem(categoriaActual);
                 if (!categoriaActual.equals(cmbCategoria.getSelectedItem())) {
                     System.err.println("Advertencia: No se pudo preseleccionar categoría '" + categoriaActual + "'");
                 }
            } else { cmbCategoria.setSelectedIndex(0); }

            // --- Cargar y mostrar imagen actual ---
            String rutaImagen = v.getImagenUrl(); // Obtener la URL del objeto
            ImageIcon icono = null;
            if (rutaImagen != null && !rutaImagen.trim().isEmpty()) {
                // Ruta relativa desde la raíz del classpath (src/)
                String rutaRecurso = "/" + rutaImagen.trim().replace("\\", "/"); // Asegurar barras /
                 System.out.println("ModVehiculoView: Intentando cargar imagen actual desde classpath: " + rutaRecurso);
                URL urlImagen = getClass().getResource(rutaRecurso);
                if (urlImagen != null) {
                    icono = new ImageIcon(urlImagen);
                    // Escalar imagen si es necesario
                    if (icono.getIconWidth() > 0 && icono.getIconHeight() > 0) {
                        int anchoMax = panelImagen.getPreferredSize().width - 10;  // Margen
                        int altoMax = panelImagen.getPreferredSize().height - 10; // Margen
                        Image img = icono.getImage();
                        double ratioAncho = (double) anchoMax / icono.getIconWidth();
                        double ratioAlto = (double) altoMax / icono.getIconHeight();
                        double ratio = Math.min(ratioAncho, ratioAlto); // Escalar manteniendo proporción

                        if (ratio < 1.0) { // Solo achicar, no agrandar
                            int nuevoAncho = (int) (icono.getIconWidth() * ratio);
                            int nuevoAlto = (int) (icono.getIconHeight() * ratio);
                            // Usar SCALE_SMOOTH para mejor calidad al escalar
                            icono = new ImageIcon(img.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH));
                            System.out.println("ModVehiculoView: Imagen actual escalada a " + nuevoAncho + "x" + nuevoAlto);
                        } else {
                             System.out.println("ModVehiculoView: Imagen actual no requiere escalado.");
                        }
                    } else {
                        System.err.println("Error: ImageIcon cargado pero con tamaño inválido para " + rutaRecurso);
                        icono = null; // Tratar como si no se hubiera cargado
                    }
                } else {
                     System.err.println("Error: No se encontró el recurso de imagen en classpath: " + rutaRecurso);
                     System.err.println("Verifica que la ruta '" + rutaImagen + "' sea correcta (relativa a 'src') y que el archivo exista en 'src/Imagenes'.");
                }
            } else {
                System.out.println("ModVehiculoView: No hay URL de imagen actual especificada para este vehículo.");
            }
            // Mostrar icono o texto placeholder
            lblImagenActual.setIcon(icono);
            if (icono == null) {
                lblImagenActual.setText("<html><center>Sin Imagen<br>Actual</center></html>");
            } else {
                lblImagenActual.setText(null); // Quitar texto si hay imagen
            }
            // Forzar redibujo del panel de imagen
            panelImagen.revalidate();
            panelImagen.repaint();

        } else {
             mostrarError("Error crítico: No se recibieron datos del vehículo para modificar.");
             // Deshabilitar campos y botones para prevenir errores
             txtMarcaModelo.setEnabled(false);
             txtPrecioDia.setEnabled(false);
             txtAnio.setEnabled(false);
             cmbSucursal.setEnabled(false);
             cmbCategoria.setEnabled(false);
             txtDescripcion.setEnabled(false);
             txtImagenUrl.setEnabled(false);
             btnGuardar.setEnabled(false);
        }
    }

     /**
     * Llena los ComboBoxes de sucursales y categorías.
     */
    public void poblarComboBoxes(TreeSet<String> sucursales, TreeSet<String> categorias) {
        System.out.println("ModVehiculoView: poblarComboBoxes iniciado...");
        // Limpiar combos
        cmbSucursal.removeAllItems();
        cmbCategoria.removeAllItems();
        System.out.println("ModVehiculoView: ComboBoxes limpiados.");

        // Poblar Sucursal
        cmbSucursal.addItem("<Seleccione Sucursal>");
        if (sucursales != null && !sucursales.isEmpty()) {
            for (String s : sucursales) cmbSucursal.addItem(s);
            System.out.println("ModVehiculoView: Añadidas " + sucursales.size() + " sucursales.");
        } else {
             System.err.println("Advertencia: No hay sucursales para añadir.");
             // Podrías deshabilitar el combo si está vacío: cmbSucursal.setEnabled(false);
        }

        // Poblar Categoría
        cmbCategoria.addItem("<Seleccione Categoría>");
        if (categorias != null && !categorias.isEmpty()) {
            for (String c : categorias) cmbCategoria.addItem(c);
            System.out.println("ModVehiculoView: Añadidas " + categorias.size() + " categorías.");
        } else {
             System.err.println("Advertencia: No hay categorías para añadir.");
             // cmbCategoria.setEnabled(false);
        }
         System.out.println("ModVehiculoView: poblarComboBoxes finalizado.");
    }

    // --- Getters (sin cambios) ---
    public String getVehiculoId() { return lblVehiculoId.getText(); }
    public String getMarcaModelo() { return txtMarcaModelo.getText().trim(); }
    public String getPrecioDiaStr() { return txtPrecioDia.getText().trim(); }
    public String getAnioStr() { return txtAnio.getText().trim(); }
    public String getDescripcion() { return txtDescripcion.getText().trim(); }
    public String getImagenUrl() { return txtImagenUrl.getText().trim(); }
    public String getSelectedSucursal() {
        Object item = cmbSucursal.getSelectedItem();
        return (item != null && !item.toString().equals("<Seleccione Sucursal>")) ? item.toString() : null;
     }
    public String getSelectedCategoria() {
        Object item = cmbCategoria.getSelectedItem();
        return (item != null && !item.toString().equals("<Seleccione Categoría>")) ? item.toString() : null;
    }
    public JButton getBtnGuardar() { return btnGuardar; }
    public JButton getBtnCancelar() { return btnCancelar; }

    // --- Métodos de mensajes y cierre (sin cambios) ---
    public void mostrarError(String mensaje) { JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.WARNING_MESSAGE); }
    public void mostrarExito(String mensaje) { JOptionPane.showMessageDialog(this, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE); }
    public void cerrar() { this.dispose(); }
}