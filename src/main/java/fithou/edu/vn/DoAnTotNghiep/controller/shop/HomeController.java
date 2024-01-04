package fithou.edu.vn.DoAnTotNghiep.controller.shop;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
