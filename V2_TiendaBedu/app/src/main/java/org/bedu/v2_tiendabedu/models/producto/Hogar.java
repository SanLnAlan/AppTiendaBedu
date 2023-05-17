package org.bedu.v2_tiendabedu.models.producto;

public class Hogar extends Producto {
    private static final int id = 0;
    protected String numeroSerie;

    public Hogar(String nombre, String numeroSerie, String modelo, String descripcion, float precio,
                 String tipo, int stock) {
        super(id, nombre, descripcion, tipo, modelo, precio, stock);
        this.numeroSerie = numeroSerie;
    }

    public String getnumeroSerie() {
        return this.numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    @Override
    public String descripcionProducto() {
        return this + "\nNúmero de serie: " + this.numeroSerie;
    }
}
