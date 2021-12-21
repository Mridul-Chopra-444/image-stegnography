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
 * @author HP
 */
public class Change_emailController implements Initializable {

    @FXML
    private Button cp;
    @FXML
    private Button da;
    @FXML
    private Button back;
    @FXML
    private Button logout;
    @FXML
    private Button submit;
    @FXML
    private Label status;
    @FXML
    private TextField email;
    @FXML
    private PasswordField pass;
    @FXML
    private TextField new_email;
    

   @FXML
    public void Back(ActionEvent event)
    {
        try {
            new SceneBuilder().createScene("Home.fxml",event);
        } catch (IOException ex) {
            Logger.getLogger(Home2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void Logout(ActionEvent event)
    {
        try {
            new SceneBuilder().createScene("Welcome.fxml",event);
        } catch (IOException ex) {
            Logger.getLogger(Home2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public void change_password(ActionEvent event)
    {
        try {
            new SceneBuilder().createScene("Settings.fxml",event);
        } catch (IOException ex) {
            Logger.getLogger(Home2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void change_email(ActionEvent event)
    {
        try {
            new SceneBuilder().createScene("change_email.fxml",event);
        } catch (IOException ex) {
            Logger.getLogger(Home2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void delete_account(ActionEvent event)
    {
        try {
            new SceneBuilder().createScene("delete_account.fxml",event);
        } catch (IOException ex) {
            Logger.getLogger(Home2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void SubmitAction()
    {
       this.status.setText("");
           try{
           int status=sql.ChangeEmail(email.getText(),pass.getText(),new_email.getText());
                    if(status==-1)
                    {   Window owner= submit.getScene().getWindow();
                        AlertView.showAlert(Alert.AlertType.ERROR,owner,"Connection Error","Please Check your Internet Connectivity.");
                    }
                    else if(status==0)
                    {
                        this.status.setText("Wrong credentials entered.");
                    }
                    else if(status==2)
                    {
                        this.status.setText("Account with this email alredy exists.Plese select a new one.");
                    }
                    
                    else if(status==1){
                        Window owner= submit.getScene().getWindow();
                        AlertView.showAlert(Alert.AlertType.CONFIRMATION, owner, "Email Change Successful!", 
                "Email changed successfully " );
                    }
           }
           catch(Exception e)
           {System.out.println(e);}
           
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
