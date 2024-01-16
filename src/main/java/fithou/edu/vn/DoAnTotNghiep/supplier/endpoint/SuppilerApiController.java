package fithou.edu.vn.DoAnTotNghiep.supplier.endpoint;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.ISender;
import fithou.edu.vn.DoAnTotNghiep.common.dto.Paginated;
import fithou.edu.vn.DoAnTotNghiep.supplier.commands.createSupplier.CreateSupplierCommand;
import fithou.edu.vn.DoAnTotNghiep.supplier.commands.deleteSupplier.DeleteSuppilerCommand;
import fithou.edu.vn.DoAnTotNghiep.supplier.commands.updateSupplier.UpdateSupplierCommand;
import fithou.edu.vn.DoAnTotNghiep.supplier.dto.SupplierDto;
import fithou.edu.vn.DoAnTotNghiep.supplier.query.getAllSupplier.GetAllSuppliersQuery;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/suppiler")
public class SuppilerApiController {
    @Autowired
    private ISender sender;

    @GetMapping()
    public ResponseEntity<Paginated<SupplierDto>> getAllSuppliers(@ParameterObject GetAllSuppliersQuery query) {
        var result = sender.send(query).orThrow();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/add")
    public ResponseEntity<String> createSuppiler (@Valid @RequestBody CreateSupplierCommand command) {
        var result = sender.send(command);
        return ResponseEntity.ok(result.orThrow());
    }

    @PutMapping("/update")
//    @PostAuthorize("hasAuthority('CATEGORY_MANAGEMENT')")
//    @Secured("CATEGORY_MANAGEMENT")
    public ResponseEntity<String> updateSuppiler (@Valid @RequestBody UpdateSupplierCommand command) {
        var result = sender.send(command);
        return ResponseEntity.ok(result.orThrow());

    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
//    @PostAuthorize("hasAuthority('CATEGORY_MANAGEMENT')")
    public ResponseEntity<String> deleteSuppiler(@PathVariable String id) {
        if (id.isBlank()) throw new IllegalArgumentException("id is null");
        sender.send(new DeleteSuppilerCommand(id));
        return ResponseEntity.ok("Xóa nhà cung cấp thành công");
    }
}