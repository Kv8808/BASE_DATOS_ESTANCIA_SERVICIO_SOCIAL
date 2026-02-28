package src;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu extends JFrame {

    public MainMenu() {

        
        setTitle("MENÚ PRINCIPAL - ESTANCIA LUPITA");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        
        JPanel fondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(220, 235, 250),
                        0, getHeight(), new Color(180, 210, 235)
                );
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        fondo.setLayout(new GridBagLayout());
        setContentPane(fondo);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            }
        };

        card.setOpaque(false);
        card.setLayout(new GridBagLayout());
        card.setPreferredSize(new Dimension(520, 420));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(12, 20, 12, 20);
        c.fill = GridBagConstraints.HORIZONTAL;

        
        JLabel titulo = new JLabel("ESTANCIA LUPITA", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 36)); // ELEGANTE
        titulo.setForeground(new Color(0, 40, 90));

        c.gridx = 0;
        c.gridy = 0;
        card.add(titulo, c);

        
        JLabel subtitulo = new JLabel("BASE DE DATOS", SwingConstants.CENTER);
        subtitulo.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20)); 
        subtitulo.setForeground(new Color(70, 70, 70));


        c.gridy++;
        card.add(subtitulo, c);

        
        c.gridy++;
        card.add(crearBotonMedico("INGRESO DIARIO", e -> {
            dispose();
            new IngresoDiarioForm(null).setVisible(true);
        }), c);

        
        c.gridy++;
        card.add(crearBotonMedico("VER PACIENTES", e -> {
            setVisible(false);
            new ListaPacientesForm(this).setVisible(true);
        }), c);

        
        c.gridy++;
        card.add(crearBotonMedico("SALIR", e -> System.exit(0)), c);

        fondo.add(card, gbc);

        setVisible(true);
    }

    private JButton crearBotonMedico(String texto, java.awt.event.ActionListener action) {

        Color azulNormal = new Color(120, 180, 230);
        Color azulHover = new Color(90, 160, 220);
        Color azulTexto = new Color(0, 40, 90);

        JButton btn = new JButton(texto.toUpperCase()) {

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 35, 35);

                super.paintComponent(g2);
                g2.dispose();
            }

            @Override
            public void setBorder(Border border) {
                
            }
        };

        
        btn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));

        btn.setForeground(azulTexto);
        btn.setBackground(azulNormal);
        btn.setPreferredSize(new Dimension(420, 65));
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(azulHover);
                btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(azulNormal);
                btn.setCursor(Cursor.getDefaultCursor());
            }
        });

        btn.addActionListener(action);
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainMenu::new);
    }
}
