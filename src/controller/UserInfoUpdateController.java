/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import utils.ConnectionDB;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class UserInfoUpdateController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField addUsername;

    @FXML
    private TextField addEmail;

    @FXML
    private TextField addMobile;

    @FXML
    private TextField addAddress;

    @FXML
    private Button btnUpdate;

    @FXML
    private TextField addLastName;

    @FXML
    private TextField addFirstName;
    
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    private int cusId;

    public void setCusId(String cusId) {
        this.cusId = Integer.parseInt(cusId);
    }

    public void setTextfield(String addFirstName, String addLastName, String addUsername, String addEmail, String addMobile, String addAddress){
        this.addFirstName.setText(addFirstName);
        this.addLastName.setText(addLastName);
        this.addUsername.setText(addUsername);
        this.addEmail.setText(addEmail);
        this.addMobile.setText(addMobile);
        this.addAddress.setText(addAddress);
    }
    
    @FXML
    void updateClicked(MouseEvent event) {
        try {
            String firstname = addFirstName.getText();
            String lastname = addLastName.getText();
            String email = addEmail.getText();
            String username = addUsername.getText();
            String mobile = addMobile.getText();
            String address = addAddress.getText();
            
            connection = ConnectionDB.conDB();
            
            String sql = "UPDATE `users` SET `firstname`= ?, `lastname`= ?, `email`= ?, `username`= ?, `mobile`= ?, `address`= ? WHERE `users`.`id` = " + cusId;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastname);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, username);
            preparedStatement.setString(5, mobile);
            preparedStatement.setString(6, address);          
            preparedStatement.execute();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Update Successful");
            alert.setContentText("Account Updated Successfully!");
            alert.setX(750);
            alert.setY(350);
            alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(UserInfoUpdateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
