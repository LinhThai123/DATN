package fithou.edu.vn.DoAnTotNghiep.controller.admin;

import fithou.edu.vn.DoAnTotNghiep.category.entity.Category;
import fithou.edu.vn.DoAnTotNghiep.product.commands.createProduct.CreateProductCommand;
import fithou.edu.vn.DoAnTotNghiep.product.commands.createProductOption.CreateProductOptionCommand;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Brand;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Product;
import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductOption;
import fithou.edu.vn.DoAnTotNghiep.product.service.ProductOptionService;
import fithou.edu.vn.DoAnTotNghiep.product.service.ProductService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/product-option")
@Secured("PRODUCT_MANAGEMENT")
public class ProductOptionController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductOptionService productOptionService;

    @GetMapping("/create")
    public String getProductOptionCreatePage(Model model, CreateProductOptionCommand rep ) {
        // Get list category
        List<Product> products = productService.getListProduct();
        model.addAttribute("products", products);

        // Get list brand
        model.addAttribute("productOption", rep);
        return "admin/product/createProductOption";
    }

    @GetMapping("/{id}")
    public String getProductOptionDetailPage (Model model, @PathVariable String id) throws NotFoundException {
        ProductOption productOption = productOptionService.getProductOptionById(id);
        model.addAttribute("id", productOption.getId());
        model.addAttribute("productOption", productOption);
        return "admin/product/detail" ;
    }

}
