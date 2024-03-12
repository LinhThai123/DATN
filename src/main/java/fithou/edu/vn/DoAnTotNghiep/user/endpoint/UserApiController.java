package fithou.edu.vn.DoAnTotNghiep.user.endpoint;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.ISender;
import fithou.edu.vn.DoAnTotNghiep.supplier.commands.createSupplier.CreateSupplierCommand;
import fithou.edu.vn.DoAnTotNghiep.user.command.createEmployee.CreateEmployeeCommand;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/employee")
public class UserApiController {

    @Autowired
    private ISender sender;

    @PostMapping("/add")
    public ResponseEntity<String> createEmployee (@Valid @RequestBody CreateEmployeeCommand command) {
        var result = sender.send(command);
        return ResponseEntity.ok(result.orThrow());
    }
}

