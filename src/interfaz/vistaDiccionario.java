package interfaz;

import conexion.Conexion;

import modelo.Vocablo;
import modelo.Acepcion;
import modelo.Sinonimo;
import modelo.Antonimo;
import modelo.Derivado;

import controlador.VocabloControlador;
import controlador.AcepcionControlador;
import controlador.SinonimoControlador;
import controlador.AntonimoControlador;
import controlador.DerivadoControlador;
import controlador.ConsultaControlador;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;


/**
 * Interfaz para el uso del diccionario.
 */
public class vistaDiccionario extends javax.swing.JFrame {
    
    private final String usuario = "root";
    private final String contrasena = "Perrita79@";
    
    private boolean admin = false;
    
    private Conexion cnx = new Conexion();
    
    private VocabloControlador vbc = new VocabloControlador(cnx);
    private AcepcionControlador apc = new AcepcionControlador(cnx);
    private AntonimoControlador anc = new AntonimoControlador(cnx);
    private SinonimoControlador snc = new SinonimoControlador(cnx);
    private DerivadoControlador drc = new DerivadoControlador(cnx);
    private ConsultaControlador ccc = new ConsultaControlador(cnx);
    
    
    /**
     * Nueva interfaz.
     */
    public vistaDiccionario() {
        
        initComponents();
        cnx.conectar();
        
        this.setTitle("MySQL-Diccionario");
        this.setLocationRelativeTo(null);
        
        //Ventana de inicio de sesión.
        
        ingreso.setTitle("Iniciar sesión");
        ingreso.setLocationRelativeTo(null);
        ingreso.setSize(245, 200);
        
        //Ventana con la lista completa de vocablos.
        
        mostrarVocablos.setTitle("Búsqueda de vocablos");
        mostrarVocablos.setLocation(300,150);
        mostrarVocablos.setSize(245, 240);
        
        // Se desactivan los componentes usados para
        // el manejo del diccionario por parte 
        // del administrador hasta que inicie sesión.    
        
        panel1.setEnabled(admin);
        panel2.setEnabled(admin);
        panel3.setEnabled(admin);
        
        edicion.setEnabled(admin);
        edicionVocablo.setEnabled(admin);
        etiquetaVocablo.setEnabled(admin);
        edicionAcepcion.setEnabled(admin);
        etiquetaAcepcion.setEnabled(admin);
        edicionEjemplo.setEnabled(admin);
        etiquetaEjemplo.setEnabled(admin);
        edicionRelacionada.setEnabled(admin);
        etiquetaRelacionada.setEnabled(admin);
        
        listaCategorias.setEnabled(admin);
        listaRelacionadas.setEnabled(admin);
        esSoez.setEnabled(admin);
        
        agregarVocablo.setEnabled(admin);
        editarVocablo.setEnabled(admin);
        borrarVocablo.setEnabled(admin);
        agregarAcepción.setEnabled(admin);
        editarAcepción.setEnabled(admin);
        borrarAcepción.setEnabled(admin);
        agregarRelacionada.setEnabled(admin);
        borrarRelacionada.setEnabled(admin);
        
        acciones.setEnabled(admin);
        mensaje.setEnabled(admin);
        
        panel4.setEnabled(admin);
        panelMostrarVocablos.setEnabled(admin);
        mostrarVocablo.setEnabled(admin);
        mostrarVocablos.setEnabled(admin);
        listaVocablosTodo.setEnabled(admin);
        anuncioListaVocablos.setEnabled(admin);
        
        buscarTodo.setEnabled(admin);
        refrescarVocablos.setEnabled(admin);
        
        panel5.setEnabled(admin);
        panel6.setEnabled(admin);
        panel7.setEnabled(admin);
        
        especial.setEnabled(admin);
        consultaTexto.setEnabled(admin);
        consultaEspecial.setEnabled(admin);
        informacion.setEnabled(admin);
        tabla.setEnabled(admin);
        
    }
    
    
    /**
     * Búsqueda de toda la información de un vocablo.
     * @param vocablo Vocablo a buscar.
     */
    private void buscar(String vocablo){
        
        if(vocablo.replace(" ", "").equals("")){
                
                acepcionesVocablo.setText("");
                sinonimosVocablo.setText("");
                antonimosVocablo.setText("");
                derivadosVocablo.setText("");
                indicaCategoria.setText("");
                indicaSoez.setText("Vocablo soez");
                
        } else {
        
            ArrayList<Vocablo> vocablos = vbc.mostrarVocablos();
            Vocablo u = new Vocablo(vocablo, "", "");
        
            // Si el vocablo se encuentra en el diccionario,
            // se procede a buscar toda su información.
        
            if(vocablos.contains(u)){
            
                ArrayList<Acepcion> acepcionesv = apc.acepcionesVocablo(vocablo);    
                ArrayList<Sinonimo> sinonimosv = snc.sinonimosVocablo(vocablo);
                ArrayList<Antonimo> antonimosv = anc.antonimosVocablo(vocablo);
                ArrayList<Derivado> derivadosv = drc.derivadosVocablo(vocablo);
             
                String acepciones = ""; String derivados = "";
                String sinonimos = ""; String antonimos = "";
            
                for(Acepcion acepcion: acepcionesv){           
                    acepciones += acepcion.toString();           
                }
            
                for(Sinonimo sinonimo: sinonimosv){            
                    sinonimos += sinonimo.toString();           
                }
            
                for(Antonimo antonimo: antonimosv){           
                    antonimos += antonimo.toString();           
                }
            
                for(Derivado derivado: derivadosv){            
                    derivados += derivado.toString();            
                }
            
                // Se plasma la información obtenida, 
                // en la interfaz.
            
                acepcionesVocablo.setText(acepciones);
                sinonimosVocablo.setText(sinonimos);
                antonimosVocablo.setText(antonimos);
                derivadosVocablo.setText(derivados);
            
                Vocablo v = vocablos.get(vocablos.indexOf(u));
            
                indicaCategoria.setText(v.getCategoria());
            
                if(u.isEs_soez().equals("si")){            
                    indicaSoez.setText("Vocablo soez");                
                } else {                
                    indicaSoez.setText("Vocablo no soez");            
                }
            
            } else {
                
                edicionVocablo1.setText("El vocablo no existe");
                
            }
    
        }
            
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ingreso = new javax.swing.JFrame();
        panelIngreso = new javax.swing.JPanel();
        contrasenaAdmin = new javax.swing.JPasswordField();
        usuarioAdmin = new javax.swing.JTextField();
        ingresarAdmin = new javax.swing.JButton();
        cancelarAdmin = new javax.swing.JButton();
        anuncioAdmin = new javax.swing.JTextField();
        mostrarVocablos = new javax.swing.JFrame();
        panelMostrarVocablos = new javax.swing.JPanel();
        mostrarVocablo = new javax.swing.JButton();
        refrescarVocablos = new javax.swing.JButton();
        anuncioListaVocablos = new javax.swing.JTextField();
        panel4 = new javax.swing.JScrollPane();
        listaVocablosTodo = new javax.swing.JList<>();
        principal = new javax.swing.JPanel();
        ventanas = new javax.swing.JTabbedPane();
        consulta = new javax.swing.JPanel();
        etiquetaVocablo1 = new javax.swing.JTextField();
        edicionVocablo1 = new javax.swing.JTextField();
        buscar = new javax.swing.JButton();
        etiquetaSinonimo = new javax.swing.JTextField();
        etiquetaAntonimo = new javax.swing.JTextField();
        etiquetaDerivados = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        sinonimosVocablo = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        derivadosVocablo = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        antonimosVocablo = new javax.swing.JTextArea();
        diccionarioUrbano = new javax.swing.JTextField();
        loginAdmin = new javax.swing.JButton();
        logoutAdmin = new javax.swing.JButton();
        salir = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        bienvenida = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        acepcionesVocablo = new javax.swing.JTextArea();
        acepcionesEjemplos = new javax.swing.JTextField();
        indicaSoez = new javax.swing.JTextField();
        indicaCategoria = new javax.swing.JTextField();
        buscarTodo = new javax.swing.JButton();
        edicion = new javax.swing.JPanel();
        edicionVocablo = new javax.swing.JTextField();
        etiquetaVocablo = new javax.swing.JTextField();
        etiquetaRelacionada = new javax.swing.JTextField();
        edicionRelacionada = new javax.swing.JTextField();
        panel1 = new javax.swing.JScrollPane();
        listaRelacionadas = new javax.swing.JList<>();
        panel2 = new javax.swing.JScrollPane();
        listaCategorias = new javax.swing.JList<>();
        agregarVocablo = new javax.swing.JButton();
        borrarVocablo = new javax.swing.JButton();
        esSoez = new javax.swing.JRadioButton();
        editarVocablo = new javax.swing.JButton();
        agregarRelacionada = new javax.swing.JButton();
        borrarRelacionada = new javax.swing.JButton();
        edicionAcepcion = new javax.swing.JTextField();
        etiquetaAcepcion = new javax.swing.JTextField();
        etiquetaEjemplo = new javax.swing.JTextField();
        edicionEjemplo = new javax.swing.JTextField();
        agregarAcepción = new javax.swing.JButton();
        editarAcepción = new javax.swing.JButton();
        borrarAcepción = new javax.swing.JButton();
        acciones = new javax.swing.JTextField();
        panel3 = new javax.swing.JScrollPane();
        mensaje = new javax.swing.JTextArea();
        especial = new javax.swing.JPanel();
        panel5 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        panel6 = new javax.swing.JScrollPane();
        consultaTexto = new javax.swing.JTextArea();
        consultaEspecial = new javax.swing.JButton();
        panel7 = new javax.swing.JScrollPane();
        informacion = new javax.swing.JTextArea();

        ingreso.setResizable(false);

        ingresarAdmin.setBackground(new java.awt.Color(204, 204, 255));
        ingresarAdmin.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ingresarAdmin.setForeground(new java.awt.Color(0, 0, 204));
        ingresarAdmin.setText("Ingresar");
        ingresarAdmin.setMaximumSize(new java.awt.Dimension(90, 25));
        ingresarAdmin.setMinimumSize(new java.awt.Dimension(90, 25));
        ingresarAdmin.setPreferredSize(new java.awt.Dimension(85, 25));
        ingresarAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingresarAdminActionPerformed(evt);
            }
        });

        cancelarAdmin.setBackground(new java.awt.Color(204, 204, 255));
        cancelarAdmin.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cancelarAdmin.setForeground(new java.awt.Color(255, 51, 51));
        cancelarAdmin.setText("Volver");
        cancelarAdmin.setMaximumSize(new java.awt.Dimension(85, 25));
        cancelarAdmin.setMinimumSize(new java.awt.Dimension(85, 25));
        cancelarAdmin.setPreferredSize(new java.awt.Dimension(85, 25));
        cancelarAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarAdminActionPerformed(evt);
            }
        });

        anuncioAdmin.setEditable(false);
        anuncioAdmin.setBackground(new java.awt.Color(204, 204, 255));
        anuncioAdmin.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        anuncioAdmin.setForeground(new java.awt.Color(0, 0, 102));
        anuncioAdmin.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        anuncioAdmin.setText("Administrador MySQL");

        javax.swing.GroupLayout panelIngresoLayout = new javax.swing.GroupLayout(panelIngreso);
        panelIngreso.setLayout(panelIngresoLayout);
        panelIngresoLayout.setHorizontalGroup(
            panelIngresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIngresoLayout.createSequentialGroup()
                .addGroup(panelIngresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelIngresoLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(ingresarAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cancelarAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelIngresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(panelIngresoLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(contrasenaAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelIngresoLayout.createSequentialGroup()
                            .addGap(45, 45, 45)
                            .addComponent(usuarioAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelIngresoLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(anuncioAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        panelIngresoLayout.setVerticalGroup(
            panelIngresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelIngresoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(anuncioAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(usuarioAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contrasenaAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelIngresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ingresarAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(cancelarAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout ingresoLayout = new javax.swing.GroupLayout(ingreso.getContentPane());
        ingreso.getContentPane().setLayout(ingresoLayout);
        ingresoLayout.setHorizontalGroup(
            ingresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelIngreso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ingresoLayout.setVerticalGroup(
            ingresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ingresoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        mostrarVocablos.setAlwaysOnTop(true);
        mostrarVocablos.setFocusable(false);
        mostrarVocablos.setResizable(false);

        mostrarVocablo.setBackground(new java.awt.Color(204, 204, 255));
        mostrarVocablo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        mostrarVocablo.setForeground(new java.awt.Color(0, 0, 204));
        mostrarVocablo.setText("Mostrar");
        mostrarVocablo.setMaximumSize(new java.awt.Dimension(90, 25));
        mostrarVocablo.setMinimumSize(new java.awt.Dimension(90, 25));
        mostrarVocablo.setPreferredSize(new java.awt.Dimension(85, 25));
        mostrarVocablo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarVocabloActionPerformed(evt);
            }
        });

        refrescarVocablos.setBackground(new java.awt.Color(204, 204, 255));
        refrescarVocablos.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        refrescarVocablos.setForeground(new java.awt.Color(0, 0, 204));
        refrescarVocablos.setText("Refrescar");
        refrescarVocablos.setMaximumSize(new java.awt.Dimension(85, 25));
        refrescarVocablos.setMinimumSize(new java.awt.Dimension(85, 25));
        refrescarVocablos.setPreferredSize(new java.awt.Dimension(85, 25));
        refrescarVocablos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refrescarVocablosActionPerformed(evt);
            }
        });

        anuncioListaVocablos.setEditable(false);
        anuncioListaVocablos.setBackground(new java.awt.Color(204, 204, 255));
        anuncioListaVocablos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        anuncioListaVocablos.setForeground(new java.awt.Color(0, 0, 102));
        anuncioListaVocablos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        anuncioListaVocablos.setText("Lista de vocablos");

        listaVocablosTodo.setBackground(new java.awt.Color(245, 245, 255));
        listaVocablosTodo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        listaVocablosTodo.setForeground(new java.awt.Color(102, 102, 255));
        listaVocablosTodo.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listaVocablosTodo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listaVocablosTodo.setMaximumSize(new java.awt.Dimension(200, 400));
        listaVocablosTodo.setMinimumSize(new java.awt.Dimension(105, 170));
        listaVocablosTodo.setPreferredSize(new java.awt.Dimension(100, 170));
        panel4.setViewportView(listaVocablosTodo);

        javax.swing.GroupLayout panelMostrarVocablosLayout = new javax.swing.GroupLayout(panelMostrarVocablos);
        panelMostrarVocablos.setLayout(panelMostrarVocablosLayout);
        panelMostrarVocablosLayout.setHorizontalGroup(
            panelMostrarVocablosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMostrarVocablosLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(panelMostrarVocablosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelMostrarVocablosLayout.createSequentialGroup()
                        .addComponent(mostrarVocablo, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(refrescarVocablos, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(anuncioListaVocablos, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                    .addComponent(panel4))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        panelMostrarVocablosLayout.setVerticalGroup(
            panelMostrarVocablosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMostrarVocablosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(anuncioListaVocablos, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel4, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelMostrarVocablosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mostrarVocablo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(refrescarVocablos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout mostrarVocablosLayout = new javax.swing.GroupLayout(mostrarVocablos.getContentPane());
        mostrarVocablos.getContentPane().setLayout(mostrarVocablosLayout);
        mostrarVocablosLayout.setHorizontalGroup(
            mostrarVocablosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMostrarVocablos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mostrarVocablosLayout.setVerticalGroup(
            mostrarVocablosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mostrarVocablosLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelMostrarVocablos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        consulta.setForeground(new java.awt.Color(153, 204, 255));

        etiquetaVocablo1.setEditable(false);
        etiquetaVocablo1.setBackground(new java.awt.Color(245, 245, 255));
        etiquetaVocablo1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etiquetaVocablo1.setForeground(new java.awt.Color(0, 51, 153));
        etiquetaVocablo1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        etiquetaVocablo1.setText("Vocablo");
        etiquetaVocablo1.setOpaque(false);
        etiquetaVocablo1.setPreferredSize(new java.awt.Dimension(80, 30));

        edicionVocablo1.setBackground(new java.awt.Color(245, 245, 255));
        edicionVocablo1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        edicionVocablo1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edicionVocablo1.setPreferredSize(new java.awt.Dimension(80, 30));

        buscar.setBackground(new java.awt.Color(245, 245, 255));
        buscar.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        buscar.setForeground(new java.awt.Color(0, 0, 204));
        buscar.setText("Buscar");
        buscar.setBorderPainted(false);
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });

        etiquetaSinonimo.setEditable(false);
        etiquetaSinonimo.setBackground(new java.awt.Color(245, 245, 255));
        etiquetaSinonimo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etiquetaSinonimo.setForeground(new java.awt.Color(0, 51, 153));
        etiquetaSinonimo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        etiquetaSinonimo.setText("Sinónimos");
        etiquetaSinonimo.setPreferredSize(new java.awt.Dimension(80, 30));

        etiquetaAntonimo.setEditable(false);
        etiquetaAntonimo.setBackground(new java.awt.Color(245, 245, 255));
        etiquetaAntonimo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etiquetaAntonimo.setForeground(new java.awt.Color(0, 51, 153));
        etiquetaAntonimo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        etiquetaAntonimo.setText("Antónimos");
        etiquetaAntonimo.setPreferredSize(new java.awt.Dimension(80, 30));

        etiquetaDerivados.setEditable(false);
        etiquetaDerivados.setBackground(new java.awt.Color(245, 245, 255));
        etiquetaDerivados.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etiquetaDerivados.setForeground(new java.awt.Color(0, 51, 153));
        etiquetaDerivados.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        etiquetaDerivados.setText("Palabras derivadas");
        etiquetaDerivados.setPreferredSize(new java.awt.Dimension(80, 30));

        sinonimosVocablo.setEditable(false);
        sinonimosVocablo.setBackground(new java.awt.Color(245, 245, 255));
        sinonimosVocablo.setColumns(10);
        sinonimosVocablo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        sinonimosVocablo.setRows(5);
        jScrollPane4.setViewportView(sinonimosVocablo);

        derivadosVocablo.setEditable(false);
        derivadosVocablo.setBackground(new java.awt.Color(245, 245, 255));
        derivadosVocablo.setColumns(10);
        derivadosVocablo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        derivadosVocablo.setRows(5);
        jScrollPane5.setViewportView(derivadosVocablo);

        antonimosVocablo.setEditable(false);
        antonimosVocablo.setBackground(new java.awt.Color(245, 245, 255));
        antonimosVocablo.setColumns(10);
        antonimosVocablo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        antonimosVocablo.setRows(5);
        jScrollPane6.setViewportView(antonimosVocablo);

        diccionarioUrbano.setEditable(false);
        diccionarioUrbano.setBackground(new java.awt.Color(245, 245, 255));
        diccionarioUrbano.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        diccionarioUrbano.setForeground(new java.awt.Color(255, 0, 0));
        diccionarioUrbano.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        diccionarioUrbano.setText("Diccionario Urbano");

        loginAdmin.setBackground(new java.awt.Color(245, 245, 255));
        loginAdmin.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        loginAdmin.setForeground(new java.awt.Color(255, 51, 51));
        loginAdmin.setText("Ingresar Administrador");
        loginAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginAdminActionPerformed(evt);
            }
        });

        logoutAdmin.setBackground(new java.awt.Color(245, 245, 255));
        logoutAdmin.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        logoutAdmin.setForeground(new java.awt.Color(255, 51, 51));
        logoutAdmin.setText("Salir del administrador");
        logoutAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutAdminActionPerformed(evt);
            }
        });

        salir.setBackground(new java.awt.Color(245, 245, 255));
        salir.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        salir.setForeground(new java.awt.Color(255, 51, 51));
        salir.setText("Salir del diccionario");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        bienvenida.setEditable(false);
        bienvenida.setBackground(new java.awt.Color(235, 235, 255));
        bienvenida.setColumns(8);
        bienvenida.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        bienvenida.setForeground(new java.awt.Color(255, 51, 51));
        bienvenida.setRows(5);
        bienvenida.setText("\n Búsqueda de palabras \nen el diccionario urbano.\n \n      Usuario común.");
        bienvenida.setMaximumSize(new java.awt.Dimension(90, 100));
        bienvenida.setMinimumSize(new java.awt.Dimension(70, 80));
        bienvenida.setPreferredSize(new java.awt.Dimension(80, 79));
        jScrollPane7.setViewportView(bienvenida);

        acepcionesVocablo.setColumns(20);
        acepcionesVocablo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        acepcionesVocablo.setRows(5);
        jScrollPane8.setViewportView(acepcionesVocablo);

        acepcionesEjemplos.setBackground(new java.awt.Color(245, 245, 255));
        acepcionesEjemplos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        acepcionesEjemplos.setForeground(new java.awt.Color(0, 0, 153));
        acepcionesEjemplos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        acepcionesEjemplos.setText("Acepciones, ejemplos");

        indicaSoez.setEditable(false);
        indicaSoez.setBackground(new java.awt.Color(245, 245, 255));
        indicaSoez.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        indicaSoez.setForeground(new java.awt.Color(0, 0, 204));
        indicaSoez.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        indicaCategoria.setEditable(false);
        indicaCategoria.setBackground(new java.awt.Color(245, 245, 255));
        indicaCategoria.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        indicaCategoria.setForeground(new java.awt.Color(0, 0, 204));
        indicaCategoria.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        buscarTodo.setBackground(new java.awt.Color(245, 245, 255));
        buscarTodo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        buscarTodo.setForeground(new java.awt.Color(0, 0, 204));
        buscarTodo.setText("Todo");
        buscarTodo.setBorderPainted(false);
        buscarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarTodoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout consultaLayout = new javax.swing.GroupLayout(consulta);
        consulta.setLayout(consultaLayout);
        consultaLayout.setHorizontalGroup(
            consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, consultaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(salir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(diccionarioUrbano)
                    .addComponent(jScrollPane7)
                    .addComponent(logoutAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(loginAdmin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8)
                    .addGroup(consultaLayout.createSequentialGroup()
                        .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(acepcionesEjemplos)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(etiquetaVocablo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(etiquetaSinonimo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(consultaLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(etiquetaAntonimo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(edicionVocablo1, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                                .addGap(10, 10, 10)
                                .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(etiquetaDerivados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(consultaLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(buscarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, consultaLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(indicaSoez)
                                .addGap(10, 10, 10)
                                .addComponent(indicaCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1))))
                    .addGroup(consultaLayout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        consultaLayout.setVerticalGroup(
            consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(consultaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buscarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(consultaLayout.createSequentialGroup()
                        .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(consultaLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(edicionVocablo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(consultaLayout.createSequentialGroup()
                                .addComponent(etiquetaVocablo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(etiquetaSinonimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(etiquetaAntonimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(etiquetaDerivados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(diccionarioUrbano, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                                .addComponent(jScrollPane7)
                                .addComponent(jScrollPane6))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(consultaLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(loginAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(logoutAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(salir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(consultaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(acepcionesEjemplos, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                            .addComponent(indicaSoez, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                            .addComponent(indicaCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );

        ventanas.addTab("Consulta diccionario", consulta);

        edicion.setMaximumSize(new java.awt.Dimension(600, 400));
        edicion.setMinimumSize(new java.awt.Dimension(200, 100));
        edicion.setPreferredSize(new java.awt.Dimension(600, 400));

        edicionVocablo.setBackground(new java.awt.Color(245, 245, 255));
        edicionVocablo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        edicionVocablo.setForeground(new java.awt.Color(0, 51, 153));
        edicionVocablo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edicionVocablo.setPreferredSize(new java.awt.Dimension(80, 30));

        etiquetaVocablo.setEditable(false);
        etiquetaVocablo.setBackground(new java.awt.Color(245, 245, 255));
        etiquetaVocablo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        etiquetaVocablo.setForeground(new java.awt.Color(0, 51, 153));
        etiquetaVocablo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        etiquetaVocablo.setText("Vocablo");
        etiquetaVocablo.setPreferredSize(new java.awt.Dimension(80, 30));

        etiquetaRelacionada.setEditable(false);
        etiquetaRelacionada.setBackground(new java.awt.Color(245, 245, 255));
        etiquetaRelacionada.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        etiquetaRelacionada.setForeground(new java.awt.Color(0, 51, 153));
        etiquetaRelacionada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        etiquetaRelacionada.setText("Palabra relacionada");
        etiquetaRelacionada.setPreferredSize(new java.awt.Dimension(80, 30));

        edicionRelacionada.setBackground(new java.awt.Color(245, 245, 255));
        edicionRelacionada.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        edicionRelacionada.setForeground(new java.awt.Color(0, 51, 153));
        edicionRelacionada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edicionRelacionada.setPreferredSize(new java.awt.Dimension(80, 30));

        listaRelacionadas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        listaRelacionadas.setForeground(new java.awt.Color(102, 102, 255));
        listaRelacionadas.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Sinónimo", "Antónimo", "Derivado" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listaRelacionadas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listaRelacionadas.setAlignmentY(5.0F);
        listaRelacionadas.setMaximumSize(new java.awt.Dimension(100, 80));
        listaRelacionadas.setMinimumSize(new java.awt.Dimension(70, 50));
        listaRelacionadas.setPreferredSize(new java.awt.Dimension(70, 50));
        panel1.setViewportView(listaRelacionadas);

        listaCategorias.setBackground(new java.awt.Color(245, 245, 255));
        listaCategorias.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        listaCategorias.setForeground(new java.awt.Color(102, 102, 255));
        listaCategorias.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Sustantivo", "Adjetivo", "Artículo", "Pronombre", "Verbo", "Adverbio", "Interjección", "Preposición", "Conjunción", " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listaCategorias.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listaCategorias.setMaximumSize(new java.awt.Dimension(200, 400));
        listaCategorias.setMinimumSize(new java.awt.Dimension(105, 170));
        listaCategorias.setPreferredSize(new java.awt.Dimension(100, 170));
        panel2.setViewportView(listaCategorias);

        agregarVocablo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        agregarVocablo.setForeground(new java.awt.Color(0, 0, 153));
        agregarVocablo.setText("Agregar");
        agregarVocablo.setPreferredSize(new java.awt.Dimension(75, 30));
        agregarVocablo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarVocabloActionPerformed(evt);
            }
        });

        borrarVocablo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        borrarVocablo.setForeground(new java.awt.Color(255, 0, 0));
        borrarVocablo.setText("Borrar");
        borrarVocablo.setPreferredSize(new java.awt.Dimension(75, 30));
        borrarVocablo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarVocabloActionPerformed(evt);
            }
        });

        esSoez.setBackground(new java.awt.Color(245, 245, 255));
        esSoez.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        esSoez.setForeground(new java.awt.Color(0, 0, 153));
        esSoez.setText("Es soez");
        esSoez.setMaximumSize(new java.awt.Dimension(100, 75));
        esSoez.setMinimumSize(new java.awt.Dimension(100, 75));

        editarVocablo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        editarVocablo.setForeground(new java.awt.Color(0, 0, 153));
        editarVocablo.setText("Editar");
        editarVocablo.setPreferredSize(new java.awt.Dimension(75, 30));
        editarVocablo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarVocabloActionPerformed(evt);
            }
        });

        agregarRelacionada.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        agregarRelacionada.setForeground(new java.awt.Color(0, 0, 153));
        agregarRelacionada.setText("Agregar");
        agregarRelacionada.setPreferredSize(new java.awt.Dimension(75, 30));
        agregarRelacionada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarRelacionadaActionPerformed(evt);
            }
        });

        borrarRelacionada.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        borrarRelacionada.setForeground(new java.awt.Color(255, 0, 0));
        borrarRelacionada.setText("Borrar");
        borrarRelacionada.setPreferredSize(new java.awt.Dimension(75, 30));
        borrarRelacionada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarRelacionadaActionPerformed(evt);
            }
        });

        edicionAcepcion.setBackground(new java.awt.Color(245, 245, 255));
        edicionAcepcion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        edicionAcepcion.setForeground(new java.awt.Color(0, 51, 153));
        edicionAcepcion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edicionAcepcion.setPreferredSize(new java.awt.Dimension(80, 30));

        etiquetaAcepcion.setEditable(false);
        etiquetaAcepcion.setBackground(new java.awt.Color(245, 245, 255));
        etiquetaAcepcion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        etiquetaAcepcion.setForeground(new java.awt.Color(0, 51, 153));
        etiquetaAcepcion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        etiquetaAcepcion.setText("Acepción");
        etiquetaAcepcion.setPreferredSize(new java.awt.Dimension(80, 30));

        etiquetaEjemplo.setEditable(false);
        etiquetaEjemplo.setBackground(new java.awt.Color(245, 245, 255));
        etiquetaEjemplo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        etiquetaEjemplo.setForeground(new java.awt.Color(0, 51, 153));
        etiquetaEjemplo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        etiquetaEjemplo.setText("Ejemplo");
        etiquetaEjemplo.setPreferredSize(new java.awt.Dimension(80, 30));

        edicionEjemplo.setBackground(new java.awt.Color(245, 245, 255));
        edicionEjemplo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        edicionEjemplo.setForeground(new java.awt.Color(0, 51, 153));
        edicionEjemplo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edicionEjemplo.setPreferredSize(new java.awt.Dimension(80, 30));

        agregarAcepción.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        agregarAcepción.setForeground(new java.awt.Color(0, 0, 153));
        agregarAcepción.setText("Agregar");
        agregarAcepción.setPreferredSize(new java.awt.Dimension(75, 30));
        agregarAcepción.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarAcepciónActionPerformed(evt);
            }
        });

        editarAcepción.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        editarAcepción.setForeground(new java.awt.Color(0, 0, 153));
        editarAcepción.setText("Editar");
        editarAcepción.setPreferredSize(new java.awt.Dimension(75, 30));
        editarAcepción.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarAcepciónActionPerformed(evt);
            }
        });

        borrarAcepción.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        borrarAcepción.setForeground(new java.awt.Color(255, 0, 0));
        borrarAcepción.setText("Borrar");
        borrarAcepción.setPreferredSize(new java.awt.Dimension(75, 30));
        borrarAcepción.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarAcepciónActionPerformed(evt);
            }
        });

        acciones.setEditable(false);
        acciones.setBackground(new java.awt.Color(245, 245, 255));
        acciones.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        acciones.setForeground(new java.awt.Color(102, 102, 255));
        acciones.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        acciones.setText("Acciones");

        mensaje.setBackground(new java.awt.Color(245, 245, 255));
        mensaje.setColumns(20);
        mensaje.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mensaje.setForeground(new java.awt.Color(0, 51, 255));
        mensaje.setLineWrap(true);
        mensaje.setRows(5);
        panel3.setViewportView(mensaje);

        javax.swing.GroupLayout edicionLayout = new javax.swing.GroupLayout(edicion);
        edicion.setLayout(edicionLayout);
        edicionLayout.setHorizontalGroup(
            edicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, edicionLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(edicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(edicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(etiquetaVocablo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(edicionVocablo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(etiquetaRelacionada, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(etiquetaAcepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(edicionAcepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(edicionRelacionada, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(edicionLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(esSoez, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(edicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(edicionLayout.createSequentialGroup()
                        .addGroup(edicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(edicionLayout.createSequentialGroup()
                                .addComponent(etiquetaEjemplo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(agregarAcepción, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(edicionEjemplo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(edicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(borrarAcepción, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editarAcepción, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(edicionLayout.createSequentialGroup()
                        .addGroup(edicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(edicionLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addGroup(edicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(agregarRelacionada, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(borrarRelacionada, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(edicionLayout.createSequentialGroup()
                                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addGroup(edicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(editarVocablo, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                                    .addComponent(agregarVocablo, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                                    .addComponent(borrarVocablo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(21, 21, 21)
                        .addGroup(edicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(acciones, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                            .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addGap(15, 15, 15))
        );
        edicionLayout.setVerticalGroup(
            edicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edicionLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(edicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(edicionLayout.createSequentialGroup()
                        .addGroup(edicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(edicionLayout.createSequentialGroup()
                                .addComponent(etiquetaVocablo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(esSoez, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(edicionVocablo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(edicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(edicionLayout.createSequentialGroup()
                                .addComponent(etiquetaRelacionada, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(edicionRelacionada, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, edicionLayout.createSequentialGroup()
                                .addComponent(agregarRelacionada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(borrarRelacionada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(edicionLayout.createSequentialGroup()
                        .addGroup(edicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(acciones, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(edicionLayout.createSequentialGroup()
                                .addComponent(agregarVocablo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(editarVocablo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(edicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(edicionLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(borrarVocablo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(edicionLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(panel3)))))
                .addGap(20, 20, 20)
                .addGroup(edicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaAcepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiquetaEjemplo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(agregarAcepción, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editarAcepción, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(edicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edicionAcepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edicionEjemplo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(borrarAcepción, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        ventanas.addTab("Edición vocablos", edicion);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Vocablos", "Categoría", "Es soez", "Sinónimos", "Antónimos", "Derivados"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        panel5.setViewportView(tabla);

        consultaTexto.setColumns(20);
        consultaTexto.setFont(new java.awt.Font("Alef", 0, 14)); // NOI18N
        consultaTexto.setRows(5);
        panel6.setViewportView(consultaTexto);

        consultaEspecial.setBackground(new java.awt.Color(245, 245, 255));
        consultaEspecial.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        consultaEspecial.setForeground(new java.awt.Color(153, 153, 255));
        consultaEspecial.setText("Consulta MySQL");
        consultaEspecial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultaEspecialActionPerformed(evt);
            }
        });

        informacion.setColumns(4);
        informacion.setFont(new java.awt.Font("Alef", 0, 12)); // NOI18N
        informacion.setLineWrap(true);
        informacion.setRows(5);
        informacion.setText("\tInformación");
        informacion.setPreferredSize(new java.awt.Dimension(200, 80));
        panel7.setViewportView(informacion);

        javax.swing.GroupLayout especialLayout = new javax.swing.GroupLayout(especial);
        especial.setLayout(especialLayout);
        especialLayout.setHorizontalGroup(
            especialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(especialLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(especialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(especialLayout.createSequentialGroup()
                        .addComponent(consultaEspecial, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panel7))
                    .addComponent(panel5, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                    .addComponent(panel6))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        especialLayout.setVerticalGroup(
            especialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, especialLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(especialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(consultaEspecial, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(panel7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        ventanas.addTab("Consulta especial", especial);

        javax.swing.GroupLayout principalLayout = new javax.swing.GroupLayout(principal);
        principal.setLayout(principalLayout);
        principalLayout.setHorizontalGroup(
            principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, principalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ventanas)
                .addContainerGap())
        );
        principalLayout.setVerticalGroup(
            principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(principalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ventanas, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(principal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(principal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ingresarAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingresarAdminActionPerformed
        
        if((usuarioAdmin.getText().equals(this.usuario)) && 
            contrasenaAdmin.getText().equals(this.contrasena)){
            
            // Cuando el administrador iniciia sesión,
            // se activan las características especiales
            // de la interfaz.
            
            admin = true;
            
            panel1.setEnabled(admin);
            panel2.setEnabled(admin);
            panel3.setEnabled(admin);

            edicion.setEnabled(admin);
            edicionVocablo.setEnabled(admin);
            etiquetaVocablo.setEnabled(admin);
            edicionAcepcion.setEnabled(admin);
            etiquetaAcepcion.setEnabled(admin);
            edicionEjemplo.setEnabled(admin);
            etiquetaEjemplo.setEnabled(admin);
            edicionRelacionada.setEnabled(admin);
            etiquetaRelacionada.setEnabled(admin);

            listaCategorias.setEnabled(admin);
            listaRelacionadas.setEnabled(admin);
            esSoez.setEnabled(admin);

            agregarVocablo.setEnabled(admin);
            editarVocablo.setEnabled(admin);
            borrarVocablo.setEnabled(admin);
            agregarAcepción.setEnabled(admin);
            editarAcepción.setEnabled(admin);
            borrarAcepción.setEnabled(admin);
            agregarRelacionada.setEnabled(admin);
            borrarRelacionada.setEnabled(admin);

            acciones.setEnabled(admin);
            mensaje.setEnabled(admin);

            panel4.setEnabled(admin);
            
            panelMostrarVocablos.setEnabled(admin);
            mostrarVocablo.setEnabled(admin);
            mostrarVocablos.setEnabled(admin);
            listaVocablosTodo.setEnabled(admin);
            anuncioListaVocablos.setEnabled(admin);

            buscarTodo.setEnabled(admin);
            refrescarVocablos.setEnabled(admin);

            panel5.setEnabled(admin);
            panel6.setEnabled(admin);
            panel7.setEnabled(admin);

            especial.setEnabled(admin);
            consultaTexto.setEnabled(admin);
            consultaEspecial.setEnabled(admin);
            informacion.setEnabled(admin);
            tabla.setEnabled(admin);

            bienvenida.setText("\n Búsqueda de palabras \n" +
                               "en el diccionario urbano.\n" +
                               " \n       Administrador.");

            ingreso.setVisible(false);
        
        } else {
            
            // La ventana permanece en espera de que
            // el administrador se identifique.
            
            usuarioAdmin.setText("");
            contrasenaAdmin.setText("");
            anuncioAdmin.setText("Usuario incorrecto");
            
        }
        
    }//GEN-LAST:event_ingresarAdminActionPerformed

    private void cancelarAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarAdminActionPerformed
        
        // Aparece la ventana de inicio de sesión.

        ingreso.setVisible(false);
        
    }//GEN-LAST:event_cancelarAdminActionPerformed

    private void agregarVocabloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarVocabloActionPerformed
        
        String vocablo = edicionVocablo.getText();
        String categoria = listaCategorias.getSelectedValue();
        
        String soez;
        
        if(esSoez.isSelected()){           
            soez = "si";        
        } else {           
            soez = "no";        
        }
        
        if((categoria == null) || vocablo.replace(" ", "").equals("")){            
            return;   
        } else {            
            // Se agrega un vocablo y su información correspondiente.       
            mensaje.setText(vbc.agregar(vocablo, categoria, soez));        
        }
        
    }//GEN-LAST:event_agregarVocabloActionPerformed

    private void borrarVocabloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarVocabloActionPerformed
        
        String vocablo = edicionVocablo.getText();
        
        mensaje.setText(vbc.borrar(vocablo));
        
    }//GEN-LAST:event_borrarVocabloActionPerformed

    private void editarVocabloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarVocabloActionPerformed
            
        String vocablo = edicionVocablo.getText();
        String categoria = listaCategorias.getSelectedValue();
        String soez;
        
        if(esSoez.isSelected()){            
            soez = "si";        
        } else {           
            soez = "no";       
        }
        
        if((categoria == null) || vocablo.replace(" ", "").equals("")){            
            return;    
        } else {        
            // Se edita la información de un vocablo.            
            mensaje.setText(vbc.editar(vocablo, categoria, soez));            
        }
        
    }//GEN-LAST:event_editarVocabloActionPerformed

    private void agregarRelacionadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarRelacionadaActionPerformed
        
        String vocablo = edicionVocablo.getText();
        String palabra = edicionRelacionada.getText();
        String tipoPalabra = listaRelacionadas.getSelectedValue();
        
        if((tipoPalabra == null) || (vocablo.replace(" ", "").equals(""))
                                 || (palabra.replace(" ", "").equals(""))){            
            return;
            
        } else {
            
            // Se agrega una palabra relacionada a cierto vocablo.
        
            switch (tipoPalabra) {
                
                case "Sinónimo":                   
                    mensaje.setText(snc.agregar(palabra, vocablo));                   
                    break;
                    
                case "Antónimo":                  
                    mensaje.setText(anc.agregar(palabra, vocablo));                   
                    break;
                    
                default:                  
                    mensaje.setText(drc.agregar(palabra, vocablo));                   
                    break;
                    
            }
        }
        
    }//GEN-LAST:event_agregarRelacionadaActionPerformed

    private void borrarRelacionadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarRelacionadaActionPerformed
        
        String vocablo = edicionVocablo.getText();
        String palabra = edicionRelacionada.getText();
        String tipoPalabra = listaRelacionadas.getSelectedValue();
        
        if(tipoPalabra == null){
            
            return;
    
        } else {
            
            // Borrado de una palabra relacionada a cierto vocablo.
        
            switch (tipoPalabra) {
                
                case "Sinónimo":                    
                    mensaje.setText(snc.borrar(palabra, vocablo));                    
                    break;
                    
                case "Antónimo":                    
                    mensaje.setText(anc.borrar(palabra, vocablo));                   
                    break;
                    
                default:                    
                    mensaje.setText(drc.borrar(palabra, vocablo));                   
                    break;
                    
            }
        }
        
    }//GEN-LAST:event_borrarRelacionadaActionPerformed

    private void agregarAcepciónActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarAcepciónActionPerformed
        
        String vocablo = edicionVocablo.getText();
        String acepcion = edicionAcepcion.getText();
        String ejemplo = edicionEjemplo.getText();
        
        if((vocablo.replace(" ", "").equals("")) ||
           (acepcion.replace(" ", "").equals("")) ||
           (ejemplo.replace(" ", "").equals(""))){            
            return;    
        } else {       
            mensaje.setText(apc.agregar(acepcion, ejemplo, vocablo));
            
        }
        
    }//GEN-LAST:event_agregarAcepciónActionPerformed

    private void editarAcepciónActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarAcepciónActionPerformed

        String acepcion = edicionAcepcion.getText();
        String ejemplo = edicionEjemplo.getText();
        
        if((acepcion.replace(" ", "").equals("")) ||
           (ejemplo.replace(" ", "").equals(""))){
            return;    
        } else {        
            mensaje.setText(apc.editar(acepcion, ejemplo));            
        }
        
    }//GEN-LAST:event_editarAcepciónActionPerformed

    private void borrarAcepciónActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarAcepciónActionPerformed
        
        String vocablo = edicionVocablo.getText();
        String acepcion = edicionAcepcion.getText();
        String ejemplo = edicionEjemplo.getText();
        
        if(vocablo.replace(" ", "").equals("")){
            return;
        } else {
            mensaje.setText(apc.borrar(acepcion, vocablo));            
        }
        
    }//GEN-LAST:event_borrarAcepciónActionPerformed

    private void loginAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginAdminActionPerformed

        ingreso.setVisible(true);
        
    }//GEN-LAST:event_loginAdminActionPerformed

    private void logoutAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutAdminActionPerformed
               
        admin = true;
        
        panel1.setEnabled(admin);
        panel2.setEnabled(admin);
        panel3.setEnabled(admin);

        edicion.setEnabled(admin);
        edicionVocablo.setEnabled(admin);
        etiquetaVocablo.setEnabled(admin);
        edicionAcepcion.setEnabled(admin);
        etiquetaAcepcion.setEnabled(admin);
        edicionEjemplo.setEnabled(admin);
        etiquetaEjemplo.setEnabled(admin);
        edicionRelacionada.setEnabled(admin);
        etiquetaRelacionada.setEnabled(admin);

        listaCategorias.setEnabled(admin);
        listaRelacionadas.setEnabled(admin);
        esSoez.setEnabled(admin);

        agregarVocablo.setEnabled(admin);
        editarVocablo.setEnabled(admin);
        borrarVocablo.setEnabled(admin);
        agregarAcepción.setEnabled(admin);
        editarAcepción.setEnabled(admin);
        borrarAcepción.setEnabled(admin);
        agregarRelacionada.setEnabled(admin);
        borrarRelacionada.setEnabled(admin);

        acciones.setEnabled(admin);
        mensaje.setEnabled(admin);

        panel4.setEnabled(admin);

        panelMostrarVocablos.setEnabled(admin);
        mostrarVocablo.setEnabled(admin);
        mostrarVocablos.setEnabled(admin);
        listaVocablosTodo.setEnabled(admin);
        anuncioListaVocablos.setEnabled(admin);

        buscarTodo.setEnabled(admin);
        refrescarVocablos.setEnabled(admin);

        panel5.setEnabled(admin);
        panel6.setEnabled(admin);
        panel7.setEnabled(admin);

        especial.setEnabled(admin);
        consultaTexto.setEnabled(admin);
        consultaEspecial.setEnabled(admin);
        informacion.setEnabled(admin);
        tabla.setEnabled(admin);

        bienvenida.setText("\n Búsqueda de palabras \n" +
                           "en el diccionario urbano.\n" +
                           " \n      Usuario normal.");       
        
    }//GEN-LAST:event_logoutAdminActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        
        // Termina la aplicación.
           cnx.desconectar();       
           System.exit(0);
        
    }//GEN-LAST:event_salirActionPerformed

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed

        this.buscar(edicionVocablo1.getText());

    }//GEN-LAST:event_buscarActionPerformed

    private void buscarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarTodoActionPerformed
        
        mostrarVocablos.setVisible(true);
        refrescarVocablosActionPerformed(evt);
        
    }//GEN-LAST:event_buscarTodoActionPerformed

    private void mostrarVocabloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrarVocabloActionPerformed
        
        String vocablo = listaVocablosTodo.getSelectedValue();
        
        if(vocablo == null){            
            this.buscar("");            
        } else {
            this.buscar(vocablo);
        }
        
    }//GEN-LAST:event_mostrarVocabloActionPerformed

    private void refrescarVocablosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refrescarVocablosActionPerformed
                
        DefaultListModel model = new DefaultListModel();
        ArrayList<Vocablo> vocablosc = vbc.mostrarVocablos();
        
        for(Vocablo vocablo: vocablosc){       
            model.addElement(vocablo.getVocablo());            
        }
        
        // La lista de vocablos se actualiza, para
        // mostrar nuevos vocablos y quitar los
        // los que han sido borrados.
        
        listaVocablosTodo.setModel(model);
            
    }//GEN-LAST:event_refrescarVocablosActionPerformed

    private void consultaEspecialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultaEspecialActionPerformed
        
        // Organiza los resultados de una consulta
        // y los muestra al usuario.
        
        String query = consultaTexto.getText();
        
        if(query.replace("\t", "").replace("\n", "").replace(" ", "").equals("")){
            
            informacion.setText("");
        
        } else {
            
            //Se realiza la consulta.
            
            ResultSet rs = ccc.consulta(query);
            
            // Si no hay resultados por mostrar, 
            // se le indica al usuario si su
            // operación se ejecutó correctamente.
            
            if(rs == null){
            
                if(ccc.getInfo().equals("")){
                    informacion.setText("\tOperación realizada");
                } else {
                    informacion.setText(ccc.getInfo());
                }
                    
            } else {
                
                // Rearreglo de la tabla para mostrar
                // la información requerida.
                
                DefaultTableModel model = new DefaultTableModel();
                
                try {
                    
                    ResultSetMetaData rsmd = rs.getMetaData();                   
                    int columnas = rsmd.getColumnCount();
                            
                    // Se crean las columnas.
                    
                    for (int i = 1; i <= columnas; i++ ) {
                        model.addColumn(rsmd.getColumnName(i));
                    }
                    
                    String[] entrada = new String[columnas];
                    
                    // Se agregan las filas.
                    
                    while (rs.next()){
                        for (int i = 0; i < columnas; i++ ) {                    
                            entrada[i] = rs.getString(i+1);                        
                        }                       
                        model.addRow(entrada);                
                    }
                    
                    // Actualización de la tabla.
                    
                    tabla.setModel(model);
                    
                } catch (SQLException ex) {                    
                    Logger.getLogger(vistaDiccionario.class.getName()).
                                     log(Level.SEVERE, null, ex);                
                }
            
            }
            
        }
        
    }//GEN-LAST:event_consultaEspecialActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(vistaDiccionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vistaDiccionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vistaDiccionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vistaDiccionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vistaDiccionario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField acciones;
    private javax.swing.JTextField acepcionesEjemplos;
    private javax.swing.JTextArea acepcionesVocablo;
    private javax.swing.JButton agregarAcepción;
    private javax.swing.JButton agregarRelacionada;
    private javax.swing.JButton agregarVocablo;
    private javax.swing.JTextArea antonimosVocablo;
    private javax.swing.JTextField anuncioAdmin;
    private javax.swing.JTextField anuncioListaVocablos;
    private javax.swing.JTextArea bienvenida;
    private javax.swing.JButton borrarAcepción;
    private javax.swing.JButton borrarRelacionada;
    private javax.swing.JButton borrarVocablo;
    private javax.swing.JButton buscar;
    private javax.swing.JButton buscarTodo;
    private javax.swing.JButton cancelarAdmin;
    private javax.swing.JPanel consulta;
    private javax.swing.JButton consultaEspecial;
    private javax.swing.JTextArea consultaTexto;
    private javax.swing.JPasswordField contrasenaAdmin;
    private javax.swing.JTextArea derivadosVocablo;
    private javax.swing.JTextField diccionarioUrbano;
    private javax.swing.JPanel edicion;
    private javax.swing.JTextField edicionAcepcion;
    private javax.swing.JTextField edicionEjemplo;
    private javax.swing.JTextField edicionRelacionada;
    private javax.swing.JTextField edicionVocablo;
    private javax.swing.JTextField edicionVocablo1;
    private javax.swing.JButton editarAcepción;
    private javax.swing.JButton editarVocablo;
    private javax.swing.JRadioButton esSoez;
    private javax.swing.JPanel especial;
    private javax.swing.JTextField etiquetaAcepcion;
    private javax.swing.JTextField etiquetaAntonimo;
    private javax.swing.JTextField etiquetaDerivados;
    private javax.swing.JTextField etiquetaEjemplo;
    private javax.swing.JTextField etiquetaRelacionada;
    private javax.swing.JTextField etiquetaSinonimo;
    private javax.swing.JTextField etiquetaVocablo;
    private javax.swing.JTextField etiquetaVocablo1;
    private javax.swing.JTextField indicaCategoria;
    private javax.swing.JTextField indicaSoez;
    private javax.swing.JTextArea informacion;
    private javax.swing.JButton ingresarAdmin;
    private javax.swing.JFrame ingreso;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JList<String> listaCategorias;
    private javax.swing.JList<String> listaRelacionadas;
    private javax.swing.JList<String> listaVocablosTodo;
    private javax.swing.JButton loginAdmin;
    private javax.swing.JButton logoutAdmin;
    private javax.swing.JTextArea mensaje;
    private javax.swing.JButton mostrarVocablo;
    private javax.swing.JFrame mostrarVocablos;
    private javax.swing.JScrollPane panel1;
    private javax.swing.JScrollPane panel2;
    private javax.swing.JScrollPane panel3;
    private javax.swing.JScrollPane panel4;
    private javax.swing.JScrollPane panel5;
    private javax.swing.JScrollPane panel6;
    private javax.swing.JScrollPane panel7;
    private javax.swing.JPanel panelIngreso;
    private javax.swing.JPanel panelMostrarVocablos;
    private javax.swing.JPanel principal;
    private javax.swing.JButton refrescarVocablos;
    private javax.swing.JButton salir;
    private javax.swing.JTextArea sinonimosVocablo;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField usuarioAdmin;
    private javax.swing.JTabbedPane ventanas;
    // End of variables declaration//GEN-END:variables
}