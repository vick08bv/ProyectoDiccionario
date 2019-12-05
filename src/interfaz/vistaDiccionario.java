package interfaz;

import conexion.Conexion;

import modelo.Vocablo;
import modelo.Acepcion;
import modelo.Relacionado;

import controlador.VocabloControlador;
import controlador.AcepcionControlador;
import controlador.ConsultaControlador;
import controlador.RelacionadoControlador;

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
    
    private final String usuario;
    private final String contrasena;
    
    private boolean admin;
    
    private final Conexion cnx;
    
    private final VocabloControlador vbc;
    private final AcepcionControlador apc;
    private final RelacionadoControlador rcc;
    private final ConsultaControlador ccc;
    
    
    /**
     * Nueva interfaz.
     */
    public vistaDiccionario() {

        usuario = "root";
        contrasena = "root";
    
        cnx = new Conexion();
    
        vbc = new VocabloControlador(cnx);
        apc = new AcepcionControlador(cnx);
        rcc = new RelacionadoControlador(cnx);
        ccc = new ConsultaControlador(cnx);
        
        initComponents();
        cnx.conectar();
        
        this.setTitle("MySQL-Diccionario");
        this.setLocationRelativeTo(null);
        
        //Ventana de inicio de sesión.
        
        ingreso.setTitle("Inicia sesión");
        ingreso.setLocationRelativeTo(null);
        ingreso.setSize(245, 190);
        
        //Ventana con la lista completa de vocablos.
        
        mostrarVocablos.setTitle("Búsqueda");
        mostrarVocablos.setLocation(300,150);
        mostrarVocablos.setSize(245, 240);
        
        panelAyuda.setTitle("Ayuda");
        panelAyuda.setLocation(375,145);
        panelAyuda.setSize(350, 250);
        
        // Se desactivan los componentes usados para
        // el manejo del diccionario por parte 
        // del administrador hasta que inicie sesión.    
        
        panel1.setEnabled(false);
        panel2.setEnabled(false);
        panel3.setEnabled(false);
        
        edicion.setEnabled(false);
        edicionVocablo.setEnabled(false);
        etiquetaVocablo.setEnabled(false);
        edicionAcepcion.setEnabled(false);
        etiquetaAcepcion.setEnabled(false);
        edicionEjemplo.setEnabled(false);
        etiquetaEjemplo.setEnabled(false);
        edicionRelacionada.setEnabled(false);
        etiquetaRelacionada.setEnabled(false);
        edicionExplicacion.setEnabled(false);
        etiquetaExplicacion.setEnabled(false);
        edicionEjemploVocablo.setEnabled(false);
        etiquetaEjemploVocablo.setEnabled(false);
        etiquetaEditar.setEnabled(false);
        limpiarEdicion.setEnabled(false);
        ayuda.setEnabled(false);
        
        listaCategorias.setEnabled(false);
        listaRelacionadas.setEnabled(false);
        esSoez.setEnabled(false);
        
        agregarVocablo.setEnabled(false);
        editarVocablo.setEnabled(false);
        borrarVocablo.setEnabled(false);
        agregarAcepcion.setEnabled(false);
        borrarAcepcion.setEnabled(false);
        agregarRelacionada.setEnabled(false);
        borrarRelacionada.setEnabled(false);
        
        acciones.setEnabled(false);
        mensaje.setEnabled(false);
        
        panel4.setEnabled(false);
        panelMostrarVocablos.setEnabled(false);
        mostrarVocablo.setEnabled(false);
        mostrarVocablos.setEnabled(false);
        listaVocablosTodo.setEnabled(false);
        anuncioListaVocablos.setEnabled(false);
        
        buscarTodo.setEnabled(false);
        refrescarVocablos.setEnabled(false);
        
        panel5.setEnabled(false);
        panel6.setEnabled(false);
        panel7.setEnabled(false);
        
        especial.setEnabled(false);
        consultaTexto.setEnabled(false);
        consultaEspecial.setEnabled(false);
        informacion.setEnabled(false);
        tabla.setEnabled(false);

    }
    
    
    /**
     * Activa o descativa las características del diccionario.
     */
    private void activar(boolean admin){
        
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
        edicionExplicacion.setEnabled(admin);
        etiquetaExplicacion.setEnabled(admin);
        edicionEjemploVocablo.setEnabled(admin);
        etiquetaEjemploVocablo.setEnabled(admin);
        etiquetaEditar.setEnabled(admin);
        limpiarEdicion.setEnabled(admin);
        ayuda.setEnabled(admin);

        listaCategorias.setEnabled(admin);
        listaRelacionadas.setEnabled(admin);
        esSoez.setEnabled(admin);

        agregarVocablo.setEnabled(admin);
        editarVocablo.setEnabled(admin);
        borrarVocablo.setEnabled(admin);
        agregarAcepcion.setEnabled(admin);
        borrarAcepcion.setEnabled(admin);
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
     * Limpia la ventana.
     * @param v Ventana a limpiar. 
     */
    public void limpiar(boolean v){
    
        if(v){
            
            //Limpia la primera ventana.
            
            edicionVocablo1.setText("");
            ejemploUso.setText("");
            sinonimosVocablo.setText("");
            antonimosVocablo.setText("");
            derivadosVocablo.setText("");
            indicaCategoria.setText("");
            indicaSoez.setText("");
            acepcionesVocablo.setText("");
            etiquetaVocablo1.setText("Vocablo");
        
        } else {
            
            //Limpia la segunda ventana.
        
            edicionVocablo.setText("");
            edicionAcepcion.setText("");
            edicionRelacionada.setText("");
            edicionExplicacion.setText("");
            edicionEjemplo.setText("");
            edicionEjemploVocablo.setText("");
            esSoez.setSelected(false);
            listaCategorias.setSelectedValue(null, false);
            listaRelacionadas.setSelectedValue(null, false);
        
        }
    
    }
    
    /**
     * Búsqueda de toda la información de un vocablo.
     * @param v Vocablo a buscar.
     */
    private void buscar(String v){
        
        // Si el vocablo se encuentra en el diccionario,
        // se procede a buscar toda su información.
        
        ArrayList<Vocablo> vocablos = vbc.mostrarVocablos();
        Vocablo u = new Vocablo(v, null, false, null);
        
        if(vocablos.contains(u)){
            
            ArrayList<Relacionado> relacionadosv = rcc.relacionadosVocablo(v);
            ArrayList<String> acepcionesv = apc.acepcionesVocablo(v);            
            ArrayList<Acepcion> acepcionesEjemplos = new ArrayList<>();
            ArrayList<Acepcion> acepcionesEjemplosv = new ArrayList<>();

            // Todas las acepciones del vocablo se agregan en una misma lista.
            
            for(String acepcionv: acepcionesv){
                
                acepcionesEjemplosv = apc.ejemplosAcepcion(v, acepcionv);
                
                for(Acepcion acepcion: acepcionesEjemplosv){
                    acepcionesEjemplos.add(acepcion);
                }
                
            }
            
            String acepciones = ""; String derivados = "";
            String sinonimos = ""; String antonimos = "";
            
            for(Acepcion acepcion: acepcionesEjemplos){           
                acepciones += acepcion.toString();           
            }
            
            // Las palabras relacionadas se separan según
            // su tipo de relación con el vocablo.
            
            for(Relacionado relacionado: relacionadosv){
                
                switch(relacionado.getTipo()){
                    
                    case "Sinónimo":
                       sinonimos += relacionado.toString(); 
                       break;
                    case "Antónimo":
                        antonimos += relacionado.toString();
                        break;
                    default:
                        derivados += relacionado.toString();
                        break;
                }       
                
            }
            
            // Se plasma la información obtenida, 
            // en la interfaz.
            
            acepcionesVocablo.setText(acepciones);
            sinonimosVocablo.setText(sinonimos);
            antonimosVocablo.setText(antonimos);
            derivadosVocablo.setText(derivados);
            
            Vocablo vocablo = vocablos.get(vocablos.indexOf(u));
            
            indicaCategoria.setText(vocablo.getCategoria());
            
            if(vocablo.isEs_soez()){            
                indicaSoez.setText("Vocablo soez");                
            } else {                
                indicaSoez.setText("Vocablo no soez");            
            }
            
            ejemploUso.setText(vocablo.getEjemplo());
            edicionVocablo1.setText(v);
            etiquetaVocablo1.setText("Vocablo");
                
        } else {
                
            if(v.replace(" ","").equals("")){
                limpiar(true);
                etiquetaVocablo1.setText("Ingresa un vocablo");
            } else {
                limpiar(true);
                edicionVocablo1.setText(v);
                etiquetaVocablo1.setText("El vocablo no existe");
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
        panelAyuda = new javax.swing.JFrame();
        panel8 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        ayudaInfo = new javax.swing.JTextArea();
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
        jScrollPane8 = new javax.swing.JScrollPane();
        acepcionesVocablo = new javax.swing.JTextArea();
        acepcionesEjemplos = new javax.swing.JTextField();
        indicaCategoria = new javax.swing.JTextField();
        indicaSoez = new javax.swing.JTextField();
        buscarTodo = new javax.swing.JButton();
        bienvenida = new javax.swing.JTextField();
        ejemploUso = new javax.swing.JTextField();
        limpiaInfoVocablos = new javax.swing.JButton();
        etiquetaInformacion = new javax.swing.JTextField();
        etiquetaEjemploUso = new javax.swing.JTextField();
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
        agregarAcepcion = new javax.swing.JButton();
        borrarAcepcion = new javax.swing.JButton();
        acciones = new javax.swing.JTextField();
        panel3 = new javax.swing.JScrollPane();
        mensaje = new javax.swing.JTextArea();
        edicionExplicacion = new javax.swing.JTextField();
        etiquetaExplicacion = new javax.swing.JTextField();
        edicionEjemploVocablo = new javax.swing.JTextField();
        etiquetaEjemploVocablo = new javax.swing.JTextField();
        etiquetaEditar = new javax.swing.JTextField();
        limpiarEdicion = new javax.swing.JButton();
        ayuda = new javax.swing.JButton();
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
        listaVocablosTodo.setMaximumSize(new java.awt.Dimension(200, 2000));
        listaVocablosTodo.setMinimumSize(new java.awt.Dimension(105, 170));
        listaVocablosTodo.setPreferredSize(new java.awt.Dimension(100, 1000));
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

        panelAyuda.setResizable(false);

        ayudaInfo.setEditable(false);
        ayudaInfo.setBackground(new java.awt.Color(245, 245, 255));
        ayudaInfo.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        ayudaInfo.setForeground(new java.awt.Color(102, 102, 255));
        ayudaInfo.setRows(5);
        ayudaInfo.setText("\n   Ayuda (Panel de edición del diccionario)\n\n-------------------------------------------------\n                      ¿Cómo funciona? \n------------------------------------------------- \n\n - Vocablos:\n\n  - Agregar: \n\n    Añade un vocablo al diccionario, con \n    la categoría gramatical seleccionada,\n    indicando si es un término soez o no\n    (de acuerdo al estado del botón), y\n    un ejemplo de su uso \n\n    (No añade ejemplo si se escribe un\n    ejemplo vacío o se escribe \"null\").\n  \n  - Editar:\n\n    Edita el vocablo indicado \n    (si es que hay escrito alguno), \n    con la nueva categoría gramatical\n    e indicando si es soez o no.\n    Edita el ejemplo si se escribió alguno\n    y lo elimina si se ecribe \"null\".\n\n  - Borrar:\n\n    Borra el vocablo que esté escrito \n    y por el diseño del diccionario,\n    toda sun infromación relacionada.\n\n-------------------------------------------------\n\n - Palabras relacionadas:\n\n  - Agregar:\n\n    Le añade una palabra relacionada al\n    vocablo indicado, de acuerdo al tipo \n    de relación con el vocablo: \n    sinónimo, antónimo o palabra derivada.\n    \n   *Agrega la palabra a la base de datos \n    antes de relacionarla con el vocablo.\n\n  - Borrar:\n\n    Borra el registro de relación entre la\n    palabra y el vocablo indicados.\n    (Sólo borra el tipo de relación marcado).\n\n   *Borra la palabra del diccionario cuando\n    ningún vocablo está relacionado con ella.\n\n-------------------------------------------------\n\n - Acepciones:\n\n  - Agregar:\n\n    Añade una acepción al vocablo indicado.\n    Se le agrega el ejemplo y la explicacion\n    deseados, si los hay.\n    (Si hay alguna explicacion pero no un \n    ejemplo, no agrega nada). \n\n   *Agrega la acepción al diccionario antes \n    de atribuirla a tal vocablo.\n   *Esta opción sólo permite un registro\n    por acecpión y vocablo. \n\n  - Borrar:\n\n    Borra una acepción para el vocablo \n    indicado.\n\n   *Esto borra todos los ejemplos y \n    explicaciones de la acepción para\n    el vocablo dado.\n\n-------------------------------------------------\n\n - Notas:\n\n  - Para agregar varias explicaciones \n    para un mismo ejemplo, o varios\n    ejemplos para una acepción y un\n    vocablo, es necesario realizarlo \n    a través de las consultas especiales.\n\n  - No hay restricción sobre lo que es \n    posible realizar en las consultas\n    especiales. \n");
        jScrollPane7.setViewportView(ayudaInfo);

        javax.swing.GroupLayout panel8Layout = new javax.swing.GroupLayout(panel8);
        panel8.setLayout(panel8Layout);
        panel8Layout.setHorizontalGroup(
            panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel8Layout.setVerticalGroup(
            panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelAyudaLayout = new javax.swing.GroupLayout(panelAyuda.getContentPane());
        panelAyuda.getContentPane().setLayout(panelAyudaLayout);
        panelAyudaLayout.setHorizontalGroup(
            panelAyudaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelAyudaLayout.setVerticalGroup(
            panelAyudaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAyudaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        edicionVocablo1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edicionVocablo1.setPreferredSize(new java.awt.Dimension(80, 30));

        buscar.setBackground(new java.awt.Color(245, 245, 255));
        buscar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buscar.setForeground(new java.awt.Color(0, 51, 153));
        buscar.setText("Buscar vocablo");
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
        sinonimosVocablo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        sinonimosVocablo.setRows(5);
        jScrollPane4.setViewportView(sinonimosVocablo);

        derivadosVocablo.setEditable(false);
        derivadosVocablo.setBackground(new java.awt.Color(245, 245, 255));
        derivadosVocablo.setColumns(10);
        derivadosVocablo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        derivadosVocablo.setRows(5);
        jScrollPane5.setViewportView(derivadosVocablo);

        antonimosVocablo.setEditable(false);
        antonimosVocablo.setBackground(new java.awt.Color(245, 245, 255));
        antonimosVocablo.setColumns(10);
        antonimosVocablo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
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
        loginAdmin.setText("Ingresar administrador");
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

        acepcionesVocablo.setEditable(false);
        acepcionesVocablo.setBackground(new java.awt.Color(245, 245, 255));
        acepcionesVocablo.setColumns(20);
        acepcionesVocablo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        acepcionesVocablo.setRows(5);
        jScrollPane8.setViewportView(acepcionesVocablo);

        acepcionesEjemplos.setEditable(false);
        acepcionesEjemplos.setBackground(new java.awt.Color(245, 245, 255));
        acepcionesEjemplos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        acepcionesEjemplos.setForeground(new java.awt.Color(0, 51, 153));
        acepcionesEjemplos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        acepcionesEjemplos.setText("Acepciones y ejemplos");

        indicaCategoria.setEditable(false);
        indicaCategoria.setBackground(new java.awt.Color(245, 245, 255));
        indicaCategoria.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        indicaCategoria.setForeground(new java.awt.Color(0, 0, 120));
        indicaCategoria.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        indicaSoez.setEditable(false);
        indicaSoez.setBackground(new java.awt.Color(245, 245, 255));
        indicaSoez.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        indicaSoez.setForeground(new java.awt.Color(0, 0, 120));
        indicaSoez.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        buscarTodo.setBackground(new java.awt.Color(245, 245, 255));
        buscarTodo.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        buscarTodo.setForeground(new java.awt.Color(102, 102, 255));
        buscarTodo.setText("Buscar todo");
        buscarTodo.setBorderPainted(false);
        buscarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarTodoActionPerformed(evt);
            }
        });

        bienvenida.setEditable(false);
        bienvenida.setBackground(new java.awt.Color(245, 245, 255));
        bienvenida.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bienvenida.setForeground(new java.awt.Color(255, 0, 0));
        bienvenida.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        bienvenida.setText("Usuario normal");

        ejemploUso.setEditable(false);
        ejemploUso.setBackground(new java.awt.Color(245, 245, 255));
        ejemploUso.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ejemploUso.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ejemploUso.setPreferredSize(new java.awt.Dimension(80, 30));

        limpiaInfoVocablos.setBackground(new java.awt.Color(245, 245, 255));
        limpiaInfoVocablos.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        limpiaInfoVocablos.setForeground(new java.awt.Color(102, 102, 255));
        limpiaInfoVocablos.setText("Limpiar");
        limpiaInfoVocablos.setBorderPainted(false);
        limpiaInfoVocablos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiaInfoVocablosActionPerformed(evt);
            }
        });

        etiquetaInformacion.setEditable(false);
        etiquetaInformacion.setBackground(new java.awt.Color(245, 245, 255));
        etiquetaInformacion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        etiquetaInformacion.setForeground(new java.awt.Color(0, 51, 153));
        etiquetaInformacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        etiquetaInformacion.setText("Información gramatical");
        etiquetaInformacion.setPreferredSize(new java.awt.Dimension(80, 30));

        etiquetaEjemploUso.setEditable(false);
        etiquetaEjemploUso.setBackground(new java.awt.Color(245, 245, 255));
        etiquetaEjemploUso.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etiquetaEjemploUso.setForeground(new java.awt.Color(0, 51, 153));
        etiquetaEjemploUso.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        etiquetaEjemploUso.setText("Ejemplo de uso");
        etiquetaEjemploUso.setPreferredSize(new java.awt.Dimension(80, 30));

        javax.swing.GroupLayout consultaLayout = new javax.swing.GroupLayout(consulta);
        consulta.setLayout(consultaLayout);
        consultaLayout.setHorizontalGroup(
            consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, consultaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane8)
                    .addGroup(consultaLayout.createSequentialGroup()
                        .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(salir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(diccionarioUrbano)
                            .addComponent(logoutAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(loginAdmin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bienvenida)
                            .addComponent(acepcionesEjemplos)
                            .addGroup(consultaLayout.createSequentialGroup()
                                .addComponent(buscarTodo)
                                .addGap(2, 2, 2)
                                .addComponent(limpiaInfoVocablos, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(consultaLayout.createSequentialGroup()
                                .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(consultaLayout.createSequentialGroup()
                                        .addComponent(etiquetaSinonimo, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(149, 149, 149)
                                        .addComponent(etiquetaDerivados, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(consultaLayout.createSequentialGroup()
                                        .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(etiquetaVocablo1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(etiquetaEjemploUso, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(consultaLayout.createSequentialGroup()
                                                .addComponent(edicionVocablo1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(ejemploUso, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(consultaLayout.createSequentialGroup()
                                .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                                    .addComponent(etiquetaInformacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(indicaCategoria)
                                    .addComponent(jScrollPane6)
                                    .addComponent(etiquetaAntonimo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(indicaSoez, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        consultaLayout.setVerticalGroup(
            consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(consultaLayout.createSequentialGroup()
                .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(consultaLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(consultaLayout.createSequentialGroup()
                                .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(etiquetaVocablo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(diccionarioUrbano, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(consultaLayout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(ejemploUso, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(bienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(etiquetaEjemploUso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(consultaLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(edicionVocablo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(etiquetaSinonimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(etiquetaAntonimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(etiquetaDerivados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(limpiaInfoVocablos, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(buscarTodo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, consultaLayout.createSequentialGroup()
                        .addComponent(loginAdmin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(logoutAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane6)
                    .addComponent(jScrollPane5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(acepcionesEjemplos, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(indicaCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(indicaSoez)
                    .addComponent(etiquetaInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        ventanas.addTab("Consulta diccionario", consulta);

        edicion.setMaximumSize(new java.awt.Dimension(600, 400));
        edicion.setMinimumSize(new java.awt.Dimension(200, 100));
        edicion.setPreferredSize(new java.awt.Dimension(600, 400));

        edicionVocablo.setBackground(new java.awt.Color(245, 245, 255));
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
        etiquetaRelacionada.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        etiquetaRelacionada.setForeground(new java.awt.Color(0, 51, 153));
        etiquetaRelacionada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        etiquetaRelacionada.setText("Palabra relacionada");
        etiquetaRelacionada.setPreferredSize(new java.awt.Dimension(80, 30));

        edicionRelacionada.setBackground(new java.awt.Color(245, 245, 255));
        edicionRelacionada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edicionRelacionada.setPreferredSize(new java.awt.Dimension(80, 30));

        listaRelacionadas.setBackground(new java.awt.Color(245, 245, 255));
        listaRelacionadas.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        listaRelacionadas.setForeground(new java.awt.Color(102, 102, 255));
        listaRelacionadas.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Sinónimo", "Antónimo", "Derivado" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listaRelacionadas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listaRelacionadas.setAlignmentY(5.0F);
        listaRelacionadas.setMaximumSize(new java.awt.Dimension(100, 80));
        listaRelacionadas.setMinimumSize(new java.awt.Dimension(70, 42));
        listaRelacionadas.setPreferredSize(new java.awt.Dimension(70, 35));
        panel1.setViewportView(listaRelacionadas);

        listaCategorias.setBackground(new java.awt.Color(245, 245, 255));
        listaCategorias.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        listaCategorias.setForeground(new java.awt.Color(102, 102, 255));
        listaCategorias.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Sustantivo", "Adjetivo", "Artículo", "Pronombre", "Verbo", "Adverbio", "Interjección", "Preposición", "Conjunción" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listaCategorias.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listaCategorias.setMaximumSize(new java.awt.Dimension(200, 400));
        listaCategorias.setMinimumSize(new java.awt.Dimension(100, 152));
        listaCategorias.setPreferredSize(new java.awt.Dimension(100, 152));
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
        edicionEjemplo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edicionEjemplo.setPreferredSize(new java.awt.Dimension(80, 30));

        agregarAcepcion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        agregarAcepcion.setForeground(new java.awt.Color(0, 0, 153));
        agregarAcepcion.setText("Agregar");
        agregarAcepcion.setPreferredSize(new java.awt.Dimension(75, 30));
        agregarAcepcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarAcepcionActionPerformed(evt);
            }
        });

        borrarAcepcion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        borrarAcepcion.setForeground(new java.awt.Color(255, 0, 0));
        borrarAcepcion.setText("Borrar");
        borrarAcepcion.setPreferredSize(new java.awt.Dimension(75, 30));
        borrarAcepcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarAcepcionActionPerformed(evt);
            }
        });

        acciones.setEditable(false);
        acciones.setBackground(new java.awt.Color(245, 245, 255));
        acciones.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        acciones.setForeground(new java.awt.Color(102, 102, 255));
        acciones.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        acciones.setText("Mensajes");

        mensaje.setBackground(new java.awt.Color(245, 245, 255));
        mensaje.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mensaje.setForeground(new java.awt.Color(0, 51, 255));
        mensaje.setLineWrap(true);
        mensaje.setRows(4);
        panel3.setViewportView(mensaje);

        edicionExplicacion.setBackground(new java.awt.Color(245, 245, 255));
        edicionExplicacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edicionExplicacion.setPreferredSize(new java.awt.Dimension(80, 30));

        etiquetaExplicacion.setEditable(false);
        etiquetaExplicacion.setBackground(new java.awt.Color(245, 245, 255));
        etiquetaExplicacion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        etiquetaExplicacion.setForeground(new java.awt.Color(0, 51, 153));
        etiquetaExplicacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        etiquetaExplicacion.setText("Explicación");
        etiquetaExplicacion.setPreferredSize(new java.awt.Dimension(80, 30));

        edicionEjemploVocablo.setBackground(new java.awt.Color(245, 245, 255));
        edicionEjemploVocablo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edicionEjemploVocablo.setPreferredSize(new java.awt.Dimension(80, 30));

        etiquetaEjemploVocablo.setEditable(false);
        etiquetaEjemploVocablo.setBackground(new java.awt.Color(245, 245, 255));
        etiquetaEjemploVocablo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        etiquetaEjemploVocablo.setForeground(new java.awt.Color(0, 51, 153));
        etiquetaEjemploVocablo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        etiquetaEjemploVocablo.setText("Ejemplo");
        etiquetaEjemploVocablo.setPreferredSize(new java.awt.Dimension(80, 30));

        etiquetaEditar.setEditable(false);
        etiquetaEditar.setBackground(new java.awt.Color(245, 245, 255));
        etiquetaEditar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        etiquetaEditar.setForeground(new java.awt.Color(102, 102, 255));
        etiquetaEditar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        etiquetaEditar.setText("¡Edita el diccionario!");

        limpiarEdicion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        limpiarEdicion.setForeground(new java.awt.Color(102, 102, 255));
        limpiarEdicion.setText("Limpiar");
        limpiarEdicion.setPreferredSize(new java.awt.Dimension(75, 30));
        limpiarEdicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarEdicionActionPerformed(evt);
            }
        });

        ayuda.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ayuda.setForeground(new java.awt.Color(102, 102, 255));
        ayuda.setText("Ayuda");
        ayuda.setPreferredSize(new java.awt.Dimension(75, 30));
        ayuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ayudaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout edicionLayout = new javax.swing.GroupLayout(edicion);
        edicion.setLayout(edicionLayout);
        edicionLayout.setHorizontalGroup(
            edicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, edicionLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(edicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(etiquetaVocablo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(etiquetaRelacionada, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(etiquetaAcepcion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(edicionRelacionada, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(edicionAcepcion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(etiquetaEjemploVocablo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addGroup(edicionLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(esSoez, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(edicionVocablo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(edicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(edicionLayout.createSequentialGroup()
                        .addGroup(edicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(edicionLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(edicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(editarVocablo, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                                    .addComponent(agregarVocablo, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                                    .addComponent(borrarVocablo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(edicionEjemploVocablo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(edicionLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(edicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(agregarRelacionada, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(borrarRelacionada, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(edicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(acciones)
                            .addComponent(panel3)
                            .addComponent(etiquetaEditar)
                            .addGroup(edicionLayout.createSequentialGroup()
                                .addComponent(limpiarEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ayuda, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(edicionLayout.createSequentialGroup()
                        .addGroup(edicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(edicionLayout.createSequentialGroup()
                                .addComponent(edicionEjemplo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(edicionExplicacion, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
                            .addGroup(edicionLayout.createSequentialGroup()
                                .addComponent(etiquetaEjemplo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(etiquetaExplicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(edicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(borrarAcepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(agregarAcepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(15, 15, 15))
        );
        edicionLayout.setVerticalGroup(
            edicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edicionLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(edicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(edicionLayout.createSequentialGroup()
                        .addComponent(agregarVocablo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(editarVocablo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(edicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(borrarVocablo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(limpiarEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ayuda, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51)
                        .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(edicionLayout.createSequentialGroup()
                        .addGroup(edicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(edicionLayout.createSequentialGroup()
                                .addComponent(etiquetaVocablo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(esSoez, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(edicionVocablo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(edicionLayout.createSequentialGroup()
                                .addComponent(etiquetaEditar)
                                .addGap(36, 36, 36)))
                        .addGroup(edicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(edicionLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(etiquetaEjemploVocablo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, edicionLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(edicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(edicionEjemploVocablo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(acciones, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(edicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(edicionLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(agregarRelacionada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(borrarRelacionada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(edicionLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(edicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(edicionLayout.createSequentialGroup()
                                        .addComponent(etiquetaRelacionada, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(edicionRelacionada, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGroup(edicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(edicionLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(edicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(etiquetaAcepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiquetaEjemplo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiquetaExplicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(edicionLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(agregarAcepcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(edicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(edicionLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(edicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edicionAcepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edicionEjemplo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edicionExplicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, edicionLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(borrarAcepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );

        ventanas.addTab("Edición vocablos", edicion);

        tabla.setBackground(new java.awt.Color(245, 245, 255));
        tabla.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tabla.setForeground(new java.awt.Color(255, 51, 51));
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
        tabla.setMaximumSize(new java.awt.Dimension(1000, 12000));
        tabla.setMinimumSize(new java.awt.Dimension(450, 150));
        tabla.setPreferredSize(new java.awt.Dimension(1000, 2500));
        panel5.setViewportView(tabla);

        consultaTexto.setBackground(new java.awt.Color(245, 245, 255));
        consultaTexto.setColumns(20);
        consultaTexto.setFont(new java.awt.Font("Alef", 0, 14)); // NOI18N
        consultaTexto.setRows(5);
        panel6.setViewportView(consultaTexto);

        consultaEspecial.setBackground(new java.awt.Color(245, 245, 255));
        consultaEspecial.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        consultaEspecial.setForeground(new java.awt.Color(102, 102, 255));
        consultaEspecial.setText("Consulta MySQL");
        consultaEspecial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultaEspecialActionPerformed(evt);
            }
        });

        informacion.setBackground(new java.awt.Color(245, 245, 255));
        informacion.setFont(new java.awt.Font("Alef", 0, 10)); // NOI18N
        informacion.setLineWrap(true);
        informacion.setRows(5);
        informacion.setTabSize(5);
        informacion.setText("\t\t\tInformación");
        panel7.setViewportView(informacion);

        javax.swing.GroupLayout especialLayout = new javax.swing.GroupLayout(especial);
        especial.setLayout(especialLayout);
        especialLayout.setHorizontalGroup(
            especialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(especialLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(especialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(especialLayout.createSequentialGroup()
                        .addComponent(consultaEspecial, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panel7, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE))
                    .addComponent(panel6)
                    .addComponent(panel5))
                .addContainerGap(53, Short.MAX_VALUE))
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
            
            this.activar(true);
            
            usuarioAdmin.setText("");
            contrasenaAdmin.setText("");
            
            bienvenida.setText("Administrador");
            ingreso.setVisible(false);
        
        } else {
            
            // La ventana permanece en espera de que
            // el administrador se identifique.
            
            usuarioAdmin.setText("");
            contrasenaAdmin.setText("");
            anuncioAdmin.setText("Usuario incorrecto.");
            
        }
        
    }//GEN-LAST:event_ingresarAdminActionPerformed

    private void cancelarAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarAdminActionPerformed
        
        // Desaparece la ventana de inicio de sesión.
        ingreso.setVisible(false);
        
        usuarioAdmin.setText("");
        contrasenaAdmin.setText("");
        
    }//GEN-LAST:event_cancelarAdminActionPerformed

    private void agregarVocabloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarVocabloActionPerformed
        
        mensaje.setText(vbc.agrega(edicionVocablo.getText(), 
                                    listaCategorias.getSelectedValue(), 
                                    esSoez.isSelected(),
                                    edicionEjemploVocablo.getText()));
        
    }//GEN-LAST:event_agregarVocabloActionPerformed

    private void borrarVocabloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarVocabloActionPerformed
        
        String vocablo = edicionVocablo.getText();
        
        mensaje.setText(vbc.borrar(vocablo));
        
    }//GEN-LAST:event_borrarVocabloActionPerformed

    private void editarVocabloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarVocabloActionPerformed

        String ejemplo = edicionEjemploVocablo.getText();
        
        if((ejemplo.replace(" ", "").equals(""))){            
            mensaje.setText(vbc.edita(edicionVocablo.getText(),
                                      listaCategorias.getSelectedValue(),
                                      esSoez.isSelected()));    
        } else {        
            mensaje.setText(vbc.edita(edicionVocablo.getText(),
                                      listaCategorias.getSelectedValue(),
                                      esSoez.isSelected(),
                                      ejemplo));
        }
        
    }//GEN-LAST:event_editarVocabloActionPerformed

    private void agregarRelacionadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarRelacionadaActionPerformed
  
        mensaje.setText(rcc.agrega(edicionVocablo.getText(),
                   edicionRelacionada.getText(),
                   listaRelacionadas.getSelectedValue()));
        
    }//GEN-LAST:event_agregarRelacionadaActionPerformed

    private void borrarRelacionadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarRelacionadaActionPerformed
        
        mensaje.setText(rcc.borra(edicionVocablo.getText(),
                   edicionRelacionada.getText(),
                   listaRelacionadas.getSelectedValue()));
        
    }//GEN-LAST:event_borrarRelacionadaActionPerformed

    private void agregarAcepcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarAcepcionActionPerformed

        mensaje.setText(apc.agregar(edicionVocablo.getText(), edicionAcepcion.getText(),
                                    edicionEjemplo.getText(), edicionExplicacion.getText()));
        
    }//GEN-LAST:event_agregarAcepcionActionPerformed

    private void borrarAcepcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarAcepcionActionPerformed

        mensaje.setText(apc.borrar(edicionVocablo.getText(), edicionAcepcion.getText()));            

    }//GEN-LAST:event_borrarAcepcionActionPerformed

    private void loginAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginAdminActionPerformed

        ingreso.setVisible(true);
        
    }//GEN-LAST:event_loginAdminActionPerformed

    private void logoutAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutAdminActionPerformed
               
        this.activar(false);
        
        bienvenida.setText("Usuario normal");       
        
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
        
        refrescarVocablosActionPerformed(evt);
        mostrarVocablos.setVisible(true);
        
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
            
        //Se realiza la consulta.  
        ResultSet rs = ccc.consulta(consultaTexto.getText());
            
        // Si no hay resultados por mostrar, 
        // se le indica al usuario si su
        // operación se ejecutó correctamente.
            
        if(rs == null){
            informacion.setText(ccc.getInfo());
        } else {

            // Rearreglo de la tabla para mostrar
            // la información requerida.                
            DefaultTableModel model = new DefaultTableModel();

            try {

                ResultSetMetaData rsmd = rs.getMetaData();  
                String tipoColumna;
                int columnas = rsmd.getColumnCount();

                // Se crean las columnas.                   
                for (int i = 1; i <= columnas; i++ ) {
                    
                    tipoColumna = rsmd.getColumnTypeName(i);
                    
                    if("TINYINT".equals(tipoColumna) || "BOOLEAN".equals(tipoColumna)){
                    }
                    
                    model.addColumn(rsmd.getColumnLabel(i));
                       
                    
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
            informacion.setText(ccc.getInfo());
        }

    }//GEN-LAST:event_consultaEspecialActionPerformed

    private void limpiarEdicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarEdicionActionPerformed
        this.limpiar(false);
    }//GEN-LAST:event_limpiarEdicionActionPerformed

    private void ayudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ayudaActionPerformed
        panelAyuda.setVisible(true);
    }//GEN-LAST:event_ayudaActionPerformed

    private void limpiaInfoVocablosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiaInfoVocablosActionPerformed
        this.limpiar(true);
    }//GEN-LAST:event_limpiaInfoVocablosActionPerformed

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
    private javax.swing.JButton agregarAcepcion;
    private javax.swing.JButton agregarRelacionada;
    private javax.swing.JButton agregarVocablo;
    private javax.swing.JTextArea antonimosVocablo;
    private javax.swing.JTextField anuncioAdmin;
    private javax.swing.JTextField anuncioListaVocablos;
    private javax.swing.JButton ayuda;
    private javax.swing.JTextArea ayudaInfo;
    private javax.swing.JTextField bienvenida;
    private javax.swing.JButton borrarAcepcion;
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
    private javax.swing.JTextField edicionEjemploVocablo;
    private javax.swing.JTextField edicionExplicacion;
    private javax.swing.JTextField edicionRelacionada;
    private javax.swing.JTextField edicionVocablo;
    private javax.swing.JTextField edicionVocablo1;
    private javax.swing.JButton editarVocablo;
    private javax.swing.JTextField ejemploUso;
    private javax.swing.JRadioButton esSoez;
    private javax.swing.JPanel especial;
    private javax.swing.JTextField etiquetaAcepcion;
    private javax.swing.JTextField etiquetaAntonimo;
    private javax.swing.JTextField etiquetaDerivados;
    private javax.swing.JTextField etiquetaEditar;
    private javax.swing.JTextField etiquetaEjemplo;
    private javax.swing.JTextField etiquetaEjemploUso;
    private javax.swing.JTextField etiquetaEjemploVocablo;
    private javax.swing.JTextField etiquetaExplicacion;
    private javax.swing.JTextField etiquetaInformacion;
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
    private javax.swing.JButton limpiaInfoVocablos;
    private javax.swing.JButton limpiarEdicion;
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
    private javax.swing.JPanel panel8;
    private javax.swing.JFrame panelAyuda;
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