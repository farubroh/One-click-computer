package controller;

import models.User;
import java.io.IOException;
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
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Inventory;
import models.Payment;
import utils.ConnectionDB;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AdminPanelController implements Initializable {

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    User user = null;
    Inventory inventory = null;
    int index = -1;

    @FXML
    private Pane slidingPane;

    @FXML
    private Label labelStatus;

    @FXML
    private Label labelDashboard;

    @FXML
    private Label labelCusCount;

    @FXML
    private Label labelTopPrdct;

    @FXML
    private Label labelTotalIncome;

    @FXML
    private LineChart<?, ?> chartProductsSold;

    @FXML
    private Label lblMonitorSold;

    @FXML
    private Label lblMoboSold;

    @FXML
    private Label lblCpuSold;

    @FXML
    private Label lblRamSold;

    @FXML
    private Label lblSsdSold;

    @FXML
    private Label lblCasingSold;

    @FXML
    private Label lblGpuSold;

    @FXML
    private Label lblHddSold;

    @FXML
    private Label lblCpuCoolerSold;

    @FXML
    private Label lblKbSold;

    @FXML
    private Label lblPsuSold;

    @FXML
    private Label lblMonitorStock;

    @FXML
    private Label lblMoboStock;

    @FXML
    private Label lblCpuStock;

    @FXML
    private Label lblRamStock;

    @FXML
    private Label lblSsdStock;

    @FXML
    private Label lblCasingStock;

    @FXML
    private Label lblGpuStock;

    @FXML
    private Label lblHddStock;

    @FXML
    private Label lblCpuCoolerStock;

    @FXML
    private Label lblKbStock;

    @FXML
    private Label lblPsuStock;

    @FXML
    private Label lblMouseSold;

    @FXML
    private Label lblMouseStock;

    @FXML
    private Label labelUsers;

    @FXML
    private Label labelInventory;

    @FXML
    private Label labelPayment;

    @FXML
    private Button btnSIgnOut;

    @FXML
    private Label labelUsername;

    @FXML
    private TabPane tabPaneSelection;

    @FXML
    private Tab tabDashboard;

    @FXML
    private Tab tabUsers;

    @FXML
    private Button btnSearchUser;

    @FXML
    private Button btnRefreshUser;

    @FXML
    private Button btnAddUser;

    @FXML
    private Button btnUpdateUser;

    @FXML
    private Button btnDeleteUser;

    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn<User, String> id;

    @FXML
    private TableColumn<User, String> fname;

    @FXML
    private TableColumn<User, String> lname;

    @FXML
    private TableColumn<User, String> uname;

    @FXML
    private TableColumn<User, String> email;

    @FXML
    private TableColumn<User, String> mbl;

    @FXML
    private TableColumn<User, String> address;

    ObservableList<User> usersList = FXCollections.observableArrayList();

    @FXML
    private Tab tabInventory;

    @FXML
    private TextField showPid;

    @FXML
    private TextField showPname;

    @FXML
    private ComboBox<String> showCategory;

    ObservableList ChoiceBoxlist = FXCollections.observableArrayList();

    @FXML
    private TextField showQuantity;

    @FXML
    private TextField showPrice;

    @FXML
    private TableView<Inventory> inventoryTable;

    @FXML
    private TableColumn<Inventory, String> pid;

    @FXML
    private TableColumn<Inventory, String> pname;

    @FXML
    private TableColumn<Inventory, String> category;

    @FXML
    private TableColumn<Inventory, String> invQuantity;

    @FXML
    private TableColumn<Inventory, String> price;

    ObservableList<Inventory> inventoryList = FXCollections.observableArrayList();

    @FXML
    private Tab tabPayment;

    @FXML
    private TableView<Payment> paymentTable;

    @FXML
    private TableColumn<Payment, String> TrxnId;

    @FXML
    private TableColumn<Payment, String> pId;

    @FXML
    private TableColumn<Payment, String> pName;

    @FXML
    private TableColumn<Payment, String> cusName;

    @FXML
    private TableColumn<Payment, String> quantity;

    @FXML
    private TableColumn<Payment, String> bkashNo;

    @FXML
    private TableColumn<Payment, String> amount;

    ObservableList<Payment> paymentList = FXCollections.observableArrayList();

    @FXML
    private TextField tfSearchPayment;

    @FXML
    private Button btnSearchPayment;

    @FXML
    private Button btnRefreshPayment;

    @FXML
    private TextField tfSearch;

    @FXML
    void signOutClickedAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void dashboardClicked(MouseEvent event) {
        TranslateTransition toDashboardTransition = new TranslateTransition(Duration.millis(100), labelStatus);
        toDashboardTransition.setToY(slidingPane.getTranslateY());
        toDashboardTransition.play();
        toDashboardTransition.setOnFinished((eventDashboard) -> {
        });
        tabPaneSelection.getSelectionModel().select(tabDashboard);
        CustomerCount();
        TotalIncomeCount();
        ProductsSold();
        ProductsInStock();
    }

    @FXML
    void inventoryClicked(MouseEvent event) {
        TranslateTransition toInverntoryTransition = new TranslateTransition(Duration.millis(100), labelStatus);
        toInverntoryTransition.setToY(slidingPane.getTranslateY() + 2 * labelStatus.getPrefHeight());
        toInverntoryTransition.play();
        toInverntoryTransition.setOnFinished((eventInventory) -> {
        });
        tabPaneSelection.getSelectionModel().select(tabInventory);
    }

    @FXML
    void paymentClicked(MouseEvent event) {
        TranslateTransition toPaymentTransition = new TranslateTransition(Duration.millis(100), labelStatus);
        toPaymentTransition.setToY(slidingPane.getTranslateY() + 3 * labelStatus.getPrefHeight());
        toPaymentTransition.play();
        toPaymentTransition.setOnFinished((eventPayment) -> {
        });
        tabPaneSelection.getSelectionModel().select(tabPayment);
        refreshPaymentTable();
    }

    @FXML
    void usersClicked(MouseEvent event) {
        TranslateTransition toUsersTransition = new TranslateTransition(Duration.millis(100), labelStatus);
        toUsersTransition.setToY(slidingPane.getTranslateY() + labelStatus.getPrefHeight());
        toUsersTransition.play();
        toUsersTransition.setOnFinished((eventUsers) -> {
        });
        tabPaneSelection.getSelectionModel().select(tabUsers);
        refreshUserTable();
    }

    public void getUsername(String name) {
        labelUsername.setText(name);
    }

    @FXML
    void getSelected(MouseEvent event) {
        index = inventoryTable.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        showPid.setText(String.valueOf(pid.getCellData(index)));
        showPname.setText(pname.getCellData(index));
        showCategory.setValue(category.getCellData(index));
        showQuantity.setText(String.valueOf(invQuantity.getCellData(index)));
        showPrice.setText(String.valueOf(price.getCellData(index)));
    }

    @FXML
    void addClickedAction(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/views/userInfoEdit.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdminPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void deleteClickedAction(ActionEvent event) {
        try {
            user = (User) userTable.getSelectionModel().getSelectedItem();
            if (userTable.getSelectionModel().isEmpty()) {
                return;
            }
            query = "DELETE FROM `users` WHERE `users`.`id` = " + user.getId();

            connection = ConnectionDB.conDB();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            refreshUserTable();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    void refreshClickedAction(ActionEvent event) {
        refreshUserTable();
    }

    @FXML
    void updateClickedAction(ActionEvent event) {
        try {
            index = userTable.getSelectionModel().getSelectedIndex();
            if (index <= -1) {
                return;
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/userInfoUpdate.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            UserInfoUpdateController uiuc = loader.getController();
            uiuc.setCusId(String.valueOf(id.getCellData(index)));
            uiuc.setTextfield(fname.getCellData(index), lname.getCellData(index), uname.getCellData(index), email.getCellData(index), mbl.getCellData(index), address.getCellData(index));

        } catch (IOException ex) {
            Logger.getLogger(AdminPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void searchClickedAction(ActionEvent event) {
        try {
            if (!tfSearch.getText().isEmpty()) {
                usersList.clear();
                String search = tfSearch.getText();
                query = "SELECT * FROM users WHERE id = ? OR firstname = ? OR lastname = ? OR username = ? OR email = ? OR mobile = ? OR address = ?";
                connection = ConnectionDB.conDB();
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, search);
                preparedStatement.setString(2, search);
                preparedStatement.setString(3, search);
                preparedStatement.setString(4, search);
                preparedStatement.setString(5, search);
                preparedStatement.setString(6, search);
                preparedStatement.setString(7, search);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    usersList.add(new User(
                            resultSet.getInt("id"),
                            resultSet.getString("firstname"),
                            resultSet.getString("lastname"),
                            resultSet.getString("username"),
                            resultSet.getString("email"),
                            resultSet.getString("address"),
                            resultSet.getString("mobile")));
                    userTable.setItems(usersList);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void searchInventoryClicked(ActionEvent event) {
        try {
            if (!showPid.getText().isEmpty()) {
                inventoryList.clear();
                String search = showPid.getText();
                query = "SELECT * FROM inventory WHERE pid = ? OR pname = ? OR category = ? OR quantity = ? OR price = ?";
                connection = ConnectionDB.conDB();
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, search);
                preparedStatement.setString(2, search);
                preparedStatement.setString(3, search);
                preparedStatement.setString(4, search);
                preparedStatement.setString(5, search);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    inventoryList.add(new Inventory(
                            resultSet.getInt("pid"),
                            resultSet.getString("pname"),
                            resultSet.getString("category"),
                            resultSet.getInt("quantity"),
                            resultSet.getDouble("price")));
                    inventoryTable.setItems(inventoryList);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void refreshInventoryClicked(ActionEvent event) {
        refreshInventoryTable();
    }

    @FXML
    void clearInventoryClicked(ActionEvent event) {
        clearInventoryManager();
    }

    @FXML
    void addInventoryClicked(ActionEvent event) {
        String pid = showPid.getText();
        String pname = showPname.getText();
        String category = showCategory.getValue();
        String quantity = showQuantity.getText();
        String price = showPrice.getText();

        connection = ConnectionDB.conDB();

        if (pid.isEmpty() || pname.isEmpty() || category.isEmpty() || quantity.isEmpty() || price.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Warning!");
            alert.setContentText("Please, fill up all fields!");
            alert.setX(750);
            alert.setY(350);
            alert.showAndWait();
            return;
        }

        String sqlInsert = "INSERT INTO `inventory` (`pid`, `pname`, `category`, `quantity`, `price`) VALUES(?,?,?,?,?)";

        try {
            preparedStatement = connection.prepareStatement(sqlInsert);
            preparedStatement.setString(1, pid);
            preparedStatement.setString(2, pname);
            preparedStatement.setString(3, category);
            preparedStatement.setString(4, quantity);
            preparedStatement.setString(5, price);
            preparedStatement.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Updated!");
            alert.setContentText("Inventory is updated Successfully!");
            alert.setX(750);
            alert.setY(350);
            alert.showAndWait();

            clearInventoryManager();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    void updateInventoryClicked(ActionEvent event) {
        try {
            String pid = showPid.getText();
            String pname = showPname.getText();
            String category = showCategory.getValue();
            String quantity = showQuantity.getText();
            String price = showPrice.getText();

            connection = ConnectionDB.conDB();

            String sql = "UPDATE `inventory` SET `pid`= ?, `pname`= ?, `category`= ?, `quantity`= ?, `price`= ? WHERE `inventory`.`pid` = " +pid;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, pid);
            preparedStatement.setString(2, pname);
            preparedStatement.setString(3, category);
            preparedStatement.setString(4, quantity);
            preparedStatement.setString(5, price);
            preparedStatement.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Update Successful");
            alert.setContentText("Inventory Updated Successfully!");
            alert.setX(750);
            alert.setY(350);
            alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(UserInfoUpdateController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void deleteInventoryClicked(ActionEvent event) {
        try {
            inventory = (Inventory) inventoryTable.getSelectionModel().getSelectedItem();
            if (inventoryTable.getSelectionModel().isEmpty()) {
                return;
            }
            query = "DELETE FROM `inventory` WHERE `inventory`.`pid` = " + inventory.getPid();

            connection = ConnectionDB.conDB();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            refreshInventoryTable();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    void searchPaymentClicked(ActionEvent event) {
        try {
            if (!tfSearchPayment.getText().isEmpty()) {
                paymentList.clear();
                String search = tfSearchPayment.getText();
                query = "SELECT * FROM payments WHERE trxnid = ? OR pid = ? OR pname = ? OR cusname = ? OR quantity = ? OR bkashno = ? OR amount = ?";
                connection = ConnectionDB.conDB();
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, search);
                preparedStatement.setString(2, search);
                preparedStatement.setString(3, search);
                preparedStatement.setString(4, search);
                preparedStatement.setString(5, search);
                preparedStatement.setString(6, search);
                preparedStatement.setString(7, search);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    paymentList.add(new Payment(
                            resultSet.getInt("trxnid"),
                            resultSet.getInt("pid"),
                            resultSet.getString("pname"),
                            resultSet.getString("cusname"),
                            resultSet.getInt("quantity"),
                            resultSet.getString("bkashno"),
                            resultSet.getDouble("amount")));
                    paymentTable.setItems(paymentList);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void refreshPaymentClicked(ActionEvent event) {
        refreshPaymentTable();
    }

    private void CustomerCount() {
        try {
            query = "SELECT COUNT(`username`) as CS FROM `users`";
            connection = ConnectionDB.conDB();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int Count = resultSet.getInt("CS");
                labelCusCount.setText(String.valueOf(Count));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void topProduct() {
        try {
            query = "SELECT `pname` AS HS FROM `payments` GROUP BY `pname` ORDER BY SUM(`quantity`) ASC";
            connection = ConnectionDB.conDB();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String Top = resultSet.getString("HS");
                labelTopPrdct.setText(String.valueOf(Top));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void TotalIncomeCount() {
        try {
            DecimalFormat df2 = new DecimalFormat("#.##");
            df2.setRoundingMode(RoundingMode.UP);
            df2.setGroupingUsed(true);
            df2.setGroupingSize(3);
            query = "SELECT SUM(`amount`) as CS FROM `payments`";
            connection = ConnectionDB.conDB();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                double Count = resultSet.getDouble("CS");
                String st = df2.format(Count);
                labelTotalIncome.setText(st);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void iniSalesChart() {
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("Sunday", 8));
        series.getData().add(new XYChart.Data("Monday", 14));
        series.getData().add(new XYChart.Data("Tuesday", 20));
        series.getData().add(new XYChart.Data("Wednesday", 8));
        series.getData().add(new XYChart.Data("Thursday", 15));
        series.getData().add(new XYChart.Data("Friday", 8));
        series.getData().add(new XYChart.Data("Saturday", 32));
        chartProductsSold.getData().addAll(series);
    }

    private void ProductsInStock() {
        try {
            String category = "";
            connection = ConnectionDB.conDB();
            query = "SELECT SUM(`quantity`) as CS FROM inventory WHERE category = ?";
            preparedStatement = connection.prepareStatement(query);

            for (int i = 1; i <= 12; i++) {
                if (i == 1) {
                    category = "Monitor";
                } else if (i == 2) {
                    category = "Motherboard";
                } else if (i == 3) {
                    category = "Processor";
                } else if (i == 4) {
                    category = "Ram";
                } else if (i == 5) {
                    category = "SSD";
                } else if (i == 6) {
                    category = "HDD";
                } else if (i == 7) {
                    category = "Graphics Card";
                } else if (i == 8) {
                    category = "Casing";
                } else if (i == 9) {
                    category = "Cpu Cooler";
                } else if (i == 10) {
                    category = "Keyboard";
                } else if (i == 11) {
                    category = "Mouse";
                } else if (i == 12) {
                    category = "Power Supply";
                }

                preparedStatement.setString(1, category);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int sum = resultSet.getInt("CS");
                    if (i == 1) {
                        lblMonitorStock.setText(String.valueOf(sum));
                    } else if (i == 2) {
                        lblMoboStock.setText(String.valueOf(sum));
                    } else if (i == 3) {
                        lblCpuStock.setText(String.valueOf(sum));
                    } else if (i == 4) {
                        lblRamStock.setText(String.valueOf(sum));
                    } else if (i == 5) {
                        lblSsdStock.setText(String.valueOf(sum));
                    } else if (i == 6) {
                        lblHddStock.setText(String.valueOf(sum));
                    } else if (i == 7) {
                        lblGpuStock.setText(String.valueOf(sum));
                    } else if (i == 8) {
                        lblCasingStock.setText(String.valueOf(sum));
                    } else if (i == 9) {
                        lblCpuCoolerStock.setText(String.valueOf(sum));
                    } else if (i == 10) {
                        lblKbStock.setText(String.valueOf(sum));
                    } else if (i == 11) {
                        lblMouseStock.setText(String.valueOf(sum));
                    } else if (i == 12) {
                        lblPsuStock.setText(String.valueOf(sum));
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ProductsSold() {
        try {
            String category = "";
            connection = ConnectionDB.conDB();
            query = "SELECT SUM(`payments`.`quantity`) as CS FROM `inventory`,`payments` WHERE `payments`.`pid` = `inventory`.`pid` AND `inventory`.`category` = ?";
            preparedStatement = connection.prepareStatement(query);

            for (int i = 1; i <= 12; i++) {
                if (i == 1) {
                    category = "Monitor";
                } else if (i == 2) {
                    category = "Motherboard";
                } else if (i == 3) {
                    category = "Processor";
                } else if (i == 4) {
                    category = "Ram";
                } else if (i == 5) {
                    category = "SSD";
                } else if (i == 6) {
                    category = "HDD";
                } else if (i == 7) {
                    category = "Graphics Card";
                } else if (i == 8) {
                    category = "Casing";
                } else if (i == 9) {
                    category = "Cpu Cooler";
                } else if (i == 10) {
                    category = "Keyboard";
                } else if (i == 11) {
                    category = "Mouse";
                } else if (i == 12) {
                    category = "Power Supply";
                }

                preparedStatement.setString(1, category);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int sum = resultSet.getInt("CS");
                    if (i == 1) {
                        lblMonitorSold.setText(String.valueOf(sum));
                    } else if (i == 2) {
                        lblMoboSold.setText(String.valueOf(sum));
                    } else if (i == 3) {
                        lblCpuSold.setText(String.valueOf(sum));
                    } else if (i == 4) {
                        lblRamSold.setText(String.valueOf(sum));
                    } else if (i == 5) {
                        lblSsdSold.setText(String.valueOf(sum));
                    } else if (i == 6) {
                        lblHddSold.setText(String.valueOf(sum));
                    } else if (i == 7) {
                        lblGpuSold.setText(String.valueOf(sum));
                    } else if (i == 8) {
                        lblCasingSold.setText(String.valueOf(sum));
                    } else if (i == 9) {
                        lblCpuCoolerSold.setText(String.valueOf(sum));
                    } else if (i == 10) {
                        lblKbSold.setText(String.valueOf(sum));
                    } else if (i == 11) {
                        lblMouseSold.setText(String.valueOf(sum));
                    } else if (i == 12) {
                        lblPsuSold.setText(String.valueOf(sum));
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadUserData() {
        connection = ConnectionDB.conDB();

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        fname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        uname.setCellValueFactory(new PropertyValueFactory<>("username"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        mbl.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    }

    public void refreshUserTable() {
        try {
            usersList.clear();

            query = "SELECT * FROM users";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                usersList.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("address"),
                        resultSet.getString("mobile")));
                userTable.setItems(usersList);

            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminPanelController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadPaymentTable() {
        connection = ConnectionDB.conDB();

        TrxnId.setCellValueFactory(new PropertyValueFactory<>("trxnid"));
        pId.setCellValueFactory(new PropertyValueFactory<>("pid"));
        pName.setCellValueFactory(new PropertyValueFactory<>("pname"));
        cusName.setCellValueFactory(new PropertyValueFactory<>("cusname"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        bkashNo.setCellValueFactory(new PropertyValueFactory<>("bkashno"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }

    private void refreshPaymentTable() {
        try {
            paymentList.clear();

            query = "SELECT * FROM `payments`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                paymentList.add(new Payment(
                        resultSet.getInt("trxnid"),
                        resultSet.getInt("pid"),
                        resultSet.getString("pname"),
                        resultSet.getString("cusname"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("bkashno"),
                        resultSet.getDouble("amount")));
                paymentTable.setItems(paymentList);

            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminPanelController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadInventory() {
        connection = ConnectionDB.conDB();
        pid.setCellValueFactory(new PropertyValueFactory<>("pid"));
        pname.setCellValueFactory(new PropertyValueFactory<>("pname"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        invQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    private void refreshInventoryTable() {
        try {
            inventoryList.clear();

            query = "SELECT * FROM `inventory`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                inventoryList.add(new Inventory(
                        resultSet.getInt("pid"),
                        resultSet.getString("pname"),
                        resultSet.getString("category"),
                        resultSet.getInt("quantity"),
                        resultSet.getDouble("price")));
                inventoryTable.setItems(inventoryList);

            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminPanelController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadCategoryChoices() {
        ChoiceBoxlist.removeAll(ChoiceBoxlist);
        ChoiceBoxlist.addAll("Monitor", "Motherboard", "Processor", "Ram", "SSD", "HDD", "Graphics Card", "Casing", "Cpu Cooler", "Keyboard", "Mouse", "Power Supply");
        showCategory.getItems().addAll(ChoiceBoxlist);
    }

    private void clearInventoryManager() {
        showPid.clear();
        showPname.clear();
        showCategory.setValue(null);
        showQuantity.clear();
        showPrice.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CustomerCount();
        topProduct();
        TotalIncomeCount();
        iniSalesChart();
        ProductsSold();
        ProductsInStock();
        loadUserData();
        refreshUserTable();
        loadInventory();
        refreshInventoryTable();
        loadCategoryChoices();
        clearInventoryManager();
        loadPaymentTable();
        refreshPaymentTable();
    }

}
