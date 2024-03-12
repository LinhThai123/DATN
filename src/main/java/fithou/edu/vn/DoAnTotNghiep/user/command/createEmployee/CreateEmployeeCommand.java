package fithou.edu.vn.DoAnTotNghiep.user.command.createEmployee;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateEmployeeCommand implements IRequest<String> {

    @NotEmpty(message = "Tên nhân viên không được để trống")
    @Length(min = 1, max = 50, message = "Tên nhà cung cấp phải từ 1 đến 50 ký tự")
    private String name;

    @NotEmpty(message = "Email không được để trống")
    @Email
    private String email;

    private String accountName;

    private String avartar;

    @NotEmpty(message = "Mật khẩu không được để trống")
    @Length(min = 8,message = "Mật khẩu phải có ít nhất 8 ký tự")
    private String password;

    private String gender;

    @Pattern(regexp = "^(\\+84|0)\\d{9,10}$", message = "Số điện thoại không hợp lệ")
    private String numberPhone;

    private String address;

    private Integer status;

    private Date birthDay;

}
