package fithou.edu.vn.DoAnTotNghiep.product.commands.updateStatusProduct;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateStatusProductCommandHandller implements IRequestHandler<UpdateStatusProductCommand, String> {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public HandleResponse<String> handle(UpdateStatusProductCommand updateStatusProductCommand) throws Exception {
        var exitsProduct = productRepository.findById(updateStatusProductCommand.getId());

        if (exitsProduct.isEmpty()) {
            return HandleResponse.error("Không tìm thấy sản phẩm");
        }

        exitsProduct.get().setStatus(updateStatusProductCommand.getStatus());
        productRepository.save(exitsProduct.get());
        return HandleResponse.ok("Thay đổi trang thái sản phẩm thành công");
    }
}
