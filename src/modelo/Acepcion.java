/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Objects;

/**
 *
 * @author vick0
 */
public class Acepcion {
    
    private String acepcion;
    private String ejemplo;

    
    public Acepcion(String acepcion, String ejemplo) {
        
        this.acepcion = acepcion;
        this.ejemplo = ejemplo;
        
    }
    

    public String getAcepcion() {
            
        return acepcion;
    
    }

    
    public String getEjemplo() {

        return ejemplo;

    }
    
    
    public void setAcepcion(String acepcion) {
        
        this.acepcion = acepcion;
        
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
        final Acepcion other = (Acepcion) obj;
        if (!Objects.equals(this.acepcion, other.acepcion)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "\n  " + acepcion + ":  " + ejemplo;
    }

    
}
