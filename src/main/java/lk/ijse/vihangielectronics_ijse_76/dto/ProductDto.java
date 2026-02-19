package lk.ijse.vihangielectronics_ijse_76.dto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductDto {
    private String productId;
    private String supplierId;
    private String name;
    private Double pricePerUnit;
    private int quantity;



}