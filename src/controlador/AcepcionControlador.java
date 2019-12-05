package controlador;


import conexion.Conexion;

import modelo.Acepcion;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;


/**
 * Controlador para la clase Acepción.
 */
public class AcepcionControlador {
    
    
    private Conexion cnx = null;

    
    /**
     * Constructor
     * @param cnx.
     */
    public AcepcionControlador(Conexion cnx) {
        
        this.cnx = cnx;
    
    }
    
    
    /**
     * Verifica si una acepción se encuentra en el diccionario. 
     * @param acepcion Acepción por buscar.
     * @return Existencia de la acepción.
     */
    public boolean existeAcepcion(String acepcion) {
        
        String query = "select * from Acepcion where acepcion = ?";
        
        try {
            
            PreparedStatement pst = cnx.getConexion().prepareStatement(query);            
            pst.setString(1, acepcion);
            ResultSet rs = pst.executeQuery();
            
            return rs.next();
            
        } catch (SQLException ex) {           
            return false;
        }
        
    }
    
    
    /**
     * Agrega una acepción a la base de datos.
     * @param acepcion Acepción por agrega.
     * @return Resultado de la operación.
     */
    public boolean agregarAcepcion(String acepcion){

        if(!this.existeAcepcion(acepcion)){
            
            String query = "insert into Acepcion values (?)";
        
            try {
            
                PreparedStatement pst = cnx.getConexion().prepareStatement(query);
            
                pst.setString(1, acepcion);
            
                pst.execute();
                    
                return true;
            
            } catch (SQLException ex) {
                return false;                    
            }
                
        } else {                       
            return true;         
        }
 
    }
    
    
    /**
     * Muestra todas las acepciones relacionadas a un vocablo.
     * @param vocablo Vocablo con las acepciones por buscar.
     * @return Lista con las acepciones.
     */
    public ArrayList<String> acepcionesVocablo(String vocablo) {
        
        ArrayList<String> entradas = new ArrayList<>(25);
        
        String query = "select distinct acepcion from Acepciones \n" +
                       "where vocablo = ?";
        
        try {
            
            PreparedStatement pst = cnx.getConexion().prepareStatement(query);
            
            pst.setString(1, vocablo);
            
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()){
                entradas.add(rs.getString(1)); 
            }
            
        } catch (SQLException ex) {
            
        }
        
