package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HomePageFormController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void btnPlaceOrderOnAction(ActionEvent actionEvent){
        try {
            root = FXMLLoader.load(getClass().getResource("../View/placeOrderForm.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void btnSearchOrderOnAction(ActionEvent actionEvent) {
        try {
            root = FXMLLoader.load(getClass().getResource("../View/searchOrderForm.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void btnSearchCustomerOnAction(ActionEvent actionEvent) {
        try {
            root = FXMLLoader.load(getClass().getResource("../View/searchCustomerForm.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void btnSearchBestCustomerOnAction(ActionEvent actionEvent) {
        try {
            root = FXMLLoader.load(getClass().getResource("../View/searchBestCustomerForm.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void btnViewOrdersOnAction(ActionEvent actionEvent) {
        try {
            root = FXMLLoader.load(getClass().getResource("../View/viewOrdersForm.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void btnUpdateOrderDetailsOnAction(ActionEvent actionEvent) {
        try {
            root = FXMLLoader.load(getClass().getResource("../View/updateOrderDetailsForm.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void btnExitOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }

}
