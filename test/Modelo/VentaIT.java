/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Usuario
 */
public class VentaIT {
    
    public VentaIT() {
    }

    /**
     * Test of getNumero method, of class Venta.
     */
    @Test
    public void testGetNumero() {
        System.out.println("getNumero");
        Venta instance = null;
        int expResult = 0;
        int result = instance.getNumero();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFechaHoraGeneracion method, of class Venta.
     */
    @Test
    public void testGetFechaHoraGeneracion() {
        System.out.println("getFechaHoraGeneracion");
        Venta instance = null;
        LocalDateTime expResult = null;
        LocalDateTime result = instance.getFechaHoraGeneracion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFechaHoraActualizacion method, of class Venta.
     */
    @Test
    public void testGetFechaHoraActualizacion() {
        System.out.println("getFechaHoraActualizacion");
        Venta instance = null;
        LocalDateTime expResult = null;
        LocalDateTime result = instance.getFechaHoraActualizacion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSuCliente method, of class Venta.
     */
    @Test
    public void testGetSuCliente() {
        System.out.println("getSuCliente");
        Venta instance = null;
        Cliente expResult = null;
        Cliente result = instance.getSuCliente();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSusPaquetesTuristicos method, of class Venta.
     */
    @Test
    public void testGetSusPaquetesTuristicos() {
        System.out.println("getSusPaquetesTuristicos");
        Venta instance = null;
        ArrayList<PaqueteTuristico> expResult = null;
        ArrayList<PaqueteTuristico> result = instance.getSusPaquetesTuristicos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEstado method, of class Venta.
     */
    @Test
    public void testGetEstado() {
        System.out.println("getEstado");
        Venta instance = null;
        char expResult = ' ';
        char result = instance.getEstado();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNumero method, of class Venta.
     */
    @Test
    public void testSetNumero() {
        System.out.println("setNumero");
        int numero = 0;
        Venta instance = null;
        instance.setNumero(numero);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFechaHoraGeneracion method, of class Venta.
     */
    @Test
    public void testSetFechaHoraGeneracion() {
        System.out.println("setFechaHoraGeneracion");
        LocalDateTime fechaHoraGeneracion = null;
        Venta instance = null;
        instance.setFechaHoraGeneracion(fechaHoraGeneracion);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFechaHoraActualizacion method, of class Venta.
     */
    @Test
    public void testSetFechaHoraActualizacion() {
        System.out.println("setFechaHoraActualizacion");
        LocalDateTime fechaHoraActualizacion = null;
        Venta instance = null;
        instance.setFechaHoraActualizacion(fechaHoraActualizacion);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSuCliente method, of class Venta.
     */
    @Test
    public void testSetSuCliente() {
        System.out.println("setSuCliente");
        Cliente suCliente = null;
        Venta instance = null;
        instance.setSuCliente(suCliente);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSusPaquetesTuristicos method, of class Venta.
     */
    @Test
    public void testSetSusPaquetesTuristicos() {
        System.out.println("setSusPaquetesTuristicos");
        ArrayList<PaqueteTuristico> susPaquetesTuristicos = null;
        Venta instance = null;
        instance.setSusPaquetesTuristicos(susPaquetesTuristicos);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEstado method, of class Venta.
     */
    @Test
    public void testSetEstado() {
        System.out.println("setEstado");
        char estado = ' ';
        Venta instance = null;
        instance.setEstado(estado);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Venta.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Venta instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calcularCantidadTotalUnidadesPaquetes method, of class Venta.
     */
    @Test
    public void testCalcularCantidadTotalUnidadesPaquetes() {
        System.out.println("calcularCantidadTotalUnidadesPaquetes");
        Venta instance = null;
        int expResult = 0;
        int result = instance.calcularCantidadTotalUnidadesPaquetes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calcularValorTotalPaquetes method, of class Venta.
     */
    @Test
    public void testCalcularValorTotalPaquetes() {
        System.out.println("calcularValorTotalPaquetes");
        Venta instance = null;
        int expResult = 0;
        int result = instance.calcularValorTotalPaquetes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calcularValorDescuento method, of class Venta.
     */
    @Test
    public void testCalcularValorDescuento() {
        System.out.println("calcularValorDescuento");
        Venta instance = null;
        int expResult = 0;
        int result = instance.calcularValorDescuento();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calcularValorTotalPagar method, of class Venta.
     */
    @Test
    public void testCalcularValorTotalPagar() {
        System.out.println("calcularValorTotalPagar");
        Venta instance = null;
        int expResult = 0;
        int result = instance.calcularValorTotalPagar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
