package fithou.edu.vn.DoAnTotNghiep.product.commands.updateCapacity;

import fithou.edu.vn.DoAnTotNghiep.category.entity.Category;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Capacity;
import fithou.edu.vn.DoAnTotNghiep.product.repository.CapacityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateCapacityCommandHandler implements IRequestHandler<UpdateCapacityCommand, String> {
    private final CapacityRepository capacityRepository;

    @Override
    public HandleResponse<String> handle(UpdateCapacityCommand command) throws Exception {
        Optional<Capacity> exitsCapacity = capacityRepository.findById(command.getId());
        if (exitsCapacity.isEmpty()) {
            return HandleResponse.error("Dung lượng không tồn tại");
        }
        boolean isUpdateName = exitsCapacity.get().getName().equals(command.getName());

        if (!isUpdateName) {
            var existWithName = capacityRepository.findByNameIgnoreCase(command.getName());
            if (existWithName.isPresent()) {
                return HandleResponse.error("Tên dung lượng đã tồn tại");
            }
            exitsCapacity.get().setName(command.getName());
        }
        capacityRepository.save(exitsCapacity.get());
        return HandleResponse.ok("Sửa dung lượng thành công");
    }
}
