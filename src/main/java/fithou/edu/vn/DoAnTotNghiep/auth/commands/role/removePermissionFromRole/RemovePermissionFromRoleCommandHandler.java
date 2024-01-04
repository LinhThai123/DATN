package fithou.edu.vn.DoAnTotNghiep.auth.commands.role.removePermissionFromRole;

import fithou.edu.vn.DoAnTotNghiep.auth.repository.PermissionRepository;
import fithou.edu.vn.DoAnTotNghiep.auth.repository.RoleRepository;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RemovePermissionFromRoleCommandHandler implements IRequestHandler<RemovePermissionFromRoleCommand, Void> {
    @Autowired
    private RoleRepository roleRepository ;

    @Autowired
    private PermissionRepository permissionRepository;


    @Override
    @Transactional
    public HandleResponse<Void> handle(RemovePermissionFromRoleCommand removePermissionFromRoleCommand) throws Exception {
        var exitsRole = roleRepository.findByName(removePermissionFromRoleCommand.getRoleName()) ;
        if (exitsRole.isEmpty()) {
            return HandleResponse.error("Role with name " + removePermissionFromRoleCommand.getRoleName() + " not found");
        }

        var exitPermission = permissionRepository.findByName(removePermissionFromRoleCommand.getPermissionName());
        if (exitPermission.isEmpty()) {
            return HandleResponse.error("Permission with name " + removePermissionFromRoleCommand.getPermissionName() + " not found");
        }

        var role = exitsRole.get();
        var listPermission = role.getPermissions();
        if (listPermission == null) {
            return HandleResponse.ok();
        }
        listPermission.removeIf(permission1 -> permission1.getNormalizedName().equals(removePermissionFromRoleCommand.getPermissionName()));
        role.setPermissions(listPermission);
        roleRepository.save(role);
        return HandleResponse.ok(null);
    }
}
