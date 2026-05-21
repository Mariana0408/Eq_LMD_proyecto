 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;

/**
 *
 * @author Mariana V
 */
public class Cliente implements Serializable {
    private char tipoIdentificacion; //Dominio: {C, N} - C: cédula, N: Nit 
    private String numeroIdentificacion; //cédula: mínimo 6 dígitos, nit: 9 digiitos
    private boolean empresa;//true si es empresa, false si es persona natural
    private String nombre; //Si persona: nombre completo. Si empresa: razón social
    private String email;
    private String telefono; //persona: móvil o fijo. empresa: fijo
    private String nombreContacto; //si persona natural puede ser el mismo nombre del cliente
    private double porcentajeDescuento; //Mínimo: 0.0, Máximo: 70.0

    public Cliente(char tipoIdentificacion, String numeroIdentificacion, boolean empresa, String nombre, String email, String telefono, String nombreContacto, double porcentajeDescuento) {
        this.tipoIdentificacion = tipoIdentificacion;
        this.numeroIdentificacion = numeroIdentificacion;
        this.empresa = empresa;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.nombreContacto = nombreContacto;
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public char getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public boolean isEmpresa() {
        return empresa;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setTipoIdentificacion(char tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public void setEmpresa(boolean empresa) {
        this.empresa = empresa;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public void setPorcentajeDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    @Override
    public String toString() {
        return "Cliente{" + "tipoIdentificacion=" + tipoIdentificacion 
                + ", numeroIdentificacion=" + numeroIdentificacion 
                + ", empresa=" + empresa 
                + ", nombre=" + nombre 
                + ", email=" + email 
                + ", telefono=" + telefono 
                + ", nombreContacto=" + nombreContacto 
                + ", porcentajeDescuento=" + porcentajeDescuento + '}';
    }
}