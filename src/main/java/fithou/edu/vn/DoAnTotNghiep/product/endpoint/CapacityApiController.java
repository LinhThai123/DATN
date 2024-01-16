package fithou.edu.vn.DoAnTotNghiep.product.endpoint;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.ISender;
import fithou.edu.vn.DoAnTotNghiep.product.commands.createCapacity.CreateCapacityCommand;
import fithou.edu.vn.DoAnTotNghiep.product.commands.updateCapacity.UpdateCapacityCommand;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/capacity")
public class CapacityApiController {
    @Autowired
    private ISender sender;

    @PostMapping("/add")
    public ResponseEntity<String> createCapacity (@Valid @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody CreateCapacityCommand command) {
        var result = sender.send(command);
        return ResponseEntity.ok(result.orThrow());
    }

    @PutMapping("/update")
//    @PostAuthorize("hasAuthority('CATEGORY_MANAGEMENT')")
//    @Secured("CATEGORY_MANAGEMENT")
    public ResponseEntity<String> updateCapacity(@Valid @RequestBody UpdateCapacityCommand command) {
        var result = sender.send(command);
        return ResponseEntity.ok(result.orThrow());

    }
}
