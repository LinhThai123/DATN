package fithou.edu.vn.DoAnTotNghiep.auth.commands.resetPassword;

import fithou.edu.vn.DoAnTotNghiep.auth.jwt.JwtService;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.SignatureException;

@Service
@AllArgsConstructor
public class ResetPasswordCommandHandler implements IRequestHandler<ResetPasswordCommand, Void> {
    private final UserDetailsPasswordService userDetailsService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailService;
    @Override
    public HandleResponse<Void> handle(ResetPasswordCommand resetPasswordCommand) throws Exception {
        String email;
        try {
            email = jwtService.getValue(resetPasswordCommand.getToken(), c -> c.get("email", String.class));
            if (email == null || email.isEmpty()) {
                return HandleResponse.error("Token không hợp lệ");
            }
        }catch (SignatureException e) {
            return HandleResponse.error("Token không hợp lệ");
        } catch (ExpiredJwtException e) {
            return HandleResponse.error("Token đã hết hạn, Vui lòng thử lại");
        }
        var user = userDetailService.loadUserByUsername(email);
        userDetailsService.updatePassword(user, resetPasswordCommand.getNewPassword());
        return HandleResponse.ok();
    }
}
