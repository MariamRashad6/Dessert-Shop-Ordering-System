
package dessertsshoporderingsystem;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
   Server class waits for socket from Client a new thread is created (multithreading) 
   and socket is sent to handler class (to handle operations)
*/


public class Server  {
    
    public static void main(String[] args){
        try{
            ServerSocket serverSocket = new ServerSocket(8000); //server waiting in port 8000 for a socket
            while(true){
                Socket socket = serverSocket.accept();//listens
                System.out.println("Server connected");
                new Thread(new Handler(socket)).start();//creating new thread every time a new client connects to a server //new run //start() goes to run()
                System.out.println("New thread");
               
                
            }
        }
        catch(IOException e){
            System.out.println("Done");
        }
    }
    }
    
    

