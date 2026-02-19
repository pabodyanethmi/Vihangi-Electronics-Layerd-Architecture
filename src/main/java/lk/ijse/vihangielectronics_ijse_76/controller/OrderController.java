package lk.ijse.vihangielectronics_ijse_76.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.vihangielectronics_ijse_76.dto.CustomerDto;
import lk.ijse.vihangielectronics_ijse_76.dto.OrderDetailsDto;
import lk.ijse.vihangielectronics_ijse_76.dto.OrderDto;
import lk.ijse.vihangielectronics_ijse_76.dto.ProductDto;
import lk.ijse.vihangielectronics_ijse_76.dto.tm.CartTM;
import lk.ijse.vihangielectronics_ijse_76.model.CustomerModel;
import lk.ijse.vihangielectronics_ijse_76.model.OrderModel;
import lk.ijse.vihangielectronics_ijse_76.model.ProductModel;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderController implements Initializable {

    @FXML
    private TextField txtProductName;
    @FXML
    private TextField txtCustomerName;
    @FXML
    private ComboBox<CustomerDto> cmbCustomerId;
    @FXML
    private ComboBox<ProductDto> cmbProductId;

    @FXML
    private TableView<CartTM> tblQuotation;
    @FXML
    private TableColumn<CartTM, String> colProductId;
    @FXML
    private TableColumn<CartTM, String> colProductName;
    @FXML
    private TableColumn<CartTM, Integer> colQty;
    @FXML
    private TableColumn<CartTM, Double> colUnitPrice;
    @FXML
    private TableColumn<CartTM, Double> colTotal;

    @FXML
    private Label lblNetTotal;
    @FXML
    private Label lblQtyOnHand;
    @FXML
    private Label lblUnitPrice;

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtDate;
    @FXML
    private TextField txtQty;

    private final CustomerModel customerModel = new CustomerModel();
    private final ProductModel productModel = new ProductModel();
    private final OrderModel orderModel = new OrderModel();

    private final ObservableList<CartTM> cartList =
            FXCollections.observableArrayList();

    public OrderController() throws SQLException {
    }

    // ===================== CUSTOMER =====================
    @FXML
    void cmbCustomerOnAction(ActionEvent event) {
        CustomerDto customer = cmbCustomerId.getValue();
        if (customer != null) {
            txtCustomerName.setText(customer.getName());
        }
    }

    // ===================== PRODUCT =====================
    @FXML
    void cmbProductOnAction(ActionEvent event) {
        ProductDto product = cmbProductId.getValue();
        if (product != null) {
            txtProductName.setText(product.getName());
            lblQtyOnHand.setText(String.valueOf(product.getQuantity()));
            lblUnitPrice.setText(String.valueOf(product.getPricePerUnit()));
        }
    }

    // ===================== BUTTONS =====================
    @FXML void handleAddToCart(ActionEvent event) { ProductDto product = cmbProductId.getValue(); if (product == null || txtQty.getText().isEmpty()) { showAlert(Alert.AlertType.WARNING, "Select product and enter quantity"); return; } int qty = Integer.parseInt(txtQty.getText()); double unitPrice = product.getPricePerUnit(); double total = qty * unitPrice; cartList.add(new CartTM( product.getProductId(), product.getName(), qty, unitPrice, total )); tblQuotation.setItems(cartList); calculateNetTotal();
        lblQtyOnHand.setText("0");
        lblUnitPrice.setText("0.00");
        txtQty.setText("");
        txtProductName.setText("");
        cmbProductId.getSelectionModel().clearSelection();}

    @FXML
    void handlePlaceQuotation(ActionEvent event) {

        // Validate cart
        if (cartList.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Cart is empty!");
            return;
        }

        // Validate customer
        CustomerDto customer = cmbCustomerId.getValue();
        if (customer == null) {
            showAlert(Alert.AlertType.WARNING, "Please select a customer!");
            return;
        }

        try {
            String orderId = txtId.getText();
            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now();
            double netTotal = Double.parseDouble(lblNetTotal.getText());
            ArrayList<OrderDetailsDto> cartLists = new ArrayList<>();
            for (CartTM item : cartList) {
                cartLists.add(new OrderDetailsDto(orderId, item.getProductId(), item.getQty(), item.getUnitPrice()));
            }
            OrderDto order = new OrderDto(orderId,
                    date,
                    time,
                    netTotal,
                    cmbCustomerId.getValue().getCustomerId(),
                    cartLists
                    );
            boolean isPlaced = orderModel.placeOrder(order);

            if (isPlaced) {
                showAlert(Alert.AlertType.INFORMATION, "Quotation placed successfully!");
                resetPage();
            } else {
                showAlert(Alert.AlertType.ERROR, "Failed to place quotation!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error placing quotation!");
        }
    }

    @FXML
    void handleReset(ActionEvent event) {
        resetPage();
    }

    // ===================== INITIALIZE =====================
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resetPage();
        loadCustomers();
        loadProducts();
        setupTableColumns();
        txtDate.setText(LocalDate.now().toString());
        txtDate.setEditable(false);
    }

    // ===================== METHODS =====================
    private void resetPage() {
        cartList.clear();
        tblQuotation.refresh();
        lblNetTotal.setText("0.00");
        setGenerateId();
        lblQtyOnHand.setText("0");
        lblUnitPrice.setText("0.00");
        txtQty.setText("");
        txtCustomerName.setText("");
        txtProductName.setText("");
        cmbCustomerId.getSelectionModel().clearSelection();
        cmbProductId.getSelectionModel().clearSelection();

    }

    private void setGenerateId() {
        try {
            String lastId = orderModel.getLastOrderId();
            int newId = (lastId != null) ? Integer.parseInt(lastId.substring(1)) + 1 : 1;
            txtId.setText(String.format("O%03d", newId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCustomers() {
        try {
            cmbCustomerId.setItems(
                    FXCollections.observableArrayList(customerModel.getAllCustomers())
            );
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Failed to load customers");
        }
    }

    private void loadProducts() {
        try {
            cmbProductId.setItems(
                    FXCollections.observableArrayList(productModel.getProducts())
            );
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Failed to load products");
        }
    }

    private void setupTableColumns() {
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
    }

    private void calculateNetTotal() {
        double netTotal = cartList.stream()
                .mapToDouble(CartTM::getTotalPrice)
                .sum();
        lblNetTotal.setText(String.format("%.2f", netTotal));
    }

    private void showAlert(Alert.AlertType type, String msg) {
        new Alert(type, msg).showAndWait();
    }
}
