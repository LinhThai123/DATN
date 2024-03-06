package fithou.edu.vn.DoAnTotNghiep.product.endpoint;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.ISender;
import fithou.edu.vn.DoAnTotNghiep.product.commands.createProductOption.CreateProductOptionCommand;
import fithou.edu.vn.DoAnTotNghiep.product.commands.updateProduct.UpdateProductCommand;
import fithou.edu.vn.DoAnTotNghiep.product.commands.updateProductOption.UpdateProductOptionCommand;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/product-option")
@AllArgsConstructor
public class ProductOptionApiController {
    private final ISender sender;

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
