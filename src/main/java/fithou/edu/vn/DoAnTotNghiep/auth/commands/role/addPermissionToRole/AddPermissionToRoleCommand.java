package fithou.edu.vn.DoAnTotNghiep.auth.commands.role.addPermissionToRole;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddPermissionToRoleCommand implements IRequest<Void> {

    @NotEmpty(message = "Tên vai trò không được để trống")
    private String roleName;

    @NotEmpty(message = "Tên quyền không được để trống")
    private String permissionName;
}
