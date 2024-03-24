package fithou.edu.vn.DoAnTotNghiep.controller.shop;

import fithou.edu.vn.DoAnTotNghiep.auth.security.CustomUserDetails;
import fithou.edu.vn.DoAnTotNghiep.blog.entity.Blog;
import fithou.edu.vn.DoAnTotNghiep.blog.service.BlogService;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Product;
import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductColor;
import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductOption;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Specification;
import fithou.edu.vn.DoAnTotNghiep.product.service.ProductOptionService;
import fithou.edu.vn.DoAnTotNghiep.product.service.ProductService;
import fithou.edu.vn.DoAnTotNghiep.product.service.SpecificationService;
import fithou.edu.vn.DoAnTotNghiep.user.entity.User;
import fithou.edu.vn.DoAnTotNghiep.user.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class ShopController {

    @Autowired
    private ProductService productService;

    @Autowired
    private SpecificationService specificationService;

    @Autowired
    private ProductOptionService productOptionService;

    @Autowired
    private UserService userService;

    @Autowired
    private BlogService blogService;


    @GetMapping("/")
    public String HomePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof CustomUserDetails) {
                CustomUserDetails userDetails = (CustomUserDetails) principal;
                String username = userDetails.getName();
                String role = userDetails.getAuthorities().iterator().next().getAuthority();
                // Thêm thông tin vào model để truyền vào view
                model.addAttribute("username", username);
                model.addAttribute("role", role);
            } else {
                String username = authentication.getName();
                model.addAttribute("username", username);
                String role = authentication.getAuthorities().iterator().next().getAuthority();
                model.addAttribute("role", role);
            }
        }
        String idCategory = "BgUupTg262gnP5O5";
        List<Product> products = productService.findByCategoryId(idCategory);
        model.addAttribute("products", products);

        int statusBlog = 1 ;
        List<Blog> blogs = blogService.getListBlogByStatus(statusBlog);
        model.addAttribute("blogs", blogs);
        return "shop/index";
    }

    @GetMapping("/detail/{slug}")
    public String ProductDetailPage(Model model, @PathVariable String slug) throws NotFoundException {

        Product product = productService.getProductBySlug(slug);

        List<Specification> specifications = specificationService.getSpecificationByProductId(product.getId());
        product.setSpecifications(specifications);

        List<ProductOption> productOptions = productOptionService.getProductOptionsByProductId(product.getId());
        product.setProductOptions(productOptions);

        List<ProductColor> productColors = productOptionService.getProductColors(product.getId());
        model.addAttribute("productColors", productColors) ;

        model.addAttribute("product" , product);

        return "shop/detail";
    }
}
