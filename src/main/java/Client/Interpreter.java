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
    Socket socket;
    Scanner s;
    
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
            s.nextLine();
        }
    }
}
