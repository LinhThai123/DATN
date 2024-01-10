package fithou.edu.vn.DoAnTotNghiep.supplier.commands.createSupplier;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.supplier.entity.Suppiler;
import fithou.edu.vn.DoAnTotNghiep.supplier.repository.SuppilerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@AllArgsConstructor
public class CreateSupplierCommandHandler implements IRequestHandler<CreateSupplierCommand, String> {
    @Autowired
    private SuppilerRepository suppilerRepository;
    @Override
    public HandleResponse<String> handle(CreateSupplierCommand createSupplierCommand) throws Exception {
        var exitsName = suppilerRepository.findByNameIgnoreCase(createSupplierCommand.getName());
        if (exitsName.isPresent()) {
            return HandleResponse.error("Tên nhà cung cấp đã tồn tại");
        }
        var suppiler = new Suppiler();
        suppiler.setName(createSupplierCommand.getName());
        suppiler.setEmail(createSupplierCommand.getEmail());
        suppiler.setNumberPhone(createSupplierCommand.getNumberPhone());
        suppiler.setAddress(createSupplierCommand.getAddress());
        suppiler.setBank(createSupplierCommand.getBank());
        suppiler.setStatus(createSupplierCommand.getStatus());
        suppiler.setAccountNumber(createSupplierCommand.getAccountNumber());
        suppiler.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        suppiler.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        return HandleResponse.ok("Thêm nhà cung cấp thành công");
    }
}
