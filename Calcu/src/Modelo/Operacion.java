package Modelo;

import Vista.Caratula;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class Operacion extends Thread{
    String x;
    String y;
    String signo;
    String resultado;
    
    private int port;
    private String url;
    private Socket s;
    private boolean bConectado;
    Caratula caratula;
    
    public Operacion(int port, String url, Caratula caratula) {
        this.port=port;
        this.url=url;
        this.caratula=caratula;
        this.x="";
        this.y="";
        this.signo="";
    }    

    
    public Operacion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void run(){
        try{
            s=new Socket(url, port);
            DataInputStream dis=new DataInputStream(s.getInputStream());
            enviarTrama(1, "", "", "");
            bConectado=true;
            while(bConectado){
                int nCodigo =dis.readInt();
                resultado=dis.readUTF();
                switch(nCodigo){
                    case 1:
                        break;
                    case 2:
                        caratula.mensajeRecibido(resultado);
                        break;
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(caratula, "No se pudo establecer la conexión");
        }
    }
    
    public void enviarMensaje(String xMensaje, String sMensaje, String yMensaje ){
        enviarTrama(2, xMensaje, sMensaje, yMensaje);
    }
    

    
    public void enviarTrama(int nCodigo, String xTrama, String sTrama, String yTrama){
        //se usa para enviar los mensajes mediante el dataoutputstream que sirve para enviar bits,enteros,cadenas de caracteres sin tener que pasarlos a una adena de bits o bytes
        try{
            DataOutputStream dos=new DataOutputStream(s.getOutputStream());
            dos.writeInt(nCodigo);
            dos.writeUTF(xTrama);
            dos.writeUTF(sTrama);
            dos.writeUTF(yTrama);
        }catch(Exception e){
            JOptionPane.showMessageDialog(caratula, "No se pudo enviar el mensaje");
        }
        
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getSigno() {
        return signo;
    }

    public void setSigno(String signo) {
        this.signo = signo;
    }
    
    
    
}
