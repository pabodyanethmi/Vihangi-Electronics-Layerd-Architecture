package lk.ijse.vihangielectronics_ijse_76.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Supplier {
    private String supplierId;
    private String name;
    private String contact;
    private String email;
    private String address;
}