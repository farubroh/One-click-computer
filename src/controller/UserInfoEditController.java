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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import utils.ConnectionDB;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class UserInfoEditController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField addLastName;

    @FXML
    private TextField addFirstName;

    @FXML
    private TextField addUsername;

    @FXML
    private PasswordField addPassword;

    @FXML
    private TextField addEmail;

    @FXML
    private TextField addMobile;

    @FXML
    private TextField addAddress;

    @FXML
    private Button btnSave;
    
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @FXML
    void saveClicked(MouseEvent event) {
        String firstname = addFirstName.getText();
        String lastname = addLastName.getText();
        String email = addEmail.getText();
        String username = addUsername.getText();
        String password = addPassword.getText();
        String mobile = addMobile.getText();
        String address = addAddress.getText();

        connection = ConnectionDB.conDB();

        if (firstname.isEmpty() || lastname.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty() || mobile.isEmpty() || address.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Warning!");
            alert.setContentText("Please, fill up all fields!");
            alert.setX(750);
            alert.setY(350);
            alert.showAndWait();
            return;
        }
            
            String sqlInsert = "INSERT INTO `users` (`firstname`, `lastname`, `email`, `username`, `password`, `mobile`, `address`) VALUES(?,?,?,?,MD5(?),?,?)";

            try {
                preparedStatement = connection.prepareStatement(sqlInsert);
                preparedStatement.setString(1, firstname);
                preparedStatement.setString(2, lastname);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, username);
                preparedStatement.setString(5, password);
                preparedStatement.setString(6, mobile);
                preparedStatement.setString(7, address);
                preparedStatement.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Inserted!");
                alert.setContentText("Information is updated Successfully!");
                alert.setX(750);
                alert.setY(350);
                alert.showAndWait();

                addFirstName.clear();
                addLastName.clear();
                addUsername.clear();
                addPassword.clear();
                addEmail.clear();
                addMobile.clear();
                addAddress.clear();

            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
