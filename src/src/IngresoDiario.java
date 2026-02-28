package src;

import java.util.Date;
import java.util.List;

public class IngresoDiario {

    private int idPaciente;
    private int idIngreso;

    private String nombrePaciente;
    private Date fechaIngreso;
    private int edad;
    private String sexo;
    private String estadoCivil;
    private int numeroHijos;
    private String ocupacion;
    private String colonia;
    private int codigoPostal;
    private String localidad;
    private String municipio;
    private String estado;
    private String diagnostico;
    private String tratamiento;
    private String duracion;
    private String hospital;
    private String telefono;
    private String observaciones;
    private String calleNumero;

    private List<Cuidador> cuidadores;
    private List<Reingreso> reingresos;

    public IngresoDiario() {}


    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public int getIdIngreso() {
        return idIngreso;
    }

    public void setIdIngreso(int idIngreso) {
        this.idIngreso = idIngreso;
    }
    
    public String getCalleNumero() {
        return calleNumero;
    }
    public void setCalleNumero(String calleNumero) {
        this.calleNumero = calleNumero;
    }
    

    public String getNombrePaciente() { return nombrePaciente; }
    public void setNombrePaciente(String nombrePaciente) { this.nombrePaciente = nombrePaciente; }

    public Date getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(Date fechaIngreso) { this.fechaIngreso = fechaIngreso; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public String getEstadoCivil() { return estadoCivil; }
    public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; }

    public int getNumeroHijos() { return numeroHijos; }
    public void setNumeroHijos(int numeroHijos) { this.numeroHijos = numeroHijos; }

    public String getOcupacion() { return ocupacion; }
    public void setOcupacion(String ocupacion) { this.ocupacion = ocupacion; }

    public String getColonia() { return colonia; }
    public void setColonia(String colonia) { this.colonia = colonia; }

    public int getCodigoPostal() { return codigoPostal; }
    public void setCodigoPostal(int codigoPostal) { this.codigoPostal = codigoPostal; }

    public String getLocalidad() { return localidad; }
    public void setLocalidad(String localidad) { this.localidad = localidad; }

    public String getMunicipio() { return municipio; }
    public void setMunicipio(String municipio) { this.municipio = municipio; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }

    public String getTratamiento() { return tratamiento; }
    public void setTratamiento(String tratamiento) { this.tratamiento = tratamiento; }

    public String getDuracion() { return duracion; }
    public void setDuracion(String duracion) { this.duracion = duracion; }

    public String getHospital() { return hospital; }
    public void setHospital(String hospital) { this.hospital = hospital; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public List<Cuidador> getCuidadores() { return cuidadores; }
    public void setCuidadores(List<Cuidador> cuidadores) { this.cuidadores = cuidadores; }

    public List<Reingreso> getReingresos() { return reingresos; }
    public void setReingresos(List<Reingreso> reingresos) { this.reingresos = reingresos; }
}



