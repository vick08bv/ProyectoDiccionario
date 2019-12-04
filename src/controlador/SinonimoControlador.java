package controlador;


import conexion.Conexion;

import modelo.Vocablo;
import modelo.Sinonimo;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 * Controlador para los sinónimos.
 */
public class SinonimoControlador {
    
    private Conexion cnx = null;

    /**
     * Constructor.
     * @param cnx
     */
    public SinonimoControlador(Conexion cnx) {
        
        this.cnx = cnx;
    
    }
    
    
    /**
     * Regresa los sinónimos de un vocablo dado. 
     * @param vocablo Vocablo buscado.
     * @return Lista con sinónimos.
     */
    public ArrayList<Sinonimo> sinonimosVocablo(String vocablo) {       
        
        ArrayList<Sinonimo> entradas = new ArrayList<>(5);
        
        String query = "SELECT sinonimo FROM Sinonimos \n"
                     + "INNER JOIN Vocablo ON Sinonimos.vocablo = Vocablo.vocablo \n" 
                     + "WHERE Vocablo.vocablo = ?";
        
        try {
            
            PreparedStatement pst = cnx.getConexion().prepareStatement(query);            
            pst.setString(1, vocablo);            
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()){            
                entradas.add(new Sinonimo(
                            rs.getString(1),vocablo));                
            }
            
        } catch (SQLException ex) {           
            
        }
        
        return entradas;
        
    }
    
    
    /**
     * Agrega un sinónimo al vocablo dado.
     * @param sinonimo Sinónimo del vocablo.
     * @param vocablo Vocablo dado.
     * @return Resultado de la operación.
     */
    public String agregar(String sinonimo, String vocablo){
        
        VocabloControlador vocabloControlador = new VocabloControlador(cnx);
        
        // Si el vocablo está en el diccionario, se le agrega el sinónimo.
        
        if(vocabloControlador.mostrarVocablos().contains(new Vocablo(vocablo, "", ""))){
            
            if(this.sinonimosVocablo(vocablo).contains(new Sinonimo(sinonimo, vocablo))){
                return "\n El sinónimo ya existe.";
            } else {
            
                String query = "INSERT INTO Sinonimos (sinonimo, vocablo) VALUES (?, ?)";
        
                try {
            
                    PreparedStatement pst = cnx.getConexion().prepareStatement(query);
            
                    pst.setString(1, sinonimo);
                    pst.setString(2, vocablo);
            
                    pst.execute();
                    
                    return "\n Sinónimo añadido";
            
                } catch (SQLException ex) {
                    return "\n Error. \n No se pudo añadir el sinónimo";                    
                }
                
            }
            
        } else {                       
            return "\n El vocablo no existe.";         
        }
 
    }
    
    
    /**
     * Borra el sinónimo del vocablo dado.
     * @param sinonimo Sinónimo por borrar.
     * @param vocablo Vocablo dado.
     * @return Resultado de la operación.
     */
    public String borrar(String sinonimo, String vocablo){
        
        VocabloControlador vocabloControlador = new VocabloControlador(cnx);
        
        // Si el vocablo está en el diccionario y 
        // posee el sinónimo indicado, se le quita.
        
        if(vocabloControlador.mostrarVocablos().contains(new Vocablo(vocablo,"", ""))){
            
            if(this.sinonimosVocablo(vocablo).contains(new Sinonimo(sinonimo, vocablo))){
            
                String query = "DELETE from Sinonimos where sinonimo = ? and vocablo = ?";
        
                try {
            
                    PreparedStatement pst = cnx.getConexion().prepareStatement(query);
            
                    pst.setString(1, sinonimo);                 
                    pst.setString(2, vocablo);
            
                    pst.execute();
                
                    return "\n Sinónimo borrado.";
            
                } catch (SQLException ex) {
                    return "\n Error. \n No se pudo borrar el sinónimo.";
                }
                
            } else {
                return "\n El sinónimo no existe.";
            }

        } else {
            return "\n El vocablo no existe.";            
        }
 
    }
    
    
}