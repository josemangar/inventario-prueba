package servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import logica.Producto;
import logica.ProductoDAO;

@WebServlet("/movimiento")
public class MovimientoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String codigo = req.getParameter("codigo");
        int cantidad = Integer.parseInt(req.getParameter("cantidad"));
        String tipo = req.getParameter("tipo");

        if (tipo.equals("entrada")) {
            ProductoDAO.aumentarStock(codigo, cantidad);
        } else if (tipo.equals("salida")) {
            ProductoDAO.disminuirStock(codigo, cantidad);
        }

        ProductoDAO.registrarMovimiento(codigo, tipo, cantidad);

        List<Producto> lista = ProductoDAO.obtenerTodos();
        req.setAttribute("productos", lista);
        req.getRequestDispatcher("inicio.jsp").forward(req, res);
    }
}
