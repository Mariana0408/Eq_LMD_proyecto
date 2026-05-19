/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Modelo.Cliente;
import Modelo.Destino;
import Modelo.PaqueteTuristico;
import Modelo.PaqueteTuristicoMultiple;
import Modelo.PaqueteTuristicoUnico;
import Modelo.Venta;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Mariana Vivero
 */
public class UsaGUIVenta extends javax.swing.JFrame {

    ArrayList<Venta> datosVentas = new ArrayList<>();
    ArrayList<Cliente> datosClientes = new ArrayList<>();

    public int generarNumeroVenta(ArrayList<Venta> datosVentas) {
        return datosVentas.size() + 1;
    }

    public ArrayList<Venta> crearNuevaVenta(ArrayList<Venta> datosVentas) {
        //a. Generar el número de la venta automáticamente
        int numeroVenta = generarNumeroVenta(datosVentas);

        //b. Solicitar datos del cliente
        char tipoId = JOptionPane.showInputDialog("Tipo de identificación del cliente(C: Cédula, N: Nit)").toUpperCase().charAt(0);
        String numId = JOptionPane.showInputDialog("Número de identificación del cliente");
        boolean esEmpresa = JOptionPane.showInputDialog("¿Es empresa? (S: Sí, N: No)").toUpperCase().charAt(0) == 'S';
        String nombreCliente = JOptionPane.showInputDialog("Nombre del cliente (o razón social si es empresa)");
        String emailCliente = JOptionPane.showInputDialog("Email del cliente");
        String telefonoCliente = JOptionPane.showInputDialog("Teléfono del cliente");
        String nombreContacto = JOptionPane.showInputDialog("Nombre del contacto");
        double porcentajeDescuento = Double.parseDouble(JOptionPane.showInputDialog("Porcentaje de descuento del cliente (0.0 - 70.0)"));

        //c. Crear el objeto cliente
        Cliente objCliente = new Cliente(tipoId, numId, esEmpresa, nombreCliente,
                emailCliente, telefonoCliente, nombreContacto, porcentajeDescuento);

        //d. Solicitar y crear los paquetes turísticos
        ArrayList<PaqueteTuristico> listaPaquetes = new ArrayList<>();
        int cantidadPaquetes = Integer.parseInt(JOptionPane.showInputDialog("¿Cuántos paquetes turísticos desea agregar a la venta?"));

        for(int i=0; i<cantidadPaquetes; i++){
            JOptionPane.showMessageDialog(null, "--- Datos del paquete " + (i + 1) + " ---");

            //i. Solicitar datos generales del paquete
            String codigoPaq = JOptionPane.showInputDialog("Código del paquete");
            String nombrePaq = JOptionPane.showInputDialog("Nombre del paquete (mínimo 10 caracteres)");
            String tipologiaPaq = JOptionPane.showInputDialog("Tipología de turismo (ej. negocios, recreación, educativo, ecológico)");
            String descripcionPaq = JOptionPane.showInputDialog("Descripción del paquete (máximo 500 caracteres)");
            String origenPaq = JOptionPane.showInputDialog("Lugar de origen (ej. Bogotá D.C.)");
            boolean hotelPaq = JOptionPane.showInputDialog("¿Incluye hotel? (S: Sí, N: No)").toUpperCase().charAt(0) == 'S';
            boolean alimentacionPaq = JOptionPane.showInputDialog("¿Incluye alimentación? (S: Sí, N: No)").toUpperCase().charAt(0) == 'S';
            boolean alimentacionTodoPaq = false;
            if(alimentacionPaq){
                alimentacionTodoPaq = JOptionPane.showInputDialog("¿La alimentación incluye todo? (S: Sí / N: Solo desayuno)").toUpperCase().charAt(0) == 'S';
            }
            boolean vueloPaq = JOptionPane.showInputDialog("¿Incluye vuelo? (S: Sí, N: No)").toUpperCase().charAt(0) == 'S';
            boolean asistenciaPaq = JOptionPane.showInputDialog("¿Incluye asistencia? (S: Sí, N: No)").toUpperCase().charAt(0) == 'S';
            int tarifaDiaPaq = Integer.parseInt(JOptionPane.showInputDialog("Tarifa por día (mayor que cero)"));
            int cantidadUnidadesPaq = Integer.parseInt(JOptionPane.showInputDialog("Cantidad de unidades/reservas (mínimo 1)"));

            //ii. Solicitar y crear los destinos del paquete
            int cantidadDestinos = Integer.parseInt(JOptionPane.showInputDialog("¿Cuántos destinos tiene el paquete?"));
            ArrayList<Destino> listaDestinos = new ArrayList<>();
            for(int j=0; j<cantidadDestinos; j++){
                JOptionPane.showMessageDialog(null, "-- Datos del destino " + (j + 1) + " --");
                String nombreLugar = JOptionPane.showInputDialog("Nombre del lugar");
                int diasPermanencia = Integer.parseInt(JOptionPane.showInputDialog("Días de permanencia (mínimo 1)"));
                boolean atractivosIncluidos = JOptionPane.showInputDialog("¿Atractivos incluidos? (S: Sí, N: Opcionales)").toUpperCase().charAt(0) == 'S';
                int cantAtractivos = Integer.parseInt(JOptionPane.showInputDialog("¿Cuántos atractivos desea registrar?"));
                LinkedList<String> atractivos = new LinkedList<>();
                for(int k=0; k<cantAtractivos; k++){
                    atractivos.add(JOptionPane.showInputDialog("Nombre del atractivo " + (k + 1)));
                }
                //iii. Crear el objeto destino y agregarlo a la lista
                Destino objDestino = new Destino(nombreLugar, diasPermanencia, atractivos, atractivosIncluidos);
                listaDestinos.add(objDestino);
            }

            //iv. Preguntar por la categoría del paquete
            String categoriaPaq = JOptionPane.showInputDialog("Categoría del paquete (U: Único, M: Múltiple)").toUpperCase();

            //v. Crear el objeto paquete de la subclase respectiva y agregarlo a la lista
            if(categoriaPaq.equals("U")){//Único
                String nombreHotel = JOptionPane.showInputDialog("Nombre del hotel");
                String tipoDesayuno = "";
                if(alimentacionPaq){
                    tipoDesayuno = JOptionPane.showInputDialog("Tipo de desayuno (ej. Buffet, Americano)");
                }
                PaqueteTuristicoUnico objPaqueteUnico = new PaqueteTuristicoUnico(nombreHotel, tipoDesayuno, codigoPaq, nombrePaq,
                        tipologiaPaq, descripcionPaq, origenPaq, listaDestinos,
                        hotelPaq, alimentacionPaq, alimentacionTodoPaq, vueloPaq,
                        asistenciaPaq, tarifaDiaPaq, cantidadUnidadesPaq);
                listaPaquetes.add(objPaqueteUnico);
            } else{//Múltiple
                String obsequio = JOptionPane.showInputDialog("Nombre del obsequio del paquete");
                PaqueteTuristicoMultiple objPaqueteMultiple = new PaqueteTuristicoMultiple(obsequio, codigoPaq, nombrePaq,
                        tipologiaPaq, descripcionPaq, origenPaq, listaDestinos,
                        hotelPaq, alimentacionPaq, alimentacionTodoPaq, vueloPaq,
                        asistenciaPaq, tarifaDiaPaq, cantidadUnidadesPaq);
                listaPaquetes.add(objPaqueteMultiple);
            }
        }

        //e. Crear la venta y agregarla a la colección
        Venta objVenta = new Venta(numeroVenta, objCliente, listaPaquetes);
        datosVentas.add(objVenta);
        return datosVentas;
    }

