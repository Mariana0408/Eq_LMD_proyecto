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

        jPanel1 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabelnombredestino1 = new javax.swing.JLabel();
        jTextFieldnombredesstino1 = new javax.swing.JTextField();
        jLabeldiaspermanencia1 = new javax.swing.JLabel();
        jTextFieldiaspermanenciadestino1 = new javax.swing.JTextField();
        jLabelatrictivosincluidos1 = new javax.swing.JLabel();
        jTextFieldatractivosincluidos1 = new javax.swing.JTextField();
        jButtonLimpiar6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTextFieldID1 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextFieldNumeroID1 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jTextFieldemail1 = new javax.swing.JTextField();
        jTextFieldtelefono1 = new javax.swing.JTextField();
        jTextFieldnombrecontacto1 = new javax.swing.JTextField();
        jTextFieldporcentajedescuento1 = new javax.swing.JTextField();
        jTextFieldEmpresa1 = new javax.swing.JTextField();
        jTextFieldNombre1 = new javax.swing.JTextField();
        jButtonLimpiarcliente1 = new javax.swing.JButton();
        jButtonGuardarCliente1 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabelnombredestino2 = new javax.swing.JLabel();
        jTextFieldnombredesstino2 = new javax.swing.JTextField();
        jLabeldiaspermanencia2 = new javax.swing.JLabel();
        jTextFieldiaspermanenciadestino2 = new javax.swing.JTextField();
        jLabelatrictivosincluidos2 = new javax.swing.JLabel();
        jTextFieldatractivosincluidos2 = new javax.swing.JTextField();
        jButtonLimpiar7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabelalimentaciontodo1 = new javax.swing.JLabel();
        jLabelalimentacion1 = new javax.swing.JLabel();
        jLabelasistencia1 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jTextFieldalimentacion1 = new javax.swing.JTextField();
        jTextFielalimentaciontodo1 = new javax.swing.JTextField();
        jTextFielasistencia1 = new javax.swing.JTextField();
        jTextFieldnombrepaqueteturistico1 = new javax.swing.JTextField();
        jTextFieldcodigopaquete1 = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabelnombrepaqueteturistico1 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jTextFieldcantidadunidades1 = new javax.swing.JTextField();
        jTextFielddescripcionpaquete1 = new javax.swing.JTextField();
        jTextFieldorigen1 = new javax.swing.JTextField();
        jTextFieldhotelpaquete1 = new javax.swing.JTextField();
        jTextFieldtipologiaturismo1 = new javax.swing.JTextField();
        jButtonGuardarpaqueteturistico1 = new javax.swing.JButton();
        jTextFieldtarifadia1 = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jTextFieldvuelo1 = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jTextFieldTipohotel1 = new javax.swing.JTextField();
        jTextFieldtipodesayuno1 = new javax.swing.JTextField();
        jButtonLimpiarpaqueteturisticoU1 = new javax.swing.JButton();
        jButtonguardarpaqueteunico1 = new javax.swing.JButton();
        jPanelTodoselementos1 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jPanel13 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jTextFieldobsequio1 = new javax.swing.JTextField();
        jButtonLimpiarpaqueteturisticoM1 = new javax.swing.JButton();
        jButtonguardarpaquetemultiple1 = new javax.swing.JButton();
        jPanelRegistro1 = new javax.swing.JPanel();
        jLabelRegistro1 = new javax.swing.JLabel();
        jTextFieldnumeroventa1 = new javax.swing.JTextField();
        jLabelNumeroventa1 = new javax.swing.JLabel();
        jLabelestadoventa1 = new javax.swing.JLabel();
        jTextFieldestadoventa1 = new javax.swing.JTextField();
        jButtonLimpiarventa1 = new javax.swing.JButton();
        jButtonguardarventa1 = new javax.swing.JButton();
        jPanelConsultas1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabelFiltrar1 = new javax.swing.JLabel();
        jTextoFiltrar1 = new javax.swing.JTextField();
        jButtonConsultartodo1 = new javax.swing.JButton();
        jButtonQNumero1 = new javax.swing.JButton();
        jButtonQPrimera1 = new javax.swing.JButton();
        jButtonQUltima1 = new javax.swing.JButton();
        jButtonQEstado1 = new javax.swing.JButton();
        jButtonQCategoria1 = new javax.swing.JButton();
        jButtonGenerararchivoventa1 = new javax.swing.JButton();
        jButtonGenerararchivo1 = new javax.swing.JButton();
        jButtonLeerarchivo1 = new javax.swing.JButton();
        jButtonSalir1 = new javax.swing.JButton();
        jButtonActualizarventa1 = new javax.swing.JButton();
        jTextArea1 = new javax.swing.JTextArea();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel38.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel38.setText("DESTINO");

        jLabelnombredestino1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabelnombredestino1.setText("nombreLugar");

        jLabeldiaspermanencia1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabeldiaspermanencia1.setText("Dias Permanencia");

        jLabelatrictivosincluidos1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabelatrictivosincluidos1.setText("Atractivos Inlcuidos");

        jButtonLimpiar6.setText("Limpiar");

        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton7.setText("Guardar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                            .addComponent(jLabeldiaspermanencia1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldiaspermanenciadestino1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                            .addComponent(jLabelnombredestino1)
                            .addGap(57, 57, 57)
                            .addComponent(jTextFieldnombredesstino1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabelatrictivosincluidos1)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldatractivosincluidos1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonLimpiar6, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(205, 205, 205)
                        .addComponent(jLabel38)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel38)
                .addGap(27, 27, 27)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelnombredestino1)
                    .addComponent(jTextFieldnombredesstino1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabeldiaspermanencia1)
                            .addComponent(jTextFieldiaspermanenciadestino1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelatrictivosincluidos1)
                            .addComponent(jTextFieldatractivosincluidos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton7)
                        .addGap(21, 21, 21)
                        .addComponent(jButtonLimpiar6)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestion de ventas de paquetes turisticos");

        jLabel16.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel16.setText("REGISTRO DE CLIENTE");
        jLabel16.setToolTipText("");

        jLabel21.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel21.setText("tipoIdentificacion");

        jLabel22.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel22.setText("numeroIdentificacion");

        jLabel24.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel24.setText("Empresa");

        jLabel33.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel33.setText("Nombre");

        jLabel34.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel34.setText("Email");

        jLabel35.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel35.setText("Telefono");

        jLabel36.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel36.setText("nombreContacto");

        jLabel37.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel37.setText("porcentajeDescuento");

        jButtonLimpiarcliente1.setText("Limpiar");
        jButtonLimpiarcliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarcliente1ActionPerformed(evt);
            }
        });

        jButtonGuardarCliente1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonGuardarCliente1.setText("Guardar Cliente");
        jButtonGuardarCliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarCliente1ActionPerformed(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel39.setText("DESTINO");

        jLabelnombredestino2.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabelnombredestino2.setText("nombreLugar");

        jTextFieldnombredesstino2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldnombredesstino2ActionPerformed(evt);
            }
        });

        jLabeldiaspermanencia2.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabeldiaspermanencia2.setText("Dias Permanencia");

        jLabelatrictivosincluidos2.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabelatrictivosincluidos2.setText("Atractivos Inlcuidos");

        jButtonLimpiar7.setText("Limpiar");
        jButtonLimpiar7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiar7ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton8.setText("Guardar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                            .addComponent(jLabeldiaspermanencia2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldiaspermanenciadestino2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                            .addComponent(jLabelnombredestino2)
                            .addGap(57, 57, 57)
                            .addComponent(jTextFieldnombredesstino2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabelatrictivosincluidos2)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldatractivosincluidos2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonLimpiar7, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(205, 205, 205)
                        .addComponent(jLabel39)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel39)
                .addGap(27, 27, 27)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelnombredestino2)
                    .addComponent(jTextFieldnombredesstino2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabeldiaspermanencia2)
                            .addComponent(jTextFieldiaspermanenciadestino2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelatrictivosincluidos2)
                            .addComponent(jTextFieldatractivosincluidos2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton8)
                        .addGap(21, 21, 21)
                        .addComponent(jButtonLimpiar7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jButtonLimpiarcliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonGuardarCliente1))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel21)
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel33)
                                        .addComponent(jLabel24)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldEmpresa1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldNumeroID1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldID1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGap(205, 205, 205)
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel36)
                                            .addComponent(jLabel35)
                                            .addComponent(jLabel37)
                                            .addComponent(jLabel34)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addGap(96, 96, 96)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldemail1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldtelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldnombrecontacto1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldporcentajedescuento1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 181, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jTextFieldID1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(jTextFieldemail1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jTextFieldNumeroID1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35)
                    .addComponent(jTextFieldtelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jTextFieldEmpresa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36)
                    .addComponent(jTextFieldnombrecontacto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jTextFieldNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37)
                    .addComponent(jTextFieldporcentajedescuento1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonLimpiarcliente1)
                    .addComponent(jButtonGuardarCliente1))
                .addGap(70, 70, 70)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105))
        );

        jTabbedPane1.addTab("tab1", jPanel9);

        jLabel40.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel40.setText("REGISTRO PAQUETE TURISTICO");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PaqueteTuristicoUnico", "PaqueteTuristicoMultiple", " " }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabelalimentaciontodo1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabelalimentaciontodo1.setText("alimentacionTodo");

        jLabelalimentacion1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabelalimentacion1.setText("Alimentacion");

        jLabelasistencia1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabelasistencia1.setText("Asistencia");

        jLabel41.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel41.setText("tarifaDia");

        jLabel42.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel42.setText("cantidadUnidades");

        jLabel43.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel43.setText("Codigo");

        jLabelnombrepaqueteturistico1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabelnombrepaqueteturistico1.setText("Nombre");

        jLabel44.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel44.setText("tipologiaTurismo");

        jLabel45.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel45.setText("Descripción");

        jLabel46.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel46.setText("Origen");

        jLabel47.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel47.setText("Hotel");

        jButtonGuardarpaqueteturistico1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonGuardarpaqueteturistico1.setText("Guardar");
        jButtonGuardarpaqueteturistico1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarpaqueteturistico1ActionPerformed(evt);
            }
        });

        jLabel48.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel48.setText("Vuelo");

        jLabel51.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel51.setText("PAQUETE TURISTICO UNICO");

        jLabel52.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel52.setText("TipoHotel");

        jLabel53.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel53.setText("TipoDesayuno (Opcional)");

        jButtonLimpiarpaqueteturisticoU1.setText("Limpiar");
        jButtonLimpiarpaqueteturisticoU1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarpaqueteturisticoU1ActionPerformed(evt);
            }
        });

        jButtonguardarpaqueteunico1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonguardarpaqueteunico1.setText("Guardar");
        jButtonguardarpaqueteunico1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonguardarpaqueteunico1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelTodoselementos1Layout = new javax.swing.GroupLayout(jPanelTodoselementos1);
        jPanelTodoselementos1.setLayout(jPanelTodoselementos1Layout);
        jPanelTodoselementos1Layout.setHorizontalGroup(
            jPanelTodoselementos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 804, Short.MAX_VALUE)
        );
        jPanelTodoselementos1Layout.setVerticalGroup(
            jPanelTodoselementos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 673, Short.MAX_VALUE)
        );

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane5.setViewportView(jTextArea3);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(658, 658, 658))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jButtonLimpiarpaqueteturisticoU1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonguardarpaqueteunico1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel14Layout.createSequentialGroup()
                                    .addComponent(jLabel53)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextFieldtipodesayuno1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel14Layout.createSequentialGroup()
                                    .addComponent(jLabel52)
                                    .addGap(131, 131, 131)
                                    .addComponent(jTextFieldTipohotel1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanelTodoselementos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(jLabel51)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel51)
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(jTextFieldTipohotel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(jTextFieldtipodesayuno1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonLimpiarpaqueteturisticoU1)
                    .addComponent(jButtonguardarpaqueteunico1))
                .addGap(117, 117, 117)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelTodoselementos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel49.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel49.setText("PAQUETE TURISTICO MULTIPLE");

        jLabel50.setText("Obsequio");

        jButtonLimpiarpaqueteturisticoM1.setText("Limpiar");
        jButtonLimpiarpaqueteturisticoM1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarpaqueteturisticoM1ActionPerformed(evt);
            }
        });

        jButtonguardarpaquetemultiple1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonguardarpaquetemultiple1.setText("Guardar");
        jButtonguardarpaquetemultiple1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonguardarpaquetemultiple1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jButtonLimpiarpaqueteturisticoM1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(jButtonguardarpaquetemultiple1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel50)
                        .addGap(58, 58, 58)
                        .addComponent(jTextFieldobsequio1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel49)
                        .addGap(82, 82, 82))))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel49)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldobsequio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50))
                .addGap(34, 34, 34)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonLimpiarpaqueteturisticoM1)
                    .addComponent(jButtonguardarpaquetemultiple1))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(194, 194, 194)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextFieldhotelpaquete1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                                    .addComponent(jTextFieldnombrepaqueteturistico1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldcodigopaquete1))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel40)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel12Layout.createSequentialGroup()
                                                .addGap(68, 68, 68)
                                                .addComponent(jLabelalimentacion1)
                                                .addGap(48, 48, 48))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabelasistencia1)
                                                    .addComponent(jLabelalimentaciontodo1)
                                                    .addComponent(jLabelnombrepaqueteturistico1)
                                                    .addComponent(jLabel43)
                                                    .addComponent(jLabel47))
                                                .addGap(18, 18, 18)))
                                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextFieldalimentacion1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFielalimentaciontodo1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFielasistencia1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(55, 55, 55)))
                                .addGap(8, 8, 8))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addContainerGap(16, Short.MAX_VALUE)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel42)
                                    .addComponent(jLabel41)
                                    .addComponent(jLabel44)
                                    .addComponent(jLabel45)
                                    .addComponent(jLabel46)
                                    .addComponent(jLabel48))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldtarifadia1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldcantidadunidades1, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                                    .addComponent(jTextFieldtipologiaturismo1)
                                    .addComponent(jTextFielddescripcionpaquete1)
                                    .addComponent(jTextFieldorigen1)
                                    .addComponent(jTextFieldvuelo1)))
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(76, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonGuardarpaqueteturistico1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(113, 113, 113))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel41)
                                    .addComponent(jTextFieldtarifadia1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelalimentacion1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel42)
                                    .addComponent(jTextFieldcantidadunidades1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFielalimentaciontodo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelalimentaciontodo1))
                                .addGap(26, 26, 26))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(jTextFieldalimentacion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelasistencia1)
                            .addComponent(jTextFielasistencia1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel44)
                            .addComponent(jTextFieldtipologiaturismo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel40))))
                .addGap(14, 14, 14)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel45)
                    .addComponent(jTextFielddescripcionpaquete1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldnombrepaqueteturistico1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelnombrepaqueteturistico1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldorigen1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldcodigopaquete1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel43)))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldhotelpaquete1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel48)
                        .addComponent(jTextFieldvuelo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32)
                .addComponent(jButtonGuardarpaqueteturistico1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(100, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab2", jPanel12);

        jLabelRegistro1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabelRegistro1.setText("REGISTRO DE VENTA");

        jLabelNumeroventa1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabelNumeroventa1.setText("Número");

        jLabelestadoventa1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabelestadoventa1.setText("Estado");

        jButtonLimpiarventa1.setText("Limpiar");
        jButtonLimpiarventa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarventa1ActionPerformed(evt);
            }
        });

        jButtonguardarventa1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonguardarventa1.setText("Guardar");
        jButtonguardarventa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonguardarventa1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel3.setText("CONSULTAS Y OPERACIONES");

        jLabelFiltrar1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabelFiltrar1.setText("FILTRAR");

        jTextoFiltrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextoFiltrar1ActionPerformed(evt);
            }
        });

        jButtonConsultartodo1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonConsultartodo1.setText("Consultar Todo");
        jButtonConsultartodo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsultartodo1ActionPerformed(evt);
            }
        });

        jButtonQNumero1.setText("Por numero");
        jButtonQNumero1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonQNumero1ActionPerformed(evt);
            }
        });

        jButtonQPrimera1.setText("Primera");
        jButtonQPrimera1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonQPrimera1ActionPerformed(evt);
            }
        });

        jButtonQUltima1.setText("Ultima");
        jButtonQUltima1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonQUltima1ActionPerformed(evt);
            }
        });

        jButtonQEstado1.setText("Por estado");
        jButtonQEstado1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonQEstado1ActionPerformed(evt);
            }
        });

        jButtonQCategoria1.setText("Por categoria");
        jButtonQCategoria1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonQCategoria1ActionPerformed(evt);
            }
        });

        jButtonGenerararchivoventa1.setText("Generar archivo venta");
        jButtonGenerararchivoventa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGenerararchivoventa1ActionPerformed(evt);
            }
        });

        jButtonGenerararchivo1.setText("Generar archivo clientes");
        jButtonGenerararchivo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGenerararchivo1ActionPerformed(evt);
            }
        });

        jButtonLeerarchivo1.setText("Leer archivo clientes");
        jButtonLeerarchivo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLeerarchivo1ActionPerformed(evt);
            }
        });

        jButtonSalir1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonSalir1.setText("Salir");
        jButtonSalir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalir1ActionPerformed(evt);
            }
        });

        jButtonActualizarventa1.setText("Actualizar venta");
        jButtonActualizarventa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizarventa1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelConsultas1Layout = new javax.swing.GroupLayout(jPanelConsultas1);
        jPanelConsultas1.setLayout(jPanelConsultas1Layout);
        jPanelConsultas1Layout.setHorizontalGroup(
            jPanelConsultas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConsultas1Layout.createSequentialGroup()
                .addGroup(jPanelConsultas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelConsultas1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanelConsultas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelConsultas1Layout.createSequentialGroup()
                                .addGap(193, 193, 193)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelConsultas1Layout.createSequentialGroup()
                                .addComponent(jLabelFiltrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextoFiltrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelConsultas1Layout.createSequentialGroup()
                                .addComponent(jButtonQEstado1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jButtonConsultartodo1)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonQNumero1)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonQPrimera1)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonQUltima1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonActualizarventa1))))
                    .addGroup(jPanelConsultas1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jButtonGenerararchivo1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonLeerarchivo1)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonGenerararchivoventa1)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonQCategoria1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelConsultas1Layout.createSequentialGroup()
                        .addGap(258, 258, 258)
                        .addComponent(jButtonSalir1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelConsultas1Layout.setVerticalGroup(
            jPanelConsultas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConsultas1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(5, 5, 5)
                .addGroup(jPanelConsultas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFiltrar1)
                    .addComponent(jTextoFiltrar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelConsultas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonQEstado1)
                    .addComponent(jButtonConsultartodo1)
                    .addComponent(jButtonQNumero1)
                    .addComponent(jButtonQPrimera1)
                    .addComponent(jButtonQUltima1)
                    .addComponent(jButtonActualizarventa1))
                .addGap(20, 20, 20)
                .addGroup(jPanelConsultas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGenerararchivo1)
                    .addComponent(jButtonLeerarchivo1)
                    .addComponent(jButtonGenerararchivoventa1)
                    .addComponent(jButtonQCategoria1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonSalir1)
                .addGap(319, 319, 319))
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);

        javax.swing.GroupLayout jPanelRegistro1Layout = new javax.swing.GroupLayout(jPanelRegistro1);
        jPanelRegistro1.setLayout(jPanelRegistro1Layout);
        jPanelRegistro1Layout.setHorizontalGroup(
            jPanelRegistro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRegistro1Layout.createSequentialGroup()
                .addGroup(jPanelRegistro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 675, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelRegistro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelRegistro1Layout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addGroup(jPanelRegistro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanelConsultas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRegistro1Layout.createSequentialGroup()
                                    .addGap(59, 59, 59)
                                    .addGroup(jPanelRegistro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelNumeroventa1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelestadoventa1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanelRegistro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextFieldestadoventa1)
                                        .addComponent(jTextFieldnumeroventa1, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
                                    .addGroup(jPanelRegistro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanelRegistro1Layout.createSequentialGroup()
                                            .addGap(129, 129, 129)
                                            .addComponent(jButtonLimpiarventa1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRegistro1Layout.createSequentialGroup()
                                            .addGap(127, 127, 127)
                                            .addComponent(jButtonguardarventa1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(75, 75, 75))))
                        .addGroup(jPanelRegistro1Layout.createSequentialGroup()
                            .addGap(273, 273, 273)
                            .addComponent(jLabelRegistro1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(157, Short.MAX_VALUE))
        );
        jPanelRegistro1Layout.setVerticalGroup(
            jPanelRegistro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRegistro1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabelRegistro1)
                .addGap(34, 34, 34)
                .addGroup(jPanelRegistro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonLimpiarventa1)
                    .addComponent(jTextFieldnumeroventa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNumeroventa1))
                .addGap(18, 18, 18)
                .addGroup(jPanelRegistro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonguardarventa1)
                    .addComponent(jLabelestadoventa1)
                    .addComponent(jTextFieldestadoventa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(jPanelConsultas1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextArea1, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("tab3", jPanelRegistro1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 854, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 674, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonGuardarCliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarCliente1ActionPerformed
            Cliente objCliente = new Cliente(
            jTextFieldID1.getText().toUpperCase().charAt(0),
            jTextFieldNumeroID1.getText(),
            jTextFieldEmpresa1.getText().toUpperCase().startsWith("S"),
            jTextFieldNombre1.getText(),
            jTextFieldemail1.getText(),
            jTextFieldtelefono1.getText(),
            jTextFieldnombrecontacto1.getText(),
            Double.parseDouble(jTextFieldporcentajedescuento1.getText())
        );
        datosClientes.add(objCliente);
        JOptionPane.showMessageDialog(null, "Cliente registrado correctamente.");    // TODO add your handling code here:
    }//GEN-LAST:event_jButtonGuardarCliente1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
           jTextFieldID1.setText("");
        jTextFieldNumeroID1.setText("");
        jTextFieldEmpresa1.setText("");
        jTextFieldNombre1.setText("");
        jTextFieldemail1.setText("");
        jTextFieldtelefono1.setText("");
        jTextFieldnombrecontacto1.setText("");
        jTextFieldporcentajedescuento1.setText("");   // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
            LinkedList<String> atractivos = new LinkedList<>();
        Destino objDestino = new Destino(
            jTextFieldnombredesstino1.getText(),
            Integer.parseInt(jTextFieldiaspermanenciadestino1.getText()),
            atractivos,
            jTextFieldatractivosincluidos1.getText().toUpperCase().startsWith("S")
        );
        listaDestinos.add(objDestino);
        JOptionPane.showMessageDialog(null, "Destino registrado correctamente.");    // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButtonGuardarpaqueteturistico1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarpaqueteturistico1ActionPerformed
            String categoria = jComboBox2.getSelectedItem().toString();
        ArrayList<Destino> destinosCopia = new ArrayList<>(listaDestinos);
        boolean hotel        = jTextFieldhotelpaquete1.getText().toUpperCase().startsWith("S");
        boolean alimentacion = jTextFieldalimentacion1.getText().toUpperCase().startsWith("S");
        boolean alimTodo     = jTextFielalimentaciontodo1.getText().toUpperCase().startsWith("S");
        boolean vuelo        = jTextFieldvuelo1.getText().toUpperCase().startsWith("S");
        boolean asistencia   = jTextFielasistencia1.getText().toUpperCase().startsWith("S");
        int tarifa   = jTextFieldtarifadia1.getText().trim().isEmpty() ? 0 : Integer.parseInt(jTextFieldtarifadia1.getText().trim());
        int cantidad = jTextFieldcantidadunidades1.getText().trim().isEmpty() ? 0 : Integer.parseInt(jTextFieldcantidadunidades1.getText().trim());
        if (categoria.equals("PaqueteTuristicoUnico")) {
            PaqueteTuristicoUnico objPaqueteUnico = new PaqueteTuristicoUnico(
                jTextFieldTipohotel1.getText(),
                jTextFieldtipodesayuno1.getText(),
                jTextFieldcodigopaquete1.getText(),
                jTextFieldnombrepaqueteturistico1.getText(),
                jTextFieldtipologiaturismo1.getText(),
                jTextFielddescripcionpaquete1.getText(),
                jTextFieldorigen1.getText(),
                destinosCopia,
                hotel, alimentacion, alimTodo, vuelo, asistencia,
                tarifa, cantidad
            );
            listaPaquetes.add(objPaqueteUnico);
        } else {
            PaqueteTuristicoMultiple objPaqueteMultiple = new PaqueteTuristicoMultiple(
                jTextFieldobsequio1.getText(),
                jTextFieldcodigopaquete1.getText(),
                jTextFieldnombrepaqueteturistico1.getText(),
                jTextFieldtipologiaturismo1.getText(),
                jTextFielddescripcionpaquete1.getText(),
                jTextFieldorigen1.getText(),
                destinosCopia,
                hotel, alimentacion, alimTodo, vuelo, asistencia,
                tarifa, cantidad
            );
            listaPaquetes.add(objPaqueteMultiple);
        }
        listaDestinos.clear();
        JOptionPane.showMessageDialog(null, "Paquete registrado. Total paquetes: " + listaPaquetes.size());    // TODO add your handling code here:
    }//GEN-LAST:event_jButtonGuardarpaqueteturistico1ActionPerformed

    private void jButtonLimpiarpaqueteturisticoM1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarpaqueteturisticoM1ActionPerformed
            jTextFieldobsequio1.setText("");
        jTextFieldcodigopaquete1.setText("");
        jTextFieldnombrepaqueteturistico1.setText("");
        jTextFieldtipologiaturismo1.setText("");
        jTextFielddescripcionpaquete1.setText("");
        jTextFieldorigen1.setText("");
        jTextFieldhotelpaquete1.setText("");
        jTextFieldalimentacion1.setText("");
        jTextFielalimentaciontodo1.setText("");
        jTextFielasistencia1.setText("");
        jTextFieldvuelo1.setText("");
        jTextFieldtarifadia1.setText("");
        jTextFieldcantidadunidades1.setText("");    // TODO add your handling code here:
    }//GEN-LAST:event_jButtonLimpiarpaqueteturisticoM1ActionPerformed

    private void jButtonguardarpaquetemultiple1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonguardarpaquetemultiple1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonguardarpaquetemultiple1ActionPerformed

    private void jButtonLimpiarpaqueteturisticoU1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarpaqueteturisticoU1ActionPerformed
            jTextFieldTipohotel1.setText("");
        jTextFieldtipodesayuno1.setText("");
        jTextFieldcodigopaquete1.setText("");
        jTextFieldnombrepaqueteturistico1.setText("");
        jTextFieldtipologiaturismo1.setText("");
        jTextFielddescripcionpaquete1.setText("");
        jTextFieldorigen1.setText("");
        jTextFieldhotelpaquete1.setText("");
        jTextFieldalimentacion1.setText("");
        jTextFielalimentaciontodo1.setText("");
        jTextFielasistencia1.setText("");
        jTextFieldvuelo1.setText("");
        jTextFieldtarifadia1.setText("");
        jTextFieldcantidadunidades1.setText("");    // TODO add your handling code here:
    }//GEN-LAST:event_jButtonLimpiarpaqueteturisticoU1ActionPerformed

    private void jButtonguardarpaqueteunico1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonguardarpaqueteunico1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonguardarpaqueteunico1ActionPerformed

    private void jButtonguardarventa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonguardarventa1ActionPerformed
            if (datosClientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Primero guarde el cliente.");
            return;
        }
        if (listaPaquetes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Primero guarde al menos un paquete.");
            return;
        }
        Cliente clienteVenta = datosClientes.get(datosClientes.size() - 1);
        int numeroVenta = generarNumeroVenta(datosVentas);
        Venta objVenta = new Venta(numeroVenta, clienteVenta, new ArrayList<>(listaPaquetes));
        datosVentas.add(objVenta);
        jTextFieldnumeroventa1.setText(String.valueOf(objVenta.getNumero()));
        jTextFieldestadoventa1.setText(String.valueOf(objVenta.getEstado()));
        listaPaquetes.clear();
        listaDestinos.clear();
        JOptionPane.showMessageDialog(null, "Venta registrada correctamente.");    // TODO add your handling code here:
    }//GEN-LAST:event_jButtonguardarventa1ActionPerformed

    private void jTextoFiltrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextoFiltrar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextoFiltrar1ActionPerformed

    private void jButtonConsultartodo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsultartodo1ActionPerformed
            if (datosVentas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay ventas registradas.");
        } else {
            jTextArea1.setText(consultarTodasVentas(datosVentas));
        }    // TODO add your handling code here:
    }//GEN-LAST:event_jButtonConsultartodo1ActionPerformed

    private void jButtonQNumero1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQNumero1ActionPerformed
            if (jTextoFiltrar1.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Escribe un número en el campo FILTRAR.");
        } else {
            jTextArea1.setText(consultarVentaDadoNumero(datosVentas, Integer.parseInt(jTextoFiltrar1.getText().trim())));    // TODO add your handling code here:
    }//GEN-LAST:event_jButtonQNumero1ActionPerformed
    }
    private void jButtonQPrimera1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQPrimera1ActionPerformed
            if (datosVentas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay ventas registradas.");
        } else {
            jTextArea1.setText(consultarVentaDadaPosicion(datosVentas, 'P'));
            }// TODO add your handling code here:
    }//GEN-LAST:event_jButtonQPrimera1ActionPerformed

    private void jButtonQUltima1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQUltima1ActionPerformed
            if (datosVentas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay ventas registradas.");
        } else {
            jTextArea1.setText(consultarVentaDadaPosicion(datosVentas, 'U'));
        }    // TODO add your handling code here:
    }//GEN-LAST:event_jButtonQUltima1ActionPerformed

    private void jButtonQEstado1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQEstado1ActionPerformed
            if (jTextoFiltrar1.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Escribe un estado en FILTRAR (A, P o C).");
        } else {
            jTextArea1.setText(consultarVentasDadoEstado(datosVentas, jTextoFiltrar1.getText().toUpperCase().charAt(0)));
        }   // TODO add your handling code here:
    }//GEN-LAST:event_jButtonQEstado1ActionPerformed

    private void jButtonQCategoria1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQCategoria1ActionPerformed
            if (jTextoFiltrar1.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Escribe una categoría en FILTRAR.");
        } else {
            jTextArea1.setText(consultarVentasDadaCategoriaPaquete(datosVentas, jTextoFiltrar1.getText()));
        }    // TODO add your handling code here:
    }//GEN-LAST:event_jButtonQCategoria1ActionPerformed

    private void jButtonGenerararchivoventa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGenerararchivoventa1ActionPerformed
    generarArchivoObjetosVentas(datosVentas);
        JOptionPane.showMessageDialog(null, "Archivo de ventas generado.");    // TODO add your handling code here:
    }//GEN-LAST:event_jButtonGenerararchivoventa1ActionPerformed

    private void jButtonGenerararchivo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGenerararchivo1ActionPerformed
            generarArchivoObjetosClientes(datosClientes);
        JOptionPane.showMessageDialog(null, "Archivo de clientes generado.");    // TODO add your handling code here:
    }//GEN-LAST:event_jButtonGenerararchivo1ActionPerformed

    private void jButtonLeerarchivo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLeerarchivo1ActionPerformed
        recuperarClientesDesdeArchivoObjetos();
        JOptionPane.showMessageDialog(null, "Clientes recuperados desde archivo.");    // TODO add your handling code here:
    }//GEN-LAST:event_jButtonLeerarchivo1ActionPerformed

    private void jButtonSalir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalir1ActionPerformed
            JOptionPane.showMessageDialog(null, "¡Gracias por utilizar nuestro programa!");
        System.exit(0);    // TODO add your handling code here:
    }//GEN-LAST:event_jButtonSalir1ActionPerformed

    private void jButtonActualizarventa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarventa1ActionPerformed
        if (jTextoFiltrar1.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Escribe el número de venta en FILTRAR.");
        } else {
            actualizarVenta(datosVentas, Integer.parseInt(jTextoFiltrar1.getText().trim()),
                JOptionPane.showInputDialog("Operación (C: Cancelar, P: Pagar)").toUpperCase().charAt(0));
        }// TODO add your handling code here:
    }//GEN-LAST:event_jButtonActualizarventa1ActionPerformed

    private void jButtonLimpiarcliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarcliente1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonLimpiarcliente1ActionPerformed

    private void jTextFieldnombredesstino2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldnombredesstino2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldnombredesstino2ActionPerformed

    private void jButtonLimpiar7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiar7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonLimpiar7ActionPerformed

    private void jButtonLimpiarventa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarventa1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonLimpiarventa1ActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> {
            new UsaGUIVenta().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButtonActualizarventa1;
    private javax.swing.JButton jButtonConsultartodo1;
    private javax.swing.JButton jButtonGenerararchivo1;
    private javax.swing.JButton jButtonGenerararchivoventa1;
    private javax.swing.JButton jButtonGuardarCliente1;
    private javax.swing.JButton jButtonGuardarpaqueteturistico1;
    private javax.swing.JButton jButtonLeerarchivo1;
    private javax.swing.JButton jButtonLimpiar6;
    private javax.swing.JButton jButtonLimpiar7;
    private javax.swing.JButton jButtonLimpiarcliente1;
    private javax.swing.JButton jButtonLimpiarpaqueteturisticoM1;
    private javax.swing.JButton jButtonLimpiarpaqueteturisticoU1;
    private javax.swing.JButton jButtonLimpiarventa1;
    private javax.swing.JButton jButtonQCategoria1;
    private javax.swing.JButton jButtonQEstado1;
    private javax.swing.JButton jButtonQNumero1;
    private javax.swing.JButton jButtonQPrimera1;
    private javax.swing.JButton jButtonQUltima1;
    private javax.swing.JButton jButtonSalir1;
    private javax.swing.JButton jButtonguardarpaquetemultiple1;
    private javax.swing.JButton jButtonguardarpaqueteunico1;
    private javax.swing.JButton jButtonguardarventa1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabelFiltrar1;
    private javax.swing.JLabel jLabelNumeroventa1;
    private javax.swing.JLabel jLabelRegistro1;
    private javax.swing.JLabel jLabelalimentacion1;
    private javax.swing.JLabel jLabelalimentaciontodo1;
    private javax.swing.JLabel jLabelasistencia1;
    private javax.swing.JLabel jLabelatrictivosincluidos1;
    private javax.swing.JLabel jLabelatrictivosincluidos2;
    private javax.swing.JLabel jLabeldiaspermanencia1;
    private javax.swing.JLabel jLabeldiaspermanencia2;
    private javax.swing.JLabel jLabelestadoventa1;
    private javax.swing.JLabel jLabelnombredestino1;
    private javax.swing.JLabel jLabelnombredestino2;
    private javax.swing.JLabel jLabelnombrepaqueteturistico1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelConsultas1;
    private javax.swing.JPanel jPanelRegistro1;
    private javax.swing.JPanel jPanelTodoselementos1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextField jTextFielalimentaciontodo1;
    private javax.swing.JTextField jTextFielasistencia1;
    private javax.swing.JTextField jTextFieldEmpresa1;
    private javax.swing.JTextField jTextFieldID1;
    private javax.swing.JTextField jTextFieldNombre1;
    private javax.swing.JTextField jTextFieldNumeroID1;
    private javax.swing.JTextField jTextFieldTipohotel1;
    private javax.swing.JTextField jTextFieldalimentacion1;
    private javax.swing.JTextField jTextFieldatractivosincluidos1;
    private javax.swing.JTextField jTextFieldatractivosincluidos2;
    private javax.swing.JTextField jTextFieldcantidadunidades1;
    private javax.swing.JTextField jTextFieldcodigopaquete1;
    private javax.swing.JTextField jTextFielddescripcionpaquete1;
    private javax.swing.JTextField jTextFieldemail1;
    private javax.swing.JTextField jTextFieldestadoventa1;
    private javax.swing.JTextField jTextFieldhotelpaquete1;
    private javax.swing.JTextField jTextFieldiaspermanenciadestino1;
    private javax.swing.JTextField jTextFieldiaspermanenciadestino2;
    private javax.swing.JTextField jTextFieldnombrecontacto1;
    private javax.swing.JTextField jTextFieldnombredesstino1;
    private javax.swing.JTextField jTextFieldnombredesstino2;
    private javax.swing.JTextField jTextFieldnombrepaqueteturistico1;
    private javax.swing.JTextField jTextFieldnumeroventa1;
    private javax.swing.JTextField jTextFieldobsequio1;
    private javax.swing.JTextField jTextFieldorigen1;
    private javax.swing.JTextField jTextFieldporcentajedescuento1;
    private javax.swing.JTextField jTextFieldtarifadia1;
    private javax.swing.JTextField jTextFieldtelefono1;
    private javax.swing.JTextField jTextFieldtipodesayuno1;
    private javax.swing.JTextField jTextFieldtipologiaturismo1;
    private javax.swing.JTextField jTextFieldvuelo1;
    private javax.swing.JTextField jTextoFiltrar1;
    // End of variables declaration//GEN-END:variables
}
