package fithou.edu.vn.DoAnTotNghiep.user.endpoint;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.ISender;
import fithou.edu.vn.DoAnTotNghiep.user.command.ToggleLockAccount.ToggleLockAccountCommand;
import fithou.edu.vn.DoAnTotNghiep.user.command.createEmployee.CreateEmployeeCommand;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/toggle-lock-account/{userId}")
//    @Secured("USER_MANAGEMENT")
    public ResponseEntity<String> toggleLockAccount(@PathVariable String userId) {
        var command = new ToggleLockAccountCommand();
        command.setUserId(userId);
        return ResponseEntity.ok(sender.send(command).orThrow());
    }
}

