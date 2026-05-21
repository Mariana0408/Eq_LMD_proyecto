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
ArrayList<PaqueteTuristico> listaPaquetes = new ArrayList<>();
    ArrayList<PaqueteTuristicoUnico> listaPaquetesUnicos = new ArrayList<>();
    ArrayList<PaqueteTuristicoMultiple> listaPaquetesMultiples = new ArrayList<>();
    ArrayList<Destino> listaDestinos = new ArrayList<>();
    
    

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
                if(estadoVenta == 'A' || estadoVenta == 'P'){
                    for(int j=0; j<datosVentas.get(i).getSusPaquetesTuristicos().size(); j++){
                        resultado += "\n" + datosVentas.get(i).getSusPaquetesTuristicos().get(j).toString()
                                + ", valorUnidad=" + datosVentas.get(i).getSusPaquetesTuristicos().get(j).calcularValorUnidad()
                                + ", valorTotal=" + datosVentas.get(i).getSusPaquetesTuristicos().get(j).calcularValorTotal();
                        if(datosVentas.get(i).getSusPaquetesTuristicos().get(j) instanceof PaqueteTuristicoMultiple){
                            PaqueteTuristicoMultiple pm = (PaqueteTuristicoMultiple) datosVentas.get(i).getSusPaquetesTuristicos().get(j);
                            resultado += ", destinoInicial=" + pm.obtenerDestinoInicial().getNombreLugar()
                    + ", destinoFinal=" + pm.obtenerDestinoFinal().getNombreLugar();
        }
    } 
}
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
                        "ERROR. CERRANDO ARCHIVO", JOptionPane.ERROR_MESSAGE);
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

        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jButtonLimpiar1 = new javax.swing.JButton();
        jButtonGuardarCliente = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jTextField25 = new javax.swing.JTextField();
        jTextField26 = new javax.swing.JTextField();
        jButtonLimpiar3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jTextField28 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jTextField29 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jTextField30 = new javax.swing.JTextField();
        jButtonLimpiar5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jTextField27 = new javax.swing.JTextField();
        jButtonLimpiar4 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanelConsultas = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabelFiltrar = new javax.swing.JLabel();
        jTextoFiltrar = new javax.swing.JTextField();
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
        jPanel8 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jTextField22 = new javax.swing.JTextField();
        jTextField23 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextField24 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jButtonLimpiar2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTextField14 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jPanelRegistro = new javax.swing.JPanel();
        jLabelRegistro = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabelNumero = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButtonLimpiar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestion de ventas de paquetes turisticos");

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel5.setText("REGISTRO DE CLIENTE");
        jLabel5.setToolTipText("");

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel6.setText("tipoIdentificacion");

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel7.setText("numeroIdentificacion");

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel8.setText("Empresa");

        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel9.setText("Nombre");

        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel10.setText("Email");

        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel11.setText("Telefono");

        jLabel12.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel12.setText("nombreContacto");

        jLabel13.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel13.setText("porcentajeDescuento");

        jButtonLimpiar1.setText("Limpiar");

        jButtonGuardarCliente.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonGuardarCliente.setText("Guardar Cliente");
        jButtonGuardarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(205, 205, 205)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButtonLimpiar1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonGuardarCliente)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(32, 32, 32)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField6)
                                        .addComponent(jTextField11)
                                        .addComponent(jTextField12)))))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(61, 61, 61))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel12)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonLimpiar1)
                    .addComponent(jButtonGuardarCliente))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel27.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel27.setText("PAQUETE TURISTICO UNICO");

        jLabel28.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel28.setText("TipoHotel");

        jLabel29.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel29.setText("TipoDesayuno (Opcional)");

        jButtonLimpiar3.setText("Limpiar");

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton5.setText("Guardar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jButtonLimpiar3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel29)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel28)
                                    .addGap(131, 131, 131)
                                    .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jLabel27)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel27)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonLimpiar3)
                    .addComponent(jButton5))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLabel32.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel32.setText("DESTINO");

        jLabel33.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel33.setText("nombreLugar");

        jLabel34.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel34.setText("Dias Permanencia");

        jLabel35.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel35.setText("Atractivos Inlcuidos");

        jButtonLimpiar5.setText("Limpiar");

        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton6.setText("Guardar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel34)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel33)
                            .addGap(57, 57, 57)
                            .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonLimpiar5, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(205, 205, 205)
                        .addComponent(jLabel32)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32)
                .addGap(27, 27, 27)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton6)
                        .addGap(21, 21, 21)
                        .addComponent(jButtonLimpiar5)))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jLabel30.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel30.setText("PAQUETE TURISTICO MULTIPLE");

        jLabel31.setText("Obsequio");

        jButtonLimpiar4.setText("Limpiar");

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4.setText("Guardar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addGap(28, 28, 28)
                                .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jButtonLimpiar4, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel30)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel30)
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonLimpiar4)
                    .addComponent(jButton4))
                .addGap(314, 314, 314))
        );

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel2.setText("CONSULTAS Y OPERACIONES");

        jLabelFiltrar.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabelFiltrar.setText("FILTRAR");

        jTextoFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextoFiltrarActionPerformed(evt);
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
        jButtonQPrimera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonQPrimeraActionPerformed(evt);
            }
        });

        jButtonQUltima.setText("Ultima");
        jButtonQUltima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonQUltimaActionPerformed(evt);
            }
        });

        jButtonQEstado.setText("Por estado");
        jButtonQEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonQEstadoActionPerformed(evt);
            }
        });

        jButtonQCategoria.setText("Por categoria");
        jButtonQCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonQCategoriaActionPerformed(evt);
            }
        });

        jButtonActualizarventa.setText("Actualizar venta");
        jButtonActualizarventa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizarventaActionPerformed(evt);
            }
        });

        jButtonGenerararchivoventa.setText("Generar archivo venta");
        jButtonGenerararchivoventa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGenerararchivoventaActionPerformed(evt);
            }
        });

        jButtonGenerararchivo.setText("Generar archivo clientes");
        jButtonGenerararchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGenerararchivoActionPerformed(evt);
            }
        });

        jButtonLeerarchivo.setText("Leer archivo clientes");
        jButtonLeerarchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLeerarchivoActionPerformed(evt);
            }
        });

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
                    .addGroup(jPanelConsultasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButtonQEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jButtonConsultartodo)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonQNumero)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonQPrimera)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonQUltima))
                    .addGroup(jPanelConsultasLayout.createSequentialGroup()
                        .addGap(199, 199, 199)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelConsultasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelConsultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelConsultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanelConsultasLayout.createSequentialGroup()
                                    .addComponent(jLabelFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextoFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanelConsultasLayout.createSequentialGroup()
                                    .addComponent(jButtonGenerararchivo)
                                    .addGap(15, 15, 15)
                                    .addComponent(jButtonLeerarchivo)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jButtonGenerararchivoventa)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jButtonQCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButtonActualizarventa))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelConsultasLayout.setVerticalGroup(
            jPanelConsultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConsultasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addGroup(jPanelConsultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFiltrar)
                    .addComponent(jTextoFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelConsultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonQEstado)
                    .addComponent(jButtonConsultartodo)
                    .addComponent(jButtonQNumero)
                    .addComponent(jButtonQPrimera)
                    .addComponent(jButtonQUltima))
                .addGap(4, 4, 4)
                .addComponent(jButtonSalir)
                .addGap(27, 27, 27)
                .addGroup(jPanelConsultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonLeerarchivo)
                    .addComponent(jButtonGenerararchivoventa)
                    .addComponent(jButtonGenerararchivo)
                    .addComponent(jButtonQCategoria)
                    .addComponent(jButtonActualizarventa))
                .addGap(330, 330, 330))
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel14.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel14.setText("REGISTRO PAQUETE TURISTICO");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PaqueteTuristicoUnico" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel22.setText("alimentacionTodo");

        jLabel21.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel21.setText("Alimentacion");

        jLabel24.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel24.setText("Asistencia");

        jLabel25.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel25.setText("tarifaDia");

        jLabel26.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel26.setText("cantidadUnidades");

        jLabel15.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel15.setText("Codigo");

        jLabel16.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel16.setText("Nombre");

        jLabel17.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel17.setText("tipologiaTurismo");

        jLabel18.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel18.setText("Descripción");

        jLabel19.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel19.setText("Origen");

        jLabel20.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel20.setText("Hotel");

        jButtonLimpiar2.setText("Limpiar");

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setText("Guardar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel23.setText("Vuelo");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel21)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel22)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel24)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(107, 107, 107)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel26)
                                    .addComponent(jLabel25)))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel20)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(218, 218, 218)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel19))))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField24, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                                .addComponent(jTextField14)
                                .addComponent(jTextField18))
                            .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14)
                            .addComponent(jButtonLimpiar2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel21)
                        .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25)
                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15)
                            .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20)
                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel23)))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonLimpiar2)
                    .addComponent(jButton3))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jLabelRegistro.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabelRegistro.setText("REGISTRO DE VENTA");

        jLabelNumero.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabelNumero.setText("Número");

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel1.setText("FechaHoraGeneracion");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel3.setText("FechaHoraActualizacion");

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel4.setText("Estado");

        jButtonLimpiar.setText("Limpiar");

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelRegistroLayout = new javax.swing.GroupLayout(jPanelRegistro);
        jPanelRegistro.setLayout(jPanelRegistroLayout);
        jPanelRegistroLayout.setHorizontalGroup(
            jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRegistroLayout.createSequentialGroup()
                .addGroup(jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelRegistroLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1)
                            .addComponent(jTextField3))
                        .addGap(41, 41, 41)
                        .addGroup(jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanelRegistroLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(34, 34, 34)
                        .addGroup(jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelRegistroLayout.createSequentialGroup()
                        .addGap(280, 280, 280)
                        .addComponent(jLabelRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(88, 88, 88))
        );
        jPanelRegistroLayout.setVerticalGroup(
            jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRegistroLayout.createSequentialGroup()
                .addGroup(jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelRegistroLayout.createSequentialGroup()
                            .addGap(37, 37, 37)
                            .addGroup(jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelNumero)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRegistroLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabelRegistro)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanelRegistroLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jButtonLimpiar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanelRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jPanelConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(1540, 1540, 1540)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanelRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jPanelConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(762, 762, 762))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextoFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextoFiltrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextoFiltrarActionPerformed

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
       JOptionPane.showMessageDialog(null, "¡Gracias por utilizar nuestro programa!");
        System.exit(0);         // TODO add your handling code here:
    }//GEN-LAST:event_jButtonSalirActionPerformed

    private void jButtonConsultartodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsultartodoActionPerformed
        jTextArea1.setText(consultarTodasVentas(datosVentas));;// TODO add your handling code here:
    }//GEN-LAST:event_jButtonConsultartodoActionPerformed

    private void jButtonQNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQNumeroActionPerformed
        jTextArea1.setText(consultarVentaDadoNumero(datosVentas, Integer.parseInt(jTextoFiltrar.getText())));// TODO add your handling code here:
    }//GEN-LAST:event_jButtonQNumeroActionPerformed

    private void jButtonQPrimeraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQPrimeraActionPerformed
        jTextArea1.setText(consultarVentaDadaPosicion(datosVentas, 'P'));// TODO add your handling code here:
    }//GEN-LAST:event_jButtonQPrimeraActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
    datosVentas = crearNuevaVenta(datosVentas);
    JOptionPane.showMessageDialog(null, "Venta registrada correctamente.");// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonGuardarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarClienteActionPerformed

        Cliente objCliente = new Cliente(
        jTextField5.getText().charAt(0),
        jTextField6.getText(),
        jTextField7.getText().toUpperCase().charAt(0) == 'S',
        jTextField8.getText(),
        jTextField9.getText(),
        jTextField10.getText(),
        jTextField11.getText(),
        Double.parseDouble(jTextField12.getText())
    );

    JOptionPane.showMessageDialog(null,
            "Cliente registrado correctamente.");// TODO add your handling code here:
    }//GEN-LAST:event_jButtonGuardarClienteActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    String categoria =
            jComboBox1.getSelectedItem().toString();

    if(categoria.equals("PaqueteTuristicoUnico")){

        PaqueteTuristicoUnico objPaqueteUnico =
                new PaqueteTuristicoUnico(

            jTextField25.getText(),
            jTextField26.getText(),

            jTextField13.getText(),
            jTextField14.getText(),
            jTextField15.getText(),
            jTextField16.getText(),
            jTextField17.getText(),

            listaDestinos,

            jTextField18.getText().toUpperCase().charAt(0) == 'S',
            jTextField19.getText().toUpperCase().charAt(0) == 'S',
            jTextField20.getText().toUpperCase().charAt(0) == 'S',
            jTextField21.getText().toUpperCase().charAt(0) == 'S',
            jTextField22.getText().toUpperCase().charAt(0) == 'S',

            Integer.parseInt(jTextField23.getText()),
            Integer.parseInt(jTextField24.getText())
        );

        listaPaquetes.add(objPaqueteUnico);
    }

    else{

        PaqueteTuristicoMultiple objPaqueteMultiple =
                new PaqueteTuristicoMultiple(

            jTextField27.getText(),

            jTextField13.getText(),
            jTextField14.getText(),
            jTextField15.getText(),
            jTextField16.getText(),
            jTextField17.getText(),

            listaDestinos,

            jTextField18.getText().toUpperCase().charAt(0) == 'S',
            jTextField19.getText().toUpperCase().charAt(0) == 'S',
            jTextField20.getText().toUpperCase().charAt(0) == 'S',
            jTextField21.getText().toUpperCase().charAt(0) == 'S',
            jTextField22.getText().toUpperCase().charAt(0) == 'S',

            Integer.parseInt(jTextField23.getText()),
            Integer.parseInt(jTextField24.getText())
        );

        listaPaquetes.add(objPaqueteMultiple);
    }

    JOptionPane.showMessageDialog(null,
            "Paquete registrado correctamente.");
// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
    LinkedList<String> atractivos =
            new LinkedList<>();

    Destino objDestino = new Destino(

        jTextField28.getText(),
        Integer.parseInt(jTextField29.getText()),
        atractivos,
        jTextField30.getText().toUpperCase().charAt(0) == 'S'
    );

    listaDestinos.add(objDestino);

    JOptionPane.showMessageDialog(null,
            "Destino registrado correctamente.");// TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButtonQUltimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQUltimaActionPerformed
        jTextArea1.setText(consultarVentaDadaPosicion(datosVentas, 'U'));
// TODO add your handling code here:
    }//GEN-LAST:event_jButtonQUltimaActionPerformed

    private void jButtonQEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQEstadoActionPerformed
        jTextArea1.setText(consultarVentasDadoEstado(datosVentas, jTextoFiltrar.getText().toUpperCase().charAt(0)));        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonQEstadoActionPerformed

    private void jButtonQCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQCategoriaActionPerformed
        jTextArea1.setText(consultarVentasDadaCategoriaPaquete(datosVentas, jTextoFiltrar.getText()));// TODO add your handling code here:
    }//GEN-LAST:event_jButtonQCategoriaActionPerformed

    private void jButtonActualizarventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarventaActionPerformed
        actualizarVenta(datosVentas, Integer.parseInt(jTextoFiltrar.getText()), 
        JOptionPane.showInputDialog("Operación (C: Cancelar, P: Pagar)").toUpperCase().charAt(0));// TODO add your handling code here:
    }//GEN-LAST:event_jButtonActualizarventaActionPerformed

    private void jButtonGenerararchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGenerararchivoActionPerformed
    generarArchivoObjetosClientes(datosClientes);   // ← CORRECTO
    JOptionPane.showMessageDialog(null, "Archivo de clientes generado.");// TODO add your handling code here:
    }//GEN-LAST:event_jButtonGenerararchivoActionPerformed

    private void jButtonLeerarchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLeerarchivoActionPerformed
    recuperarClientesDesdeArchivoObjetos();          // ← CORRECTO
    JOptionPane.showMessageDialog(null, "Clientes recuperados desde archivo.");// TODO add your handling code here:
    }//GEN-LAST:event_jButtonLeerarchivoActionPerformed

    private void jButtonGenerararchivoventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGenerararchivoventaActionPerformed
    generarArchivoObjetosVentas(datosVentas);
    JOptionPane.showMessageDialog(null, "Archivo de ventas generado.");// TODO add your handling code here:
    }//GEN-LAST:event_jButtonGenerararchivoventaActionPerformed

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
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButtonActualizarventa;
    private javax.swing.JButton jButtonConsultartodo;
    private javax.swing.JButton jButtonGenerararchivo;
    private javax.swing.JButton jButtonGenerararchivoventa;
    private javax.swing.JButton jButtonGuardarCliente;
    private javax.swing.JButton jButtonLeerarchivo;
    private javax.swing.JButton jButtonLimpiar;
    private javax.swing.JButton jButtonLimpiar1;
    private javax.swing.JButton jButtonLimpiar2;
    private javax.swing.JButton jButtonLimpiar3;
    private javax.swing.JButton jButtonLimpiar4;
    private javax.swing.JButton jButtonLimpiar5;
    private javax.swing.JButton jButtonQCategoria;
    private javax.swing.JButton jButtonQEstado;
    private javax.swing.JButton jButtonQNumero;
    private javax.swing.JButton jButtonQPrimera;
    private javax.swing.JButton jButtonQUltima;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelFiltrar;
    private javax.swing.JLabel jLabelNumero;
    private javax.swing.JLabel jLabelRegistro;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanelConsultas;
    private javax.swing.JPanel jPanelRegistro;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextField jTextoFiltrar;
    // End of variables declaration//GEN-END:variables
}
