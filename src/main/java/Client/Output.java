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

    public void writeMessage(String msg) {
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
        } else {
            toBeSend +=  ":" + msg;
        }
        System.out.println("Testing message: " + toBeSend);
        pw.println(toBeSend + ":" + msg);
    }

    public void login(String username) {
        System.out.println("Logging in");
        pw.println("LOGIN:" + username);
    }

    void logout() {
        System.out.println("Logging out");
        pw.println("LOGOUT:");
    }

}
