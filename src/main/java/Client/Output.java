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
    public Output(PrintWriter pw){
        this.pw=pw;
    }
    
    
    public void writeMessage(String msg, String[] users){
        String toBeSend="MSG:";
        boolean first=true;
        for (int i = 0; i < users.length; i++) {
            if (first){
                first=false;
                toBeSend+=users[i];
            }
            else toBeSend+=","+users[i];
        }
        pw.println(toBeSend+":"+msg);
    }
    
    public void login(String username){
        System.out.println("Logging in");
        pw.println("LOGIN:"+username);
    }
    
    
}
