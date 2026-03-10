package lk.ijse.vihangielectronics_ijse_76.entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Order {
    private String order_id;
    private LocalDate order_date;
    private LocalTime time;
    private double total_amount;
    private String cus_id;
}