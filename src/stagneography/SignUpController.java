/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stagneography;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Acer
 */
public class SignUpController implements Initializable {

    @FXML
    private TextField user;
    @FXML
    private PasswordField pass;
    @FXML
    private Button signup;
    @FXML
    private Button Login;
    @FXML
    private PasswordField cpass;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    


    @FXML
    private void SignUpAction(ActionEvent event) {
       
        try{
           
        int status = sql.SignUp(user.getText(),pass.getText(),Crypto.getKey());
            if(status==-1)
            {
              Window owner= signup.getScene().getWindow();
                AlertView.showAlert(Alert.AlertType.ERROR,owner,"Not Connected","Please Check your Internet Connectivity");
              System.out.println("Not Connected");   
            }
            else if(status==1){
                Window owner = signup.getScene().getWindow();
            AlertView.showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!", 
                "PLease Login to continue");  
             new SceneBuilder().createScene("Welcome.fxml",event);
             
            }
            else if(status==0){
                Window owner= signup.getScene().getWindow();
                AlertView.showAlert(Alert.AlertType.ERROR,owner,"Form Error!","UserName Already Exists.!!!");
              System.out.println("Sign Up Un-Successful");   
            }
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
//  sql.SignUp(user.getText(),pass.getText(),"123");
    }
    @FXML
    private void LoginPage(ActionEvent event)
    {
        try {
            new SceneBuilder().createScene("Welcome.fxml",event);
        } catch (IOException ex) {
          System.out.println(ex+"---------");
        }
    }
}
