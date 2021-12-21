/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stagneography;

import Backend.Steganography;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import Backend.decode;
import static Backend.decode.DecodeTheMessage;
import static Backend.decode.b_msg;
import static Backend.decode.len;
import static Backend.decode.readImageFile;
import java.io.FileWriter;
import java.io.PrintWriter;
import Backend.Test;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class Home2Controller implements Initializable {

    @FXML
    private Button e_page;
    @FXML
    private Button settings;
    @FXML
    private Button imageChooser;
    @FXML
    private Button decrypt;
    @FXML
    private Button DestChooser;
   @FXML
   private TextArea message;
    @FXML
    public void enc(ActionEvent event)
    {
        try {
            new SceneBuilder().createScene("Home.fxml",event);
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
            imageChooser.setText(f.getAbsolutePath());
        }

    }
     @FXML
    public void DestChooser(ActionEvent event) throws Exception
    {
         FileChooser fc= new FileChooser();
          fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*.jpg"));
       File dest = fc.showSaveDialog(null);
    if (dest != null) {
        DestChooser.setText(dest.getAbsolutePath());        
        }
    }
      public static String b_msg="";
	      public static int len = 0;
    @FXML
    public void decrypt(ActionEvent event)
    {
        // String key;
             
        try {
             final String Key=WelcomeController.key;
        // System.out.println(Key);
        Backend.Test t = new Backend.Test(); 
        t.Decrypt(imageChooser.getText(),DestChooser.getText(),Key);
      
           BufferedImage yImage=decode.readImageFile(DestChooser.getText());
            System.out.println(DestChooser.getText());
            System.out.println(yImage);
            decode.DecodeTheMessage(yImage);
            String msg="";
            System.out.println("len is "+decode.len*8);
           for(int i=0;i<decode.len*8;i=i+8){
	
	String sub=decode.b_msg.substring(i,i+8);
	
	int m=Integer.parseInt(sub,2);
	char ch=(char) m;
	System.out.println("m "+m+" c "+ch);
	msg+=ch;
        }
      //  PrintWriter out = new PrintWriter(new FileWriter("C:\\Users\\Acer\\Desktop\\FY Project\\Code\\Demo\\message_dec.txt", true), true);
     //   out.write(msg);
       // out.close(); 
       message.setText(msg);
       Window owner = decrypt.getScene().getWindow();
        AlertView.showAlert(Alert.AlertType.INFORMATION, owner, "Decryption Successful!", 
                "Decryption done successfully. Please browse to your destination folder to view the image." );
        }
        catch (Exception e)
        {
            
            System.out.println(e);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
