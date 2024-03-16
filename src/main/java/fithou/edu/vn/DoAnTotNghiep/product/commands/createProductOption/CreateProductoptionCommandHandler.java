package fithou.edu.vn.DoAnTotNghiep.product.commands.createProductOption;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductOption;
import fithou.edu.vn.DoAnTotNghiep.product.repository.CapacityRepository;
import fithou.edu.vn.DoAnTotNghiep.product.repository.ProductColorRepository;
import fithou.edu.vn.DoAnTotNghiep.product.repository.ProductOptionRepository;
import fithou.edu.vn.DoAnTotNghiep.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@AllArgsConstructor
@Service
public class CreateProductoptionCommandHandler implements IRequestHandler<CreateProductOptionCommand, String> {

    @Autowired
    private ProductOptionRepository productOptionRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductColorRepository productColorRepository;

    @Autowired
    private CapacityRepository capacityRepository;

    @Override
    @Transactional
    public HandleResponse<String> handle(CreateProductOptionCommand createProductOptionCommand) throws Exception {
        var product = productRepository.findById(createProductOptionCommand.getProductId()) ;
        if (product.isEmpty()) {
            return HandleResponse.error("Sản phẩm không tồn tại");
        }
        var productColor = productColorRepository.findById(createProductOptionCommand.getProductColorId());
        if (productColor.isEmpty()) {
            return HandleResponse.error("Màu sắc không tồn tại trong bảng màu");
        }
        var capacity = capacityRepository.findById(createProductOptionCommand.getCapacityId());
        if (capacity.isEmpty()) {
            return HandleResponse.error("Dung lượng điện thoại không tồn tại");
        }

        var productOption = new ProductOption();
        productOption.setProduct_color(productColor.get());
        productOption.setCapacity(capacity.get());
        productOption.setStock(0);
        productOption.setQuantity(0);
        productOption.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        productOption.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        productOption.setProduct(product.get());
        productOptionRepository.save(productOption);
        return HandleResponse.ok(productOption.getId());
    }
}
