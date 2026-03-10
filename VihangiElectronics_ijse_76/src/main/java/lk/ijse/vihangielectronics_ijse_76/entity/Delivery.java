package lk.ijse.vihangielectronics_ijse_76.entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Delivery {
    private String delivery_id;
    private String order_id;
    private LocalDate date;
    private LocalTime time;
    private String address;
    private String status;
}
