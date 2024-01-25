package fithou.edu.vn.DoAnTotNghiep.controller.admin;

import fithou.edu.vn.DoAnTotNghiep.category.entity.Category;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Brand;
import fithou.edu.vn.DoAnTotNghiep.product.repository.BrandRepository;
import fithou.edu.vn.DoAnTotNghiep.product.service.BrandService;
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
@RequestMapping("/admin/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping()
//    @Secured("CATEGORY_MANAGEMENT")
    public String getBrands(Model model,
                                @RequestParam(defaultValue = "", required = false) String name,
                                @RequestParam(defaultValue = "1", required = false) Integer page) {
        Page<Brand> brand = brandService.adminGetListBrand(name, page);
        List<Brand> brands = brand.getContent();
        model.addAttribute("brands", brands);
        model.addAttribute("totalPages", brand.getTotalPages());
        model.addAttribute("currentPage", brand.getPageable().getPageNumber() + 1);

        return "admin/brand/index";
    }
}
