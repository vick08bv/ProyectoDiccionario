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
     * Verifica si un usuario está registrado como administrador.
     * @param usuario Usuario que desea ingresar.
     * @param contrasena Constrasena del usuario.
     * @return Resultados
     */
    public boolean ingresa(String usuario, String contrasena){
    
        String query = "select contrasena from Administrador where usuario = ?";
        
        try {
            
            PreparedStatement pst = cnx.getConexion().prepareStatement(query);            
            pst.setString(1, usuario);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){            
                return rs.getString(1).equals(contrasena);
            } else {
                return false;
            }
            
        } catch (SQLException ex) {  
            System.out.println(ex);
            return false;
        }
    
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
            
            pst.execute();
            info = "\t\t     Operación realizada.";
            return pst.getResultSet();
            
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
