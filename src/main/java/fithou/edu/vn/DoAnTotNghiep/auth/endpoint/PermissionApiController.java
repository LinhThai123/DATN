package fithou.edu.vn.DoAnTotNghiep.auth.endpoint;

import fithou.edu.vn.DoAnTotNghiep.auth.dto.PermissionDto;
import fithou.edu.vn.DoAnTotNghiep.auth.dto.RoleDto;
import fithou.edu.vn.DoAnTotNghiep.auth.query.permission.GetAllPermissionQuery;
import fithou.edu.vn.DoAnTotNghiep.auth.query.role.GetAllRolesQuery;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.ISender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
