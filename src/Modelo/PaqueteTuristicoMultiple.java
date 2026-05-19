/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author Laura Correa
 */
public final class PaqueteTuristicoMultiple extends PaqueteTuristico {
    private String obsequio;

    public PaqueteTuristicoMultiple(String obsequio, String codigo, String nombre, String tipologiaTurismo, String descripcion, String origen, ArrayList<Destino> susDestinos, boolean hotel, boolean alimentacion, boolean alimentacionTodo, boolean vuelo, boolean asistencia, int tarifaDia, int cantidadUnidades) {
        super(codigo, nombre, tipologiaTurismo, descripcion, origen, susDestinos, hotel, alimentacion, alimentacionTodo, vuelo, asistencia, tarifaDia, cantidadUnidades);
        this.obsequio = obsequio;
    }

    public String getObsequio() {
        return obsequio;
    }

    public void setObsequio(String obsequio) {
        this.obsequio = obsequio;
    }

    @Override
    public String toString() {
        return "PaqueteTuristicoMultiple{" 
                + super.toString()
                + "obsequio=" + obsequio + '}';
    }
    
    @Override
    public int calcularValorUnidad() {
        int incremento = (int)(tarifaDia * 0.01 * susDestinos.size());
        return (tarifaDia * calcularDuracionTotalDias()) + incremento;
    }
    
    public Destino obtenerDestinoInicial() {
        return susDestinos.get(0);
    }
    
    public Destino obtenerDestinoFinal() {
        return susDestinos.get(susDestinos.size() - 1);
    }
}
