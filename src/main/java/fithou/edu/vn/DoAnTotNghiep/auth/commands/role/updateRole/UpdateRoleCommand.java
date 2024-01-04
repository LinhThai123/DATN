package fithou.edu.vn.DoAnTotNghiep.auth.commands.role.updateRole;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateRoleCommand implements IRequest<Void> {

    private String id;

    @NotEmpty(message = "Mã vai trò không được để trống")
    private String normalizedName;

    @NotEmpty(message = "Tên vai trò không được để trống")
    private String displayName;

    @NotEmpty(message = "Mô tả vai trò không được để trống")
    private String description;
}
