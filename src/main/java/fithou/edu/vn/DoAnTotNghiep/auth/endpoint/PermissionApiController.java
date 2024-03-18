package fithou.edu.vn.DoAnTotNghiep.auth.endpoint;

import fithou.edu.vn.DoAnTotNghiep.auth.commands.permission.CreatePermissionCommand;
import fithou.edu.vn.DoAnTotNghiep.auth.commands.role.createRole.CreateRoleCommand;
import fithou.edu.vn.DoAnTotNghiep.auth.dto.PermissionDto;
import fithou.edu.vn.DoAnTotNghiep.auth.dto.RoleDto;
import fithou.edu.vn.DoAnTotNghiep.auth.query.permission.GetAllPermissionQuery;
import fithou.edu.vn.DoAnTotNghiep.auth.query.role.GetAllRolesQuery;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.ISender;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/v1/permission")
public class PermissionApiController {

    @Autowired
    private ISender sender;

    @GetMapping("/list")
    public ResponseEntity<List<PermissionDto>> getAllPermission() {
        var query = new GetAllPermissionQuery();
        var result = sender.send(query);
        return ResponseEntity.ok(result.orThrow());
    }

    @PostMapping("/add")
    public ResponseEntity<String> createPermission(@Valid @RequestBody CreatePermissionCommand command) {
        try {
            String result = sender.send(command).get(); // Lấy dữ liệu từ phản hồi
            return ResponseEntity.ok("Permission added successfully: " + result);
        } catch (ResponseStatusException e) {
            // Xử lý nếu có lỗi
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }
}
