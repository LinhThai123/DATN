package fithou.edu.vn.DoAnTotNghiep.auth.commands.permission;

import fithou.edu.vn.DoAnTotNghiep.auth.commands.role.addPermissionToRole.AddPermissionToRoleCommand;
import fithou.edu.vn.DoAnTotNghiep.auth.entity.Permission;
import fithou.edu.vn.DoAnTotNghiep.auth.repository.PermissionRepository;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.ISender;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CreatePermissionCommandHandler implements IRequestHandler<CreatePermissionCommand, String> {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private ISender sender ;

    @Override
    @Transactional
    public HandleResponse<String> handle(CreatePermissionCommand createPermissionCommand) throws Exception {
        var exitsPermission = permissionRepository.findByName(createPermissionCommand.getNormalizedName());
        if (exitsPermission.isPresent()) {
            return HandleResponse.error("Quyền với mã " + createPermissionCommand.getNormalizedName() + " đã tồn tại");
        }
        Permission permission = new Permission() ;
        permission.setNormalizedName(createPermissionCommand.getNormalizedName());
        permission.setDisplayName(createPermissionCommand.getDisplayName());
        permission.setDescription(createPermissionCommand.getDescription());

        permissionRepository.save(permission);
        sender.send(AddPermissionToRoleCommand.builder()
                .permissionName(permission.getNormalizedName())
                .roleName("ROLE_ADMIN")
                .build());
        return HandleResponse.ok(permission.getNormalizedName());
    }
}
