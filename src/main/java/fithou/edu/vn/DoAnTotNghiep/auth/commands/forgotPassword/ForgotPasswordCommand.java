package fithou.edu.vn.DoAnTotNghiep.auth.commands.forgotPassword;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import jakarta.validation.constraints.Email;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPasswordCommand implements IRequest<Void> {

    @Email(message = "Email không hợp lệ")
    private String email;
}
