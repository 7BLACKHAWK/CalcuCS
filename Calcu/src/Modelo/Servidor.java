package Modelo;

import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
public class Servidor extends Thread{
    
    private int port;
    private JFrame ventana;
    
    /** Creates a new instance of MServidor */
    public Servidor(JFrame ventana, int port) {
        this.port = port;
        this.ventana = ventana;
    }
    
    public void run(){
        //esta excecion se usa para abrir el puerto y luego cerrarlo con la funcion que no se quede colgado el programa esperando una nueva conexion 
        //tambien en este metodo se permite que se conecten varios al chat
        ServerSocket ss=null;
        try{
            ss=new ServerSocket(port);
            JOptionPane.showMessageDialog(ventana,"Servidor iniciado");

            while (true){
                Socket s=ss.accept();
                MSConexion conex = new MSConexion(s);
                        
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(ventana,"Error al abrir el puerto. Posiblemente ya esta en uso.");
        }
        try{
            ss.close();
        }catch(Exception e){
        }
    }
    
    public Servidor() {
        Servidor servidor = new Servidor(ventana, 3552);
        servidor.start();
    }
    
    public static void main(String[] args) {
        new Servidor();
    }    
    
}
