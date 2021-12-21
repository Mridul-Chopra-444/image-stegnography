/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stagneography;
// demo git
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class WelcomeController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private PasswordField pass;
    @FXML
    private Button login;
    @FXML
    private Button SignUp;
    

    public static String key;
    
   @FXML
    public void LoginAction(ActionEvent event) 
    {
        try{
       int status = sql.Login(email.getText(),pass.getText());
       if(status==-1)
       {
           Window owner= login.getScene().getWindow();
                AlertView.showAlert(Alert.AlertType.ERROR,owner,"Not Connected","Please Check your Internet Connectivity");
              System.out.println("Not Connected");   
       }
       else if(status ==0)
       {
           Window owner= login.getScene().getWindow();
          
         AlertView.showAlert(Alert.AlertType.ERROR,owner,"Login Error","Please Check your user name or password");
           
           
       }
       else if(status ==1)
       {
          new SceneBuilder().createScene("Home.fxml",event);
          key=sql.key;
       }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
   
   
   public void SignUp(ActionEvent e) throws IOException{
       new SceneBuilder().createScene("SignUp.fxml",e);
       
   }
   
   
   
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    
    
}
