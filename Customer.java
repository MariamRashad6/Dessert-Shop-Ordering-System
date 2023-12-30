package dessertsshoporderingsystem;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
  Customer class has all customer variables and different methods and scenes to view products,
  order , checkout and confirm the order
*/

public class Customer  extends User {
    
    //class variables
    private static String userType = "customer";
    private Order order;
    
   
    
   

    //constructors
    public Customer(){
      
    }
    public Customer(String userName, String password , String address , String mobileNumber , String firstName , String lastName) {
        super(userName,password ,address ,mobileNumber ,firstName ,lastName);
        
    }
    
   
     
    //customer main scene
    public Scene customerScene(Stage primaryStage) throws FileNotFoundException {
        
        order = new Order();
        //Border pane as the root for scene
        BorderPane borderPane = new BorderPane();
        
        
        //setting left border pane
        //Image
        FileInputStream inputstream = new FileInputStream("C:\\Users\\mariam\\Documents\\NetBeansProjects\\JavafxStructure\\src\\javafxstructure\\imagecopy.jpg"); 
        Image image = new Image(inputstream); 
        ImageView imageView = new ImageView(image);//set image view
        
        imageView.setFitHeight(480);
        imageView.setFitWidth(250);
       
        //creating a stackpane and adding image to it
        StackPane stackPane = new StackPane();//Create a pane
        stackPane.getChildren().add(imageView);   
       
       
        
        //setting left border pane
        //creating Hbox
        HBox hb = new HBox(35);
        
        
        //creating buttons
        //Check out button creation and handling event
        Button checkOutBtn = new Button("Check out");
        checkOutBtn.setStyle("-fx-text-fill: lightseagreen; -fx-font-size: 20; -fx-background-radius: 6;");
        checkOutBtn.setOnAction(e->{
            try {
                primaryStage.setScene(customerCheckOutScene(primaryStage,order));
                //primaryStage.show();
                
            } catch (Exception ex) {
                System.out.println("Exception");
            }
        });
        
       
        
        //Log out button craetion and handling event
        Button logOutBtn = new Button("Log Out");
        logOutBtn.setStyle("-fx-text-fill: lightseagreen; -fx-font-size: 20; -fx-background-radius: 6;");
        logOutBtn.setOnAction(e->{
            try {
                new User().start(primaryStage);
            } catch (Exception ex) {
                System.out.println("Exception");
            }
        });
        
        //Add buttons to hbox
        hb.getChildren().addAll(checkOutBtn,logOutBtn);
        hb.setPadding(new Insets(20,10,10,70));
        
        //creating hbox and adding label to it
        HBox hb2 = new HBox();
        Label labelTitle = new Label("Select Products:");
        labelTitle.setStyle("-fx-font-size: 16pt;");
        hb2.setPadding(new Insets(50,10,10,10));
        hb2.getChildren().add(labelTitle);
         
        //creating vbox , adding its components , setting its style
        VBox vb1 = new VBox();
        vb1.getChildren().addAll(hb,hb2,order.displayProducts());
        vb1.setStyle("-fx-background-color: #b4dcdd");
        
        borderPane.setCenter(vb1);//adding anchorPane to center border 
        
        //adding stack pane to the left border
        borderPane.setLeft(stackPane);
        
        //creating scene
        Scene scene = new Scene(borderPane , 800 , 480);
        
        
        //setting primaryStage title and icon
        primaryStage.setTitle("Desserts Shop");
        FileInputStream inputstream2= new FileInputStream("C:\\Users\\mariam\\Downloads\\—Pngtree—cartoon dessert delicious cupcake_5780474.png"); 
        Image image2 = new Image(inputstream2); 
        primaryStage.getIcons().add(image2);
       
        return scene;
    }
    
