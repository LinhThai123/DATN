package fithou.edu.vn.DoAnTotNghiep.product.commands.createCapacity;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Capacity;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Color;
import fithou.edu.vn.DoAnTotNghiep.product.repository.CapacityRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@AllArgsConstructor
public class CreateCapacityCommandHandler implements IRequestHandler<CreateCapacityCommand, String> {

    @Autowired
    private CapacityRepository capacityRepository;
    @Override
    public HandleResponse<String> handle(CreateCapacityCommand command) throws Exception {
        var existWithName = capacityRepository.findByNameIgnoreCase(command.getName());
        if (existWithName.isPresent()) {
            return HandleResponse.error("Tên dung lượng đã tồn tại");
        }
        var capacity = new Capacity();
        capacity.setName(command.getName());
        capacity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        capacity.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        capacityRepository.save(capacity);
        return HandleResponse.ok("Thêm dung lượng thành công");
    }
}
