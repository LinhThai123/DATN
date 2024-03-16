package fithou.edu.vn.DoAnTotNghiep.product.commands.deleteProductColor;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductColor;
import fithou.edu.vn.DoAnTotNghiep.product.repository.ProductColorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class DeleteProductColorCommandHandler implements IRequestHandler<DeleteProductColorCommand, String> {

    @Autowired
    private ProductColorRepository productColorRepository;
    @Override
    public HandleResponse<String> handle(DeleteProductColorCommand command) throws Exception {
        Optional<ProductColor> exitsProductColor = productColorRepository.findById(command.getId());
        if (exitsProductColor.isEmpty()) {
            return HandleResponse.error("Màu sắc không tồn tại");
        }
        if (exitsProductColor.get().getProductOptions().isEmpty()) {
            productColorRepository.hardDeleteById(command.getId());
            return HandleResponse.ok();
        }
        productColorRepository.delete(exitsProductColor.get());
        return HandleResponse.ok("Xóa màu sắc thành công");
    }

}
