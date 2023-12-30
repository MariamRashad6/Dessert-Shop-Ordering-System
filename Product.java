
package dessertsshoporderingsystem;

/*
  class for all products in ordering system having all setters and getters 
*/

public class Product {
    
    //class variables
    private double price;
    private int quantity;
    private String name;
    private String category;
   
    //constructors
    public Product(){
        
    }
    
    public Product(String name , int quantity, double price , String category){
        this.name=name;
        this.price=price;
        this.quantity=quantity;
        this.category=category;
        
    }
    public Product(String name , int quantity, String category){
        this.name=name;
        this.quantity=quantity;
        this.category=category;
        
    }

    //setters and getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }
    
    //overloading
    public void setPrice(String price){
        this.price=Double.parseDouble(price);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    
    
    
}
