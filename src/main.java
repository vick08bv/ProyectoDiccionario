/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import modelo.Acepcion;
import modelo.Vocablo;
import modelo.Derivado;
import modelo.Sinonimo;
import modelo.Antonimo;

import controlador.AcepcionControlador;
import controlador.AntonimoControlador;
import controlador.DerivadoControlador;
import controlador.SinonimoControlador;
import controlador.VocabloControlador;

/**
 *
 * @author vick0
 */
public class main {
    
    public static void main(String s[]){
        
        conexion.Conexion cnx = new conexion.Conexion();
    
        ArrayList<Vocablo> l = new ArrayList<Vocablo>(4);
    
        l.add(new Vocablo("Chairo", "Sustantivo", "si"));
        l.add(new Vocablo("Fifi", "Adjetivo", "no"));
        
        Acepcion p = new Acepcion("Rico","Los fifis odian a lopez", "Fifi");
        
        boolean c = l.contains(new Vocablo("Chairo", "Sustantivo", "si"));
        
        AcepcionControlador acc = new AcepcionControlador(cnx);
        VocabloControlador vcc = new VocabloControlador(cnx);
        
        cnx.conectar();
        
        System.out.println(cnx.getConexion());
        
        Vocablo r = new Vocablo("Chairo", "Sustantivo", "si");
        Vocablo u = new Vocablo("Chairo", "Adjetivo", "no");
        
        System.out.println(r.equals(u));
                
        
//        vcc.agregar("Fifi", "Adjetivo", "no");
//       acc.agregar("Rico","Los fifis odian a lopez", "Fifi");
        
        l = vcc.mostrarVocablos();
        
        for(Vocablo v: l){
        
            System.out.println(v);
        
        }
        
    }
     
}
