package src;

import src.ConexionBD;
import src.Paciente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    
    public void agregarPaciente(Paciente p) {
        String sql = "INSERT INTO pacientes (nombre, edad, sexo, estado_civil, num_hijos, ocupacion, direccion, telefono) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setInt(2, p.getEdad());
            ps.setString(3, p.getSexo());
            ps.setString(4, p.getEstadoCivil());
            ps.setInt(5, p.getNumHijos());
            ps.setString(6, p.getOcupacion());
            ps.setString(8, p.getTelefono());
            ps.executeUpdate();
            System.out.println("✅ Paciente agregado correctamente.");

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar paciente: " + e.getMessage());
        }
    }

    //listar todos los pacientes
    public List<Paciente> listarPacientes() {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM pacientes";

        try (Connection con = ConexionBD.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Paciente p = new Paciente();
                p.setId(rs.getInt("id_paciente"));
                p.setNombre(rs.getString("nombre"));
                p.setEdad(rs.getInt("edad"));
                p.setSexo(rs.getString("sexo"));
                p.setEstadoCivil(rs.getString("estado_civil"));
                p.setNumHijos(rs.getInt("num_hijos"));
                p.setOcupacion(rs.getString("ocupacion"));
                p.setTelefono(rs.getString("telefono"));
                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al listar pacientes: " + e.getMessage());
        }

        return lista;
    }


 
 
    public List<Paciente> buscarPorNombre(String nombre) {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM pacientes WHERE nombre LIKE ?";
        
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + nombre + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Paciente p = new Paciente();
                p.setId(rs.getInt("id_paciente"));
                p.setNombre(rs.getString("nombre"));
                p.setEdad(rs.getInt("edad"));
                p.setSexo(rs.getString("sexo"));
                p.setEstadoCivil(rs.getString("estado_civil"));
                p.setNumHijos(rs.getInt("num_hijos"));
                p.setOcupacion(rs.getString("ocupacion"));
                p.setTelefono(rs.getString("telefono"));
                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar paciente: " + e.getMessage());
        }

        return lista;
    }


    
    public void eliminarPaciente(int id) {
        String sql = "DELETE FROM pacientes WHERE id_paciente = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("🗑️ Paciente eliminado correctamente.");

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar paciente: " + e.getMessage());
        }
    }
}
