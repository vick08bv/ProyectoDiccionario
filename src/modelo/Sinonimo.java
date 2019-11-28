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
public class Sinonimo {
    
    private String palabra;
    private String vocablo;

    
    public Sinonimo(String palabra, String vocablo) {
        
        this.palabra = palabra;
        this.vocablo = vocablo;
        
    }

    public String getPalabra() {
        
        return palabra;
        
    }

    
    public String getVocablo() {
        
        return vocablo;
        
    }

    public void setPalabra(String palabra) {
        
        this.palabra = palabra;
        
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
        final Sinonimo other = (Sinonimo) obj;
        if (!Objects.equals(this.palabra, other.palabra)) {
            return false;
        }
        if (!Objects.equals(this.vocablo, other.vocablo)) {
            return false;
        }
        return true;
    }
   
    
}