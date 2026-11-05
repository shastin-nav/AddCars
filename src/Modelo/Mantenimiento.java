package Modelo; // Updated package

import java.util.Date;

/**
 * POJO for Maintenance data.
 */
public class Mantenimiento {
    private Date fecha;
    private String tipo;
    private String detalle;
    private int costo;

    public Mantenimiento(Date fecha, String tipo, String detalle, int costo) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.detalle = detalle;
        this.costo = costo;
    }

    // Getters
    public Date getFecha() { return fecha; }
    public String getTipo() { return tipo; }
    public String getDetalle() { return detalle; }
    public int getCosto() { return costo; }
}