package fithou.edu.vn.DoAnTotNghiep.controller.admin;

import fithou.edu.vn.DoAnTotNghiep.supplier.entity.Supplier;
import fithou.edu.vn.DoAnTotNghiep.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @GetMapping()
//    @Secured("BLOG_MANAGEMENT")
    public String getSupplier(Model model,
                            @RequestParam(defaultValue = "", required = false) String name,
                            @RequestParam(defaultValue = "1", required = false) Integer page) {
        Page<Supplier> supplier = supplierService.adminGetListSuppiler(name, page);
        List<Supplier> suppliers = supplier.getContent();
        model.addAttribute("suppliers", suppliers);
        model.addAttribute("totalPages", supplier.getTotalPages());
        model.addAttribute("currentPage", supplier.getPageable().getPageNumber() + 1);

        return "admin/supplier/index";
    }
}
