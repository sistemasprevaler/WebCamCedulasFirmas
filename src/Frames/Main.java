package Frames;

public class Main {

    public Main() {
       
    }
    
    public static void main(String[] args) { 
       //Ventana lg = new Ventana();
       
       //DAVID ALVAREZ CAMBIO PARA UNIFICAR EL PROGRAMA EN UNA SOLA PANTALLA
       /*Camara lg = new Camara();
       lg.setVisible(true);
       lg.setLocationRelativeTo(null);*/
       
       Principal pprincipal = new Principal();
       pprincipal.setVisible(true);
       pprincipal.setLocationRelativeTo(null);
    }
}
