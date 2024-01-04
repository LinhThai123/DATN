package fithou.edu.vn.DoAnTotNghiep.auth.query.permission;

import fithou.edu.vn.DoAnTotNghiep.auth.dto.PermissionDto;
import fithou.edu.vn.DoAnTotNghiep.auth.repository.PermissionRepository;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllPermissionQueryHandler implements IRequestHandler<GetAllPermissionQuery, List<PermissionDto>> {

    @Autowired
    private PermissionRepository permissionRepository;

    private final ModelMapper modelMapper;

    @Override
    public HandleResponse<List<PermissionDto>> handle(GetAllPermissionQuery getAllPermissionQuery) throws Exception {
        var permissions = permissionRepository.findAll();
        var permissionDtos = permissions.stream().map(permission -> {
            return modelMapper.map(permission, PermissionDto.class);
        }).toList();
        return HandleResponse.ok(permissionDtos);
    }
}
