package fithou.edu.vn.DoAnTotNghiep.product.commands.deleteProduct;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.product.repository.ProductOptionRepository;
import fithou.edu.vn.DoAnTotNghiep.product.repository.ProductRepository;
import fithou.edu.vn.DoAnTotNghiep.product.repository.SpecificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class DeleteProductCommandHandler implements IRequestHandler<DeleteProductCommand, String> {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SpecificationRepository specificationRepository;

    @Override
    @Transactional
    public HandleResponse<String> handle(DeleteProductCommand command) throws Exception {
        var product = productRepository.findById(command.getId());
        if (product.isEmpty()) {
            return HandleResponse.error("Không tìm thấy sản phẩm", HttpStatus.NOT_FOUND);
        }
        specificationRepository.deleteByProductId(product.get().getId());
        productRepository.delete(product.get());
        return HandleResponse.ok("Xóa sản phẩm thành công");
    }
}
