package fithou.edu.vn.DoAnTotNghiep.auth.commands.register;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import fithou.edu.vn.DoAnTotNghiep.user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
@Getter
@Setter
@Builder
public class RegisterCommand implements IRequest<User> {

    @NotEmpty(message = "Họ và tên đệm không được để trống")
    private String name;

    @NotEmpty(message = "Tên không được để trống")
    private String accountName;

    @NotEmpty(message = "Email không được để trống")
    @Email
    private String email;

    @NotEmpty(message = "Mật khẩu không được để trống")
    @Length(min = 8,message = "Mật khẩu phải có ít nhất 8 ký tự")
    private String password;

}
