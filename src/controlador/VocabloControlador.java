package controlador;


import conexion.Conexion;

import modelo.Vocablo;

import java.util.Arrays;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;


/**
 * Controlador para la clase Vocablo.
 */
public class VocabloControlador {
   
    
    private Conexion cnx = null;
    private final ArrayList<String> categorias;

    
    /**
     * Constructor.
     * @param cnx
     */
    public VocabloControlador(Conexion cnx) {
        
        this.cnx = cnx;
        categorias = new ArrayList<>(
                         Arrays.asList("Categorías", 
                         "Sustantivo", "Adjetivo", "Artículo", 
                         "Pronombre", "Verbo", "Adverbio", 
                         "Interjección", "Preposición", "Conjunción"));
    
    }
    
    
    /**
     * Verifica si una vocablo se encuentra en el diccionario. 
     * @param vocablo Vocablo por buscar.
     * @return Existencia del vocablo.
     */
    public boolean existeVocablo(String vocablo) {
        
        if(vocablo.replace(" ", "").equals("")){
            return false;
        }
        
        String query = "select * from Vocablo \n" +
                       "where Vocablo.vocablo = ?";
        
        try {
            
            PreparedStatement pst = cnx.getConexion().prepareStatement(query);            
            pst.setString(1, vocablo);
            ResultSet rs = pst.executeQuery();
            
            return rs.next();
            
        } catch (SQLException ex) {           
            return false;
        }
        
    }
    
    
    /**
     * Agrega un nuevo vocablo al diccionario.
     * @param v Vocablo por agrega.
     * @param c Categoría gramatical del vocablo.
     * @param s Indica si el vocablo es soez.
     * @param ejemplo Ejemplo del uso del vocablo.
     * @return Resultado de la operación.
     */
    public String agrega(String v, String c, boolean s, String ejemplo){

        String e = ejemplo;
        
        if(v.replace(" ", "").equals("")){            
            return "\n     Indique un vocablo.";    
        }
        if(c == null){
            return "\n   Indique una categoría.";
        }
        if(e.replace(" ", "").equals("") || e.replace(" ", "").equals("null")){
            e = null;
        }


        // Si el v ya existe en el diccionario, no se agrega
        
        if(this.existeVocablo(v)){        
            return "\n   El vocablo ya existe.";
        } else {
        
            String query = "insert into Vocablo values (?, ?, ?, ?)";
        
            try {
            
                PreparedStatement pst = cnx.getConexion().prepareStatement(query);
            
                pst.setString(1, v);
                pst.setInt(2, categorias.indexOf(c));
                pst.setBoolean(3, s);
                pst.setString(4, e);
                
                pst.execute();
                
                return "\n      Vocablo añadido.";
                
            } catch (SQLException ex) {
                return "\n           Error. \n" + 
                           "No se pudo agregar el vocablo.";
            
            }
        }
 
    }
    
    
    /**
     * Edita el registro de un vocablo.
     * @param v Vocablo por edita.
     * @param c Nueva categoría del vocablo.
     * @param s Indica si es soez el vocablo.
     * @return Resultado de la operación.
     */
    public String edita(String v, String c, boolean s){
        
        if(v.replace(" ", "").equals("")){            
            return "\n     Indique un vocablo.";    
        }
        if(c == null){
            return "\n   Indique una categoría.";
        }
        
        if(this.existeVocablo(v)){
            
            String query = "update Vocablo set id_categoria = ?, es_soez = ? \n" +
                           "where Vocablo = ?";
        
            try {
            
                PreparedStatement pst = cnx.getConexion().prepareStatement(query);
           
                pst.setInt(1, categorias.indexOf(c));
                pst.setBoolean(2, s);
                pst.setString(3, v);
                
                pst.execute();
                
                return "\n      Vocablo editado.";
            
            } catch (SQLException ex) {
                return "\n           Error. \n" + 
                           "No se pudo agregar el vocablo.";
            }
        
        } else {
            return "\n   El vocablo no existe.";
        }
 
    }
    
    
    /**
     * Edita el registro de un vocablo.
     * @param v Vocablo por editar.
     * @param c Nueva categoría del vocablo.
     * @param s Indica si es soez el vocablo.
     * @param ejemplo Nuevo ejemplo del vocablo.
     * @return Resultado de la operación.
     */
    public String edita(String v, String c, boolean s, String ejemplo){
        
        String e = ejemplo;
        
        if(v.replace(" ", "").equals("")){            
            return "\n     Indique un vocablo.";    
        }
        if(c == null){
            return "\n   Indique una categoría.";
        }
        if(e.replace(" ", "").equals("null")){
            e = null;
        }
        
        if(this.existeVocablo(v)){
            
            String query = "update Vocablo set id_categoria = ?, es_soez = ?, ejemplo = ? \n" + 
                           "where Vocablo = ?";
        
            try {
            
                PreparedStatement pst = cnx.getConexion().prepareStatement(query);
           
                pst.setInt(1, categorias.indexOf(c));
                pst.setBoolean(2, s);
                pst.setString(3, e);
                pst.setString(4, v);
                
                pst.execute();
                
                return "\n     Vocablo editado.";
                
            } catch (SQLException ex) {
                return "\n           Error. \n" + 
                               "No se pudo editar el vocablo";
            }
        
        } else {
            return "\n   El vocablo no existe.";
        }
 
    }
    
    
    /**
     * Regresa una lista con todos los vocablos del diccionario.
     * @return Lista con vocablos.
     */
    public ArrayList<Vocablo> mostrarVocablos() {
        
        ArrayList<Vocablo> entradas = new ArrayList<>(25);
        
        String query = "select * from Vocablo";
        
        try {
            
            PreparedStatement pst = cnx.getConexion().prepareStatement(query);
            
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()){
            
                entradas.add(new Vocablo(rs.getString(1), 
                          categorias.get(rs.getInt(2)),
                                         rs.getBoolean(3), 
                                         rs.getString(4)));
                
            }
            
        } catch (SQLException ex) {
            
        }
        
        return entradas;
        
    }
    
    
    /**
     * Borra un vocablo del diccionario, 
     * junto con su información relacionada.
     * @param vocablo Vocablo a borrar.
     * @return Resultado de la operación.
     */
    public String borrar(String vocablo){
        
        if(this.existeVocablo(vocablo)){   
                        
            String query = "delete from Vocablo where vocablo = ?";
        
            try {
            
                PreparedStatement pst = cnx.getConexion().prepareStatement(query);
            
                pst.setString(1, vocablo);
            
                pst.execute();
                
                return "\n     Vocablo borrado.";
            
            } catch (SQLException ex) {
                    return "\n           Error. \n" + 
                           "No se pudo borrar el vocablo.";               
            }
        
        } else {           
            return "\n   El vocablo no existe.";         
        }
 
    }

    
}