package src;

import java.util.Date;

public class Documento {
    private int id;
    private int idPaciente;
    private Integer idCuidador; 
    private Integer idReingreso;
    private String nombreOriginal;
    private String tipoDocumento;
    private String mime;
    private byte[] contenido;
    private long tamanio;
    private String referenciaNombre;
    private Date fechaSubida;

    public Documento() {}

    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    
    public int getIdPaciente() {
        return idPaciente;
    }
    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    
    public Integer getIdCuidador() {
        return idCuidador;
    }
    public void setIdCuidador(Integer idCuidador) {
        this.idCuidador = idCuidador;
    }

    
    public Integer getIdReingreso() {
        return idReingreso;
    }
    public void setIdReingreso(Integer idReingreso) {
        this.idReingreso = idReingreso;
    }

    
    public String getNombreOriginal() {
        return nombreOriginal;
    }
    public void setNombreOriginal(String nombreOriginal) {
        this.nombreOriginal = nombreOriginal;
    }

    
    public String getTipoDocumento() {
        return tipoDocumento;
    }
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    
    public String getMime() {
        return mime;
    }
    public void setMime(String mime) {
        this.mime = mime;
    }

    
    public byte[] getContenido() {
        return contenido;
    }
    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

    
    public long getTamanio() {
        return tamanio;
    }
    public void setTamanio(long tamanio) {
        this.tamanio = tamanio;
    }

    
    public String getReferenciaNombre() {
        return referenciaNombre;
    }
    public void setReferenciaNombre(String referenciaNombre) {
        this.referenciaNombre = referenciaNombre;
    }

    
    public Date getFechaSubida() {
        return fechaSubida;
    }
    public void setFechaSubida(Date fechaSubida) {
        this.fechaSubida = fechaSubida;
    }

    @Override
    public String toString() {
        return "Documento{" +
                "id=" + id +
                ", idPaciente=" + idPaciente +
                ", idCuidador=" + idCuidador +
                ", idReingreso=" + idReingreso +
                ", nombreOriginal='" + nombreOriginal + '\'' +
                ", tipoDocumento='" + tipoDocumento + '\'' +
                ", mime='" + mime + '\'' +
                ", tamanio=" + tamanio +
                ", referenciaNombre='" + referenciaNombre + '\'' +
                ", fechaSubida=" + fechaSubida +
                '}';
    }
}