package lk.ijse.vihangielectronics_ijse_76.entity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetails {
    private String order_id;
    private String item_id;
    private int quantity;
    private  double unit_price;

}
