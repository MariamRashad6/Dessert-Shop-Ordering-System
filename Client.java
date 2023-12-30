
package dessertsshoporderingsystem;

/*
  class Client when connected to Server recieves from Server url , username , password 
  connects program to database then starts gui (multithreading is handled)
*/

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.stage.Stage;


public class Client  extends Application{
    
    //class variables
    static Connection c;
    static String u;
    static String user;
    static String password;
    
    public static void main(String[] args) {
      try{
            Socket socket = new Socket("localhost",8000);
            DataInputStream fromServer = new DataInputStream(socket.getInputStream());
            
            
            //from server
            u = fromServer.readUTF();
            user = fromServer.readUTF();
            password = fromServer.readUTF();
            
            System.out.println(u+user+password);//checking 
        
        //handling connection
        try{
	    //Load Driver for MySQL
	    Class.forName("com.mysql.cj.jdbc.Driver"); //throws ClassNotFoundExcpetion 
	}
	catch (ClassNotFoundException  ex){
		System.out.println("DataBase driver can not be loaded");
		return;
	}
	System.out.println("DataBase driver loaded  sucessfully ");
        
        try{
            c = DriverManager.getConnection(u,user,password);
            
        }
        catch(SQLException ex)   {
        System.out.println("Can not connect to database");
        return;
        }
	System.out.println("Connected to DataBase successfully ");
    }
      catch(IOException e){ //handling network excpetion
            System.out.println("Done");
        }
            launch(args);//goes to start    
            
        }
        
    

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new User().mainScene(primaryStage));
        primaryStage.show();
    }

   

  
    

}
