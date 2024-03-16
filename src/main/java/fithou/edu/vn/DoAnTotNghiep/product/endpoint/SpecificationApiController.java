package fithou.edu.vn.DoAnTotNghiep.product.endpoint;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.ISender;
import fithou.edu.vn.DoAnTotNghiep.product.commands.createSpecification.CreateSpecificationCommand;
import fithou.edu.vn.DoAnTotNghiep.product.commands.updateSpecification.UpdateSpecificationCommand;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/specification")
public class SpecificationApiController {

    @Autowired
    private  ISender sender;

    @PostMapping("/create")
    public ResponseEntity<String> createProductOption (@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody @Valid CreateSpecificationCommand command) {
        var result = sender.send(command) ;
        return ResponseEntity.ok(result.orThrow());
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateProductOption(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody @Valid UpdateSpecificationCommand command) {
        var result = sender.send(command);
        return ResponseEntity.ok(result.orThrow());
    }
}
