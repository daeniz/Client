/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

/**
 *
 * @author dennisschmock
 */
public class Message {
    private String message;
    private String sender;
    
    public Message(String msg,String sender){
        this.message = msg;
        this.sender = sender;
    }
    
    public String getMessage(){
        return this.message;
    }

    @Override
    public String toString() {
        return sender + " wrote: " + message;
    }
    
    
}
