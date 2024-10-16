package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import models.Product;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import utils.ConnectionDB;
import views.MyListener;

public class HomeController implements Initializable {

    @FXML
    private ImageView home;

    @FXML
    private TextField tfSearch;

    @FXML
    private Button btnSearch;

    @FXML
    private Label labelMyAccount;

    @FXML
    private Label labelSignOut;

    @FXML
    private TabPane tabPaneHome;

    @FXML
    private Tab tabDefault;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    @FXML
    private Tab tabShowProduct;

    @FXML
    private Label labelProductName;

    @FXML
    private ImageView imgProduct;

    @FXML
    private Label labelProductID;

    @FXML
    private Label labelProductInfo;

    @FXML
    private Label labelProductPrice;

    @FXML
    private Button btnBuyNow;

    @FXML
    private Tab tabShowCpuOnly;

    @FXML
    private ScrollPane scrollCpu;

    @FXML
    private GridPane gridCpu;

    @FXML
    private Tab tabShowMoboOnly;

    @FXML
    private ScrollPane scrollMobo;

    @FXML
    private GridPane gridMobo;

    @FXML
    private Tab tabShowGpuOnly;

    @FXML
    private ScrollPane scrollGpu;

    @FXML
    private GridPane gridGpu;

    @FXML
    private Tab tabShowRamOnly;

    @FXML
    private ScrollPane scrollRam;

    @FXML
    private GridPane gridRam;

    @FXML
    private Tab tabShowSSDOnly;

    @FXML
    private ScrollPane scrollSSD;

    @FXML
    private GridPane gridSSD;

    @FXML
    private Tab tabShowHDDOnly;

    @FXML
    private ScrollPane scrollHdd;

    @FXML
    private GridPane gridHdd;

    @FXML
    private Tab tabShowCasingOnly;

    @FXML
    private ScrollPane scrollCasing;

    @FXML
    private GridPane gridCasing;

    @FXML
    private Tab tabShowPsuOnly;

    @FXML
    private ScrollPane scrollPsu;

    @FXML
    private GridPane gridPsu;

    @FXML
    private Tab tabShowMonitorOnly;

    @FXML
    private ScrollPane scrollMonitor;

    @FXML
    private GridPane gridMonitor;

    @FXML
    private Tab tabShowCpuCoolerOnly;

    @FXML
    private ScrollPane scrollCooler;

    @FXML
    private GridPane gridCooler;

    @FXML
    private Tab tabShowKbOnly;

    @FXML
    private ScrollPane scrollKb;

    @FXML
    private GridPane gridKb;

    @FXML
    private Tab tabShowMouseOnly;

    @FXML
    private ScrollPane scrollMouse;

