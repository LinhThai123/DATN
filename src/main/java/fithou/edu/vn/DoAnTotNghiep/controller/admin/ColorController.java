package fithou.edu.vn.DoAnTotNghiep.controller.admin;

import fithou.edu.vn.DoAnTotNghiep.category.entity.Category;
import fithou.edu.vn.DoAnTotNghiep.category.service.CategoryService;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.ISender;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Color;
import fithou.edu.vn.DoAnTotNghiep.product.service.ColorService;
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
@RequestMapping("/admin/color")
public class ColorController {

    @Autowired
    private ColorService colorService;

    // Hiện thị màu sắc tìm kiếm theo tên và phân trang
    @GetMapping()
    @Secured("CATEGORY_MANAGEMENT")
    public String getCategories(Model model,
                                @RequestParam(defaultValue = "", required = false) String name,
                                @RequestParam(defaultValue = "1", required = false) Integer page) {
        Page<Color> color = colorService.adminGetListColor(name, page);
        List<Color> colors = color.getContent();
        model.addAttribute("colors", colors);
        model.addAttribute("totalPages", color.getTotalPages());
        model.addAttribute("currentPage", color.getPageable().getPageNumber() + 1);

        return "admin/color/index";
    }
}
