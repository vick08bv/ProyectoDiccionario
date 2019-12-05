package conexion;

import java.sql.*;

/**
 * Clase donde definimos la conexion con nuestra base de datos.
 */
public class Conexion {
    
    // La conexion que queremos establecer.
    private Connection conexion = null;
    // *************************************************************************
    // IMPORTANTE: Para que funcione en sus equipos deben cambiar por su usuario
    // y contrasenia correspondientes.
    // Aqui va el usuario con el que nos conectamos a mysql.
    private final String usr = "root";
    // El password correspondiente al usuario.
    private final String pwd = "Perrita79@";
    // *************************************************************************
    // La base de datos que queremos utilizar.
    private final String db = "Diccionario";
    
    /**
     * Permite establecer una conexion con el servidor de mysql y la base de 
     * datos deseada.
     */
    public void conectar() {
        if(conexion == null){
            try {
                // Llamamos al menejador que estamos usando.
                Class.forName("com.mysql.jdbc.Driver");
                // Definimos la url de nuestra base de datos.
                String url = "jdbc:mysql://localhost:3306/Diccionario";
                conexion = DriverManager.getConnection(url, usr, pwd);
            } catch (SQLException sqlex) {
                System.out.println("No se logro establecer la conexion.");
                System.out.println(sqlex);
            } catch (ClassNotFoundException cnfe) {
                System.out.println("No se encontro el manejador.");
            }
        }
    }
    
    /**
     * Regresa la conexion, si es que existe.
     * @return La conexion establecida previamente.
     */
    public Connection getConexion() {
        return conexion;
    }
    
    /**
     * Termina la conexion con el servidor.
     */
    public void desconectar() {
        if(conexion != null) {
            try {
                conexion.close();
            } catch (SQLException ex) {
                System.out.println("Error al terminar la conexion.");
            }
        }
    }
}
