package servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import logica.Producto;
import logica.ProductoDAO;

@WebServlet("/eliminarProducto")
public class EliminarProductoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String codigo = req.getParameter("codigo");

        ProductoDAO.eliminarProducto(codigo);

        List<Producto> lista = ProductoDAO.obtenerTodos();
        req.setAttribute("productos", lista);
        req.getRequestDispatcher("inicio.jsp").forward(req, res);
    }
}