    //customer checkout scene
    public Scene customerCheckOutScene(Stage primaryStage,Order order) throws FileNotFoundException {
        
        //Border pane as the root for scene
        BorderPane borderPane = new BorderPane();
        
        
        //setting left border pane
        //Image
        FileInputStream inputstream = new FileInputStream("C:\\Users\\mariam\\Documents\\NetBeansProjects\\JavafxStructure\\src\\javafxstructure\\imagecopy.jpg"); 
        Image image = new Image(inputstream); 
        ImageView imageView = new ImageView(image);//set image view
        
        imageView.setFitHeight(490);
        imageView.setFitWidth(250);
       
        //creating a stackpane and adding image to it
        StackPane stackPane = new StackPane();//Create a pane
        stackPane.getChildren().add(imageView);   
       
       
        
        //setting center border pane
        //creating Hbox
        HBox hb = new HBox(35);
        
        
        //creating buttons
        //back button creation and handling its event
        Button backBtn = new Button("Back");
        backBtn.setStyle("-fx-text-fill: lightseagreen; -fx-font-size: 20; -fx-background-radius: 6;");
        //goes to previous Scene
        backBtn.setOnAction(e->{
            try {
                
                primaryStage.setScene(customerScene(primaryStage));
            } catch (FileNotFoundException ex) {
                System.out.println("Exception");
            }
        });
        
        //confirm button creation and event handling
        Button confirmBtn = new Button("Confirm");
        confirmBtn.setStyle("-fx-text-fill: lightseagreen; -fx-font-size: 20; -fx-background-radius: 6;");
        confirmBtn.setOnAction(e->{
            
            try {
                order.confirmOrder();
                primaryStage.setScene(confirmed(primaryStage,order));
            } catch (FileNotFoundException ex) {
                System.out.println("Order button exception");
            }
           
            
        });
        
        //Log out button creation and event handling
        Button logOutBtn = new Button("Log Out");
        logOutBtn.setStyle("-fx-text-fill: lightseagreen; -fx-font-size: 20; -fx-background-radius: 6;");
        logOutBtn.setOnAction(e->{ //goes to main Scene of program
            try {
                primaryStage.setScene(new User().mainScene(primaryStage));
            } catch (FileNotFoundException ex) {
                System.out.println("Exception");
            }
        });
        
        //Add buttons to hbox
        hb.getChildren().addAll(backBtn,confirmBtn,logOutBtn);
        hb.setPadding(new Insets(30,10,10,10));
        
        //adding stack pane to the left border
        borderPane.setLeft(stackPane);
        
        //creating StackPane and adding an image
        StackPane s = new StackPane();
        FileInputStream inputstream2 = new FileInputStream("C:\\Users\\mariam\\Downloads\\imageedit_1_9703133057.png"); 
        Image image2 = new Image(inputstream2); 
        ImageView imageView2 = new ImageView(image2);//set image view
        //setting dimensions 
        imageView2.setFitHeight(490);
        imageView2.setFitWidth(550);
        
        //creating vbox and adding components
        VBox vb1 = new VBox();
        vb1.getChildren().addAll(hb,order.displayOrder(getUserName()));
        //adding vbox to stackpane
        s.getChildren().addAll(imageView2,vb1);
        
        borderPane.setCenter(s);//adding stackPane to center border 
        
        
        
        
        //creating scene
        Scene scene = new Scene(borderPane , 800 , 480);
        
        //setting primaryStage title and icon
        primaryStage.setTitle("Desserts Shop");
        FileInputStream inputstream3= new FileInputStream("C:\\Users\\mariam\\Downloads\\—Pngtree—cartoon dessert delicious cupcake_5780474.png"); 
        Image image3 = new Image(inputstream3); 
        primaryStage.getIcons().add(image3);
       
        return scene;
    }
    
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(customerScene(primaryStage));
        primaryStage.show();
    }

    

    public static String getUserType() {
        return userType;
    }

    public static void setUserType(String userType) {
        Customer.userType = userType;
    }

    
    
    
   public static void main(String[] args){
       launch(args);//goes to start
   }

    
   //order after confimation scene
   public Scene confirmed(Stage primaryStage,Order order) throws FileNotFoundException{
        
        //Border pane as the root for scene
        BorderPane borderPane = new BorderPane();
        
        
        //setting left border pane
        //Image
        FileInputStream inputstream = new FileInputStream("C:\\Users\\mariam\\Documents\\NetBeansProjects\\JavafxStructure\\src\\javafxstructure\\imagecopy.jpg"); 
        Image image = new Image(inputstream); 
        ImageView imageView = new ImageView(image);//set image view
        //set image dimensions
        imageView.setFitHeight(480);
        imageView.setFitWidth(250);
       
        //creating a stackpane and adding image to it
        StackPane stackPane = new StackPane();//Create a pane
        stackPane.getChildren().add(imageView);   
       
        //adding stack pane to the left border
        borderPane.setLeft(stackPane);
        
        //setting center border pane
        //Image
        FileInputStream inputstream2 = new FileInputStream("C:\\Users\\mariam\\Downloads\\imageedit_1_9703133057.png"); 
        Image image2 = new Image(inputstream2); 
        ImageView imageView2 = new ImageView(image2);//set image view
        //image dimensions
        imageView2.setFitHeight(480);
        imageView2.setFitWidth(550);
       
        //creating labels and setting style
        Label label = new Label("Your order is confirmed");
        label.setStyle("-fx-font-size: 20pt; -fx-font-weight: bold;");
        Label label1= new Label("     Enjoy every bite!");
        label1.setStyle("-fx-font-size: 20pt; -fx-font-weight: bold;");
        Label label2 = new Label("     Made With Love!");
        label2.setStyle("-fx-font-size: 20pt; -fx-font-weight: bold;");
        
        //creating a vbox
        VBox vb = new VBox(5);
        vb.setPadding(new Insets(150,10,10,130));
        
        VBox vb1 = new VBox(5);//Create a pane
        vb1.setPadding(new Insets(30,100,100,100));
        
        //creating back button and event handling
        Button backBtn = new Button("Back");
        backBtn.setStyle("-fx-text-fill: lightseagreen; -fx-font-size: 20; -fx-background-radius: 6;");
        backBtn.setOnAction(e->{
            try {
                primaryStage.setScene(customerScene(primaryStage));
            } catch (FileNotFoundException ex) {
                System.out.println("Exception in backBtn");
            }
        });
        vb1.getChildren().add(backBtn);//adding back button to VBox (vb1)
        
        vb.getChildren().addAll(label,label1,label2,vb1); //adding labels and vb1 to Vbox(vb)  
        
        //creating stackPane and adding its components
        StackPane stackPane2 = new StackPane();
        stackPane2.getChildren().addAll(imageView2,vb);
        
        borderPane.setCenter(stackPane2);//adding stackPane to center border 
        
      
       //creating scene
       Scene scene = new Scene(borderPane , 800 , 480);
       
       //setting primaryStage title and icon
        primaryStage.setTitle("Desserts Shop");
        FileInputStream inputstream3= new FileInputStream("C:\\Users\\mariam\\Downloads\\—Pngtree—cartoon dessert delicious cupcake_5780474.png"); 
        Image image3 = new Image(inputstream3); 
        primaryStage.getIcons().add(image3);
       return scene;
   }
    
    
    
}
