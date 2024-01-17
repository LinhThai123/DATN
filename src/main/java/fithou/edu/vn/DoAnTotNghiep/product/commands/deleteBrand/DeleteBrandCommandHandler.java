package fithou.edu.vn.DoAnTotNghiep.product.commands.deleteBrand;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.product.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class DeleteBrandCommandHandler implements IRequestHandler<DeleteBrandCommand, String> {
    @Autowired
    private BrandRepository brandRepository;
    @Override
    @Transactional
    public HandleResponse<String> handle(DeleteBrandCommand command) throws Exception {
        var brand = brandRepository.findById(command.getId());
        if (brand.isEmpty()) {
            return HandleResponse.error("Không tìm thấy thương hiệu", HttpStatus.NOT_FOUND);
        }
        brandRepository.delete(brand.get());
        return HandleResponse.ok("Xóa thương hiệu thành công");
    }
}
