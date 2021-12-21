/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stagneography;

import static java.awt.SystemColor.window;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import java.nio.file.Files;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import Backend.Steganography;
import java.io.FileNotFoundException;
import Backend.Test;
import java.security.Key;
import java.sql.SQLException;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class HomeController implements Initializable {

    @FXML
    private Button d_page;
    @FXML
    private Button settings;
    @FXML
    private Button imageChooser;
    @FXML
    private Button DestChooser;
    @FXML
    public  TextArea messageText;
    @FXML
    private Button encrypt;
    @FXML
    private TextField email;
    private String key1;
    private String source_path="";
    private String dest_path="";
    private String content="";
    
    public void dec(ActionEvent event)
    {
        try {
            new SceneBuilder().createScene("Home2.fxml",event);
        } catch (IOException ex) {
            Logger.getLogger(Home2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public void Settings(ActionEvent event)
    {
        try {
            new SceneBuilder().createScene("Settings.fxml",event);
        } catch (IOException ex) {
            Logger.getLogger(Home2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void imageChooser(ActionEvent event)
    {
        FileChooser fc= new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*.jpg"));
        File f= fc.showOpenDialog(null);
       if(f !=null)
        {
            source_path=f.getAbsolutePath();
            imageChooser.setText(source_path);
        }

    }
     @FXML
    public void DestChooser(ActionEvent event) throws Exception
    {
         FileChooser fc= new FileChooser();
          fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*.jpg"));
       File dest = fc.showSaveDialog(null);
    if (dest != null) {
        dest_path=dest.getAbsolutePath();
        DestChooser.setText(dest_path);
}   

    }
    @FXML
    public void encrypt(ActionEvent event)
    {
       // String key;
        try
        {
      String  status = sql.RecvEmail(email.getText());
        if(status.equals("-1"))
       {
           Window owner= encrypt.getScene().getWindow();
                AlertView.showAlert(Alert.AlertType.ERROR,owner,"Not Connected","Please Check your Internet Connectivity");
              System.out.println("Not Connected");   
              return;
       }
       else if(status.equals("2"))
       {
           Window owner= encrypt.getScene().getWindow();
                AlertView.showAlert(Alert.AlertType.ERROR,owner,"Wrong user","Please Enter Correct Email!");
              System.out.println("Wrong email entered.");   
              return;
       }
        else
       {
            this.key1= status;
            
           System.out.println("Running Encrypt"+"key:"+key1);
        try {
            this.content=Steganography.readMessageFile(messageText.getText());
        //    System.out.print(messageText.getText());
        int[] bits=Steganography.bit_Msg(content);
        System.out.println("msg in file "+content);
        for(int i=0;i<bits.length;i++)
        System.out.print(bits[i]);
        System.out.println();
        BufferedImage theImage=Steganography.readImageFile(imageChooser.getText());
        Steganography.hideTheMessage(bits, theImage,DestChooser.getText());
       final String Key=key1;
        Backend.Test t = new Backend.Test();
       t.Encrypt(DestChooser.getText(), DestChooser.getText(),Key);
		// Input file, Output file, key
	
        } catch (Exception ex) {
            System.out.print(ex);
        }
       }
        
        // Encryption done alert is here
        Window owner = encrypt.getScene().getWindow();
        AlertView.showAlert(Alert.AlertType.INFORMATION, owner, "Encryption Successful!", 
                "Encryption done successfully. Please browse to your destination folder to view the image." );
        
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        
    }
    
    @FXML
    public void EncryptAction(ActionEvent event)
    {
        
        if(email.getText().equals("")| email.getText()==null)
        {
                Window owner = encrypt.getScene().getWindow();
        AlertView.showAlert(Alert.AlertType.INFORMATION, owner, "Email not entered", 
                "Enter receiver email." );
        
        }
        
        else if(source_path.equals("")  )
        {
            Window owner = encrypt.getScene().getWindow();
        AlertView.showAlert(Alert.AlertType.INFORMATION, owner, "Selection Error", 
                "Please select an image first." );
        }
        else if(dest_path.equals(""))
            {
            Window owner = encrypt.getScene().getWindow();
        AlertView.showAlert(Alert.AlertType.INFORMATION, owner, "Selection Error", 
                "Please select a storage location for encypted image." );
        }
        else if(content.equals(""))
            {
            Window owner = encrypt.getScene().getWindow();
        int selection = AlertView.showAlert(Alert.AlertType.CONFIRMATION, owner, "Empty Message", 
                "Are you sure you want to send an empty message ?" );
        System.out.println(selection);
                            if(selection==0)
                            {return;}
                            else if(selection==1)
                            {encrypt(event);}
        
        }
        else{
            encrypt(event);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    
    
}