    public String consultarTodasVentas(ArrayList<Venta> datosVentas) {
        String resultado = "RESULTADO DE CONSULTAR TODAS LAS VENTAS\n";
        for(int i=0; i<datosVentas.size(); i++){
            resultado += datosVentas.get(i).toString() + "\n";
        }
        return resultado;
    }

    public String consultarVentaDadoNumero(ArrayList<Venta> datosVentas, int numeroVenta) {
        String resultado = "RESULTADO DE CONSULTA DE LA VENTA NÚMERO " + numeroVenta + "\n";
        for(int i=0; i<datosVentas.size(); i++){
            if(datosVentas.get(i).getNumero() == numeroVenta){
                resultado += datosVentas.get(i).toString();
            }
        }
        return resultado;
    }

    // posicionVenta => P: primera, U: última
    public String consultarVentaDadaPosicion(ArrayList<Venta> datosVentas, char posicionVenta) {
        String resultado = "RESULTADO DE CONSULTA DE LA VENTA "
                + (posicionVenta == 'P' ? "PRIMERA" : "ÚLTIMA") + "\n";
        if(posicionVenta == 'P'){
            resultado += datosVentas.get(0).toString();
        } else{
            resultado += datosVentas.get(datosVentas.size() - 1).toString();
        }
        return resultado;
    }

