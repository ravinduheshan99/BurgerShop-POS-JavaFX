package Controller;

import DB.ListCus;
import DB.ListOrd;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class SearchOrderFormController {
    // Codes From Old System
    public static ListCus dbcus = ListCus.getInstance();
    public static ListOrd dbord = ListOrd.getInstance();
    public static double BURGERPRICE = 500;
    public static final int PREPARING = 0;
    public static final int DELIVERED = 1;
    public static final int CANCEL = 2;
    public static String getStatusText(int orderStatus) {
        switch (orderStatus) {
            case PREPARING:
                return "Preparing";
            case DELIVERED:
                return "Delivered";
            case CANCEL:
                return "Cancelled";
            default:
                return "Unknown";
        }
    }

    // JavaFX Codes
    public TextField txtOid;
    public TextField txtCid;
    public TextField txtName;
    public TextField txtQty;
    public TextField txtTotal;
    public TextField txtOrderStatus;

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

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String oid = txtOid.getText();

        if (oid.isEmpty() || oid.charAt(0) != 'B') {
            showAlert("Alert", "Info Validation", "Please Provide Valid Informations.");
        }else {
            for(int i=0; i<dbord.size(); i++){
                if(dbord.get(i).getOrderId().equals(txtOid.getText())){
                    txtCid.setText(dbcus.get(i).getCustomerId());
                    txtName.setText(dbcus.get(i).getCustomerName());
                    txtQty.setText(dbord.get(i).getOrderQty()+"");
                    txtTotal.setText(dbord.get(i).getOrderValue()+"0");
                    txtOrderStatus.setText(getStatusText(dbord.get(i).getOrderStatus()));
                }
            }
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
        txtOid.clear();
        txtCid.clear();
        txtName.clear();
        txtQty.clear();
        txtTotal.clear();
        txtOrderStatus.clear();
    }

}
