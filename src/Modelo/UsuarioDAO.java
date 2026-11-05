package Modelo;

import Conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class UsuarioDAO implements DAO<Usuario> {

    public Usuario validarUsuario(String username, String password) {
        String sql = "SELECT id_usuario, id_rol, nom_usuario FROM usuarios WHERE nom_usuario = ? AND contrasenia = ?"; //consulta para verificar usuario
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (conn == null) {
                System.err.println("error conexion"); //conexion fallida
                return null;
            }

            ps.setString(1, username); //asigna usuario
            ps.setString(2, password); //asigna contrasena
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Usuario( //retorna usuario si existe
                        rs.getString("id_usuario"),
                        rs.getString("id_rol"),
                        rs.getString("nom_usuario")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("error sql: " + e.getMessage()); //error de consulta
        }
        return null; //no encontrado
    }

    @Override
    public Usuario getById(String id) {
        System.out.println("getById no implementado"); //pendiente
        return null;
    }

    @Override
    public List<Usuario> getAll() {
        System.out.println("getAll no implementado"); //pendiente
        return new ArrayList<>();
    }

    @Override
    public void save(Usuario t) {
        System.out.println("save no implementado"); //pendiente
    }

    @Override
    public void delete(Usuario t) {
        System.out.println("delete no implementado"); //pendiente
    }
}
