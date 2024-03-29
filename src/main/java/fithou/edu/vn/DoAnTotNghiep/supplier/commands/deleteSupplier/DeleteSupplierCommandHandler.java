package fithou.edu.vn.DoAnTotNghiep.supplier.commands.deleteSupplier;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.supplier.entity.Supplier;
import fithou.edu.vn.DoAnTotNghiep.supplier.repository.SuppilerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DeleteSupplierCommandHandler implements IRequestHandler<DeleteSupplierCommand, String> {
    @Autowired
    private SuppilerRepository suppilerRepository;
    @Override
    public HandleResponse<String> handle(DeleteSupplierCommand command) throws Exception {
        Optional<Supplier> exitsSuppiler = suppilerRepository.findById(command.getId());
        if (exitsSuppiler.isEmpty()) {
            return HandleResponse.error("Nhà cung cấp không tồn tại");
        }
        suppilerRepository.delete(exitsSuppiler.get());
        return HandleResponse.ok("Xóa nhà cung cấp thành công");
    }
}
