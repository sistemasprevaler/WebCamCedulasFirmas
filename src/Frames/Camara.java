package Frames;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import ds.desktop.notify.DesktopNotify;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;



public final class Camara extends javax.swing.JFrame {
    //DAVID ALVAREZ
    public static Webcam webc;
    public Dimension dd;
    boolean ejecutando = true;
    boolean encendida = true;
    private int x;
    private int y;
    int currentX, currentY, oldX, oldY;
    Graphics2D graphics2D;
    Image image;
    public static int cont = 0;
    public static int contador = 0;
    public static int numero_cedula = 0;
    public static int guardado = 0;
    int Caracteres = 0;
    int contadorpregunta = 0;
    
   
    public Camara() {
        initComponents();
        iniciar();
        apariencia();
        jButton1.setEnabled(false);
        jButton8.setVisible(false);
        
    }
    
    void llenarcombo(){
        combodocumento.removeAllItems();
        combodocumento.addItem("[Seleccione]");
        combodocumento.addItem("[Cedula]");
        combodocumento.addItem("[Carnet]");
    }

    void iniciarcam(){
        //Inicio de la Camara
        ejecutando = true;
        new Thread(){
           public void run(){
               try {
                   if(webc.isOpen()){
                     DesktopNotify.showDesktopMessage("La Camara Ya esta Inciada","Por Favor "
                         + "Verifique..", DesktopNotify.WARNING, 6000L);
                   }else{     
                    DesktopNotify.showDesktopMessage("Se Esta Iniciando la Camara"," "
                            + "Por Favor No Retire el Documento Hasta Terminar el Proceso", DesktopNotify.TIP, 5000L);   
                     webc.open();
                     jButton1.setEnabled(true);
                   }
               } catch (Exception e) {
                  DesktopNotify.showDesktopMessage("Camara no Encontrada","Por Favor Reconecte la Camara y Reinicie el Software",
                  DesktopNotify.INFORMATION, 6000L);
                  txtcedula.setText("");
                  txtcedula1.setText("");
                  txtcedula.setEditable(false);
                  txtcedula1.setEditable(false);
                  DefaultComboBoxModel modelo = new DefaultComboBoxModel();
                  combodocumento2.setModel(modelo);
                  combodocumento.setSelectedIndex(0);
                  //combodocumento2.setEnabled(false);
                  checkmenor.setEnabled(false);
                
               }
               iniciar_video();
           } 
        }.start();
    }
    
    void detenercam(){
        new Thread(){
           public void run(){
               webc.close();
               //combodocumento.setEnabled(true);
               contador = 0;
               colocarlogo();
               ejecutando = false;
           } 
        }.start();
    }
    
    
    
    void apariencia(){
        colocarlogo();
        combodocumento.requestFocus();
         this.setIconImage(new ImageIcon(getClass().getResource
        ("/Imagenes/logoprevaler.png")).getImage());
         txtcedula.setEditable(false);
    }
    
    void colocarlogo(){
                String ruta = "C:\\ClienteAGS\\Adempiere\\reports\\Firmas\\logonuevoMediano.png";
                
                ImageIcon pre = new ImageIcon(ruta);
                ImageIcon ver = new ImageIcon(pre.getImage().getScaledInstance(325, 88, 0));
                imagen.setIcon((ver));
    }
    
    
    
