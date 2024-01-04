package fithou.edu.vn.DoAnTotNghiep.auth.commands.resetPassword;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class ResetPasswordCommand implements IRequest<Void> {

    private String token;

    @Length(min = 8, message = "Mật khẩu phải có ít nhất 8 ký tự")
    private String newPassword;
}
