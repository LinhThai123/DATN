package fithou.edu.vn.DoAnTotNghiep.supplier.commands.createSupplier;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateSupplierCommand implements IRequest<String> {

    private String id ;

    @NotEmpty(message = "Tên nhà cung cấp không được để trống")
    @Length(min = 1, max = 50, message = "Tên nhà cung cấp phải từ 1 đến 50 ký tự")
    private String name;

    @NotEmpty(message = "Email không được để trống")
    @Email
    private String email;

    @Pattern(regexp = "^(\\+84|0)\\d{9,10}$", message = "Số điện thoại không hợp lệ")
    private String numberPhone;

    @NotEmpty(message = "Địa chỉ không được trống")
    private String address;

    private Integer status;

    @NotEmpty(message = "Ngân hàng không được trống")
    @NotNull(message = "Ngân hàng không được trống")
    private String bank;

    @NotEmpty(message = "Số tài khoản ngân hàng không được trống")
    @NotNull(message = "Số tài khoản ngân hàng không được trống")
    private String accountNumber;

}
