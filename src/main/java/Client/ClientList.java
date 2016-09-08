/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.util.List;

/**
 *
 * @author dennisschmock
 */
public class ClientList {
        List<String> clientList;
        
        public ClientList(List clientList){
            this.clientList = clientList;
        }
        
        public List getClientList(){
            return clientList;
        }

    @Override
    public String toString() {
        String str = "";
        for (String string : clientList) {
            
            if(!string.contentEquals("null")) str += string + "\n";
        }
        
       return str;
    }
        
        

}
