package modelo;

import java.util.Objects;

/**
 * Palabra relacionada a un vocablo.
 */
public class Relacionado {
    
    
    private final String palabra;
    private final String tipo;

    
    /**{
     * Constructor.
     * @param palabra Palabra
     * @param tipo Tipo de palabra
     */
    public Relacionado(String palabra, String tipo) {
        this.palabra = palabra;
        this.tipo = tipo;
    }

    
    public String getPalabra() {
        return palabra;
    }

    
    public String getTipo() {
        return tipo;
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
        final Relacionado other = (Relacionado) obj;
        if (!Objects.equals(this.palabra, other.palabra)) {
            return false;
        }
        return Objects.equals(this.tipo, other.tipo);
    }
    

    @Override
    public String toString() {
        return "\n  " + palabra;
    }

    
}
