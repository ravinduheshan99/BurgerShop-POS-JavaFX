package Controller;

import DB.ListCus;
import DB.ListOrd;
import Model.orders;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;

public class SearchCustomerFormController {

    //Codes From Old System
    public static orders[] searchCusOrdDtl(String cid) {
        orders[] co = new orders[0]; // Initialize an empty array of orders
        int count = 0; // Variable to count the number of orders found

        for (int i = 0; i < cus.size(); i++) {
            if (cid.equals(cus.get(i).getCustomerId())) {
                // Resize the array to accommodate the new order
                orders[] temp = new orders[co.length + 1];
                System.arraycopy(co, 0, temp, 0, co.length);
                temp[temp.length - 1] = ord.get(i); // Assign the new order
                co = temp; // Update the reference to the resized array
                count++; // Increment the count
            }
        }

        if (count == 0) {
            return null; // Return null if no orders were found
        } else {
            return co; // Return the array of orders
        }
    }

    //JavaFX Codes
    public TableView tblCusDetails;
    public TableColumn colOid;
    public TableColumn colQty;
    public TableColumn colTot;
    public TextField txtCid;
    public TextField txtName;

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

    public static ListCus cus = ListCus.getInstance();
    public static ListOrd ord = ListOrd.getInstance();

    public void loadTable() {
        orders[] co = searchCusOrdDtl(txtCid.getText());
        ObservableList<orders> ordarray = ord.toArraySc(co);
        tblCusDetails.setItems(ordarray);
    }
    public void btnSearchOnAction(ActionEvent actionEvent) {

        String cid = txtCid.getText();

        if (cid.isEmpty() || cid.charAt(0) != '0' || cid.length() != 10) {
            showAlert("Alert", "Info Validation", "Please Provide Valid Informations.");
        }else {
            for (int i = 0; i < cus.size(); i++) {
                if (cus.get(i).getCustomerId().equals(txtCid.getText())) {
                    txtName.setText(cus.get(i).getCustomerName());
                    colOid.setCellValueFactory(new PropertyValueFactory<>("orderId"));
                    colQty.setCellValueFactory(new PropertyValueFactory<>("orderQty"));
                    colTot.setCellValueFactory(new PropertyValueFactory<>("orderValue"));
                }
            }
            loadTable();
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        try {
            root = FXMLLoader.load(getClass().getResource("../View/homePageForm.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void btnClearOnAction(ActionEvent actionEvent) {
        txtCid.clear();
        txtName.clear();
        for ( int i = 0; i<tblCusDetails.getItems().size(); i++) {
            tblCusDetails.getItems().clear();
        }
    }
}
