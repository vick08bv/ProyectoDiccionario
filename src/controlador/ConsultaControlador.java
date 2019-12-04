/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

/**
 *
 * @author vick0
 */

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

public class ConsultaControlador {
   
    private Conexion cnx = null;
    private String info = "";
    
    /**
     * Constructor.
     * @param cnx - La conexion establecida.
     */
    public ConsultaControlador(Conexion cnx) {
        
        this.cnx = cnx;
    
    }
    
    public ResultSet consulta(String query){
    
        try {
            
            PreparedStatement pst = cnx.getConexion().prepareStatement(query);
            
            ResultSet rs = pst.executeQuery();
            
            info = "";
            
            return rs;
            
        } catch (SQLException ex) {
            
            info = ex.toString();
            
            return null;
            
        }
    
    }
    
    
    public String getInfo(){
        
        return this.info;
    
    }
    
    
}
