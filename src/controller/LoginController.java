package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import utils.ConnectionDB;

public class LoginController implements Initializable {

    @FXML
    private TabPane TabPaneSelection;

    @FXML
    private Tab tabSignIn;

    @FXML
    private TextField tfUsername;

    @FXML
    private Button btnLogin;

    @FXML
    private Label labelResetPassword;

    @FXML
    private Label errorMessageLabel;

    @FXML
    private Label labelCreateAccount;

    @FXML
    private TextField tfPasswordShow;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private Tab tabSignUp;

    @FXML
    private TextField tfFirstNameNew;

    @FXML
    private TextField tfLastNameNew;

    @FXML
    private TextField tfMobileNoNew;

    @FXML
    private TextField tfEmailNew;

    @FXML
    private TextField tfUsernameNew;

    @FXML
    private TextField tfAddressNew;

    @FXML
    private Button btnSignUp;

    @FXML
    private TextField tfPasswordShow1;

    @FXML
    private PasswordField tfPassword1;

    @FXML
    private Label errorMssgLabelSignUp;

    @FXML
    private Label labelErrorUsername;

    @FXML
    private Label labelErrorPassword;

    @FXML
    private Label labelErrorEmail;

    @FXML
    private Label labelErrorMbl;

    @FXML
    private Tab tabVerify;

    @FXML
    private TextField tfVerifyMobile;

    @FXML
    private TextField tfVerifyEmail;

    @FXML
    private Button btnVerify;

    @FXML
    private Tab tabReset;

    @FXML
    private Button btnReset;

    @FXML
    private TextField tfResetConfirmPasswordShow;

    @FXML
    private PasswordField tfResetConfirmPassword;

    @FXML
    private TextField tfResetNewPasswordShow;

    @FXML
    private PasswordField tfResetNewPassword;

    @FXML
    private Label labelResetErrorMsg;

    @FXML
    private Pane slidingPane;

    @FXML
    private Label labelSignIn;

    @FXML
    private Label labelSignUp;

    @FXML
    private Label labelStatus;

    @FXML
    void showPassword(MouseEvent event) {
        tfPasswordShow.setText(tfPassword.getText());
        tfPasswordShow.setVisible(true);
        tfPassword.setVisible(false);
    }

    @FXML
    void hidePassword(MouseEvent event) {
        tfPassword.setVisible(true);
        tfPasswordShow.setVisible(false);
    }

    @FXML
    void showPassword1(MouseEvent event) {
        tfPasswordShow1.setText(tfPassword1.getText());
        tfPasswordShow1.setVisible(true);
        tfPassword1.setVisible(false);
    }

    @FXML
    void hidePassword1(MouseEvent event) {
        tfPassword1.setVisible(true);
        tfPasswordShow1.setVisible(false);
    }

    @FXML
    void showPasswordReset(MouseEvent event) {
        tfResetNewPasswordShow.setText(tfResetNewPassword.getText());
        tfResetNewPasswordShow.setVisible(true);
        tfResetNewPassword.setVisible(false);
    }

    @FXML
    void hidePasswordReset(MouseEvent event) {
        tfResetNewPassword.setVisible(true);
        tfResetNewPasswordShow.setVisible(false);
    }

    @FXML
    void showPasswordReset1(MouseEvent event) {
        tfResetConfirmPasswordShow.setText(tfResetConfirmPassword.getText());
        tfResetConfirmPasswordShow.setVisible(true);
        tfResetConfirmPassword.setVisible(false);
    }

    @FXML
    void hidePasswordReset1(MouseEvent event) {
        tfResetConfirmPassword.setVisible(true);
        tfResetConfirmPasswordShow.setVisible(false);
    }

    @FXML
    void signUpClicked(MouseEvent event) {
        TranslateTransition toRightTransition = new TranslateTransition(Duration.millis(200), labelStatus);
        toRightTransition.setToX(slidingPane.getTranslateX() + slidingPane.getPrefWidth() - labelStatus.getPrefWidth());
        toRightTransition.play();
        toRightTransition.setOnFinished((eventToRight) -> {
            labelStatus.setText("Sign Up");
        });
        TabPaneSelection.getSelectionModel().select(tabSignUp);
    }

    @FXML
    void signInClicked(MouseEvent event) {
        TranslateTransition toLeftTransition = new TranslateTransition(Duration.millis(200), labelStatus);
        toLeftTransition.setToX(slidingPane.getTranslateX());
        toLeftTransition.play();
        toLeftTransition.setOnFinished((eventToLeft) -> {
            labelStatus.setText("Sign In");
        });
        TabPaneSelection.getSelectionModel().select(tabSignIn);
    }

