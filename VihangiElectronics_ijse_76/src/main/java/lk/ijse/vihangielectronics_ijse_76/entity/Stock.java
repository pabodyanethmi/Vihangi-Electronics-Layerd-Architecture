package lk.ijse.vihangielectronics_ijse_76.entity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Stock  {
    private int stockId;
    private String productId;
    private int stockQuantity;
}
