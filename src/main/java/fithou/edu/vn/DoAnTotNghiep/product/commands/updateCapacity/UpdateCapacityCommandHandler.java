package fithou.edu.vn.DoAnTotNghiep.product.commands.updateCapacity;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.product.repository.CapacityRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UpdateCapacityCommandHandler implements IRequestHandler<UpdateCapacityCommand, String> {

    @Autowired
    private CapacityRepository capacityRepository;
    @Override
    public HandleResponse<String> handle(UpdateCapacityCommand updateCapacityCommand) throws Exception {
        var exist = capacityRepository.findById(updateCapacityCommand.getId());
        if (exist.isEmpty()) {
            return HandleResponse.error("Dung lượng không tồn tại");
        }
        var isUpdateName = exist.get().getName().equals(updateCapacityCommand.getName());
        if (isUpdateName) {
            return HandleResponse.error("Không có gì thay đổi");
        }
        if (!isUpdateName) {
            var existWithName = capacityRepository.findByName(updateCapacityCommand.getName());
            if (existWithName.isPresent()) {
                return HandleResponse.error("Tên dung lượng đã tồn tại");
            }
            exist.get().setName(updateCapacityCommand.getName());
        }
        capacityRepository.save(exist.get());
        return HandleResponse.ok("Sửa dung lượng thành công");
    }
}
