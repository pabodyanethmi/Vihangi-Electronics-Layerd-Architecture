package lk.ijse.vihangielectronics_ijse_76.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.vihangielectronics_ijse_76.dao.custom.PaymentDAO;
import lk.ijse.vihangielectronics_ijse_76.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.vihangielectronics_ijse_76.dto.PaymentDto;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {

    @FXML
    private TextField txtPaymentId;
    @FXML
    private ComboBox<String> cmbOrderId;
    @FXML
    private TextField txtPaymentAmount;
    @FXML
    private ComboBox<String> cmbPaymentMethod;
    @FXML
    private ComboBox<String> cmbPaymentStatus;
    @FXML
    private DatePicker dpPaymentDate;
    @FXML
    private TextField txtSearchPayment;
    @FXML
    private TableView<PaymentDto> tblPayment;
    @FXML
    private TableColumn<PaymentDto, String> colPayId;
    @FXML
    private TableColumn<PaymentDto, String> colOrderId;
    @FXML
    private TableColumn<PaymentDto, LocalDate> colPayDate;
    @FXML
    private TableColumn<PaymentDto, Integer> colAmount;
    @FXML
    private TableColumn<PaymentDto, String> colMethod;
    @FXML
    private TableColumn<PaymentDto, String> colStatus;

    private final PaymentDAO paymentDAO = new PaymentDAOImpl();
    private final ObservableList<PaymentDto> paymentList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactories();
        loadOrderIds();
        loadAllPayments();
        setDefaultValues();
        tableRowSelectListener();
    }

    private void setCellValueFactories() {
        colPayId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colPayDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colMethod.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void loadOrderIds() {
        cmbOrderId.getItems().addAll("OD001", "OD002", "OD003");
        cmbPaymentMethod.getItems().addAll("Cash", "Card", "Online");
        cmbPaymentStatus.getItems().addAll("Pending", "Completed");
    }

    private void loadAllPayments() {
        try {
            paymentList.clear();
            paymentList.addAll(paymentDAO.getAllPayments());
            tblPayment.setItems(paymentList);
        } catch (SQLException | ClassNotFoundException e) {
            showError("Failed to load payments!");
        }
    }

    @FXML
    private void handleSavePayment() {

        try {
            PaymentDto dto = collectPaymentData();

            if (paymentDAO.isPaymentExists(dto.getPaymentId())) {
                showWarning("Payment ID already exists!");
                return;
            }

            boolean isSaved = paymentDAO.savePayment(dto);

            if (isSaved) {
                showInfo("Payment Saved Successfully!");
                loadAllPayments();
                clearFields();
            }

        } catch (NumberFormatException e) {
            showWarning("Invalid Amount!");
        } catch (SQLException | ClassNotFoundException e) {
            showError("Database Error!");
        }
    }

    @FXML
    private void handleUpdatePayment() {

        try {
            PaymentDto dto = collectPaymentData();
            boolean isUpdated = paymentDAO.updatePayment(dto);

            if (isUpdated) {
                showInfo("Payment Updated Successfully!");
                loadAllPayments();
                clearFields();
            }

        } catch (SQLException | ClassNotFoundException e) {
            showError("Update Failed!");
        }
    }

    @FXML
    private void handleDeletePayment() {

        try {
            String paymentId = txtPaymentId.getText();

            boolean isDeleted = paymentDAO.deletePayment(paymentId);

            if (isDeleted) {
                showInfo("Payment Deleted Successfully!");
                loadAllPayments();
                clearFields();
            }

        } catch (SQLException | ClassNotFoundException e) {
            showError("Delete Failed!");
        }
    }

    @FXML
    private void handleSearchPayment() {

        String paymentId = txtSearchPayment.getText();

        // 🔹 Empty check
        if (paymentId == null || paymentId.trim().isEmpty()) {
            showWarning("Please enter Payment ID!");
            return;
        }

        try {
            PaymentDto dto = paymentDAO.searchPayment(txtSearchPayment.getText());

            if (dto != null) {

                // Fill form fields
                fillFields(dto);

                // Select row in table (if exists)
                tblPayment.getItems().stream()
                        .filter(p -> p.getPaymentId().equals(paymentId))
                        .findFirst()
                        .ifPresent(p -> tblPayment.getSelectionModel().select(p));

                showInfo("Payment Found!");

            } else {
                showWarning("Payment Not Found!");
            }

        } catch (SQLException | ClassNotFoundException e) {
            showError("Search Failed: " + e.getMessage());
        }
    }


    @FXML
    private void handleResetFields() {
        clearFields();
    }

    private PaymentDto collectPaymentData() {
        return new PaymentDto(
                txtPaymentId.getText(),
                cmbOrderId.getValue(),
                dpPaymentDate.getValue(),
                Integer.parseInt(txtPaymentAmount.getText()),
                cmbPaymentMethod.getValue(),
                cmbPaymentStatus.getValue()
        );
    }

    private void fillFields(PaymentDto dto) {
        txtPaymentId.setText(dto.getPaymentId());
        cmbOrderId.setValue(dto.getOrderId());
        dpPaymentDate.setValue(dto.getDate());
        txtPaymentAmount.setText(String.valueOf(dto.getAmount()));
        cmbPaymentMethod.setValue(dto.getPaymentMethod());
        cmbPaymentStatus.setValue(dto.getStatus());
    }

    private void clearFields() {
        txtPaymentId.clear();
        txtPaymentAmount.clear();
        txtSearchPayment.clear();
        cmbOrderId.getSelectionModel().clearSelection();
        cmbPaymentMethod.getSelectionModel().clearSelection();
        cmbPaymentStatus.getSelectionModel().clearSelection();
        dpPaymentDate.setValue(LocalDate.now());
    }

    private void setDefaultValues() {
        dpPaymentDate.setValue(LocalDate.now());
    }

    private void tableRowSelectListener() {
        tblPayment.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        fillFields(newValue);
                    }
                }
        );
    }

    private void showInfo(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).show();
    }

    private void showWarning(String msg) {
        new Alert(Alert.AlertType.WARNING, msg).show();
    }

    private void showError(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).show();
    }
}