    // estadoVenta => A: activa, C: cancelada/anulada, P: pago asociado
    public String consultarVentasDadoEstado(ArrayList<Venta> datosVentas, char estadoVenta) {
        String nombreEstado = "";
        if(estadoVenta == 'A'){
            nombreEstado = "ACTIVA";
        } else{
            if(estadoVenta == 'C'){
                nombreEstado = "CANCELADA/ANULADA";
            } else{
                nombreEstado = "CON PAGO ASOCIADO";
            }
        }
        String resultado = "RESULTADO DE CONSULTA DE VENTAS EN ESTADO: " + nombreEstado + "\n";
        for(int i=0; i<datosVentas.size(); i++){
            if(datosVentas.get(i).getEstado() == estadoVenta){
                resultado += "numero=" + datosVentas.get(i).getNumero()
                        + ", estado=" + datosVentas.get(i).getEstado()
                        + ", \nsuCliente=" + datosVentas.get(i).getSuCliente().toString()
                        + ", cantidadPaquetes=" + datosVentas.get(i).getSusPaquetesTuristicos().size()
                        + ", cantidadTotalUnidades=" + datosVentas.get(i).calcularCantidadTotalUnidadesPaquetes()
                        + ", valorTotalPaquetes=" + datosVentas.get(i).calcularValorTotalPaquetes()
                        + ", valorDescuento=" + datosVentas.get(i).calcularValorDescuento()
                        + ", valorTotalPagar=" + datosVentas.get(i).calcularValorTotalPagar();
                resultado += "\n";
            }
        }
        return resultado;
    }

    // categoriaPaquete => Único, Múltiple
    public String consultarVentasDadaCategoriaPaquete(ArrayList<Venta> datosVentas, String categoriaPaquete) {
        String resultado = "RESULTADO DE CONSULTA DE VENTAS CON PAQUETES DE CATEGORÍA: " + categoriaPaquete + "\n";
        if(categoriaPaquete.equalsIgnoreCase("Único")){
            for(int i=0; i<datosVentas.size(); i++){
                for(int j=0; j<datosVentas.get(i).getSusPaquetesTuristicos().size(); j++){
                    if(datosVentas.get(i).getSusPaquetesTuristicos().get(j) instanceof PaqueteTuristicoUnico){
                        resultado += datosVentas.get(i).toString() + "\n";
                        break;
                    }
                }
            }
        } else{
            for(int i=0; i<datosVentas.size(); i++){
                for(int j=0; j<datosVentas.get(i).getSusPaquetesTuristicos().size(); j++){
                    if(datosVentas.get(i).getSusPaquetesTuristicos().get(j) instanceof PaqueteTuristicoMultiple){
                        resultado += datosVentas.get(i).toString() + "\n";
                        break;
                    }
                }
            }
        }
        return resultado;
    }

    // operacion => C: Cancelar, P: Pagar
    public void actualizarVenta(ArrayList<Venta> datosVentas, int numeroVenta, char operacion) {
        for(int i=0; i<datosVentas.size(); i++){
            if(datosVentas.get(i).getNumero() == numeroVenta){
                //1. Actualizar el estado
                if(operacion == 'C'){
                    datosVentas.get(i).setEstado('C');
                } else{
                    datosVentas.get(i).setEstado('P');
                }
                //2. Actualizar la fecha y hora de actualización
                datosVentas.get(i).setFechaHoraActualizacion(java.time.LocalDateTime.now());
            }
        }
    }

