package fithou.edu.vn.DoAnTotNghiep.controller.shop;

import fithou.edu.vn.DoAnTotNghiep.auth.commands.login.LoginRequest;
import fithou.edu.vn.DoAnTotNghiep.auth.commands.register.RegisterCommand;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

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
}
