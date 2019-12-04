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
import modelo.Derivado;


/**
 *
 * @author vick0
 */
public class DerivadoControlador {
    
    private Conexion cnx = null;

    /**
     * Constructor.
     * @param cnx - La conexion establecida.
     */
    public DerivadoControlador(Conexion cnx) {
        
        this.cnx = cnx;
    
    }
    
    public ArrayList<Derivado> derivadosVocablo(String vocablo) {       
        
        ArrayList<Derivado> entradas = new ArrayList<>(5);
        
        String query = "SELECT derivado FROM Derivados \n"
                     + "INNER JOIN Vocablo ON Derivados.vocablo = Vocablo.vocablo \n" 
                     + "WHERE Vocablo.vocablo = ?";
        
        try {
            
            PreparedStatement pst = cnx.getConexion().prepareStatement(query);
            
            pst.setString(1, vocablo);
            
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()){
            
                entradas.add(new Derivado(rs.getString(1),vocablo));
                
            }
            
        } catch (SQLException ex) {
           
        }
        
        return entradas;
        
    }
    
    
    public String agregar(String derivado, String vocablo){
        
        VocabloControlador vocabloControlador = new VocabloControlador(cnx);
        
        if(vocabloControlador.mostrarVocablos().contains(new Vocablo(vocablo, "", ""))){
            
            if(this.derivadosVocablo(vocablo).contains(new Derivado(derivado, vocablo))){
            
                return "El derivado ya existe";
                
            } else {
            
                String query = "INSERT INTO Derivados (derivado, vocablo) VALUES (?, ?)";
        
                try {
            
                    PreparedStatement pst = cnx.getConexion().prepareStatement(query);
            
                    pst.setString(1, derivado);
                    pst.setString(2, vocablo);
            
                    pst.execute();
                    
                    return "\n Derivado añadido.";
            
                } catch (SQLException ex) {                  
                    
                    return "\n Error. \n No se puedo añadir el derivado.";
            
                }
                
            }
            
        } else {
                        
            return "\n El vocablo no existe";
        
        }
        
    }
        
        
    public String borrar(String derivado, String vocablo){
        
        VocabloControlador vocabloControlador = new VocabloControlador(cnx);
        
        if(vocabloControlador.mostrarVocablos().contains(new Vocablo(vocablo,"", ""))){
            
            if(this.derivadosVocablo(vocablo).contains(new Derivado(derivado, vocablo))){
            
                String query = "DELETE from Derivados where derivado = ? and vocablo = ?";
        
                try {
            
                    PreparedStatement pst = cnx.getConexion().prepareStatement(query);
            
                    pst.setString(1, derivado);
                    pst.setString(2, vocablo);
            
                    pst.execute();
                
                    return "\n Derivado borrado.";
            
                } catch (SQLException ex) {
            
                    return "\n Error. \n No se pudo borrar el derivado.";
                
                }
                
            } else {
                
                return "\n El derivado no existe.";
                
            }

        } else {
        
            return "\n El vocablo no existe.";
            
        }
 
    }
        
    
}