    public void guardarcedulatemp(){
        try {
            //int nuevo_ancho = 640;  DAVID ALVAREZ INACTIVO PARA BAJAR EL TAMAÑO
            //int nuevo_alto = 480;   DAVID ALVAREZ INACTIVO PARA BAJAR EL TAMAÑO
            
            int nuevo_ancho = 352;
            int nuevo_alto = 250;
            
            BufferedImage nueva_imagen = new BufferedImage
            (nuevo_ancho, nuevo_alto, BufferedImage.TYPE_INT_RGB);
            ByteArrayOutputStream buff = new ByteArrayOutputStream();
            ImageIO.write(Camara.webc.getImage(), "png", buff);
            byte[] bytes = buff.toByteArray();
            
            ByteArrayInputStream inbuff = new ByteArrayInputStream(bytes);
            BufferedImage imagen = ImageIO.read(inbuff);
            
            Graphics2D g = nueva_imagen.createGraphics();
            g.drawImage(imagen, 0, 0, nuevo_ancho, nuevo_alto,null);
            String ruta = "";
            String destino ="";
            String nombre = "";
            
            if(combodocumento.getSelectedIndex()==0){
             DesktopNotify.showDesktopMessage("Error de Selección","Por Favor"
                         + " verifique la selección del menu desplegable",
                            DesktopNotify.ERROR, 6000L);   
            }else{
            if(combodocumento2.getSelectedItem().equals("C-")){
                if(checkmenor.isSelected()){
                     nombre = "C"+txtcedula.getText()+"-"+txtcedula1.getText()+".png";
                }else{
               nombre = "C"+txtcedula.getText()+".png";
                }
            }
            else{
                if(checkmenor.isSelected()){
                nombre = txtcedula.getText()+"-"+txtcedula1.getText()+".png";
                }else{
            nombre = txtcedula.getText()+".png";
                }
            }
            File carpetatemp = new File("C:\\Temp");
            if(carpetatemp.exists()){
                carpetatemp.delete();
                carpetatemp.mkdir();
            }else{
                carpetatemp.mkdir();
            }
            
            ruta = "C:\\Temp\\"+nombre;
            String ruta2 = "C:\\Temp\\";
            File archivo = new File(ruta);
            File carpetatem = new File(ruta2);
            ImageIO.write(nueva_imagen, "png", archivo);
            
           /* int confirmar = JOptionPane.showConfirmDialog(null, "Se Guardo Con Exito ¿Desea Proceder a la Firma?");
            if(JOptionPane.OK_OPTION == confirmar){
                Firma1 ff= new Firma1();
                ff.setVisible(true); 
                detenercam();
                this.dispose();
            }*/
       // else{
            int confirmar2 = JOptionPane.showConfirmDialog(null, "¿Desea Guardar Otro Documento del Paciente?");
            if(JOptionPane.OK_OPTION == confirmar2 ){
                //combodocumento.setEnabled(false);
                detenercam();
                Camara cc = new Camara();
                this.dispose();
                cc.setVisible(true);
                cc.setLocationRelativeTo(null);
            }else{
                detenercam();
                ejecutando = false;
                movercedulas();
                DesktopNotify.showDesktopMessage("Proceso Completo","Se completo"
                         + " el proceso de captura de datos con exito",
                            DesktopNotify.SUCCESS, 6000L);
                //Camara cc = new Camara();  DAVID ALVAREZ INACTIVO
                this.dispose();
                //DAVID ALVAREZ INACTIVO PARA NO VOLVER A CARGAR EL JFrame de Cedulas
                //cc.setVisible(true);
                //cc.setLocationRelativeTo(null);
            }   
             
            //} 
        }
             
             
             
        } catch (Exception e) {
        }
    }
    
