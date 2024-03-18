package fithou.edu.vn.DoAnTotNghiep.controller.admin;

import fithou.edu.vn.DoAnTotNghiep.blog.entity.Blog;
import fithou.edu.vn.DoAnTotNghiep.category.entity.Category;
import fithou.edu.vn.DoAnTotNghiep.category.service.CategoryService;
import fithou.edu.vn.DoAnTotNghiep.common.dto.PaginatedDto;
import fithou.edu.vn.DoAnTotNghiep.product.commands.createProduct.CreateProductCommand;
import fithou.edu.vn.DoAnTotNghiep.product.entity.*;
import fithou.edu.vn.DoAnTotNghiep.product.service.*;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/product")
@Secured("PRODUCT_MANAGEMENT")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SpecificationService specificationService;

    @Autowired
    private ProductOptionService productOptionService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private ProductColorService productColorService;

    @Autowired
    private CapacityService capacityService;



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

    @GetMapping("/create")
    public String getProductCreatePage(Model model, CreateProductCommand rep ) {
        // Get list category
        List<Category> categories = categoryService.getListCategory();
        model.addAttribute("categories", categories);

        // Get list brand
        List<Brand> brands = brandService.getListBrand();
        model.addAttribute("brands", brands);

        // Get list color
        List<ProductColor> productColors = productColorService.getListProductColor();
        model.addAttribute("productColors" , productColors);

        List<Capacity> capacities = capacityService.getListCapacity();
        model.addAttribute("capacities" , capacities) ;

        model.addAttribute("product", rep);
        return "admin/product/create";
    }
    @GetMapping("/{slug}")
    public String getProductDetailPage (Model model, @PathVariable String slug) throws NotFoundException {

        Product product = productService.getProductBySlug(slug);

        List<Specification> specifications = specificationService.getSpecificationByProductId(product.getId());
        product.setSpecifications(specifications);

        List<ProductOption> productOptions = productOptionService.getProductOptionsByProductId(product.getId());
        product.setProductOptions(productOptions);

        List<ProductColor> productColors = productColorService.getListProductColor();
        model.addAttribute("productColors" , productColors);

        List<Capacity> capacities = capacityService.getListCapacity();
        model.addAttribute("capacites" , capacities);

        model.addAttribute("id", product.getId());
        // Get list category
        List<Category> categories = categoryService.getListCategory();
        model.addAttribute("categories", categories);
        // Get list brand
        List<Brand> brands = brandService.getListBrand();

        model.addAttribute("brands", brands);

        model.addAttribute("product", product);

        return "admin/product/detail" ;
    }

}
