package fithou.edu.vn.DoAnTotNghiep.product.endpoint;

import fithou.edu.vn.DoAnTotNghiep.category.commands.updateCategory.UpdateCategoryCommand;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.ISender;
import fithou.edu.vn.DoAnTotNghiep.product.commands.createColor.CreateColorCommand;
import fithou.edu.vn.DoAnTotNghiep.product.commands.deleteBrand.DeleteBrandCommand;
import fithou.edu.vn.DoAnTotNghiep.product.commands.deleteColor.DeleteColorCommand;
import fithou.edu.vn.DoAnTotNghiep.product.commands.updateColor.UpdateColorCommand;
import fithou.edu.vn.DoAnTotNghiep.product.dto.ColorDto;
import fithou.edu.vn.DoAnTotNghiep.product.query.getAllColors.GetAllColorQuery;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/v1/color")
public class ColorApiController {

    @Autowired
    private ISender sender;

    @GetMapping()
    public ResponseEntity<Collection<ColorDto>> getAllColors() {
        var result = sender.send(new GetAllColorQuery()).orThrow();
        return ResponseEntity.ok(result);
    }
    @PostMapping("/add")
    public ResponseEntity<String> createColor (@Valid @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody CreateColorCommand command) {
        var result = sender.send(command);
        return ResponseEntity.ok(result.orThrow());
    }

    @PutMapping("/update")
//    @PostAuthorize("hasAuthority('CATEGORY_MANAGEMENT')")
//    @Secured("CATEGORY_MANAGEMENT")
    public ResponseEntity<String> updateColor(@Valid @RequestBody UpdateColorCommand command) {
        var result = sender.send(command);
        return ResponseEntity.ok(result.orThrow());
    }

    @DeleteMapping("/delete/{id}")
    //@Secured("PRODUCT_MANAGEMENT")
    @ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteBrand(@PathVariable String id) {
        var result = sender.send(new DeleteColorCommand(id));
        return ResponseEntity.ok(result.orThrow());
    }
}