    public void movercedulas() throws IOException{
        String rutacarp = "C:\\Temp\\";
        File carpetatemp = new File(rutacarp);
        String[] ficheros = carpetatemp.list();
        
        if(ficheros==null){
            
        }else{
            for(int i=0;i<ficheros.length;i++){
                InputStream in = null;
                try {
                    String origen = "C:\\Temp\\"+ficheros[i];
                    
                    //String destino = "\\\\192.168.5.1\\Cedulas\\"+ficheros[i];  DAVID ALVAREZ CAMBIO ABAJO
                    String destino = "\\" + Principal.RutaCedulas +"\\"+ficheros[i];
                    
                    in = new FileInputStream(origen);
                    OutputStream out = new FileOutputStream(destino);
                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }in.close();
                    out.close();
                    File archivos = new File(origen);
                    if(archivos.exists()){
                        archivos.delete();
                    }
                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Camara.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        in.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Camara.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
            if(carpetatemp.exists()){
                carpetatemp.delete();
            }
            
        }
    }   
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupo_btn = new javax.swing.ButtonGroup();
        contenedor = new javax.swing.JPanel();
        contenedor1 = new javax.swing.JPanel();
        txtcedula = new javax.swing.JTextField();
        lblcedula = new javax.swing.JLabel();
        combodocumento = new javax.swing.JComboBox();
        combodocumento2 = new javax.swing.JComboBox();
        checkmenor = new javax.swing.JCheckBox();
        txtcedula1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        contenedor2 = new javax.swing.JPanel();
        imagen = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Captura de Datos PREVALER");
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(420, 495));
        setResizable(false);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        contenedor.setBackground(new java.awt.Color(255, 255, 255));
        contenedor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        contenedor1.setBackground(new java.awt.Color(255, 255, 255));
        contenedor1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 0), 1, true));

        txtcedula.setEditable(false);
        txtcedula.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        txtcedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcedulaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcedulaKeyTyped(evt);
            }
        });

        lblcedula.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        lblcedula.setForeground(new java.awt.Color(0, 51, 153));
        lblcedula.setText("Por Favor Seleccione:");

        combodocumento.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        combodocumento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "[Seleccione]", "Cedula", "Carnet" }));
        combodocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combodocumentoActionPerformed(evt);
            }
        });
        combodocumento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                combodocumentoKeyPressed(evt);
            }
        });

        combodocumento2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        combodocumento2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combodocumento2ActionPerformed(evt);
            }
        });

        checkmenor.setBackground(new java.awt.Color(255, 255, 255));
        checkmenor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        checkmenor.setText("Beneficiario sin Cedula");
        checkmenor.setEnabled(false);
        checkmenor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkmenorActionPerformed(evt);
            }
        });

        txtcedula1.setEditable(false);
        txtcedula1.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        txtcedula1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcedula1ActionPerformed(evt);
            }
        });
        txtcedula1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcedula1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcedula1KeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("-");

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Unavailable_24px.png"))); // NOI18N
        jButton9.setToolTipText("Cerrar");
        jButton9.setBorderPainted(false);
        jButton9.setContentAreaFilled(false);
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton9.setFocusPainted(false);
        jButton9.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Unavailable_22px.png"))); // NOI18N
        jButton9.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Unavailable_22px.png"))); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Minus_24px.png"))); // NOI18N
        jButton8.setToolTipText("Minimizar");
        jButton8.setBorderPainted(false);
        jButton8.setContentAreaFilled(false);
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton8.setFocusPainted(false);
        jButton8.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Minus_22px.png"))); // NOI18N
        jButton8.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Minus_22px.png"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        jLabel2.setText("Atajos:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        jLabel3.setText("V: Venezolana / E: Extranjera / C: Carnet");

        javax.swing.GroupLayout contenedor1Layout = new javax.swing.GroupLayout(contenedor1);
        contenedor1.setLayout(contenedor1Layout);
        contenedor1Layout.setHorizontalGroup(
            contenedor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenedor1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
            .addGroup(contenedor1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(contenedor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contenedor1Layout.createSequentialGroup()
                        .addComponent(lblcedula)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(contenedor1Layout.createSequentialGroup()
                        .addGroup(contenedor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(contenedor1Layout.createSequentialGroup()
                                .addComponent(combodocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(combodocumento2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(contenedor1Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addGroup(contenedor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(checkmenor)
                                    .addGroup(contenedor1Layout.createSequentialGroup()
                                        .addComponent(txtcedula, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(1, 1, 1)
                                        .addComponent(jLabel1)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtcedula1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(17, Short.MAX_VALUE))))
        );
        contenedor1Layout.setVerticalGroup(
            contenedor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contenedor1Layout.createSequentialGroup()
                .addGroup(contenedor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton8)
                    .addComponent(jButton9)
                    .addComponent(lblcedula, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(contenedor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combodocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combodocumento2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(checkmenor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(contenedor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(txtcedula, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtcedula1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(contenedor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addContainerGap())
        );

        contenedor.add(contenedor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 390, 210));

        contenedor2.setBackground(new java.awt.Color(255, 255, 255));
        contenedor2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 0), 1, true));

        imagen.setBackground(new java.awt.Color(255, 255, 255));
        imagen.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        imagen.setForeground(new java.awt.Color(255, 255, 255));
        imagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        imagen.setOpaque(true);

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Gerencia TI  (2021) -  PREVALER, C.A.");

        javax.swing.GroupLayout contenedor2Layout = new javax.swing.GroupLayout(contenedor2);
        contenedor2.setLayout(contenedor2Layout);
        contenedor2Layout.setHorizontalGroup(
            contenedor2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenedor2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(contenedor2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contenedor2Layout.createSequentialGroup()
                        .addGroup(contenedor2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contenedor2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        contenedor2Layout.setVerticalGroup(
            contenedor2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenedor2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        contenedor.add(contenedor2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, 290));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contenedor, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contenedor, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleDescription("");

        setBounds(0, 0, 411, 560);
    }// </editor-fold>//GEN-END:initComponents

    private void txtcedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcedulaKeyTyped
        char captecla = evt.getKeyChar();
        if(!Character.isDigit(evt.getKeyChar())) {

            getToolkit().beep();

            evt.consume();
        }
        
        if(txtcedula.getText().length()>=Caracteres){
            evt.consume();
        }

        
    }//GEN-LAST:event_txtcedulaKeyTyped

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
       x=evt.getX();
        y=evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
                setLocation(point.x - x, point.y - y);
    }//GEN-LAST:event_formMouseDragged

    private void txtcedulaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcedulaKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){ 
            contador++;
            if(txtcedula.getText().equals("")){
               DesktopNotify.showDesktopMessage("Campos Vacios","Por Favor "
                         + "Debe Completar el Campo de Cedula",
            DesktopNotify.WARNING, 6000L);  
            }else{
            if(checkmenor.isSelected() && txtcedula1.getText().equals("")){
               DesktopNotify.showDesktopMessage("Complete los Campos","Por Favor "
                         + "Debe Completar el Campo de Cedula",
            DesktopNotify.WARNING, 6000L); 
            }else{
                if(contador==1){
              iniciarcam();  
                }else{
                    
                }
            }
            }
            
        }
    }//GEN-LAST:event_txtcedulaKeyPressed

    private void combodocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combodocumentoActionPerformed
        if(!txtcedula.getText().equals("")){
            
        }else{
        if(combodocumento.getSelectedItem().equals("Cedula")){
            DefaultComboBoxModel modelo = new DefaultComboBoxModel();
            combodocumento2.setModel(modelo);
            combodocumento2.setEnabled(true);
            combodocumento2.addItem("[Seleccione]");
            combodocumento2.addItem("V-");
            combodocumento2.addItem("E-");
        }
        else if(combodocumento.getSelectedItem().equals("Carnet")){
             DefaultComboBoxModel modelo = new DefaultComboBoxModel();
            combodocumento2.setModel(modelo);
            combodocumento2.setEnabled(true);
            combodocumento2.addItem("[Seleccione]");
            combodocumento2.addItem("C-");
        }
        } 
    }//GEN-LAST:event_combodocumentoActionPerformed

    private void combodocumento2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combodocumento2ActionPerformed
       if(combodocumento2.getSelectedItem().equals("V-")){
          txtcedula.setEditable(true);
          txtcedula.setText("");
          Caracteres = 8;
          checkmenor.setEnabled(true);
          txtcedula.requestFocus();
       }
       if(combodocumento2.getSelectedItem().equals("E-")){
            txtcedula.setEditable(true);
          txtcedula.setText("");
          Caracteres = 9;
          checkmenor.setEnabled(true);
          txtcedula.requestFocus();
            
            
       }
       if(combodocumento2.getSelectedItem().equals("C-")){
            txtcedula.setEditable(true);
          txtcedula.setText("");
          Caracteres = 8;
          checkmenor.setEnabled(true);
          txtcedula.requestFocus();
           
           
       }
    }//GEN-LAST:event_combodocumento2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       guardarcedulatemp();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void checkmenorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkmenorActionPerformed
       if(checkmenor.isSelected()){
            txtcedula.requestFocus();
            txtcedula1.setEditable(true);
        }else{
            txtcedula1.setText("");
            txtcedula1.setEditable(false);
       }
    }//GEN-LAST:event_checkmenorActionPerformed

    private void txtcedula1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcedula1KeyTyped
        char captecla = evt.getKeyChar();
        if(!Character.isDigit(evt.getKeyChar())) {

            getToolkit().beep();

            evt.consume();
        }
        
        if(txtcedula1.getText().length()>=1){
            evt.consume();
        }
        
        
    }//GEN-LAST:event_txtcedula1KeyTyped

    private void txtcedula1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcedula1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){ 
            contador++;
            if(txtcedula.getText().equals("")){
               DesktopNotify.showDesktopMessage("Campos Vacios","Por Favor "
                         + "Debe Completar el Campo de Cedula",
            DesktopNotify.WARNING, 6000L);  
            }else{
            if(checkmenor.isSelected() && txtcedula1.getText().equals("")){
               DesktopNotify.showDesktopMessage("Complete los Campos","Por Favor "
                         + "Debe Completar el Campo de Cedula",
            DesktopNotify.WARNING, 6000L); 
            }else{
                if(contador==1){
              iniciarcam();  
                }else{
                    
                }
            }
            }
            
        }
    }//GEN-LAST:event_txtcedula1KeyPressed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        //int confirmar = JOptionPane.showConfirmDialog(null, "¿Desea Salir de Esta Ventana?");
        //if(JOptionPane.OK_OPTION == confirmar){
            try {
                movercedulas();
                //System.exit(0);  David Alvarez
                this.dispose();
                
            } catch (IOException ex) {
                Logger.getLogger(Camara.class.getName()).log(Level.SEVERE, null, ex);
            }
        //}
        //else{

        //}
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        this.setExtendedState(1);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void combodocumentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_combodocumentoKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_V){ 
            combodocumento.setSelectedIndex(1);
            combodocumento2.setSelectedIndex(1);
        }
        
        else if(evt.getKeyCode()==KeyEvent.VK_E){ 
            combodocumento.setSelectedIndex(1);
            combodocumento2.setSelectedIndex(2);
        }
        else if(evt.getKeyCode()==KeyEvent.VK_C){ 
            combodocumento.setSelectedIndex(2);
            combodocumento2.setSelectedIndex(1);
        }
    }//GEN-LAST:event_combodocumentoKeyPressed

    private void txtcedula1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcedula1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcedula1ActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JCheckBox checkmenor;
    public static javax.swing.JComboBox combodocumento;
    public static javax.swing.JComboBox combodocumento2;
    private javax.swing.JPanel contenedor;
    private javax.swing.JPanel contenedor1;
    private javax.swing.JPanel contenedor2;
    public static javax.swing.ButtonGroup grupo_btn;
    private javax.swing.JLabel imagen;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblcedula;
    public static javax.swing.JTextField txtcedula;
    public static javax.swing.JTextField txtcedula1;
    // End of variables declaration//GEN-END:variables

    private void iniciar(){
        try {
            dd = WebcamResolution.VGA.getSize();
            webc = Webcam.getDefault();
            webc.setViewSize(dd);
            
            for(Dimension d2 : webc.getViewSizes()){
                System.out.println("Largo: "+d2.getWidth()+"Ancho: "+d2.getHeight());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No Hay Camaras Conectadas");
            System.exit(0);
        }
    }
    
    private void iniciar_video(){
        new Thread(){
            @Override
            public void run(){
                while(true&&ejecutando){
                    try {
                        Image imag = webc.getImage();
                        ImageIcon icon = new ImageIcon(imag);
                        icon.setImage(icon.getImage().getScaledInstance
                        (imagen.getWidth(), imagen.getHeight(), 100));
                        imagen.setIcon(icon);
                        Thread.sleep(50);
                    } catch (Exception e) {
                        
                    }
                }
            }
        }.start();
    }
    


}
