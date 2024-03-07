package fithou.edu.vn.DoAnTotNghiep.controller.shop;

import fithou.edu.vn.DoAnTotNghiep.auth.commands.forgotPassword.ForgotPasswordCommand;
import fithou.edu.vn.DoAnTotNghiep.auth.commands.login.LoginRequest;
import fithou.edu.vn.DoAnTotNghiep.auth.commands.register.RegisterCommand;
import fithou.edu.vn.DoAnTotNghiep.auth.commands.resetPassword.ResetPasswordCommand;
import fithou.edu.vn.DoAnTotNghiep.auth.jwt.JwtService;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.ISender;
import fithou.edu.vn.DoAnTotNghiep.common.dto.NotificationDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private ISender sender;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/")
    public String index() {
        return "shop/index";
    }

        @GetMapping("/login")
    public String Login(Model model,
                        @ModelAttribute("loginRequest") LoginRequest loginRequest) {
        model.addAttribute("loginRequest", loginRequest);
        return "login";
    }

    @GetMapping("/register")
    public String Register(Model model,
                           @ModelAttribute("registerCommand") RegisterCommand registerCommand) {
        model.addAttribute("registerCommand", registerCommand);
        return "register";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword(Model model) {
        model.addAttribute("forgotPasswordCommand", new ForgotPasswordCommand());
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String createForgotPassword(@ModelAttribute("forgotPasswordCommand") ForgotPasswordCommand forgotPasswordCommand, Model model) {
        var result = sender.send(forgotPasswordCommand);
        if (result.isOk()) {
            model.addAttribute("success", true);
        } else {
            model.addAttribute("error", result.getError());
        }
        return "forgot-password";
    }

    @GetMapping("/reset-password")
    public String resetPassword(@RequestParam String token, Model model) {
        try {
            var email = jwtService.getValue(token, c -> c.get("email", String.class));
        } catch (Exception e) {
            model.addAttribute("error", "Token không hợp lệ");
            return "forgot-password";
        }
        var command = new ResetPasswordCommand();
        command.setToken(token);
        model.addAttribute("resetPasswordCommand", command);
        return "reset-password";
    }
    @PostMapping("/reset-password")
    public String changePassword(@Valid ResetPasswordCommand resetPasswordCommand, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "reset-password";
        }
        var result = sender.send(resetPasswordCommand);
        if(result.hasError()){
            model.addAttribute("error", result.getError());
            return "reset-password";
        }

        return "redirect:/login";
    }
}
