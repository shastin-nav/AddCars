package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane; //para mostrar mensajes

public class ConexionBD {
    public static final String URL = "jdbc:mysql://localhost:3306/addcars"; //url bdd
    public static final String USER = "root"; //usuario mysql
    public static final String PASSWORD = ""; //contraseña mysql

    public static Connection getConnection() {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //carga driver mysql
            conexion = DriverManager.getConnection(URL, USER, PASSWORD); //establece conexion
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                "Error Crítico: No se encontró el driver de MySQL (mysql-connector-j-X.X.jar).\n" +
                "Asegúrate de que la librería (.jar) esté en el proyecto.\nLa aplicación se cerrará.",
                "Error de Driver", JOptionPane.ERROR_MESSAGE); //error driver
            System.exit(1); //cierra app
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null,
                "Error al conectar con la base de datos: " + e.getMessage() +
                "\nVerifique que XAMPP (o su servidor MySQL) esté encendido y la base de datos 'addcars' exista.",
                "Error de Conexión", JOptionPane.ERROR_MESSAGE); //error conexion
        }
        return conexion; //retorna conexion o null
    }

    public static void main(String[] args) {
        Connection conn = getConnection(); //prueba conexion
        if (conn != null) {
            System.out.println("¡Conexión de prueba exitosa a MySQL!"); //conexion ok
            try {
                conn.close(); //cierra conexion
                System.out.println("Conexión de prueba cerrada."); //cerrada ok
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexión de prueba: " + e.getMessage()); //error cierre
            }
        } else {
            System.out.println("Fallo en la conexión de prueba."); //no conecta
        }
    }
}
