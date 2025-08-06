<%@ page import="logica.Usuario" %>
<%@ page session="true" %>
<%
Usuario u = (Usuario) session.getAttribute("usuario");
if (u == null) {
    response.sendRedirect("login.jsp");
    return;
}
String rol = u.getRol();
%>
<!DOCTYPE html>



<html>
<head>
    <title>Menu principal</title>
    <style>
        .opcion {
            margin-bottom: 10px;
        }
        .desactivado {
            color: gray;
            text-decoration: none;
            pointer-events: none;
            cursor: default;
        }
    </style>
</head>



<body>
    <h2>Bienvenido, <%= u.getNombre() %>!</h2>
    <p>Rol: <%= rol %></p>

    <h3>Opciones del sistema</h3>

    <div class="opcion"><a href="inventario">Ver inventario</a></div>

    <div class="opcion">
        <% if ("admin".equals(rol)) { %>
            <a href="#">Agregar producto</a>
        <% } else { %>
            <a class="desactivado">Agregar producto</a>
        <% } %>
    </div>
    

    <div class="opcion">
        <% if ("admin".equals(rol)) { %>
            <a href="#">Editar producto</a>
        <% } else { %>
            <a class="desactivado">Editar producto</a>
        <% } %>
    </div>
    

    <div class="opcion">
        <% if ("admin".equals(rol)) { %>
            <a href="#">Eliminar producto</a>
        <% } else { %>
            <a class="desactivado">Eliminar producto</a>
        <% } %>
    </div>
    

    <div class="opcion">
        <% if ("empleado".equals(rol)) { %>
            <a href="#">Registrar entrada/salida</a>
        <% } else { %>
            <a class="desactivado">Registrar entrada/salida</a>
        <% } %>
    </div>
    

    <div class="opcion">
        <% if ("admin".equals(rol)) { %>
            <a href="#">Ver reportes</a>
        <% } else { %>
            <a class="desactivado">Ver reportes</a>
        <% } %>
    </div>

    <br>
    <a href="login.jsp">Cerrar sesion</a>
</body>
</html>
