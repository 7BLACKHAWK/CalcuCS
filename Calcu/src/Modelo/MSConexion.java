package Modelo;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 *
 * @author Administrador
 */
public class MSConexion extends Thread{
    
    private Socket s;
    private DataInputStream dis;
    private DataOutputStream dos;
    private String nick;
    
    /** Creates a new instance of MSConexion */
    public MSConexion(Socket s) {
        try{
            this.s=s;
            dis=new DataInputStream(s.getInputStream());
            dos=new DataOutputStream(s.getOutputStream());
            start();
        }catch(Exception e){
        }
    }
    
    public String getNick(){
        return nick;
    }
    
    public void run(){
        while (true){
            try{
                int nCodigo=dis.readInt();
                String xTrama=dis.readUTF();
                String sTrama=dis.readUTF();
                String yTrama=dis.readUTF();
                switch(nCodigo){
                    case 1:
                        nick=sTrama;
                        enviarTrama(nCodigo, sTrama);
                        break;
                    case 2:                        
                        Double result=0.0;
                        String resultado;
        
                        if(sTrama.equals("-")){
                            result=Double.parseDouble(xTrama)-Double.parseDouble(yTrama);
                        }else{}
                        if(sTrama.equals("+")){
                            result=Double.parseDouble(xTrama)+Double.parseDouble(yTrama);
                        }else{}        
                        if(sTrama.equals("*")){
                            result=Double.parseDouble(xTrama)*Double.parseDouble(yTrama);
                        }else{}
                        if(sTrama.equals("/")){
                            result=Double.parseDouble(xTrama)/Double.parseDouble(yTrama);
                        }else{}
                        resultado=result.toString();
                        
                        enviarTrama(nCodigo, resultado);
                        System.out.print(xTrama + " " + sTrama + " " + yTrama + " = " + resultado);
                        break;
                    case 3:
                        break;
                }
                
            }catch(Exception e){
            }
            
        }
    }
    
    public void enviarTrama(int nCodigo, String rTrama){
        try{
           dos.writeInt(nCodigo);
           dos.writeUTF(rTrama);
        }catch(Exception e){
        }
    }
}
