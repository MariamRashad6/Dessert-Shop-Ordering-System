
package dessertsshoporderingsystem;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Manager extends User {
      
    //JDBC variables
    private Statement statement;
    private ResultSet resultSet;
    private Connection connection;
    
    //Table view Variables
    private ObservableList<Product> data;
    private TableView<Product> table;
    private Product product;
   
    private static String userType = "manager";

    public static String getUserType() {
        return userType;
    }

    public static void setUserType(String userType) {
        Manager.userType = userType;
    }
    
   
    
    
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
       primaryStage.setScene(ManagerProductScene(primaryStage));
       primaryStage.show();
    }
    
    
    
    //manager main scene
    public Scene ManagerProductScene(Stage primaryStage) throws FileNotFoundException{
        
        
        //creating borderpane
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: #b4dcdd");
        
        //creating load button and handling its event
        Button load = new Button("Load");
        load.setStyle("-fx-text-fill: lightseagreen; -fx-font-size: 20; -fx-background-radius: 6;");
        load.setOnAction(e->loadBtnAction());
        
        //creating table view
        data = FXCollections.observableArrayList(); 
        table = new TableView<>();
        
        //creating columns
        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setMinWidth(100);
        
        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        price.setMinWidth(100);
        
        TableColumn quantity = new TableColumn("Quantity");
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        quantity.setMinWidth(100);
        
        TableColumn category = new TableColumn("Category");
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        category.setMinWidth(100);

        table.getColumns().addAll( name, price , quantity,category);
        
        table.setItems(data);
        
        
        //adding table to right border
        VBox vb = new VBox();
        vb.setPadding(new Insets(20,100,10,10));
        vb.getChildren().add(table);
        borderPane.setRight(vb);
        
        //creating labels and textfields
        GridPane gridpane = new GridPane();
        
        //name textfield
        Label productNameLabel = new Label("Product Name: ");
        TextField productNameTextField = new TextField();
        productNameLabel.setStyle("-fx-font-size: 14pt;");
        productNameTextField.setPromptText("Enter product name");
        gridpane.setPadding(new Insets(10, 12.5, 13.5, 50));
        gridpane.setHgap(5.5);
        gridpane.setVgap(8);
        gridpane.add(productNameLabel,2,2);
        gridpane.add(productNameTextField,2,3);
        
        //price textfield
        Label productPriceLabel = new Label("Product Price: ");
        productPriceLabel.setStyle("-fx-font-size: 14pt;");
        TextField productPriceTextField = new TextField();
        productPriceTextField.setPromptText("Enter product price");
        gridpane.add(productPriceLabel,2,5);
        gridpane.add(productPriceTextField,2,6);
        
        //quantity textfield
        Label productQuantityLabel = new Label("Product Quantity: ");
        productQuantityLabel.setStyle("-fx-font-size: 14pt;");
        TextField productQuantityTextField = new TextField();
        productQuantityTextField.setPromptText("Enter product quantity");
        gridpane.add(productQuantityLabel,2,9);
        gridpane.add(productQuantityTextField,2,10);
        
        //category textfield
        Label productCategoryLabel = new Label("Product Category: ");
        productCategoryLabel.setStyle("-fx-font-size: 14pt;");
        TextField productCategoryTextField = new TextField();
        productCategoryTextField.setPromptText("Enter product category");
        gridpane.add(productCategoryLabel,2,13);
        gridpane.add(productCategoryTextField,2,14);
        
        
        
        
        //creating add button and handling its event
        Button addBtn = new Button("Add");
        addBtn.setStyle("-fx-text-fill: lightseagreen; -fx-font-size: 20; -fx-background-radius: 6;");
        addBtn.setOnAction(e->addBtnAction(productNameTextField ,productPriceTextField ,productQuantityTextField,productCategoryTextField));
        
        //adding load and add button to hbox
        HBox hbox = new HBox(50);
        hbox.setPadding(new Insets(20,10,10,100));
        hbox.getChildren().addAll(load,addBtn);
        
        //creting edit button and handling its event
        Button editBtn = new Button("Edit");
        editBtn.setStyle("-fx-text-fill: lightseagreen; -fx-font-size: 20; -fx-background-radius: 6;");
        editBtn.setOnAction(e->{
            editBtnAction(productNameTextField ,productPriceTextField ,productQuantityTextField,productCategoryTextField);
            
                });
        
        
        //adding edit and back button to hbox
        HBox hbox1 = new HBox(50);
        hbox1.setPadding(new Insets(10,10,10,50));
        
        
        //creating delete button and handling its event
        Button deleteBtn = new Button("Delete");
        deleteBtn.setStyle("-fx-text-fill: lightseagreen; -fx-font-size: 20; -fx-background-radius: 6;");
        deleteBtn.setOnAction(e->{
           
            deleteBtnAction(productNameTextField ,productPriceTextField ,productQuantityTextField);
                });
        
        //creating logout button and handling its event
        Button logOutBtn = new Button("Logout");
        logOutBtn.setStyle("-fx-text-fill: lightseagreen; -fx-font-size: 20; -fx-background-radius: 6;");
        logOutBtn.setOnAction(e->
                {
            try {
                new User().start(primaryStage);
            } catch (FileNotFoundException ex) {
               System.out.println("logOut button error");
            } catch (Exception ex) {
                System.out.println("logOut button error");
            }
                });
    
        
        //adding logout and delete button to hbox
        hbox1.getChildren().addAll(editBtn,deleteBtn,logOutBtn);
        
        
        //setting left border
        VBox vb2 = new VBox();
        vb2.setPadding(new Insets(10,100,100,10));
        vb2.getChildren().addAll(gridpane,hbox,hbox1);
        borderPane.setLeft(vb2);
        
        //adding scene
        Scene scene = new Scene(borderPane , 1000 , 500);
        
        //setting primaryStage title and icon
        primaryStage.setTitle("Desserts Shop");
        FileInputStream inputstream = new FileInputStream("C:\\Users\\mariam\\Downloads\\—Pngtree—cartoon dessert delicious cupcake_5780474.png"); 
        Image image = new Image(inputstream); 
        primaryStage.getIcons().add(image);
        
        return scene;
    }
    
    //load button event handling
    //access database and views data in table
    public void loadBtnAction(){
        try {
            connectToDataBase();
            //create statement
            statement = connection.createStatement();
            
            //excute query
            resultSet = statement.executeQuery("select * from products");//DQL
            while(resultSet.next()){
                    data.add(new Product(
                            resultSet.getString("pname"),
                            resultSet.getInt("quantity"),
                            resultSet.getDouble("price"),
                            resultSet.getString("category")
                            
                    ));
                    table.setItems(data);
                }
                statement.close();
                connection.close();
            }
         catch (SQLException ex) {
            System.out.println("Exception");
            
        }
        refreshTable();
    }
    
    //method to refresh table (updating table with any new changes)
    public  void refreshTable(){
        
        data.clear();
	connectToDataBase();
    
       
        try {
            //create statement
            statement = connection.createStatement();
            
            //excute query
            resultSet = statement.executeQuery("select * from products");//DQL
            while(resultSet.next()){
                    data.add(new Product(
                            resultSet.getString("pname"),
                            resultSet.getInt("quantity"),
                            resultSet.getDouble("price"),
                            resultSet.getString("category")
                            
                    ));
                    table.setItems(data);
                }
                statement.close();
                connection.close();
            }
         catch (SQLException ex) {
            System.out.println("Exception");
            
        }
        
        }
    
    //add button event handling
    //method to add new products in database
    public void addBtnAction(TextField productNameTextField , TextField productPriceTextField , TextField productQuantityTextField ,TextField productCategoryTextField){
        if("".equals(productNameTextField.getText()) || "".equals(productPriceTextField.getText()) || "".equals(productQuantityTextField.getText()) || "".equals(productCategoryTextField.getText())){
           new AlertBox().display("Products","Please fill all the blanks" ,"Can't continue");
       }
       else{
        connectToDataBase();
        try {
            
             //create statement
            statement = connection.createStatement();
            
            //excute query
 
            product = new Product(productNameTextField.getText(),Integer.parseInt(productQuantityTextField.getText()),productCategoryTextField.getText());
            product.setPrice(productPriceTextField.getText());//overloading
            System.out.println(product.getName());
            System.out.println(product.getPrice());
            System.out.println(product.getQuantity());
            System.out.println(product.getCategory());
            
            //create statement
            PreparedStatement preparedStatement = connection.prepareStatement(" insert into products(pname,quantity,price,category)"+ " values (?, ?, ?,?)");//prepared statement
        
        //excute query
        preparedStatement.setString(1, product.getName());
        preparedStatement.setInt(2, product.getQuantity());
        preparedStatement.setDouble(3, product.getPrice());
        preparedStatement.setString(4, product.getCategory());
        preparedStatement.execute();//DML                
        productNameTextField.clear();
        productPriceTextField.clear();
        productQuantityTextField.clear();
            statement.close();
            connection.close();
                    
                } catch (SQLException ex) {
               System.out.println("Exception");
            }
        refreshTable();
        }
        }
    
    //delete button event handling
    //method to delete a product from textfield just by selecting row from table
    public void deleteBtnAction( TextField productNameTextField , TextField productPriceTextField , TextField productQuantityTextField ){
        
        connectToDataBase();
        
        try {
            
             //create statement
            statement = connection.createStatement();
            
            //excute query
       
            //listening to table selection
            String pname = table.getSelectionModel().getSelectedItem().getName();
            
            
            //create statement
            statement.execute("delete from products where pname = '"+pname+"'");
        
           //clearing text fields        
           productNameTextField.clear();
           productPriceTextField.clear();
           productQuantityTextField.clear();
            statement.close();
            connection.close();
                    
                } catch (SQLException ex) {
               System.out.println("Exception");
            }
        refreshTable();
        }
    
    //edit button event handling
    //method to edit an existing product by selecting row from table view and updating the wanted infromation
    public void editBtnAction(TextField productNameTextField , TextField productPriceTextField , TextField productQuantityTextField,TextField productCategoryTextField){
        
       //handling table selection
       selection(productNameTextField ,productPriceTextField ,productQuantityTextField,productCategoryTextField);
       //handling blank textfields
       if("".equals(productNameTextField.getText()) || "".equals(productPriceTextField.getText()) || "".equals(productQuantityTextField.getText()) || "".equals(productCategoryTextField.getText())){
           new AlertBox().display("Products","Please fill all the blanks" ,"Can't continue");
       }
       else{
       connectToDataBase();
        try {
        product = new Product(productNameTextField.getText(),Integer.parseInt(productQuantityTextField.getText()),Double.parseDouble(productPriceTextField.getText()),productCategoryTextField.getText());
            System.out.println(product.getName());
            System.out.println(product.getPrice());
            System.out.println(product.getQuantity());
            System.out.println(product.getCategory());
    
            //create statement
            PreparedStatement preparedStatement = connection.prepareStatement(" update  products set pname = ? , quantity = ? , price = ? , category = ? where pname = '"+productNameTextField.getText()+"'");//prepared statement
        
           //excute query
           preparedStatement.setString(1, product.getName());
           preparedStatement.setInt(2, product.getQuantity());
           preparedStatement.setDouble(3, product.getPrice());
           preparedStatement.setString(4, product.getCategory());
           preparedStatement.execute();//DML                
           productNameTextField.clear();
           productPriceTextField.clear();
           productQuantityTextField.clear();
           productCategoryTextField.clear();
            statement.close();
            connection.close();
                    
                } catch (SQLException ex) {
               System.out.println("Exception");
            }
        
        refreshTable();
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
    
    
    //handling table mouse selections
    //alows user to perform some actions by selecting row of wanted product using mouse
    public void selection(TextField productNameTextField , TextField productPriceTextField , TextField productQuantityTextField,TextField productCategoryTextField){
            String pname = table.getSelectionModel().getSelectedItem().getName();
            productNameTextField.setText(pname);
            
            Double price = table.getSelectionModel().getSelectedItem().getPrice();
            productPriceTextField.setText(String.valueOf(price));
            
            int quantity = table.getSelectionModel().getSelectedItem().getQuantity();
            productQuantityTextField.setText(String.valueOf(quantity));
            
            
    }
    
   
}

