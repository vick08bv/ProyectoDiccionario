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
public class VocabloControlador {
    
    private Conexion cnx = null;

    /**
     * Constructor.
     * @param cnx - La conexion establecida.
     */
    public VocabloControlador(Conexion cnx) {
        
        this.cnx = cnx;
    
    }
    
    public ArrayList<Vocablo> mostrarVocablos() {
        
        ArrayList<Vocablo> entradas = new ArrayList<>(25);
        
        String query = "SELECT * FROM Vocablo";
        
        try {
            
            PreparedStatement pst = cnx.getConexion().prepareStatement(query);
            
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()){
            
                entradas.add(new Vocablo(
                            rs.getString(1),
                            rs.getString(2),
                            rs.getString(3)));
                
            }
            
        } catch (SQLException ex) {
            
        }
        
        return entradas;
        
    }
    
    
    public void agregar(String vocablo, String categoria, String es_soez){
        
        if(this.mostrarVocablos().contains(new Vocablo(vocablo, categoria, es_soez))){
            
        
        } else {
        
            String query = "INSERT INTO Vocablo (vocablo, categoria, es_soez) VALUES (?, ?, ?)";
        
            try {
            
                PreparedStatement pst = cnx.getConexion().prepareStatement(query);
            
                pst.setString(1, vocablo);
                pst.setString(2, categoria);
                pst.setString(3, es_soez);
            
                pst.execute();
            
            } catch (SQLException ex) {
            
            }
        }
 
    }   
    
    
    public void borrar(String vocablo){
        
        if(this.mostrarVocablos().contains(new Vocablo(vocablo, "", ""))){   
            
            String query = "DELETE from Vocablo where vocablo = ?";
        
            try {
            
                PreparedStatement pst = cnx.getConexion().prepareStatement(query);
            
                pst.setString(1, vocablo);
            
                pst.execute();
            
            } catch (SQLException ex) {
            
            }
        
        } else {
        
        }
 
    }
    
    
}