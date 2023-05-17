package org.bedu.v2_tiendabedu.models.producto;

public class CalzadoRopa extends Producto {

    private static final int id = 0;
    protected float talla;


    public CalzadoRopa(String nombre, String descripcion, float precio,
                       String modelo, float talla, String tipo, int stock) {
        super(id, nombre, descripcion, tipo, modelo, precio, stock);
        this.talla = talla;
    }


    public float getTalla(float talla) {
        return this.talla = talla;
    }

    public void setTalla(float talla) {
        this.talla = talla;
    }

    @Override
    public String descripcionProducto() {
        return this + "\nTalla del producto:" + this.talla;
    }
}