    @FXML
    private GridPane gridMouse;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @FXML
    void myAccountClicked(MouseEvent event) {
        try {
            String username = labelMyAccount.getText();
            query = "SELECT `firstname` as FN, `lastname` as LN, `username` as UN, `email` as UE, `mobile` as UM, `address` as UA FROM `users` WHERE `username` = '"+username+"' " ;
            connection = ConnectionDB.conDB();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String fn = resultSet.getString("FN");
                String ln = resultSet.getString("LN");
                String un = resultSet.getString("UN");
                String ue = resultSet.getString("UE");
                String um = resultSet.getString("UM");
                String ua = resultSet.getString("UA");
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/userInfoUpdate.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                UserInfoUpdateController uiuc = loader.getController();
                uiuc.setTextfield(fn, ln, un, ue, um,ua);
            }

        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void searchBoxClicked(MouseEvent event) {
        List<String> Pname = new ArrayList<>();
        try {
            connection = ConnectionDB.conDB();
            query = "SELECT pname as PN FROM inventory";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Pname.add(resultSet.getString("PN"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AutoCompletionBinding<String> autoComplete = TextFields.bindAutoCompletion(tfSearch, Pname);
        autoComplete.prefWidthProperty().bind(this.tfSearch.widthProperty());
    }

    @FXML
    void searchClicked(ActionEvent event) {
        products.addAll(getData());
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equals(tfSearch.getText())) {
                setChosenProduct(products.get(i));
                tabPaneHome.getSelectionModel().select(tabShowProduct);
            }
        }
    }

    @FXML
    void signOutClicked(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void homeClicked(MouseEvent event) {
        tabPaneHome.getSelectionModel().select(tabDefault);
    }

    @FXML
    void monitorClicked(MouseEvent event) {
        tabPaneHome.getSelectionModel().select(tabShowMonitorOnly);
    }

    @FXML
    void motherClicked(MouseEvent event) {
        tabPaneHome.getSelectionModel().select(tabShowMoboOnly);
    }

    @FXML
    void ramClicked(MouseEvent event) {
        tabPaneHome.getSelectionModel().select(tabShowRamOnly);
    }

    @FXML
    void cpuClicked(MouseEvent event) {
        tabPaneHome.getSelectionModel().select(tabShowCpuOnly);
    }

    @FXML
    void casingClicked(MouseEvent event) {
        tabPaneHome.getSelectionModel().select(tabShowCasingOnly);
    }

    @FXML
    void coolerClicked(MouseEvent event) {
        tabPaneHome.getSelectionModel().select(tabShowCpuCoolerOnly);
    }

    @FXML
    void gpuClicked(MouseEvent event) {
        tabPaneHome.getSelectionModel().select(tabShowGpuOnly);
    }

    @FXML
    void hddClicked(MouseEvent event) {
        tabPaneHome.getSelectionModel().select(tabShowHDDOnly);
    }

    @FXML
    void kbClicked(MouseEvent event) {
        tabPaneHome.getSelectionModel().select(tabShowKbOnly);
    }

    @FXML
    void mouseClicked(MouseEvent event) {
        tabPaneHome.getSelectionModel().select(tabShowMouseOnly);
    }

    @FXML
    void psuClicked(MouseEvent event) {
        tabPaneHome.getSelectionModel().select(tabShowPsuOnly);
    }

    @FXML
    void ssdClicked(MouseEvent event) {
        tabPaneHome.getSelectionModel().select(tabShowSSDOnly);
    }

    @FXML
    void buyNowClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/payment.fxml"));
            Parent root = loader.load();
            PaymentController pc = loader.getController();

            pc.setUserName(labelMyAccount.getText());
            pc.setProductInfo(labelProductID.getText(), labelProductName.getText(), labelProductPrice.getText(), imgProduct.getImage());
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(HomeController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getAccountName(String name) {
        labelMyAccount.setText(name);
    }

    private List<Product> products = new ArrayList<>();
    private List<Product> productsCpu = new ArrayList<>();
    private List<Product> productsMobo = new ArrayList<>();
    private List<Product> productsGpu = new ArrayList<>();
    private List<Product> productsRam = new ArrayList<>();
    private List<Product> productsSSD = new ArrayList<>();
    private List<Product> productsHDD = new ArrayList<>();
    private List<Product> productsPsu = new ArrayList<>();
    private List<Product> productsCasing = new ArrayList<>();
    private List<Product> productsMonitor = new ArrayList<>();
    private List<Product> productsCpuCooler = new ArrayList<>();
    private List<Product> productsKb = new ArrayList<>();
    private List<Product> productsMouse = new ArrayList<>();

    private Image image;
    private MyListener myListener;

    private List<Product> getData() {
        List<Product> products = new ArrayList<>();
        Product product;

        //MotherBoard
        product = new Product();
        product.setPid(1001001);
        product.setName("ASRock Z590 Steel Legend 10th and 11th Gen ATX");
        product.setType("mobo");
        product.setPrice(23000.00);
        product.setImgSrc("/img/Motherboard/ASRock Z590 Steel Legend 10th and 11th Gen ATX Motherboard(23,000tk).jpg");
        products.add(product);
        productsMobo.add(product);

        product = new Product();
        product.setPid(1001002);
        product.setName("MSI MAG X570 TOMAHAWK WiFi AMD Motherboard");
        product.setType("mobo");
        product.setPrice(24900.00);
        product.setImgSrc("/img/Motherboard/MSI MAG X570 TOMAHAWK WiFi AMD Motherboard(24,900tk).jpg");
        products.add(product);
        productsMobo.add(product);

        product = new Product();
        product.setPid(1001003);
        product.setName("Gigabyte Z590 UD Intel 10th and 11th Gen ATX");
        product.setType("mobo");
        product.setPrice(20200.00);
        product.setImgSrc("/img/Motherboard/Gigabyte Z590 UD Intel 10th and 11th Gen ATX Motherboard(20,200tk).jpg");
        products.add(product);
        productsMobo.add(product);

        product = new Product();
        product.setPid(1001004);
        product.setName("Gigabyte Z590 AORUS PRO AX Intel 10th and 11th Gen ATX");
        product.setType("mobo");
        product.setPrice(34000.00);
        product.setImgSrc("/img/Motherboard/Gigabyte Z590 AORUS PRO AX Intel 10th and 11th Gen ATX Motherboard(34,000tk).jpg");
        products.add(product);
        productsMobo.add(product);

        product = new Product();
        product.setPid(1001005);
        product.setName("Asus ROG Maximus XIII Hero");
        product.setType("mobo");
        product.setPrice(47500.00);
        product.setImgSrc("/img/Motherboard/Asus ROG Maximus XIII Hero Z590 Intel 10th and 11th Gen ATX Motherboard(47,500tk)(1).jpg");
        products.add(product);
        productsMobo.add(product);

        product = new Product();
        product.setPid(1001006);
        product.setName("MSI MEG B550 UNIFY AM4 ATX AMD Motherboard");
        product.setType("mobo");
        product.setPrice(28500.00);
        product.setImgSrc("/img/Motherboard/MSI MEG B550 UNIFY AM4 ATX AMD Motherboard(28,500tk)(1).jpg");
        products.add(product);
        productsMobo.add(product);

        product = new Product();
        product.setPid(1001007);
        product.setName("MSI MPG Z590 GAMING EDGE WIFI M-ATX Motherboard");
        product.setType("mobo");
        product.setPrice(37000.00);
        product.setImgSrc("/img/Motherboard/MSI MPG Z590 GAMING EDGE WIFI 10th and 11th Gen M-ATX Motherboard(37,000yk)(1).jpg");
        products.add(product);
        productsMobo.add(product);

        //CPU
        product = new Product();
        product.setPid(1002001);
        product.setName("Intel 8th Generation Core i5-8400 Processor");
        product.setType("cpu");
        product.setPrice(15700.00);
        product.setImgSrc("/img/Intel/Intel 8th Generation Core i5-8400 Processor (Tray Processor).png");
        products.add(product);
        productsCpu.add(product);

        product = new Product();
        product.setPid(1002002);
        product.setName("Intel 8th Generation Core i7-8700 Processor");
        product.setType("cpu");
        product.setPrice(28500.00);
        product.setImgSrc("/img/Intel/Intel 8th Generation Core i7-8700 Processor.jpg");
        products.add(product);
        productsCpu.add(product);

        product = new Product();
        product.setPid(1002003);
        product.setName("Intel 9th Gen Core i5-9400 Processor");
        product.setType("cpu");
        product.setPrice(16800.00);
        product.setImgSrc("/img/Intel/Intel 9th Gen Core i5-9400 Processor.jpg");
        products.add(product);
        productsCpu.add(product);

        product = new Product();
        product.setPid(1002004);
        product.setName("Intel 9th Generation Core i5-9600K Processor");
        product.setType("cpu");
        product.setPrice(17300.00);
        product.setImgSrc("/img/Intel/Intel 9th Generation Core i5-9600K Processor.jpg");
        products.add(product);
        productsCpu.add(product);

        product = new Product();
        product.setPid(1002005);
        product.setName("Intel 9th Generation Core i7-9700K Processor");
        product.setType("cpu");
        product.setPrice(30500.00);
        product.setImgSrc("/img/Intel/Intel 9th Generation Core i7-9700K Processor.jpg");
        products.add(product);
        productsCpu.add(product);

        product = new Product();
        product.setPid(1002006);
        product.setName("Intel 10th Gen Core i3 10100 Processor");
        product.setType("cpu");
        product.setPrice(11450.00);
        product.setImgSrc("/img/Intel/Intel 10th Gen Core i3 10100 Processor.jpg");
        products.add(product);
        productsCpu.add(product);

        product = new Product();
        product.setPid(1002007);
        product.setName("AMD Ryzen 3 3200G Processor with Radeon RX Vega 8 Graphics");
        product.setType("cpu");
        product.setPrice(15450.00);
        product.setImgSrc("/img/AMD/AMD Ryzen 3 3200G Processor with Radeon RX Vega 8 Graphics.jpg");
        products.add(product);
        productsCpu.add(product);

        product = new Product();
        product.setPid(1002008);
        product.setName("AMD Ryzen 5 3400G Processor with Radeon RX Vega 11 Graphics");
        product.setType("cpu");
        product.setPrice(22000.00);
        product.setImgSrc("/img/AMD/AMD Ryzen 5 3400G Processor with Radeon RX Vega 11 Graphics.jpg");
        products.add(product);
        productsCpu.add(product);

        product = new Product();
        product.setPid(1002009);
        product.setName("AMD RYZEN 5 3500 Processor");
        product.setType("cpu");
        product.setPrice(12900.00);
        product.setImgSrc("/img/AMD/AMD RYZEN 5 3500 Processor.jpg");
        products.add(product);
        productsCpu.add(product);

        product = new Product();
        product.setPid(1002010);
        product.setName("AMD Ryzen 5 3600 Processor");
        product.setType("cpu");
        product.setPrice(16950.00);
        product.setImgSrc("/img/AMD/AMD Ryzen 5 3600 Processor.jpg");
        products.add(product);
        productsCpu.add(product);

        product = new Product();
        product.setPid(1002011);
        product.setName("AMD Ryzen 5 3600X Processor");
        product.setType("cpu");
        product.setPrice(20500.00);
        product.setImgSrc("/img/AMD/AMD Ryzen 5 3600X Processor.jpg");
        products.add(product);
        productsCpu.add(product);

        product = new Product();
        product.setPid(1002012);
        product.setName("AMD Ryzen 5 3600XT Processor");
        product.setType("cpu");
        product.setPrice(20800.00);
        product.setImgSrc("/img/AMD/AMD Ryzen 5 3600XT Processor.jpg");
        products.add(product);
        productsCpu.add(product);

        //GPU
        product = new Product();
        product.setPid(1003001);
        product.setName("Colorful GeForce GTX 1660 Super NB 6GB-V Graphics Card");
        product.setType("gpu");
        product.setPrice(48925.00);
        product.setImgSrc("/img/GPU/Colorful GeForce GTX 1660 Super NB 6GB-V Graphics Card(48,925tk)(1).jpg");
        products.add(product);
        productsGpu.add(product);

        product = new Product();
        product.setPid(1003002);
        product.setName("Colorful iGame GeForce RTX 3070 Advanced OC-V 8GB Graphics Card");
        product.setType("gpu");
        product.setPrice(95000.00);
        product.setImgSrc("/img/GPU/Colorful iGame GeForce RTX 3070 Advanced OC-V 8GB Graphics Card(95,000tk)(1).jpg");
        products.add(product);
        productsGpu.add(product);

        product = new Product();
        product.setPid(1003003);
        product.setName("Colorful iGame GeForce RTX 3070 Ultra W OC-V 8GB GDDR6 Graphics Card");
        product.setType("gpu");
        product.setPrice(99000.00);
        product.setImgSrc("/img/GPU/Colorful iGame GeForce RTX 3070 Ultra W OC-V 8GB GDDR6 Graphics Card(99000tk).jpg");
        products.add(product);
        productsGpu.add(product);

        product = new Product();
        product.setPid(1003004);
        product.setName("Gigabyte GeForce GTX 1650 D6 EAGLE OC 4GB GDDR6 Graphics Card");
        product.setType("gpu");
        product.setPrice(37000.00);
        product.setImgSrc("/img/GPU/Gigabyte GeForce GTX 1650 D6 EAGLE OC 4GB GDDR6 Graphics Card(37000tk).jpg");
        products.add(product);
        productsGpu.add(product);

        product = new Product();
        product.setPid(1003005);
        product.setName("Gigabyte GeForce GTX 1650 D6 OC 4GB Graphics Card");
        product.setType("gpu");
        product.setPrice(34000.00);
        product.setImgSrc("/img/GPU/Gigabyte GeForce GTX 1650 D6 OC 4GB Graphics Card(34,000tk)(1).jpg");
        products.add(product);
        productsGpu.add(product);

        product = new Product();
        product.setPid(1003006);
        product.setName("Gigabyte GeForce RTX 3070 TI EAGLE 8GB GDDR6X Graphics Card");
        product.setType("gpu");
        product.setPrice(110000.00);
        product.setImgSrc("/img/GPU/Gigabyte GeForce RTX 3070 TI EAGLE 8GB GDDR6X Graphics Card(110,000tk)(1).jpg");
        products.add(product);
        productsGpu.add(product);

        product = new Product();
        product.setPid(1003007);
        product.setName("Gigabyte GT 1030 2GB OC Graphics card");
        product.setType("gpu");
        product.setPrice(11200.00);
        product.setImgSrc("/img/GPU/Gigabyte GT 1030 2GB OC Graphics card(11200tk).png");
        products.add(product);
        productsGpu.add(product);

        product = new Product();
        product.setPid(1003008);
        product.setName("MSI GeForce RTX 3090 SUPRIM X 24GB Graphics Card");
        product.setType("gpu");
        product.setPrice(245000.00);
        product.setImgSrc("/img/GPU/MSI GeForce RTX 3090 SUPRIM X 24GB Graphics Card(245,000tk)(1).jpg");
        products.add(product);
        productsGpu.add(product);

        product = new Product();
        product.setPid(1003009);
        product.setName("SAPPHIRE NITRO+ AMD Radeon RX 6900 XT 6GB GDDR6 Graphics Card");
        product.setType("gpu");
        product.setPrice(163900.00);
        product.setImgSrc("/img/GPU/SAPPHIRE NITRO+ AMD Radeon RX 6900 XT Special Edition 16GB GDDR6 Graphics Card(163,900tk)(1).jpg");
        products.add(product);
        productsGpu.add(product);

        product = new Product();
        product.setPid(1003010);
        product.setName("ZOTAC GAMING GeForce GTX 1660 Ti 6GB GDDR6 Graphics Card");
        product.setType("gpu");
        product.setPrice(55000.00);
        product.setImgSrc("/img/GPU/ZOTAC GAMING GeForce GTX 1660 Ti 6GB GDDR6 Graphics Card(55,000tk)(1).jpg");
        products.add(product);
        productsGpu.add(product);

        //Ram
        product = new Product();
        product.setPid(1004001);
        product.setName("Corsair Dominator Platinum RGB 16GB 4000MHz DDR4");
        product.setType("ram");
        product.setPrice(23900.00);
        product.setImgSrc("/img/Ram/Corsair Dominator Platinum RGB 16GB 4000MHz DDR4 RAM (Black)(23,999tk).jpg");
        products.add(product);
        productsRam.add(product);

        product = new Product();
        product.setPid(1004002);
        product.setName("Corsair Vengeance LPX 16GB DDR4 DRAM 3200MHz");
        product.setType("ram");
        product.setPrice(9000.00);
        product.setImgSrc("/img/Ram/Corsair Vengeance LPX 16GB DDR4 DRAM 3200MHz Ram(9,000tk).jpg");
        products.add(product);
        productsRam.add(product);

        product = new Product();
        product.setPid(1004003);
        product.setName("Corsair Vengeance RGB Pro 8GB DDR4 4000MHz");
        product.setType("ram");
        product.setPrice(9900.00);
        product.setImgSrc("/img/Ram/Corsair Vengeance RGB Pro 8GB DDR4 4000MHz RAM(9,900tk).jpg");
        products.add(product);
        productsRam.add(product);

        product = new Product();
        product.setPid(1004004);
        product.setName("G.SKILL Trident Z Royal RGB 8GB DDR4 4266MHz");
        product.setType("ram");
        product.setPrice(9800.00);
        product.setImgSrc("/img/Ram/G.SKILL Trident Z Royal RGB 8GB DDR4 4266MHz Desktop RAM(9800tk).jpg");
        products.add(product);
        productsRam.add(product);

        product = new Product();
        product.setPid(1004005);
        product.setName("Gigabyte AORUS RGB 16GB (2x8GB) DDR4 4400MHz");
        product.setType("ram");
        product.setPrice(21000.00);
        product.setImgSrc("/img/Ram/Gigabyte AORUS RGB 16GB (2x8GB) DDR4 4400MHz Desktop Gaming RAM(17,000tk).jpg");
        products.add(product);
        productsRam.add(product);

        product = new Product();
        product.setPid(1004006);
        product.setName("Adata XPG SPECTRIX D50 8GB DDR4 3200MHz RGB Gaming RAM");
        product.setType("ram");
        product.setPrice(5300.00);
        product.setImgSrc("/img/Ram/Adata XPG SPECTRIX D50 8GB DDR4 3200MHz RGB Gaming RAM(5300tk).jpg");
        products.add(product);
        productsRam.add(product);

        product = new Product();
        product.setPid(1004007);
        product.setName("Corsair Vengeance RGB Pro 8GB DDR4 3200MHz Ram");
        product.setType("ram");
        product.setPrice(5000.00);
        product.setImgSrc("/img/Ram/Corsair Vengeance RGB Pro 8GB DDR4 3200MHz Ram(5000tk).jpg");
        products.add(product);
        productsRam.add(product);

        product = new Product();
        product.setPid(1004008);
        product.setName("Thermaltake TOUGHRAM 8GB 3200MHz DDR4 Desktop RAM");
        product.setType("ram");
        product.setPrice(4200.00);
        product.setImgSrc("/img/Ram/Thermaltake TOUGHRAM 8GB 3200MHz DDR4 Desktop RAM(4200tk).jpg");
        products.add(product);
        productsRam.add(product);

        product = new Product();
        product.setPid(1004009);
        product.setName("Thermaltake TOUGHRAM RGB 32GB 3600MHz DDR4 Desktop RAM");
        product.setType("ram");
        product.setPrice(16000.00);
        product.setImgSrc("/img/Ram/Thermaltake TOUGHRAM RGB 32GB 3600MHz DDR4 Desktop RAM(16000tk).jpg");
        products.add(product);
        productsRam.add(product);

        product = new Product();
        product.setPid(1004010);
        product.setName("Team Delta RGB White 16GB 3600MHz DDR4 Desktop RAM");
        product.setType("ram");
        product.setPrice(9500.00);
        product.setImgSrc("/img/Ram/Team Delta RGB White 16GB 3600MHz DDR4 Desktop RAM(9500tk).jpg");
        products.add(product);
        productsRam.add(product);

        //ssd
        product = new Product();
        product.setPid(1005001);
        product.setName("Gigabyte 120GB Solid State Drive (SSD)");
        product.setType("ssd");
        product.setPrice(2450.00);
        product.setImgSrc("/img/SSD/Gigabyte 120GB Solid State Drive (SSD)(2450tk).jpg");
        products.add(product);
        productsSSD.add(product);

        product = new Product();
        product.setPid(1005002);
        product.setName("Corsair Force MP600 1TB Gen.4 PCIe NVMe M.2 SSD");
        product.setType("ssd");
        product.setPrice(23900.00);
        product.setImgSrc("/img/SSD/Corsair Force MP600 1TB Gen.4 PCIe NVMe M.2 SSD(23900tk).png");
        products.add(product);
        productsSSD.add(product);

        product = new Product();
        product.setPid(1005003);
        product.setName("Gigabyte M.2 PCIe SSD 128GB");
        product.setType("ssd");
        product.setPrice(3050.00);
        product.setImgSrc("/img/SSD/Gigabyte M.2 PCIe SSD 128GB(3050tk).jpg");
        products.add(product);
        productsSSD.add(product);

        product = new Product();
        product.setPid(1005004);
        product.setName("Samsung 870 EVO 500GB 2.5 Inch SATA III Internal SSD");
        product.setType("ssd");
        product.setPrice(7000.00);
        product.setImgSrc("/img/SSD/Samsung 870 EVO 500GB 2.5 Inch SATA III Internal SSD(7000tk).jpg");
        products.add(product);
        productsSSD.add(product);

        product = new Product();
        product.setPid(1005005);
        product.setName("Samsung 970 EVO Plus 1TB NVMe M.2 SSD");
        product.setType("ssd");
        product.setPrice(18500.00);
        product.setImgSrc("/img/SSD/Samsung 970 EVO Plus 1TB NVMe M.2 SSD(18500tk).jpg");
        products.add(product);
        productsSSD.add(product);

        product = new Product();
        product.setPid(1005006);
        product.setName("Samsung 980 250GB PCIe 3.0 M.2 NVMe SSD");
        product.setType("ssd");
        product.setPrice(6200.00);
        product.setImgSrc("/img/SSD/Samsung 980 250GB PCIe 3.0 M.2 NVMe SSD(6200tk).jpg");
        products.add(product);
        productsSSD.add(product);

        product = new Product();
        product.setPid(1005007);
        product.setName("Samsung 980 500GB PCIe 3.0 M.2 NVMe SSD");
        product.setType("ssd");
        product.setPrice(7600.00);
        product.setImgSrc("/img/SSD/Samsung 980 500GB PCIe 3.0 M.2 NVMe SSD(7600tk).jpg");
        products.add(product);
        productsSSD.add(product);

        product = new Product();
        product.setPid(1005008);
        product.setName("Samsung 980 Pro 250GB PCIe 4.0 M.2 NVMe SSD");
        product.setType("ssd");
        product.setPrice(8000.00);
        product.setImgSrc("/img/SSD/Samsung 980 Pro 250GB PCIe 4.0 M.2 NVMe SSD(8000tk).jpg");
        products.add(product);
        productsSSD.add(product);

        product = new Product();
        product.setPid(1005009);
        product.setName("Transcend 110S 1TB NVMe M.2 2280 PCle SSD");
        product.setType("ssd");
        product.setPrice(13500.00);
        product.setImgSrc("/img/SSD/Transcend 110S 1TB NVMe M.2 2280 PCle SSD(13500tk).jpg");
        products.add(product);
        productsSSD.add(product);

        product = new Product();
        product.setPid(1005010);
        product.setName("Transcend 110S 256GB M.2 (M-Key) PCIe SSD Drive");
        product.setType("ssd");
        product.setPrice(4150.00);
        product.setImgSrc("/img/SSD/Transcend 110S 256GB M.2 (M-Key) PCIe SSD Drive(4150tk).jpg");
        products.add(product);
        productsSSD.add(product);

        //HDD
        product = new Product();
        product.setPid(1006001);
        product.setName("Seagate Barracuda 4TB SATA 3.5 inch HDD");
        product.setType("hdd");
        product.setPrice(9875.00);
        product.setImgSrc("/img/HDD/Seagate Barracuda 4TB SATA 3.5 inch HDD(9875tk).jpg");
        products.add(product);
        productsHDD.add(product);

        product = new Product();
        product.setPid(1006002);
        product.setName("Seagate Exos X18 18TB 7200rpm SATA III HDD");
        product.setType("hdd");
        product.setPrice(85700.00);
        product.setImgSrc("/img/HDD/Seagate Exos X18 18TB 7200rpm SATA III HDD(85700tk).jpg");
        products.add(product);
        productsHDD.add(product);

        product = new Product();
        product.setPid(1006003);
        product.setName("Seagate Internal 1TB SATA Barracuda HDD");
        product.setType("hdd");
        product.setPrice(3650.00);
        product.setImgSrc("/img/HDD/Seagate Internal 1TB SATA Barracuda HDD(3650tk).jpg");
        products.add(product);
        productsHDD.add(product);

        product = new Product();
        product.setPid(1006004);
        product.setName("Seagate Ironwolf 10TB Home, SOHO and Small Business NAS HDD");
        product.setType("hdd");
        product.setPrice(32500.00);
        product.setImgSrc("/img/HDD/Seagate Ironwolf 10TB Home, SOHO and Small Business NAS HDD(32500tk).jpg");
        products.add(product);
        productsHDD.add(product);

        product = new Product();
        product.setPid(1006005);
        product.setName("Seagate SkyHawk 8TB 3.5 inch SATA 7200RPM Surveillance HDD");
        product.setType("hdd");
        product.setPrice(21500.00);
        product.setImgSrc("/img/HDD/Seagate SkyHawk 8TB 3.5 inch SATA 7200RPM Surveillance HDD(21,500tk).jpeg");
        products.add(product);
        productsHDD.add(product);

        product = new Product();
        product.setPid(1006006);
        product.setName("Toshiba MG07ACA Enterprise 14TB 3.5 Inch SATA 7200RPM HDD");
        product.setType("hdd");
        product.setPrice(39450.00);
        product.setImgSrc("/img/HDD/Toshiba MG07ACA Enterprise 14TB 3.5 Inch SATA 7200RPM HDD(39450tk).jpg");
        products.add(product);
        productsHDD.add(product);

        product = new Product();
        product.setPid(1006007);
        product.setName("TOSHIBA Tomcat Nearline 4TB 3.5 Inch 7200RPM SATA NAS HDD");
        product.setType("hdd");
        product.setPrice(14000.00);
        product.setImgSrc("/img/HDD/TOSHIBA Tomcat Nearline 4TB 3.5 Inch 7200RPM SATA NAS HDD(14000tk).jpg");
        products.add(product);
        productsHDD.add(product);

        product = new Product();
        product.setPid(1006008);
        product.setName("Western Digital 2TB Purple Surveillance HDD");
        product.setType("hdd");
        product.setPrice(5400.00);
        product.setImgSrc("/img/HDD/Western Digital 2TB Purple Surveillance HDD(5400tk).jpg");
        products.add(product);
        productsHDD.add(product);

        product = new Product();
        product.setPid(1006009);
        product.setName("Western Digital 4TB Purple Surveillance HDD");
        product.setType("hdd");
        product.setPrice(8500.00);
        product.setImgSrc("/img/HDD/Western Digital 4TB Purple Surveillance HDD(8500tk).jpg");
        products.add(product);
        productsHDD.add(product);

        product = new Product();
        product.setPid(1006010);
        product.setName("Western Digital 10TB Purple Surveillance HDD");
        product.setType("hdd");
        product.setPrice(27000.00);
        product.setImgSrc("/img/HDD/Western Digital 10TB Purple Surveillance HDD(27000tk).jpg");
        products.add(product);
        productsHDD.add(product);

        //PSU
        product = new Product();
        product.setPid(1007001);
        product.setName("Cooler Master MWE 450W V2 Non-Modular 80 Plus Bronze Power Supply");
        product.setType("psu");
        product.setPrice(3700.00);
        product.setImgSrc("/img/PSU/Cooler Master MWE 450W V2 Non-Modular 80 Plus Bronze Power Supply(3700tk).jpg");
        products.add(product);
        productsPsu.add(product);

        product = new Product();
        product.setPid(1007002);
        product.setName("Cooler Master MWE Gold 750 V2 Full Modular 750W 80 Plus Gold Power Supply");
        product.setType("psu");
        product.setPrice(10450.00);
        product.setImgSrc("/img/PSU/Cooler Master MWE Gold 750 V2 Full Modular 750W 80 Plus Gold Power Supply(10450tk).jpg");
        products.add(product);
        productsPsu.add(product);

        product = new Product();
        product.setPid(1007003);
        product.setName("Corsair CV550 550Watt 80 Plus Bronze Certified Power Supply");
        product.setType("psu");
        product.setPrice(4480.00);
        product.setImgSrc("/img/PSU/Corsair CV550 550Watt 80 Plus Bronze Certified Power Supply(4480tk).jpg");
        products.add(product);
        productsPsu.add(product);

        product = new Product();
        product.setPid(1007004);
        product.setName("Corsair CV650 650Watt 80 Plus Bronze Certified Power Supply");
        product.setType("psu");
        product.setPrice(5550.00);
        product.setImgSrc("/img/PSU/Corsair CV650 650Watt 80 Plus Bronze Certified Power Supply(5550tk).jpg");
        products.add(product);
        productsPsu.add(product);

        product = new Product();
        product.setPid(1007005);
        product.setName("Corsair CX650F RGB 80 Plus Bronze 650 Watt Fully Modular Power Supply");
        product.setType("psu");
        product.setPrice(7800.00);
        product.setImgSrc("/img/PSU/Corsair CX650F RGB 80 Plus Bronze 650 Watt Fully Modular Power Supply(7800tk).jpg");
        products.add(product);
        productsPsu.add(product);

        product = new Product();
        product.setPid(1007006);
        product.setName("Gigabyte P650B 650W 80 Plus Bronze Certified Non-Modular Power Supply");
        product.setType("psu");
        product.setPrice(5080.00);
        product.setImgSrc("/img/PSU/Gigabyte P650B 650W 80 Plus Bronze Certified Non-Modular Power Supply(5080tk).jpg");
        products.add(product);
        productsPsu.add(product);

        product = new Product();
        product.setPid(1007007);
        product.setName("Thermaltake Litepower 450W Non Modular Power Supply");
        product.setType("psu");
        product.setPrice(3250.00);
        product.setImgSrc("/img/PSU/Thermaltake Litepower 450W Non Modular Power Supply(3250tk).jpg");
        products.add(product);
        productsPsu.add(product);

        product = new Product();
        product.setPid(1007008);
        product.setName("Thermaltake Toughpower PF1 ARGB 1050W 80 Plus Power Supply");
        product.setType("psu");
        product.setPrice(24500.00);
        product.setImgSrc("/img/PSU/Thermaltake Toughpower PF1 ARGB 1050W 80 Plus Platinum Fully Modular Power Supply(24500tk).jpg");
        products.add(product);
        productsPsu.add(product);

        //Casing
        product = new Product();
        product.setPid(1008001);
        product.setName("MaxGreen A366BK ATX");
        product.setType("casing");
        product.setPrice(4700.00);
        product.setImgSrc("/img/Casing/MaxGreen A366BK ATX Casing(4,700tk).jpg");
        products.add(product);
        productsCasing.add(product);

        product = new Product();
        product.setPid(1008002);
        product.setName("Lian Li O11D O11 Dynamic Razer Edition ATX");
        product.setType("casing");
        product.setPrice(14800.00);
        product.setImgSrc("/img/Casing/Lian Li O11D O11 Dynamic Razer Edition ATX Mid Tower Gaming Case (Black)(14.800tk)(1).jpg");
        products.add(product);
        productsCasing.add(product);

        product = new Product();
        product.setPid(1008003);
        product.setName("Lian Li LANCOOL II Mesh RGB Gaming Case (White)");
        product.setType("casing");
        product.setPrice(9200.00);
        product.setImgSrc("/img/Casing/Lian Li LANCOOL II Mesh RGB Gaming Case (White)(9,200tk)(1).jpg");
        products.add(product);
        productsCasing.add(product);

        product = new Product();
        product.setPid(1008004);
        product.setName("Gigabyte C200 Glass RGB LED Casing");
        product.setType("casing");
        product.setPrice(4100.00);
        product.setImgSrc("/img/Casing/Gigabyte C200 Glass RGB LED Casing(4,100tk).jpg");
        products.add(product);
        productsCasing.add(product);

        product = new Product();
        product.setPid(1008005);
        product.setName("Gamdias MARS E2 Micro Tower Casing White");
        product.setType("casing");
        product.setPrice(5300.00);
        product.setImgSrc("/img/Casing/Gamdias MARS E2 Micro Tower Casing White(5,300tk).jpg");
        products.add(product);
        productsCasing.add(product);

        product = new Product();
        product.setPid(1008006);
        product.setName("Gamdias ATHENA M1 Elite RGB Mid Tower Gaming Case");
        product.setType("casing");
        product.setPrice(6700.00);
        product.setImgSrc("/img/Casing/Gamdias ATHENA M1 Elite RGB Mid Tower Gaming Case(6,700tk)(1).jpg");
        products.add(product);
        productsCasing.add(product);

        product = new Product();
        product.setPid(1008007);
        product.setName("Corsair Obsidian 1000D ATX");
        product.setType("casing");
        product.setPrice(48000.00);
        product.setImgSrc("/img/Casing/Corsair Obsidian 1000D ATX Full Tower Casing(48,000tk)(1).jpg");
        products.add(product);
        productsCasing.add(product);

        product = new Product();
        product.setPid(1008008);
        product.setName("Asus TUF Gaming GT501 White Edition Mid Tower Gaming Casing");
        product.setType("casing");
        product.setPrice(13900.00);
        product.setImgSrc("/img/Casing/Asus TUF Gaming GT501 White Edition Mid Tower Gaming Casing(13,900tk)(1).jpg");
        products.add(product);
        productsCasing.add(product);

        product = new Product();
        product.setPid(1008009);
        product.setName("Antec Striker ITX open Gaming Casing");
        product.setType("casing");
        product.setPrice(18200.00);
        product.setImgSrc("/img/Casing/Antec Striker ITX open Gaming Casing(18.200tk)(1).jpg");
        products.add(product);
        productsCasing.add(product);

        product = new Product();
        product.setPid(1008010);
        product.setName("Antec NX500 Mid-Tower Gaming Case");
        product.setType("casing");
        product.setPrice(4400.00);
        product.setImgSrc("/img/Casing/Antec NX500 Mid-Tower Gaming Case(4,400tk).jpg");
        products.add(product);
        productsCasing.add(product);

        //monitor
        product = new Product();
        product.setPid(1009001);
        product.setName("ASUS BE24AQLB IPS Business Monitor");
        product.setType("monitor");
        product.setPrice(22500.00);
        product.setImgSrc("/img/Monitor/ASUS BE24AQLB IPS Business Monitor(22500tk).jpg");
        products.add(product);
        productsMonitor.add(product);

        product = new Product();
        product.setPid(1009002);
        product.setName("ASUS MB169B+ FHD IPS USB Portable Monitor");
        product.setType("monitor");
        product.setPrice(23500.00);
        product.setImgSrc("/img/Monitor/ASUS MB169B+ FHD IPS USB Portable Monitor(23500tk).jpg");
        products.add(product);
        productsMonitor.add(product);

        product = new Product();
        product.setPid(1009003);
        product.setName("ASUS TUF VG328H1B 32 Inch FHD 165Hz Curved Gaming Monitor");
        product.setType("monitor");
        product.setPrice(38500.00);
        product.setImgSrc("/img/Monitor/ASUS TUF VG328H1B 32 Inch FHD 165Hz Curved Gaming Monitor(38500tk).jpg");
        products.add(product);
        productsMonitor.add(product);

        product = new Product();
        product.setPid(1009004);
        product.setName("GIGABYTE G27FC 165Hz Full HD Curved Gaming Monitor");
        product.setType("monitor");
        product.setPrice(29000.00);
        product.setImgSrc("/img/Monitor/GIGABYTE G27FC 165Hz Full HD Curved Gaming Monitor(29000tk).jpg");
        products.add(product);
        productsMonitor.add(product);

        product = new Product();
        product.setPid(1009005);
        product.setName("HP P27V G4 IPS LED Monitor");
        product.setType("monitor");
        product.setPrice(24500.00);
        product.setImgSrc("/img/Monitor/HP P27V G4 IPS LED Monitor(24,500tk).jpg");
        products.add(product);
        productsMonitor.add(product);

        product = new Product();
        product.setPid(1009006);
        product.setName("LG 22MK600M 21.5 inch IPS Full HD LED Monitor");
        product.setType("monitor");
        product.setPrice(15500.00);
        product.setImgSrc("/img/Monitor/LG 22MK600M 21.5 inch IPS Full HD LED Monitor(15500tk).jpg");
        products.add(product);
        productsMonitor.add(product);

        product = new Product();
        product.setPid(1009007);
        product.setName("LG UltraGear 27GN800-B QHD IPS 1ms 144Hz HDR Gaming Monitor");
        product.setType("monitor");
        product.setPrice(51000.00);
        product.setImgSrc("/img/Monitor/LG UltraGear 27GN800-B QHD IPS 1ms 144Hz HDR Gaming Monitor(51000tk).jpg");
        products.add(product);
        productsMonitor.add(product);

        product = new Product();
        product.setPid(1009008);
        product.setName("Samsung 21.5 Inch S22F350F LED FULL HD Monitor");
        product.setType("monitor");
        product.setPrice(12200.00);
        product.setImgSrc("/img/Monitor/Samsung 21.5 Inch S22F350F LED FULL HD Monitor(12200tk).jpg");
        products.add(product);
        productsMonitor.add(product);

        //cpu cooler
        product = new Product();
        product.setPid(1010001);
        product.setName("Corsair H100 RGB 240mm Liquid CPU Cooler");
        product.setType("CPU cooler");
        product.setPrice(10000.00);
        product.setImgSrc("/img/CPU cooler/Corsair H100 RGB 240mm Liquid CPU Cooler(10000tk).jpg");
        products.add(product);
        productsCpuCooler.add(product);

        product = new Product();
        product.setPid(1010002);
        product.setName("Corsair Hydro Series H100X High Performance Liquid CPU Cooler");
        product.setType("CPU cooler");
        product.setPrice(7500.00);
        product.setImgSrc("/img/CPU cooler/Corsair Hydro Series H100X High Performance Liquid CPU Cooler(7500tk).jpg");
        products.add(product);
        productsCpuCooler.add(product);

        product = new Product();
        product.setPid(1010003);
        product.setName("Corsair Hydro Series H150i PRO RGB 360mm Liquid CPU Cooler");
        product.setType("CPU cooler");
        product.setPrice(13500.00);
        product.setImgSrc("/img/CPU cooler/Corsair Hydro Series H150i PRO RGB 360mm Liquid CPU Cooler(13500tk).jpg");
        products.add(product);
        productsCpuCooler.add(product);

        product = new Product();
        product.setPid(1010004);
        product.setName("Corsair iCUE H150i 360mm Elite Capellix Liquid CPU Cooler");
        product.setType("CPU cooler");
        product.setPrice(17500.00);
        product.setImgSrc("/img/CPU cooler/Corsair iCUE H150i 360mm Elite Capellix Liquid CPU Cooler(17500tk).jpg");
        products.add(product);
        productsCpuCooler.add(product);

        product = new Product();
        product.setPid(1010005);
        product.setName("Deepcool GAMMAXX L240T Red All In One Liquid CPU Cooler");
        product.setType("CPU cooler");
        product.setPrice(5250.00);
        product.setImgSrc("/img/CPU cooler/Deepcool GAMMAXX L240T Red All In One Liquid CPU Cooler(5250tk).jpg");
        products.add(product);
        productsCpuCooler.add(product);

        product = new Product();
        product.setPid(1010006);
        product.setName("EKWB EK-AIO Elite Aurum 360 D-RGB CPU Cooler");
        product.setType("CPU cooler");
        product.setPrice(26000.00);
        product.setImgSrc("/img/CPU cooler/EKWB EK-AIO Elite Aurum 360 D-RGB CPU Cooler(26,000tk).jpg");
        products.add(product);
        productsCpuCooler.add(product);

        product = new Product();
        product.setPid(1010007);
        product.setName("NZXT Kraken X73 RGB 360mm All-in-One Liquid CPU Cooler");
        product.setType("CPU cooler");
        product.setPrice(22000.00);
        product.setImgSrc("/img/CPU cooler/NZXT Kraken X73 RGB 360mm All-in-One Liquid CPU Cooler(22,000tk).jpg");
        products.add(product);
        productsCpuCooler.add(product);

        //keyboard
        product = new Product();
        product.setPid(1011001);
        product.setName("Asus TUF Gaming K1 RGB Keyboard");
        product.setType("keyboard");
        product.setPrice(4000.00);
        product.setImgSrc("/img/keyboard/Asus TUF Gaming K1 RGB Keyboard(4000tk).jpg");
        products.add(product);
        productsKb.add(product);

        product = new Product();
        product.setPid(1011002);
        product.setName("Fantech FTK-801 USB Numeric Keypad");
        product.setType("keyboard");
        product.setPrice(350.00);
        product.setImgSrc("/img/keyboard/Fantech FTK-801 USB Numeric Keypad(350tk).jpg");
        products.add(product);
        productsKb.add(product);

        product = new Product();
        product.setPid(1011003);
        product.setName("Havit KB510L Multi Function Backlit Gaming Keyboard");
        product.setType("keyboard");
        product.setPrice(870.00);
        product.setImgSrc("/img/keyboard/Havit KB510L Multi Function Backlit Gaming Keyboard(870tk).jpg");
        products.add(product);
        productsKb.add(product);

        product = new Product();
        product.setPid(1011004);
        product.setName("Logitech G310 Mechanical Gaming Keyboard");
        product.setType("keyboard");
        product.setPrice(9550.00);
        product.setImgSrc("/img/keyboard/Logitech G310 Mechanical Gaming Keyboard(9550tk).png");
        products.add(product);
        productsKb.add(product);

        product = new Product();
        product.setPid(1011005);
        product.setName("Logitech G413 Mechanical Gaming Keyboard");
        product.setType("keyboard");
        product.setPrice(7500.00);
        product.setImgSrc("/img/keyboard/Logitech G413 Mechanical Gaming Keyboard(7500tk).png");
        products.add(product);
        productsKb.add(product);

        product = new Product();
        product.setPid(1011006);
        product.setName("Rapoo V52 Backlit Gaming Keyboard");
        product.setType("keyboard");
        product.setPrice(1500.00);
        product.setImgSrc("/img/keyboard/Rapoo V52 Backlit Gaming Keyboard(1500tk).jpg");
        products.add(product);
        productsKb.add(product);

        product = new Product();
        product.setPid(1011007);
        product.setName("Rapoo V500 SE USB Mix-Colored Backlit Mechanical Gaming Keyboard");
        product.setType("keyboard");
        product.setPrice(2550.00);
        product.setImgSrc("/img/keyboard/Rapoo V500 SE USB Mix-Colored Backlit Mechanical Gaming Keyboard(2550tk).jpg");
        products.add(product);
        productsKb.add(product);

        product = new Product();
        product.setPid(1011008);
        product.setName("azer Huntsman Elite Opto-Mechanical Switch Gaming Keyboard");
        product.setType("keyboard");
        product.setPrice(16800.00);
        product.setImgSrc("/img/keyboard/Razer Huntsman Elite Opto-Mechanical Switch Gaming Keyboard - Purple Switch(16800tk).jpg");
        products.add(product);
        productsKb.add(product);

        //Mouse
        product = new Product();
        product.setPid(1012001);
        product.setName("A4TECH OP-620D 2X Click Optical Mouse");
        product.setType("Mouse");
        product.setPrice(320.00);
        product.setImgSrc("/img/Mouse/A4TECH OP-620D 2X Click Optical Mouse(320tk).jpg");
        products.add(product);
        productsMouse.add(product);

        product = new Product();
        product.setPid(1012002);
        product.setName("Fantech Helios UX3 Space Edition RGB Gaming Mouse");
        product.setType("Mouse");
        product.setPrice(4500.00);
        product.setImgSrc("/img/Mouse/Fantech Helios UX3 Space Edition RGB Gaming Mouse White(4500tk).jpg");
        products.add(product);
        productsMouse.add(product);

        product = new Product();
        product.setPid(1012003);
        product.setName("Havit HV-MS814 RGB Backlit Programmable Gaming Mouse");
        product.setType("Mouse");
        product.setPrice(1150.00);
        product.setImgSrc("/img/Mouse/Havit HV-MS814 RGB Backlit Programmable Gaming Mouse(1150tk).jpg");
        products.add(product);
        productsMouse.add(product);

        product = new Product();
        product.setPid(1012004);
        product.setName("Havit MS1008 RGB Backlit Optical Gaming Mouse");
        product.setType("Mouse");
        product.setPrice(590.00);
        product.setImgSrc("/img/Mouse/Havit MS1008 RGB Backlit Optical Gaming Mouse(590tk).jpg");
        products.add(product);
        productsMouse.add(product);

        product = new Product();
        product.setPid(1012005);
        product.setName("Logitech G502 Hero Gaming Mouse");
        product.setType("Mouse");
        product.setPrice(6800.00);
        product.setImgSrc("/img/Mouse/Logitech G502 Hero Gaming Mouse(6800tk).jpg");
        products.add(product);
        productsMouse.add(product);

        product = new Product();
        product.setPid(1012006);
        product.setName("Logitech G903 Lightspeed HERO RGB Wireless Gaming Mouse");
        product.setType("Mouse");
        product.setPrice(11500.00);
        product.setImgSrc("/img/Mouse/Logitech G903 Lightspeed HERO RGB Wireless Gaming Mouse(11500tk).jpg");
        products.add(product);
        productsMouse.add(product);

        product = new Product();
        product.setPid(1012007);
        product.setName("Rapoo VT300 IR Optical Gaming Mouse");
        product.setType("Mouse");
        product.setPrice(2420.00);
        product.setImgSrc("/img/Mouse/Rapoo VT300 IR Optical Gaming Mouse(2420tk).jpg");
        products.add(product);
        productsMouse.add(product);

        product = new Product();
        product.setPid(1012008);
        product.setName("Razer DeathAdder Essential Gaming Mouse");
        product.setType("Mouse");
        product.setPrice(1900.00);
        product.setImgSrc("/img/Mouse/Razer DeathAdder Essential Gaming Mouse(1900tk).jpg");
        products.add(product);
        productsMouse.add(product);

        product = new Product();
        product.setPid(1012009);
        product.setName("XIAOMI XMWS001TM Fashion Wireless Mouse Black");
        product.setType("Mouse");
        product.setPrice(1650.00);
        product.setImgSrc("/img/Mouse/XIAOMI XMWS001TM Fashion Wireless Mouse Black(1650tk).jpg");
        products.add(product);
        productsMouse.add(product);

        return products;
    }

    private void setChosenProduct(Product product) {
        labelProductID.setText(Integer.toString(product.getPid()));
        labelProductName.setText(product.getName());
        labelProductPrice.setText(String.valueOf(product.getPrice()));
        labelProductInfo.setText("Intel LGA 1200 socket:\nReady for 10th Gen Intel Core processor\nFan Xpert 4\nEnhanced power solution\nReady for Ultrafast\nConnectivity");
        image = new Image(getClass().getResourceAsStream(product.getImgSrc()));
        imgProduct.setImage(image);
    }

    private void iniGrid(List<Product> products, GridPane grid) {
        int column = 0;
        int row = 1;

        try {
            for (int i = 0; i < products.size(); i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/views/item.fxml"));
                AnchorPane anchorPane = fxmlloader.load();

                ItemController itemController = fxmlloader.getController();
                itemController.setData(products.get(i), myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row);

                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        products.addAll(getData());

        if (products.size() > 0) {
            setChosenProduct(products.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Product product) {
                    setChosenProduct(product);
                    tabPaneHome.getSelectionModel().select(tabShowProduct);
                }
            };
        }

        iniGrid(products, grid);
        iniGrid(productsCpu, gridCpu);
        iniGrid(productsMobo, gridMobo);
        iniGrid(productsGpu, gridGpu);
        iniGrid(productsRam, gridRam);
        iniGrid(productsSSD, gridSSD);
        iniGrid(productsHDD, gridHdd);
        iniGrid(productsCasing, gridCasing);
        iniGrid(productsPsu, gridPsu);
        iniGrid(productsMonitor, gridMonitor);
        iniGrid(productsCpuCooler, gridCooler);
        iniGrid(productsKb, gridKb);
        iniGrid(productsMouse, gridMouse);

    }

}
