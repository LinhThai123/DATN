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
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
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



    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {

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

            request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

            // Lưu token vào cookie
            Cookie cookie = new Cookie("JWT_TOKEN", token);
            cookie.setSecure(true);
            cookie.setHttpOnly(true);
            cookie.setMaxAge((int) TimeUnit.MILLISECONDS.toSeconds(15 * 60 * 1000)); // 15 minutes
            cookie.setPath("/"); // Đảm bảo rằng cookie có thể được truy cập trên mọi đường dẫn
            response.addCookie(cookie);
            return ResponseEntity.ok(jwtResponse);
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }


    @PostMapping("/register")
    public ResponseEntity<Object> processRegister(@Valid @RequestBody RegisterCommand registerCommand, BindingResult registerResult) {
        if (registerResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid registration data");
        }

        // Process registration
        var result = sender.send(registerCommand);
        if (result.isOk()) {
            // Registration successful
            return ResponseEntity.ok().body(
                    NotificationDto.builder()
                            .title("Đăng ký thành công")
                            .content("Vui lòng kiểm tra email để kích hoạt tài khoản")
                            .build()
            );
        }
        // Registration failed
        return ResponseEntity.badRequest().body(result.getError());
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
