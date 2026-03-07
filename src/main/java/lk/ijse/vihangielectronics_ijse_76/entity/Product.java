package lk.ijse.vihangielectronics_ijse_76.entity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Product {
    private String productId;
    private String supplierId;
    private String name;
    private Double pricePerUnit;
    private int quantity;
}