        return entradas;
        
    }
    
    
    /**
     * Devuelve los ejemplos de uso de un vocablo con la acepción dada.
     * @param vocablo Vocablo a buscar.
     * @param acepcion Acepción del vocablo.
     * @return Acepciones.
     */
    public ArrayList<Acepcion> ejemplosAcepcion(String vocablo, String acepcion) {       
        
        ArrayList<Acepcion> entradas = new ArrayList<>(5);
        
        String query = "select ejemplo, explicacion from Acepciones \n" + 
                       "where vocablo = ? and acepcion = ?";
        
        
        try {
            
            PreparedStatement pst = cnx.getConexion().prepareStatement(query);           
            pst.setString(1, vocablo);
            pst.setString(2, acepcion);
            
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()){           
                entradas.add(new Acepcion(acepcion,rs.getString(1),rs.getString(2)));                
            }
            
        } catch (SQLException ex) {
           
        }
        
        return entradas;
        
    }
    

    /**
     * Agrega una nueva acepción para el v dado.
     * @param v Vocablo al que se le agrega la acepción.
     * @param a Acepción del v.
     * @param e Un e para la acepción.
     * @param x Explicación del e.
     * @return Resultado de la operación.
     */
    public String agregar(String v, String a, String e, String x){
        
        String vocablo = v; String acepcion = a;
        String ejemplo = e; String explicacion = x;
        
        if(vocablo.replace(" ", "").equals("")){
            return "\n     Indique un vocablo."; 
        }
        if(acepcion.replace(" ","").equals("")){
            return "\n    Indique una acepción.";
        }
        
        if(ejemplo.replace(" ", "").equals("null") || 
           ejemplo.replace(" ", "").equals("")){
            e = null;
            x = null;
        } else {
        
            if(x.replace(" ", "").equals("null") || 
                x.replace(" ", "").equals("")){
                x = null;
            }
            
        }
        
        VocabloControlador vocabloControlador = new VocabloControlador(cnx);
        
        // Si el v no existe en la base de datos, no se agrega nada.
        
        if(vocabloControlador.existeVocablo(v)){
            
            // Se agrega la acepción a la base de datos, para 
            // posteriormente, relacionarla con el v.
         
            if(this.agregarAcepcion(a)){
            
                // Si la el v ya cuenta con la acepción, no se hace nada.
                // Si no, se le agrega.
            
                if(this.acepcionesVocablo(v).contains(a)){            
                    return "\n  La acepción ya existe.";
                } else {
            
                    String query = "insert into Acepciones values (?, ?, ?, ?)";
        
                    try {
            
                        PreparedStatement pst = cnx.getConexion().prepareStatement(query);
            
                        pst.setString(1, a);
                        pst.setString(2, v);
                        pst.setString(3, e);
                        pst.setString(4, x);
            
                        pst.execute();
                    
                        return "\n    Acepción añadida.";
                        
                    } catch (SQLException ex) {
                        return "\n           Error. \n" + 
                               "No se pudo añadir la acepción.";
                    }
                
                }
                    
            } else {
                return "\n           Error. \n" + 
                               "No se pudo añadir la acepción.";
                    }
            
        } else {       
            return "\n   El vocablo no existe.";            
        }
 
    }
    
    
    /**
     * Borra una acepción para un vocablo dado.
     * @param vocablo Vocablo indicado.
     * @param acepcion Acepción que se remueve del vocablo.
     * @return Resultado de la operación.
     */
    public String borrar(String vocablo, String acepcion){

        if(vocablo.replace(" ", "").equals("")){
            return "\n     Indique un vocablo.";
        }
        if(acepcion.replace(" ","").equals("")){
            return "\n    Indique una acepción.";
        }
        
        VocabloControlador vocabloControlador = new VocabloControlador(cnx);
        
        // Si el vocablo existe y posee la acepción, se le quita. 
        
        if(vocabloControlador.existeVocablo(vocablo)){
            
            if(this.acepcionesVocablo(vocablo).contains(acepcion)){
            
                String query = "delete from Acepciones \n" + 
                               "where acepcion = ? and vocablo = ?";
        
                try {
            
                    PreparedStatement pst = cnx.getConexion().prepareStatement(query);
            
                    pst.setString(1, acepcion);
                    pst.setString(2, vocablo);
            
                    pst.execute();
            
                } catch (SQLException ex) {            
                    return "\n           Error. \n" + 
                               "No se pudo borrar la acepción.";                
                }
                
                // Cuando ningún vocablo posee la acepción 
                // en la base de datos, esta se elimina.
                
                query = "Select count(Acepciones.acepcion) from Acepciones \n" +
                        "inner join Acepcion on Acepciones.acepcion = Acepcion.acepcion \n" +
                        "where Acepcion.acepcion = ?";
                int registros = 1;
        
                try {
            
                    PreparedStatement pst = cnx.getConexion().prepareStatement(query);            
                    pst.setString(1, acepcion);      
                    pst.execute();
                    
                    ResultSet rs = pst.executeQuery();                    
                    rs.next();        
                    registros = rs.getInt(1);
                    
                } catch (SQLException ex) {           
                    return "\n    Acepción borrada.";
                }
                
                if(registros == 0){
                
                    query = "delete from Acepcion where acepcion = ?";
        
                    try {
            
                        PreparedStatement pst = cnx.getConexion().prepareStatement(query);            
                        pst.setString(1, acepcion);   
                        pst.execute();
            
                    } catch (SQLException ex) {            
                        return "\n    Acepción borrada.";                
                    }
                
                } 
                
                return "\n    Acepción borrada \n" +
                       "     del diccionario.";
                
            } else {                
                return "\n  El vocablo no contiene \n" +
                         "       la acepción.";
            }

        } else {
            return "\n   El vocablo no existe.";         
        }
 
    }
      
    
}