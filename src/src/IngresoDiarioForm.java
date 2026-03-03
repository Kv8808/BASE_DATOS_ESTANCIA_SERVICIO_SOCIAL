package src;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.awt.Desktop;
import java.io.File;
import java.nio.file.Files;




public class IngresoDiarioForm extends JFrame {
	

    private JTextField txtNombrePaciente;
    private JDateChooser dateFechaIngreso;
    private JTextField txtEdad;
    private JComboBox<String> comboSexo;
    private JComboBox<String> comboEstadoCivil;
    private JTextField txtNumeroHijos;
    private JTextField txtOcupacion;
    private JTextField txtColonia;
    private JTextField txtCodigoPostal;
    private JTextField txtLocalidad;
    private JTextField txtMunicipio;
    private JTextField txtEstado;
    private JTextField txtDiagnostico;
    private JTextField txtTratamiento;
    private JTextField txtDuracion;
    private JTextField txtHospital;
    private JTextField txtTelefono;
    private JTextArea areaObservaciones;
    private JTextField txtIdPaciente;
    private static final Color COLOR_AZUL_MARINO = new Color(0, 40, 90);
    private static final Color COLOR_NEGRO = Color.BLACK;
    private static final Font FONT_SECTION_TITLE = new Font("Segoe UI", Font.BOLD, 20);
    private static final Font FONT_LABEL = new Font("Segoe UI", Font.BOLD, 14);
    private static final Font FONT_BOTON_DOC = new Font("Segoe UI", Font.BOLD, 14);
    private static final Dimension SIZE_BOTON_DOC = new Dimension(175, 30);




    private DefaultTableModel modelCuidadores;
    private JTable tablaCuidadores;
    private JButton btnAgregarCuidador;

    private DefaultTableModel modelReingresos;
    private JTable tablaReingresos;
    private JButton btnAgregarReingreso;

    private JButton btnGuardar;
    private JButton btnBuscar;
    private JButton btnSubirDoc;
    private JButton btnVerDoc;

    private JTextField txtBuscarNombre;

    private IngresoDiarioDAO ingresoDAO;
    
