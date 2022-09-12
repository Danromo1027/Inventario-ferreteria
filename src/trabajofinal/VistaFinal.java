/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajofinal;
import Modelo.Categorias;
import Modelo.Consultas;
import Modelo.Productos;
import Modelo.Proveedor;
import static trabajofinal.Conexion.getConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AndresOsorio
 */
public class VistaFinal extends javax.swing.JFrame {
    /****************MODEL TABLAS**********************************/
       public DefaultTableModel tableProvTodoModel;
       public DefaultTableModel tableProdTodoModel;
       public DefaultTableModel tableCatTodoModel;
    /****************FIN MODEL TABLAS**********************************/
       /**
     * Creates new form VistaFinal
     */
    public VistaFinal() {
        initComponents();
        //OCULTAR BOX FILTRAR Y BOTON FILTRAR
        jComboBoxFilCat.setVisible(false);
        jButtonFilt.setVisible(false);
        jCheckBoxProv.setVisible(false);
        jCheckBoxCat.setVisible(false);
        jComboBoxFilProv.setVisible(false);
        jButtonFilt.setEnabled(false);
        // ACCIONES AL INICIAR EL PROGRAMA ///
        ConsulProd();// LLENAR LISTA OPCIONES CONSULTA
        ListCat();// LLENAR TODOS LOS LIST CATEGORIA
        ListProv();// LLENAR TODOS LOS LIST PROVEEDOR
        ListProd();// LLENAR TODOS LOS LIST PRODUCTOS
        GetAddCatId();//LLENAR ID CREAR CAT
        GetAddProvId();//LLENAR ID CREAR PROVEEDOR
        GetAddProdId();//LLENAR ID CREAR PRODUCTO
        /*******************************TABLAS******************************************/
        this.tableProvTodoModel=(DefaultTableModel)jTableConsProv.getModel();
        this.tableProdTodoModel=(DefaultTableModel)jTableConsCat.getModel();
        this.tableCatTodoModel=(DefaultTableModel)jTableConsCat.getModel();
        /****************FIN TABLAS**********************************/
                // TODO code application logic here

    }
    
    /**************************AUTO LLENADO ID CREAR*************************************/
    /********************RETORNAR ID CATEGORIA SIGUIENTE*********************************/
    public  void GetAddCatId(){
       int IdCat = 0;
        try{
                Connection con= getConexion();
                String sql="select max(cat_id) as max from categorias";
                PreparedStatement ps= con.prepareStatement(sql);
                
                //ps.execute(); para insert delete etc
                ResultSet rs = ps.executeQuery();
                
        while(rs.next()){                    
                    IdCat = rs.getInt("max");
                }
            IdCat = IdCat+1;
 
            }catch(Exception e){
                System.out.println(e);
            }
        jSpinnerCatID.setValue(IdCat);
    }
    /********************RETORNAR ID PROVEEDOR SIGUIENTE*********************************/
        public void GetAddProvId(){
        int IdProv = 0;
        try{
                Connection con= getConexion();
                String sql="select max(prov_id) as max from proveedor";
                PreparedStatement ps= con.prepareStatement(sql);
                
                //ps.execute(); para insert delete etc
                ResultSet rs = ps.executeQuery();
                
        while(rs.next()){                    
                    IdProv = rs.getInt("max");
                }
            IdProv=IdProv+1;
            }catch(Exception e){
                System.out.println(e);
            }
        jSpinnerProvID.setValue(IdProv);
    }
    /********************RETORNAR ID PRODUCTO SIGUIENTE*********************************/
            public void GetAddProdId(){
        int IdProd = 0;
        try{
                Connection con= getConexion();
                String sql="select max(pro_id) as max from productos";
                PreparedStatement ps= con.prepareStatement(sql);
                 
                //ps.execute(); para insert delete etc
                ResultSet rs = ps.executeQuery();
                
        while(rs.next()){                    
                    IdProd = rs.getInt("max");
                }
            IdProd=IdProd+1;
            }catch(Exception e){
                System.out.println(e);
            }
        jSpinnerIDProd.setValue(IdProd);      
    }
     /************************Consultas Prod LISTAS******************************/
    public  void ConsulProd(){       
        jComboBoxProd.removeAllItems();
        jComboBoxProd.addItem("Consultar todo");
        jComboBoxProd.addItem("Consultar clasificados por categoría");
        jComboBoxProd.addItem("Consultar clasificados por proveedor");
        jComboBoxProd.addItem("Consultar 3 mas economicos");     
    }    

    /************************FIN Consultas Prod LISTAS*******************************/
    
    /************************Consultar ID CATEGORIA*******************************/
        public static int consulCatID(String nombre){
        int IdCat = 0;
        try{
                Connection con= getConexion();
                String sql="select * from categorias where nombre=? ";//where cat_id = ? incognitas cuando vayan a pasar variables
                PreparedStatement ps= con.prepareStatement(sql);
                ps.setString(1,nombre); 
                //ps.execute(); para insert delete etc
                ResultSet rs = ps.executeQuery();
                
        while(rs.next()){                    
                    IdCat = rs.getInt("cat_id");
                }
 
            }catch(Exception e){
                System.out.println(e);
            }
        return IdCat;
    }
    /************************FIN Consultar ID CATEGORIA*******************************/
        
    /************************Consultar ID PROVEEDOR*******************************/
        public static int consulProvID(String nombre){
        int IdProv = 0;
        try{
                Connection con= getConexion();
                String sql="select * from proveedor where prov_nombre=? ";//where cat_id = ? incognitas cuando vayan a pasar variables
                PreparedStatement ps= con.prepareStatement(sql);
                ps.setString(1,nombre); 
                //ps.execute(); para insert delete etc
                ResultSet rs = ps.executeQuery();
                
        while(rs.next()){                    
                    IdProv = rs.getInt("prov_id");
                }
 
            }catch(Exception e){
                System.out.println(e);
            }
        return IdProv;
    }
    /************************FIN Consultar ID PROVEEDOR*******************************/
        
    /************************Consultar ID PRODUCTO*******************************/
        public static int consulProdID(String nombre){
        int IdProd = 0;
        try{
                Connection con= getConexion();
                String sql="select * from productos where pro_name=? ";//where cat_id = ? incognitas cuando vayan a pasar variables
                PreparedStatement ps= con.prepareStatement(sql);
                ps.setString(1,nombre); 
                //ps.execute(); para insert delete etc
                ResultSet rs = ps.executeQuery();
                
        while(rs.next()){                    
                    IdProd = rs.getInt("pro_id");
                }
 
            }catch(Exception e){
                System.out.println(e);
            }
        return IdProd;
    }
    /************************FIN Consultar ID PRODUCTO*******************************/        
        
