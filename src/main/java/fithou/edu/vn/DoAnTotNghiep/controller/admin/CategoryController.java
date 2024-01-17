package fithou.edu.vn.DoAnTotNghiep.controller.admin;

import fithou.edu.vn.DoAnTotNghiep.category.commands.createCategory.CreateCategoryCommand;
import fithou.edu.vn.DoAnTotNghiep.category.commands.updateCategory.UpdateCategoryCommand;
import fithou.edu.vn.DoAnTotNghiep.category.query.getAllCategories.GetAllCategoriesQueries;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.ISender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private ISender sender;

//    @GetMapping()
//    //@Secured("CATEGORY_MANAGEMENT")
//    public String getCategories(Model model, CreateCategoryCommand createCategoryCommand) {
//        var page = new GetAllCategoriesQueries();
//        page.setPageSize(100);
//        var allCategories = sender.send(page).get();
//        model.addAttribute("categories", allCategories);
//        model.addAttribute("createCategoryRequest", createCategoryCommand);
//        model.addAttribute("updateCategoryRequest", new UpdateCategoryCommand());
//        return "admin/category/index";
//    }
    @GetMapping()
    public String getCategories(Model model) {
        return "admin/category/index";
    }
}