    public void generarArchivoObjetosVentas(ArrayList<Venta> datosVentas) {
        String nombreArchivo = "datosVentas.obj"; //Definiendo archivo
        ObjectOutputStream salida = null; //Definiendo flujo de salida
        try{
            salida = new ObjectOutputStream(new FileOutputStream(nombreArchivo)); //creando flujo de salida
            salida.writeObject(datosVentas); //almacenando la colección en el archivo
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error almacenando los datos: " + e.toString(),
                    "ERROR ALMACENANDO EN ARCHIVO", JOptionPane.ERROR_MESSAGE);
        } finally{
            try{
                salida.close(); //cerrando el archivo
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error cerrando el archivo: " + e.toString(),
                        "ERROR CERRANDO ARCHIVO", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//fin generarArchivoObjetosVentas

    public void recuperarVentasDesdeArchivoObjetos() {
        String nombreArchivo = "datosVentas.obj"; //Definiendo archivo
        ObjectInputStream entrada = null; //Definiendo flujo de entrada
        ArrayList<Venta> datosLeidos = new ArrayList<>();
        try{
            entrada = new ObjectInputStream(new FileInputStream(nombreArchivo)); //creando flujo de entrada
            datosLeidos = (ArrayList<Venta>) entrada.readObject(); //recuperando los datos desde el flujo de entrada
            datosVentas.clear();
            datosVentas.addAll(datosLeidos); //copiar toda la colección "datosLeidos" en la colección "datosVentas"
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error recuperando datos: " + e.toString(),
                    "ERROR LEYENDO ARCHIVO", JOptionPane.ERROR_MESSAGE);
        } finally{
            try{
                entrada.close(); //cerrando el archivo
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error cerrando el archivo: " + e.toString(),
                        "ERROR CERRANDO ARCHIVO", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//fin recuperarVentasDesdeArchivoObjetos

    public void generarArchivoObjetosClientes(ArrayList<Cliente> datosClientes) {
        //1. A partir de la colección de ventas, generar colección de clientes sin repetir
        datosClientes.clear();
        for(int i=0; i<datosVentas.size(); i++){
            Cliente clienteVenta = datosVentas.get(i).getSuCliente();
            boolean existe = false;
            for(int j=0; j<datosClientes.size(); j++){
                if(datosClientes.get(j).getNumeroIdentificacion().equals(clienteVenta.getNumeroIdentificacion())){
                    existe = true;
                }
            }
            if(!existe){
                datosClientes.add(clienteVenta);
            }
        }
        //2. Generar el archivo de objetos de clientes
        String nombreArchivo = "datosClientes.obj"; //Definiendo archivo
        ObjectOutputStream salida = null; //Definiendo flujo de salida
        try{
            salida = new ObjectOutputStream(new FileOutputStream(nombreArchivo)); //creando flujo de salida
            salida.writeObject(datosClientes); //almacenando la colección en el archivo
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error almacenando los datos: " + e.toString(),
                    "ERROR ALMACENANDO EN ARCHIVO", JOptionPane.ERROR_MESSAGE);
        } finally{
            try{
                salida.close(); //cerrando el archivo
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error cerrando el archivo: " + e.toString(),
                        "ERROR CERRANDO ARCHIVO", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//fin generarArchivoObjetosClientes

    public void recuperarClientesDesdeArchivoObjetos() {
        String nombreArchivo = "datosClientes.obj"; //Definiendo archivo
        ObjectInputStream entrada = null; //Definiendo flujo de entrada
        ArrayList<Cliente> datosLeidos = new ArrayList<>();
        try{
            entrada = new ObjectInputStream(new FileInputStream(nombreArchivo)); //creando flujo de entrada
            datosLeidos = (ArrayList<Cliente>) entrada.readObject(); //recuperando los datos desde el flujo de entrada
            datosClientes.clear();
            datosClientes.addAll(datosLeidos); //copiar toda la colección "datosLeidos" en la colección "datosClientes"
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error recuperando datos: " + e.toString(),
                    "ERROR LEYENDO ARCHIVO", JOptionPane.ERROR_MESSAGE);
        } finally{
            try{
                entrada.close(); //cerrando el archivo
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error cerrando el archivo: " + e.toString(),
                        "ERROR CERRANDO ARCHIVO", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Creates new form UsaGUIVenta
     */
    public UsaGUIVenta() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelRegistro = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabelRegistro = new javax.swing.JLabel();
        jPanelConsultas = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabelFiltrar = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButtonConsultartodo = new javax.swing.JButton();
        jButtonQNumero = new javax.swing.JButton();
        jButtonQPrimera = new javax.swing.JButton();
        jButtonQUltima = new javax.swing.JButton();
        jButtonQEstado = new javax.swing.JButton();
        jButtonQCategoria = new javax.swing.JButton();
        jButtonActualizarventa = new javax.swing.JButton();
        jButtonGenerararchivoventa = new javax.swing.JButton();
        jButtonGenerararchivo = new javax.swing.JButton();
        jButtonLeerarchivo = new javax.swing.JButton();
        jButtonSalir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestion de ventas de paquetes turisticos");

        jButton1.setText("Nueva Venta");

        jLabelRegistro.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabelRegistro.setText("REGISTRO DE VENTA");

        javax.swing.GroupLayout jPanelRegistroLayout = new javax.swing.GroupLayout(jPanelRegistro);
        jPanelRegistro.setLayout(jPanelRegistroLayout);
        jPanelRegistroLayout.setHorizontalGroup(
            jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRegistroLayout.createSequentialGroup()
                .addGap(215, 215, 215)
                .addGroup(jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelRegistroLayout.setVerticalGroup(
            jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRegistroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelRegistro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel2.setText("CONSULTAS Y OPERACIONES");

        jLabelFiltrar.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabelFiltrar.setText("FILTRAR");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButtonConsultartodo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonConsultartodo.setText("Consultar Todo");
        jButtonConsultartodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsultartodoActionPerformed(evt);
            }
        });

        jButtonQNumero.setText("Por numero");
        jButtonQNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonQNumeroActionPerformed(evt);
            }
        });

        jButtonQPrimera.setText("Primera");

        jButtonQUltima.setText("Ultima");

        jButtonQEstado.setText("Por estado");

        jButtonQCategoria.setText("Por categoria");

        jButtonActualizarventa.setText("Actualizar venta");

        jButtonGenerararchivoventa.setText("Generar archivo venta");

        jButtonGenerararchivo.setText("Generar archivo clientes");

        jButtonLeerarchivo.setText("Leer archivo clientes");

        jButtonSalir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonSalir.setText("Salir");
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelConsultasLayout = new javax.swing.GroupLayout(jPanelConsultas);
        jPanelConsultas.setLayout(jPanelConsultasLayout);
        jPanelConsultasLayout.setHorizontalGroup(
            jPanelConsultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConsultasLayout.createSequentialGroup()
                .addGroup(jPanelConsultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelConsultasLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelConsultasLayout.createSequentialGroup()
                        .addGroup(jPanelConsultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelConsultasLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabelFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelConsultasLayout.createSequentialGroup()
                                .addGap(180, 180, 180)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelConsultasLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButtonGenerararchivo)
                                .addGap(15, 15, 15)
                                .addComponent(jButtonLeerarchivo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonGenerararchivoventa)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanelConsultasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonQEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonQCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonActualizarventa)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelConsultasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonConsultartodo)
                .addGap(18, 18, 18)
                .addComponent(jButtonQNumero)
                .addGap(18, 18, 18)
                .addComponent(jButtonQPrimera)
                .addGap(18, 18, 18)
                .addComponent(jButtonQUltima)
                .addGap(142, 142, 142))
        );
        jPanelConsultasLayout.setVerticalGroup(
            jPanelConsultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConsultasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addGroup(jPanelConsultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFiltrar)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanelConsultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonQUltima)
                    .addComponent(jButtonQPrimera)
                    .addComponent(jButtonQNumero)
                    .addComponent(jButtonConsultartodo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelConsultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonQEstado)
                    .addComponent(jButtonQCategoria)
                    .addComponent(jButtonActualizarventa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelConsultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonLeerarchivo)
                    .addComponent(jButtonGenerararchivoventa)
                    .addComponent(jButtonGenerararchivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSalir)
                .addGap(16, 16, 16))
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setEnabled(false);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addComponent(jPanelConsultas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelRegistro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jPanelRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonSalirActionPerformed

    private void jButtonConsultartodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsultartodoActionPerformed
        consultarTodasVentas (datosVentas);// TODO add your handling code here:
    }//GEN-LAST:event_jButtonConsultartodoActionPerformed

    private void jButtonQNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonQNumeroActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UsaGUIVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UsaGUIVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UsaGUIVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UsaGUIVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UsaGUIVenta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonActualizarventa;
    private javax.swing.JButton jButtonConsultartodo;
    private javax.swing.JButton jButtonGenerararchivo;
    private javax.swing.JButton jButtonGenerararchivoventa;
    private javax.swing.JButton jButtonLeerarchivo;
    private javax.swing.JButton jButtonQCategoria;
    private javax.swing.JButton jButtonQEstado;
    private javax.swing.JButton jButtonQNumero;
    private javax.swing.JButton jButtonQPrimera;
    private javax.swing.JButton jButtonQUltima;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelFiltrar;
    private javax.swing.JLabel jLabelRegistro;
    private javax.swing.JPanel jPanelConsultas;
    private javax.swing.JPanel jPanelRegistro;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
