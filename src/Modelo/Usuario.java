package Modelo;

public class Usuario {
    private String idUsuario;
    private String idRol;
    private String nomUsuario;

    public Usuario(String idUsuario, String idRol, String nomUsuario) {
        this.idUsuario = idUsuario; //identificador unico del usuario
        this.idRol = idRol; //rol asignado al usuario
        this.nomUsuario = nomUsuario; //nombre visible del usuario
    }

    //setters y getters
    public String getIdUsuario() { return idUsuario; }
    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }
    public String getIdRol() { return idRol; }
    public void setIdRol(String idRol) { this.idRol = idRol; }
    public String getNomUsuario() { return nomUsuario; }
    public void setNomUsuario(String nomUsuario) { this.nomUsuario = nomUsuario; }
}
