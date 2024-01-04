package fithou.edu.vn.DoAnTotNghiep.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDto {

    private String id;

    private String normalizedName;

    private String displayName;

    private String description;

    private java.util.List<PermissionDto> permissions;
}
