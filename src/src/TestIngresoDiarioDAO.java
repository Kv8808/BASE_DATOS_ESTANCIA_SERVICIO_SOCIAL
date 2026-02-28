package src;

import java.util.List;

public class TestIngresoDiarioDAO {
    public static void main(String[] args) {
        try {
            IngresoDiarioDAO dao = new IngresoDiarioDAO();

            // Buscar pacientes cuyo nombre contenga "Juan"
            String nombreBuscar = "pedro";
            List<IngresoDiario> pacientes = dao.buscarPorNombrePaciente(nombreBuscar);

            if (pacientes.isEmpty()) {
                System.out.println("No se encontraron pacientes con nombre que contenga: " + nombreBuscar);
            } else {
                for (IngresoDiario paciente : pacientes) {
                    System.out.println("Paciente: " + paciente.getNombrePaciente() + ", ID: " + paciente.getIdPaciente());
                    System.out.println("Edad: " + paciente.getEdad() + ", Sexo: " + paciente.getSexo());
                    System.out.println("Diagnóstico: " + paciente.getDiagnostico());
                    System.out.println("Fecha ingreso: " + paciente.getFechaIngreso());
                    System.out.println("Observaciones: " + paciente.getObservaciones());

                    // Mostrar cuidadores
                    List<Cuidador> cuidadores = paciente.getCuidadores();
                    System.out.println("Cuidadores encontrados: " + (cuidadores != null ? cuidadores.size() : 0));
                    if (cuidadores != null && !cuidadores.isEmpty()) {
                        for (Cuidador c : cuidadores) {
                            System.out.println("  Cuidador: " + c.getNombre() + ", Teléfono: " + c.getTelefono() + ", Parentesco: " + c.getParentesco());
                        }
                    }

                    // Mostrar reingresos
                    List<Reingreso> reingresos = paciente.getReingresos();
                    System.out.println("Reingresos encontrados: " + (reingresos != null ? reingresos.size() : 0));
                    if (reingresos != null && !reingresos.isEmpty()) {
                        for (Reingreso r : reingresos) {
                            System.out.println("  Reingreso: Fecha=" + r.getFecha() + ", Motivo=" + r.getMotivo() + ", Cuidador=" + r.getCuidador());
                        }
                    }

                    System.out.println("---------------------------------------------------");
                }
            }

            dao.cerrarConexion();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
