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
public class Client implements Observer {

    private static boolean clientRunning = true;
    private static Scanner input;
    private static ClientController cc;
    private static Client client;

    public static void main(String[] args) {
        client = new Client();
        input = new Scanner(System.in);

        String host;
        int port;
        if (args.length == 2) {
            host = args[0];
            port = Integer.parseInt(args[1]);
        } else {
            return;
        }
        cc = new ClientController(client,host,port);
        //cc.runClient();
        
        while (clientRunning) {
            
            cc.sendMessage(input.nextLine());
            
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println((String) arg);
    
    }
}
