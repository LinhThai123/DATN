package fithou.edu.vn.DoAnTotNghiep.product.commands.createProductColor;

import fithou.edu.vn.DoAnTotNghiep.category.entity.Category;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.product.commands.createProductOption.CreateProductoptionCommandHandler;
import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductColor;
import fithou.edu.vn.DoAnTotNghiep.product.repository.ProductColorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@AllArgsConstructor
@Service
public class CreateProductColorCommandHandler implements IRequestHandler<CreateProductColorCommand, String> {

    @Autowired
    private ProductColorRepository productColorRepository;
    @Override
    public HandleResponse<String> handle(CreateProductColorCommand request) throws Exception {
        var productColor = new ProductColor() ;
        var colorName = productColorRepository.findByName(request.getName());
        if(colorName.isPresent()) {
            return HandleResponse.error("Màu sắc đã tồn tại");
        }
        productColor.setName(request.getName());
        productColor.setDeleteDate(null);
        productColor.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        productColor.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        productColorRepository.save(productColor);
        return HandleResponse.ok(productColor.getId());
    }
}
