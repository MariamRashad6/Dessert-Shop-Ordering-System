
package dessertsshoporderingsystem;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/*
  class handling alert messages 
*/
public class AlertBox {
    
    //method to display alert box according to the handeled error
    //method takes title , message and header of alert box as parameter
    public  void display(String title , String message,String header){
        Alert alert = new Alert(Alert.AlertType.NONE,  message , ButtonType.OK);//creating an object of Alert class 
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(header);//setting header of Alert box 
        alert.setTitle(title);//setting title of alert box
        alert.show();  
    }
}
