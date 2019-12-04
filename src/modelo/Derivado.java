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
public class Derivado {
    
    private String derivado;
    private String vocablo;

    
    public Derivado(String palabra, String vocablo) {
        
        this.derivado = palabra;
        this.vocablo = vocablo;
        
    }

    public String getDerivado() {
        
        return derivado;
        
    }

    
    public String getVocablo() {
        
        return vocablo;
        
    }

    public void setDerivado(String derivado) {
        
        this.derivado = derivado;
        
    }

    
    public void setVocablo(String vocablo) {
        
        this.vocablo = vocablo;
        
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
        final Derivado other = (Derivado) obj;
        if (!Objects.equals(this.derivado, other.derivado)) {
            return false;
        }
        if (!Objects.equals(this.vocablo, other.vocablo)) {
            return false;
        }
        return true;
    }
    
    
    
    @Override
    public String toString() {
        return "\n  " + derivado;
    }
    
    
}
