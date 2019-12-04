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
import modelo.Acepcion;
import modelo.Antonimo;
import modelo.Sinonimo;
import modelo.Derivado;


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
    
    
    public String agregar(String vocablo, String categoria, String es_soez){
        
        if(this.mostrarVocablos().contains(new Vocablo(vocablo, "", ""))){        
        
            return "\n Vocablo ya existente";
            
        } else {
        
            String query = "INSERT INTO Vocablo (vocablo, categoria, es_soez) VALUES (?, ?, ?)";
        
            try {
            
                PreparedStatement pst = cnx.getConexion().prepareStatement(query);
            
                pst.setString(1, vocablo);
                pst.setString(2, categoria);
                pst.setString(3, es_soez);
            
                pst.execute();
                
                return "\n Vocablo añadido";
            
            } catch (SQLException ex) {
                
                return "\n Error. \n No se pudo agregar el vocablo.";
            
            }
        }
 
    }
    
    
    public String editar(String vocablo, String categoria, String es_soez){
        
        if(this.mostrarVocablos().contains(new Vocablo(vocablo, "", ""))){
            
            String query = "UPDATE Vocablo SET categoria = ?, es_soez = ? where Vocablo = ?";
        
            try {
            
                PreparedStatement pst = cnx.getConexion().prepareStatement(query);
           
                pst.setString(1, categoria);
                pst.setString(2, es_soez);
                pst.setString(3, vocablo);
                
                pst.execute();
                
                return "\n Vocablo editado correctamente";
            
            } catch (SQLException ex) {
            
                return "\n Error. \n No se pudo editar el vocablo.";
                
            }
        
        } else {
        
            return "\n El vocablo no existe";
            
        }
 
    }
    
    
    public String borrar(String vocablo){
        
        if(this.mostrarVocablos().contains(new Vocablo(vocablo, "", ""))){   
            
            AcepcionControlador ac = new AcepcionControlador(cnx);
            AntonimoControlador anc = new AntonimoControlador(cnx);
            SinonimoControlador sic = new SinonimoControlador(cnx);
            DerivadoControlador dec = new DerivadoControlador(cnx);
        
            ArrayList<Acepcion> acepciones = ac.acepcionesVocablo(vocablo);
            ArrayList<Sinonimo> sinonimos = sic.sinonimosVocablo(vocablo);
            ArrayList<Antonimo> antonimos = anc.antonimosVocablo(vocablo);
            ArrayList<Derivado> derivados = dec.derivadosVocablo(vocablo);
        
            for(Acepcion acepcion: acepciones){
        
                ac.borrar(acepcion.getAcepcion(), vocablo);
        
            }
        
            for(Sinonimo sinonimo: sinonimos){
        
                sic.borrar(sinonimo.getSinonimo(), vocablo);
        
            }
        
            for(Antonimo antonimo: antonimos){
        
                anc.borrar(antonimo.getAntonimo(), vocablo);
        
            }
        
            for(Derivado derivado: derivados){
        
                dec.borrar(derivado.getDerivado(), vocablo);
        
            }
            
            String query = "DELETE from Vocablo where vocablo = ?";
        
            try {
            
                PreparedStatement pst = cnx.getConexion().prepareStatement(query);
            
                pst.setString(1, vocablo);
            
                pst.execute();
                
                return "\n Vocablo borrado.";
            
            } catch (SQLException ex) {
            
                return "\n Error. \n No se pudo borrar el vocablo.";
                
            }
        
        } else {
            
            return "\n El vocablo no existe";
        
        }
 
    }
    
    
}