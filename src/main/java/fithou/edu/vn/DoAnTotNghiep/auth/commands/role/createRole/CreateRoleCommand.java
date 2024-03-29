package fithou.edu.vn.DoAnTotNghiep.auth.commands.role.createRole;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class CreateRoleCommand implements IRequest<String> {

    private String id;

    @Pattern(regexp = "^[a-zA-Z0-9_]{3,50}$",message = "Mã vai trò phải có độ dài từ 3 đến 50 ký tự và không chứa ký tự đặc biệt")
    private String normalizedName;

    @NotEmpty(message = "Tên vai trò không được để trống")
    private String displayName;

    @NotEmpty(message = "Mô tả vai trò không được để trống")
    private String description;
}
