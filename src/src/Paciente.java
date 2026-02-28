package src;

public class Paciente {
    private int id;                  
    private String nombre;
    private int edad;
    private String sexo;
    private String estadoCivil;      
    private int numHijos;            
    private String ocupacion;
    private String colonia;          
    private String estado;
    private String diagnostico;
    private String tratamiento;
    private String duracion;
    private String hospital;
    private String telefono;
    private java.util.Date fechaIngreso;   
    private String observaciones;
    private String municipio;
    private String cuidadoresConcat;
    private String reingresosConcat;

    public Paciente() {}

    

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public String getEstadoCivil() { return estadoCivil; }
    public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; }

    public int getNumHijos() { return numHijos; }
    public void setNumHijos(int numHijos) { this.numHijos = numHijos; }

    public String getOcupacion() { return ocupacion; }
    public void setOcupacion(String ocupacion) { this.ocupacion = ocupacion; }


    public String getColonia() { return colonia; }
    public void setColonia(String colonia) { this.colonia = colonia; }

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

    public java.util.Date getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(java.util.Date fechaIngreso) { this.fechaIngreso = fechaIngreso; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    
    public String getCuidadoresConcat() { return cuidadoresConcat; }
    public void setCuidadoresConcat(String c) { this.cuidadoresConcat = c; }

    public String getReingresosConcat() { return reingresosConcat; }
    public void setReingresosConcat(String r) { this.reingresosConcat = r; }
    

        private Cuidador cuidador;

        public Cuidador getCuidador() {
            return cuidador;
        }

        public void setCuidador(Cuidador cuidador) {
            this.cuidador = cuidador;
        }
    


    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", sexo='" + sexo + '\'' +
                ", estadoCivil='" + estadoCivil + '\'' +
                ", numHijos=" + numHijos +
                ", ocupacion='" + ocupacion + '\'' +
                ", colonia='" + colonia + '\'' +
                ", municipio='" + municipio + '\'' +
                ", estado='" + estado + '\'' +
                ", diagnostico='" + diagnostico + '\'' +
                ", tratamiento='" + tratamiento + '\'' +
                ", duracion='" + duracion + '\'' +
                ", hospital='" + hospital + '\'' +
                ", telefono='" + telefono + '\'' +
                ", fechaIngreso=" + fechaIngreso +
                ", observaciones='" + observaciones + '\'' +
                '}';
    }
}
