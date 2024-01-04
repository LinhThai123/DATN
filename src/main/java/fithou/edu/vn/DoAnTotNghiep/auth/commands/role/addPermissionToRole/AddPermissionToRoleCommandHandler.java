package fithou.edu.vn.DoAnTotNghiep.auth.commands.role.addPermissionToRole;

import fithou.edu.vn.DoAnTotNghiep.auth.repository.PermissionRepository;
import fithou.edu.vn.DoAnTotNghiep.auth.repository.RoleRepository;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class AddPermissionToRoleCommandHandler implements IRequestHandler<AddPermissionToRoleCommand, Void> {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;


    @Override
    @Transactional
    public HandleResponse<Void> handle(AddPermissionToRoleCommand addPermissionToRoleCommand) throws Exception {
        var exitsRole = roleRepository.findByName(addPermissionToRoleCommand.getRoleName()) ;
        if (exitsRole.isEmpty()) {
            return HandleResponse.error("Role with name " + addPermissionToRoleCommand.getRoleName() + " not found");
        }

        var exitPermission = permissionRepository.findByName(addPermissionToRoleCommand.getPermissionName());
        if (exitPermission.isEmpty()) {
            return HandleResponse.error("Permission with name " + addPermissionToRoleCommand.getPermissionName() + " not found");
        }

        var role = exitsRole.get();
        var listRole = role.getPermissions();
        if (listRole == null) {
            listRole = new ArrayList<>() ;
        }
        listRole.add(exitPermission.get());
        roleRepository.save(role);
        return HandleResponse.ok(null);
    }
}
