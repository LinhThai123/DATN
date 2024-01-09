package fithou.edu.vn.DoAnTotNghiep.product.commands.createBrand;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Brand;
import fithou.edu.vn.DoAnTotNghiep.product.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@AllArgsConstructor
public class CreateBrandCommandHandler implements IRequestHandler<CreateBrandCommand, String> {

    private final BrandRepository brandRepository;
    @Override
    public HandleResponse<String> handle(CreateBrandCommand createBrandCommand) throws Exception {
        var existWithName = brandRepository.findByNameIgnoreCase(createBrandCommand.getName());
        if (existWithName.isPresent()) {
            return HandleResponse.error("Tên thương hiệu đã tồn tại");
        }
        var brand = new Brand();
        brand.setName(createBrandCommand.getName());
        brand.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        brand.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        brand.setImageUrl(createBrandCommand.getImageUrl());
        brandRepository.save(brand);
        return HandleResponse.ok("Thêm thương hiệu thành công");
    }
}
