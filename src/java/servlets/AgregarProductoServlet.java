package servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import logica.Producto;
import logica.ProductoDAO;
import java.util.List;

@WebServlet("/agregarProducto")
public class AgregarProductoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String codigo = req.getParameter("codigo");
        String nombre = req.getParameter("nombre");
        int cantidad = Integer.parseInt(req.getParameter("cantidad"));
        double precio = Double.parseDouble(req.getParameter("precio"));
        String categoria = req.getParameter("categoria");
        String marca = req.getParameter("marca");
        String fecha = req.getParameter("fecha");

        Producto p = new Producto(codigo, nombre, cantidad, precio, categoria, marca, fecha);
        ProductoDAO.insertarProducto(p);

        List<Producto> lista = ProductoDAO.obtenerTodos();
        req.setAttribute("productos", lista);
        req.getRequestDispatcher("inicio.jsp").forward(req, res);
    }
}
