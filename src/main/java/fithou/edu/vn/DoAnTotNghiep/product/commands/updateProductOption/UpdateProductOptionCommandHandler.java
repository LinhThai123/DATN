package fithou.edu.vn.DoAnTotNghiep.product.commands.updateProductOption;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Capacity;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Product;
import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductColor;
import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductOption;
import fithou.edu.vn.DoAnTotNghiep.product.repository.CapacityRepository;
import fithou.edu.vn.DoAnTotNghiep.product.repository.ProductColorRepository;
import fithou.edu.vn.DoAnTotNghiep.product.repository.ProductOptionRepository;
import fithou.edu.vn.DoAnTotNghiep.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateProductOptionCommandHandler implements IRequestHandler<UpdateProductOptionCommand, String> {

    @Autowired
    private ProductOptionRepository productOptionRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductColorRepository productColorRepository;

    @Autowired
    private CapacityRepository capacityRepository;
    @Override
    public HandleResponse<String> handle(UpdateProductOptionCommand updateProductOptionCommand) throws Exception {
        Optional<ProductOption> exitsProductOption = productOptionRepository.findById(updateProductOptionCommand.getId());
        if (exitsProductOption.isEmpty()) {
            return HandleResponse.error("Không tìm thấy phân loại của sản phẩm");
        }
        Optional<Product> exitsProduct = productRepository.findById(updateProductOptionCommand.getProductId());
        if (exitsProduct.isEmpty()) {
            return HandleResponse.error("Không tìm thấy sản phẩm");
        }
        exitsProductOption.get().setProduct(exitsProduct.get());
        Optional<ProductColor> exitsProductColor = productColorRepository.findById(updateProductOptionCommand.getProductColorId());
        if (exitsProductColor.isEmpty()) {
            return HandleResponse.error("Không tìm thấy màu sắc sản phẩm");
        }
        exitsProductOption.get().setProduct_color(exitsProductColor.get());
        Optional<Capacity> exitsCapacity = capacityRepository.findById(updateProductOptionCommand.getCapacityId());
        if (exitsCapacity.isEmpty()) {
            return HandleResponse.error("Không tìm thấy dung lượng sản phẩm");
        }
        exitsProductOption.get().setCapacity(exitsCapacity.get());
        productOptionRepository.save(exitsProductOption.get());
        return HandleResponse.ok(exitsProductOption.get().getId());
    }
}
