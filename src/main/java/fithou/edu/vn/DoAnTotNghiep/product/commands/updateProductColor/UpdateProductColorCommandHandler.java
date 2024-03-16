package fithou.edu.vn.DoAnTotNghiep.product.commands.updateProductColor;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.product.repository.ProductColorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UpdateProductColorCommandHandler implements IRequestHandler<UpdateProductColorCommand, String> {
    @Autowired
    private ProductColorRepository productColorRepository;
    @Override
    public HandleResponse<String> handle(UpdateProductColorCommand updateProductColorCommand) throws Exception {
        var exist = productColorRepository.findById(updateProductColorCommand.getId());
        if (exist.isEmpty()) {
            return HandleResponse.error("Màu sắc không tồn tại");
        }
        var isUpdateName = exist.get().getName().equals(updateProductColorCommand.getName());
        if (isUpdateName) {
            return HandleResponse.error("Không có gì thay đổi");
        }
        if (!isUpdateName) {
            var existWithName = productColorRepository.findByName(updateProductColorCommand.getName());
            if (existWithName.isPresent()) {
                return HandleResponse.error("Tên màu sắc đã tồn tại");
            }
            exist.get().setName(updateProductColorCommand.getName());
        }
        productColorRepository.save(exist.get());
        return HandleResponse.ok("Sửa màu sắc thành công");
    }
}
