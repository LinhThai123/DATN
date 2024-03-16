package fithou.edu.vn.DoAnTotNghiep.controller.admin;

import fithou.edu.vn.DoAnTotNghiep.product.entity.Capacity;
import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductColor;
import fithou.edu.vn.DoAnTotNghiep.product.repository.CapacityRepository;
import fithou.edu.vn.DoAnTotNghiep.product.service.CapacityService;
import fithou.edu.vn.DoAnTotNghiep.product.service.ProductColorService;
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
@RequestMapping("/admin/capacity")
public class CapacityController {

    @Autowired
    private CapacityService capacityService;

    @GetMapping()
    @Secured("CATEGORY_MANAGEMENT")
    public String getCapacity(Model model,
                                  @RequestParam(defaultValue = "", required = false) String name,
                                  @RequestParam(defaultValue = "1", required = false) Integer page) {
        Page<Capacity> capacity = capacityService.adminGetListProductColor(name, page);
        List<Capacity> capacities = capacity.getContent();
        model.addAttribute("capacities", capacities);
        model.addAttribute("totalPages", capacity.getTotalPages());
        model.addAttribute("currentPage", capacity.getPageable().getPageNumber() + 1);

        return "admin/capacity/index";
    }
}
