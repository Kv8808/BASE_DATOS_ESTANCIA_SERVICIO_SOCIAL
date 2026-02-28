package src;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class ListaPacientesForm extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;
    private ListaPacientesDAO dao;
    private JTextField txtBuscar;
    private JComboBox<String> comboFiltro;
    private TableRowSorter<DefaultTableModel> sorter;
    private JFrame menuPadre; 

    public ListaPacientesForm(JFrame menu) {
        this.menuPadre = menu; 

        setTitle("LISTA COMPLETA DE PACIENTES");

        
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setSize(2150, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        dao = new ListaPacientesDAO(ConexionBD.getConexion());

        
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel lblBuscar = new JLabel("BUSCAR:");
        lblBuscar.setFont(new Font("Segoe UI", Font.BOLD, 14));

        txtBuscar = new JTextField(30);

        comboFiltro = new JComboBox<>(new String[]{
                "PACIENTE",
                "CUIDADOR",
                "REINGRESO"
        });

        JButton btnBuscar = new JButton("BUSCAR");
        btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 12));

        top.add(lblBuscar);
        top.add(txtBuscar);
        top.add(comboFiltro);
        top.add(btnBuscar);

        add(top, BorderLayout.NORTH);

        
        modelo = new DefaultTableModel(new String[]{
                "ID", "NOMBRE", "EDAD", "SEXO", "ESTADO CIVIL", "HIJOS",
                "OCUPACIÓN", "COLONIA", "MUNICIPIO", "ESTADO", "CP", "LOCALIDAD",
                "TELÉFONO", "DIAGNÓSTICO", "TRATAMIENTO", "DURACIÓN", "HOSPITAL",
                "FECHA INGRESO", "OBSERVACIONES",
                "CUIDADOR NOMBRE", "CUIDADOR EDAD", "CUIDADOR SEXO",
                "CUIDADOR EST. CIVIL", "CUIDADOR HIJOS", "CUIDADOR OCUPACIÓN",
                "CUIDADOR CALLE Y NÚMERO", "CUIDADOR MUNICIPIO", "CUIDADOR ESTADO", "CUIDADOR CP",
                "CUIDADOR LOCALIDAD", "CUIDADOR TELÉFONO", "CUIDADOR PARENTESCO",
                "FECHA CUIDADOR",
                "REINGRESO FECHA", "REINGRESO MOTIVO", "REINGRESO CUIDADOR"
        }, 0) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        tabla = new JTable(modelo);
        tabla.setRowHeight(26);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        
        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {

                Component comp = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                if (!isSelected) {
                    if (column <= 18)
                        comp.setBackground(new Color(220,235,255));   
                    else if (column <= 32)
                        comp.setBackground(new Color(220,255,220));   
                    else
                        comp.setBackground(new Color(255,250,210));   
                }

                comp.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                return comp;
            }
        });

        
        sorter = new TableRowSorter<>(modelo);
        tabla.setRowSorter(sorter);

        txtBuscar.addActionListener(e -> aplicarFiltro());
        btnBuscar.addActionListener(e -> aplicarFiltro());

        
        cargarDatos();
        ajustarColumnas();

        add(new JScrollPane(tabla), BorderLayout.CENTER);

        
        JButton btnSalir = new JButton("SALIR");
        btnSalir.addActionListener(e -> {
            this.dispose();
            if (menuPadre != null) {
                menuPadre.setVisible(true);
            }
        });

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.add(btnSalir);

        add(bottom, BorderLayout.SOUTH);
    }

    
    private void aplicarFiltro() {
        int[] columnas;
        switch (comboFiltro.getSelectedIndex()) {
            case 0: columnas = new int[]{1}; break;
            case 1: columnas = new int[]{19}; break;
            default: columnas = new int[]{33,34,35};
        }

        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscar.getText(), columnas));
    }

    
    private void ajustarColumnas() {
        TableColumnModel c = tabla.getColumnModel();
        for (int i = 0; i < c.getColumnCount(); i++)
            c.getColumn(i).setPreferredWidth(160);

        c.getColumn(1).setPreferredWidth(260);
        c.getColumn(18).setPreferredWidth(320);
        c.getColumn(25).setPreferredWidth(260);
        c.getColumn(34).setPreferredWidth(260);
    }

    
    private void cargarDatos() {
        modelo.setRowCount(0);
        for (Object[] fila : dao.obtenerTodoCompleto())
            modelo.addRow(fila);
    }
}
