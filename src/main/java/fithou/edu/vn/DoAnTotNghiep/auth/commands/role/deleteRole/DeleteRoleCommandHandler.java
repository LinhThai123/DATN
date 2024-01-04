package fithou.edu.vn.DoAnTotNghiep.auth.commands.role.deleteRole;

import fithou.edu.vn.DoAnTotNghiep.auth.repository.RoleRepository;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class DeleteRoleCommandHandler implements IRequestHandler<DeleteRoleCommand, Void> {

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public HandleResponse<Void> handle(DeleteRoleCommand deleteRoleCommand) throws Exception {
        var role = roleRepository.findByName(deleteRoleCommand.getRoleName());
        roleRepository.delete(role.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found")));
        return HandleResponse.ok();
    }
}
