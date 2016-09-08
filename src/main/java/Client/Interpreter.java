package Client;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
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
public class Interpreter extends Observable implements Runnable {

    private Socket socket;
    private Scanner s;
    private AtomicBoolean loggedOut;
    private List<String> clientList;

    public Interpreter(Socket socket, Observer obs) {
        this.socket = socket;
        loggedOut = new AtomicBoolean();
        loggedOut.set(false);
        clientList = new CopyOnWriteArrayList<>();
        this.addObserver(obs);
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
                notifier("Your connection is closed!");
                loggedOut.set(true);
            }
            if (cmd.split(":")[0].equals("MSGRES")) {
                postMSG(cmd.split(":"));
            }
            if (cmd.split(":")[0].equals("CLIENTLIST")) {
                getClients(cmd.split(":"));
            }
        }
        System.out.println("TESTSTASEGAFS");
        notifier("Logged out, close client!");
    }

    public void setLoggedOut() {
        loggedOut.set(true);
    }

    public void postMSG(String[] command) {
        Message msg = new Message(command[2], command[1]);
        this.setChanged();
        notifyObservers(msg);
    }

    private void getClients(String[] split) {
        String[] tmp;
        if (split.length > 1) {
            tmp = split[1].split(",");
            clientList = new CopyOnWriteArrayList<String>(Arrays.asList(tmp));
        }
        ClientList lst = new ClientList(clientList);
        this.setChanged();
        this.notifyObservers(lst);

    }

    private void notifier(String msg) {
        this.setChanged();
        notifyObservers(msg);
    }
}
