package fithou.edu.vn.DoAnTotNghiep.auth.commands.role.updateRole;

import fithou.edu.vn.DoAnTotNghiep.auth.repository.RoleRepository;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
@AllArgsConstructor
public class UpdateRoleCommandHandler implements IRequestHandler<UpdateRoleCommand, Void> {

    @Autowired
    private RoleRepository roleReposiroty;

    @Override
    @Transactional
    public HandleResponse<Void> handle(UpdateRoleCommand updateRoleCommand) throws Exception {
        var existRole = roleReposiroty.findByName(updateRoleCommand.getNormalizedName());
        if (existRole.isEmpty()) {
            return HandleResponse.error("Role with name " + updateRoleCommand.getNormalizedName() + " not found");
        }
        var role = existRole.get();
        role.setDisplayName(updateRoleCommand.getDisplayName());
        role.setDescription(updateRoleCommand.getDescription());
        role.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        roleReposiroty.save(role);
        return HandleResponse.ok(null);
    }
}
