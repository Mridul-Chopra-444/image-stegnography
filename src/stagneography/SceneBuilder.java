
package stagneography;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class SceneBuilder {
    
    
    public void  createScene(String nextScene , ActionEvent event) throws IOException
    {
         Parent NextParent = FXMLLoader.load(getClass().getResource(nextScene));
        Scene NextScene = new Scene(NextParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(NextScene);
        window.show();
    }
    
    
    
    
}