    @FXML
    void forgotPasswordAction(MouseEvent event) {
        TabPaneSelection.getSelectionModel().select(tabVerify);
    }

    @FXML
    void createAccountAction(MouseEvent event) {
        TranslateTransition toRightTransition = new TranslateTransition(Duration.millis(200), labelStatus);
        toRightTransition.setToX(slidingPane.getTranslateX() + slidingPane.getPrefWidth() - labelStatus.getPrefWidth());
        toRightTransition.play();
        toRightTransition.setOnFinished((eventToRight) -> {
            labelStatus.setText("Sign Up");
        });
        TabPaneSelection.getSelectionModel().select(tabSignUp);
    }

    @FXML
    void logInButtonAction(ActionEvent event) {
        errorMessage = "";

        if (isFieldFilled()) {
            if (logIn().equals("SuccessAdmin")) {
                startAdminPanel();
                Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
                stage.close();
            } else if (logIn().equals("SuccessUser")) {
                startWelcomeScreen();
                Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
                stage.close();
            }
        }
    }

    @FXML
    void signUpButtonAction(ActionEvent event) {
        signUp();
    }

    @FXML
    void verifyButtonAction(ActionEvent event) {
        verify();
    }

    @FXML
    void resetButtonAction(ActionEvent event) {
        resetPassword();
    }

    @FXML
    void autoSignIn(MouseEvent event) {
        startAdminPanel();
        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.close();
    }

    @FXML
    void autoSignInUser(MouseEvent event) {
        startWelcomeScreen();
        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.close();
    }

    @FXML
    void mobileNoReleased(KeyEvent event) {
        mobileNoValidation();
    }

    @FXML
    void emailReleased(KeyEvent event) {
        emailValidation();
    }

    @FXML
    void usernameReleased(KeyEvent event) {
        usernameValidation();
    }

    @FXML
    void passwordReleased(KeyEvent event) {
        passwordValidation();
    }

    private String errorMessage = "";

    private boolean isFieldFilled() {
        boolean isFilled = true;

        if (tfUsername.getText().isEmpty()) {
            isFilled = false;
            errorMessage = "Username is Empty!";
        }
        if (tfPassword.getText().isEmpty()) {
            isFilled = false;
            if (errorMessage.isEmpty()) {
                errorMessage = "Password is Empty!";
            } else {
                errorMessage += "\nPassword is Empty!";
            }
        }
        errorMessageLabel.setText(errorMessage);
        return isFilled;
    }

    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public LoginController() {
        con = ConnectionDB.conDB();
    }

    private String logIn() {
        String username = tfUsername.getText();
        String password = tfPassword.getText();

        String sqlAdmin = "SELECT * FROM admins Where username = ? and password = ? ";
        String sqlUser = "SELECT * FROM users Where username = ? and password = MD5(?) ";
        String who = null;

        try {
            for (int i = 1; i <= 2; i++) {
                if (i == 1) {
                    who = sqlAdmin;
                } else if (i == 2) {
                    who = sqlUser;
                }
                preparedStatement = con.prepareStatement(who);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();

                if (i == 1 && !resultSet.next()) {
                    // Not Found on Admin Table. Go -> User Table

                } else if (i == 1) { // Found on Admin Table
                    errorMessageLabel.setTextFill(Color.GREEN);
                    errorMessageLabel.setText("Login Successful..Redirecting..");
                    System.out.println("Successful Admin Login");
                    return "SuccessAdmin";

                } else if (i == 2 && !resultSet.next()) { //Not Found on Admin table nor users
                    errorMessageLabel.setTextFill(Color.TOMATO);
                    errorMessageLabel.setText("Enter Correct Username/Password");
                    System.err.println("Login Unsuccessful");
                    return "Error";

                } else if (i == 2) { // Found on User Table
                    errorMessageLabel.setTextFill(Color.GREEN);
                    errorMessageLabel.setText("Login Successful..Redirecting..");
                    System.out.println("Successful User Login");
                    return "SuccessUser";
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return "Exception";
        }
        return null;
    }

    private void signUp() {
        String firstname = tfFirstNameNew.getText();
        String lastname = tfLastNameNew.getText();
        String email = tfEmailNew.getText();
        String username = tfUsernameNew.getText();
        String password = tfPassword1.getText();
        String mobile = tfMobileNoNew.getText();
        String address = tfAddressNew.getText();

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
            preparedStatement = con.prepareStatement(sqlInsert);
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastname);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, username);
            preparedStatement.setString(5, password);
            preparedStatement.setString(6, mobile);
            preparedStatement.setString(7, address);
            preparedStatement.executeUpdate();

            errorMssgLabelSignUp.setTextFill(Color.GREEN);
            errorMssgLabelSignUp.setText("Sign Up Successful!");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void verify() {
        String email = tfVerifyEmail.getText();
        String mobile = tfVerifyMobile.getText();

        String sql = "SELECT * FROM users WHERE email = ? AND mobile = ?";

        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, mobile);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Found!");
                alert.setContentText("Identity verified!\nPlease, Reset your password.");
                alert.setX(750);
                alert.setY(350);
                alert.showAndWait();

                TabPaneSelection.getSelectionModel().select(tabReset);

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Not Found!");
                alert.setContentText("Email and Mobile no. don't match.\nVerification failed!");
                alert.setX(750);
                alert.setY(350);
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void resetPassword() {
        String email = tfVerifyEmail.getText();
        String mobile = tfVerifyMobile.getText();
        String newPass = tfResetNewPassword.getText();
        String confirmPass = tfResetConfirmPassword.getText();

        if (newPass.equals(confirmPass)) {
            try {
                labelResetErrorMsg.setText("");

                String sql = "UPDATE `users` SET `password`= MD5(?) WHERE `users`.`email` = ? AND `users`.`mobile` = ?";
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, newPass);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, mobile);
                preparedStatement.execute();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Reset Successful");
                alert.setContentText("Password is updated Successfully!");
                alert.setX(750);
                alert.setY(350);
                alert.showAndWait();

                TabPaneSelection.getSelectionModel().select(tabSignIn);
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            labelResetErrorMsg.setText("Passwords don't match. Try Again!");
        }
    }

