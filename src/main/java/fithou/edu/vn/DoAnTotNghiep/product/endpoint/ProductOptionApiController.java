package fithou.edu.vn.DoAnTotNghiep.product.endpoint;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.ISender;
import fithou.edu.vn.DoAnTotNghiep.product.commands.createProductOption.CreateProductOptionCommand;
import fithou.edu.vn.DoAnTotNghiep.product.commands.updateProduct.UpdateProductCommand;
import fithou.edu.vn.DoAnTotNghiep.product.commands.updateProductOption.UpdateProductOptionCommand;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Capacity;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Product;
import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductColor;
import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductOption;
import fithou.edu.vn.DoAnTotNghiep.product.service.ProductOptionService;
import jakarta.validation.Valid;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/product-option")
@AllArgsConstructor
public class ProductOptionApiController {

    private final ISender sender;

    @Autowired
    private ProductOptionService productOptionService;

    @GetMapping("")
    public List<ProductOption> getAllProductOption() {
        return productOptionService.getListProductOption();
    }
    @GetMapping("/{productOptionId}")
    public ProductOption getProductDetailPage (@PathVariable String productOptionId) {
        try {
            return productOptionService.getProductOptionById(productOptionId);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/colors/{productId}")
    public ResponseEntity<List<ProductColor>> getProductColors(@PathVariable String productId) {
        List<ProductColor> colors = productOptionService.getProductColors(productId);
        return ResponseEntity.ok().body(colors);
    }

    @GetMapping("/capacities/{productId}")
    public ResponseEntity<List<Capacity>> getProductCapacities(@PathVariable String productId) {
        List<Capacity> capacities = productOptionService.getCapacity(productId);
        return ResponseEntity.ok().body(capacities);
    }
    @PostMapping("/create")
    public ResponseEntity<String> createProductOption (@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody @Valid CreateProductOptionCommand command) {
        var result = sender.send(command) ;
        return ResponseEntity.ok(result.orThrow());
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateProductOption(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody @Valid UpdateProductOptionCommand command) {
        var result = sender.send(command);
        return ResponseEntity.ok(result.orThrow());
    }
}
