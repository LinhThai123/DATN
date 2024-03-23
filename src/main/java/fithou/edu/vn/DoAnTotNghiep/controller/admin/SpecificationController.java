package fithou.edu.vn.DoAnTotNghiep.controller.admin;

import fithou.edu.vn.DoAnTotNghiep.category.entity.Category;
import fithou.edu.vn.DoAnTotNghiep.product.commands.createSpecification.CreateSpecificationCommand;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Product;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Specification;
import fithou.edu.vn.DoAnTotNghiep.product.service.ProductService;
import fithou.edu.vn.DoAnTotNghiep.product.service.SpecificationService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/specification")
public class SpecificationController {

    @Autowired
    private ProductService productService;

    @Autowired
    private SpecificationService specificationService;
    @GetMapping("/create")
    public String getProductSpecificationPage (Model model, CreateSpecificationCommand command) throws NotFoundException {
        List<Product> products = productService.getListProduct();
        model.addAttribute("products", products);
        model.addAttribute("specification", command);
        return "admin/product/specification";
    }
    @GetMapping("/{id}")
    public String getProductSpecificationDetailPage (Model model, @PathVariable String id) throws NotFoundException {
        Specification specification = specificationService.getSpecificationById(id);
        model.addAttribute("specification", specification);

        List<Product> products = productService.getListProduct();
        model.addAttribute("products", products);
        return "admin/product/specification_detail";
    }
}
