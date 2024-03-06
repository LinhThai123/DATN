package fithou.edu.vn.DoAnTotNghiep.product.commands.createProductOption;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductOption;
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

    @Override
    @Transactional
    public HandleResponse<String> handle(CreateProductOptionCommand createProductOptionCommand) throws Exception {
        var product = productRepository.findById(createProductOptionCommand.getProductId()) ;
        if (product.isEmpty()) {
            return HandleResponse.error("Sản phẩm không tồn tại");
        }
        var productOption = new ProductOption();
        productOption.setScreen_size(createProductOptionCommand.getScreen_size());
        productOption.setResolution_screen(createProductOptionCommand.getResolution_screen());
        productOption.setRom(createProductOptionCommand.getRom());
        productOption.setRam(createProductOptionCommand.getRam());
        productOption.setChip(createProductOptionCommand.getChip());
        productOption.setCpu(createProductOptionCommand.getCpu());
        productOption.setSize(createProductOptionCommand.getSize());
        productOption.setWeight(createProductOptionCommand.getWeight());
        productOption.setCamera_after(createProductOptionCommand.getCamera_after());
        productOption.setCamera_before(createProductOptionCommand.getCamera_before());
        productOption.setPin(createProductOptionCommand.getPin());
        productOption.setCharging_port(createProductOptionCommand.getCharging_port());
        productOption.setOs(createProductOptionCommand.getOs());
        productOption.setBluetooth(createProductOptionCommand.getBluetooth());
        productOption.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        productOption.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        productOption.setColor(createProductOptionCommand.getColor());
        productOption.setStock(createProductOptionCommand.getStock());
        productOption.setProduct(product.get());
        productOptionRepository.save(productOption);
        return HandleResponse.ok(productOption.getId());
    }
}
