package src;

import javax.swing.*;
import java.awt.*;

public class CuidadorForm extends JFrame {

    private JTextField txtNombre, txtEdad, txtTelefono, txtDireccion;

    public CuidadorForm() {
        setTitle("Registro de Cuidador");
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
        btnGuardar.addActionListener(e -> guardarCuidador());
        add(btnGuardar);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        add(btnCerrar);

        setVisible(true);
    }

    private void guardarCuidador() {
        JOptionPane.showMessageDialog(this, "Cuidador guardado correctamente.");
        dispose();
    }
}

