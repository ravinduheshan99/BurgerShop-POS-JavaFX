package Controller;

import DB.ListCus;
import DB.ListOrd;
import Model.customer;
import Model.orders;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewOrdersFormController implements Initializable {
    public TableColumn colCid;
    public TableColumn colName;
    public TableColumn colOid;
    public TableColumn colStatus;
    public TableColumn colQty;
    public TableColumn colTot;
    public TableView tblOrdOrders;
    public TableView tblCusOrders;

    private Stage stage;
    private Scene scene;
    private Parent root;

    ListOrd ord = ListOrd.getInstance();
    ListCus cus = ListCus.getInstance();

    public void loadTable() {
        ObservableList<customer> cusarray = cus.toArray();
        tblCusOrders.setItems(cusarray);
        ObservableList<orders> ordarray = ord.toArray();
        tblOrdOrders.setItems(ordarray);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colOid.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("orderQty"));
        colTot.setCellValueFactory(new PropertyValueFactory<>("orderValue"));
        colCid.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colStatus.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<orders, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<orders, String> param) {
                String status;
                int orderStatus = param.getValue().getOrderStatus();

                switch (orderStatus) {
                    case 0:
                        status = "Processing";
                        break;
                    case 1:
                        status = "Delivered";
                        break;
                    case 2:
                        status = "Cancelled";
                        break;
                    default:
                        status = "Unknown";
                }

                return new SimpleStringProperty(status);
            }
        });
        loadTable();
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


}
