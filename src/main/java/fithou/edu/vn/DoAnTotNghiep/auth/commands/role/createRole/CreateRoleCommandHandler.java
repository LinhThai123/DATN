package fithou.edu.vn.DoAnTotNghiep.auth.commands.role.createRole;

import fithou.edu.vn.DoAnTotNghiep.auth.entity.Role;
import fithou.edu.vn.DoAnTotNghiep.auth.repository.RoleRepository;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
@AllArgsConstructor

public class CreateRoleCommandHandler implements IRequestHandler<CreateRoleCommand, String> {
    private static Logger logger = Logger.getLogger(String.valueOf(CreateRoleCommandHandler.class));
    @Autowired
    private RoleRepository roleReposiroty;
    @Override
    @Transactional
    public HandleResponse<String> handle(CreateRoleCommand createRoleCommand) throws Exception{
        var exitsRole = roleReposiroty.findByName(createRoleCommand.getNormalizedName());
        if (exitsRole.isPresent()) {
            return HandleResponse.error("Role with name" + createRoleCommand.getNormalizedName() + " already exist");
        }
        Role role = new Role() ;
        role.setNormalizedName(createRoleCommand.getNormalizedName());
        role.setDisplayName(createRoleCommand.getDisplayName());
        role.setDescription(createRoleCommand.getDescription());
        role.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        role.setModifiedDate(new Timestamp(System.currentTimeMillis()));
            roleReposiroty.save(role) ;
            return HandleResponse.ok(role.getNormalizedName());
    }
}
