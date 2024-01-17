package fithou.edu.vn.DoAnTotNghiep.product.endpoint;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.ISender;
import fithou.edu.vn.DoAnTotNghiep.product.commands.createBrand.CreateBrandCommand;
import fithou.edu.vn.DoAnTotNghiep.product.commands.deleteBrand.DeleteBrandCommand;
import fithou.edu.vn.DoAnTotNghiep.product.commands.deleteProduct.DeleteProductCommand;
import fithou.edu.vn.DoAnTotNghiep.product.commands.updateBrand.UpdateBrandCommand;
import fithou.edu.vn.DoAnTotNghiep.product.dto.BrandDto;
import fithou.edu.vn.DoAnTotNghiep.product.query.getAllBrands.GetAllBrandQuery;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/v1/brand")
public class BrandApiController {

    @Autowired
    private ISender sender;

    @GetMapping()
    public ResponseEntity<Collection<BrandDto>> getAllBrands() {
        var result = sender.send(new GetAllBrandQuery()).orThrow();
        return ResponseEntity.ok(result);
    }
    @PostMapping("/add")
    public ResponseEntity<String> createBrand (@Valid @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody CreateBrandCommand command) {
        var result = sender.send(command);
        return ResponseEntity.ok(result.orThrow());
    }

    @PutMapping("/update")
//    @PostAuthorize("hasAuthority('CATEGORY_MANAGEMENT')")
//    @Secured("CATEGORY_MANAGEMENT")
    public ResponseEntity<String> updateBrand(@Valid @RequestBody UpdateBrandCommand command) {
        var result = sender.send(command);
        return ResponseEntity.ok(result.orThrow());

    }

    @DeleteMapping("/delete/{id}")
    //@Secured("PRODUCT_MANAGEMENT")
    @ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteBrand(@PathVariable String id) {
        var result = sender.send(new DeleteBrandCommand(id));
        return ResponseEntity.ok(result.orThrow());
    }
}
