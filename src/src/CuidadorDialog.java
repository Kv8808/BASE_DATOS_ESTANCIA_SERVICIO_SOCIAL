package src;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.*;

public class CuidadorDialog extends JDialog {

    private static final Color AZUL_MARINO = new Color(0, 40, 90);
    private static final Font FONT_LABEL = new Font("Segoe UI", Font.BOLD, 13);
    private static final Font FONT_BOTON = new Font("Segoe UI", Font.BOLD, 14);

    private boolean okPressed = false;

    private JTextField txtNombre, txtEdad, txtNumeroHijos, txtOcupacion,
            txtCalleNumero, txtColonia, txtCodigoPostal, txtLocalidad,
            txtMunicipio, txtEstado, txtTelefono, txtParentesco;

    private JComboBox<String> comboSexo, comboEstadoCivil;
    private JDateChooser dateFechaIngreso;

    public CuidadorDialog(JFrame parent) {
        super(parent, "AGREGAR CUIDADOR", true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));

        JPanel contenido = new JPanel(new GridBagLayout());
        contenido.setBorder(BorderFactory.createEmptyBorder(12, 12, 8, 12));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        
        txtNombre = new JTextField();
        dateFechaIngreso = new JDateChooser(); dateFechaIngreso.setDateFormatString("dd/MM/yyyy");
        txtEdad = new JTextField();
        comboSexo = new JComboBox<>(new String[]{"FEMENINO","MASCULINO","OTRO"});
        comboEstadoCivil = new JComboBox<>(new String[]{"SOLTERO","CASADO","UNIÓN LIBRE","OTRO"});
        txtNumeroHijos = new JTextField();
        txtOcupacion = new JTextField();
        txtCalleNumero = new JTextField();
        txtColonia = new JTextField();
        txtCodigoPostal = new JTextField();
        txtLocalidad = new JTextField();
        txtMunicipio = new JTextField();
        txtEstado = new JTextField();
        txtTelefono = new JTextField();
        txtParentesco = new JTextField();

        Dimension fieldPref = new Dimension(260, 26);
        for (JComponent f : new JComponent[]{
                txtNombre, dateFechaIngreso, txtEdad, comboSexo, comboEstadoCivil,
                txtNumeroHijos, txtOcupacion, txtCalleNumero, txtColonia,
                txtCodigoPostal, txtLocalidad, txtMunicipio, txtEstado,
                txtTelefono, txtParentesco}) {
            f.setPreferredSize(fieldPref);
        }

        int row = 0;
        

        
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0; contenido.add(label("Nombre"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0; contenido.add(txtNombre, gbc); row++;

        
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0; contenido.add(label("Fecha ingreso"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0; contenido.add(dateFechaIngreso, gbc); row++;

        
        gbc.gridx = 0; gbc.gridy = row; contenido.add(label("Edad"), gbc);
        gbc.gridx = 1; contenido.add(txtEdad, gbc); row++;

        
        gbc.gridx = 0; gbc.gridy = row; contenido.add(label("Sexo"), gbc);
        gbc.gridx = 1; contenido.add(comboSexo, gbc); row++;

        
        gbc.gridx = 0; gbc.gridy = row; contenido.add(label("Estado civil"), gbc);
        gbc.gridx = 1; contenido.add(comboEstadoCivil, gbc); row++;

        
        gbc.gridx = 0; gbc.gridy = row; contenido.add(label("Calle y número"), gbc);
        gbc.gridx = 1; contenido.add(txtCalleNumero, gbc); row++;

        
        gbc.gridx = 0; gbc.gridy = row; contenido.add(label("Número de hijos"), gbc);
        gbc.gridx = 1; contenido.add(txtNumeroHijos, gbc); row++;

        
        gbc.gridx = 0; gbc.gridy = row; contenido.add(label("Ocupación"), gbc);
        gbc.gridx = 1; contenido.add(txtOcupacion, gbc); row++;

        
        gbc.gridx = 0; gbc.gridy = row; contenido.add(label("Colonia"), gbc);
        gbc.gridx = 1; contenido.add(txtColonia, gbc); row++;

        
        gbc.gridx = 0; gbc.gridy = row; contenido.add(label("Código postal"), gbc);
        gbc.gridx = 1; contenido.add(txtCodigoPostal, gbc); row++;

        
        gbc.gridx = 0; gbc.gridy = row; contenido.add(label("Localidad"), gbc);
        gbc.gridx = 1; contenido.add(txtLocalidad, gbc); row++;

        
        gbc.gridx = 0; gbc.gridy = row; contenido.add(label("Municipio"), gbc);
        gbc.gridx = 1; contenido.add(txtMunicipio, gbc); row++;

        
        gbc.gridx = 0; gbc.gridy = row; contenido.add(label("Estado"), gbc);
        gbc.gridx = 1; contenido.add(txtEstado, gbc); row++;

        
        gbc.gridx = 0; gbc.gridy = row; contenido.add(label("Teléfono"), gbc);
        gbc.gridx = 1; contenido.add(txtTelefono, gbc); row++;

        
        gbc.gridx = 0; gbc.gridy = row; contenido.add(label("Parentesco"), gbc);
        gbc.gridx = 1; contenido.add(txtParentesco, gbc); row++;

        add(contenido, BorderLayout.CENTER);

        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 8));
        JButton btnCancelar = new JButton("CANCELAR");
        JButton btnAceptar = new JButton("ACEPTAR");
        btnAceptar.setFont(FONT_BOTON); btnCancelar.setFont(FONT_BOTON);

        btnAceptar.addActionListener(e -> { okPressed = true; dispose(); });
        btnCancelar.addActionListener(e -> { okPressed = false; dispose(); });

        panelBotones.add(btnCancelar); panelBotones.add(btnAceptar);
        add(panelBotones, BorderLayout.SOUTH);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    private JLabel label(String texto) {
        JLabel lbl = new JLabel(texto.toUpperCase());
        lbl.setFont(FONT_LABEL);
        lbl.setForeground(AZUL_MARINO);
        return lbl;
    }

    public boolean isOkPressed() { return okPressed; }

    public Cuidador getCuidador() {
        Cuidador c = new Cuidador();
        try {
            c.setNombre(txtNombre.getText().trim());
            c.setFechaIngreso(dateFechaIngreso.getDate());
            String sEdad = txtEdad.getText().trim();
            c.setEdad(sEdad.isEmpty() ? 0 : Integer.parseInt(sEdad));
            c.setSexo(comboSexo.getSelectedItem().toString());
            c.setEstadoCivil(comboEstadoCivil.getSelectedItem().toString());
            String sHijos = txtNumeroHijos.getText().trim();
            c.setNumeroHijos(sHijos.isEmpty() ? 0 : Integer.parseInt(sHijos));
            c.setOcupacion(txtOcupacion.getText().trim());
            c.setCalleNumero(txtCalleNumero.getText().trim());
            c.setColonia(txtColonia.getText().trim());
            c.setCodigoPostal(txtCodigoPostal.getText().trim());
            c.setLocalidad(txtLocalidad.getText().trim());
            c.setMunicipio(txtMunicipio.getText().trim());
            c.setEstado(txtEstado.getText().trim());
            c.setTelefono(txtTelefono.getText().trim());
            c.setParentesco(txtParentesco.getText().trim());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "ERROR EN LOS DATOS DEL CUIDADOR");
            return null;
        }
        return c;
    }
}
