package fithou.edu.vn.DoAnTotNghiep.controller.admin;

import fithou.edu.vn.DoAnTotNghiep.user.command.createEmployee.CreateEmployeeCommand;
import fithou.edu.vn.DoAnTotNghiep.user.entity.User;
import fithou.edu.vn.DoAnTotNghiep.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/admin/employee")
    public String getEmployee(Model model,
                              @RequestParam(defaultValue = "", required = false) String name,
                              @RequestParam(defaultValue = "1", required = false) Integer page) {
        Page<User> employee= userService.adminGetListEmployee(name, page);
        model.addAttribute("employees", employee.getContent());
        model.addAttribute("totalPages", employee.getTotalPages());
        model.addAttribute("currentPage", employee.getPageable().getPageNumber() + 1);
        return "admin/employee/index";
    }

    @GetMapping("/admin/customer")
    public String getCustomer(Model model,
                              @RequestParam(defaultValue = "", required = false) String name,
                              @RequestParam(defaultValue = "1", required = false) Integer page) {
        Page<User> customer = userService.adminGetListCustomer(name, page);
        model.addAttribute("customers", customer.getContent());
        model.addAttribute("totalPages", customer.getTotalPages());
        model.addAttribute("currentPage", customer.getPageable().getPageNumber() + 1);
        return "admin/customer/index";
    }

    @GetMapping("/admin/employee/create")
    public String getEmployeeCreatePage(Model model, CreateEmployeeCommand rep ) {
        model.addAttribute("employee", rep);
        return "admin/employee/create";
    }
}
