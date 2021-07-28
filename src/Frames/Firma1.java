
package Frames;

import ds.desktop.notify.DesktopNotify;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class Firma1 extends javax.swing.JFrame {
    Image image;
    Graphics2D graphics2D;
    public Firma1() {
        initComponents();
        this.setLocationRelativeTo(null); //DAVID ALVAREZ Posiciono formulario en el centro de la pantalla
        jButton8.setVisible(false);
        txtcedula.requestFocus();
        apariencia();
    }
 
    public void guardarfirma(){
                BufferedImage image;
                image = new BufferedImage(firma1.getWidth(),firma1.getHeight(),BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics = image.createGraphics();
                firma1.print(graphics);
                graphics.dispose();
                String ruta = "";
                
                //ruta = "\\\\192.168.5.1\\firmaspacientes\\"+txtcedula.getText()+".png";   DAVID ALVAREZ ABAJO CAMBIO
                ruta = "\\" + Principal.RutaFirmas + "\\"+txtcedula.getText()+".png";
                
                File file = new java.io.File(ruta);
                    if(file.exists()){
                        file.delete();
                    }
                        try {
                            ImageIO.write(image, "png", file);
                            Firma.cont = 0;
                            DesktopNotify.showDesktopMessage("Se Guardo la Firma con Exito","Puede Proceder a Guardar"
                                    + "otra Firma.", DesktopNotify.SUCCESS, 5000L);
                            this.dispose();
                            //DAVID ALVAREZ INACTIVO TODO BLOQUE ABAJO PARA QUE NO SE VUELVA ABRIR EL JFrame
                            /*Firma1 ff = new Firma1();
                            ff.setVisible(true);
                            Firma.cont = 0;
                            firma1.revalidate();
                            txtcedula.requestFocus();*/
                        } catch (IOException ex) {
                            System.out.println("Error al guardar archivo");
                        }            
    }
    
    void apariencia(){
        String rutatxt ="C:\\ClienteAGS\\ProgExternos\\ConfigIP.txt";
        String server = "";
        String puerto = "";
        String c_doctype = "";
        String servipri = "";
        try {
                FileReader f = new FileReader(rutatxt);
                BufferedReader b = new BufferedReader(f);
                server = b.readLine();
                puerto = b.readLine();
                c_doctype = b.readLine();
                
                b.close();
                //JOptionPane.showMessageDialog(null, server+" -- "+puerto+" -- "+c_doctype);
                
            } catch (Exception e) {
            }
        
         this.setIconImage(new ImageIcon(getClass().getResource
        ("/Imagenes/logoprevaler.png")).getImage());
         //this.setExtendedState(6);
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        contenedor3 = new javax.swing.JPanel();
        firma1 = new Frames.Firma();
        btnfoto1 = new javax.swing.JButton();
        btnfoto2 = new javax.swing.JButton();
        txtcedula = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Firma del Paciente");
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        setResizable(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 153));
        jLabel3.setText("Numero de Cedula del Paciente:");

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Unavailable_24px.png"))); // NOI18N
        jButton9.setToolTipText("Cerrar");
        jButton9.setBorderPainted(false);
        jButton9.setContentAreaFilled(false);
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton9.setFocusPainted(false);
        jButton9.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Unavailable_22px.png"))); // NOI18N
        jButton9.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Unavailable_22px.png"))); // NOI18N
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton9MouseClicked(evt);
            }
        });
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        contenedor3.setBackground(new java.awt.Color(255, 255, 255));
        contenedor3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 0), 1, true));
        contenedor3.setMinimumSize(new java.awt.Dimension(0, 0));
        contenedor3.setPreferredSize(new java.awt.Dimension(0, 0));

        firma1.setMinimumSize(new java.awt.Dimension(0, 0));
        firma1.setPreferredSize(new java.awt.Dimension(1200, 665));

        javax.swing.GroupLayout firma1Layout = new javax.swing.GroupLayout(firma1);
        firma1.setLayout(firma1Layout);
        firma1Layout.setHorizontalGroup(
            firma1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 848, Short.MAX_VALUE)
        );
        firma1Layout.setVerticalGroup(
            firma1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 421, Short.MAX_VALUE)
        );

        btnfoto1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnfoto1.setText("Guardar Datos");
        btnfoto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfoto1ActionPerformed(evt);
            }
        });

        btnfoto2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnfoto2.setText("Borrar Firma");
        btnfoto2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfoto2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout contenedor3Layout = new javax.swing.GroupLayout(contenedor3);
        contenedor3.setLayout(contenedor3Layout);
        contenedor3Layout.setHorizontalGroup(
            contenedor3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenedor3Layout.createSequentialGroup()
                .addGroup(contenedor3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(firma1, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(contenedor3Layout.createSequentialGroup()
                        .addComponent(btnfoto2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnfoto1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(468, 468, 468))
        );
        contenedor3Layout.setVerticalGroup(
            contenedor3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenedor3Layout.createSequentialGroup()
                .addComponent(firma1, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(contenedor3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnfoto1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnfoto2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        txtcedula.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        txtcedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcedulaActionPerformed(evt);
            }
        });
        txtcedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcedulaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcedulaKeyTyped(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(contenedor3, javax.swing.GroupLayout.PREFERRED_SIZE, 862, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 4, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtcedula, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(307, 307, 307)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtcedula, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contenedor3, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnfoto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfoto1ActionPerformed
        try{
            if(!txtcedula.getText().equals("")){
                if(Firma.cont == 0){
                    DesktopNotify.showDesktopMessage("Debe Firmar Antes de Guardar"," ",
                        DesktopNotify.ERROR, 5000L);
                }else{
                guardarfirma();
                }
            }else{
                DesktopNotify.showDesktopMessage("Ingrese el Numero de la Cedula"," ",
                    DesktopNotify.ERROR, 5000L);
            }
        }catch(Exception e){

        }
        
    }//GEN-LAST:event_btnfoto1ActionPerformed

    private void btnfoto2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfoto2ActionPerformed
       this.dispose();
       Firma1 ff = new Firma1();
       ff.setVisible(true);
       Firma.cont = 0;
       firma1.revalidate();
       
    
    }//GEN-LAST:event_btnfoto2ActionPerformed

    private void txtcedulaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcedulaKeyPressed
        /*if(evt.getKeyCode()==KeyEvent.VK_ENTER){
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

                    iniciarcam();
                }
            }

        }*/
    }//GEN-LAST:event_txtcedulaKeyPressed

    private void txtcedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcedulaKeyTyped
        int Caracteres = 8;
        char captecla = evt.getKeyChar();
        if(!Character.isDigit(evt.getKeyChar())) {

            getToolkit().beep();

            evt.consume();
        }

        if(txtcedula.getText().length()>=Caracteres){
            evt.consume();
        }

    }//GEN-LAST:event_txtcedulaKeyTyped

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        //int confirmar = JOptionPane.showConfirmDialog(null, "¿Desea Salir de Esta Ventana?");
        //if(JOptionPane.OK_OPTION == confirmar){
            //System.exit(0); DAVID ALVAREZ
           //this.dispose();
        //}
        //else{

        //}
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        this.setExtendedState(1);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void txtcedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcedulaActionPerformed

    private void jButton9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jButton9MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnfoto1;
    private javax.swing.JButton btnfoto2;
    public static javax.swing.JPanel contenedor3;
    private Frames.Firma firma1;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel3;
    public static javax.swing.JTextField txtcedula;
    // End of variables declaration//GEN-END:variables
}
