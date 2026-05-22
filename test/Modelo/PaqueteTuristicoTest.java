/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Modelo;

import Modelo.Destino;
import java.util.ArrayList;
import java.util.LinkedList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author adria
 */
public class PaqueteTuristicoTest {
    

private PaqueteTuristicoUnico elPaqueteTuristicoUnico;
private PaqueteTuristicoMultiple elPaqueteTuristicoMultiple;
  
    /**
     * Test of calcularDuracionTotalDias method, of class PaqueteTuristico.
     */
    @Test
    public void testCalcularDuracionTotalDias_Unico() {
        System.out.println("calcularDuracionTotalDias Unico");
        ArrayList<Destino> susDestinos=new ArrayList<>();
        Destino destino;
        LinkedList<String> atractivos= new LinkedList<>();
        atractivos.add("Cartagena");
        atractivos.add("Santa Marta");
        destino=new Destino("Costa Atlantica",5,atractivos,true);
        susDestinos.add(destino);
        destino=new Destino("Costa",3,atractivos,true);
        susDestinos.add(destino);
        PaqueteTuristicoUnico elPaqueteTuristicoUnico = new PaqueteTuristicoUnico("Hotel Prueba","buffet","Paq1","recreacion","viaje familiar","Bogota",susDestinos,true,true,true,true,true,250000,2);
        int expResult = 8;
        int result = elPaqueteTuristicoUnico.calcularDuracionTotalDias();
        assertEquals(expResult, result);
        
    }
    @Test
    public void testCalcularDuracionTotalDias_Multiple() {
        System.out.println("calcularDuracionTotalDias Multiple");
        ArrayList<Destino> susDestinos=new ArrayList<>();
        Destino destino;
        LinkedList<String> atractivos= new LinkedList<>();
        atractivos.add("Cartagena");
        atractivos.add("Santa Marta");
        destino=new Destino("Costa Atlantica",5,atractivos,true);
        susDestinos.add(destino);
        destino=new Destino("Costa",3,atractivos,true);
        susDestinos.add(destino);
    
       PaqueteTuristicoMultiple elPaqueteTuristicoMultiple = new PaqueteTuristicoMultiple("Camiseta","Paq1","paquete multiple","recreacion","viaje familiar","Bogota",susDestinos,true,true,true,true,true,250000,2);
        int expResult = 8;
        int result = elPaqueteTuristicoMultiple.calcularDuracionTotalDias();
        assertEquals(expResult, result);
        
    }
    

    /**
     * Test of calcularValorUnidad method, of class PaqueteTuristico.
     */

    @Test
    public void testCalcularValorUnidad_Unico() {
        System.out.println("calcularValorUnidad Unico");
        ArrayList<Destino> susDestinos = new ArrayList<>();
        Destino destino;
        LinkedList<String> atractivos = new LinkedList<>();
        atractivos.add("Cartagena");
        atractivos.add("Santa Marta");
        destino = new Destino("Costa Atlantica", 5, atractivos, true);
        susDestinos.add(destino);
        destino = new Destino("Sierra Nevada", 3, atractivos, true);
        susDestinos.add(destino);
        PaqueteTuristicoUnico elPaqueteTuristicoUnico = new PaqueteTuristicoUnico("Hotel Prueba", "buffet", "Paq1", "recreacion", "viaje familiar", "Bogota", susDestinos, true, true, true, true, true, 250000, 2);
        int expResult = 2000000;
        int result = elPaqueteTuristicoUnico.calcularValorUnidad();
        assertEquals(expResult, result);
}
    @Test
    public void testCalcularValorUnidad_Multiple() {
        System.out.println("calcularValorUnidad Multiple");
        ArrayList<Destino> susDestinos = new ArrayList<>();
        Destino destino;
        LinkedList<String> atractivos = new LinkedList<>();
        atractivos.add("Cartagena");
        atractivos.add("Santa Marta");
        destino = new Destino("Costa Atlantica", 5, atractivos, true);
        susDestinos.add(destino);
        destino = new Destino("Costa", 3, atractivos, true);
        susDestinos.add(destino);
        PaqueteTuristicoMultiple elPaqueteTuristicoMultiple = new PaqueteTuristicoMultiple("Camiseta", "Paq1", "paquete multiple", "recreacion", "viaje familiar", "Bogota", susDestinos, true, true, true, true, true, 250000, 2);
        int expResult = 2005000;
        int result = elPaqueteTuristicoMultiple.calcularValorUnidad();
        assertEquals(expResult, result);
}
    /**
     * Test of calcularValorTotal method, of class PaqueteTuristico.
     */
    
    @Test
public void testCalcularValorTotal_Unico() {
        System.out.println("calcularValorTotal Unico");
        ArrayList<Destino> susDestinos = new ArrayList<>();
        Destino destino;
        LinkedList<String> atractivos = new LinkedList<>();
        atractivos.add("Cartagena");
        atractivos.add("Santa Marta");
        destino = new Destino("Costa Atlantica", 5, atractivos, true);
        susDestinos.add(destino);
        destino = new Destino("Sierra Nevada", 3, atractivos, true);
        susDestinos.add(destino);
        PaqueteTuristicoUnico elPaqueteTuristicoUnico = new PaqueteTuristicoUnico("Hotel Prueba", "buffet", "Paq1", "recreacion", "viaje familiar", "Bogota", susDestinos, true, true, true, true, true, 250000, 2);
        int expResult = 4000000;
        int result = elPaqueteTuristicoUnico.calcularValorTotal();
         assertEquals(expResult, result);
}
   @Test
    public void testCalcularValorTotal_Multiple() {
        System.out.println("calcularValorTotal Multiple");
        ArrayList<Destino> susDestinos = new ArrayList<>();
        Destino destino;
        LinkedList<String> atractivos = new LinkedList<>();
        atractivos.add("Cartagena");
        atractivos.add("Santa Marta");
        destino = new Destino("Costa Atlantica", 5, atractivos, true);
        susDestinos.add(destino);
        destino = new Destino("Costa", 3, atractivos, true);
        susDestinos.add(destino);
        PaqueteTuristicoMultiple elPaqueteTuristicoMultiple = new PaqueteTuristicoMultiple("Camiseta", "Paq1", "paquete multiple", "recreacion", "viaje familiar", "Bogota", susDestinos, true, true, true, true, true, 250000, 2);
        int expResult = 4010000;
        int result = elPaqueteTuristicoMultiple.calcularValorTotal();
        assertEquals(expResult, result);
}
    
}
//unico y multiple para cada uno. 