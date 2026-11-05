package Modelo;

import Conexion.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import javax.swing.JOptionPane;

public class VehiculoDAO {

    public List<Vehiculo> buscarVehiculos(String anio, String categoria, String precio, String sucursal) {
        List<Vehiculo> vehiculos = new ArrayList<>();
        //base de la consulta con joins principales
        String baseSql = "SELECT v.id_vehiculo, m.nombre AS marca_nombre, mo.nombre_modelo, v.precio_base_dia, s.nombre_sucursal " +
                         "FROM vehiculos v " +
                         "JOIN modelos mo ON v.id_modelo = mo.id_modelo " +
                         "JOIN marcas m ON mo.id_marca = m.id_marca " +
                         "JOIN sucursales s ON v.id_sucursal = s.id_sucursal ";
        StringBuilder sqlWhere = new StringBuilder(" WHERE 1=1 ");
        List<Object> params = new ArrayList<>();

        try {
            //filtro por año
            if (anio != null && !anio.equals("Año")) {
                sqlWhere.append(" AND v.anio = ? ");
                params.add(Integer.parseInt(anio));
            }
            //filtro por categoría
            if (categoria != null && !categoria.equals("Categoría")) {
                sqlWhere.append(" AND v.id_categoria = (SELECT id_categoria FROM cat_vehiculos WHERE nombre_categoria = ?) ");
                params.add(categoria);
            }
            //filtro por sucursal
            if (sucursal != null && !sucursal.equals("Sucursal")) {
                sqlWhere.append(" AND v.id_sucursal = (SELECT id_sucursal FROM sucursales WHERE nombre_sucursal = ?) ");
                params.add(sucursal);
            }
            //rango de precio
            if (precio != null && !precio.equals("Rango Precio")) {
                if (precio.equals("$20.000 - $40.000")) {
                    sqlWhere.append(" AND v.precio_base_dia BETWEEN 20000 AND 40000 ");
                } else if (precio.equals("$40.001 - $70.000")) {
                    sqlWhere.append(" AND v.precio_base_dia BETWEEN 40001 AND 70000 ");
                } else if (precio.equals("$70.001+")) {
                    sqlWhere.append(" AND v.precio_base_dia >= 70001 ");
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("Error al parsear el año: " + anio);
            e.printStackTrace();
            return vehiculos;
        }

        String sqlCompleto = baseSql + sqlWhere.toString() + " ORDER BY v.precio_base_dia ASC";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlCompleto)) {

            if (conn == null) {
                System.err.println("No se pudo obtener conexión a la base de datos en buscarVehiculos.");
                return vehiculos;
            }

            //asigna parámetros dinámicos
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                //construye objetos vehiculo con los resultados
                while (rs.next()) {
                    Vehiculo vehiculo = new Vehiculo(
                        rs.getString("id_vehiculo"),
                        rs.getString("marca_nombre") + " " + rs.getString("nombre_modelo"),
                        rs.getInt("precio_base_dia"),
                        rs.getString("nombre_sucursal")
                    );
                    vehiculos.add(vehiculo);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error SQL al buscar vehículos: " + e.getMessage());
            e.printStackTrace();
        }
        return vehiculos;
    }

    public VehiculoDetalle getVehiculoDetalle(String idVehiculo) {
        VehiculoDetalle detalle = null;
        //consulta para obtener toda la información del vehículo
        String sql = "SELECT " +
                     "v.id_vehiculo, m.nombre AS marca_nombre, mo.nombre_modelo, v.precio_base_dia, s.nombre_sucursal, " +
                     "v.descripcion, v.imagen_url, v.anio, c.nombre_categoria, " +
                     "v.id_categoria, v.id_modelo, v.id_sucursal " +
                     "FROM vehiculos v " +
                     "JOIN modelos mo ON v.id_modelo = mo.id_modelo " +
                     "JOIN marcas m ON mo.id_marca = m.id_marca " +
                     "JOIN sucursales s ON v.id_sucursal = s.id_sucursal " +
                     "JOIN cat_vehiculos c ON v.id_categoria = c.id_categoria " +
                     "WHERE v.id_vehiculo = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (conn == null) {
                 System.err.println("No se pudo obtener conexión a la base de datos en getVehiculoDetalle.");
                 return null;
            }

            ps.setString(1, idVehiculo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    //crea el objeto detalle con todos los campos
                    detalle = new VehiculoDetalle(
                        rs.getString("id_vehiculo"),
                        rs.getString("marca_nombre") + " " + rs.getString("nombre_modelo"),
                        rs.getInt("precio_base_dia"),
                        rs.getString("nombre_sucursal"),
                        rs.getString("descripcion"),
                        rs.getString("imagen_url"),
                        rs.getInt("anio"),
                        rs.getString("id_categoria"),
                        rs.getString("id_modelo"),
                        rs.getString("id_sucursal")
                    );
                    detalle.setCategoriaNombre(rs.getString("nombre_categoria"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error SQL al obtener detalle del vehículo con ID " + idVehiculo + ": " + e.getMessage());
            e.printStackTrace();
        }
        return detalle;
    }

    public Seguro getSeguro(String idVehiculo) {
        String sql = "SELECT compania, descripcion, cobertura, fecha_inicio, fecha_fin, costo_anual FROM seguros WHERE id_vehiculo = ?";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            if (conn == null) {
                System.err.println("No se pudo obtener conexión a la base de datos en getSeguro.");
                return null;
            }
            ps.setString(1, idVehiculo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    //devuelve un objeto seguro con la info encontrada
                    return new Seguro(
                        rs.getString("compania"),
                        rs.getString("descripcion"),
                        rs.getString("cobertura"),
                        rs.getDate("fecha_inicio"),
                        rs.getDate("fecha_fin"),
                        rs.getInt("costo_anual")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error SQL al obtener seguro para vehículo ID " + idVehiculo + ": " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    //obtiene los últimos mantenimientos del vehículo
    public List<Mantenimiento> getMantenimientos(String idVehiculo) {
        List<Mantenimiento> lista = new ArrayList<>();
        String sql = "SELECT fecha, tipo, detalle, costo FROM mantenimientos WHERE id_vehiculo = ? ORDER BY fecha DESC LIMIT 5";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            if (conn == null) {
                 System.err.println("error conexion getMantenimientos");
                 return lista;
            }
            ps.setString(1, idVehiculo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Mantenimiento(
                        rs.getDate("fecha"),
                        rs.getString("tipo"),
                        rs.getString("detalle"),
                        rs.getInt("costo")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("error sql getMantenimientos: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    //obtiene los años distintos de los vehículos registrados
    public TreeSet<String> getAniosDisponibles() {
        TreeSet<String> lista = new TreeSet<>();
        String sql = "SELECT DISTINCT anio FROM vehiculos ORDER BY anio DESC";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
             if (conn == null) {
                 System.err.println("error conexion getAniosDisponibles");
                 return lista;
             }
            while (rs.next()) {
                lista.add(String.valueOf(rs.getInt("anio")));
            }
        } catch (SQLException e) {
             System.err.println("error sql getAniosDisponibles: " + e.getMessage());
             e.printStackTrace();
        }
        return lista;
    }

    //obtiene todas las categorías de vehículos disponibles
    public TreeSet<String> getCategoriasDisponibles() {
        TreeSet<String> lista = new TreeSet<>();
        String sql = "SELECT nombre_categoria FROM cat_vehiculos ORDER BY nombre_categoria";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
             if (conn == null) {
                 System.err.println("error conexion getCategoriasDisponibles");
                 return lista;
             }
            while (rs.next()) {
                lista.add(rs.getString("nombre_categoria"));
            }
        } catch (SQLException e) {
            System.err.println("error sql getCategoriasDisponibles: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

   

     //obtiene todas las sucursales disponibles
    public TreeSet<String> getSucursalesDisponibles() {
        TreeSet<String> lista = new TreeSet<>();
        String sql = "SELECT nombre_sucursal FROM sucursales ORDER BY nombre_sucursal";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
             if (conn == null) {
                 System.err.println("error conexion getSucursalesDisponibles");
                 return lista;
             }
            while (rs.next()) {
                lista.add(rs.getString("nombre_sucursal"));
            }
        } catch (SQLException e) {
            System.err.println("error sql getSucursalesDisponibles: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    //devuelve nombres de categorías existentes
    public List<String> getNombresCategorias() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT nombre_categoria FROM cat_vehiculos ORDER BY nombre_categoria";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
             if (conn == null) return lista;
             while (rs.next()) { lista.add(rs.getString("nombre_categoria")); }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    //devuelve nombres de sucursales registradas
    public List<String> getNombresSucursales() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT nombre_sucursal FROM sucursales ORDER BY nombre_sucursal";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
             if (conn == null) return lista;
             while (rs.next()) { lista.add(rs.getString("nombre_sucursal")); }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    //obtiene id de sucursal desde su nombre
    public String obtenerIdSucursalPorNombre(String nombreSucursal) {
        if (nombreSucursal == null || nombreSucursal.trim().isEmpty() || nombreSucursal.equals("<Seleccione Sucursal>")) {
             System.err.println("error: nombre de sucursal inválido o no seleccionado");
             return null;
        }
        String sql = "SELECT id_sucursal FROM sucursales WHERE nombre_sucursal = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             if (conn == null) {
                 System.err.println("error conexion obtenerIdSucursalPorNombre");
                 return null;
             }
             ps.setString(1, nombreSucursal.trim());
             try (ResultSet rs = ps.executeQuery()) {
                 if (rs.next()) {
                     return rs.getString("id_sucursal");
                 } else {
                     System.err.println("no se encontró id para sucursal: '" + nombreSucursal + "'");
                 }
             }
        } catch (SQLException e) {
            System.err.println("error sql obtenerIdSucursalPorNombre: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    //obtiene id de categoría desde su nombre
    public String obtenerIdCategoriaPorNombre(String nombreCategoria) {
        if (nombreCategoria == null || nombreCategoria.trim().isEmpty() || nombreCategoria.equals("<Seleccione Categoría>")) {
             System.err.println("error: nombre de categoría inválido o no seleccionado");
             return null;
        }
        String sql = "SELECT id_categoria FROM cat_vehiculos WHERE nombre_categoria = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             if (conn == null) {
                 System.err.println("error conexion obtenerIdCategoriaPorNombre");
                 return null;
             }
             ps.setString(1, nombreCategoria.trim());
             try (ResultSet rs = ps.executeQuery()) {
                 if (rs.next()) {
                     return rs.getString("id_categoria");
                 } else {
                     System.err.println("no se encontró id para categoría: '" + nombreCategoria + "'");
                 }
             }
        } catch (SQLException e) {
            System.err.println("error sql obtenerIdCategoriaPorNombre: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    //obtiene id de modelo a partir del texto "Marca Modelo"
    public String obtenerIdModeloDesdeNombreCompleto(String marcaModelo) {
        if (marcaModelo == null || marcaModelo.trim().isEmpty()) {
             System.err.println("error: nombre marca/modelo vacío");
             return null;
        }

        marcaModelo = marcaModelo.trim();
        int primerEspacio = marcaModelo.indexOf(" ");
        if (primerEspacio <= 0 || primerEspacio >= marcaModelo.length() - 1) {
            System.err.println("error formato marca/modelo '" + marcaModelo + "'");
            return null;
        }

        String marcaNombre = marcaModelo.substring(0, primerEspacio).trim();
        String modeloNombre = marcaModelo.substring(primerEspacio + 1).trim();

        if (marcaNombre.isEmpty() || modeloNombre.isEmpty()){
             System.err.println("error: marca o modelo vacío tras separar");
             return null;
        }

        String sql = "SELECT mo.id_modelo FROM modelos mo JOIN marcas ma ON mo.id_marca = ma.id_marca WHERE ma.nombre = ? AND mo.nombre_modelo = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             if (conn == null) {
                 System.err.println("error conexion obtenerIdModeloDesdeNombreCompleto");
                 return null;
             }
             ps.setString(1, marcaNombre);
             ps.setString(2, modeloNombre);
             try (ResultSet rs = ps.executeQuery()) {
                 if (rs.next()) {
                     return rs.getString("id_modelo");
                 } else {
                     System.err.println("no se encontró id modelo para '" + marcaModelo + "'");
                 }
             }
        } catch (SQLException e) {
            System.err.println("error sql obtenerIdModeloDesdeNombreCompleto: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    //actualiza los datos de un vehículo existente
    public boolean actualizarVehiculo(VehiculoDetalle v) {
        if (v.getIdModelo() == null || v.getIdSucursal() == null || v.getIdCategoria() == null || v.getIdVehiculo() == null) {
             System.err.println("error crítico: faltan ids requeridos");
             return false;
        }

        String sql = "UPDATE vehiculos SET " +
                     "id_modelo = ?, " +
                     "precio_base_dia = ?, " +
                     "id_sucursal = ?, " +
                     "descripcion = ?, " +
                     "imagen_url = ?, " +
                     "anio = ?, " +
                     "id_categoria = ? " +
                     "WHERE id_vehiculo = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (conn == null) {
                 System.err.println("error conexion actualizarVehiculo");
                 return false;
            }

            ps.setString(1, v.getIdModelo());
            ps.setInt(2, v.getPrecioDia());
            ps.setString(3, v.getIdSucursal());
            ps.setString(4, v.getDescripcion());

            if (v.getImagenUrl() == null || v.getImagenUrl().trim().isEmpty()) {
                 ps.setNull(5, Types.VARCHAR);
            } else {
                 ps.setString(5, v.getImagenUrl().trim());
            }
            ps.setInt(6, v.getAnio());
            ps.setString(7, v.getIdCategoria());
            ps.setString(8, v.getIdVehiculo());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 1) {
                 System.out.println("Vehículo con ID " + v.getIdVehiculo() + " actualizado correctamente.");
                 return true;
            } else if (filasAfectadas == 0) {
                 System.err.println("Advertencia: No se encontró ningún vehículo con ID " + v.getIdVehiculo() + " para actualizar. No se realizaron cambios.");
                 return false;
            } else {
                 System.err.println("Advertencia Crítica: Se actualizaron " + filasAfectadas + " filas para el vehículo ID " + v.getIdVehiculo() + ". Se esperaba 1. Revisar Primary Key.");
                 return true;
            }

        } catch (SQLException e) {
            System.err.println("Error SQL al actualizar vehículo con ID " + v.getIdVehiculo() + ": " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public Contrato getContratoPorVehiculoId(String idVehiculo) {
        String sql = "SELECT c.* FROM contratos c " +
                     "JOIN vehiculos v ON c.patente = REPLACE(v.patente, '-', '') " +
                     "WHERE v.id_vehiculo = ? LIMIT 1";
        
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             ps.setString(1, idVehiculo);
             try (ResultSet rs = ps.executeQuery()) {
                 if (rs.next()) {
                     return new Contrato(
                         rs.getString("id_contrato"),
                         rs.getDate("fecha_inicio"),
                         rs.getDate("fecha_fin"),
                         rs.getString("condiciones"),
                         rs.getString("estado_contrato"),
                         rs.getString("dni"),
                         rs.getString("telefono"),
                         rs.getString("patente"),
                         rs.getString("id_rol"),
                         rs.getString("id_modelo")
                     );
                 }
             }
        } catch (SQLException e) {
            System.err.println("Error SQL al obtener contrato por ID de vehículo: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Contrato> getContratos(String filtroEstado) {
        List<Contrato> contratos = new ArrayList<>();
        String sql = "SELECT id_contrato, fecha_inicio, fecha_fin, condiciones, estado_contrato, dni, telefono, patente, id_rol, id_modelo FROM contratos";
        if (filtroEstado.equals("Activo") || filtroEstado.equals("Finalizado")) {
            sql += " WHERE estado_contrato = ?";
        }

        try (Connection conn = ConexionBD.getConnection(); //
             PreparedStatement ps = conn.prepareStatement(sql)) {

            //asignar el parámetro del filtro solo si es necesario
            if (!filtroEstado.equals("Todos")) {
                ps.setString(1, filtroEstado);
            }
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //crear el objeto Contrato usando el constructor existente
                Contrato c = new Contrato(
                    rs.getString("id_contrato"),
                    rs.getDate("fecha_inicio"),
                    rs.getDate("fecha_fin"),
                    rs.getString("condiciones"),
                    rs.getString("estado_contrato"),
                    rs.getString("dni"),
                    rs.getString("telefono"),
                    rs.getString("patente"),
                    rs.getString("id_rol"),
                    rs.getString("id_modelo")
                ); //
                contratos.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al obtener contratos de la BD: " + e.getMessage());
        }
        return contratos;
    }
    public boolean updateContractStatus(String idContrato, String nuevoEstado) {
        String sql = "UPDATE contratos SET estado_contrato = ? WHERE id_contrato = ?";
        try (Connection conn = ConexionBD.getConnection(); //
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, nuevoEstado);
            ps.setString(2, idContrato);
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Retorna true si se actualizó al menos 1 fila
        
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar estado del contrato: " + e.getMessage());
            return false;
        }
    }
}