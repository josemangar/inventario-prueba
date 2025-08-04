package logica;

import java.sql.*;

public class ConexionBD {

    private static final String URL = "jdbc:derby://localhost:1527/inventario";
    private static final String USUARIO = "app"; // o tu usuario si lo cambiaste
    private static final String CONTRASENA = "app"; // o tu contraseña si la cambiaste

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }

    public static void crearTablaSiNoExiste() {
        try (Connection con = obtenerConexion();
             Statement stmt = con.createStatement()) {

            String sql = "CREATE TABLE productos (" +
                         "codigo VARCHAR(10) PRIMARY KEY, " +
                         "nombre VARCHAR(50), " +
                         "cantidad INT, " +
                         "precio DOUBLE, " +
                         "categoria VARCHAR(30), " +
                         "marca VARCHAR(30), " +
                         "fechaReposicion VARCHAR(20))";

            stmt.executeUpdate(sql);
            System.out.println("Tabla 'productos' creada con éxito.");

        } catch (SQLException e) {
            if ("X0Y32".equals(e.getSQLState())) {
                System.out.println("La tabla ya existe.");
            } else {
                e.printStackTrace();
            }
        }
    }
}
