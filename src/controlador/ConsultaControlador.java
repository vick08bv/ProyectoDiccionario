package controlador;


import conexion.Conexion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;


/**
 * Controlador para las consultas especiales del adminisitrador.
 */
public class ConsultaControlador {
   
    
    private Conexion cnx = null;
    private String info = "";
    
    
    /**
     * Constructor.
     * @param cnx
     */
    public ConsultaControlador(Conexion cnx) {
        
        this.cnx = cnx;
    
    }
    
    
    /**
     * Consulta especial.
     * @param consulta Consulta por realizar.
     * @return Resultados
     */
    public ResultSet consulta(String consulta){
        
        if(consulta.replace("\n", "").replace("\t", "").replace(" ", "").equals("")){            
            info = "\t\t       Consulta vacía.";
            return null;
        }
    
        try {
            
            PreparedStatement pst = cnx.getConexion().prepareStatement(consulta);           
            ResultSet rs = pst.executeQuery();
            info = "\t\t     Operación realizada.";
            return rs;
            
        } catch (SQLException ex) {            
            info = ex.toString();           
            return null;          
        }
    
    }
    
    
    /**
     * Informació sobre las excepciones ocurridas.
     * @return 
     */
    public String getInfo(){
        
        return this.info;
    
    }
    
    
}
