package Controlador; // Updated package

import Modelo.Usuario;
import Modelo.UsuarioDAO;
import Modelo.VehiculoDAO; // Necesario para crear MenuController
import Vista.LoginView;
import Vista.MenuView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane; // Importar para mensajes

public class LoginController implements ActionListener {
    private final LoginView view;
    private final UsuarioDAO model;

    public LoginController(LoginView view, UsuarioDAO model) {
        this.view = view;
        this.model = model;
        this.view.setController(this); //conecta vista con controlador
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getBtnIniciar()) { //boton iniciar sesion
            String username = view.getUsuario();
            String password = view.getContrasena();

            if (username.isEmpty() || password.isEmpty()) {
                view.mostrarError("Debe ingresar usuario y contraseña."); //valida campos vacios
                return;
            }

            Usuario usuario = model.validarUsuario(username, password); //verifica credenciales

            if (usuario != null) {
                String rol = usuario.getIdRol(); //obtiene rol del usuario

                if ("ADM".equals(rol)) {
                    JOptionPane.showMessageDialog(view, "Has iniciado sesión como Administrador.", "Bienvenido Administrador", JOptionPane.INFORMATION_MESSAGE); //mensaje admin
                } else if ("R01".equals(rol)) {
                    JOptionPane.showMessageDialog(view, "Has iniciado sesión como Empleado.", "Bienvenido", JOptionPane.INFORMATION_MESSAGE); //mensaje empleado
                } else if ("R02".equals(rol)) {
                    view.mostrarError("Error: Los clientes no tienen permitido el acceso a esta aplicación."); //bloquea acceso cliente
                    return;
                }

                view.loginExitoso(); //cierra ventana login
                MenuView menuView = new MenuView(usuario.getIdRol()); //crea vista menu
                VehiculoDAO vehiculoModel = new VehiculoDAO(); //instancia dao vehiculos
                new MenuController(menuView, vehiculoModel); //crea controlador del menu
                menuView.setVisible(true); //muestra menu

            } else {
                view.mostrarError("Usuario o contraseña incorrectos."); //mensaje error login
            }
        }
    }
}
