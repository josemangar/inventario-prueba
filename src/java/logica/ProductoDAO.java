package logica;

import java.sql.*;
import java.util.*;

public class ProductoDAO {

    public static List<Producto> obtenerTodos() {
        List<Producto> lista = new ArrayList<>();
        try (Connection con = ConexionBD.obtenerConexion();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM productos")) {

            while (rs.next()) {
                Producto p = new Producto(
                    rs.getString("codigo"),
                    rs.getString("nombre"),
                    rs.getInt("cantidad"),
                    rs.getDouble("precio"),
                    rs.getString("categoria"),
                    rs.getString("marca"),
                    rs.getString("fechaReposicion")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public static void insertarProducto(Producto p) {
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(
                 "INSERT INTO productos VALUES (?, ?, ?, ?, ?, ?, ?)")) {

            stmt.setString(1, p.getCodigo());
            stmt.setString(2, p.getNombre());
            stmt.setInt(3, p.getCantidad());
            stmt.setDouble(4, p.getPrecio());
            stmt.setString(5, p.getCategoria());
            stmt.setString(6, p.getMarca());
            stmt.setString(7, p.getFechaReposicion());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void aumentarStock(String codigo, int cantidad) {
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(
                 "UPDATE productos SET cantidad = cantidad + ? WHERE codigo = ?")) {

            stmt.setInt(1, cantidad);
            stmt.setString(2, codigo);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void disminuirStock(String codigo, int cantidad) {
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(
                 "UPDATE productos SET cantidad = cantidad - ? WHERE codigo = ?")) {

            stmt.setInt(1, cantidad);
            stmt.setString(2, codigo);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void actualizarProducto(String codigo, String nombre, int cantidad, double precio,
                                          String categoria, String marca, String fecha) {
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(
                 "UPDATE productos SET nombre=?, cantidad=?, precio=?, categoria=?, marca=?, fechaReposicion=? WHERE codigo=?")) {

            stmt.setString(1, nombre);
            stmt.setInt(2, cantidad);
            stmt.setDouble(3, precio);
            stmt.setString(4, categoria);
            stmt.setString(5, marca);
            stmt.setString(6, fecha);
            stmt.setString(7, codigo);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void eliminarProducto(String codigo) {
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(
                 "DELETE FROM productos WHERE codigo = ?")) {

            stmt.setString(1, codigo);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Producto> obtenerBajoStock(int limite) {
        List<Producto> lista = new ArrayList<>();
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(
                 "SELECT * FROM productos WHERE cantidad <= ?")) {

            stmt.setInt(1, limite);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Producto p = new Producto(
                    rs.getString("codigo"),
                    rs.getString("nombre"),
                    rs.getInt("cantidad"),
                    rs.getDouble("precio"),
                    rs.getString("categoria"),
                    rs.getString("marca"),
                    rs.getString("fechaReposicion")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public static void registrarMovimiento(String codigo, String tipo, int cantidad) {
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(
                 "INSERT INTO movimientos (codigoProducto, tipo, cantidad, fecha) VALUES (?, ?, ?, CURRENT_TIMESTAMP)")) {

            stmt.setString(1, codigo);
            stmt.setString(2, tipo);
            stmt.setInt(3, cantidad);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String[]> obtenerHistorial() {
        List<String[]> lista = new ArrayList<>();
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(
                 "SELECT * FROM movimientos ORDER BY fecha DESC")) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String[] fila = new String[4];
                fila[0] = rs.getString("codigoProducto");
                fila[1] = rs.getString("tipo");
                fila[2] = String.valueOf(rs.getInt("cantidad"));
                fila[3] = rs.getTimestamp("fecha").toString();
                lista.add(fila);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
