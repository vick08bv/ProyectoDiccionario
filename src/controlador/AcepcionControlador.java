package controlador;


import conexion.Conexion;

import modelo.Vocablo;
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
     * Muestra todas las acepciones existentes en el diccionario.
     * @return Lista con las acepciones.
     */
    public ArrayList<Acepcion> mostrarAcepciones() {
        
        ArrayList<Acepcion> entradas = new ArrayList<>(25);
        
        String query = "SELECT * FROM Acepcion";
        
        try {
            
            PreparedStatement pst = cnx.getConexion().prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()){
                entradas.add(new Acepcion(rs.getString(1), rs.getString(2))); 
            }
            
        } catch (SQLException ex) {
            
        }
        
        return entradas;
        
    }
    
    
    /**
     * Devuelve las acepciones del vocablo dado.
     * @param vocablo Vocablo a buscar.
     * @return Acepciones.
     */
    public ArrayList<Acepcion> acepcionesVocablo(String vocablo) {       
        
        ArrayList<Acepcion> entradas = new ArrayList<>(5);
        
        String query = "SELECT A.acepcion, A.ejemplo FROM Acepcion A \n"
                     + "INNER JOIN Acepciones B ON A.acepcion = B.acepcion \n" 
                     + "WHERE B.vocablo = ?";
        
        try {
            
            PreparedStatement pst = cnx.getConexion().prepareStatement(query);           
            pst.setString(1, vocablo);
            
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()){           
                entradas.add(new Acepcion(rs.getString(1), rs.getString(2)));                
            }
            
        } catch (SQLException ex) {
           
        }
        
        return entradas;
        
    }
    

    /**
     * Agrega una nueva acepción para el vocablo dado.
     * @param acepcion Acepción.
     * @param ejemplo Un ejemplo para la acepción.
     * @param vocablo Vocablo al que se le agrega la acepción.
     * @return Resultado de la operación.
     */
    public String agregar(String acepcion, String ejemplo, String vocablo){
        
        VocabloControlador vocabloControlador = new VocabloControlador(cnx);
        
        // Si el vocablo no existe en la base de datos, no se agrega nada.
        
        if(vocabloControlador.mostrarVocablos().contains(new Vocablo(vocablo, "", ""))){
            
            // Se agrega la acepción a la base de datos, para 
            // posteriormente, relacionarla con el vocablo.
         
            if(!this.mostrarAcepciones().contains(new Acepcion(acepcion, ""))){
            
                String query = "INSERT INTO Acepcion (acepcion, ejemplo) VALUES (?, ?)";
        
                try {
            
                    PreparedStatement pst = cnx.getConexion().prepareStatement(query);
                    
                    pst.setString(1, acepcion);
                    pst.setString(2, ejemplo);
            
                    pst.execute();
            
                } catch (SQLException ex) {
                    return "\n Error. \n No se pudo añadir la acepción.";            
                }
                
            }
            
            // Si la el vocablo ya cuenta con la acepción, no se hace nada.
            // Si no, se le agrega.
            
            if(this.acepcionesVocablo(vocablo).contains(new Acepcion(acepcion, ejemplo))){            
                return "\n La acepción ya existe.";    
            } else {
            
                String query = "INSERT INTO Acepciones (acepcion, vocablo) VALUES (?, ?)";
        
                try {
            
                    PreparedStatement pst = cnx.getConexion().prepareStatement(query);
            
                    pst.setString(1, acepcion);
                    pst.setString(2, vocablo);
            
                    pst.execute();
                    
                    return "\n Acepción añadida.";
            
                } catch (SQLException ex) {                    
                    return "\n Error. \n No se pudo añadir la acepción.";            
                }
                
            }
            
        } else {       
            return "\n El vocablo no existe.";            
        }
 
    }
    
    
    /**
     * Edita el ejemplo de una acepción.
     * @param acepcion Acepción que se va a editar.
     * @param ejemplo Nuevo ejemplo para la acepción.
     * @return Resultado de la operación.
     */
    public String editar(String acepcion, String ejemplo){
            
        // Si la acepción está en la base de datos, 
        // se procede a editarla.
        
        if(this.mostrarAcepciones().contains(new Acepcion(acepcion, ""))){
            
            String query = "UPDATE Acepcion SET ejemplo = ? where acepcion = ?";
        
            try {
            
                PreparedStatement pst = cnx.getConexion().prepareStatement(query);
            
                pst.setString(1, ejemplo);
                pst.setString(2, acepcion);
                pst.execute();
                    
                return "\n Acepción editada.";
            
            } catch (SQLException ex) {                   
                return "\n Error. \n No se pudo editar la acepción.";            
            }
                
        } else {               
            return "\n La acepción no existe";       
        }
 
    }
    
    
    /**
     * Borra una acepción para un vocablo dado.
     * @param acepcion Acepción que se remueve del vocablo.
     * @param vocablo Vocablo indicado.
     * @return Resultado de la operación.
     */
    public String borrar(String acepcion, String vocablo){
        
        VocabloControlador vocabloControlador = new VocabloControlador(cnx);
        
        // Si el vocablo existe y posee la acepción, se le quita. 
        
        if(vocabloControlador.mostrarVocablos().contains(new Vocablo(vocablo,"", ""))){
            
            if(this.acepcionesVocablo(vocablo).contains(new Acepcion(acepcion, ""))){
            
                String query = "DELETE from Acepciones where acepcion = ? and vocablo = ?";
        
                try {
            
                    PreparedStatement pst = cnx.getConexion().prepareStatement(query);
            
                    pst.setString(1, acepcion);
                    pst.setString(2, vocablo);
            
                    pst.execute();
            
                } catch (SQLException ex) {            
                    return "\n Error. \n No se pudo borrar la acepción.";                
                }
                
                // Cuando ningún vocablo posee la acepción 
                // en la base de datos, esta se elimina.
                
                ArrayList<String> vocablos_asociados = new ArrayList<>();
                
                query = "Select vocablo from Acepciones where acepcion = ?";
        
                try {
            
                    PreparedStatement pst = cnx.getConexion().prepareStatement(query);            
                    pst.setString(1, acepcion);      
                    pst.execute();
                    
                    ResultSet rs = pst.executeQuery();                    
                    while (rs.next()){            
                        vocablos_asociados.add(rs.getString(1));                
                    }
            
                } catch (SQLException ex) {           
                    return "\n  Acepción borrada.";               
                }
                
                if(vocablos_asociados.isEmpty()){
                
                    query = "DELETE from Acepcion where acepcion = ?";
        
                    try {
            
                        PreparedStatement pst = cnx.getConexion().prepareStatement(query);            
                        pst.setString(1, acepcion);   
                        pst.execute();
            
                    } catch (SQLException ex) {            
                        return "\n  Acepción borrada.";                
                    }
                
                } 
                
                return "\n  Acepción borrada del diccionario.";
                
            } else {                
                return "\n El vocablo no contiene la acepción.";                
            }

        } else {
            return "\n El vocablo no existe.";         
        }
 
    }
    
}