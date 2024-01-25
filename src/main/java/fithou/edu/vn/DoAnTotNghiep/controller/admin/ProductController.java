package fithou.edu.vn.DoAnTotNghiep.controller.admin;

import fithou.edu.vn.DoAnTotNghiep.category.entity.Category;
import fithou.edu.vn.DoAnTotNghiep.category.service.CategoryService;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.ISender;
import fithou.edu.vn.DoAnTotNghiep.common.dto.PaginatedDto;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Brand;
import fithou.edu.vn.DoAnTotNghiep.product.service.BrandService;
import fithou.edu.vn.DoAnTotNghiep.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/product")
public class ProductController {

    @Autowired
    private ISender sender;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;


    @GetMapping("")
    public String getProductManagePage(Model model,
                                       @RequestParam(defaultValue = "1") int page,
                                       @RequestParam(defaultValue = "product.created_at") String order,
                                       @RequestParam(defaultValue = "desc") String direction,
                                       @RequestParam(defaultValue = "") String id,
                                       @RequestParam(defaultValue = "") String name,
                                       @RequestParam(defaultValue = "%%") String brand,
                                       @RequestParam(defaultValue = "%%") String category) {
        if (!direction.toLowerCase().equals("desc")) {
            direction = "asc";
        }

        // Get list category
        List<Category> categories = categoryService.getListCategory();
        model.addAttribute("categories", categories);

        // Get list brand
        List<Brand> brands = brandService.getListBrand();
        model.addAttribute("brands", brands);

        // Get list product
        PaginatedDto rs = productService.adminGetListProduct(id, name, category, brand, order, direction, page);
        model.addAttribute("products", rs.getItems());
        model.addAttribute("totalPages", rs.getTotalPages());
        model.addAttribute("currentPage", rs.getCurrentPage());

        return "admin/product/index";
    }
}
