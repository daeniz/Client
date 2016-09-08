package Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
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
public class ClientController implements Observer {

    Scanner s;
    Scanner input;
    PrintWriter pw;
    Output o;
    Interpreter i;
    Socket socket;
    List<String> clientList;

    public ClientController(Socket socket) {
        this.socket = socket;
        i = new Interpreter(socket);
            
        try {
            s = new Scanner(socket.getInputStream());
            pw = new PrintWriter(socket.getOutputStream(), true);
            input = new Scanner(System.in);
            o = new Output(pw);
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        
    public void runClient(){
        
        
        Thread it=new Thread(i);
            it.start();
        System.out.println("Ready to log in - please type your name");
        o.login(input.nextLine());  //Assumes user succesfully logs in on first try
        String msg = "";
        while (!msg.equals("LOGOUT")) {
           System.out.println("Type your message!");
           msg = input.nextLine();
           if (msg.equals("LOGOUT")){
               logout();
               break;
           }
           
//            System.out.println("Now tell us: who could ever benefit from hearing this shit?");
//            String[] receivers = input.nextLine().split(",");
            o.writeMessage(msg);
        }
        try {
            it.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Exiting!");
    }

    public void logout() {
        o.logout();
        i.setLoggedOut();
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }

}
