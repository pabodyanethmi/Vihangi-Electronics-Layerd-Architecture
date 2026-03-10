package lk.ijse.vihangielectronics_ijse_76.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {

    private String customerId;
    private String name;
    private String contact;
    private String email;
    private String address;

    @Override
    public String toString() {
        return customerId + " - " + name;
    }

}