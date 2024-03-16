package fithou.edu.vn.DoAnTotNghiep.product.commands.updateSpecification;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Product;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Specification;
import fithou.edu.vn.DoAnTotNghiep.product.repository.ProductRepository;
import fithou.edu.vn.DoAnTotNghiep.product.repository.SpecificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UpdateSpecificationCommandHandler implements IRequestHandler<UpdateSpecificationCommand , String> {

    @Autowired
    private SpecificationRepository specificationRepository;

    @Autowired
    private ProductRepository productRepository;
    @Override
    @Transactional
    public HandleResponse<String> handle(UpdateSpecificationCommand command) throws Exception {
        Optional<Specification> exitsSpecification = specificationRepository.findById(command.getId());
        if (exitsSpecification.isEmpty()) {
            return HandleResponse.error("Thông số kỹ thuật của sản phẩm không tồn tại ");
        }
        Optional<Product> exitsProduct = productRepository.findById(command.getProductId());
        if (exitsProduct.isEmpty()) {
            return HandleResponse.error("Thông số kỹ thuật của sản phẩm không tồn tại ");
        }
        exitsSpecification.get().setScreen_size(command.getScreen_size());
        exitsSpecification.get().setResolution_screen(command.getResolution_screen());
        exitsSpecification.get().setBluetooth(command.getBluetooth());
        exitsSpecification.get().setOs(command.getOs());
        exitsSpecification.get().setCpu(command.getCpu());
        exitsSpecification.get().setPin(command.getPin());
        exitsSpecification.get().setSize(command.getSize());
        exitsSpecification.get().setWeight(command.getWeight());
        exitsSpecification.get().setChip(command.getChip());
        exitsSpecification.get().setCamera_after(command.getCamera_after());
        exitsSpecification.get().setCamera_before(command.getCamera_before());
        exitsSpecification.get().setProduct(exitsProduct.get());
        exitsSpecification.get().setModifiedDate(new Timestamp(System.currentTimeMillis()));
        specificationRepository.save(exitsSpecification.get());
        return HandleResponse.ok(exitsSpecification.get().getId());
    }
}
