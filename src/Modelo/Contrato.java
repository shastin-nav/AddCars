package Modelo;

import java.util.Date;

public class Contrato {

    private String idContrato;
    private Date fechaInicio;
    private Date fechaFin;
    private String condiciones;
    private String estadoContrato;
    private String dni;
    private String telefono;
    private String patente;
    private String idRol;
    private String idModelo;

    public Contrato(String idContrato, Date fechaInicio, Date fechaFin, String condiciones, String estadoContrato, String dni, String telefono, String patente, String idRol, String idModelo) {
        this.idContrato = idContrato; //identificador unico del contrato
        this.fechaInicio = fechaInicio; //fecha en que comienza el contrato
        this.fechaFin = fechaFin; //fecha en que termina el contrato
        this.condiciones = condiciones; //condiciones del acuerdo
        this.estadoContrato = estadoContrato; //estado actual del contrato
        this.dni = dni; //identificacion del cliente
        this.telefono = telefono; //contacto del cliente
        this.patente = patente; //patente del vehiculo asociado
        this.idRol = idRol; //rol del usuario que registra
        this.idModelo = idModelo; //modelo del vehiculo
    }

    //getters para acceder a los datos del contrato
    public String getIdContrato() { return idContrato; }
    public Date getFechaInicio() { return fechaInicio; }
    public Date getFechaFin() { return fechaFin; }
    public String getCondiciones() { return condiciones; }
    public String getEstadoContrato() { return estadoContrato; }
    public String getDni() { return dni; }
    public String getTelefono() { return telefono; }
    public String getPatente() { return patente; }
    public String getIdRol() { return idRol; }
    public String getIdModelo() { return idModelo; }
}
