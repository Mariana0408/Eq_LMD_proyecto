/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author adria
 */
public class VentaTest {
    
       private Cliente crearClienteConDescuento(double porcentaje) {
        return new Cliente('C', "123456", false, "Juan Perez","juan@email.com", "3001234567", "Juan Perez", porcentaje);
    }
 
    private ArrayList<PaqueteTuristico> crearPaquetesUnico() {
        ArrayList<Destino> destinos = new ArrayList<>();
        LinkedList<String> atractivos= new LinkedList<>();
        atractivos.add("Cartagena");
        atractivos.add("Santa Marta");
        destinos.add(new Destino("Costa Atlantica", 5, atractivos, true));
        destinos.add(new Destino("Sierra Nevada", 3, atractivos, true));
 
        ArrayList<PaqueteTuristico> paquetes = new ArrayList<>();
        paquetes.add(new PaqueteTuristicoUnico(
                "Hotel Prueba", "buffet", "Paq1", "recreacion",
                "viaje familiar", "Bogota", destinos,
                true, true, true, true, true, 250000, 2));
        return paquetes;
    }
 
    private ArrayList<PaqueteTuristico> crearPaquetesMixtos() {
        ArrayList<Destino> destinos1 = new ArrayList<>();
     
        LinkedList<String> atractivos= new LinkedList<>();
        atractivos.add("Cartagena");
        destinos1.add(new Destino("Costa Atlantica", 5, atractivos, true));
        destinos1.add(new Destino("Sierra Nevada", 3, atractivos, true));
 
        ArrayList<Destino> destinos2 = new ArrayList<>();
        destinos2.add(new Destino("Medellin", 4, atractivos, true));
        destinos2.add(new Destino("Guatape", 2, atractivos, false));
 
        ArrayList<PaqueteTuristico> paquetes = new ArrayList<>();
 
        paquetes.add(new PaqueteTuristicoUnico(
                "Hotel Sol", "americano", "Paq2", "aventura",
                "viaje pareja", "Cali", destinos1,
                true, true, false, true, false, 100000, 1));
 
        paquetes.add(new PaqueteTuristicoMultiple(
                "Camiseta", "Paq3", "paquete multiple", "cultural",
                "viaje grupo", "Medellin", destinos2,
                true, true, true, false, true, 200000, 3));
 
        return paquetes;
    }



    /**
     * Test of calcularCantidadTotalUnidadesPaquetes method, of class Venta.
     */
    @Test
    public void testCalcularCantidadTotalUnidadesPaquetes_UnPaquete() {
        System.out.println("calcularCantidadTotalUnidadesPaquetes-UnPaquete");
        ArrayList <PaqueteTuristico> paquetes = crearPaquetesUnico();
        Cliente cliente=crearClienteConDescuento (0.0);  
        Venta venta = new Venta(1,cliente,paquetes);
    
        int expResult = 2;
        int result = venta.calcularCantidadTotalUnidadesPaquetes();
        assertEquals(expResult, result);
    }
    @Test
    public void testCalcularCantidadTotalUnidadesPaquetes_VariosPaquetes() {
        System.out.println("calcularCantidadTotalUnidadesPaquetes-VariosPaquetes");
        ArrayList <PaqueteTuristico> paquetes = crearPaquetesMixtos();
        Cliente cliente=crearClienteConDescuento (0.0);  
        Venta venta = new Venta(1,cliente,paquetes);
    
        int expResult = 4;
        int result = venta.calcularCantidadTotalUnidadesPaquetes();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of calcularValorTotalPaquetes method, of class Venta.
     */
    
   @Test
 
    public void testCalcularValorTotalPaquetes_UnPaqueteUnico() {
        System.out.println("calcularValorTotalPaquetes-UnPaqueteUnico");
        Cliente cliente = crearClienteConDescuento (0.0);
        ArrayList<PaqueteTuristico> paquetes= crearPaquetesUnico();
        Venta venta = new Venta (1,cliente,paquetes);
        int expResult = 4000000;
        int result = venta.calcularValorTotalPaquetes();
        assertEquals(expResult, result);
        
    }
    //EXPLICACION de sto es:crearPaquetesUnico() — valorTotal = 4.000.000
    //crearPaquetesUnico() — valorTotal = 4.000.000
    //calcularDuracionTotalDias() = 8
    //calcularValorUnidad() = tarifaDia × días = 250.000 × 8 = 2.000.000
    //calcularValorTotal() = valorUnidad × cantidadUnidades = 2.000.000 × 2 = 4.000.000
    
    
    @Test

    public void testCalcularValorTotalPaquetes_VariosPaquetes() {
        System.out.println("calcularValorTotalPaquetes-VariosPaquetesMixtos");
        Cliente cliente = crearClienteConDescuento (0.0);
        ArrayList<PaqueteTuristico> paquetes= crearPaquetesMixtos();
        Venta venta = new Venta (2,cliente,paquetes);
        int expResult = 4412000;
        int result = venta.calcularValorTotalPaquetes();
        assertEquals(expResult, result);
        
        
    }

    //EXPLICACION de esto es:crearPaquetesMixtos() — valorTotal = 4.412.000
    //Paquete 1 — PaqueteTuristicoUnico (Hotel Sol):
    //calcularValorTotal() = 800.000 × 1 unidad = 800.000
    //Paquete 2 — PaqueteTuristicoMultiple (Camiseta):
    //calcularValorTotal() = 1.204.000 × 3 unidades = 3.612.000
    //Suma total:800.000 + 3.612.000 = 4.412.000
    
    
    
    /**
    * Test of calcularValorDescuento method, of class Venta.
     */
    @Test
    public void testCalcularValorDescuento_SinDescuento() {
        System.out.println("calcularValorDescuento-SinDescuento");
        Cliente cliente = crearClienteConDescuento(0.0);
        ArrayList<PaqueteTuristico> paquetes = crearPaquetesUnico();
        Venta venta = new Venta(1, cliente, paquetes);
        int expResult = 0;
        int result = venta.calcularValorDescuento();
        assertEquals(expResult, result);
    }

    @Test
    public void testCalcularValorDescuento_ConDescuento() {
        System.out.println("calcularValorDescuento-ConDescuento");
        Cliente cliente = crearClienteConDescuento(10.0);
        ArrayList<PaqueteTuristico> paquetes = crearPaquetesUnico();
        Venta venta = new Venta(1, cliente, paquetes);
        int expResult = 400000;
        int result = venta.calcularValorDescuento();
        assertEquals(expResult, result);
    }

    /**
    * Test of calcularValorTotalPagar method, of class Venta.
    */
   

    @Test
    public void testCalcularValorTotalPagar_ConDescuento() {
        System.out.println("calcularValorTotalPagar-ConDescuento");
        Cliente cliente = crearClienteConDescuento(10.0);
        ArrayList<PaqueteTuristico> paquetes = crearPaquetesUnico();
        Venta venta = new Venta(1, cliente, paquetes);
        int expResult = 3600000;
        int result = venta.calcularValorTotalPagar();
        assertEquals(expResult, result);
    }

    @Test
    public void testCalcularValorTotalPagar_VariosPaquetesConDescuento() {
        System.out.println("calcularValorTotalPagar-VariosPaquetesConDescuento");
        Cliente cliente = crearClienteConDescuento(10.0);
        ArrayList<PaqueteTuristico> paquetes = crearPaquetesMixtos();
        Venta venta = new Venta(2, cliente, paquetes);
        int expResult = 3970800;
        int result = venta.calcularValorTotalPagar();
        assertEquals(expResult, result);
    }
}