    private void showDialog(String info, String header, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(info);
        alert.setHeaderText(header);
        alert.showAndWait();
    }

    private void startWelcomeScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/loginSuccessScreen.fxml"));
            Parent root = loader.load();

            LoginSuccessScreenController successScreenController = loader.getController();
            successScreenController.getUsername(tfUsername.getText());

            Stage stage = new Stage();
            stage.setTitle("OneClickComputers");
            stage.getIcons().add(new Image("/img/Screenshot_2021-09-09_225922-removebg-preview.png"));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void startAdminPanel() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/adminPanel.fxml"));
            Parent root = loader.load();

            AdminPanelController adminPanelController = loader.getController();
            adminPanelController.getUsername(tfUsername.getText());
            Stage stage = new Stage();

            stage.setTitle("OneClickComputers");
            stage.getIcons().add(new Image("/img/Screenshot_2021-09-09_225922-removebg-preview.png"));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void mobileNoValidation() {
        String nm = tfMobileNoNew.getText();

        String regex = "^(?:\\+88|01)?(?:\\d{10}|\\d{12})$";
        Pattern p = Pattern.compile(regex);
        Matcher match = p.matcher(nm);
        if (!match.matches()) {
            labelErrorMbl.setTextFill(Color.RED);
            labelErrorMbl.setText("Invalid Mobile No");
        } else {
            labelErrorMbl.setText(null);
        }
    }

    public void emailValidation() {
        String ne = tfEmailNew.getText();

        String regex = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
        Pattern p = Pattern.compile(regex);
        Matcher match = p.matcher(ne);
        if (!match.matches()) {
            labelErrorEmail.setTextFill(Color.RED);
            labelErrorEmail.setText("Invalid Email");
        } else {
            labelErrorEmail.setText(null);
        }
    }

    public void usernameValidation() {
        String nu = tfUsernameNew.getText();

        String regex = "^[a-zA-Z0-9_-]{5,15}$";
        Pattern p = Pattern.compile(regex);
        Matcher match = p.matcher(nu);
        if (!match.matches()) {
            labelErrorUsername.setTextFill(Color.RED);
            labelErrorUsername.setText("*Username must be 6-16 characters long!");
        } else {
            labelErrorUsername.setText("");
        }
    }

    public void passwordValidation() {
        String np = tfPassword1.getText();

        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{7,15}$";
        Pattern p = Pattern.compile(regex);
        Matcher match = p.matcher(np);
        if (!match.matches()) {
            labelErrorPassword.setTextFill(Color.RED);
            labelErrorPassword.setText("**Password length must be 8-16 mix of digits & letters ");
        } else {
            labelErrorPassword.setText(null);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }
}
