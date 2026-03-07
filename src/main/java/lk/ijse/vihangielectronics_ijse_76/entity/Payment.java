package lk.ijse.vihangielectronics_ijse_76.entity;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Payment {
    private String paymentId;
    private String orderId;
    private LocalDate date;
    private int amount;
    private String paymentMethod;
    private String status;
}
