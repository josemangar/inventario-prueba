package pruebas;

import logica.Producto;
import org.junit.Test;
import static org.junit.Assert.*;

public class PruebaProducto {

    @Test
    public void pruebaNombre() {
        Producto p = new Producto("P01", "Lapicero", 10, 5.50, "Oficina", "Bic", "2025-10-01");
        assertEquals("Lapicero", p.getNombre());
    }
}
