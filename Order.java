
package dessertsshoporderingsystem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/*
  this class handles all the ordering system accessing user database and products database
*/

public class Order implements Ordering {
    
    //class variables
    public ArrayList<String> selectedProducts = new ArrayList<>();
    public ArrayList<CheckBox> checkbox = new ArrayList<>();
    private double totalCost;
    private Product p;
    
    //JDBC variables
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    
   

    public double getTotalCost() {
        
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    
   
    //displaying all available products in shop
    //displayed products are updated dynamically according to database
    public  ScrollPane displayProducts() throws FileNotFoundException{
        
        connectToDataBase();
        VBox vb1 = new VBox(10);
        vb1.setStyle("-fx-background-color: #b4dcdd");
        
        ImageView imageView;
        
        ScrollPane scrollpane = new ScrollPane();
        scrollpane.setStyle("-fx-background-color: #b4dcdd;");
        scrollpane.setPadding(new Insets(5,0,5,190));
       
            try {
            //create statement
            statement = connection.createStatement();
            
            //excute query
            resultSet = statement.executeQuery("select * from products");//DQL
            while(resultSet.next()){
                  p =  new Product(
                            resultSet.getString("pname"),
                            resultSet.getInt("quantity"),
                            resultSet.getDouble("price"),
                            resultSet.getString("category")
                    );
                    
                    //checking product quantity making sure that its is avaliable
                    if(p.getQuantity()>0){
                    Label label = new Label("    Price: "+p.getPrice());//price label
                    label.setStyle("-fx-font-size: 12pt;");
                    CheckBox c = new CheckBox(p.getName()); 
                    c.setFont(new Font(16));
                    checkbox.add(c);
                    
                    //setting product image according to category
                    if("cakes".equals(p.getCategory())){
                        FileInputStream inputstream = new FileInputStream("C:\\Users\\mariam\\Downloads\\cake-slice.png"); 
                        Image image = new Image(inputstream); 
                        imageView = new ImageView(image);//set image view
        
                        imageView.setFitHeight(80);
                        imageView.setFitWidth(80);
                        vb1.getChildren().addAll(imageView,c,label);
                    }
                    else if("icecream".equals(p.getCategory())) {
                        FileInputStream inputstream = new FileInputStream("C:\\Users\\mariam\\Downloads\\ice-cream.png"); 
                        Image image = new Image(inputstream); 
                        imageView = new ImageView(image);//set image view
        
                        imageView.setFitHeight(100);
                        imageView.setFitWidth(100);
                        vb1.getChildren().addAll(imageView,c,label);
                    }
                    else{
                        FileInputStream inputstream = new FileInputStream("C:\\Users\\mariam\\Downloads\\bakery.png"); 
                        Image image = new Image(inputstream); 
                        imageView = new ImageView(image);//set image view
        
                        imageView.setFitHeight(100);
                        imageView.setFitWidth(100);
                        vb1.getChildren().addAll(imageView,c,label);
                    }
                    
                    }
                }
                statement.close();
                connection.close();
                
            }
         catch (SQLException ex) {
            System.out.println("Exception");
            
        }
            
           //creating addToCart button and setting it on action and adding it to VBox
           Button addToCartBtn = new Button("Add to cart");
           addToCartBtn.setStyle("-fx-text-fill: lightseagreen; -fx-font-size: 16; -fx-background-radius: 6;");
           addToCartBtn.setOnAction(e->{
               handleOrder();
           });
           
           vb1.getChildren().add(addToCartBtn);
          
           //adding VBox to scroll pane
           scrollpane.setContent(vb1);
           scrollpane.setFitToWidth(true); 
           
           return scrollpane;
    }
    
    //handling user order according to selected products calculating total price
    @Override
    public void handleOrder(){
        connectToDataBase();
        for(int i=0 ; i<checkbox.size() ; i++){
        if(checkbox.get(i).isSelected()){
            System.out.println(checkbox.get(i).getText());
            selectedProducts.add(checkbox.get(i).getText());
            
        
       
        System.out.println("In fun");
        
        try {
            //create statement
            statement = connection.createStatement();
            
            //excute query
            resultSet = statement.executeQuery("select * from products");//DQL
            System.out.println("In fun");
            
            while(resultSet.next()){
                  p =  new Product(
                            resultSet.getString("pname"),
                            resultSet.getInt("quantity"),
                            resultSet.getDouble("price"),
                            resultSet.getString("category")
                    );
               int j=0;
                if(selectedProducts.get(j).equals(p.getName())){
                  j++;
                   totalCost = totalCost +p.getPrice();
                   
                }                 
                }
            
                statement.close();
                connection.close();
             
            }
         catch (SQLException ex) {
            System.out.println("Can't excute query");   
        }
           
       
        
        }
    }
    }
    
    
    //displaying order details and customer details for confirmation
    public VBox displayOrder(String username) throws FileNotFoundException{
        
        //ordered products details
        VBox vb = new VBox(7);
        vb.setPadding(new Insets(30,10,10,10));
        
        for(int i=0 ; i< selectedProducts.size();i++){
            Label label = new Label(selectedProducts.get(i));
            label.setStyle("-fx-font-size: 12pt; -fx-font-weight: bold;");
            vb.getChildren().add(label);
        }
    
        
        Label label = new Label("Total Cost:"+String.valueOf(totalCost));
        label.setStyle("-fx-font-size: 14pt; -fx-font-weight: bold;");
        vb.getChildren().add(label);
        
        System.out.println(username);
        
        //customer details
         connectToDataBase();
            try {
            //create statement
            statement = connection.createStatement();
            
            //excute query
              resultSet = statement.executeQuery("SELECT * FROM users");
                while(resultSet.next()){
                   
                   //polymorphism
                   User customer =  new Customer(
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("address"),
                            resultSet.getString("mobile_phone"),
                            resultSet.getString("user_first_name"),
                            resultSet.getString("user_last_name")
                            
                    );
                    
                    if(username.equals(customer.getUserName())){
                    System.out.println(customer.getUserName());
                    Label name = new Label("Name:"+customer.getFirstName()+" "+customer.getLastName()); //customer name
                    Label address = new Label("Address:"+customer.getAddress());//customer address
                    Label mobile = new Label("Mobile: "+customer.getMobileNumber());//customer mobile number
                    name.setStyle("-fx-font-size: 14pt; -fx-font-weight: bold;");
                    address.setStyle("-fx-font-size: 14pt; -fx-font-weight: bold;");
                    mobile.setStyle("-fx-font-size: 14pt; -fx-font-weight: bold;");
                    vb.getChildren().addAll(name,address,mobile);
                    }
                }
                statement.close();
                connection.close();
             
            }
         catch (SQLException ex) {
            System.out.println("Can't excute query");   
        }
        
        return vb;
    }
    
    
    //confirming order
    //after order in confimred a message is displayed for customer and the ordered products quantity decrements effecting database
    @Override
    public void confirmOrder(){
        connectToDataBase();
        
        for(int i=0 ; i< selectedProducts.size();i++){
            try {
            //create statement
            statement = connection.createStatement();
            
            //excute query
            resultSet = statement.executeQuery("select * from products");//DQL
            System.out.println("In fun");
            
            while(resultSet.next()){
                  p =  new Product(
                            resultSet.getString("pname"),
                            resultSet.getInt("quantity"),
                            resultSet.getDouble("price"),
                          resultSet.getString("category")
                    );
               int j=0;
                if(selectedProducts.get(j).equals(p.getName())){
                  j++;
                   p.setQuantity(p.getQuantity()-1);//decrement product quantity by 1
                   PreparedStatement preparedStatement = connection.prepareStatement(" update  products set pname = ? , quantity = ? , price = ? where pname = '"+p.getName()+"'");//prepared statement
        
                  //excute query
                  preparedStatement.setString(1, p.getName());
                  preparedStatement.setInt(2, p.getQuantity());
                  preparedStatement.setDouble(3, p.getPrice());
                  preparedStatement.execute();//DML                
                  System.out.println(p.getQuantity());
                }                 
                }
                //close connection
                statement.close();
                connection.close();
             
            }
         catch (SQLException ex) {
            System.out.println("Can't excute query");   
        }
           
        }
        
    }

    //connecting to database
    public void connectToDataBase(){
        try{
	    //Load Driver for MySQL
	    Class.forName("com.mysql.cj.jdbc.Driver"); 
	}
	catch (ClassNotFoundException  ex){
		System.out.println("DataBase driver can not be loaded");
		return;
	}
	System.out.println("DataBase driver loaded  sucessfully ");
    
        try{
             connection = DriverManager.getConnection("jdbc:mysql://localhost/desserts_shop_ordering_system","root","0");
        }
        catch(SQLException ex)   {
        System.out.println("Can not connect to database");
        return;
        }
	System.out.println("Connected to DataBase successfully ");
    }
    
    
}
