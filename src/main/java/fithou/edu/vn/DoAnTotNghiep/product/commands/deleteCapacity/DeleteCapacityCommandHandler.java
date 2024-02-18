package fithou.edu.vn.DoAnTotNghiep.product.commands.deleteCapacity;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.product.repository.CapacityRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class DeleteCapacityCommandHandler implements IRequestHandler<DeleteCapacityCommand, String> {
    @Autowired
    private CapacityRepository capacityRepository;
    @Override
    @Transactional
    public HandleResponse<String> handle(DeleteCapacityCommand deleteCapacityCommand) throws Exception {
        var capacity = capacityRepository.findById(deleteCapacityCommand.getId());
        if (capacity.isEmpty()) {
            return HandleResponse.error("Không tìm thấy dung lượng ", HttpStatus.NOT_FOUND);
        }
        capacityRepository.delete(capacity.get());
        return HandleResponse.ok("Xóa dung lượng thành công");
    }
}
