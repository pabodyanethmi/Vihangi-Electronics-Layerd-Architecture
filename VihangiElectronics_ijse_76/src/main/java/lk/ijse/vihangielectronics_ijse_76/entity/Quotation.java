package lk.ijse.vihangielectronics_ijse_76.entity;

import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Quotation {
    private  int quotationId;
    private String customerId;
    private LocalDate date;
    private String status;

    public Quotation(int quotationId, String customerId, Date date, String status) {
        this.quotationId = quotationId;
        this.customerId = customerId;
        this.date = date.toLocalDate();
        this.status = status;
    }
}
