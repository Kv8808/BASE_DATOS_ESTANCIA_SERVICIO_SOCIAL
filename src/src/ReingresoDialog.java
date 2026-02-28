package src;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.*;

public class ReingresoDialog extends JDialog {

    private static final Color AZUL_MARINO = new Color(0, 40, 90);
    private static final Font FONT_LABEL = new Font("Segoe UI", Font.BOLD, 13);
    private static final Font FONT_BOTON = new Font("Segoe UI", Font.BOLD, 14);

    private boolean okPressed = false;
    private JDateChooser dateFecha;
    private JTextField txtMotivo, txtCuidador;

    public ReingresoDialog(JFrame parent) {
        super(parent, "AGREGAR REINGRESO", true);
        setSize(420, 260);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        JPanel panel = new JPanel(new GridLayout(3, 2, 8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(label("Fecha"));
        dateFecha = new JDateChooser();
        dateFecha.setDateFormatString("dd/MM/yyyy");
        panel.add(dateFecha);

        panel.add(label("Motivo"));
        txtMotivo = new JTextField();
        panel.add(txtMotivo);

        panel.add(label("Cuidador"));
        txtCuidador = new JTextField();
        panel.add(txtCuidador);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        JButton btnCancelar = new JButton("CANCELAR");
        JButton btnAceptar = new JButton("ACEPTAR");

        btnAceptar.setFont(FONT_BOTON);
        btnCancelar.setFont(FONT_BOTON);

        btnAceptar.addActionListener(e -> {
            okPressed = true;
            dispose();
        });

        btnCancelar.addActionListener(e -> dispose());

        panelBotones.add(btnCancelar);
        panelBotones.add(btnAceptar);

        add(panel, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private JLabel label(String texto) {
        JLabel lbl = new JLabel(texto.toUpperCase());
        lbl.setFont(FONT_LABEL);
        lbl.setForeground(AZUL_MARINO);
        return lbl;
    }

    public boolean isOkPressed() {
        return okPressed;
    }


public Reingreso getReingreso() {
    Reingreso r = new Reingreso();
    try {
        r.setFecha(dateFecha.getDate());
        r.setMotivo(txtMotivo.getText().trim());
        r.setCuidador(txtCuidador.getText().trim());
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "ERROR EN LOS DATOS DEL REINGRESO");
        return null;
    }
    return r;
}
}
