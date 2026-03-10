package lk.ijse.vihangielectronics_ijse_76.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartTM {
    private String productId;
    private String productName;
    private int qty;
    private double unitPrice;
    private double totalPrice;

}