    /************************LISTAR CATEGORIAS*******************************/
    public  void ListCat(){
             try{
                Connection con= getConexion();
                String sql="select * from categorias  ";//where cat_id = ? incognitas cuando vayan a pasar variables
                PreparedStatement ps= con.prepareStatement(sql);
                //ps.setInt(1,2); // aqui van reemplazando las variables de la query con lo que necesiten
                //ps.execute(); para insert delete etc
                ResultSet rs = ps.executeQuery();
                jComboBoxDelCat.removeAllItems();
                jComboBoxActCat.removeAllItems();
                jComboBoxProdCat.removeAllItems();
                jComboBoxActProdCat.removeAllItems();
                jComboBoxFilCat.removeAllItems();
                while(rs.next()){
                    
                    //System.out.println("Obtenido"); // PROBAR SI ESTA OBTENIENDO DATOS EN CONSOLA
                    // LLENAR LISTAS DONDE APARECE CATEGORIA
                    jComboBoxDelCat.addItem(rs.getString("nombre"));
                    jComboBoxActCat.addItem(rs.getString("nombre"));
                    jComboBoxProdCat.addItem(rs.getString("nombre"));
                    jComboBoxActProdCat.addItem(rs.getString("nombre"));
                    jComboBoxFilCat.addItem(rs.getString("nombre"));
                }
                
                
               
            }catch(Exception e){
                System.out.println(e);
            }
        }
    /************************ FIN LISTAR CATEGORIAS*******************************/
    
        /************************LISTAR PRODUCTOS*******************************/
    public  void ListProd(){
             try{
                Connection con= getConexion();
                String sql="select * from productos  ";//where cat_id = ? incognitas cuando vayan a pasar variables
                PreparedStatement ps= con.prepareStatement(sql);
                //ps.setInt(1,2); // aqui van reemplazando las variables de la query con lo que necesiten
                //ps.execute(); para insert delete etc
                ResultSet rs = ps.executeQuery();
                jComboBoxDelProd.removeAllItems();
                jComboBoxActProd.removeAllItems();
                while(rs.next()){
                    
                    //System.out.println("Obtenido"); // PROBAR SI ESTA OBTENIENDO DATOS EN CONSOLA
                    // LLENAR LISTAS DONDE APARECE CATEGORIA
                    jComboBoxDelProd.addItem(rs.getString("pro_name"));
                    jComboBoxActProd.addItem(rs.getString("pro_name"));
                    
                    
                    
                }
                
                
               
            }catch(Exception e){
                System.out.println(e);
            }
        }
    /************************ FIN LISTAR PRODUCTOS*******************************/
    
    /************************LISTAR PROVEEDORES*******************************/
    public  void ListProv(){
             try{
                Connection con= getConexion();
                String sql="select * from proveedor  ";//where cat_id = ?
                PreparedStatement ps= con.prepareStatement(sql);
                //ps.setInt(1,2);
                //ps.execute(); para insert delete etc
                ResultSet rs = ps.executeQuery();
                jComboBoxDelProv.removeAllItems();
                jComboBoxActProv.removeAllItems();
                jComboBoxProdProv.removeAllItems();
                jComboBoxActProdProv.removeAllItems();
                jComboBoxFilProv.removeAllItems();
                while(rs.next()){
                    
                    //System.out.println("Obtenido"); // PROBAR SI ESTA OBTENIENDO DATOS EN CONSOLA
                    // LLENAR LISTAS DONDE APARECE PROVEEDOR
                    jComboBoxDelProv.addItem(rs.getString("prov_nombre"));
                    jComboBoxActProv.addItem(rs.getString("prov_nombre"));
                    jComboBoxProdProv.addItem(rs.getString("prov_nombre"));
                    jComboBoxActProdProv.addItem(rs.getString("prov_nombre"));
                    jComboBoxFilProv.addItem(rs.getString("prov_nombre"));
                }
                
                
               
            }catch(Exception e){
                System.out.println(e);
            }
        }
    /************************ FIN LISTAR PROVEEDORES*******************************/

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem2 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem3 = new javax.swing.JRadioButtonMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        Consultar = new javax.swing.JTabbedPane();
        Categoria2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableConsCat = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        Proveedor2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableConsProv = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        Producto2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableConsulProd = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxProd = new javax.swing.JComboBox<>();
        jButtonConsulProd = new javax.swing.JButton();
        jComboBoxFilCat = new javax.swing.JComboBox<>();
        jToggleButtonCatCon = new javax.swing.JToggleButton();
        jButtonFilt = new javax.swing.JButton();
        jCheckBoxProv = new javax.swing.JCheckBox();
        jCheckBoxCat = new javax.swing.JCheckBox();
        jComboBoxFilProv = new javax.swing.JComboBox<>();
        Borrar = new javax.swing.JTabbedPane();
        Categoria4 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jComboBoxDelCat = new javax.swing.JComboBox<>();
        jButtonDelCat = new javax.swing.JButton();
        Proveedor4 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jComboBoxDelProv = new javax.swing.JComboBox<>();
        jButtonDelProv = new javax.swing.JButton();
        Producto4 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jComboBoxDelProd = new javax.swing.JComboBox<>();
        jButtonDelProd = new javax.swing.JButton();
        Crear = new javax.swing.JTabbedPane();
        Categoria = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jSpinnerCatID = new javax.swing.JSpinner();
        jTextFieldCatNom = new javax.swing.JTextField();
        jButtonAddCat = new javax.swing.JButton();
        jTextCatFecha = new javax.swing.JTextField();
        Proveedor = new javax.swing.JPanel();
        Categoria1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSpinnerProvID = new javax.swing.JSpinner();
        jTextFieldProvNom = new javax.swing.JTextField();
        jButtonAddProv = new javax.swing.JButton();
        jTextFieldProvTel = new javax.swing.JTextField();
        Producto = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldProdNom = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButtonAddProd = new javax.swing.JButton();
        jTextFieldProdMarca = new javax.swing.JTextField();
        jComboBoxProdCat = new javax.swing.JComboBox<>();
        jComboBoxProdProv = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        jSpinnerPrecioProd = new javax.swing.JSpinner();
        jSpinnerIDProd = new javax.swing.JSpinner();
        Actualizar = new javax.swing.JTabbedPane();
        Categoria6 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jTextFieldActCatNom = new javax.swing.JTextField();
        jButtonActCat = new javax.swing.JButton();
        jComboBoxActCat = new javax.swing.JComboBox<>();
        jTextFieldActCatFecha = new javax.swing.JTextField();
        Proveedor1 = new javax.swing.JPanel();
        Categoria5 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jTextFieldActProvNom = new javax.swing.JTextField();
        jButtonActProv = new javax.swing.JButton();
        jTextFieldActProvTel = new javax.swing.JTextField();
        jComboBoxActProv = new javax.swing.JComboBox<>();
        Producto1 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTextFieldActProdNom = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jButtonActProd = new javax.swing.JButton();
        jTextFieldActProdPrec = new javax.swing.JTextField();
        jTextFieldActProdMarc = new javax.swing.JTextField();
        jComboBoxActProdCat = new javax.swing.JComboBox<>();
        jComboBoxActProdProv = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        jComboBoxActProd = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        jRadioButtonMenuItem2.setSelected(true);
        jRadioButtonMenuItem2.setText("jRadioButtonMenuItem2");

