package src;

import java.sql.*;
import java.util.*;

public class ListaPacientesDAO {

    private Connection conn;

    public ListaPacientesDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Object[]> obtenerTodoCompleto() {

        List<Object[]> lista = new ArrayList<>();

        String sql =
            "SELECT " +
            
            "p.id_paciente, p.nombre, p.edad, p.sexo, p.estado_civil, p.num_hijos, " +
            "p.ocupacion, p.colonia, p.municipio, p.estado, p.codigo_postal, p.localidad, " +
            "p.telefono, p.diagnostico, p.tratamiento, p.duracion, p.hospital, " +
            "p.fecha_ingreso, p.observaciones, " +

            
            "c.nombre, c.edad, c.sexo, c.estado_civil, c.num_hijos, c.ocupacion, " +
            "c.calle_numero, c.municipio, c.estado, c.codigo_postal, c.localidad, " +
            "c.telefono, c.parentesco, c.fecha_ingreso, " +

            
            "r.fecha, r.motivo, cr.nombre " +

            "FROM pacientes p " +
            "LEFT JOIN cuidadores c ON p.id_paciente = c.id_paciente " +
            "LEFT JOIN reingresos r ON p.id_paciente = r.id_paciente " +
            "LEFT JOIN cuidadores cr ON r.id_cuidador = cr.id_cuidador " +
            "ORDER BY p.id_paciente, r.fecha";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Object[] {
                    rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4),
                    rs.getString(5), rs.getInt(6), rs.getString(7),
                    rs.getString(8), rs.getString(9), rs.getString(10),
                    rs.getString(11), rs.getString(12), rs.getString(13),
                    rs.getString(14), rs.getString(15), rs.getString(16),
                    rs.getString(17), rs.getDate(18), rs.getString(19),

                    rs.getString(20), rs.getInt(21), rs.getString(22),
                    rs.getString(23), rs.getInt(24), rs.getString(25),
                    rs.getString(26), rs.getString(27), rs.getString(28),
                    rs.getString(29), rs.getString(30), rs.getString(31),
                    rs.getString(32), rs.getDate(33),

                    rs.getDate(34), rs.getString(35), rs.getString(36)
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}


