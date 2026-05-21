/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Mariana Vivero
 */
public abstract class PaqueteTuristico implements Serializable {
    protected String codigo;
    protected String nombre; //mínimo 10 caracteres
    protected String tipologiaTurismo; //ej. negocios, recreación, educativo, ecológico
    protected String descripcion; //máximo 500 caracteres
    protected String origen; //nombre lugar de partida, ej. Bogotá D.C.
    protected ArrayList<Destino> susDestinos;
    protected boolean hotel; //Si incluye hotel o no, por defecto está incluido
    protected boolean alimentacion; //Si incluye alimentación o no, por defecto está incluido
    protected boolean alimentacionTodo; //Si la alimentación incluye todo o únicamente el desayuno
    protected boolean vuelo; //Si incluye vuelo o no, por defecto está incluido
    protected boolean asistencia; //Si incluye asistencia o no, por defecto no está incluida
    protected int tarifaDia; //mayor que cero
    protected int cantidadUnidades; //mínimo 1, se refiere a la cantidad de reservas del paquete

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipologiaTurismo() {
        return tipologiaTurismo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getOrigen() {
        return origen;
    }

    public ArrayList<Destino> getSusDestinos() {
        return susDestinos;
    }

    public boolean isHotel() {
        return hotel;
    }

    public boolean isAlimentacion() {
        return alimentacion;
    }

    public boolean isAlimentacionTodo() {
        return alimentacionTodo;
    }

    public boolean isVuelo() {
        return vuelo;
    }

    public boolean isAsistencia() {
        return asistencia;
    }

    public int getTarifaDia() {
        return tarifaDia;
    }

    public int getCantidadUnidades() {
        return cantidadUnidades;
    }

    public PaqueteTuristico(String codigo, String nombre, String tipologiaTurismo, String descripcion, String origen, ArrayList<Destino> susDestinos, boolean hotel, boolean alimentacion, boolean alimentacionTodo, boolean vuelo, boolean asistencia, int tarifaDia, int cantidadUnidades) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipologiaTurismo = tipologiaTurismo;
        this.descripcion = descripcion;
        this.origen = origen;
        this.susDestinos = susDestinos;
        this.hotel = hotel;
        this.alimentacion = alimentacion;
        this.alimentacionTodo = alimentacionTodo;
        this.vuelo = vuelo;
        this.asistencia = asistencia;
        this.tarifaDia = tarifaDia;
        this.cantidadUnidades = cantidadUnidades;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipologiaTurismo(String tipologiaTurismo) {
        this.tipologiaTurismo = tipologiaTurismo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public void setSusDestinos(ArrayList<Destino> susDestinos) {
        this.susDestinos = susDestinos;
    }

    public void setHotel(boolean hotel) {
        this.hotel = hotel;
    }

    public void setAlimentacion(boolean alimentacion) {
        this.alimentacion = alimentacion;
    }

    public void setAlimentacionTodo(boolean alimentacionTodo) {
        this.alimentacionTodo = alimentacionTodo;
    }

    public void setVuelo(boolean vuelo) {
        this.vuelo = vuelo;
    }

    public void setAsistencia(boolean asistencia) {
        this.asistencia = asistencia;
    }

    public void setTarifaDia(int tarifaDia) {
        this.tarifaDia = tarifaDia;
    }

    public void setCantidadUnidades(int cantidadUnidades) {
        this.cantidadUnidades = cantidadUnidades;
    }

    @Override
    public String toString() {
        return "PaqueteTuristico{" + "codigo=" + codigo 
                + ", nombre=" + nombre 
                + ", tipologiaTurismo=" + tipologiaTurismo 
                + ", descripcion=" + descripcion 
                + ", origen=" + origen 
                + ", susDestinos=" + susDestinos 
                + ", hotel=" + hotel 
                + ", alimentacion=" + alimentacion 
                + ", alimentacionTodo=" + alimentacionTodo 
                + ", vuelo=" + vuelo 
                + ", asistencia=" + asistencia 
                + ", tarifaDia=" + tarifaDia 
                + ", cantidadUnidades=" + cantidadUnidades + '}';
    }
    
    public int calcularDuracionTotalDias() {
        int totalDias = 0;
        for(int i=0; i<susDestinos.size(); i++){
            totalDias += susDestinos.get(i).getDiasPermanencia();
        }
        return totalDias;
    }
    public abstract int calcularValorUnidad();
    
    public int calcularValorTotal() {
        return calcularValorUnidad() * cantidadUnidades;
    }
}
