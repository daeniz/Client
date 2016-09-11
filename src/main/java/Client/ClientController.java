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
public class ClientController {

    Scanner s;
    PrintWriter pw;
    Output o;
    Interpreter i;
    Socket socket;
    List<String> clientList;

    public ClientController(Observer obs, String host, int port) throws IOException {
        
        try {
            socket = new Socket(host, port);
            i = new Interpreter(socket, obs);
            s = new Scanner(socket.getInputStream());
            pw = new PrintWriter(socket.getOutputStream(), true);

            o = new Output(pw);
        } catch (IOException ex) {
            throw ex;
        }
    }

    public void runClient() {

        Thread it = new Thread(i);
        it.start();

    }

    public void login(String username) {
        pw.println("LOGIN:" + username);
    }

    public void sendMessage(String msg) {
        if (msg.equals("LOGOUT:")) {
            logout();
            return;
        }

        pw.println(o.writeMessage(msg));
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

}
