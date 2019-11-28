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
public class Vocablo {
    
    private String vocablo;
    private String categoria;
    private String es_soez;

    public Vocablo(String vocablo, String categoria, String es_soez) {
        
        this.vocablo = vocablo;
        this.categoria = categoria;
        this.es_soez = es_soez;
        
    }

    public String getVocablo() {
        
        return vocablo;
        
    }

    public String getCategoria() {
        
        return categoria;
        
    }

    public String isEs_soez() {
        
        return es_soez;
        
    }

    public void setVocablo(String vocablo) {
        
        this.vocablo = vocablo;
        
    }

    public void setCategoria(String categoria){
            
        this.categoria = categoria;
    
    }

    public void setEs_soez(String es_soez) {

        this.es_soez = es_soez;

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

        return "Vocablo{" + "vocablo=" + vocablo + ", categoria=" + categoria + ", es_soez=" + es_soez + '}';
    
    }
     
}
