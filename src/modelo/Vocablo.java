package modelo;


import java.util.Objects;


/**
 * Vocablo.
 */
public class Vocablo {
    
    
    private final String vocablo;
    private String categoria;
    private boolean es_soez;
    private String ejemplo;
    
    
    /**
     * Constructor
     * @param vocablo Palabra.
     * @param categoria Categoría gramatical a la que pertenece.
     * @param es_soez Indica si el término es soez.
     * @param ejemplo Frase de ejemplo.
     */
    public Vocablo(String vocablo, String categoria, boolean es_soez, String ejemplo) {
        
        this.vocablo = vocablo;
        this.categoria = categoria;
        this.es_soez = es_soez;
        this.ejemplo = ejemplo;
        
    }

    
    public String getVocablo() {
        
        return vocablo;
        
    }
    

    public String getCategoria() {
        
        return categoria;
        
    }

    public boolean isEs_soez() {
        
        return es_soez;
        
    }

    
    public String getEjemplo() {
    
        return ejemplo;
    
    }
    

    public void setCategoria(String categoria){
            
        this.categoria = categoria;
    
    }

    public void setEs_soez(boolean es_soez) {

        this.es_soez = es_soez;

    }
    
    
    public void setEjemplo(String ejemplo) {
        
        this.ejemplo = ejemplo;
    
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
        final Vocablo other = (Vocablo) obj;
        if (!Objects.equals(this.vocablo, other.vocablo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return "\n  " + vocablo + categoria + ejemplo;
    
    }
     
    
}
