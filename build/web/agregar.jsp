<%@ page session="true" %>
<%@ page import="logica.Usuario" %>
<%
Usuario u = (Usuario) session.getAttribute("usuario");
if (u == null) {
    response.sendRedirect("login.jsp");
    return;
}
%>
<!DOCTYPE html>
<html>
<head><title>Agregar producto</title></head>
<body>
    <h2>Nuevo producto</h2>
    <form method="post" action="agregarProducto">
        Código: <input type="text" name="codigo"><br>
        Nombre: <input type="text" name="nombre"><br>
        Cantidad: <input type="number" name="cantidad"><br>
        Precio: <input type="number" step="0.01" name="precio"><br>
        Categoría: <input type="text" name="categoria"><br>
        Marca: <input type="text" name="marca"><br>
        Fecha de reposición: <input type="date" name="fecha"><br>
        <input type="submit" value="Guardar">
    </form>
    <br><a href="inicio.jsp">Volver</a>
</body>
</html>
