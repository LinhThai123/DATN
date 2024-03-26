package fithou.edu.vn.DoAnTotNghiep.controller.admin;

import fithou.edu.vn.DoAnTotNghiep.product.commands.createProduct.CreateProductCommand;
import fithou.edu.vn.DoAnTotNghiep.promotion.commands.createPromotion.CreatePromotionCommand;
import fithou.edu.vn.DoAnTotNghiep.promotion.entity.Promotion;
import fithou.edu.vn.DoAnTotNghiep.promotion.service.PromotionService;
import fithou.edu.vn.DoAnTotNghiep.supplier.entity.Supplier;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/promotion")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @GetMapping()
    public String getPromotionManagePage(Model model,
                                         @RequestParam(defaultValue = "1") int page,
                                         @RequestParam(defaultValue = "") String active,
                                         @RequestParam(defaultValue = "") String code,
                                         @RequestParam(defaultValue = "") String name)
    {
        Page<Promotion> result = promotionService.adminGetListPromotion(code, name, active, page);
        model.addAttribute("promotions", result.getContent());
        model.addAttribute("totalPages", result.getTotalPages());
        model.addAttribute("currentPage", result.getPageable().getPageNumber()+1);
        return "admin/promotion/index";
    }
    @GetMapping("/create")
    public String getPromotionCreatePage (Model model, CreatePromotionCommand rep ) {
        model.addAttribute("promotion", rep);
        return "admin/promotion/create";
    }
    @GetMapping("/{code}")
    public String getPromotionDetailPage (Model model, @PathVariable String code) throws NotFoundException {
        Promotion promotion = promotionService.getPromotonByCode(code);
        model.addAttribute("id", promotion.getId());
        model.addAttribute("promotion", promotion);
        return "admin/promotion/detail" ;
    }
}
