package src;

import java.util.Date;

public class Cuidador {
    private int id;  
    private String nombre;
    private Date fechaIngreso;
    private int edad;
    private String sexo;
    private String estadoCivil;
    private int numeroHijos;
    private String ocupacion;
    private String calleNumero;   


    private String codigoPostal;
    private String localidad;

    private String colonia;
    private String municipio;
    private String estado;
    private String telefono;
    private String parentesco;

    public Cuidador() {}

    //getters y setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Date getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(Date fechaIngreso) { this.fechaIngreso = fechaIngreso; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public String getEstadoCivil() { return estadoCivil; }
    public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; }
    
    public String getCalleNumero() { return calleNumero; }
    public void setCalleNumero(String calleNumero) { this.calleNumero = calleNumero; }

    public int getNumeroHijos() { return numeroHijos; }
    public void setNumeroHijos(int numeroHijos) { this.numeroHijos = numeroHijos; }

    public String getOcupacion() { return ocupacion; }
    public void setOcupacion(String ocupacion) { this.ocupacion = ocupacion; }

    public String getCodigoPostal() { return codigoPostal; }
    public void setCodigoPostal(String codigoPostal) { this.codigoPostal = codigoPostal; }

    public String getLocalidad() { return localidad; }
    public void setLocalidad(String localidad) { this.localidad = localidad; }

    public String getColonia() { return colonia; }
    public void setColonia(String colonia) { this.colonia = colonia; }

    public String getMunicipio() { return municipio; }
    public void setMunicipio(String municipio) { this.municipio = municipio; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getParentesco() { return parentesco; }
    public void setParentesco(String parentesco) { this.parentesco = parentesco; }
}
