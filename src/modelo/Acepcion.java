package modelo;


import java.util.Objects;


/**
 * Acepción para un vocablo.
 */
public class Acepcion {
    
    
    private final String acepcion;
    private String ejemplo;
    private String explicacion;

    
    /**
     * Constructor.
     * @param acepcion Acepción.
     * @param ejemplo Ejemplo del uso de la acepción.
     * @param explicacion Explicación del ejemplo usado.
     */
    public Acepcion(String acepcion, String ejemplo, String explicacion) {
        
        this.acepcion = acepcion;
        this.ejemplo = ejemplo;
        this.explicacion = explicacion;
        
    }
    

    public String getAcepcion() {
            
        return acepcion;
    
    }

    
    public String getEjemplo() {

        return ejemplo;

    }
    

    public void setEjemplo(String ejemplo) {
        
        this.ejemplo = ejemplo;
        
    }

    
    public void setExplicacion(String explicacion) {
        
        this.explicacion = explicacion;
        
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Acepcion other = (Acepcion) obj;
        if (!Objects.equals(this.acepcion, other.acepcion)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        
        String ejemplo = this.ejemplo; String explicacion = this.explicacion;
        
        if(ejemplo == null){
            ejemplo = ""; explicacion = "";
        } else {
            if(explicacion == null){
                explicacion = "";
            }
        }
        
        return "\n " + acepcion + ": " + ejemplo + " (" + explicacion + ")";
    }

    
}