        jRadioButtonMenuItem3.setSelected(true);
        jRadioButtonMenuItem3.setText("jRadioButtonMenuItem3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TRABAJO FINAL-COROPA");
        jLabel1.setToolTipText("TRABAJO FINAL-COROPA");
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 0, 102)));

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 51, 0)));
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        Consultar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ConsultarFocusGained(evt);
            }
        });
        Consultar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ConsultarMouseClicked(evt);
            }
        });

        jTableConsCat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(jTableConsCat);

        jButton1.setText("Consultar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Categoria2Layout = new javax.swing.GroupLayout(Categoria2);
        Categoria2.setLayout(Categoria2Layout);
        Categoria2Layout.setHorizontalGroup(
            Categoria2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Categoria2Layout.createSequentialGroup()
                .addGroup(Categoria2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Categoria2Layout.createSequentialGroup()
                        .addGap(289, 289, 289)
                        .addComponent(jButton1))
                    .addGroup(Categoria2Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(182, Short.MAX_VALUE))
        );
        Categoria2Layout.setVerticalGroup(
            Categoria2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Categoria2Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(65, 65, 65))
        );

        Consultar.addTab("CATEGORIA", Categoria2);

        jTableConsProv.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(jTableConsProv);

        jButton2.setText("Consultar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Proveedor2Layout = new javax.swing.GroupLayout(Proveedor2);
        Proveedor2.setLayout(Proveedor2Layout);
        Proveedor2Layout.setHorizontalGroup(
            Proveedor2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Proveedor2Layout.createSequentialGroup()
                .addGroup(Proveedor2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Proveedor2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Proveedor2Layout.createSequentialGroup()
                        .addGap(261, 261, 261)
                        .addComponent(jButton2)))
                .addContainerGap(198, Short.MAX_VALUE))
        );
        Proveedor2Layout.setVerticalGroup(
            Proveedor2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Proveedor2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jButton2)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        Consultar.addTab("PROVEEDOR", Proveedor2);

        jTableConsulProd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTableConsulProd);

        jLabel2.setText("Seleccione consulta");

        jComboBoxProd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxProdActionPerformed(evt);
            }
        });

        jButtonConsulProd.setText("Consultar productos");
        jButtonConsulProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsulProdActionPerformed(evt);
            }
        });

        jComboBoxFilCat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jToggleButtonCatCon.setText("Filtrar");
        jToggleButtonCatCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonCatConActionPerformed(evt);
            }
        });

        jButtonFilt.setText("Filtrar");
        jButtonFilt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFiltActionPerformed(evt);
            }
        });

        jCheckBoxProv.setText("Proveedor");
        jCheckBoxProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxProvActionPerformed(evt);
            }
        });

        jCheckBoxCat.setText("Categoria");
        jCheckBoxCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxCatActionPerformed(evt);
            }
        });

        jComboBoxFilProv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout Producto2Layout = new javax.swing.GroupLayout(Producto2);
        Producto2.setLayout(Producto2Layout);
        Producto2Layout.setHorizontalGroup(
            Producto2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Producto2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Producto2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Producto2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(Producto2Layout.createSequentialGroup()
                        .addComponent(jCheckBoxProv)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addComponent(jCheckBoxCat))
                    .addComponent(jComboBoxProd, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxFilCat, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxFilProv, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButtonCatCon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonConsulProd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonFilt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        Producto2Layout.setVerticalGroup(
            Producto2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Producto2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 47, Short.MAX_VALUE))
            .addGroup(Producto2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToggleButtonCatCon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBoxProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(Producto2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxProv)
                    .addComponent(jCheckBoxCat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBoxFilCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxFilProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonConsulProd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonFilt)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Consultar.addTab("PRODUCTO", Producto2);

        jTabbedPane1.addTab("CONSULTAR", Consultar);

        Borrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BorrarMouseClicked(evt);
            }
        });

        jLabel30.setText("Eliminar categoria");

        jComboBoxDelCat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButtonDelCat.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButtonDelCat.setText("Eliminar categoria");
        jButtonDelCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelCatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Categoria4Layout = new javax.swing.GroupLayout(Categoria4);
        Categoria4.setLayout(Categoria4Layout);
        Categoria4Layout.setHorizontalGroup(
            Categoria4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Categoria4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jComboBoxDelCat, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 278, Short.MAX_VALUE))
            .addGroup(Categoria4Layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(jButtonDelCat, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Categoria4Layout.setVerticalGroup(
            Categoria4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Categoria4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(Categoria4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxDelCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(jButtonDelCat, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(196, Short.MAX_VALUE))
        );

        Borrar.addTab("CATEGORIA", Categoria4);

        jLabel31.setText("Eliminar proveedor");

        jComboBoxDelProv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButtonDelProv.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButtonDelProv.setText("Eliminar proveedor");
        jButtonDelProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelProvActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Proveedor4Layout = new javax.swing.GroupLayout(Proveedor4);
        Proveedor4.setLayout(Proveedor4Layout);
        Proveedor4Layout.setHorizontalGroup(
            Proveedor4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Proveedor4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jComboBoxDelProv, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 278, Short.MAX_VALUE))
            .addGroup(Proveedor4Layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(jButtonDelProv, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Proveedor4Layout.setVerticalGroup(
            Proveedor4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Proveedor4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(Proveedor4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxDelProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(jButtonDelProv, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(196, Short.MAX_VALUE))
        );

        Borrar.addTab("PROVEEDOR", Proveedor4);

        jLabel29.setText("Eliminar producto");

        jComboBoxDelProd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButtonDelProd.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButtonDelProd.setText("Eliminar producto");
        jButtonDelProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelProdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Producto4Layout = new javax.swing.GroupLayout(Producto4);
        Producto4.setLayout(Producto4Layout);
        Producto4Layout.setHorizontalGroup(
            Producto4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Producto4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jComboBoxDelProd, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 278, Short.MAX_VALUE))
            .addGroup(Producto4Layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(jButtonDelProd, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Producto4Layout.setVerticalGroup(
            Producto4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Producto4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(Producto4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxDelProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(jButtonDelProd, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(196, Short.MAX_VALUE))
        );

        Borrar.addTab("PRODUCTO", Producto4);

        jTabbedPane1.addTab("BORRAR", Borrar);

        Crear.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 51, 0)));

        jLabel9.setText("ID");

        jLabel10.setText("Nombre");

        jLabel11.setText("Fecha");

        jSpinnerCatID.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        jButtonAddCat.setBackground(new java.awt.Color(255, 51, 0));
        jButtonAddCat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButtonAddCat.setText("Agregar categoria");
        jButtonAddCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddCatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CategoriaLayout = new javax.swing.GroupLayout(Categoria);
        Categoria.setLayout(CategoriaLayout);
        CategoriaLayout.setHorizontalGroup(
            CategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CategoriaLayout.createSequentialGroup()
                .addGroup(CategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(32, 32, 32)
                .addGroup(CategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldCatNom, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                    .addComponent(jSpinnerCatID, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextCatFecha))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(CategoriaLayout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(jButtonAddCat, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(421, Short.MAX_VALUE))
        );
        CategoriaLayout.setVerticalGroup(
            CategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CategoriaLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(CategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jSpinnerCatID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(CategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextFieldCatNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(CategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextCatFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(76, 76, 76)
                .addComponent(jButtonAddCat, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(129, Short.MAX_VALUE))
        );

        Crear.addTab("CATEGORIA", Categoria);

        jLabel12.setText("ID");

        jLabel13.setText("Nombre");

        jLabel14.setText("Telefono");

        jSpinnerProvID.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        jButtonAddProv.setBackground(new java.awt.Color(255, 51, 0));
        jButtonAddProv.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButtonAddProv.setText("Agregar proveedor");
        jButtonAddProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddProvActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Categoria1Layout = new javax.swing.GroupLayout(Categoria1);
        Categoria1.setLayout(Categoria1Layout);
        Categoria1Layout.setHorizontalGroup(
            Categoria1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Categoria1Layout.createSequentialGroup()
                .addGroup(Categoria1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addGap(32, 32, 32)
                .addGroup(Categoria1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldProvNom, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addComponent(jTextFieldProvTel, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addComponent(jSpinnerProvID, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(Categoria1Layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(jButtonAddProv, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(139, Short.MAX_VALUE))
        );
        Categoria1Layout.setVerticalGroup(
            Categoria1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Categoria1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(Categoria1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jSpinnerProvID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(Categoria1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTextFieldProvNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(Categoria1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextFieldProvTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addComponent(jButtonAddProv, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(129, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ProveedorLayout = new javax.swing.GroupLayout(Proveedor);
        Proveedor.setLayout(ProveedorLayout);
        ProveedorLayout.setHorizontalGroup(
            ProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProveedorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Categoria1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(272, Short.MAX_VALUE))
        );
        ProveedorLayout.setVerticalGroup(
            ProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProveedorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Categoria1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Crear.addTab("PROVEEDOR", Proveedor);

        Producto.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 0, 0)));
        Producto.setForeground(new java.awt.Color(255, 51, 51));

        jLabel4.setText("Nombre");

        jLabel5.setText("Precio");

        jTextFieldProdNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldProdNomActionPerformed(evt);
            }
        });

        jLabel6.setText("Marca");

        jLabel7.setText("Proveedor");

        jLabel8.setText("Categoria");

        jButtonAddProd.setBackground(new java.awt.Color(255, 51, 0));
        jButtonAddProd.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButtonAddProd.setText("Agregar producto");
        jButtonAddProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddProdActionPerformed(evt);
            }
        });

        jTextFieldProdMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldProdMarcaActionPerformed(evt);
            }
        });

        jComboBoxProdCat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxProdProv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel33.setText("ID");

        jSpinnerPrecioProd.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        jSpinnerIDProd.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        javax.swing.GroupLayout ProductoLayout = new javax.swing.GroupLayout(Producto);
        Producto.setLayout(ProductoLayout);
        ProductoLayout.setHorizontalGroup(
            ProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProductoLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(ProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ProductoLayout.createSequentialGroup()
                        .addGroup(ProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(ProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ProductoLayout.createSequentialGroup()
                                .addGroup(ProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jSpinnerIDProd, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                        .addComponent(jTextFieldProdMarca)
                                        .addComponent(jTextFieldProdNom)
                                        .addComponent(jSpinnerPrecioProd, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                                    .addComponent(jComboBoxProdCat, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonAddProd, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(114, 114, 114))
                            .addGroup(ProductoLayout.createSequentialGroup()
                                .addComponent(jComboBoxProdProv, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(ProductoLayout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addGap(0, 743, Short.MAX_VALUE))))
        );
        ProductoLayout.setVerticalGroup(
            ProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProductoLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(ProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jSpinnerIDProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldProdNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jSpinnerPrecioProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(ProductoLayout.createSequentialGroup()
                        .addGroup(ProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextFieldProdMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(ProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxProdCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addComponent(jButtonAddProd, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(ProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBoxProdProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(163, Short.MAX_VALUE))
        );

        Crear.addTab("PRODUCTO", Producto);

        jTabbedPane1.addTab("CREAR", Crear);

        Actualizar.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 51, 0)));
        Actualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ActualizarMouseClicked(evt);
            }
        });

        jLabel25.setText("Categoria");

        jLabel26.setText("Nombre");

        jLabel27.setText("Fecha");

        jButtonActCat.setBackground(new java.awt.Color(255, 51, 0));
        jButtonActCat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButtonActCat.setText("Actualizar categoria");
        jButtonActCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActCatActionPerformed(evt);
            }
        });

        jComboBoxActCat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxActCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxActCatActionPerformed(evt);
            }
        });

        jTextFieldActCatFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldActCatFechaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Categoria6Layout = new javax.swing.GroupLayout(Categoria6);
        Categoria6.setLayout(Categoria6Layout);
        Categoria6Layout.setHorizontalGroup(
            Categoria6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Categoria6Layout.createSequentialGroup()
                .addGroup(Categoria6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27))
                .addGap(32, 32, 32)
                .addGroup(Categoria6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldActCatNom, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addComponent(jComboBoxActCat, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldActCatFecha))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Categoria6Layout.createSequentialGroup()
                .addContainerGap(334, Short.MAX_VALUE)
                .addComponent(jButtonActCat, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(225, 225, 225))
        );
        Categoria6Layout.setVerticalGroup(
            Categoria6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Categoria6Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(Categoria6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jComboBoxActCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(Categoria6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jTextFieldActCatNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(Categoria6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jTextFieldActCatFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addComponent(jButtonActCat, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                .addGap(133, 133, 133))
        );

        Actualizar.addTab("CATEGORIA", Categoria6);

        jLabel17.setText("Proveedor");

        jLabel18.setText("Nombre");

        jLabel19.setText("Telefono");

        jButtonActProv.setBackground(new java.awt.Color(255, 51, 0));
        jButtonActProv.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButtonActProv.setText("Actualizar proveedor");
        jButtonActProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActProvActionPerformed(evt);
            }
        });

        jComboBoxActProv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxActProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxActProvActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Categoria5Layout = new javax.swing.GroupLayout(Categoria5);
        Categoria5.setLayout(Categoria5Layout);
        Categoria5Layout.setHorizontalGroup(
            Categoria5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Categoria5Layout.createSequentialGroup()
                .addGroup(Categoria5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19))
                .addGap(32, 32, 32)
                .addGroup(Categoria5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldActProvNom, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addComponent(jTextFieldActProvTel, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addComponent(jComboBoxActProv, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(Categoria5Layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(jButtonActProv, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(139, Short.MAX_VALUE))
        );
        Categoria5Layout.setVerticalGroup(
            Categoria5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Categoria5Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(Categoria5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jComboBoxActProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(Categoria5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTextFieldActProvNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(Categoria5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jTextFieldActProvTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addComponent(jButtonActProv, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                .addGap(129, 129, 129))
        );

        javax.swing.GroupLayout Proveedor1Layout = new javax.swing.GroupLayout(Proveedor1);
        Proveedor1.setLayout(Proveedor1Layout);
        Proveedor1Layout.setHorizontalGroup(
            Proveedor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Proveedor1Layout.createSequentialGroup()
                .addComponent(Categoria5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 282, Short.MAX_VALUE))
        );
        Proveedor1Layout.setVerticalGroup(
            Proveedor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Proveedor1Layout.createSequentialGroup()
                .addComponent(Categoria5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        Actualizar.addTab("PROVEEDOR", Proveedor1);

        Producto1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 0, 0)));
        Producto1.setForeground(new java.awt.Color(255, 51, 51));

        jLabel20.setText("Nombre");

        jLabel21.setText("Precio");

        jTextFieldActProdNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldActProdNomActionPerformed(evt);
            }
        });

        jLabel22.setText("Marca");

        jLabel23.setText("Proveedor");

        jLabel24.setText("Categoria");

        jButtonActProd.setBackground(new java.awt.Color(255, 51, 0));
        jButtonActProd.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButtonActProd.setText("Actualizar producto");
        jButtonActProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActProdActionPerformed(evt);
            }
        });

        jTextFieldActProdPrec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldActProdPrecActionPerformed(evt);
            }
        });

        jTextFieldActProdMarc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldActProdMarcActionPerformed(evt);
            }
        });

        jComboBoxActProdCat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxActProdProv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel28.setText("Producto");

        jComboBoxActProd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout Producto1Layout = new javax.swing.GroupLayout(Producto1);
        Producto1.setLayout(Producto1Layout);
        Producto1Layout.setHorizontalGroup(
            Producto1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Producto1Layout.createSequentialGroup()
                .addGroup(Producto1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(Producto1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBoxActProd, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldActProdNom)
                    .addComponent(jComboBoxActProdProv, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxActProdCat, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldActProdMarc)
                    .addComponent(jTextFieldActProdPrec))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Producto1Layout.createSequentialGroup()
                .addGap(0, 356, Short.MAX_VALUE)
                .addComponent(jButtonActProd, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(201, 201, 201))
        );
        Producto1Layout.setVerticalGroup(
            Producto1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Producto1Layout.createSequentialGroup()
                .addGroup(Producto1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jComboBoxActProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(Producto1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jTextFieldActProdNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(Producto1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jTextFieldActProdPrec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(Producto1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jTextFieldActProdMarc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(Producto1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jComboBoxActProdCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(Producto1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jComboBoxActProdProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonActProd, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                .addContainerGap())
        );

        Actualizar.addTab("PRODUCTO", Producto1);

        jTabbedPane1.addTab("ACTUALIZAR", Actualizar);

        jLabel3.setText("By Jhonatan C.");

        jLabel15.setText("Daniel R.");

        jLabel16.setText("Fabian B.");

        jButton3.setText("Actualizar listas/id");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel32.setText("Grupo 13");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel32)
                .addGap(67, 67, 67)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jButton3)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel32)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15)
                    .addComponent(jLabel3))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void ActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ActualizarMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_ActualizarMouseClicked

    private void jTextFieldActCatFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldActCatFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldActCatFechaActionPerformed

    private void jComboBoxActCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxActCatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxActCatActionPerformed

    private void jButtonActCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActCatActionPerformed
        // TODO add your handling code here:
        /************************TRAER ID CATEGORIA ELEGIDO***********************/
        String CatNombre = jComboBoxActCat.getItemAt(jComboBoxActCat.getSelectedIndex());
        int Cat_id =consulCatID(CatNombre);
        //System.out.println(prov_id);jSpinnerActCatFecha jTextFieldActCatNom
        /************************FIN TRAER ID CATEGORIA ELEGIDO***********************/
        String Cat_Nombre = jTextFieldActCatNom.getText();
        String Cat_Fecha = jTextFieldActCatFecha.getText();

        try{
            Connection con= getConexion();
            String sql= "UPDATE categorias SET nombre= ?, fecha_creacion=? where cat_id=?";
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setString(1, Cat_Nombre);
            ps.setString(2, Cat_Fecha);
            ps.setInt(3, Cat_id);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Se actualizo la categoria " + jComboBoxActCat.getItemAt(jComboBoxActCat.getSelectedIndex()));
            ListCat();            
            jTextFieldActCatNom.setText("");
            jTextFieldActCatFecha.setText("");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VistaFinal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VistaFinal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButtonActCatActionPerformed

    private void jTextFieldActProdMarcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldActProdMarcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldActProdMarcActionPerformed

    private void jTextFieldActProdPrecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldActProdPrecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldActProdPrecActionPerformed

    private void jButtonActProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActProdActionPerformed
        // TODO add your handling code here:
        /************************TRAER ID PRODUCTO ELEGIDO***********************/
        String ProdNombre = jComboBoxActProd.getItemAt(jComboBoxActProd.getSelectedIndex());
        int prod_id =consulProdID(ProdNombre);
        //System.out.println(prov_id);
        /************************FIN TRAER ID PRODUCTO ELEGIDO***********************/

        /************************TRAER ID PROVEEDOR ELEGIDO***********************/
        String ProvNombre = jComboBoxActProdProv.getItemAt(jComboBoxActProdProv.getSelectedIndex());
        int prov_id =consulProvID(ProvNombre);
        //System.out.println(prov_id);
        /************************FIN TRAER ID PROVEEDOR ELEGIDO***********************/

        /************************TRAER ID CATEGORIA ELEGIDO***********************/
        String CatNombre = jComboBoxActProdCat.getItemAt(jComboBoxActProdCat.getSelectedIndex());
        int Cat_id =consulCatID(CatNombre);
        //System.out.println(prov_id);
        /************************FIN TRAER ID CATEGORIA ELEGIDO***********************/
        String pro_name = jTextFieldActProdNom.getText();
        String pro_precio = jTextFieldActProdPrec.getText();
        String pro_marca = jTextFieldActProdMarc.getText();
        int pro_precioi = Integer.parseInt(pro_precio);
        //System.out.println(pro_precioi);
        try{
            Connection con= getConexion();
            String sql= "UPDATE productos SET pro_name= ?, pro_precio=?, pro_marca=?, pro_categoria=?,pro_proveedor=? where pro_id=?";
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setString(1, pro_name);
            ps.setInt(2, pro_precioi);
            ps.setString(3, pro_marca);
            ps.setInt(4, Cat_id);
            ps.setInt(5, prov_id);
            ps.setInt(6, prod_id);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Se actualizo el producto " + jComboBoxActProdCat.getItemAt(jComboBoxActProdCat.getSelectedIndex()));
            ListProd();
            jTextFieldActProdNom.setText("");
            jTextFieldActProdPrec.setText("");
            jTextFieldActProdMarc.setText("");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VistaFinal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VistaFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonActProdActionPerformed

    private void jTextFieldActProdNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldActProdNomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldActProdNomActionPerformed

    private void jComboBoxActProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxActProvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxActProvActionPerformed

    private void jButtonActProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActProvActionPerformed

        // TODO add your handling code here:
        /************************TRAER ID PROVEEDOR ELEGIDO***********************/
        String ProvNombre = jComboBoxActProv.getItemAt(jComboBoxActProv.getSelectedIndex());
        int prov_id =consulProvID(ProvNombre);
        String provNombre = jTextFieldActProvNom.getText();
        String provTelefono = jTextFieldActProvTel.getText();
        //System.out.println(prov_id);
        /************************FIN TRAER ID PROVEEDOR ELEGIDO***********************/
        try{
            Connection con= getConexion();
            String sql= "UPDATE proveedor SET prov_nombre= ?, prov_telefono=? where prov_id=?";
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setString(1, provNombre);
            ps.setString(2, provTelefono);
            ps.setInt(3, prov_id);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Se actualizo el proveedor " + jComboBoxActProv.getItemAt(jComboBoxActProv.getSelectedIndex()));
            ListProv();
            jTextFieldActProvTel.setText("");
            jTextFieldActProvNom.setText("");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VistaFinal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VistaFinal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButtonActProvActionPerformed

    private void BorrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BorrarMouseClicked
        // TODO add your handling code here:
        

    }//GEN-LAST:event_BorrarMouseClicked

    private void ConsultarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ConsultarMouseClicked
        // OCULTAR FILTRAR
    }//GEN-LAST:event_ConsultarMouseClicked

    private void ConsultarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ConsultarFocusGained

    }//GEN-LAST:event_ConsultarFocusGained

    private void jButtonFiltActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFiltActionPerformed
        /****************BOTON FILTRAR PRODUCTOS**********************************/

        // LLENADO ENCABEZADOS
        String[]encabezados= {"ID","Nombre","Precio","Marca","Categoria","Proveedor"};
        String[] datos= new String[encabezados.length];
        tableProdTodoModel = new DefaultTableModel(null, encabezados);
        //LLENADO TABLA:
        jTableConsulProd.setModel(tableProdTodoModel);
        if (jCheckBoxProv.isSelected() == true){
        String ProvNombre = jComboBoxFilProv.getItemAt(jComboBoxFilProv.getSelectedIndex());
        int Prov_id =consulProvID(ProvNombre);
        //QUERY
        Connection con;
        Statement st;
        ResultSet rs;
        String sql="select pro_id,pro_name,pro_precio,pro_marca,nombre,prov_nombre from productos t1 left join "
        + "categorias t2 on t1.pro_categoria = t2.cat_id left join  proveedor t3 on t1.pro_proveedor = "
        + "t3.prov_id where t3.prov_id ="+Prov_id;
        try{
            con= getConexion();
            st= con.createStatement();
            rs= st.executeQuery(sql);
            while(rs.next()){
                /****************LLENAR FILTRAR PROD**********************************/
                datos[0]= rs.getInt("pro_id")+"";
                datos[1]= rs.getString("pro_name");
                datos[2]= rs.getInt("pro_precio")+"";
                datos[3]= rs.getString("pro_marca");
                datos[4]= rs.getString("nombre");
                datos[5]= rs.getString("prov_nombre");
                tableProdTodoModel.addRow(datos);
                /****************FIN LLENAR FILTRAR PROD**********************************/
            }
            jTableConsulProd.setModel(tableProdTodoModel);

        }catch(SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VistaFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
        /****************FIN BOTON FILTRAR PRODUCTOS**********************************/
        }else if(jCheckBoxCat.isSelected() == true){
        String CatNombre = jComboBoxFilCat.getItemAt(jComboBoxFilCat.getSelectedIndex());
        int cat_id =consulCatID(CatNombre);
        //QUERY
        Connection con;
        Statement st;
        ResultSet rs;
        String sql="select pro_id,pro_name,pro_precio,pro_marca,nombre,prov_nombre from productos t1 left join "
        + "categorias t2 on t1.pro_categoria = t2.cat_id left join  proveedor t3 on t1.pro_proveedor = "
        + "t3.prov_id where t2.cat_id ="+cat_id;
        try{
            con= getConexion();
            st= con.createStatement();
            rs= st.executeQuery(sql);
            while(rs.next()){
                /****************LLENAR FILTRAR PROD**********************************/
                datos[0]= rs.getInt("pro_id")+"";
                datos[1]= rs.getString("pro_name");
                datos[2]= rs.getInt("pro_precio")+"";
                datos[3]= rs.getString("pro_marca");
                datos[4]= rs.getString("nombre");
                datos[5]= rs.getString("prov_nombre");
                tableProdTodoModel.addRow(datos);
                /****************FIN LLENAR FILTRAR PROD**********************************/
            }
            jTableConsulProd.setModel(tableProdTodoModel);

        }catch(SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VistaFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
        /****************FIN BOTON FILTRAR PRODUCTOS CAT**********************************/            
        }
    }//GEN-LAST:event_jButtonFiltActionPerformed

    private void jToggleButtonCatConActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonCatConActionPerformed
        // TODO add your handling code here:
        if(jToggleButtonCatCon.isSelected()== true){
            //CATEGORIAS
            jToggleButtonCatCon.setText("Consultar");
            //LISTAS
            //jComboBoxFilCat.setVisible(true);
            jComboBoxProd.setVisible(false);
            //BOTONES:
            jButtonFilt.setVisible(true);
            jButtonConsulProd.setVisible(false);
            jLabel2.setText("Seleccione la categoria:");
            jCheckBoxProv.setVisible(true);
            jCheckBoxCat.setVisible(true);
            
            
            jCheckBoxProv.setSelected(false);
            

        }else{
            //CONSULTAS
            jToggleButtonCatCon.setText("Filtrar");
            ///Listas:
            jComboBoxFilCat.setVisible(false);
            jComboBoxProd.setVisible(true);
            jComboBoxFilProv.setVisible(false);
            jCheckBoxProv.setVisible(false);
            jCheckBoxCat.setVisible(false);
            //BOTONES:
            jButtonFilt.setVisible(false);
            jButtonConsulProd.setVisible(true);
            jLabel2.setText("Seleccione la consulta:");

        }

    }//GEN-LAST:event_jToggleButtonCatConActionPerformed

    private void jButtonConsulProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsulProdActionPerformed
        /****************BOTON CONSULTAS PRODUCTOS**********************************/

        // LLENADO ENCABEZADOS
        String[]encabezados= {"ID","Nombre","Precio","Marca","Categoria","Proveedor"};
        String[] datos= new String[encabezados.length];
        tableProdTodoModel = new DefaultTableModel(null, encabezados);
        //LLENADO TABLA:
        jTableConsulProd.setModel(tableProdTodoModel);
        //QUERY
        Connection con;
        Statement st;
        ResultSet rs;
        String sql="select pro_id,pro_name,pro_precio,pro_marca,nombre,prov_nombre from productos t1 left join categorias t2 on t1.pro_categoria = t2.cat_id left join  proveedor t3 on t1.pro_proveedor = t3.prov_id";

        int consOption = jComboBoxProd.getSelectedIndex();
        /****************OPCION CONSULTAS PRODUCTOS**********************************/
        switch (consOption){
            case 0:
            sql=sql+"";
            //System.out.println("TODO");
            break;
            case 1:
            sql=sql+" order by nombre asc";
            //System.out.println("Orden categoria");
            break;
            case 2:
            sql=sql+" order by prov_nombre ASC";
            //System.out.println("Proveedor");
            break;
            case 3:
            sql=sql+" order by pro_precio ASC LIMIT 3";
            //System.out.println("3 Mas economicos");
            break;
            /****************FIN OPCION CONSULTAS PRODUCTOS**********************************/
        }
        try{
            con= getConexion();
            st= con.createStatement();
            rs= st.executeQuery(sql);
            while(rs.next()){
                /****************LLENAR DATOS CONS PROD**********************************/
                datos[0]= rs.getInt("pro_id")+"";
                datos[1]= rs.getString("pro_name");
                datos[2]= rs.getInt("pro_precio")+"";
                datos[3]= rs.getString("pro_marca");
                datos[4]= rs.getString("nombre");
                datos[5]= rs.getString("prov_nombre");
                tableProdTodoModel.addRow(datos);
                /****************FIN LLENAR DATOS CONS PROD**********************************/
            }
            jTableConsulProd.setModel(tableProdTodoModel);

        }catch(SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VistaFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
        /****************FIN BOTON CONSULTAS PRODUCTOS**********************************/
    }//GEN-LAST:event_jButtonConsulProdActionPerformed

    private void jComboBoxProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxProdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxProdActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        /****************BOTON CONSULTAS PROVEEDOR**********************************/
        String[]encabezados= {"ID","Proveedor","Telefono"};
        String[] datos= new String[encabezados.length];
        tableProvTodoModel = new DefaultTableModel(null, encabezados);
        jTableConsProv.setModel(tableProvTodoModel);

        //LLENADO TABLA:
        jTableConsulProd.setModel(tableProdTodoModel);
        //QUERY
        Connection con;
        Statement st;
        ResultSet rs;
        String sql="select * from proveedor";

        try{
            con= getConexion();
            st= con.createStatement();
            rs= st.executeQuery(sql);
            while(rs.next()){
                /****************LLENAR DATOS CATEGORIA**********************************/
                datos[0]= rs.getInt("prov_id")+"";
                datos[1]= rs.getString("prov_nombre");
                datos[2]= rs.getString("prov_telefono");
                tableProvTodoModel.addRow(datos);
            }
            jTableConsProv.setModel(tableProvTodoModel);
            /**************** FIN LLENAR DATOS CATEGORIA**********************************/

        }catch(SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VistaFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
        /****************FIN BOTON CONSULTAS PROVEEDOR**********************************/
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        /****************BOTON CONSULTAS CATEGORIAS**********************************/

        String[]encabezados= {"ID","Nombre","Fecha"};
        String[] datos= new String[encabezados.length];
        tableCatTodoModel = new DefaultTableModel(null, encabezados);
        jTableConsCat.setModel(tableCatTodoModel);

        //LLENADO TABLA:
        jTableConsCat.setModel(tableCatTodoModel);
        //QUERY
        Connection con;
        Statement st;
        ResultSet rs;
        String sql="select * from categorias";

        try{
            con= getConexion();
            st= con.createStatement();
            rs= st.executeQuery(sql);
            while(rs.next()){
                /****************LLENAR DATOS CATEGORIA**********************************/
                datos[0]= rs.getInt("cat_id")+"";
                datos[1]= rs.getString("nombre");
                datos[2]= rs.getString("fecha_creacion");
                tableCatTodoModel.addRow(datos);
            }
            jTableConsCat.setModel(tableCatTodoModel);
            /****************LLENAR DATOS CATEGORIA**********************************/
        }catch(SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VistaFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
        /****************FIN BOTON CONSULTAS CATEGORIAS**********************************/
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonAddCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddCatActionPerformed
        int id= Integer.parseInt(jSpinnerCatID.getValue().toString());
        String nombre= jTextFieldCatNom.getText();
        String fecha = jTextCatFecha.getText();
        try{
            Connection con = getConexion();
            String sql = "INSERT INTO categorias VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2,nombre);
            ps.setString(3,fecha);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Se creo la Categoria " + nombre);

        }catch(Exception e){
            System.out.println(e);
        }
        jSpinnerCatID.setValue(id+1);
        jTextFieldCatNom.setText("");
        jTextCatFecha.setText("");
        ListCat();
        GetAddCatId();
    }//GEN-LAST:event_jButtonAddCatActionPerformed

    private void jTextFieldProdNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldProdNomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldProdNomActionPerformed

    private void jButtonAddProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddProdActionPerformed
        try{
            int id = Integer.parseInt(jSpinnerIDProd.getValue().toString());
            String nombre = jTextFieldProdNom.getText();
            int precio = Integer.parseInt(jSpinnerPrecioProd.getValue().toString());
            String marca = jTextFieldProdMarca.getText();
            String categoria = jComboBoxProdCat.getSelectedItem().toString();
            String proveedor = jComboBoxProdProv.getSelectedItem().toString();
            Consultas cons = new Consultas();
            ArrayList<Categorias>listaCategorias = new ArrayList<>();
            listaCategorias = cons.getCategorias();
            ArrayList<Proveedor> listaProv = new ArrayList<>();
            listaProv = cons.getProveedores();

            int categoriaID = consulCatID(categoria);
            int proveedorID = consulProvID(proveedor);

            try{
                Connection con = getConexion();
                String sql = "INSERT INTO productos VALUES(?,?,?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                ps.setString(2,nombre);
                ps.setInt(3,precio);
                ps.setString(4,marca);
                //ps.setInt(5,cantidad);
                ps.setInt(5,categoriaID);
                ps.setInt(6,proveedorID);
                ps.execute();
                JOptionPane.showMessageDialog(null, "Se creo el producto " + nombre );

            }catch(Exception e){
                System.out.println(e);
            }
            jSpinnerIDProd.setValue(id+1);
            jTextFieldProdNom.setText("");
            jSpinnerPrecioProd.setValue(1);
            jTextFieldProdMarca.setText("");
            
            ListProd();
            GetAddProdId();

        }catch(ClassNotFoundException ex){
            Logger.getLogger(VistaFinal.class.getName()).log(Level.SEVERE, null,ex);
        } catch (SQLException ex) {
            Logger.getLogger(VistaFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonAddProdActionPerformed

    private void jTextFieldProdMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldProdMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldProdMarcaActionPerformed

    private void jButtonAddProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddProvActionPerformed
        int id= Integer.parseInt(jSpinnerProvID.getValue().toString());
        String nombre= jTextFieldProvNom.getText();
        String telefono = jTextFieldProvTel.getText();
        try{
            Connection con = getConexion();
            String sql = "INSERT INTO proveedor VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2,nombre);
            ps.setString(3,telefono);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Se creo el proveedor ");

        }catch(Exception e){
            System.out.println(e);
        }
        jSpinnerProvID.setValue(id+1);
        jTextFieldProvNom.setText("");
        jTextFieldProvTel.setText("");
        ListProv();
        GetAddProvId();
    }//GEN-LAST:event_jButtonAddProvActionPerformed

    private void jButtonDelCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelCatActionPerformed
        // TODO add your handling code here:
        int CatId = consulCatID(jComboBoxDelCat.getSelectedItem().toString());
                try{
            Connection con= getConexion();
            String sql= "DELETE from categorias where cat_id=?";
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setInt(1,CatId);
            
            ps.execute();
            JOptionPane.showMessageDialog(null, "Se elimino la Categoria " + jComboBoxDelCat.getSelectedItem().toString());
            ListCat();
            

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VistaFinal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VistaFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonDelCatActionPerformed

    private void jButtonDelProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelProvActionPerformed
        // TODO add your handling code here:
        int ProvId = consulProvID(jComboBoxDelProv.getSelectedItem().toString());
                try{
            Connection con= getConexion();
            String sql= "DELETE from proveedor where prov_id=?";
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setInt(1, ProvId);
            
            ps.execute();
            JOptionPane.showMessageDialog(null, "Se elimino el proveedor " + jComboBoxDelProv.getSelectedItem().toString());
            ListProv();
            

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VistaFinal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VistaFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonDelProvActionPerformed

    private void jButtonDelProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelProdActionPerformed
        // TODO add your handling code here:
             int ProId = consulProdID(jComboBoxDelProd.getSelectedItem().toString());
                try{
            Connection con= getConexion();
            String sql= "DELETE from productos where Pro_id=?";
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setInt(1,ProId);
            
            ps.execute();
            JOptionPane.showMessageDialog(null, "Se elimino el producto " + jComboBoxDelProd.getSelectedItem().toString());
            ListProd();
            

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VistaFinal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VistaFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonDelProdActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        ConsulProd();// LLENAR LISTA OPCIONES CONSULTA
        ListCat();// LLENAR TODOS LOS LIST CATEGORIA
        ListProv();// LLENAR TODOS LOS LIST PROVEEDOR
        ListProd();// LLENAR TODOS LOS LIST PRODUCTOS
        GetAddCatId();//LLENAR ID CREAR CAT
        GetAddProvId();//LLENAR ID CREAR PROVEEDOR
        GetAddProdId();//LLENAR ID CREAR PRODUCTO
        JOptionPane.showMessageDialog(null, "Se actualizaron los listados \n y los ID(creacion) ");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jCheckBoxProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxProvActionPerformed
        // TODO add your handling code here:
        if (jCheckBoxProv.isSelected() == true){
            jButtonFilt.setEnabled(true);
            jCheckBoxCat.setSelected(false);
            jComboBoxFilProv.setVisible(true);
            jComboBoxFilCat.setVisible(false);
            
        }else{
            jCheckBoxCat.setSelected(false);
            jComboBoxFilProv.setVisible(false);
            jButtonFilt.setEnabled(false);
            
        }
    }//GEN-LAST:event_jCheckBoxProvActionPerformed

    private void jCheckBoxCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxCatActionPerformed
        // TODO add your handling code here:
        if (jCheckBoxCat.isSelected() == true){
            jButtonFilt.setEnabled(true);
            jCheckBoxProv.setSelected(false);
            jComboBoxFilCat.setVisible(true);
            jComboBoxFilProv.setVisible(false);
            
        }else{
            jCheckBoxProv.setSelected(false);
            jComboBoxFilCat.setVisible(false);
            jButtonFilt.setEnabled(false);
        }        
    }//GEN-LAST:event_jCheckBoxCatActionPerformed

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
            java.util.logging.Logger.getLogger(VistaFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaFinal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane Actualizar;
    private javax.swing.JTabbedPane Borrar;
    private javax.swing.JPanel Categoria;
    private javax.swing.JPanel Categoria1;
    private javax.swing.JPanel Categoria2;
    private javax.swing.JPanel Categoria4;
    private javax.swing.JPanel Categoria5;
    private javax.swing.JPanel Categoria6;
    private javax.swing.JTabbedPane Consultar;
    private javax.swing.JTabbedPane Crear;
    private javax.swing.JPanel Producto;
    private javax.swing.JPanel Producto1;
    private javax.swing.JPanel Producto2;
    private javax.swing.JPanel Producto4;
    private javax.swing.JPanel Proveedor;
    private javax.swing.JPanel Proveedor1;
    private javax.swing.JPanel Proveedor2;
    private javax.swing.JPanel Proveedor4;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonActCat;
    private javax.swing.JButton jButtonActProd;
    private javax.swing.JButton jButtonActProv;
    private javax.swing.JButton jButtonAddCat;
    private javax.swing.JButton jButtonAddProd;
    private javax.swing.JButton jButtonAddProv;
    private javax.swing.JButton jButtonConsulProd;
    private javax.swing.JButton jButtonDelCat;
    private javax.swing.JButton jButtonDelProd;
    private javax.swing.JButton jButtonDelProv;
    private javax.swing.JButton jButtonFilt;
    private javax.swing.JCheckBox jCheckBoxCat;
    private javax.swing.JCheckBox jCheckBoxProv;
    private javax.swing.JComboBox<String> jComboBoxActCat;
    private javax.swing.JComboBox<String> jComboBoxActProd;
    private javax.swing.JComboBox<String> jComboBoxActProdCat;
    private javax.swing.JComboBox<String> jComboBoxActProdProv;
    private javax.swing.JComboBox<String> jComboBoxActProv;
    private javax.swing.JComboBox<String> jComboBoxDelCat;
    private javax.swing.JComboBox<String> jComboBoxDelProd;
    private javax.swing.JComboBox<String> jComboBoxDelProv;
    private javax.swing.JComboBox<String> jComboBoxFilCat;
    private javax.swing.JComboBox<String> jComboBoxFilProv;
    private javax.swing.JComboBox<String> jComboBoxProd;
    private javax.swing.JComboBox<String> jComboBoxProdCat;
    private javax.swing.JComboBox<String> jComboBoxProdProv;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem2;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSpinner jSpinnerCatID;
    private javax.swing.JSpinner jSpinnerIDProd;
    private javax.swing.JSpinner jSpinnerPrecioProd;
    private javax.swing.JSpinner jSpinnerProvID;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableConsCat;
    private javax.swing.JTable jTableConsProv;
    private javax.swing.JTable jTableConsulProd;
    private javax.swing.JTextField jTextCatFecha;
    private javax.swing.JTextField jTextFieldActCatFecha;
    private javax.swing.JTextField jTextFieldActCatNom;
    private javax.swing.JTextField jTextFieldActProdMarc;
    private javax.swing.JTextField jTextFieldActProdNom;
    private javax.swing.JTextField jTextFieldActProdPrec;
    private javax.swing.JTextField jTextFieldActProvNom;
    private javax.swing.JTextField jTextFieldActProvTel;
    private javax.swing.JTextField jTextFieldCatNom;
    private javax.swing.JTextField jTextFieldProdMarca;
    private javax.swing.JTextField jTextFieldProdNom;
    private javax.swing.JTextField jTextFieldProvNom;
    private javax.swing.JTextField jTextFieldProvTel;
    private javax.swing.JToggleButton jToggleButtonCatCon;
    // End of variables declaration//GEN-END:variables
}
