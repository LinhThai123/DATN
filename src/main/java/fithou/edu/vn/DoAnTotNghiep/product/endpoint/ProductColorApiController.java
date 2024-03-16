package fithou.edu.vn.DoAnTotNghiep.product.endpoint;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.ISender;
import fithou.edu.vn.DoAnTotNghiep.product.commands.createProductColor.CreateProductColorCommand;
import fithou.edu.vn.DoAnTotNghiep.product.commands.deleteProductColor.DeleteProductColorCommand;
import fithou.edu.vn.DoAnTotNghiep.product.commands.updateProductColor.UpdateProductColorCommand;
import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductColor;
import fithou.edu.vn.DoAnTotNghiep.product.service.ProductColorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product_color")
public class ProductColorApiController {

    @Autowired
    private ISender sender;

    @Autowired
    private ProductColorService productColorService;

    @GetMapping("")
    public List<ProductColor> getAllColors() {
        return productColorService.getListProductColor();
    }

    @PostMapping("/add")
//    @PostAuthorize("hasAuthority('CATEGORY_MANAGEMENT')")
    public ResponseEntity<String> createProductColor (@Valid @RequestBody CreateProductColorCommand command) {
        var result = sender.send(command);
        return ResponseEntity.ok(result.orThrow());
    }

    @PutMapping("/update")
//    @PostAuthorize("hasAuthority('CATEGORY_MANAGEMENT')")
    @Secured("CATEGORY_MANAGEMENT")
    public ResponseEntity<String> updatePRoductColor(@Valid @RequestBody UpdateProductColorCommand command) {
        var result = sender.send(command);
        return ResponseEntity.ok(result.orThrow());

    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
//    @PostAuthorize("hasAuthority('CATEGORY_MANAGEMENT')")
    public ResponseEntity<String> deleteCategory(@PathVariable String id) {
        if (id.isBlank()) throw new IllegalArgumentException("id is null");
        sender.send(new DeleteProductColorCommand(id));
        return ResponseEntity.ok("Xóa màu sắc thành công");
    }
}
