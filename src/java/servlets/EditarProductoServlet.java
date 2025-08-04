package servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import logica.Producto;
import logica.ProductoDAO;

@WebServlet("/editarProducto")
public class EditarProductoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String codigo = req.getParameter("codigo");
        String nombre = req.getParameter("nombre");
        int cantidad = Integer.parseInt(req.getParameter("cantidad"));
        double precio = Double.parseDouble(req.getParameter("precio"));
        String categoria = req.getParameter("categoria");
        String marca = req.getParameter("marca");
        String fecha = req.getParameter("fecha");

        ProductoDAO.actualizarProducto(codigo, nombre, cantidad, precio, categoria, marca, fecha);

        List<Producto> lista = ProductoDAO.obtenerTodos();
        req.setAttribute("productos", lista);
        req.getRequestDispatcher("inicio.jsp").forward(req, res);
    }
}
