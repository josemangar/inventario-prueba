package servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import logica.Producto;
import logica.ProductoDAO;

@WebServlet("/inventario")
public class InventarioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<Producto> lista = ProductoDAO.obtenerTodos();
        req.setAttribute("productos", lista);
        req.getRequestDispatcher("inventario.jsp").forward(req, res);
    }
}
