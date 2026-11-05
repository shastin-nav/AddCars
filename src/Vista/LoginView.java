package Vista; // Updated package

// Updated imports
import Controlador.LoginController;
import java.awt.*;
import javax.swing.*;

/**
 * The View (JFrame) for the Login.
 */
public class LoginView extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnIniciar;
    private JLabel lblRecuperar;
    private JLabel lblLogo;
    private LoginController controller; // Reference to the controller

    public LoginView() { initComponents(); }

    public void setController(LoginController controller) {
        this.controller = controller;
        this.btnIniciar.addActionListener(controller); // Set controller as listener
    }

    private void initComponents() {
        setTitle("AddCars - Iniciar Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 450); setLocationRelativeTo(null); setResizable(false);
        JPanel panel = new JPanel(); panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE); panel.setBorder(BorderFactory.createEmptyBorder(60, 80, 60, 80));
        lblLogo = new JLabel("AddCars", SwingConstants.CENTER); lblLogo.setFont(new Font("SansSerif", Font.BOLD, 26));
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT); lblLogo.setForeground(Color.BLACK);
        panel.add(lblLogo); panel.add(Box.createRigidArea(new Dimension(0, 40)));
        txtUsuario = new JTextField(); txtUsuario.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtUsuario.setBorder(BorderFactory.createTitledBorder("Nombre Usuario")); panel.add(txtUsuario);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        txtContrasena = new JPasswordField(); txtContrasena.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtContrasena.setBorder(BorderFactory.createTitledBorder("Contraseña")); panel.add(txtContrasena);
        panel.add(Box.createRigidArea(new Dimension(0, 25)));
        btnIniciar = new JButton("Iniciar Sesión"); btnIniciar.setBackground(Color.WHITE); btnIniciar.setForeground(Color.BLACK);
        btnIniciar.setFocusPainted(false); btnIniciar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnIniciar.setAlignmentX(Component.CENTER_ALIGNMENT); panel.add(btnIniciar);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        lblRecuperar = new JLabel("Recupera tu contraseña"); lblRecuperar.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblRecuperar.setForeground(Color.BLACK); lblRecuperar.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblRecuperar.setCursor(new Cursor(Cursor.HAND_CURSOR)); panel.add(lblRecuperar);
        add(panel);
    }

    // --- Getters for the Controller ---
    public String getUsuario() { return txtUsuario.getText(); }
    public String getContrasena() { return new String(txtContrasena.getPassword()); }
    public JButton getBtnIniciar() { return btnIniciar; }

    // --- Methods the Controller can call ---
    public void mostrarError(String mensaje) { JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE); }
    public void loginExitoso() { JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso."); this.dispose(); }
}