package src;

import java.util.Date;

public class Reingreso {
    private int idReingreso;
    private int idCuidador;
    private Date fecha;
    private String motivo;
    private String cuidador;

    public Reingreso() {}

    

    public int getIdReingreso() {
        return idReingreso;
    }

    public void setIdReingreso(int idReingreso) {
        this.idReingreso = idReingreso;
    }

    public int getIdCuidador() {
        return idCuidador;
    }

    public void setIdCuidador(int idCuidador) {
        this.idCuidador = idCuidador;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getCuidador() {
        return cuidador;
    }

    public void setCuidador(String cuidador) {
        this.cuidador = cuidador;
    }
}
