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
    
    public Message(String msg){
        this.message = msg;
    }
    
    public String getMessage(){
        return this.message;
    }
}
