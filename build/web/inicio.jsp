<%@ page import="logica.Usuario" %>
<%@ page import="java.util.*, logica.Producto" %>
<%@ page session="true" %>
<%
Usuario u = (Usuario) session.getAttribute("usuario");
if (u == null) {
    response.sendRedirect("login.jsp");
    return;
}
String rol = u.getRol();
List<Producto> lista = (List<Producto>) request.getAttribute("productos");
if (lista == null) {
    lista = logica.ProductoDAO.obtenerTodos();
}
%>



<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sistema de Inventario</title>
    <link rel="stylesheet" href="recursos/estilos.css">
    <style>
        .contenido h2 {
            margin-top: 0;
            color: #4B0000;
        }
        .formulario form {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 15px;
        }
        .formulario input[type="submit"] {
            grid-column: span 2;
            justify-self: start;
        }
        table {
            margin-top: 20px;
        }
        table tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        .contenido > table {
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
            border-radius: 6px;
            overflow: hidden;
        }
    </style>
    <script>
        function mostrar(id) {
            let forms = ["form_agregar", "form_mov", "form_editar", "form_eliminar", "form_reportes", "form_historial"];
            forms.forEach(f => document.getElementById(f).style.display = "none");
            if (id) document.getElementById(id).style.display = "block";
        }
    </script>
</head>
<body>
<div class="contenedor">
    <aside class="menu">
        <h3>Menú principal</h3>
        <button onclick="mostrar('form_agregar')"> Agregar producto</button>
        <button onclick="mostrar('form_mov')"> Entrada / Salida</button>
        <button onclick="mostrar('form_editar')">? Editar producto</button>
        <button onclick="mostrar('form_eliminar')"> Eliminar producto</button>
        <button onclick="mostrar('form_reportes')"> Ver reportes</button>
        <button onclick="mostrar('form_historial')"> Historial</button>
        <button onclick="mostrar('')">? Ocultar formularios</button>
        <br><a href="login.jsp">? Cerrar sesión</a>
    </aside>

    <main class="contenido">
        
        
        
        
        
        <!--Formulario para Agregar -->
        <div id="form_agregar" class="formulario">
            <h3>Agregar producto</h3>
            <form method="post" action="agregarProducto">
                <input type="text" name="codigo" placeholder="Código">
                <input type="text" name="nombre" placeholder="Nombre">
                <input type="number" name="cantidad" placeholder="Cantidad">
                <input type="number" step="0.01" name="precio" placeholder="Precio">
                <input type="text" name="categoria" placeholder="Categoría">
                <input type="text" name="marca" placeholder="Marca">
                <input type="date" name="fecha">
                <input type="submit" value="Guardar">
            </form>
        </div>

        
        
        
        
        <!--Formulario para Movimiento -->
        <div id="form_mov" class="formulario">
            <h3>Registrar entrada/salida</h3>
            <form method="post" action="movimiento">
                <input type="text" name="codigo" placeholder="Código">
                <input type="number" name="cantidad" placeholder="Cantidad">
                <select name="tipo">
                    <option value="entrada">Entrada</option>
                    <option value="salida">Salida</option>
                </select>
                <input type="submit" value="Actualizar">
            </form>
        </div>

        
        
        
        
        <!--Formulario para Editar -->
        <div id="form_editar" class="formulario">
            <h3>Editar producto</h3>
            <form method="post" action="editarProducto">
                <input type="text" name="codigo" placeholder="Código">
                <input type="text" name="nombre" placeholder="Nuevo nombre">
                <input type="number" name="cantidad" placeholder="Nueva cantidad">
                <input type="number" step="0.01" name="precio" placeholder="Nuevo precio">
                <input type="text" name="categoria" placeholder="Nueva categoría">
                <input type="text" name="marca" placeholder="Nueva marca">
                <input type="date" name="fecha">
                <input type="submit" value="Actualizar">
            </form>
        </div>

        
        
        
        
        <!--formulario para Eliminar -->
        <div id="form_eliminar" class="formulario">
            <h3>Eliminar producto</h3>
            <form method="post" action="eliminarProducto">
                <input type="text" name="codigo" placeholder="Código del producto">
                <input type="submit" value="Eliminar">
            </form>
        </div>

        
        
        
        
        <!--Reportes -->
        <div id="form_reportes" class="formulario">
            <h3>Productos con bajo stock (? 5)</h3>
            <table>
                <tr><th>Código</th><th>Nombre</th><th>Cantidad</th><th>Precio</th><th>Categoría</th><th>Marca</th><th>Reposición</th></tr>
                <%
                List<Producto> bajoStock = logica.ProductoDAO.obtenerBajoStock(5);
                for (Producto p : bajoStock) {
                %>
                <tr>
                    <td><%= p.getCodigo() %></td><td><%= p.getNombre() %></td><td><%= p.getCantidad() %></td>
                    <td><%= p.getPrecio() %></td><td><%= p.getCategoria() %></td><td><%= p.getMarca() %></td>
                    <td><%= p.getFechaReposicion() %></td>
                </tr>
                <% } %>
            </table>
        </div>

            
            
            
            
        <!--historial -->
        <div id="form_historial" class="formulario">
            <h3>Historial de movimientos</h3>
            <table>
                <tr><th>Producto</th><th>Tipo</th><th>Cantidad</th><th>Fecha</th></tr>
                <%
                List<String[]> historial = logica.ProductoDAO.obtenerHistorial();
                for (String[] fila : historial) {
                %>
                <tr>
                    <td><%= fila[0] %></td><td><%= fila[1] %></td><td><%= fila[2] %></td><td><%= fila[3] %></td>
                </tr>
                <% } %>
            </table>
        </div>

            
            
            
        <!-- Tabla para inventario -->
        <h2>Inventario actual</h2>
        <table>
            <tr><th>Código</th><th>Nombre</th><th>Cantidad</th><th>Precio</th><th>Categoría</th><th>Marca</th><th>Reposición</th></tr>
            <%
            if (lista != null) {
                for (Producto p : lista) {
            %>
            <tr>
                <td><%= p.getCodigo() %></td><td><%= p.getNombre() %></td><td><%= p.getCantidad() %></td>
                <td><%= p.getPrecio() %></td><td><%= p.getCategoria() %></td><td><%= p.getMarca() %></td>
                <td><%= p.getFechaReposicion() %></td>
            </tr>
            <% }} else { %>
            <tr><td colspan="7">No hay productos</td></tr>
            <% } %>
        </table>
    </main>
</div>
</body>
</html>