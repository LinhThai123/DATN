package fithou.edu.vn.DoAnTotNghiep.product.commands.createSpecification;

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
public class CreateSpecificationCommandHandler implements IRequestHandler<CreateSpecificationCommand, String> {
    @Autowired
    private SpecificationRepository specificationRepository;

    @Autowired
    private ProductRepository productRepository;
    @Override
    @Transactional
    public HandleResponse<String> handle(CreateSpecificationCommand command) throws Exception {
        Optional<Product> exitsProduct = productRepository.findById(command.getProductId());
        if (exitsProduct.isEmpty()) {
            return HandleResponse.error("Sản phẩm không tồn tại");
        }
        Specification specification = new Specification() ;
        specification.setScreen_size(command.getScreen_size());
        specification.setResolution_screen(command.getResolution_screen());
        specification.setSize(command.getSize());
        specification.setWeight(command.getWeight());
        specification.setCamera_after(command.getCamera_after());
        specification.setCamera_before(command.getCamera_before());
        specification.setBluetooth(command.getBluetooth());
        specification.setOs(command.getOs());
        specification.setChip(command.getChip());
        specification.setPin(command.getPin());
        specification.setCpu(command.getCpu());
        specification.setCharging_port(command.getCharging_port());
        specification.setProduct(exitsProduct.get());
        specification.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        specification.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        specificationRepository.save(specification);
        return HandleResponse.ok(specification.getId());
    }
}
