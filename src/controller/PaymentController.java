/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.sun.glass.events.WindowEvent;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.ConnectionDB;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class PaymentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ImageView showPic;

    @FXML
    private Label showId;

    @FXML
    private Label showName;

    @FXML
    private Label showPrice;

    @FXML
    private Label showSubTotal;

    @FXML
    private Button btnConfirm;

    @FXML
    private ComboBox<String> quantityBox;

    @FXML
    private TextField bkashNo;

    @FXML
    private PasswordField bkashPin;

    @FXML
    private Button btnCheckout;

    @FXML
    private Label showUsername;

    ObservableList ChoiceBoxlist = FXCollections.observableArrayList();
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @FXML
    void checkoutClicked(ActionEvent event) {
        String pid = showId.getText();
        String pname = showName.getText();
        String quantity = quantityBox.getValue();
        String cusname = showUsername.getText();
        String bkashno = bkashNo.getText();
        String bkashpin = bkashPin.getText();
        String amount = showSubTotal.getText();

        connection = ConnectionDB.conDB();

        if (showSubTotal.getText().equals("00.00")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Confirm Product First.");
            alert.setTitle("Error");
            alert.showAndWait();
        } else if (bkashno.isEmpty() || bkashpin.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Input feilds cannot be empty!\nPlease, fill up all.");
            alert.setTitle("Error");
            alert.showAndWait();
        } else {
            try {
                query = "INSERT INTO `payments`(`pid`, `pname`, `quantity`, `cusname`, `bkashno`, `amount`) VALUES (?,?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, pid);
                preparedStatement.setString(2, pname);
                preparedStatement.setString(3, quantity);
                preparedStatement.setString(4, cusname);
                preparedStatement.setString(5, bkashno);
                preparedStatement.setString(6, amount);
                preparedStatement.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Successful!");
                alert.setContentText("Payment Successful!\nYour shipping will arrive in 7-8 working Days.");
                alert.showAndWait();

                query = "SELECT `quantity` as QT FROM `inventory` WHERE `pid` = " + pid;
                preparedStatement = connection.prepareStatement(query);
                resultSet = preparedStatement.executeQuery();
                String newqtn = "";
                while (resultSet.next()) {
                    String qtn = resultSet.getString("QT");
                    int Qtn = Integer.valueOf(qtn) - Integer.valueOf(quantity);
                    newqtn = String.valueOf(Qtn);
                }

                query = "UPDATE `inventory` SET `quantity`= ? WHERE `pid` = " + pid;
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, newqtn);
                preparedStatement.execute();

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    void confirmClicked(ActionEvent event) {
        try {
            String pid = showId.getText();
            String quantity = quantityBox.getValue();
            boolean stock = false;

            if (quantity == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Error!");
                alert.setContentText("Please select quantity.");
                alert.showAndWait();

            } else {
                connection = ConnectionDB.conDB();
                query = "SELECT `quantity` as QT FROM `inventory` WHERE `pid` = " + pid;
                preparedStatement = connection.prepareStatement(query);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String qtn = resultSet.getString("QT");
                    int check = Integer.valueOf(qtn) - Integer.valueOf(quantity);
                    if (check >= 0) {
                        stock = true;
                    }
                }
                if (stock) {
                    DecimalFormat df = new DecimalFormat("#.##");
                    df.setRoundingMode(RoundingMode.UP);
                    df.setGroupingUsed(true);
                    double q = Double.valueOf(quantity);
                    double unitPrice = Double.valueOf(showPrice.getText());

                    double subTotal = q * unitPrice;
                    String ST = df.format(subTotal);
                    showSubTotal.setText(ST);
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("No Stock!");
                    alert.setContentText("Sorry, there is not enough stock for your desired product.");
                    alert.showAndWait();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setUserName(String name) {
        showUsername.setText(name);
    }

    private void loadQuantityBox() {
        ChoiceBoxlist.removeAll(ChoiceBoxlist);
        ChoiceBoxlist.addAll("1", "2", "3", "4", "5");
        quantityBox.getItems().addAll(ChoiceBoxlist);
    }

    void setProductInfo(String pid, String pname, String pprice, Image pimage) {
        showId.setText(pid);
        showName.setText(pname);
        showPrice.setText(pprice);
        showPic.setImage(pimage);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadQuantityBox();
    }

}
