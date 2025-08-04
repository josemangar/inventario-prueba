package logica;

public class Producto {
    private String codigo;
    private String nombre;
    private int cantidad;
    private double precio;
    private String categoria;
    private String marca;
    private String fechaReposicion;

    public Producto(String codigo, String nombre, int cantidad, double precio,
                    String categoria, String marca, String fechaReposicion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.categoria = categoria;
        this.marca = marca;
        this.fechaReposicion = fechaReposicion;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public int getCantidad() { return cantidad; }
    public double getPrecio() { return precio; }
    public String getCategoria() { return categoria; }
    public String getMarca() { return marca; }
    public String getFechaReposicion() { return fechaReposicion; }
}
