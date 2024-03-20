package fithou.edu.vn.DoAnTotNghiep.controller.shop;

import fithou.edu.vn.DoAnTotNghiep.auth.security.CustomUserDetails;
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
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService ;

    @GetMapping("/")
    public String HomePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                String username = userDetails.getUsername();
                Optional<User> userOptional = userService.getUserInfo(username);
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    model.addAttribute("user", user);
                }
            }
        }
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
