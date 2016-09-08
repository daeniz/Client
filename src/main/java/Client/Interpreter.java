package Client;


import java.io.IOException;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
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
    private AtomicBoolean loggedOut;
    
    public Interpreter(Socket socket){
        this.socket=socket;
        loggedOut=new AtomicBoolean();
        loggedOut.set(false);
        try {
            s=new Scanner(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Interpreter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while (!loggedOut.get()){   //while we are not logged out..
            String cmd="";
            try{
                cmd=s.nextLine();
            }
            catch (NoSuchElementException ex){
                System.out.println("Socket vanished in front of our eyes!");
                loggedOut.set(true);
            }
            if (cmd.split(":")[0].equals("MSGRES")) {
                postMSG(cmd.split(":"));
            }
            if (cmd.split(":")[0].equals("CLIENTLIST")) {
                getClients(cmd.split(":"));
            }
        }
        System.out.println("Exiting interpreter");
    }
    
    public void setLoggedOut(){
        loggedOut.set(true);
    }
    
    public void postMSG(String[] command){
        String sender = command[1];
        System.out.println(sender+" spews: "+ command[2]+" out of his ***");
    }

    private void getClients(String[] split) {
        //Insert code
    }
}
