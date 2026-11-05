package Modelo; //paquete base

public class VehiculoDetalle extends Vehiculo {

    private String descripcion; 
    private String imagenUrl;
    private int anio; 
    private String categoriaNombre; 
    private String idCategoria; 
    private String idModelo; 
    private String idSucursal; 

    public VehiculoDetalle(String idVehiculo, String marcaModelo, int precioDia, String sucursalNombre,
                           String descripcion, String imagenUrl, int anio,
                           String idCategoria, String idModelo, String idSucursal) {
        super(idVehiculo, marcaModelo, precioDia, sucursalNombre); //llama constructor padre
        this.descripcion = descripcion;
        this.imagenUrl = imagenUrl;
        this.anio = anio;
        this.idCategoria = idCategoria;
        this.idModelo = idModelo;
        this.idSucursal = idSucursal;
        this.categoriaNombre = null; //asignado luego desde dao
    }

    public String getDescripcion() { return descripcion; } //getter descripcion
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; } //setter descripcion

    public String getImagenUrl() { return imagenUrl; } //getter imagen
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; } //setter imagen

    public int getAnio() { return anio; } //getter año
    public void setAnio(int anio) { this.anio = anio; } //setter año

    public String getCategoriaNombre() { return categoriaNombre; } //getter nombre categoria
    public void setCategoriaNombre(String categoriaNombre) { this.categoriaNombre = categoriaNombre; } //setter nombre categoria

    public String getIdCategoria() { return idCategoria; } //getter id categoria
    public void setIdCategoria(String idCategoria) { this.idCategoria = idCategoria; } //setter id categoria

    public String getIdModelo() { return idModelo; } //getter id modelo
    public void setIdModelo(String idModelo) { this.idModelo = idModelo; } //setter id modelo

    public String getIdSucursal() { return idSucursal; } //getter id sucursal
    public void setIdSucursal(String idSucursal) { this.idSucursal = idSucursal; } //setter id sucursal

    @Override
    public String toString() {
        return super.toString() + String.format(" | Año: %d", anio); //muestra año adicional
    }
}
