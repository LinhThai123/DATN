package fithou.edu.vn.DoAnTotNghiep.product.commands.recoveryProduct;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RecoveryProductCommandHandler implements IRequestHandler<RecoveryProductCommand, String> {
    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public HandleResponse<String> handle(RecoveryProductCommand recoveryProductCommand) throws Exception {
        productRepository.recoveryByProductId(recoveryProductCommand.getId());
        return HandleResponse.ok("Recovery success");
    }
}
