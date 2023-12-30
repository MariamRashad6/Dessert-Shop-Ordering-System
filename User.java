
package dessertsshoporderingsystem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
  User class handles main scene common between all users handling sign in 
  and sign up implementing inteface person as it is a parent class for customer 
  and manager classes 
*/

public  class User extends Application implements Person {

    //class variables
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String address;
    private String mobileNumber;
    private Customer customer;
    private Manager manager;
   
    
     
    //JDBC variables
    private Statement statement;
    private ResultSet resultSet;
    
    
    
    //
    private ComboBox<String> comboBox;
    
    //constructors
    public User(){
        
    } 
    public User(String userName, String password , String address , String mobileNumber , String firstName , String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.userName = userName;
        this.password = password;
    }
    
    //getters and setters
    @Override
    public String getFirstName() {
        return firstName;
    }
    
    
    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

   

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getMobileNumber() {
        return mobileNumber;
    }

    @Override
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
    
    
    public static void main(String[] args) {
        launch(args);//goes to start
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(mainScene(primaryStage));
        primaryStage.show();
        
    }
    

    
    //main scene method
    public Scene mainScene(Stage primaryStage) throws FileNotFoundException {
        
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
       
        //adding stack pane to the left border
        borderPane.setLeft(stackPane);
        
        //setting center border pane
        //creating Hbox
        HBox hb = new HBox(35);
        AnchorPane anchorPane = new AnchorPane();
        AnchorPane.setLeftAnchor(hb, 150.0);
        AnchorPane.setRightAnchor(hb, 35.0);
        AnchorPane.setTopAnchor(hb, 150.0);
        AnchorPane.setBottomAnchor(hb, 20.0);
        
        //creating buttons
        //Sign in button creation and event handling
        Button signInBtn = new Button("Sign in");
        signInBtn.setStyle("-fx-text-fill: lightseagreen; -fx-font-size: 20; -fx-background-radius: 6;");
        signInBtn.setOnAction(e->{
            try {
                primaryStage.setScene(signInScene(primaryStage));
            } catch (FileNotFoundException ex) {
               System.out.println("Exception");
            }
        });
        
        //Sign up button creation and event handling
        Button signUpBtn = new Button("Sign up");
        signUpBtn.setStyle("-fx-text-fill: lightseagreen; -fx-font-size: 20; -fx-background-radius: 6;");
        signUpBtn.setOnAction(e->{
            try {
                primaryStage.setScene(signUpScene(primaryStage));
            } catch (FileNotFoundException ex) {
               System.out.println("Exception");
            }
        });
        
        //Add buttons to hbox
        hb.getChildren().addAll(signInBtn,signUpBtn);
        
        anchorPane.getChildren().add(hb);//adding hbox to anchor pane 
        
       
        
        
        //setting bottom border pane
        HBox hb2= new HBox();
        hb2.setPadding(new Insets(15,10,10,215));//top, ,  ,right
        
        //creating buttons
     
        //Close up button creation and event handling
        Button closeBtn = new Button("Close");
        closeBtn.setStyle("-fx-text-fill: lightseagreen; -fx-font-size: 20; -fx-background-radius: 6;");
        closeBtn.setOnAction((ActionEvent event) -> {
                  Platform.exit();
             });
        //Add buttons to hbox
        hb2.getChildren().addAll(closeBtn);
        
        VBox vb = new VBox(35);
        vb.getChildren().addAll(anchorPane,hb2);
        vb.setStyle("-fx-background-color: #b4dcdd");
        borderPane.setCenter(vb);//adding anchorPane to center border 
        
      
        //creating scene
        Scene scene = new Scene(borderPane , 800 , 480);
        
        //setting primaryStage title and icon
        primaryStage.setTitle("Desserts Shop");
        FileInputStream inputstream2= new FileInputStream("C:\\Users\\mariam\\Downloads\\—Pngtree—cartoon dessert delicious cupcake_5780474.png"); 
        Image image2 = new Image(inputstream2); 
        primaryStage.getIcons().add(image2);
       
        return scene;
    }
    
