package com.chat.client;

import javax.swing.JOptionPane;

public class ClientServer {
    public static void main(String [] args){

        Object[] selectionValues = { "Server","Client"};
        String initialSection = "Server";

        Object selection = JOptionPane.showInputDialog(null, "Login as : ", "MyChatApp", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSection);
        if(selection.equals("Server")){
            String[] arguments = new String[] {};
            new ChatServerSync().main(arguments);
        }else if(selection.equals("Client")){
            String IPServer = JOptionPane.showInputDialog("Enter the Server ip adress");
            String[] arguments = new String[] {IPServer};
            new ClientChat().main(arguments);
        }

    }
}
