package Client;

import java.io.PrintWriter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Daniel
 */
public class Output {

    PrintWriter pw;

    public Output(PrintWriter pw) {
        this.pw = pw;
    }

    /**
     * The purpose of this method is to take a message string, and create the proper output
     * for sending to the server
     * @param msg String containing message plus persons sending to.
     */
    public String writeMessage(String msg) {
        String toBeSend = "MSG:";
        boolean first = true;
        String[] tempString = msg.split(":");
        
        if (tempString.length > 1) {
            String[] users = tempString[0].split(",");

            for (int i = 0; i < users.length; i++) {
                if (first) {
                    first = false;
                    toBeSend += users[i];
                } else {
                    toBeSend += "," + users[i];
                }
            }
            toBeSend += ":" + tempString[1];
        } else if (!msg.contains(":")) {
            toBeSend +=  ":" + msg;
        }
        System.out.println(toBeSend);
        return toBeSend;
    }

    public void login(String username) {
        pw.println("LOGIN:" + username);
    }

    public void logout() {
        pw.println("LOGOUT:");
    }

}
