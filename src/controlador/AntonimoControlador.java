/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Vocablo;
import modelo.Antonimo;

/**
 *
 * @author vick0
 */
public class AntonimoControlador {
    
    private Conexion cnx = null;

    /**
     * Constructor.
     * @param cnx - La conexion establecida.
     */
    public AntonimoControlador(Conexion cnx) {
        
        this.cnx = cnx;
    
    }
    
    public ArrayList<Antonimo> antonimosVocablo(String vocablo) {       
        
        ArrayList<Antonimo> entradas = new ArrayList<>(5);
        
        String query = "SELECT antonimo FROM Antonimos \n"
                     + "INNER JOIN Vocablo ON Antonimos.vocablo = Vocablo.vocablo \n" 
                     + "WHERE Vocablo.vocablo = ?";
        
        try {
            
            PreparedStatement pst = cnx.getConexion().prepareStatement(query);
            
            pst.setString(1, vocablo);
            
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()){
            
                entradas.add(new Antonimo(rs.getString(1),vocablo));
                
            }
            
        } catch (SQLException ex) {
           
        }
        
        return entradas;
        
    }
    
    
    public String agregar(String antonimo, String vocablo){
        
        VocabloControlador vocabloControlador = new VocabloControlador(cnx);
        
        if(vocabloControlador.mostrarVocablos().contains(new Vocablo(vocablo,"", ""))){
            
            if(this.antonimosVocablo(vocablo).contains(new Antonimo(antonimo, vocablo))){
            
                return "\n El antónimo ya existe";
                
            } else {
            
                String query = "INSERT INTO Antonimos (antonimo, vocablo) VALUES (?, ?)";
        
                try {
            
                    PreparedStatement pst = cnx.getConexion().prepareStatement(query);
            
                    pst.setString(1, antonimo);
                    pst.setString(2, vocablo);
            
                    pst.execute();
                    
                    return "\n Antónimo añadido";
            
                } catch (SQLException ex) {
                    
                    return "\n Error. \n No se pudo añadir el antónimo.";
            
                }
                
            }
            
        } else {
        
            return "El vocablo no existe";
            
        }
 
    }
    
    
    public String borrar(String antonimo, String vocablo){
        
        VocabloControlador vocabloControlador = new VocabloControlador(cnx);
        
        if(vocabloControlador.mostrarVocablos().contains(new Vocablo(vocablo,"", ""))){
            
            if(this.antonimosVocablo(vocablo).contains(new Antonimo(antonimo, vocablo))){
            
                String query = "DELETE from Antonimos where antonimo = ? and vocablo = ?";
        
                try {
            
                    PreparedStatement pst = cnx.getConexion().prepareStatement(query);
            
                    pst.setString(1, antonimo);
                    pst.setString(2, vocablo);
            
                    pst.execute();
                
                    return "\n Antónimo borrado.";
            
                } catch (SQLException ex) {
            
                    return "\n Error. \n No se pudo borrar el antónimo.";
                
                }
                
            } else {
                
                return "\n El antónimo no existe.";
                
            }

        } else {
        
            return "\n El vocablo no existe.";
            
        }
 
    }
    
    
}