/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author Mariana V
 */
public class Venta implements Serializable{
    private int numero;
    private LocalDateTime fechaHoraGeneracion; //Formato: aaaa-dd-mm hh:mm:ss - tomada del sistema
    private LocalDateTime fechaHoraActualizacion; //Al momento de la creación, se toma la misma de generación
    private Cliente suCliente;
    private ArrayList<PaqueteTuristico> susPaquetesTuristicos;
    private char estado; //Dominio: {A, P, C} - A: activa (por defecto), P: pago, C: cancelada

    public Venta(int numero, Cliente suCliente, ArrayList<PaqueteTuristico> susPaquetesTuristicos) {
        this.numero = numero;
        this.fechaHoraGeneracion = LocalDateTime.now();
        this.fechaHoraActualizacion = LocalDateTime.now();
        this.suCliente = suCliente;
        this.susPaquetesTuristicos = susPaquetesTuristicos;
        this.estado = 'A';
    }

    public int getNumero() {
        return numero;
    }

    public LocalDateTime getFechaHoraGeneracion() {
        return fechaHoraGeneracion;
    }

    public LocalDateTime getFechaHoraActualizacion() {
        return fechaHoraActualizacion;
    }

    public Cliente getSuCliente() {
        return suCliente;
    }

    public ArrayList<PaqueteTuristico> getSusPaquetesTuristicos() {
        return susPaquetesTuristicos;
    }

    public char getEstado() {
        return estado;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setFechaHoraGeneracion(LocalDateTime fechaHoraGeneracion) {
        this.fechaHoraGeneracion = fechaHoraGeneracion;
    }

    public void setFechaHoraActualizacion(LocalDateTime fechaHoraActualizacion) {
        this.fechaHoraActualizacion = fechaHoraActualizacion;
    }

    public void setSuCliente(Cliente suCliente) {
        this.suCliente = suCliente;
    }

    public void setSusPaquetesTuristicos(ArrayList<PaqueteTuristico> susPaquetesTuristicos) {
        this.susPaquetesTuristicos = susPaquetesTuristicos;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Venta{" + "numero=" + numero 
                + ", fechaHoraGeneracion=" + fechaHoraGeneracion 
                + ", fechaHoraActualizacion=" + fechaHoraActualizacion 
                + ", suCliente=" + suCliente 
                + ", susPaquetesTuristicos=" + susPaquetesTuristicos 
                + ", estado=" + estado + '}';
    }
    
    public int calcularCantidadTotalUnidadesPaquetes() {
        int totalUnidades = 0;
        for(int i=0; i<susPaquetesTuristicos.size(); i++){
            totalUnidades += susPaquetesTuristicos.get(i).getCantidadUnidades();
        }
        return totalUnidades;
    }   
    
    public int calcularValorTotalPaquetes() {
        int valorTotal = 0;
        for(int i=0; i<susPaquetesTuristicos.size(); i++){
            valorTotal += susPaquetesTuristicos.get(i).calcularValorTotal();
        }
        return valorTotal;
    }
    
    public int calcularValorDescuento() {
        return (int)(calcularValorTotalPaquetes() * (suCliente.getPorcentajeDescuento() / 100));
    }
    
    public int calcularValorTotalPagar() {
        return calcularValorTotalPaquetes() - calcularValorDescuento();
    }
}

