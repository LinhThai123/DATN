package fithou.edu.vn.DoAnTotNghiep.product.endpoint;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.ISender;
import fithou.edu.vn.DoAnTotNghiep.product.commands.createBrand.CreateBrandCommand;
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

}
