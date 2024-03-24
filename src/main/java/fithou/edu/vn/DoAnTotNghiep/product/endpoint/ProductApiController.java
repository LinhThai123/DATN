package fithou.edu.vn.DoAnTotNghiep.product.endpoint;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.ISender;
import fithou.edu.vn.DoAnTotNghiep.product.commands.createProduct.CreateProductCommand;
import fithou.edu.vn.DoAnTotNghiep.product.commands.createProductImage.CreateProductImageCommand;
import fithou.edu.vn.DoAnTotNghiep.product.commands.deleteProduct.DeleteProductCommand;
import fithou.edu.vn.DoAnTotNghiep.product.commands.recoveryProduct.RecoveryProductCommand;
import fithou.edu.vn.DoAnTotNghiep.product.commands.updateProduct.UpdateProductCommand;
import fithou.edu.vn.DoAnTotNghiep.product.dto.ProductDTO;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Product;
import fithou.edu.vn.DoAnTotNghiep.product.service.ProductService;
import jakarta.validation.Valid;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductApiController {

    @Autowired
    private ISender sender;

    @Autowired
    private ProductService productService ;

    @GetMapping("")
    @ResponseBody
    public List<ProductDTO> getAllProduct() {
        return productService.getProductsWithProductOptionIds();
    }

    @GetMapping("/{productId}")
    public Product getProductDetailPage (@PathVariable String productId) {
        try {
            return productService.getProductById(productId);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/category")
    public ResponseEntity<List<Product>> getProductsByCategoryName(@RequestParam(defaultValue = "BgUupTg262gnP5O5") String id) {
        List<Product> products = productService.findByCategoryId(id);
        return ResponseEntity.ok(products);
    }
    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody @Valid CreateProductCommand command) {
        var result = sender.send(command);
        return ResponseEntity.ok(result.orThrow());
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateProduct(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody @Valid UpdateProductCommand command) {
        var result = sender.send(command);
        return ResponseEntity.ok(result.orThrow());
    }

    @DeleteMapping("/delete/{productId}")
    //@Secured("PRODUCT_MANAGEMENT")
    @ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteProduct(@PathVariable String productId) {
        var result = sender.send(new DeleteProductCommand(productId));
        return ResponseEntity.ok(result.orThrow());
    }

    @PatchMapping("/recovery/{productId}")
    //@Secured("PRODUCT_MANAGEMENT")
    @ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> recoveryProduct(@PathVariable String productId) {
        sender.send(new RecoveryProductCommand(productId)).orThrow();
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/images")
    public ResponseEntity<String> addProductImage (@RequestBody CreateProductImageCommand command) {
        var result = sender.send(command);
        return ResponseEntity.ok(result.orThrow());
    }

}
