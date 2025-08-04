package servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import logica.Usuario;
import logica.ConexionBD;

@WebServlet("/login") // <-- Esta línea reemplaza web.xml
public class LoginServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        ConexionBD.crearTablaSiNoExiste(); // Crea tabla Derby si no existe
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String nombre = req.getParameter("usuario");
        String pass = req.getParameter("clave");

        Usuario u;
        if(nombre.equals("admin") && pass.equals("123")) {
            u = new Usuario(nombre, pass, "admin");
        } else if(nombre.equals("empleado") && pass.equals("123")) {
            u = new Usuario(nombre, pass, "empleado");
        } else {
            res.sendRedirect("error.jsp");
            return;
        }

        HttpSession sesion = req.getSession();
        sesion.setAttribute("usuario", u);
        res.sendRedirect("inicio.jsp");

    }
}
