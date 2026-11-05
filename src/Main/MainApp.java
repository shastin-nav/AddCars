package Main; //paquete principal

import Controlador.LoginController;
import Modelo.UsuarioDAO;
import Vista.LoginView;
import javax.swing.UIManager;

public class MainApp {

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } //ajusta apariencia
        catch (Exception e) { e.printStackTrace(); }

        java.awt.EventQueue.invokeLater(() -> { mostrarLogin(); }); //lanza interfaz
    }

    public static void mostrarLogin() {
        LoginView view = new LoginView(); //vista
        UsuarioDAO model = new UsuarioDAO(); //modelo
        new LoginController(view, model); //controlador
        view.setVisible(true); //muestra ventana
    }
}
