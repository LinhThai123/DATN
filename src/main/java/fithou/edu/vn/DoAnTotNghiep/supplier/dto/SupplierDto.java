package fithou.edu.vn.DoAnTotNghiep.supplier.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SupplierDto {
    private String id;
    private String name;
    private String address;
    private Integer status;
    private String numberPhone;
    private String email;
    private String description;
    private String bank;
    private String accountNumber;
}
