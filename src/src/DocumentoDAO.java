package src;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocumentoDAO {
    private final Connection conn;

    public DocumentoDAO(Connection conn) {
        this.conn = conn;
    }

    public int guardarDocumento(Documento doc) throws SQLException {
        String sql = "INSERT INTO documentos (id_paciente, id_cuidador, id_reingreso, nombre_original, tipo_documento, mime, contenido, tamanio, referencia_nombre) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, doc.getIdPaciente());
            if (doc.getIdCuidador() != null) ps.setInt(2, doc.getIdCuidador()); else ps.setNull(2, Types.INTEGER);
            if (doc.getIdReingreso() != null) ps.setInt(3, doc.getIdReingreso()); else ps.setNull(3, Types.INTEGER);
            ps.setString(4, doc.getNombreOriginal());
            ps.setString(5, doc.getTipoDocumento());
            ps.setString(6, doc.getMime());
            if (doc.getContenido() != null) ps.setBytes(7, doc.getContenido()); else ps.setNull(7, Types.BLOB);
            ps.setLong(8, doc.getTamanio());
            ps.setString(9, doc.getReferenciaNombre());

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return 0;
    }

    public List<Documento> listarPorPaciente(int idPaciente) throws SQLException {
        String sql = "SELECT id_documento, id_paciente, id_cuidador, id_reingreso, nombre_original, tipo_documento, mime, tamanio, referencia_nombre, fecha_subida FROM documentos WHERE id_paciente = ? ORDER BY fecha_subida DESC";
        List<Documento> lista = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPaciente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Documento d = new Documento();
                    d.setId(rs.getInt("id_documento"));
                    d.setIdPaciente(rs.getInt("id_paciente"));
                    int idC = rs.getInt("id_cuidador");
                    d.setIdCuidador(rs.wasNull() ? null : idC);
                    int idR = rs.getInt("id_reingreso");
                    d.setIdReingreso(rs.wasNull() ? null : idR);
                    d.setNombreOriginal(rs.getString("nombre_original"));
                    d.setTipoDocumento(rs.getString("tipo_documento"));
                    d.setMime(rs.getString("mime"));
                    d.setTamanio(rs.getLong("tamanio"));
                    d.setReferenciaNombre(rs.getString("referencia_nombre"));
                    d.setFechaSubida(rs.getTimestamp("fecha_subida"));
                    lista.add(d);
                }
            }
        }
        return lista;
    }

    public Documento obtenerContenido(int idDocumento) throws SQLException {
        String sql = "SELECT * FROM documentos WHERE id_documento = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idDocumento);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Documento d = new Documento();
                    d.setId(rs.getInt("id_documento"));
                    d.setIdPaciente(rs.getInt("id_paciente"));
                    int idC = rs.getInt("id_cuidador");
                    d.setIdCuidador(rs.wasNull() ? null : idC);
                    int idR = rs.getInt("id_reingreso");
                    d.setIdReingreso(rs.wasNull() ? null : idR);
                    d.setNombreOriginal(rs.getString("nombre_original"));
                    d.setTipoDocumento(rs.getString("tipo_documento"));
                    d.setMime(rs.getString("mime"));
                    d.setContenido(rs.getBytes("contenido"));
                    d.setTamanio(rs.getLong("tamanio"));
                    d.setReferenciaNombre(rs.getString("referencia_nombre"));
                    d.setFechaSubida(rs.getTimestamp("fecha_subida"));
                    return d;
                }
            }
        }
        return null;
    }
}