    //sign in scene
    public Scene signInScene(Stage primaryStage) throws FileNotFoundException {
        
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
       
        //adding stack pane to the left border
        borderPane.setLeft(stackPane);
        
        //setting center border pane
        GridPane gridpane = new GridPane();
        
        //combobox
        comboBox = new ComboBox<>();
        comboBox.getItems().addAll(
                "Customer",
                "Manager"
                
        );
        gridpane.add(comboBox,2,0);
        comboBox.setPromptText("-Select-");
        
        //username and password label and text fields
        Label usernameLabel = new Label("Username: ");
        TextField usernameTextField = new TextField();
        usernameLabel.setStyle("-fx-font-size: 14pt;");
        usernameTextField.setPromptText("Enter your username");
        gridpane.setPadding(new Insets(100, 12.5, 13.5, 150));
        gridpane.setHgap(5.5);
        gridpane.setVgap(8);
        gridpane.add(usernameLabel,2,2);
        gridpane.add(usernameTextField,2,3);
        
        Label passwordLabel = new Label("Password: ");
        passwordLabel.setStyle("-fx-font-size: 14pt;");
        PasswordField passwordTextField = new PasswordField();
        passwordTextField.setPromptText("Enter your password");
        gridpane.add(passwordLabel,2,5);
        gridpane.add(passwordTextField,2,6);
        
        //buttons
        HBox hb = new HBox(40);
        //Log in button
        Button logInBtn = new Button("Login");
        logInBtn.setStyle("-fx-text-fill: lightseagreen; -fx-font-size: 20; -fx-background-radius: 6;");
        logInBtn.setOnAction(e->{
            try {
                loginBtn(primaryStage,usernameTextField,passwordTextField);
                System.out.println(customer.getUserName());
            } catch (Exception ex) {
                System.out.println("Login button error");
            }
            
        });
        
        //Reset button
        Button resetBtn = new Button("Reset");
        resetBtn.setStyle("-fx-text-fill: lightseagreen; -fx-font-size: 20; -fx-background-radius: 6;");
        resetBtn.setOnAction(e->{
            usernameTextField.clear();
            passwordTextField.clear();
            
        });
        
        //Add buttons to hbox
        hb.getChildren().addAll(logInBtn,resetBtn);
        gridpane.add(hb,2,11);
        
        HBox hb2 = new HBox(10);
        Button backBtn = new Button("Back");
        backBtn.setStyle("-fx-text-fill: lightseagreen; -fx-font-size: 20; -fx-background-radius: 6;");
        backBtn.setOnAction(e->{
            try {
                primaryStage.setScene(mainScene(primaryStage));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        hb2.setPadding(new Insets(10,5,5,230));
        hb2.getChildren().add(backBtn);
        
        
        VBox vb = new VBox(35);
        vb.setStyle("-fx-background-color: #b4dcdd");
        vb.getChildren().addAll(gridpane,hb2);
        borderPane.setCenter(vb);//adding anchorPane to right border 
        
      
        //creating scene
        Scene scene = new Scene(borderPane , 800 , 480);
        
        //setting primaryStage title and icon
        primaryStage.setTitle("Desserts Shop");
        FileInputStream inputstream2= new FileInputStream("C:\\Users\\mariam\\Downloads\\—Pngtree—cartoon dessert delicious cupcake_5780474.png"); 
        Image image2 = new Image(inputstream2); 
        primaryStage.getIcons().add(image2);
       
        return scene;
    }
    
    
    //sign up
    public Scene signUpScene(Stage primaryStage) throws FileNotFoundException {
        
        //Border pane as the root for scene
        BorderPane borderPane = new BorderPane();
        
        
        //setting left border pane
        VBox vb1 = new VBox(10);
        vb1.setPadding(new Insets(50,10,10,150));
        vb1.setStyle("-fx-background-color: #b4dcdd");
        
        Label firstNameLabel = new Label("First Name: ");
        firstNameLabel.setStyle("-fx-font-size: 14pt;");
        TextField firstNameTextField = new TextField();
        firstNameTextField.setPromptText("Enter your first name");
        vb1.getChildren().addAll(firstNameLabel,firstNameTextField);
        
        Label lastNameLabel = new Label("Last Name: ");
        lastNameLabel.setStyle("-fx-font-size: 14pt;");
        TextField lastNameTextField = new TextField();
        lastNameTextField.setPromptText("Enter your last name");
        vb1.getChildren().addAll(lastNameLabel,lastNameTextField);
        
        Label addressLabel = new Label("Address: ");
        addressLabel.setStyle("-fx-font-size: 14pt;");
        TextField addressTextField = new TextField();
        addressTextField.setPromptText("Enter your address");
        vb1.getChildren().addAll(addressLabel,addressTextField);
        
        
        
        //adding vbox to the left border
        borderPane.setLeft(vb1);
        
        
        //setting center border
        VBox vb2 = new VBox(10);
        vb2.setPadding(new Insets(50,150,10,10));
        vb2.setStyle("-fx-background-color: #b4dcdd");
        
        Label usernameLabel = new Label("Username: ");
        usernameLabel.setStyle("-fx-font-size: 14pt;");
        TextField usernameTextField = new TextField();
        usernameTextField.setPromptText("Enter your username");
        vb2.getChildren().addAll(usernameLabel,usernameTextField);
        
        Label passwordLabel = new Label("Password: ");
        passwordLabel.setStyle("-fx-font-size: 14pt;");
        PasswordField passwordTextField = new PasswordField();
        passwordTextField.setPromptText("Enter your password");
        vb2.getChildren().addAll(passwordLabel,passwordTextField);
        
        Label mobileNumberLabel = new Label("Mobile Number: ");
        mobileNumberLabel.setStyle("-fx-font-size: 14pt;");
        TextField mobileNumberTextField = new TextField();
        mobileNumberTextField.setPromptText("Enter your mobile number");
        vb2.getChildren().addAll(mobileNumberLabel,mobileNumberTextField);
        
        
        
        borderPane.setRight(vb2);
        
        VBox vb3 = new VBox();
        vb3.setStyle("-fx-background-color: #b4dcdd");
        borderPane.setCenter(vb3);
        
        
        //Buttons 
        HBox hb = new HBox(150);
        hb.setPadding(new Insets(50,10,300,250));
        hb.setStyle("-fx-background-color: #b4dcdd");
        
        //Sign up button
        Button signUpBtn = new Button("Sign up");
        signUpBtn.setStyle("-fx-text-fill: lightseagreen; -fx-font-size: 20; -fx-background-radius: 6;");
        signUpBtn.setOnAction(e->{
            try {
                signUpBtnAction(primaryStage,firstNameTextField,lastNameTextField,addressTextField,usernameTextField,passwordTextField,mobileNumberTextField);
            } catch (FileNotFoundException ex) {
                System.out.println("Sign up button Exception");
            }
        });
        
        
        
        
        hb.getChildren().addAll(signUpBtn);
        
        
        //back button
        Button backBtn = new Button("Back");
        backBtn.setStyle("-fx-text-fill: lightseagreen; -fx-font-size: 20; -fx-background-radius: 6;");
        backBtn.setOnAction(e->{
            try {
                primaryStage.setScene(mainScene(primaryStage));
            } catch (FileNotFoundException ex) {
                System.out.println("back button exception");
            }
        });
        hb.getChildren().addAll(backBtn);
        
        borderPane.setBottom(hb);
        
        
        //creating scene
        Scene scene = new Scene(borderPane , 800 , 480);
        
        //setting primaryStage title and icon
        primaryStage.setTitle("Desserts Shop");
        FileInputStream inputstream2= new FileInputStream("C:\\Users\\mariam\\Downloads\\—Pngtree—cartoon dessert delicious cupcake_5780474.png"); 
        Image image2 = new Image(inputstream2); 
        primaryStage.getIcons().add(image2);
       
        return scene;
    }
    
    //login button event handling
    //this method validiates user and view suitable scene to him handling blank textfields, wrong usertype , username , password
    public void loginBtn(Stage primaryStage,TextField userNameTextField , TextField passwordTextField) throws Exception{
        String loginStatus = comboBox.getValue();
        customer = new Customer();
        manager = new Manager();
        
        boolean found = false;
        
        //customer
        if("Customer".equals(loginStatus)){
            customer.setUserName(userNameTextField.getText());
            customer.setPassword(passwordTextField.getText());
            //checking for password and username and validating them
            
            //handling blank textfields
            if(customer.getUserName().equals("") || customer.getPassword().equals("")){
                System.out.println("No Input");
                new AlertBox().display("Login","Please fill all the blanks" ,"Can't Login");
            }
            else{
            
            statement = Client.c.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM users ");
            while(resultSet.next()){
                //checking for username and usertype before excuting the query
                if(customer.getUserName().equals(resultSet.getString("username")) && "customer".equals(resultSet.getString("usertype"))){
                    found = true;
                    resultSet = statement.executeQuery("SELECT * FROM users WHERE username='"+customer.getUserName()+"'");
                    resultSet.next();
                   if(resultSet.getString("password").equalsIgnoreCase(customer.getPassword())){
                System.out.println("Found");
                customer.start(primaryStage);
                
            }
                   else{
                       new AlertBox().display("Login","Invalid Password" ,"Can't Login");
                   }
                }
               
            }
            if(found == false){
                       new AlertBox().display("Login","Invalid" ,"Can't Login");
                   
            }
            //clearing textfields
            userNameTextField.clear();
            passwordTextField.clear();
        }
        }
        
        //manager
        else if("Manager".equals(loginStatus)){
           boolean isfound = false;
           manager.setUserName(userNameTextField.getText());
           manager.setPassword(passwordTextField.getText());
           
           //checking for password and username and validating them
           
           //handling blank text fields
           if(manager.getUserName().equals("") || manager.getPassword().equals("")){
                System.out.println("No Input");
                new AlertBox().display("Login","Please fill all the blanks" ,"Can't Login");
            }
           else{
            
            statement = Client.c.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM users ");
            while(resultSet.next()){
                //checking for username and usertype before excuting the query
                if(manager.getUserName().equals(resultSet.getString("username"))&& "manager".equals(resultSet.getString("usertype"))){
                    isfound = true;
                    resultSet = statement.executeQuery("SELECT password FROM users WHERE username='"+manager.getUserName()+"'");
                    resultSet.next();
                   if(resultSet.getString("password").equalsIgnoreCase(manager.getPassword())){
                System.out.println("Found");
                new Manager().start(primaryStage);   
            }
                   else{
                       new AlertBox().display("Login","Invalid Password" ,"Can't Login");
                   }
                }
            }
            if(isfound == false){
                       new AlertBox().display("Login","Invalid" ,"Can't Login");
                   
            }
            //clearing textfields
            userNameTextField.clear();
            passwordTextField.clear();
        }
           
        }
        else{
            System.out.println("");
        }
    }
    
    //sign up button event handling
    //takes information of new customer checking that username is unique and saves it in database
    public void signUpBtnAction(Stage primaryStage,TextField firstNameTextField,TextField lastNameTextField,TextField addressTextField,TextField usernameTextField,TextField passwordTextField,TextField mobileNumberTextField) throws FileNotFoundException{
        
        customer = new Customer();
        //handling blank textfields
        if("".equals(firstNameTextField.getText()) || "".equals(lastNameTextField.getText()) || "".equals(addressTextField.getText()) || "".equals(usernameTextField.getText()) || "".equals(passwordTextField.getText()) || "".equals(mobileNumberTextField.getText())){
            new AlertBox().display("Sign up","Please fill the blanks" ,"Can't sign up");
            primaryStage.setScene(signUpScene(primaryStage));
            primaryStage.show();
        }
      
        else{
            boolean usernameFound = false;
        try {
            
            //handling existing username
            try {
            statement = Client.c.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM users ");
            while(resultSet.next()){
                //checking if username exists
                if(usernameTextField.getText().equals(resultSet.getString("username"))){  
                usernameFound = true;
                System.out.println("Found");
                
                   }
                }      
            }
            
            catch(SQLException ex){
                    System.out.println("Excpetion in first query");
                    }
            if(usernameFound == true){
                new AlertBox().display("Sign up","username already exists" ,"Can't sign up");
                usernameTextField.clear();//clear username text field
            }
            else{
            //create statement
            statement = Client.c.createStatement();
            
            //excute query
 
            //creating new customer
            customer = new Customer(usernameTextField.getText(),passwordTextField.getText(),addressTextField.getText(),mobileNumberTextField.getText(),firstNameTextField.getText(),lastNameTextField.getText());
            System.out.println(customer.getAddress());
            System.out.println(customer.getFirstName());
            System.out.println(customer.getLastName());
            System.out.println(customer.getMobileNumber());
            System.out.println(customer.getPassword());
            System.out.println(customer.getUserName());
            
            //create statement
            //query to insert new customer information in database
            PreparedStatement preparedStatement = Client.c.prepareStatement(" insert into users(user_first_name,user_last_name,username,password,usertype,mobile_phone,address)"+ " values (?, ?, ?,?,?,?,?)");//prepared statement
        
        //excute query
        preparedStatement.setString(1, customer.getFirstName());
        preparedStatement.setString(2, customer.getLastName());
        preparedStatement.setString(3, customer.getUserName());
        preparedStatement.setString(4, customer.getPassword());
        preparedStatement.setString(5, Customer.getUserType());
        preparedStatement.setString(6, customer.getMobileNumber());
        preparedStatement.setString(7, customer.getAddress());
        preparedStatement.execute();
       
        //clearing text fields
        usernameTextField.clear();
        passwordTextField.clear();
        addressTextField.clear();
        mobileNumberTextField.clear();
        firstNameTextField.clear();
        lastNameTextField.clear();
         
            //closing connection
            statement.close();
            Client.c.close();
             try {
            customer.start(primaryStage);
        } catch (Exception ex) {
            System.out.println("Sign up button exception");
        }
            }        
                } catch (SQLException ex) {
               System.out.println("Exception");
            }
        
       
        }
        
    
    }
    
    
        
   
}
