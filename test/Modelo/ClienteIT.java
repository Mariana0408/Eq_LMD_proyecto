/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Modelo;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Usuario
 */
public class ClienteIT {
    
    public ClienteIT() {
    }

    /**
     * Test of getTipoIdentificacion method, of class Cliente.
     */
    @Test
    public void testGetTipoIdentificacion() {
        System.out.println("getTipoIdentificacion");
        Cliente instance = null;
        char expResult = ' ';
        char result = instance.getTipoIdentificacion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumeroIdentificacion method, of class Cliente.
     */
    @Test
    public void testGetNumeroIdentificacion() {
        System.out.println("getNumeroIdentificacion");
        Cliente instance = null;
        String expResult = "";
        String result = instance.getNumeroIdentificacion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEmpresa method, of class Cliente.
     */
    @Test
    public void testIsEmpresa() {
        System.out.println("isEmpresa");
        Cliente instance = null;
        boolean expResult = false;
        boolean result = instance.isEmpresa();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNombre method, of class Cliente.
     */
    @Test
    public void testGetNombre() {
        System.out.println("getNombre");
        Cliente instance = null;
        String expResult = "";
        String result = instance.getNombre();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmail method, of class Cliente.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        Cliente instance = null;
        String expResult = "";
        String result = instance.getEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTelefono method, of class Cliente.
     */
    @Test
    public void testGetTelefono() {
        System.out.println("getTelefono");
        Cliente instance = null;
        String expResult = "";
        String result = instance.getTelefono();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNombreContacto method, of class Cliente.
     */
    @Test
    public void testGetNombreContacto() {
        System.out.println("getNombreContacto");
        Cliente instance = null;
        String expResult = "";
        String result = instance.getNombreContacto();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPorcentajeDescuento method, of class Cliente.
     */
    @Test
    public void testGetPorcentajeDescuento() {
        System.out.println("getPorcentajeDescuento");
        Cliente instance = null;
        double expResult = 0.0;
        double result = instance.getPorcentajeDescuento();
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTipoIdentificacion method, of class Cliente.
     */
    @Test
    public void testSetTipoIdentificacion() {
        System.out.println("setTipoIdentificacion");
        char tipoIdentificacion = ' ';
        Cliente instance = null;
        instance.setTipoIdentificacion(tipoIdentificacion);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNumeroIdentificacion method, of class Cliente.
     */
    @Test
    public void testSetNumeroIdentificacion() {
        System.out.println("setNumeroIdentificacion");
        String numeroIdentificacion = "";
        Cliente instance = null;
        instance.setNumeroIdentificacion(numeroIdentificacion);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEmpresa method, of class Cliente.
     */
    @Test
    public void testSetEmpresa() {
        System.out.println("setEmpresa");
        boolean empresa = false;
        Cliente instance = null;
        instance.setEmpresa(empresa);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNombre method, of class Cliente.
     */
    @Test
    public void testSetNombre() {
        System.out.println("setNombre");
        String nombre = "";
        Cliente instance = null;
        instance.setNombre(nombre);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEmail method, of class Cliente.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "";
        Cliente instance = null;
        instance.setEmail(email);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTelefono method, of class Cliente.
     */
    @Test
    public void testSetTelefono() {
        System.out.println("setTelefono");
        String telefono = "";
        Cliente instance = null;
        instance.setTelefono(telefono);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNombreContacto method, of class Cliente.
     */
    @Test
    public void testSetNombreContacto() {
        System.out.println("setNombreContacto");
        String nombreContacto = "";
        Cliente instance = null;
        instance.setNombreContacto(nombreContacto);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPorcentajeDescuento method, of class Cliente.
     */
    @Test
    public void testSetPorcentajeDescuento() {
        System.out.println("setPorcentajeDescuento");
        double porcentajeDescuento = 0.0;
        Cliente instance = null;
        instance.setPorcentajeDescuento(porcentajeDescuento);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Cliente.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Cliente instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
