package fithou.edu.vn.DoAnTotNghiep.product.commands.updateProductOption;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.product.repository.ProductOptionRepository;
import fithou.edu.vn.DoAnTotNghiep.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@AllArgsConstructor
public class UpdateProductOptionCommandHandler implements IRequestHandler<UpdateProductOptionCommand, String> {

    @Autowired
    private ProductOptionRepository productOptionRepository;

    @Autowired
    private ProductRepository productRepository;
    @Override
    public HandleResponse<String> handle(UpdateProductOptionCommand updateProductOptionCommand) throws Exception {
        var existProductOption = productOptionRepository.findById(updateProductOptionCommand.getId());
        if (existProductOption.isEmpty()) {
            return HandleResponse.error("Chi tiết sản phẩm không tồn tại");
        }
        if (updateProductOptionCommand.getProductId() != existProductOption.get().getProduct().getId()) {
            var existProduct = productRepository.findById(updateProductOptionCommand.getProductId());
            if (existProduct.isEmpty()) {
                return HandleResponse.error("Sản phẩm không tồn tại");
            }
            existProductOption.get().setProduct(existProduct.get());
        }
        existProductOption.get().setScreen_size(updateProductOptionCommand.getScreen_size());
        existProductOption.get().setResolution_screen(updateProductOptionCommand.getResolution_screen());
        existProductOption.get().setRom(updateProductOptionCommand.getRom());
        existProductOption.get().setRam(updateProductOptionCommand.getRam());
        existProductOption.get().setChip(updateProductOptionCommand.getChip());
        existProductOption.get().setCpu(updateProductOptionCommand.getCpu());
        existProductOption.get().setSize(updateProductOptionCommand.getSize());
        existProductOption.get().setWeight(updateProductOptionCommand.getWeight());
        existProductOption.get().setCamera_after(updateProductOptionCommand.getCamera_after());
        existProductOption.get().setCamera_before(updateProductOptionCommand.getCamera_before());
        existProductOption.get().setPin(updateProductOptionCommand.getPin());
        existProductOption.get().setCharging_port(updateProductOptionCommand.getCharging_port());
        existProductOption.get().setOs(updateProductOptionCommand.getOs());
        existProductOption.get().setBluetooth(updateProductOptionCommand.getBluetooth());
        existProductOption.get().setCreatedDate(new Timestamp(System.currentTimeMillis()));
        existProductOption.get().setModifiedDate(new Timestamp(System.currentTimeMillis()));
        existProductOption.get().setColor(updateProductOptionCommand.getColor());
        existProductOption.get().setStock(updateProductOptionCommand.getStock());
        productOptionRepository.save(existProductOption.get());
        return HandleResponse.ok("Sửa sản phẩm thành công");
    }
}
