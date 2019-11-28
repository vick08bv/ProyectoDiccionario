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
import modelo.Acepcion;
import modelo.Vocablo;
import modelo.Derivado;
import modelo.Sinonimo;
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
        
        String query = "SELECT * FROM Derivado WHERE vocablo = ?";
        
        try {
            
            PreparedStatement pst = cnx.getConexion().prepareStatement(query);
            
            pst.setString(1, vocablo);
            
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()){
            
                entradas.add(new Antonimo(
                            rs.getString(1),
                            rs.getString(2)));
                
            }
            
        } catch (SQLException ex) {
           
        }
        
        return entradas;
        
    }
    
    
    public void agregar(String palabra, String vocablo){
        
        VocabloControlador vocabloControlador = new VocabloControlador(cnx);
        
        if(vocabloControlador.mostrarVocablos().contains(new Vocablo(vocablo,"", ""))){
            
            if(this.antonimosVocablo(vocablo).contains(new Antonimo(palabra, vocablo))){
            
            } else {
            
                String query = "INSERT INTO Antonimo (palabra, vocablo) VALUES (?, ?)";
        
                try {
            
                    PreparedStatement pst = cnx.getConexion().prepareStatement(query);
            
                    pst.setString(1, palabra);
                    pst.setString(3, vocablo);
            
                    pst.execute();
            
                } catch (SQLException ex) {
            
                }
                
            }
            
        } else {
        
        }
 
    }
    
    
}