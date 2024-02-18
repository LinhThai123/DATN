package fithou.edu.vn.DoAnTotNghiep.controller.admin;

import fithou.edu.vn.DoAnTotNghiep.blog.commands.createBlog.CreateBlogCommand;
import fithou.edu.vn.DoAnTotNghiep.blog.entity.Blog;
import fithou.edu.vn.DoAnTotNghiep.blog.service.BlogService;
import fithou.edu.vn.DoAnTotNghiep.category.entity.Category;
import fithou.edu.vn.DoAnTotNghiep.product.commands.createProduct.CreateProductCommand;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Brand;
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
@RequestMapping("/admin/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping()
    @Secured("BLOG_MANAGEMENT")
    public String getBrands(Model model,
                            @RequestParam(defaultValue = "", required = false) String title,
                            @RequestParam(defaultValue = "1", required = false) Integer page) {
        Page<Blog> blog = blogService.adminGetListBlog(title, page);
        List<Blog> blogs = blog.getContent();
        model.addAttribute("blogs", blogs);
        model.addAttribute("totalPages", blog.getTotalPages());
        model.addAttribute("currentPage", blog.getPageable().getPageNumber() + 1);

        return "admin/blog/index";
    }

    @GetMapping("/create")
    public String getBlogCreatePage(Model model, CreateBlogCommand rep ) {
        model.addAttribute("blog", rep);
        return "admin/blog/create";
    }

}
