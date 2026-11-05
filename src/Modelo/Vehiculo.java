package Modelo;

public class Vehiculo {
    private String idVehiculo;
    private String marcaModelo;
    private int precioDia;
    private String sucursal;

    public Vehiculo(String idVehiculo, String marcaModelo, int precioDia, String sucursal) {
        this.idVehiculo = idVehiculo; //identificador unico del vehiculo
        this.marcaModelo = marcaModelo; //marca y modelo del vehiculo
        this.precioDia = precioDia; //valor por dia
        this.sucursal = sucursal; //sucursal donde se encuentra
    }

    public String getIdVehiculo() { return idVehiculo; }
    public String getMarcaModelo() { return marcaModelo; }
    public int getPrecioDia() { return precioDia; }
    public String getSucursal() { return sucursal; }

    @Override
    public String toString() {
        return String.format("%-6s | %-22s | $%-10s | %s",
                idVehiculo != null ? idVehiculo : "",
                marcaModelo != null ? marcaModelo : "",
                precioDia > 0 ? String.format("%,d", precioDia) : "",
                sucursal != null ? sucursal : ""); //formato de salida para mostrar datos
    }
}
