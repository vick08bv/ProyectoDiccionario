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
     * @param cnx.
     */
    public AcepcionControlador(Conexion cnx) {
        
        this.cnx = cnx;
    
    }
    
    
    public ArrayList<Acepcion> mostrarAcepciones() {
        
        ArrayList<Acepcion> entradas = new ArrayList<>(25);
        
        String query = "SELECT * FROM Acepcion";
        
        try {
            
            PreparedStatement pst = cnx.getConexion().prepareStatement(query);
            
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()){
            
                entradas.add(new Acepcion(
                            rs.getString(1),
                            rs.getString(2)));
                
            }
            
        } catch (SQLException ex) {
            
        }
        
        return entradas;
        
    }
    
    
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
    

    public String agregar(String acepcion, String ejemplo, String vocablo){
        
        VocabloControlador vocabloControlador = new VocabloControlador(cnx);
        
        if(!this.mostrarAcepciones().contains(new Acepcion(acepcion, ejemplo))){
            
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
        
        if(vocabloControlador.mostrarVocablos().contains(new Vocablo(vocablo, "", ""))){
            
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
    
    
    public String editar(String acepcion, String ejemplo){
            
        if(this.mostrarAcepciones().contains(new Acepcion(acepcion, ejemplo))){
            
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
    
    
    public String borrar(String acepcion, String vocablo){
        
        VocabloControlador vocabloControlador = new VocabloControlador(cnx);
        
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
                
                return "\n  Acepción borrada.";
                
            } else {
                
                return "\n La acepción no existe.";
                
            }

        } else {
        
            return "\n El vocablo no existe.";
            
        }
 
    }
    
}
