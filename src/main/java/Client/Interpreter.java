package Client;


import java.io.IOException;
import java.net.Socket;
import java.util.Observable;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Daniel
 */
public class Interpreter extends Observable implements Runnable{
    private Socket socket;
    private Scanner s;
    
    public Interpreter(Socket socket){
        this.socket=socket;
        try {
            s=new Scanner(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Interpreter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while (true){
            String cmd=s.nextLine();
            System.out.println(cmd);
            if (cmd.split(":")[0].equals("msgRes")) {
                System.out.println("Message incomming!");
                postMSG(cmd.split(":"));
            }
        }
    }
    
    public void postMSG(String[] command){
        String sender = command[1];
        System.out.println(sender+" spews: "+ command[2]+" out of his ***");
    }
}
