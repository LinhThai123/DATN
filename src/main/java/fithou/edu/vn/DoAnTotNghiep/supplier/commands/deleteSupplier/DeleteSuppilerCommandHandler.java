package fithou.edu.vn.DoAnTotNghiep.supplier.commands.deleteSupplier;

import fithou.edu.vn.DoAnTotNghiep.category.entity.Category;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.supplier.entity.Suppiler;
import fithou.edu.vn.DoAnTotNghiep.supplier.repository.SuppilerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DeleteSuppilerCommandHandler implements IRequestHandler<DeleteSuppilerCommand, String> {
    @Autowired
    private SuppilerRepository suppilerRepository;
    @Override
    public HandleResponse<String> handle(DeleteSuppilerCommand command) throws Exception {
        Optional<Suppiler> exitsSuppiler = suppilerRepository.findById(command.getId());
        if (exitsSuppiler.isEmpty()) {
            return HandleResponse.error("Nhà cung cấp không tồn tại");
        }
        suppilerRepository.delete(exitsSuppiler.get());
        return HandleResponse.ok("Xóa nhà cung cấp thành công");
    }
}
