package Client;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
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
public class ClientController implements Observer{

    Scanner s;
    Scanner input;
    PrintWriter pw;

    public ClientController(Socket socket) {
        try {
            s = new Scanner(socket.getInputStream());
            pw = new PrintWriter(socket.getOutputStream());
            input=new Scanner(System.in);
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (true){
            input.nextLine();
        }
        
    }

    @Override
    public void update(Observable o, Object arg) {
        
    }

    
    
    
    

}
