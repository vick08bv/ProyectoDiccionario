package controlador;


import conexion.Conexion;

import modelo.Relacionado;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Arrays;


/**
 * Controlador para las palabras relacionadas.
 */
public class RelacionadoControlador {
    
    private Conexion cnx = null;
    private final ArrayList<String> tipos;

    /**
     * Constructor.
     * @param cnx
     */
    public RelacionadoControlador(Conexion cnx) {
        
        this.cnx = cnx;
        tipos = new ArrayList<>(
                         Arrays.asList("Tipos", 
                         "Sinónimo", "Antónimo", "Derivado"));
    }
    
    
    /**
     * Verifica si una palabra relacionada se encuentra en el diccionario. 
     * @param palabra Palabra por buscar.
     * @return Existencia de la palabra.
     */
    public boolean existeRelacionado(String palabra) {
        
        String query = "select * from Relacionado where relacionado = ?";
        
        try {
            
            PreparedStatement pst = cnx.getConexion().prepareStatement(query);            
            pst.setString(1, palabra);
            ResultSet rs = pst.executeQuery();
            
            return rs.next();
            
        } catch (SQLException ex) {           
            return false;
        }
        
    }
    
    
    /**
     * Agrega una palabra relacionada a la base de datos.
     * @param palabra Palabra por agrega.
     * @return Resultado de la operación.
     */
    public boolean agregarRelacionado(String palabra){

        if(!this.existeRelacionado(palabra)){
            
            String query = "insert into Relacionado values (?)";
        
            try {
            
                PreparedStatement pst = cnx.getConexion().prepareStatement(query);            
                pst.setString(1, palabra);
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
     * Regresa las palabras relacionadas de un vocablo dado. 
     * @param vocablo Vocablo buscado.
     * @return Lista con palabras relacionadas.
     */
    public ArrayList<Relacionado> relacionadosVocablo(String vocablo) {       
        
        ArrayList<Relacionado> entradas = new ArrayList<>(5);
        
        String query = "select Relacionado.relacionado, Tipo.id_tipo from Relacionado \n" +
                       "inner join Relacionados on Relacionados.relacionado = Relacionado.relacionado \n" +
                       "inner join Tipo on Tipo.id_tipo = Relacionados.id_tipo \n" +       
                       "inner join Vocablo on Vocablo.vocablo = Relacionados.vocablo \n" +
                       "where Vocablo.vocablo = ?";
        
        try {
            
            PreparedStatement pst = cnx.getConexion().prepareStatement(query);            
            pst.setString(1, vocablo);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()){            
                entradas.add(new Relacionado(rs.getString(1),tipos.get(rs.getInt(2))));                
            }
            
        } catch (SQLException ex) {           
            
        }
        
        return entradas;
        
    }
    
    
    /**
     * Agrega una palabra relacionada al vocablo dado.
     * @param vocablo Vocablo dado.
     * @param palabra Palabra relacionada al vocablo.
     * @param tipo Tipo de relación.
     * @return Resultado de la operación.
     */
    public String agrega(String vocablo, String palabra, String tipo){
        
        if(vocablo.replace(" ","").equals("")){
            return "\n     Indique un vocablo.";
        }
        
        if(palabra.replace(" ", "").equals("")){
            return "\n    Indique una palabra.";
        }
        
        if(tipo == null){
            return "\n       Indique un tipo\n" + 
                   "           de palabra.";
        }
        
        VocabloControlador vocabloControlador = new VocabloControlador(cnx);
        
        // Si el vocablo está en el diccionario, se le agrega la palabra relacionada.
        
        if(vocabloControlador.existeVocablo(vocablo)){
            
            if(this.agregarRelacionado(palabra)){
                
                if(this.relacionadosVocablo(vocablo).contains(new Relacionado(palabra, tipo))){
                    return "\n   La palabra ya existe.";
                } else {
            
                    String query = "insert into Relacionados values (?, ?, ?)";
        
                    try {
            
                        PreparedStatement pst = cnx.getConexion().prepareStatement(query);
                                    
                        pst.setString(1, palabra);
                        pst.setString(2, vocablo);
                        pst.setInt(3, tipos.indexOf(tipo));
            
                        pst.execute();
                    
                    return "\n      Palabra añadida.";
                    
                    } catch (SQLException ex) {
                        System.out.println(ex);
                        return "\n           Error. \n" + 
                               "No se pudo añadir la palabra";                    
                    }
                
                }
                
            } else {
                return "\n           Error. \n" + 
                               "No se pudo añadir la palabra";                    
            }
        } else {                       
            return "\n   El vocablo no existe.";         
        }
            
    }


    /**
     * Borra la palabra asociada del vocablo dado.
     * @param vocablo Vocablo dado.
     * @param palabra Palabra por borra.
     * @param tipo Tipo de la palabra.
     * @return Resultado de la operación.
     */
    public String borra(String vocablo, String palabra, String tipo){
        
        if(vocablo.replace(" ","").equals("")){
            return "\n     Indique un vocablo.";
        }
        
        if(palabra.replace(" ", "").equals("")){
            return "\n    Indique una palabra.";
        }
        
        if(tipo == null){
            return "\n       Indique un tipo\n" + 
                   "           de palabra.";
        }

        VocabloControlador vocabloControlador = new VocabloControlador(cnx);
        
        // Si el vocablo está en el diccionario y 
        // posee la palabra con el tipo indicado, se le quita.
        
        if(vocabloControlador.existeVocablo(vocablo)){
            
            if(this.relacionadosVocablo(vocablo).contains(new Relacionado(palabra, tipo))){
            
                String query = "delete from Relacionados \n"
                             + "where relacionado = ? and vocablo = ? and id_tipo = ?";
        
                try {
            
                    PreparedStatement pst = cnx.getConexion().prepareStatement(query);
            
                    pst.setString(1, palabra);                 
                    pst.setString(2, vocablo);
                    pst.setInt(3, tipos.indexOf(tipo));
            
                    pst.execute();
            
                } catch (SQLException ex) {
                    return "\n           Error. \n" + 
                           "No se pudo borrar la palabra."; 
                }
                
                // Cuando ningún vocablo posee la palabra relacionada 
                // en la base de datos, esta se elimina.
                
                query = "Select count(Relacionados.relacionado) from Relacionados \n" +
                        "inner join Relacionado on Relacionados.relacionado = Relacionado.relacionado \n" +
                        "where Relacionado.relacionado = ?";
                
                int registros = 1;
        
                try {
            
                    PreparedStatement pst = cnx.getConexion().prepareStatement(query);            
                    pst.setString(1, palabra);      
                    pst.execute();
                    
                    ResultSet rs = pst.executeQuery();                    
                    rs.next();        
                    registros = rs.getInt(1);
                    
                } catch (SQLException ex) {           
                    return "\n     Palabra borrada.";               
                }
                
                if(registros == 0){
                
                    query = "delete from Relacionado where relacionado = ?";
        
                    try {
            
                        PreparedStatement pst = cnx.getConexion().prepareStatement(query);            
                        pst.setString(1, palabra);   
                        pst.execute();
            
                    } catch (SQLException ex) {            
                        return "\n     Palabra borrada.";                
                    }
                
                } 
                
                return "\n     Palabra borrada \n" +
                       "     del diccionario."; 
            } else {
                return "\n  La palabra con el tipo \n" +
                         "    indicado no existe.";
            }

        } else {
            return "\n   El vocablo no existe.";         
        }
 
    }
    
    
}