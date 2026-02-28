package src;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IngresoDiarioDAO {
    private Connection conn;

    public IngresoDiarioDAO() throws SQLException {
        this.conn = ConexionBD.getConexion();
        if (this.conn == null) {
            throw new SQLException("No se pudo establecer conexión a la base de datos.");
        }
    }

    public IngresoDiarioDAO(Connection conn) throws SQLException {
        if (conn == null) {
            System.err.println("⚠️ La conexión pasada es null, intentando obtener conexión interna...");
            this.conn = ConexionBD.getConexion();
            if (this.conn == null) {
                throw new SQLException("No se pudo establecer conexión a la base de datos.");
            }
        } else {
            this.conn = conn;
        }
    }

    public void cerrarConexion() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Error cerrando la conexión: " + e.getMessage());
            }
        }
    }
    
 
    public Connection getConnection() {
        return this.conn;
    }

    
    public int insertarPaciente(IngresoDiario paciente) throws SQLException {
        String sql = """
            INSERT INTO pacientes (nombre, edad, sexo, estado_civil, num_hijos, ocupacion,
                                   colonia, municipio, estado, diagnostico, tratamiento, duracion,
                                   hospital, telefono, fecha_ingreso, observaciones, codigo_postal, localidad)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, paciente.getNombrePaciente());
            ps.setInt(2, paciente.getEdad());
            ps.setString(3, paciente.getSexo());
            ps.setString(4, paciente.getEstadoCivil());
            ps.setInt(5, paciente.getNumeroHijos());
            ps.setString(6, paciente.getOcupacion());
            
            ps.setString(7, paciente.getColonia());
            ps.setString(8, paciente.getMunicipio());
            ps.setString(9, paciente.getEstado());
            ps.setString(10, paciente.getDiagnostico());
            ps.setString(11, paciente.getTratamiento());
            ps.setString(12, paciente.getDuracion());
            ps.setString(13, paciente.getHospital());
            ps.setString(14, paciente.getTelefono());
            if (paciente.getFechaIngreso() != null)
                ps.setDate(15, new java.sql.Date(paciente.getFechaIngreso().getTime()));
            else
                ps.setNull(15, java.sql.Types.DATE);
            ps.setString(16, paciente.getObservaciones());
            ps.setInt(17, paciente.getCodigoPostal());
            ps.setString(18, paciente.getLocalidad());

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return 0;
    }
    
    
    public int contarPacientes() throws SQLException {
        String sql = "SELECT COUNT(*) FROM pacientes";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    public IngresoDiario buscarPorId(int idPaciente) throws SQLException {
        String sql = "SELECT * FROM pacientes WHERE id_paciente = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPaciente);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    IngresoDiario ingreso = new IngresoDiario();
                    ingreso.setIdPaciente(idPaciente);

                    
                    Integer ultimaPagina = obtenerUltimoIngreso(idPaciente);
                    ingreso.setIdIngreso(ultimaPagina != null ? ultimaPagina : 0);

                    
                    ingreso.setNombrePaciente(rs.getString("nombre"));
                    ingreso.setEdad(rs.getInt("edad"));
                    ingreso.setSexo(rs.getString("sexo"));
                    ingreso.setEstadoCivil(rs.getString("estado_civil"));
                    ingreso.setNumeroHijos(rs.getInt("num_hijos"));
                    ingreso.setOcupacion(rs.getString("ocupacion"));
                    ingreso.setCodigoPostal(rs.getInt("codigo_postal"));
                    ingreso.setColonia(rs.getString("colonia"));
                    ingreso.setLocalidad(rs.getString("localidad"));
                    ingreso.setMunicipio(rs.getString("municipio"));
                    ingreso.setEstado(rs.getString("estado"));
                    ingreso.setDiagnostico(rs.getString("diagnostico"));
                    ingreso.setTratamiento(rs.getString("tratamiento"));
                    ingreso.setDuracion(rs.getString("duracion"));
                    ingreso.setHospital(rs.getString("hospital"));
                    ingreso.setTelefono(rs.getString("telefono"));
                    ingreso.setObservaciones(rs.getString("observaciones"));
                    ingreso.setFechaIngreso(rs.getDate("fecha_ingreso"));

                    
                    ingreso.setCuidadores(cargarCuidadoresPorPaciente(idPaciente));
                    ingreso.setReingresos(cargarReingresosPorPaciente(idPaciente));

                    return ingreso;
                }
            }
        }

        return null;
    }


    public void actualizarPaciente(IngresoDiario paciente) throws SQLException {
        String sql = """
            UPDATE pacientes SET
                nombre = ?, edad = ?, sexo = ?, estado_civil = ?, num_hijos = ?, ocupacion = ?,
                colonia = ?, municipio = ?, estado = ?, diagnostico = ?, tratamiento = ?, duracion = ?,
                hospital = ?, telefono = ?, fecha_ingreso = ?, observaciones = ?, codigo_postal = ?, localidad = ?
            WHERE id_paciente = ?
            """;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, paciente.getNombrePaciente());
            ps.setInt(2, paciente.getEdad());
            ps.setString(3, paciente.getSexo());
            ps.setString(4, paciente.getEstadoCivil());
            ps.setInt(5, paciente.getNumeroHijos());
            ps.setString(6, paciente.getOcupacion());
            ps.setString(7, paciente.getColonia());
            ps.setString(8, paciente.getMunicipio());
            ps.setString(9, paciente.getEstado());
            ps.setString(10, paciente.getDiagnostico());
            ps.setString(11, paciente.getTratamiento());
            ps.setString(12, paciente.getDuracion());
            ps.setString(13, paciente.getHospital());
            ps.setString(14, paciente.getTelefono());
            if (paciente.getFechaIngreso() != null)
                ps.setDate(15, new java.sql.Date(paciente.getFechaIngreso().getTime()));
            else
                ps.setNull(15, java.sql.Types.DATE);
            ps.setString(16, paciente.getObservaciones());
            ps.setInt(17, paciente.getCodigoPostal());
            ps.setString(18, paciente.getLocalidad());
            ps.setInt(19, paciente.getIdPaciente());

            ps.executeUpdate();
        }
    }
    
    
    
    public int insertarCuidador(Cuidador cuidador, int idPaciente) throws SQLException {
        String sql = """
            INSERT INTO cuidadores (nombre, edad, sexo, estado_civil, num_hijos, ocupacion,
                                    municipio, estado, telefono, parentesco, fecha_ingreso,
                                    calle_numero, codigo_postal, localidad, id_paciente)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, cuidador.getNombre());
            ps.setInt(2, cuidador.getEdad());
            ps.setString(3, cuidador.getSexo());
            ps.setString(4, cuidador.getEstadoCivil());
            ps.setInt(5, cuidador.getNumeroHijos());
            ps.setString(6, cuidador.getOcupacion());
            ps.setString(7, cuidador.getMunicipio());
            ps.setString(8, cuidador.getEstado());
            ps.setString(9, cuidador.getTelefono());
            ps.setString(10, cuidador.getParentesco());
         
            if (cuidador.getFechaIngreso() != null)
                ps.setDate(11, new java.sql.Date(cuidador.getFechaIngreso().getTime()));
            else
                ps.setNull(11, java.sql.Types.DATE);

            
            ps.setString(12, cuidador.getCalleNumero());
            ps.setString(13, cuidador.getCodigoPostal());
            ps.setString(14, cuidador.getLocalidad());
            ps.setInt(15, idPaciente);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return 0;
    }

    
    public void insertarIngresoDiario(int idPaciente, int idCuidador, Date fechaIngreso, String motivo, String observaciones) throws SQLException {
        String sql = "INSERT INTO ingresos_diarios (id_paciente, id_cuidador, fecha_ingreso, motivo, observaciones) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPaciente);
            ps.setInt(2, idCuidador);
            if (fechaIngreso != null)
                ps.setDate(3, new java.sql.Date(fechaIngreso.getTime()));
            else
                ps.setNull(3, java.sql.Types.DATE);
            ps.setString(4, motivo);
            ps.setString(5, observaciones);
            ps.executeUpdate();
        }
    }

    
    public Cuidador buscarCuidadorPorNombre(String nombre) throws SQLException {
        String sql = "SELECT * FROM cuidadores WHERE nombre = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cuidador c = new Cuidador();
                    c.setId(rs.getInt("id_cuidador"));
                    c.setNombre(rs.getString("nombre"));
                    c.setEdad(rs.getInt("edad"));
                    c.setSexo(rs.getString("sexo"));
                    c.setEstadoCivil(rs.getString("estado_civil"));
                    c.setNumeroHijos(rs.getInt("num_hijos"));
                    c.setOcupacion(rs.getString("ocupacion"));
                    c.setMunicipio(rs.getString("municipio"));
                    c.setEstado(rs.getString("estado"));
                    c.setTelefono(rs.getString("telefono"));
                    c.setParentesco(rs.getString("parentesco"));
                    c.setCalleNumero(rs.getString("calle_numero"));
                    c.setFechaIngreso(rs.getDate("fecha_ingreso"));
                    c.setCodigoPostal(rs.getString("codigo_postal"));
                    c.setLocalidad(rs.getString("localidad"));
                    return c;
                }
            }
        }
        return null;
    }



    
    private void eliminarCuidadoresPorPaciente(int idPaciente) throws SQLException {
        String sql = "DELETE FROM cuidadores WHERE id_paciente = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPaciente);
            ps.executeUpdate();
        }
    }

    
    private void eliminarReingresosPorPaciente(int idPaciente) throws SQLException {
        String sql = "DELETE FROM reingresos WHERE id_paciente = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPaciente);
            ps.executeUpdate();
        }
    }

    
    public int guardarIngreso(IngresoDiario ingreso) throws SQLException {
        if (conn == null) throw new SQLException("Conexión no disponible.");

        int idPaciente = ingreso.getIdPaciente();

        try {
            conn.setAutoCommit(false);

            if (idPaciente > 0) {
                actualizarPaciente(ingreso);

                eliminarCuidadoresPorPaciente(idPaciente);
                eliminarReingresosPorPaciente(idPaciente);
            } else {
                idPaciente = insertarPaciente(ingreso);
            }

            
            if (ingreso.getCuidadores() != null) {
                for (Cuidador c : ingreso.getCuidadores()) {
                    int idCuidador = insertarCuidador(c, idPaciente);
                    insertarIngresoDiario(idPaciente, idCuidador, ingreso.getFechaIngreso(), ingreso.getDiagnostico(), ingreso.getObservaciones());
                }
            }

            
            if (ingreso.getReingresos() != null) {
                for (Reingreso r : ingreso.getReingresos()) {
                    int idCuidador = 0;
                    if (r.getCuidador() != null && !r.getCuidador().isEmpty()) {
                        Cuidador c = buscarCuidadorPorNombre(r.getCuidador());
                        if (c != null) idCuidador = c.getId();
                    }

                    String sql = "INSERT INTO reingresos (id_paciente, id_cuidador, fecha, motivo) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement ps = conn.prepareStatement(sql)) {
                        ps.setInt(1, idPaciente);
                        if (idCuidador > 0)
                            ps.setInt(2, idCuidador);
                        else
                            ps.setNull(2, java.sql.Types.INTEGER);
                        if (r.getFecha() != null)
                            ps.setDate(3, new java.sql.Date(r.getFecha().getTime()));
                        else
                            ps.setNull(3, java.sql.Types.DATE);
                        ps.setString(4, r.getMotivo());
                        ps.executeUpdate();
                    }
                }
            }

            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }

        return idPaciente;
    }


    
    private List<Cuidador> cargarCuidadoresPorPaciente(int idPaciente) throws SQLException {
        List<Cuidador> cuidadores = new ArrayList<>();
        String sql = "SELECT * FROM cuidadores WHERE id_paciente = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPaciente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Cuidador c = new Cuidador();
                    c.setId(rs.getInt("id_cuidador"));
                    c.setNombre(rs.getString("nombre"));
                    c.setEdad(rs.getInt("edad"));
                    c.setSexo(rs.getString("sexo"));
                    c.setEstadoCivil(rs.getString("estado_civil"));
                    c.setNumeroHijos(rs.getInt("num_hijos"));
                    c.setOcupacion(rs.getString("ocupacion"));
                    c.setMunicipio(rs.getString("municipio"));
                    c.setEstado(rs.getString("estado"));
                    c.setTelefono(rs.getString("telefono"));
                    c.setParentesco(rs.getString("parentesco"));
                    c.setFechaIngreso(rs.getDate("fecha_ingreso"));
                    c.setCalleNumero(rs.getString("calle_numero"));
                    c.setCodigoPostal(rs.getString("codigo_postal"));
                    c.setLocalidad(rs.getString("localidad"));
                    cuidadores.add(c);
                }
            }
        }
        return cuidadores;
    }

    
    private List<Reingreso> cargarReingresosPorPaciente(int idPaciente) throws SQLException {
        List<Reingreso> reingresos = new ArrayList<>();
        String sql = "SELECT r.*, c.nombre AS nombre_cuidador FROM reingresos r LEFT JOIN cuidadores c ON r.id_cuidador = c.id_cuidador WHERE r.id_paciente = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPaciente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Reingreso r = new Reingreso();
                    r.setIdReingreso(rs.getInt("id_reingreso"));
                    r.setFecha(rs.getDate("fecha"));
                    r.setMotivo(rs.getString("motivo"));
                    r.setCuidador(rs.getString("nombre_cuidador"));
                    r.setIdCuidador(rs.getInt("id_cuidador"));
                    reingresos.add(r);
                }
            }
        }
        return reingresos;
    }
    
    public Integer obtenerUltimoIngreso(int idPaciente) throws SQLException {
        String sql = "SELECT id_ingreso FROM ingresos_diarios WHERE id_paciente = ? ORDER BY id_ingreso DESC LIMIT 1";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPaciente);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_ingreso");
                }
            }
        }
        return null; 
    }

    public int obtenerUltimoId() throws SQLException {
        String sql = "SELECT IFNULL(MAX(id_paciente), 0) FROM pacientes";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }



    public List<IngresoDiario> buscarPorNombrePaciente(String nombre) throws SQLException {
        List<IngresoDiario> lista = new ArrayList<>();
        String sql = "SELECT * FROM pacientes WHERE nombre LIKE ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + nombre + "%");

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    int idPaciente = rs.getInt("id_paciente");

                    IngresoDiario ingreso = new IngresoDiario();
                    ingreso.setIdPaciente(idPaciente);

                    Integer ultimaPagina = obtenerUltimoIngreso(idPaciente);
                    ingreso.setIdIngreso(ultimaPagina != null ? ultimaPagina : 0);
                    ingreso.setNombrePaciente(rs.getString("nombre"));
                    ingreso.setEdad(rs.getInt("edad"));
                    ingreso.setSexo(rs.getString("sexo"));
                    ingreso.setEstadoCivil(rs.getString("estado_civil"));
                    ingreso.setNumeroHijos(rs.getInt("num_hijos"));
                    ingreso.setOcupacion(rs.getString("ocupacion"));
                    ingreso.setCodigoPostal(rs.getInt("codigo_postal"));
                    ingreso.setColonia(rs.getString("colonia"));
                    ingreso.setLocalidad(rs.getString("localidad"));
                    ingreso.setMunicipio(rs.getString("municipio"));
                    ingreso.setEstado(rs.getString("estado"));
                    ingreso.setDiagnostico(rs.getString("diagnostico"));
                    ingreso.setTratamiento(rs.getString("tratamiento"));
                    ingreso.setDuracion(rs.getString("duracion"));
                    ingreso.setHospital(rs.getString("hospital"));
                    ingreso.setTelefono(rs.getString("telefono"));
                    ingreso.setObservaciones(rs.getString("observaciones"));
                    ingreso.setFechaIngreso(rs.getDate("fecha_ingreso"));
                    ingreso.setCuidadores(cargarCuidadoresPorPaciente(idPaciente));
                    ingreso.setReingresos(cargarReingresosPorPaciente(idPaciente));

                    lista.add(ingreso);
                }
            }
        }

        return lista;
    }

    }

