package fithou.edu.vn.DoAnTotNghiep.product.endpoint;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.ISender;
import fithou.edu.vn.DoAnTotNghiep.product.commands.createProduct.CreateProductCommand;
import fithou.edu.vn.DoAnTotNghiep.product.commands.deleteProduct.DeleteProductCommand;
import fithou.edu.vn.DoAnTotNghiep.product.commands.updateProduct.UpdateProductCommand;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/products")
public class ProductApiController {
    @Autowired
    private ISender sender;

//    @Secured("PRODUCT_MANAGEMENT")
    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody @Valid CreateProductCommand command) {
        var result = sender.send(command);
        return ResponseEntity.ok(result.orThrow());
    }

    //@Secured("PRODUCT_MANAGEMENT")
    @PutMapping("/update")
    public ResponseEntity<String> updateProduct(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody @Valid UpdateProductCommand command) {
        var result = sender.send(command);
        return ResponseEntity.ok(result.orThrow());
    }

    @DeleteMapping("/delete/{productId}")
    //@Secured("PRODUCT_MANAGEMENT")
    @ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteProduct(@PathVariable String productId) {
        sender.send(new DeleteProductCommand(productId)).orThrow();
        return ResponseEntity.noContent().build();
    }
}
