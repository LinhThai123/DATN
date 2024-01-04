package fithou.edu.vn.DoAnTotNghiep.auth.commands.permission;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePermissionCommand implements IRequest<String> {

    private String id ;

    @Pattern(regexp = "^[a-zA-Z0-9_]{3,50}$",message = "Mã quyền phải có độ dài từ 3 đến 50 ký tự và không chứa ký tự đặc biệt")
    private String normalizedName;

    @NotEmpty(message = "Tên quyền không được để trống")
    private String displayName;

    @NotEmpty(message = "Mô tả quyền không được để trống")
    private String description;
}
