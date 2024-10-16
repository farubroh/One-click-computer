package controller;

import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import models.Product;
import views.Main;
import views.MyListener;

public class ItemController implements Initializable {

    @FXML
    private ImageView imgProduct;

    @FXML
    private Label labelProductName;

    @FXML
    private Label labelProductPrice;

    @FXML
    private void itemClicked(MouseEvent event) {
        myListener.onClickListener(product);

    }

    private Product product;
    private MyListener myListener;

    public void setData(Product product, MyListener myListener) {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.UP);
        df.setGroupingUsed(true);
        df.setGroupingSize(3);
        
        this.product = product;
        this.myListener = myListener;
        labelProductName.setText(product.getName());
        labelProductPrice.setText("Price - " + df.format(product.getPrice()) + " " + Main.CURRENCY);
        Image image = new Image(getClass().getResourceAsStream(product.getImgSrc()));
        imgProduct.setImage(image);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
