package fithou.edu.vn.DoAnTotNghiep.controller.admin;

import fithou.edu.vn.DoAnTotNghiep.auth.security.CustomUserDetails;
import fithou.edu.vn.DoAnTotNghiep.blog.entity.Blog;
import fithou.edu.vn.DoAnTotNghiep.product.dto.ProductDTO;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Capacity;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Product;
import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductColor;
import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductOption;
import fithou.edu.vn.DoAnTotNghiep.product.service.CapacityService;
import fithou.edu.vn.DoAnTotNghiep.product.service.ProductColorService;
import fithou.edu.vn.DoAnTotNghiep.product.service.ProductOptionService;
import fithou.edu.vn.DoAnTotNghiep.product.service.ProductService;
import fithou.edu.vn.DoAnTotNghiep.receipt.entity.Receipt;
import fithou.edu.vn.DoAnTotNghiep.receipt.entity.ReceiptItem;
import fithou.edu.vn.DoAnTotNghiep.receipt.service.ReceiptService;
import fithou.edu.vn.DoAnTotNghiep.supplier.entity.Supplier;
import fithou.edu.vn.DoAnTotNghiep.supplier.service.SupplierService;
import fithou.edu.vn.DoAnTotNghiep.user.entity.User;
import fithou.edu.vn.DoAnTotNghiep.user.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin/stock-receipt")
public class StockReceiptController {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private CapacityService capacityService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductOptionService productOptionService;

    @Autowired
    private ProductColorService productColorService;

    @Autowired
    private ReceiptService receiptService;
    @GetMapping("")
    public String getReceipt(Model model,
                             @RequestParam(defaultValue = "", required = false) String employeeName,
                             @RequestParam(defaultValue = "", required = false) String supplierName,
                             @RequestParam(defaultValue = "1", required = false) Integer page) {
        Page<Receipt> receipt = receiptService.adminGetListReceipt(employeeName, supplierName, page);
        model.addAttribute("receipts", receipt.getContent());
        model.addAttribute("totalPages", receipt.getTotalPages());
        model.addAttribute("currentPage", receipt.getPageable().getPageNumber() + 1);
        return "admin/receipt/index";
    }
    @GetMapping("/create")
    public String getStockReceiptPage (Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails details = (CustomUserDetails) authentication.getPrincipal();
            User user = details.getUser();

            model.addAttribute("employee" , user);

            List<Supplier> suppliers = supplierService.getListSupplier();
            model.addAttribute("suppliers", suppliers);

            List<Capacity> capacities = capacityService.getListCapacity();
            model.addAttribute("capacities" , capacities);

            List<ProductDTO> products = productService.getProductsWithProductOptionIds();
            model.addAttribute("products" , products) ;

            List<ProductOption> productOptions = productOptionService.getListProductOption();
            model.addAttribute("productOptions" , productOptions);

            List<ProductColor> productColors = productColorService.getListProductColor();
            model.addAttribute("productColors" , productColors);
        }
        return "admin/receipt/create" ;
    }

    @GetMapping("/{id}")
    public String getReceiptDetailPage (Model model, @PathVariable String id) throws NotFoundException {

        List<Supplier> suppliers = supplierService.getListSupplier();
        model.addAttribute("suppliers", suppliers);

        Receipt receipt = receiptService.getReceiptById(id);
        model.addAttribute("receipt" , receipt);
        model.addAttribute("user" , receipt.getEmployee()) ;

        List<ReceiptItem> receiptItems = receiptService.getReceiptItemByReceiptId(receipt.getId());
        model.addAttribute("receiptItems" , receiptItems);

        Set<ProductOption> productOptions = receiptService.getProductOptionByReceiptId(id);
        model.addAttribute("productOptions" , productOptions) ;

        Set<ProductColor> productColors = new HashSet<>();
        for (ProductOption productOption : productOptions) {
            productColors.addAll(productOptionService.getProductColorByProductOptionId(productOption.getId()));
        }
        model.addAttribute("productColors" , productColors);

        Set<Capacity> capacities = new HashSet<>();
        for (ProductOption productOption : productOptions) {
            capacities.addAll(productOptionService.getCapacityByProductOptionId(productOption.getId()));
        }
        model.addAttribute("capacities" , capacities);

        // Lấy và thêm thông tin sản phẩm liên quan vào model
        List<Product> products = new ArrayList<>();
        for (ProductOption productOption : productOptions) {
            products.add(productOptionService.getProductByProductOptionId(productOption.getId()));
        }
        model.addAttribute("products", products);

        return "admin/receipt/detail" ;
    }

}
