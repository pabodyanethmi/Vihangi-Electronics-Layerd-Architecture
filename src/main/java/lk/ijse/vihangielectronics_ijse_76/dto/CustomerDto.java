package lk.ijse.vihangielectronics_ijse_76.dto;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerDto {

    private String customerId;
    private String name;
    private String contact;
    private String email;
    private String  address;

    @Override
    public String toString() {
        return customerId + " - " + name;
    }

}