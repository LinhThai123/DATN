package fithou.edu.vn.DoAnTotNghiep.product.commands.updateBrand;

import fithou.edu.vn.DoAnTotNghiep.category.entity.Category;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Brand;
import fithou.edu.vn.DoAnTotNghiep.product.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateBrandCommandHandler implements IRequestHandler<UpdateBrandCommand, String> {
    @Autowired
    private BrandRepository brandRepository;
    @Override
    public HandleResponse<String> handle(UpdateBrandCommand command) throws Exception {
        Optional<Brand> exitsBrand = brandRepository.findById(command.getId());
        if (exitsBrand.isEmpty()) {
            return HandleResponse.error("Thương hiệu không tồn tại");
        }
        boolean isUpdateName = exitsBrand.get().getName().equals(command.getName());

        if (isUpdateName) {
            var existWithName = brandRepository.findByNameIgnoreCase(command.getName());
            if (existWithName.isPresent()) {
                return HandleResponse.error("Tên danh mục đã tồn tại");
            }
            exitsBrand.get().setName(command.getName());
        }
        brandRepository.save(exitsBrand.get());
        return HandleResponse.ok("Sửa thương hiệu thành công");
    }
}
