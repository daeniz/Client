/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.IOException;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author danie
 */
public class InputListener extends Observable implements Runnable{

    private final AtomicBoolean loggedOut;
    private final Socket socket;
    private Scanner s;
    private Observer o;
    private Interpreter i;
    public InputListener(Observer o, Socket socket){
        this.socket = socket;
        loggedOut = new AtomicBoolean();
        loggedOut.set(false);
        i=new Interpreter(socket, o);
        this.o=o;
        //System.out.println(o.getClass().getName());
        
        try {
            s = new Scanner(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Interpreter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        this.addObserver(o);
        
        //System.out.println(o.getClass().getName());
        while (!loggedOut.get()) {   //while we are not logged out..
            String cmd = "";
            try {
                cmd = s.nextLine();
            } catch (NoSuchElementException ex) {
                loggedOut.set(true);
            }
            //System.out.println(o.getClass().getName());
            if (o.getClass().getName().equals("ServerTestSuite.DummyObserver")){  
            setChanged();
            notifyObservers(cmd);
            }
            i.executeCommand(cmd);
            
        }
    }
    
       public void setLoggedOut() {

        loggedOut.set(true);
    }
}
