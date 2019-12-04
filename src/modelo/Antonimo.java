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
public class Antonimo {
    
    private String antonimo;
    private String vocablo;

    
    public Antonimo(String palabra, String vocablo) {
        
        this.antonimo = palabra;
        this.vocablo = vocablo;
        
    }

    public String getAntonimo() {
        
        return antonimo;
        
    }

    
    public String getVocablo() {
        
        return vocablo;
        
    }

    public void setAntonimo(String antonimo) {
        
        this.antonimo = antonimo;
        
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
        final Antonimo other = (Antonimo) obj;
        if (!Objects.equals(this.antonimo, other.antonimo)) {
            return false;
        }
        if (!Objects.equals(this.vocablo, other.vocablo)) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return "\n  " + antonimo;
    }

    
}