package fithou.edu.vn.DoAnTotNghiep.product.commands.deleteCapacity;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Capacity;
import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductColor;
import fithou.edu.vn.DoAnTotNghiep.product.repository.CapacityRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class DeleteCapacityCommandHandler implements IRequestHandler<DeleteCapacityCommand, String> {

    @Autowired
    private CapacityRepository capacityRepository;
    @Override
    public HandleResponse<String> handle(DeleteCapacityCommand command) throws Exception {
        Optional<Capacity> exitsCapacity = capacityRepository.findById(command.getId());
        if (exitsCapacity.isEmpty()) {
            return HandleResponse.error("Dung lượng không tồn tại");
        }
        if (exitsCapacity.get().getProductOptions().isEmpty()) {
            capacityRepository.hardDeleteById(command.getId());
            return HandleResponse.ok();
        }
        capacityRepository.delete(exitsCapacity.get());
        return HandleResponse.ok("Xóa dung lượng thành công");
    }
}