    private int idPacienteActual = 0;
    private JLabel lblPagina;



    
    public IngresoDiarioForm(Connection conn) {
        super("Ingreso Diario");

        try {
            ingresoDAO = new IngresoDiarioDAO(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "ERROR AL INICIALIZAR CONEXIÓN DAO:\n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }
    
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1100, 750);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        add(panelPrincipal);

        panelPrincipal.add(crearPanelPaciente(), BorderLayout.NORTH);
        panelPrincipal.add(crearPanelTablas(), BorderLayout.CENTER);
        panelPrincipal.add(crearPanelBotones(), BorderLayout.SOUTH);
        
        mostrarSiguientePagina();

        setVisible(true);
    }
    
    private JLabel label(String texto) {
        JLabel lbl = new JLabel(texto.toUpperCase());
        lbl.setFont(FONT_LABEL);
        lbl.setForeground(COLOR_NEGRO);
        return lbl;
    }


    
    private JPanel crearPanelPaciente() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(
        	    BorderFactory.createTitledBorder(
        	        BorderFactory.createEtchedBorder(),
        	        "DATOS DEL PACIENTE",
        	        TitledBorder.LEFT,
        	        TitledBorder.TOP,
        	        FONT_SECTION_TITLE,
        	        COLOR_AZUL_MARINO
        	    )
        	);




        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridy = 0;

        gbc.gridx = 0;
        gbc.gridwidth = 1;
        panel.add(label("NOMBRE PACIENTE:"), gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 3; 
        txtNombrePaciente = new JTextField(28);
        panel.add(txtNombrePaciente, gbc);

        gbc.gridx = 4;
        gbc.gridwidth = 1;
        panel.add(label("FECHA INGRESO:"), gbc);

        gbc.gridx = 5;
        gbc.gridwidth = 1;
        dateFechaIngreso = new JDateChooser();
        dateFechaIngreso.setDateFormatString("dd/MM/yyyy");
        dateFechaIngreso.setPreferredSize(new Dimension(120, 28));
        panel.add(dateFechaIngreso, gbc);


        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(label("EDAD:"), gbc);

        gbc.gridx = 1;
        txtEdad = new JTextField(5);
        panel.add(txtEdad, gbc);

        gbc.gridx = 2;
        panel.add(label("SEXO:"), gbc);

        gbc.gridx = 3;
        comboSexo = new JComboBox<>(new String[]{"FEMENINO", "MASCULINO", "OTRO"});
        panel.add(comboSexo, gbc);

        gbc.gridx = 4;
        panel.add(label("ESTADO CIVIL:"), gbc);

        gbc.gridx = 5;
        comboEstadoCivil = new JComboBox<>(new String[]{"SOLTERO", "CASADO", "UNION LIBRE", "OTRO"});
        panel.add(comboEstadoCivil, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(label("NUMERO HIJOS:"), gbc);

        gbc.gridx = 1;
        txtNumeroHijos = new JTextField(5);
        panel.add(txtNumeroHijos, gbc);

        gbc.gridx = 2;
        panel.add(label("OCUPACION:"), gbc);

        gbc.gridx = 3;
        gbc.gridwidth = 3;
        txtOcupacion = new JTextField(15);
        panel.add(txtOcupacion, gbc);

        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(label("CALLE Y NUMERO:"), gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        txtColonia = new JTextField(20);
        panel.add(txtColonia, gbc);

        gbc.gridwidth = 1;

        gbc.gridx = 3;
        panel.add(label("CODIGO POSTAL:"), gbc);

        gbc.gridx = 4;
        txtCodigoPostal = new JTextField(10);
        panel.add(txtCodigoPostal, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(label("LOCALIDAD:"), gbc);

        gbc.gridx = 1;
        txtLocalidad = new JTextField(15);
        panel.add(txtLocalidad, gbc);

        gbc.gridx = 2;
        panel.add(label("MUNICIPIO:"), gbc);

        gbc.gridx = 3;
        txtMunicipio = new JTextField(15);
        panel.add(txtMunicipio, gbc);

        gbc.gridx = 4;
        panel.add(label("ESTADO:"), gbc);

        gbc.gridx = 5;
        txtEstado = new JTextField(10);
        panel.add(txtEstado, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(label("DIAGNOSTICO:"), gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        txtDiagnostico = new JTextField(20);
        panel.add(txtDiagnostico, gbc);

        gbc.gridwidth = 1;

        gbc.gridx = 3;
        panel.add(label("TRATAMIENTO:"), gbc);

        gbc.gridx = 4;
        gbc.gridwidth = 2;
        txtTratamiento = new JTextField(20);
        panel.add(txtTratamiento, gbc);

        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(label("DURACION (DIAS):"), gbc);

        gbc.gridx = 1;
        txtDuracion = new JTextField(5);
        panel.add(txtDuracion, gbc);

        gbc.gridx = 2;
        panel.add(label("HOSPITAL:"), gbc);

        gbc.gridx = 3;
        gbc.gridwidth = 3;
        txtHospital = new JTextField(20);
        panel.add(txtHospital, gbc);

        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(label("TELEFONO:"), gbc);

        gbc.gridx = 1;
        txtTelefono = new JTextField(15);
        panel.add(txtTelefono, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 7;
        panel.add(label("ID PACIENTE:"), gbc);

        gbc.gridx = 3;
        txtIdPaciente = new JTextField(10);
        txtIdPaciente.setEditable(false);
        panel.add(txtIdPaciente, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panel.add(label("OBSERVACIONES:"), gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        areaObservaciones = new JTextArea(5, 40);
        JScrollPane scrollObs = new JScrollPane(areaObservaciones);
        panel.add(scrollObs, gbc);
        
     gbc.gridx = 0;
     gbc.gridy = 9;
     gbc.gridwidth = 6;
     gbc.fill = GridBagConstraints.HORIZONTAL;
     gbc.weightx = 0;
     gbc.weighty = 0;

     JPanel panelDocsPaciente = new JPanel(new FlowLayout(FlowLayout.RIGHT));

     btnSubirDoc = new JButton("SUBIR DOCUMENTO");
     btnVerDoc = new JButton("VER DOCUMENTOS");

     btnSubirDoc.setFont(FONT_BOTON_DOC);
     btnVerDoc.setFont(FONT_BOTON_DOC);

     btnSubirDoc.setPreferredSize(SIZE_BOTON_DOC);
     btnVerDoc.setPreferredSize(SIZE_BOTON_DOC);

     panelDocsPaciente.add(btnSubirDoc);
     panelDocsPaciente.add(btnVerDoc);
     btnSubirDoc.addActionListener(e -> subirDocumentoPaciente());
     btnVerDoc.addActionListener(e -> verDocumentosDelPaciente());

     panel.add(panelDocsPaciente, gbc);
     

        return panel;
    }
    
 private void subirDocumentoPaciente() {
     try {
         if (idPacienteActual <= 0) {
             JOptionPane.showMessageDialog(this,
                 "Guarda primero el paciente antes de subir documentos.");
             return;
         }

         JFileChooser chooser = new JFileChooser();
         int sel = chooser.showOpenDialog(this);
         if (sel != JFileChooser.APPROVE_OPTION) return;

         File file = chooser.getSelectedFile();

         byte[] bytes = java.nio.file.Files.readAllBytes(file.toPath());
         String mime = java.nio.file.Files.probeContentType(file.toPath());
         if (mime == null) mime = "application/octet-stream";

         String tipoDoc = JOptionPane.showInputDialog(this,
                 "Tipo de documento (INE, AUTORIZACION, OTRO):",
                 "TIPO DOCUMENTO",
                 JOptionPane.QUESTION_MESSAGE);

         if (tipoDoc == null) tipoDoc = "OTRO";

         Documento d = new Documento();
         d.setIdPaciente(idPacienteActual);
         d.setNombreOriginal(file.getName());
         d.setTipoDocumento(tipoDoc);
         d.setMime(mime);
         d.setContenido(bytes);
         d.setTamanio(bytes.length);
         d.setReferenciaNombre("PACIENTE");

         DocumentoDAO docDAO = new DocumentoDAO(ingresoDAO.getConnection());
         int idDoc = docDAO.guardarDocumento(d);

         JOptionPane.showMessageDialog(this,
                 "Documento subido correctamente (ID=" + idDoc + ")");

     } catch (Exception ex) {
         ex.printStackTrace();
         JOptionPane.showMessageDialog(this,
                 "ERROR SUBIENDO DOCUMENTO: " + ex.getMessage());
     }
 }
    

    
    private JPanel crearPanelTablas() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(
        	    BorderFactory.createTitledBorder(
        	        BorderFactory.createEtchedBorder(),
        	        "CUIDADORES Y REINGRESOS",
        	        TitledBorder.LEFT,
        	        TitledBorder.TOP,
        	        FONT_SECTION_TITLE,
        	        COLOR_AZUL_MARINO
        	    )
        	);




        modelCuidadores = new DefaultTableModel(
                new String[]{
                        "NOMBRE", "FECHA", "EDAD", "SEXO", "ESTADO CIVIL",
                        "HIJOS", "OCUPACION", "CALLE", "CODIGO POSTAL",
                        "LOCALIDAD", "MUNICIPIO", "ESTADO", "TELEFONO", "PARENTESCO"
                }, 0);

        tablaCuidadores = new JTable(modelCuidadores);
        tablaCuidadores.setFont(new Font("Segoe UI", Font.BOLD, 12));
        tablaCuidadores.setRowHeight(20);

        tablaCuidadores.getTableHeader()
            .setFont(new Font("Segoe UI", Font.BOLD, 13));


        tablaCuidadores.getTableHeader()
            .setFont(new Font("Segoe UI", Font.BOLD, 13));
        JScrollPane scrollCuidadores = new JScrollPane(tablaCuidadores);

        btnAgregarCuidador = new JButton("AGREGAR CUIDADOR");
        btnAgregarCuidador.addActionListener(e -> agregarCuidador());
        btnAgregarCuidador.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnAgregarCuidador.setPreferredSize(new Dimension(220, 36));


        JPanel panelBotonesCuidadores = new JPanel(new FlowLayout(FlowLayout.RIGHT));


        JPanel panelCuidadores = new JPanel(new BorderLayout());
        panelCuidadores.add(btnAgregarCuidador, BorderLayout.NORTH);
        panelCuidadores.add(scrollCuidadores, BorderLayout.CENTER);
        panelCuidadores.add(panelBotonesCuidadores, BorderLayout.SOUTH);
        

        modelReingresos = new DefaultTableModel(
                new String[]{"FECHA", "MOTIVO", "CUIDADOR"}, 0);

        tablaReingresos = new JTable(modelReingresos);
        tablaReingresos.setFont(new Font("Segoe UI", Font.BOLD, 12));
        tablaReingresos.setRowHeight(20);

        tablaReingresos.getTableHeader()
            .setFont(new Font("Segoe UI", Font.BOLD, 13));


        tablaReingresos.getTableHeader()
            .setFont(new Font("Segoe UI", Font.BOLD, 13));
        JScrollPane scrollReingresos = new JScrollPane(tablaReingresos);

        btnAgregarReingreso = new JButton("AGREGAR REINGRESO");
        btnAgregarReingreso.addActionListener(e -> agregarReingreso());
        btnAgregarReingreso.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnAgregarReingreso.setPreferredSize(new Dimension(220, 36));


        JPanel panelBotonesReingresos = new JPanel(new FlowLayout(FlowLayout.RIGHT));



        JPanel panelReingresos = new JPanel(new BorderLayout());
        panelReingresos.add(btnAgregarReingreso, BorderLayout.NORTH);
        panelReingresos.add(scrollReingresos, BorderLayout.CENTER);
        panelReingresos.add(panelBotonesReingresos, BorderLayout.SOUTH);
        
       
        JPanel panelCentro = new JPanel(new GridLayout(2, 1, 0, 10));
        panelCentro.add(panelCuidadores);
        panelCentro.add(panelReingresos);

        panel.add(panelCentro, BorderLayout.CENTER);

        return panel;
    }


    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        lblPagina = new JLabel("PAGINA: 0");
        panel.add(lblPagina);

        
        panel.add(label("BUSCAR PACIENTE:"));
        txtBuscarNombre = new JTextField(15);
        txtBuscarNombre.setToolTipText("Escribe el nombre del paciente y pulsa BUSCAR");
        panel.add(txtBuscarNombre);

        btnBuscar = new JButton("BUSCAR");
        btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnBuscar.setPreferredSize(new Dimension(100, 30));
        btnBuscar.addActionListener(e -> buscarPaciente());
        panel.add(btnBuscar);

        btnGuardar = new JButton("GUARDAR");
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnGuardar.setPreferredSize(new Dimension(120, 30));
        btnGuardar.addActionListener(e -> guardarIngreso());
        panel.add(btnGuardar);

        JButton btnNuevoRegistro = new JButton("NUEVO");
        btnNuevoRegistro.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnNuevoRegistro.setPreferredSize(new Dimension(100, 30));
        btnNuevoRegistro.addActionListener(e -> limpiarFormulario());
        panel.add(btnNuevoRegistro);

        JButton btnAnterior = new JButton("←");
        btnAnterior.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnAnterior.setPreferredSize(new Dimension(50, 30));
        btnAnterior.addActionListener(e -> irAlAnterior());
        panel.add(btnAnterior);

        JButton btnSiguiente = new JButton("→");
        btnSiguiente.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnSiguiente.setPreferredSize(new Dimension(50, 30));
        btnSiguiente.addActionListener(e -> irAlSiguiente());
        panel.add(btnSiguiente);

        UIManager.put("OptionPane.yesButtonText", "SI");
        UIManager.put("OptionPane.noButtonText", "NO");

        JButton btnSalirMenu = new JButton("SALIR");
        btnSalirMenu.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnSalirMenu.setPreferredSize(new Dimension(100, 30));
        btnSalirMenu.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿SEGUR@ QUE DESEA SALIR?",
                "CONFIRMAR SALIDA",
                JOptionPane.YES_NO_OPTION
            );

            if (opcion == JOptionPane.YES_OPTION) {
                dispose();
                new MainMenu();
            }
        });
        panel.add(btnSalirMenu);

        return panel;
    }


    
    private void irAlAnterior() {
        try {
            int ultimoId = ingresoDAO.obtenerUltimoId();
            if (ultimoId <= 0) {
                JOptionPane.showMessageDialog(this, "NO HAY MAS REGISTROS EN LA BASE DE DATOS.");
                return;
            }
            int idBuscar = (idPacienteActual <= 0) ? ultimoId : idPacienteActual - 1;

            while (idBuscar >= 1) {
                IngresoDiario ingreso = ingresoDAO.buscarPorId(idBuscar);
                if (ingreso != null) {
                    cargarIngresoEnFormulario(ingreso);
                    idPacienteActual = idBuscar;
                    txtIdPaciente.setText(String.valueOf(idPacienteActual));
                    actualizarPagina();
                    return;
                }
                idBuscar--;
            }

            JOptionPane.showMessageDialog(this, "NO HAY MAS REGISTROS ANTERIORES.");

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "ERROR BUSCANDO ANTERIOR: " + ex.getMessage());
        }
    }

    private void irAlSiguiente() {
        try {
            int ultimoId = ingresoDAO.obtenerUltimoId();
            if (ultimoId <= 0) {
                JOptionPane.showMessageDialog(this, "NO HAY REGISTROS EN LA BASE DE DATOS.");
                return;
            }

            int idBuscar = (idPacienteActual <= 0) ? 1 : idPacienteActual + 1;
            while (idBuscar <= ultimoId) {
                IngresoDiario ingreso = ingresoDAO.buscarPorId(idBuscar);
                if (ingreso != null) {
                    cargarIngresoEnFormulario(ingreso);
                    idPacienteActual = idBuscar;
                    txtIdPaciente.setText(String.valueOf(idPacienteActual));
                    actualizarPagina();
                    return;
                }
                idBuscar++;
            }

            JOptionPane.showMessageDialog(this, "NO HAY MAS REGISTROS SIGUIENTES.");

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "ERROR BUSCANDO SIGUIENTE: " + ex.getMessage());
        }
    }

    
    private void actualizarPagina() throws SQLException {
        int total = ingresoDAO.contarPacientes();
        lblPagina.setText("Página: " + idPacienteActual + " DE " + total);
    }




    private void agregarCuidador() {
        CuidadorDialog dialog = new CuidadorDialog(this);
        dialog.setVisible(true);

        if (dialog.isOkPressed()) {
            Cuidador c = dialog.getCuidador();
            if (c == null) {
                JOptionPane.showMessageDialog(this, "Datos del cuidador inválidos o incompletos.", "ERROR", JOptionPane.WARNING_MESSAGE);
                return;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String fechaStr = (c.getFechaIngreso() != null) ? sdf.format(c.getFechaIngreso()) : "";
            modelCuidadores.addRow(new Object[]{
                c.getNombre(),
                fechaStr,
                c.getEdad(),
                c.getSexo(),
                c.getEstadoCivil(),
                c.getNumeroHijos(),
                c.getOcupacion(),
                c.getCalleNumero(),
                c.getCodigoPostal(),
                c.getLocalidad(),
                c.getMunicipio(),
                c.getEstado(),
                c.getTelefono(),
                c.getParentesco()
            });
        }
    }
	

    private void agregarReingreso() {
        ReingresoDialog dialog = new ReingresoDialog(this);
        dialog.setVisible(true);

        if (dialog.isOkPressed()) {
            Reingreso r = dialog.getReingreso();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            modelReingresos.addRow(new Object[]{
                sdf.format(r.getFecha()),
                r.getMotivo(),
                r.getCuidador()
            });
        }
    }

    private void buscarPaciente() {
        String nombreBuscar = txtBuscarNombre.getText().trim();
        if (nombreBuscar.isEmpty()) {
            JOptionPane.showMessageDialog(this, "INTRODUCE UN NOMBRE PARA BUSCAR.");
            return;
        }

        try {
            List<IngresoDiario> lista = ingresoDAO.buscarPorNombrePaciente(nombreBuscar);

            if (lista == null || lista.isEmpty()) {
                JOptionPane.showMessageDialog(this, "NO SE ENCONTRARON PACIENTES CON ESE NOMBRE.");
                return;
            }

            IngresoDiario ingreso = lista.get(0);

            System.out.println("Paciente encontrado: " + ingreso.getNombrePaciente());
            System.out.println("Número de cuidadores: " + 
                (ingreso.getCuidadores() != null ? ingreso.getCuidadores().size() : "null"));
            System.out.println("Número de reingresos: " + 
                (ingreso.getReingresos() != null ? ingreso.getReingresos().size() : "null"));

            lblPagina.setText("Página: " + ingreso.getIdPaciente());
            cargarIngresoEnFormulario(ingreso);

            JOptionPane.showMessageDialog(this, "PACIENTE ENCONTRADO.");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "ERROR AL BUSCAR PACIENTE: " + ex.getMessage());
            ex.printStackTrace();
        }
    }



    private void cargarIngresoEnFormulario(IngresoDiario ingreso) throws SQLException {
    	idPacienteActual = ingreso.getIdPaciente(); //guarda id en variable
    	txtIdPaciente.setText(String.valueOf(idPacienteActual)); //muestra en el campo
    	actualizarPagina();
        txtNombrePaciente.setText(ingreso.getNombrePaciente());
        dateFechaIngreso.setDate(ingreso.getFechaIngreso());
        txtEdad.setText(String.valueOf(ingreso.getEdad()));
        comboSexo.setSelectedItem(ingreso.getSexo());
        comboEstadoCivil.setSelectedItem(ingreso.getEstadoCivil());
        txtNumeroHijos.setText(String.valueOf(ingreso.getNumeroHijos()));
        txtOcupacion.setText(ingreso.getOcupacion());
        txtColonia.setText(ingreso.getColonia());
        txtCodigoPostal.setText(String.valueOf(ingreso.getCodigoPostal()));
        txtLocalidad.setText(ingreso.getLocalidad());
        txtMunicipio.setText(ingreso.getMunicipio());
        txtEstado.setText(ingreso.getEstado());
        txtDiagnostico.setText(ingreso.getDiagnostico());
        txtTratamiento.setText(ingreso.getTratamiento());
        txtDuracion.setText(String.valueOf(ingreso.getDuracion()));
        txtHospital.setText(ingreso.getHospital());
        txtTelefono.setText(ingreso.getTelefono());
        areaObservaciones.setText(ingreso.getObservaciones());
        modelCuidadores.setRowCount(0);
        System.out.println("LIMPIO TABLA CUIDADORES. FILAS ACTUALES: " + modelCuidadores.getRowCount());

        if (ingreso.getCuidadores() != null && !ingreso.getCuidadores().isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            for (Cuidador c : ingreso.getCuidadores()) {
                System.out.println("AGREGANDO CUIDADOR: " + c.getNombre());

                try {
                	modelCuidadores.addRow(new Object[]{
                		    c.getNombre(),
                		    sdf.format(c.getFechaIngreso()),
                		    c.getEdad(),
                		    c.getSexo(),
                		    c.getEstadoCivil(),
                		    c.getNumeroHijos(),
                		    c.getOcupacion(),
                		    c.getCalleNumero(),
                		    c.getCodigoPostal(),
                		    c.getLocalidad(),
                		    c.getMunicipio(),
                		    c.getEstado(),
                		    c.getTelefono(),
                		    c.getParentesco()
                		});
                } catch (Exception e) {
                    System.err.println("⚠️ ERROR AL AGREGAR CUIDADOR: " + e.getMessage());
                }
            }

            System.out.println("CUIDADORES AGREGADOS: " + modelCuidadores.getRowCount());
        } else {
            System.out.println("❗ NO SE ENCONTRARON CUIDADORES.");
        }

        modelReingresos.setRowCount(0);

        if (ingreso.getReingresos() != null && !ingreso.getReingresos().isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            for (Reingreso r : ingreso.getReingresos()) {
                System.out.println("AGREGANDO REINGRESO DEL CUIDADOR: " + r.getCuidador());

                try {
                    modelReingresos.addRow(new Object[]{
                    	    sdf.format(r.getFecha()),
                    	    r.getMotivo(),
                    	    r.getCuidador(),
                    	    "",
                    	    ""
                    	});
                } catch (Exception e) {
                    System.err.println("⚠️ ERROR AL INGRESAR REINGRRESO: " + e.getMessage());
                }
            }

            System.out.println("REINGRESOS AGREGADOS: " + modelReingresos.getRowCount());
        } else {
            System.out.println("❗ NO SE ENCONTRARON REINGRESOS.");
        }
    }


    private void guardarIngreso() {
        try {
            IngresoDiario ingreso = new IngresoDiario();
            ingreso.setIdPaciente(idPacienteActual);
            ingreso.setNombrePaciente(txtNombrePaciente.getText());
            ingreso.setFechaIngreso(dateFechaIngreso.getDate());
            ingreso.setEdad(txtEdad.getText().isEmpty() ? 0 : Integer.parseInt(txtEdad.getText()));
            ingreso.setSexo(comboSexo.getSelectedItem().toString());
            ingreso.setEstadoCivil(comboEstadoCivil.getSelectedItem().toString());
            ingreso.setNumeroHijos(txtNumeroHijos.getText().isEmpty() ? 0 : Integer.parseInt(txtNumeroHijos.getText()));
            ingreso.setOcupacion(txtOcupacion.getText());
            ingreso.setColonia(txtColonia.getText());
            ingreso.setCodigoPostal(txtCodigoPostal.getText().isEmpty() ? 0 : Integer.parseInt(txtCodigoPostal.getText()));
            ingreso.setLocalidad(txtLocalidad.getText());
            ingreso.setMunicipio(txtMunicipio.getText());
            ingreso.setEstado(txtEstado.getText());
            ingreso.setDiagnostico(txtDiagnostico.getText());
            ingreso.setTratamiento(txtTratamiento.getText());
            ingreso.setDuracion(txtDuracion.getText());
            ingreso.setHospital(txtHospital.getText());
            ingreso.setTelefono(txtTelefono.getText());
            ingreso.setObservaciones(areaObservaciones.getText());

            
            List<Cuidador> listaCuidadores = new ArrayList<>();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            for (int i = 0; i < modelCuidadores.getRowCount(); i++) {
                Cuidador c = new Cuidador();
                c.setNombre(modelCuidadores.getValueAt(i, 0).toString());
                c.setFechaIngreso(sdf.parse(modelCuidadores.getValueAt(i, 1).toString()));
                c.setEdad(Integer.parseInt(modelCuidadores.getValueAt(i, 2).toString()));
                c.setSexo(modelCuidadores.getValueAt(i, 3).toString());
                c.setEstadoCivil(modelCuidadores.getValueAt(i, 4).toString());
                c.setNumeroHijos(Integer.parseInt(modelCuidadores.getValueAt(i, 5).toString()));
                c.setOcupacion(modelCuidadores.getValueAt(i, 6).toString());
                Object val7 = modelCuidadores.getValueAt(i, 7);
                c.setCalleNumero(val7 == null ? "" : val7.toString());
                Object val8 = modelCuidadores.getValueAt(i, 8);
                c.setCodigoPostal(val8 == null ? "" : val8.toString());
                c.setLocalidad(modelCuidadores.getValueAt(i, 9).toString());
                c.setMunicipio(modelCuidadores.getValueAt(i, 10).toString());
                c.setEstado(modelCuidadores.getValueAt(i, 11).toString());
                c.setTelefono(modelCuidadores.getValueAt(i, 12).toString());
                c.setParentesco(modelCuidadores.getValueAt(i, 13).toString());
                listaCuidadores.add(c);
            }
            ingreso.setCuidadores(listaCuidadores);

            
            List<Reingreso> listaReingresos = new ArrayList<>();
            for (int i = 0; i < modelReingresos.getRowCount(); i++) {
                Reingreso r = new Reingreso();
                r.setFecha(sdf.parse(modelReingresos.getValueAt(i, 0).toString()));
                r.setMotivo(modelReingresos.getValueAt(i, 1).toString());
                r.setCuidador(modelReingresos.getValueAt(i, 2).toString());
                listaReingresos.add(r);
            }
            ingreso.setReingresos(listaReingresos);

            
            int idGenerado = ingresoDAO.guardarIngreso(ingreso);

            
            idPacienteActual = idGenerado;
            txtIdPaciente.setText(String.valueOf(idGenerado));
            actualizarPagina();

            
            JOptionPane.showMessageDialog(this,
                "INGRESO GUARDADO CORRECTAMENTE.\nPAGINA ASIGNADA: " + idGenerado);

            
            String[] opciones = {"Subir documento", "Nuevo (limpiar)", "Mantener datos"};
            int sel = JOptionPane.showOptionDialog(
                this,
                "¿Qué deseas hacer ahora?",
                "Siguiente acción",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
            );

            if (sel == 0) {
                
                subirDocumentoPaciente();
            } else if (sel == 1) {
                limpiarFormulario();
            } else {
                
            }

           

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "ERROR AL GUARDAR: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
 private void verDocumentosDelPaciente() {
        try {
            int idPaciente = idPacienteActual;
            if (idPaciente <= 0) {
                JOptionPane.showMessageDialog(this, "No hay paciente seleccionado.");
                return;
            }
            DocumentoDAO docDAO = new DocumentoDAO(ingresoDAO.getConnection());
            java.util.List<Documento> docs = docDAO.listarPorPaciente(idPaciente);
            if (docs.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron documentos para este paciente.");
                return;
            }

            String[] opciones = new String[docs.size()];
            for (int i = 0; i < docs.size(); i++) {
                Documento d = docs.get(i);
                opciones[i] = String.format("[%d] %s - %s %s", d.getId(), d.getTipoDocumento(), d.getNombreOriginal(),
                        d.getReferenciaNombre() != null ? ("(" + d.getReferenciaNombre() + ")") : "");
            }

            String sel = (String) JOptionPane.showInputDialog(this, "Seleccione documento para abrir:", "Documentos", JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
            if (sel == null) return;
            int idDoc = Integer.parseInt(sel.substring(sel.indexOf('[')+1, sel.indexOf(']')));

            Documento doc = docDAO.obtenerContenido(idDoc);
            if (doc == null || doc.getContenido() == null) {
                JOptionPane.showMessageDialog(this, "No se encontró contenido del documento.");
                return;
            }

            File tmp = File.createTempFile("doc_"+idDoc+"_", "_" + doc.getNombreOriginal());
            Files.write(tmp.toPath(), doc.getContenido());
            tmp.deleteOnExit();

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(tmp);
            } else {
                JOptionPane.showMessageDialog(this, "Documento guardado temporalmente en: " + tmp.getAbsolutePath());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "ERROR AL VER DOCUMENTO: " + ex.getMessage());
        }
    }

    
    private void limpiarFormulario() {
        idPacienteActual = 0;
        lblPagina.setText("PAGINA: 0");
        txtIdPaciente.setText("");
        txtNombrePaciente.setText("");
        dateFechaIngreso.setDate(null);
        txtEdad.setText("");
        comboSexo.setSelectedIndex(0);
        comboEstadoCivil.setSelectedIndex(0);
        txtNumeroHijos.setText("");
        txtOcupacion.setText("");
        txtColonia.setText("");
        txtCodigoPostal.setText("");
        txtLocalidad.setText("");
        txtMunicipio.setText("");
        txtEstado.setText("");
        txtDiagnostico.setText("");
        txtTratamiento.setText("");
        txtDuracion.setText("");
        txtHospital.setText("");
        txtTelefono.setText("");
        areaObservaciones.setText("");

        
        modelCuidadores.setRowCount(0);
        modelReingresos.setRowCount(0);
        
        
        idPacienteActual = 0;
        
     
        mostrarSiguientePagina();
        

        JOptionPane.showMessageDialog(this, "FORMULARIO LISTO PARA NUEVO REGISTRO.");
    }
    
    private void mostrarSiguientePagina() {
        try {
            
            int ultimoId = ingresoDAO.obtenerUltimoId();

            
            int siguienteId = ultimoId + 1;

            
            lblPagina.setText("Página: " + siguienteId);
            txtIdPaciente.setText(String.valueOf(siguienteId));

            
            idPacienteActual = 0;

        } catch (Exception e) {
            lblPagina.setText("PAGINA: ?");
            System.err.println("ERROR AL OBTENER SIGUIENTE PAGINA: " + e.getMessage());
        }
    }
    
    
    public void cargarPorId(int id) {
        try {
            IngresoDiario ingreso = ingresoDAO.buscarPorId(id);
            if (ingreso != null) {
                cargarIngresoEnFormulario(ingreso);
            } else {
                JOptionPane.showMessageDialog(this, "NO SE ENCONTRO EL PACIENTE CON: " + id);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "ERROR AL CARGAR PACIENTE: " + ex.getMessage());
        }
    }
    
    public void cargarPacient(int idPaciente) {
        try {
            IngresoDiario ingreso = ingresoDAO.buscarPorId(idPaciente);
            if (ingreso != null) {
                cargarIngresoEnFormulario(ingreso);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "ERROR AL CARGAR PACIENTE: " + ex.getMessage());
        }
    }
    //
    





    public static void main(String[] args) {
        try {
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/estancia_lupita", "root", "");

            SwingUtilities.invokeLater(() -> {
                new IngresoDiarioForm(conn);
            });

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERROR AL CONECTAR A LA BASE DE DATOS: " + ex.getMessage());
        }
    }
}