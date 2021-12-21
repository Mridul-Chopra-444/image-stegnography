/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stagneography;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Window;

/**
 *
 * @author Acer
 */
public class AlertView {
      public static int showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
         //alert.show();
         
         Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == null) 
         return -1;
         else if (option.get() == ButtonType.CANCEL) 
         return 0;
         else if (option.get() == ButtonType.OK)
             return 1;
         else return 4;
 
    
   }
        
    
}
