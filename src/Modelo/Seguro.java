package Modelo; // Updated package

import java.util.Date;

/**
 * POJO for Insurance data.
 */
public class Seguro {
    private String compania;
    private String descripcion;
    private String cobertura;
    private Date fechaInicio;
    private Date fechaFin;
    private int costoAnual;

    public Seguro(String compania, String descripcion, String cobertura, Date fechaInicio, Date fechaFin, int costoAnual) {
        this.compania = compania;
        this.descripcion = descripcion;
        this.cobertura = cobertura;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.costoAnual = costoAnual;
    }

    // Getters
    public String getCompania() { return compania; }
    public String getDescripcion() { return descripcion; }
    public String getCobertura() { return cobertura; }
    public Date getFechaInicio() { return fechaInicio; }
    public Date getFechaFin() { return fechaFin; }
    public int getCostoAnual() { return costoAnual; }
}