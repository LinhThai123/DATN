package fithou.edu.vn.DoAnTotNghiep.controller.shop;

import fithou.edu.vn.DoAnTotNghiep.auth.commands.forgotPassword.ForgotPasswordCommand;
import fithou.edu.vn.DoAnTotNghiep.auth.commands.login.LoginRequest;
import fithou.edu.vn.DoAnTotNghiep.auth.commands.register.RegisterCommand;
import fithou.edu.vn.DoAnTotNghiep.auth.commands.resetPassword.ResetPasswordCommand;
import fithou.edu.vn.DoAnTotNghiep.auth.jwt.JwtService;
import fithou.edu.vn.DoAnTotNghiep.auth.security.CustomUserDetails;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.ISender;
import fithou.edu.vn.DoAnTotNghiep.common.dto.NotificationDto;
import fithou.edu.vn.DoAnTotNghiep.common.response.JwtResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    @Autowired
    private ISender sender;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/register")
    public String Register(Model model,
                           @ModelAttribute("registerCommand") RegisterCommand registerCommand) {
        model.addAttribute("registerCommand", registerCommand);
        return "register";
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response) {

        // Lấy thông tin người dùng từ form đăng nhập
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        Map<String, Object> claims = new HashMap<>();
        claims.put("email", userDetails.getUser().getEmail());
        claims.put("userId", userDetails.getUser().getId());
        //for 15 minutes
        String token = jwtService.generateToken(claims, 15 * 60 * 1000);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        JwtResponse jwtResponse;

        if (userDetails.getUser() != null) {
            jwtResponse = new JwtResponse(token ,userDetails.getUser().getName() ,
                    userDetails.getUser().getEmail() ,roles );
            return ResponseEntity.ok(jwtResponse);
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }

//    @GetMapping("/login")
//    public String Login(Model model,
//                        @ModelAttribute("loginRequest") LoginRequest loginRequest) {
//        model.addAttribute("loginRequest", loginRequest);
//        return "login";
//    }

//    @GetMapping("/login")
//    public String login(Model model, Authentication authentication) {
//        model.addAttribute("loginRequest", LoginRequest.builder().build());
//        if (authentication != null) {
//            var redirectUrl = "redirect:/?" + NotificationDto.builder()
//                    .title("Đăng nhập thành công")
//                    .type("success")
//                    .content("")
//                    .build().toParams();
//
//            return redirectUrl;
//        }
//        return "login";
//    }

    @PostMapping("/register")
    public String processLoginRegister(@Valid @ModelAttribute("registerCommand") RegisterCommand registerCommand, BindingResult registerResult,
                                       Model model) {

        // Process registration
        var result = sender.send(registerCommand);
        if (result.isOk()) {
            model.addAttribute("notification", NotificationDto.builder()
                    .title("Đăng ký thành công")
                    .content("Vui lòng kiểm tra email để kích hoạt tài khoản")
                    .build());
            // Redirect to login page
            return "redirect:/v1/auth/login";
        }
        registerResult.addError(new ObjectError("registerCommand", result.getError()));
        return "register";
    }

    @GetMapping("/forgot-password")
    public String forgetPassword (Model model) {
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String createForgotPassword(@RequestParam String email, Model model) {
        var command = new ForgotPasswordCommand(email);
        var result = sender.send(command);
        if (result.isOk()) {
            model.addAttribute("success", true);
            return "forgot-password";
        }
        model.addAttribute("error", result.getError());
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

        return "redirect:/auth/login";
    }

    @GetMapping("/reset-password/success")
    public String resetPasswordSuccess() {
        return "reset-password-success";
    }

}
