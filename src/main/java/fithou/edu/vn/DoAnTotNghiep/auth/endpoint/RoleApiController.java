package fithou.edu.vn.DoAnTotNghiep.auth.endpoint;

import fithou.edu.vn.DoAnTotNghiep.auth.commands.role.addPermissionToRole.AddPermissionToRoleCommand;
import fithou.edu.vn.DoAnTotNghiep.auth.commands.role.createRole.CreateRoleCommand;
import fithou.edu.vn.DoAnTotNghiep.auth.commands.role.deleteRole.DeleteRoleCommand;
import fithou.edu.vn.DoAnTotNghiep.auth.commands.role.removePermissionFromRole.RemovePermissionFromRoleCommand;
import fithou.edu.vn.DoAnTotNghiep.auth.commands.role.updateRole.UpdateRoleCommand;
import fithou.edu.vn.DoAnTotNghiep.auth.dto.RoleDto;
import fithou.edu.vn.DoAnTotNghiep.auth.query.role.GetAllRolesQuery;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.ISender;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/v1/role")
public class RoleApiController {

    @Autowired
    private ISender sender;

    @GetMapping("/list")
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        var query = new GetAllRolesQuery();
        var result = sender.send(query);
        return ResponseEntity.ok(result.orThrow());
    }

    @PostMapping("/add")
    public ResponseEntity<String> createRole(@Valid @RequestBody CreateRoleCommand command) {
        try {
            String result = sender.send(command).get(); // Lấy dữ liệu từ phản hồi
            return ResponseEntity.ok("Role added successfully: " + result);
        } catch (ResponseStatusException e) {
            // Xử lý nếu có lỗi
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateRole(@Valid @RequestBody UpdateRoleCommand command) {
        try {
             sender.send(command).get(); // Lấy dữ liệu từ phản hồi
            return ResponseEntity.ok("Role update successfully " );
        } catch (ResponseStatusException e) {
            // Xử lý nếu có lỗi
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @PatchMapping("/assign-permission")
    public ResponseEntity<String> assignPermission (@Valid @RequestBody AddPermissionToRoleCommand command) {
        try{
            sender.send(command).orThrow();
            return ResponseEntity.ok("Assign Role Successfully");
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @PatchMapping("/remove-permission")
    public ResponseEntity<String> removePermissionFromRole(@Valid @RequestBody RemovePermissionFromRoleCommand command)  {
        try{
            sender.send(command).orThrow();
            return ResponseEntity.ok("Remove Permission For Role Successfully");
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @DeleteMapping("/delete/{roleName}")
    public ResponseEntity<String> deleteRole(@PathVariable String roleName) {
        try {
             sender.send(new DeleteRoleCommand(roleName)).orThrow();
            return ResponseEntity.ok("Role delete successfully");
        } catch (ResponseStatusException e) {
            // Xử lý nếu có lỗi
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }

    }
}
