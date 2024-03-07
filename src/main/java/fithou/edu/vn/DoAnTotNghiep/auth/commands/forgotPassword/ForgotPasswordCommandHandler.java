package fithou.edu.vn.DoAnTotNghiep.auth.commands.forgotPassword;

import fithou.edu.vn.DoAnTotNghiep.auth.jwt.JwtService;
import fithou.edu.vn.DoAnTotNghiep.auth.repository.IUserRepository;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.config.AppProperties;
import fithou.edu.vn.DoAnTotNghiep.mail.MailService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class ForgotPasswordCommandHandler implements IRequestHandler<ForgotPasswordCommand, Void> {
    private final JwtService jwtService;
    private final IUserRepository iUserRepository;
    private final MailService mailService;
    private final AppProperties appProperties;
    @Override
    public HandleResponse<Void> handle(ForgotPasswordCommand forgotPasswordCommand) throws Exception {
        var user = iUserRepository.findByEmail(forgotPasswordCommand.getEmail());
        if (user.isEmpty()) {
            return HandleResponse.error("Email không tồn tại");
        }
        if (!user.get().isAccountEnabled()) {
            return HandleResponse.error("Tài khoản đã bị khóa");
        }
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", user.get().getEmail());
        claims.put("userId", user.get().getId());

        String token = jwtService.generateToken(claims, 15*60*1000) ;
        var to = user.get().getEmail();
        var subject = "Reset Password";
        var url = appProperties.getHost() + "/reset-password?token=" + token;
        var content = "Nhấn vào link sau để reset password: " + url;
        CompletableFuture.runAsync(() -> {
            try {
                mailService.sendEmail(to, subject, content);
            }
            catch (MessagingException e) {
                e.printStackTrace();
            }
        });
        return HandleResponse.ok();
    }
}
