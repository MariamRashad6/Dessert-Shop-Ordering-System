
package dessertsshoporderingsystem;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;

/*
   Handler class implements Runnable to handle multithreadin
   also it handles Server operations (sending url , password , username)
   to Client to connect to DataBase
*/
public class Handler implements Runnable   {
    
    //Class Variables
    private Socket socket;
    public static Connection c;
    private static String u;
    private static String user;
    private static String password;
    
    public Handler(Socket s){ //constructor takes socket
        socket = s;
    }
    Handler(){
        
    }
    

    //getters and setters
    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        Handler.user = user;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Handler.password = password;
    }
    
    
    @Override
    public void run() {
        try{
            
            
            DataOutputStream toClient = new DataOutputStream(socket.getOutputStream()); //output stream write in socket
            
            
            toClient.writeUTF("jdbc:mysql://localhost/desserts_shop_ordering_system");
            toClient.writeUTF("root");
            toClient.writeUTF("0");
            
            
            toClient.writeUTF("connected");
        }
        
	
        
        catch(IOException e){
            System.out.println("Done");
        }
        
    }
    
   
    
}
