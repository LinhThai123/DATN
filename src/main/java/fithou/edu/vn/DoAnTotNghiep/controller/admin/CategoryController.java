package fithou.edu.vn.DoAnTotNghiep.controller.admin;

import fithou.edu.vn.DoAnTotNghiep.category.commands.createCategory.CreateCategoryCommand;
import fithou.edu.vn.DoAnTotNghiep.category.commands.updateCategory.UpdateCategoryCommand;
import fithou.edu.vn.DoAnTotNghiep.category.dto.CategoryDto;
import fithou.edu.vn.DoAnTotNghiep.category.entity.Category;
import fithou.edu.vn.DoAnTotNghiep.category.query.getAllCategories.GetAllCategoriesQueries;
import fithou.edu.vn.DoAnTotNghiep.category.service.CategoryService;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.ISender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private ISender sender;

    @Autowired
    private CategoryService categoryService;

    // Hiện thị danh mục tìm kiếm theo tên và phân trang
    @GetMapping()
    @Secured("CATEGORY_MANAGEMENT")
    public String getCategories(Model model,
                           @RequestParam(defaultValue = "", required = false) String name,
                           @RequestParam(defaultValue = "1", required = false) Integer page) {
        Page<Category> category = categoryService.adminGetListCategory(name, page);
        List<Category> categories = category.getContent();
        model.addAttribute("categories", categories);
        model.addAttribute("totalPages", category.getTotalPages());
        model.addAttribute("currentPage", category.getPageable().getPageNumber() + 1);

        return "admin/category/index";
    }


}
