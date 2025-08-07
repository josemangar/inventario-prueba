<%@ page import="java.util.*, logica.Producto" %>
<%@ page session="true" %>
<%
List<Producto> lista = (List<Producto>) request.getAttribute("productos");
%>
<!DOCTYPE html>





<html>
<head>
    <title>Inventario</title>
</head>
<body>
    <h2>Listado de Productos</h2>

    <table border="1">
        <tr>
            <th>Codigo</th><th>Nombre</th><th>Cantidad</th>
            <th>Precio</th><th>Categoria</th><th>Marca</th><th>Reposicion</th>
        </tr>
        <%
        if (lista != null) {
            for (Producto p : lista) {
        %>
        <tr>
            <td><%= p.getCodigo() %></td>
            <td><%= p.getNombre() %></td>
            <td><%= p.getCantidad() %></td>
            <td><%= p.getPrecio() %></td>
            <td><%= p.getCategoria() %></td>
            <td><%= p.getMarca() %></td>
            <td><%= p.getFechaReposicion() %></td>
        </tr>
        <%
            }
        } else {
        %>
        <tr><td colspan="7">No se tienen productos registrados.</td></tr>
        <%
        }
        %>
    </table>
</body>
</html>
