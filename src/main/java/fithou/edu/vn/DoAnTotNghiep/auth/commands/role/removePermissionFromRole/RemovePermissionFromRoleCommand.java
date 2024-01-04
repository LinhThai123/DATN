package fithou.edu.vn.DoAnTotNghiep.auth.commands.role.removePermissionFromRole;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RemovePermissionFromRoleCommand implements IRequest<Void> {

    @NotEmpty(message = "Tên vai trò không được để trống")
    private String roleName;

    @NotEmpty(message = "Tên quyền không được để trống")
    private String permissionName;
}
