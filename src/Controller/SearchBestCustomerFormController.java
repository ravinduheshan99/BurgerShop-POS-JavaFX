package Controller;

import DB.ListCus;
import DB.ListOrd;
import Model.customer;
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
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchBestCustomerFormController implements Initializable {

    public TableView tblBestCustomerDetails;
    public TableColumn colCid;
    public TableColumn colName;
    public TableColumn colTot;

    private Stage stage;
    private Scene scene;
    private Parent root;

    static ListOrd ord = ListOrd.getInstance();
    static ListCus cus = ListCus.getInstance();

    public static customer[] bestCustomer() {
        // Removing duplicate customer id's and names
        customer[] idArrayDupRemoved = removeDuplicates();

        // Summing the total order values for each customer id
        calculateTotalOrderValues(idArrayDupRemoved);

        // Sort the objects according to the order value using bubble sort
        customer[] bc = bubbleSort(idArrayDupRemoved);

        return bc;
    }

    public static customer[] removeDuplicates() {
        customer[] idArrayDupRemoved = new customer[0];
        for (int i = 0; i < cus.size(); i++) {
            if (!(search(cus.get(i).getCustomerId(), idArrayDupRemoved))) {
                customer[] tempIdArray = new customer[idArrayDupRemoved.length + 1];
                for (int j = 0; j < idArrayDupRemoved.length; j++) {
                    tempIdArray[j] = new customer(idArrayDupRemoved[j].getCustomerId(), idArrayDupRemoved[j].getCustomerName());
                }
                tempIdArray[tempIdArray.length - 1] = new customer(cus.get(i).getCustomerId(), cus.get(i).getCustomerName());
                idArrayDupRemoved = tempIdArray;
            }
        }
        return idArrayDupRemoved;
    }
    public static void calculateTotalOrderValues(customer[] idArrayDupRemoved) {
        for (int k = 0; k < idArrayDupRemoved.length; k++) {
            int total = 0;
            for (int m = 0; m < cus.size(); m++) {
                if (idArrayDupRemoved[k].getCustomerId().equals(cus.get(m).getCustomerId())) {
                    total += ord.get(m).getOrderValue();
                }
            }
            idArrayDupRemoved[k].setTotal(total);
        }
    }
    public static boolean search(String customerId, customer[] idArrayDupRemoved) {
        for (customer aCustomer : idArrayDupRemoved) {
            if (aCustomer.getCustomerId().equals(customerId)) {
                return true;
            }
        }
        return false;
    }
    public static customer[] bubbleSort(customer[] idArrayDupRemoved) {
        for (int i = 0; i < idArrayDupRemoved.length - 1; i++) {
            for (int j = 0; j < idArrayDupRemoved.length - 1; j++) {
                if (idArrayDupRemoved[j].getTotal() < idArrayDupRemoved[j + 1].getTotal()) {
                    customer temp = new customer(idArrayDupRemoved[j].getCustomerId(), idArrayDupRemoved[j].getCustomerName(), idArrayDupRemoved[j].getTotal());
                    idArrayDupRemoved[j] = idArrayDupRemoved[j + 1];
                    idArrayDupRemoved[j + 1] = temp;
                }
            }
        }
        return idArrayDupRemoved;
    }
    public void loadTable() {
        customer[] bcarr = bestCustomer();
        ObservableList<customer> cusarray = cus.toArrayBc(bcarr);
        tblBestCustomerDetails.setItems(cusarray);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCid.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colTot.setCellValueFactory(new PropertyValueFactory<>("total"));
        loadTable();
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

}
