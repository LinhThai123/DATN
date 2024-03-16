package fithou.edu.vn.DoAnTotNghiep.product.endpoint;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.ISender;
import fithou.edu.vn.DoAnTotNghiep.product.commands.createCapacity.CreateCapacityCommand;
import fithou.edu.vn.DoAnTotNghiep.product.commands.deleteCapacity.DeleteCapacityCommand;
import fithou.edu.vn.DoAnTotNghiep.product.commands.updateCapacity.UpdateCapacityCommand;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Capacity;
import fithou.edu.vn.DoAnTotNghiep.product.service.CapacityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/capacity")
public class CapacityApiController {
    @Autowired
    private ISender sender;

    @Autowired
    private CapacityService capacityService;

    @GetMapping("")
    public List<Capacity> getAllCapacity() {
        return capacityService.getListCapacity();
    }

    @PostMapping("/add")
//    @PostAuthorize("hasAuthority('CATEGORY_MANAGEMENT')")
    public ResponseEntity<String> createCapacity (@Valid @RequestBody CreateCapacityCommand command) {
        var result = sender.send(command);
        return ResponseEntity.ok(result.orThrow());
    }

    @PutMapping("/update")
//    @PostAuthorize("hasAuthority('CATEGORY_MANAGEMENT')")
    @Secured("CATEGORY_MANAGEMENT")
    public ResponseEntity<String> updatePRoductColor(@Valid @RequestBody UpdateCapacityCommand command) {
        var result = sender.send(command);
        return ResponseEntity.ok(result.orThrow());

    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
//    @PostAuthorize("hasAuthority('CATEGORY_MANAGEMENT')")
    public ResponseEntity<String> deleteCategory(@PathVariable String id) {
        if (id.isBlank()) throw new IllegalArgumentException("id is null");
        sender.send(new DeleteCapacityCommand(id));
        return ResponseEntity.ok("Xóa dung lượng thành công");
    }
}
