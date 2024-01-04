package fithou.edu.vn.DoAnTotNghiep.auth.commands.role.deleteRole;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteRoleCommand implements IRequest<String> {

    private String roleName ;

}
