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
    Observer o;
    Interpreter i;
    public InputListener(Observer o, Socket socket){
        this.socket = socket;
        loggedOut = new AtomicBoolean();
        loggedOut.set(false);
        i=new Interpreter(socket, o);
        this.o=o;
        //System.out.println("ClassName" + o.getClass().getName());
        this.addObserver(o);
        try {
            s = new Scanner(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Interpreter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while (!loggedOut.get()) {   //while we are not logged out..
            String cmd = "";
            try {
                cmd = s.nextLine();
            } catch (NoSuchElementException ex) {
                loggedOut.set(true);
            }
            i.executeCommand(cmd);
            if (o.getClass().getName().equals("dummyClient")){
            setChanged();
            notifyObservers(cmd);
            }
        }
    }
    
       public void setLoggedOut() {

        loggedOut.set(true);
    }
}
