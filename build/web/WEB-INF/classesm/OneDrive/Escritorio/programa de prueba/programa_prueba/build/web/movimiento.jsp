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
<head><title>Movimiento</title></head>
<body>
    <h2>Registrar entrada/salida</h2>
    <form method="post" action="movimiento">
        Codigo del producto: <input type="text" name="codigo"><br>
        Cantidad: <input type="number" name="cantidad"><br>
        Tipo:
        <select name="tipo">
            <option value="entrada">Entrada</option>
            <option value="salida">Salida</option>
        </select><br>
        <input type="submit" value="Actualizar">
    </form>
    <br><a href="inicio.jsp">Volver</a>
    
    
    
</body>
</html>
