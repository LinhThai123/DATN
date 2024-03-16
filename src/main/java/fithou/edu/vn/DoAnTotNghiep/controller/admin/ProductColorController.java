package fithou.edu.vn.DoAnTotNghiep.controller.admin;

import fithou.edu.vn.DoAnTotNghiep.category.entity.Category;
import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductColor;
import fithou.edu.vn.DoAnTotNghiep.product.service.ProductColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/product_color")
public class ProductColorController {

    @Autowired
    private ProductColorService productColorService;

    @GetMapping()
    @Secured("CATEGORY_MANAGEMENT")
    public String getProductColor(Model model,
                                @RequestParam(defaultValue = "", required = false) String name,
                                @RequestParam(defaultValue = "1", required = false) Integer page) {
        Page<ProductColor> productColor = productColorService.adminGetListProductColor(name, page);
        List<ProductColor> productColors = productColor.getContent();
        model.addAttribute("productColors", productColors);
        model.addAttribute("totalPages", productColor.getTotalPages());
        model.addAttribute("currentPage", productColor.getPageable().getPageNumber() + 1);

        return "admin/productColor/index";
    }
}
