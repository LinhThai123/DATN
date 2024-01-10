package fithou.edu.vn.DoAnTotNghiep.supplier.commands.updateSupplier;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.supplier.entity.Suppiler;
import fithou.edu.vn.DoAnTotNghiep.supplier.repository.SuppilerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateSupplierCommandHandler implements IRequestHandler<UpdateSupplierCommand, String> {
    @Autowired
    private SuppilerRepository suppilerRepository;
    @Override
    public HandleResponse<String> handle(UpdateSupplierCommand command) throws Exception {
        Optional<Suppiler> exitsSuppiler = suppilerRepository.findById(command.getId());
        if (exitsSuppiler.isEmpty()) {
            return HandleResponse.error("Nhà cung cấp không tồn tại");
        }
        boolean isUpdateName = exitsSuppiler.get().getName().equals(command.getName());

        if (isUpdateName) {
            var existWithName = suppilerRepository.findByNameIgnoreCase(command.getName());
            if (existWithName.isPresent()) {
                return HandleResponse.error("Tên nhà cung cấp đã tồn tại");
            }
            exitsSuppiler.get().setName(command.getName());
            exitsSuppiler.get().setEmail(command.getEmail());
            exitsSuppiler.get().setNumberPhone(command.getNumberPhone());
            exitsSuppiler.get().setBank(command.getBank());
            exitsSuppiler.get().setAddress(command.getAddress());
            exitsSuppiler.get().setStatus(command.getStatus());
            exitsSuppiler.get().setAccountNumber(command.getAccountNumber());
            exitsSuppiler.get().setModifiedDate(new Timestamp(System.currentTimeMillis()));
        }
        suppilerRepository.save(exitsSuppiler.get());
        return HandleResponse.ok("Sửa nhà cung cấp thành công");
    }
}
