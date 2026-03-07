package lk.ijse.vihangielectronics_ijse_76.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import lk.ijse.vihangielectronics_ijse_76.dto.tm.CartTM;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDto {
    private String order_id;
    private LocalDate order_date;
    private LocalTime time;
    private double total_amount;
    private String cus_id;
    private ArrayList<OrderDetailsDto> cartList;


}