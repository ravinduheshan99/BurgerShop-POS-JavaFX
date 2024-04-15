package Controller;

import DB.ListCus;
import DB.ListOrd;
import Model.customer;
import Model.orders;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlaceOrderFormController implements Initializable {

    //Codes From Old System
    public static ListCus dbcus = ListCus.getInstance();
    public static ListOrd dbord = ListOrd.getInstance();
    public static double BURGERPRICE = 500;
    public void generateOrderId() {
        if (dbcus.size() == 0) {
            txtOid.setText("B0001");
        }
        else {
            String lastOrderId = dbord.get(dbord.size() - 1).getOrderId();
            int number = Integer.parseInt(lastOrderId.split("B")[1]);
            number++;
            txtOid.setText(String.format("B%04d", number));
        }
    }

    //JavaFX Codes
    public TextField txtOid;
    public TextField txtCid;
    public TextField txtCname;
    public TextField txtQty;
    public Label lblNetValue;
    private double tot;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateOrderId();
    }
    public void btnCheckOnAction(ActionEvent actionEvent) {
        String cid = txtCid.getText();
        boolean found = false; // Variable to track if customer is found
        for(int i = 0; i < dbcus.size(); i++) {
            if(cid.equals(dbcus.get(i).getCustomerId())) {
                txtCname.setText(dbcus.get(i).getCustomerName());
                found = true; // Customer found
                break; // No need to continue loop
            }
        }
        if(!found) { // If customer not found after looping through all entries
            showAlert("Alert", "Customer Info", "Customer Does Not Exists.");
        }
    }
    public void btnCalcTotalOnAction(ActionEvent actionEvent) {
        tot=Integer.parseInt(txtQty.getText())*BURGERPRICE;
        lblNetValue.setText(tot+"0 LKR");
    }
    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {

        try {
            String cid = txtCid.getText();
            String cname = txtCname.getText();
            int qty = Integer.parseInt(txtQty.getText());
            if (cid.isEmpty() || cid.charAt(0) != '0' || cid.length() != 10 || cname.isEmpty() || qty<=0) {
                showAlert("Alert", "Info Validation", "Please Provide Valid Informations.");
            }else {
                dbord.add(new orders(txtOid.getText(),0,qty,tot));
                dbcus.add(new customer(cid,cname));
                showAlert("Info", "Operation Status", "Order Placed Successfully!!");
                txtCid.clear();
                txtCname.clear();;
                txtQty.clear();
                lblNetValue.setText("0.00");
                generateOrderId();
            }
        } catch (NumberFormatException e) {
            showAlert("Alert", "Info Validation", "Please Provide Valid Informations.");
            return; // Stop execution if quantity is invalid
        }


    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        try {
            root = FXMLLoader.load(getClass().getResource("../View/homePageForm.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void btnClearOnAction(ActionEvent actionEvent) {
        txtCid.clear();
        txtCname.clear();;
        txtQty.clear();
        lblNetValue.setText("0.00");
    }


}
