package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class LoginSuccessScreenController implements Initializable {

    @FXML
    private Label labelUsername;
    
    @FXML
    private Button btnStartShopping;
    
    @FXML
    void startShopAction(ActionEvent event) throws IOException {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/home.fxml"));
            Parent root = loader.load();
            
            HomeController hc = loader.getController();
            hc.getAccountName(labelUsername.getText());
            
            Scene scene = new Scene(root);            
            Stage stage = (Stage)((Node)(event.getSource())).getScene().getWindow();
            stage.setScene(scene);
            stage.show();  
    }
    
    public void getUsername(String name){
           labelUsername.setText(name);
    }
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
}
