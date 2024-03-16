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

    @Override
    public HandleResponse<String> handle(UpdateProductOptionCommand updateProductOptionCommand) throws Exception {
        return null;
    }
}
