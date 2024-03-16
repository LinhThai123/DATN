package fithou.edu.vn.DoAnTotNghiep.product.commands.createCapacity;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Capacity;
import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductColor;
import fithou.edu.vn.DoAnTotNghiep.product.repository.CapacityRepository;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@AllArgsConstructor
@Service
public class CreateCapacityCommandHandler implements IRequestHandler<CreateCapacityCommand, String> {

    @Autowired
    private CapacityRepository capacityRepository;
    @Override
    public HandleResponse<String> handle(CreateCapacityCommand request) throws Exception {
        var capacity = new Capacity() ;
        var capacityName = capacityRepository.findByName(request.getName());
        if(capacityName.isPresent()) {
            return HandleResponse.error("Dung lượng đã tồn tại");
        }
        capacity.setName(request.getName());
        capacity.setDeleteDate(null);
        capacity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        capacity.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        capacityRepository.save(capacity);
        return HandleResponse.ok(capacity.getId());
    }
}
