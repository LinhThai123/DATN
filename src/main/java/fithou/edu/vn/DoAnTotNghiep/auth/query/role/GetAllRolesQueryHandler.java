package fithou.edu.vn.DoAnTotNghiep.auth.query.role;

import fithou.edu.vn.DoAnTotNghiep.auth.dto.RoleDto;
import fithou.edu.vn.DoAnTotNghiep.auth.repository.RoleRepository;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllRolesQueryHandler implements IRequestHandler<GetAllRolesQuery, List<RoleDto>> {

    @Autowired
    private RoleRepository roleRepository;

    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public HandleResponse<List<RoleDto>> handle(GetAllRolesQuery getAllRolesQuery){
        var roles = roleRepository.findAll();
        var roleDtos = roles.stream().map(role -> {
            return modelMapper.map(role, RoleDto.class);
        }).toList();
        return HandleResponse.ok(roleDtos);
    }
}
