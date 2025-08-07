package pruebas;

import static org.junit.Assert.*;
import org.junit.Test;
import logica.Producto;

public class PruebaProducto {

    @Test
    public void testCrearProducto() {
        Producto p = new Producto();
        p.setCodigo("P001");
        p.setNombre("Producto de prueba");
        p.setCantidad(10);
        p.setPrecio(15.5);
        p.setCategoria("General");
        p.setMarca("MarcaX");
        p.setFechaReposicion("2025-12-01");

        assertEquals("P001", p.getCodigo());
        assertEquals("Producto de prueba", p.getNombre());
        assertEquals(10, p.getCantidad());
        assertEquals(15.5, p.getPrecio(), 0.01);
        assertEquals("General", p.getCategoria());
        assertEquals("MarcaX", p.getMarca());
        assertEquals("2025-12-01", p.getFechaReposicion());
    }
}
