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
    private String vocablo;

    
    public Acepcion(String acepcion, String ejemplo, String vocablo) {
        
        this.acepcion = acepcion;
        this.ejemplo = ejemplo;
        this.vocablo = vocablo;
        
    }
    

    public String getAcepcion() {
            
        return acepcion;
    
    }

    
    public String getEjemplo() {

        return ejemplo;

    }

    
    public String getVocablo() {

        return vocablo;

    }
    

    public void setAcepcion(String acepcion) {
        
        this.acepcion = acepcion;
        
    }
    

    public void setEjemplo(String ejemplo) {
        
        this.ejemplo = ejemplo;
        
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
        final Acepcion other = (Acepcion) obj;
        if (!Objects.equals(this.acepcion, other.acepcion)) {
            return false;
        }
        if (!Objects.equals(this.ejemplo, other.ejemplo)) {
            return false;
        }
        if (!Objects.equals(this.vocablo, other.vocablo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Acepcion{" + "acepcion=" + acepcion + ", ejemplo=" + ejemplo + ", vocablo=" + vocablo + '}';
    }

    
}
