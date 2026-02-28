package src;

import javax.swing.*;
import java.awt.*;

public class PacienteForm extends JFrame {

    private JTextField txtNombre, txtEdad, txtTelefono, txtDireccion;

    public PacienteForm() {
        setTitle("Registro de Paciente");
        setSize(400, 300);
        setLayout(new GridLayout(6, 2, 10, 10));
        setLocationRelativeTo(null);

        
        add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("Edad:"));
        txtEdad = new JTextField();
        add(txtEdad);

        add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        add(txtTelefono);

        add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        add(txtDireccion);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarPaciente());
        add(btnGuardar);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        add(btnCerrar);

        setVisible(true);
    }

    private void guardarPaciente() {
        String nombre = txtNombre.getText().trim();
        int edad = Integer.parseInt(txtEdad.getText().trim());
        String telefono = txtTelefono.getText().trim();
        String direccion = txtDireccion.getText().trim();

        Paciente p = new Paciente();
        p.setNombre(nombre);
        p.setEdad(edad);
        p.setTelefono(telefono);

        PacienteDAO dao = new PacienteDAO();
        dao.agregarPaciente(p);
        JOptionPane.showMessageDialog(this, "Paciente guardado correctamente.");
        dispose();
    }
}
