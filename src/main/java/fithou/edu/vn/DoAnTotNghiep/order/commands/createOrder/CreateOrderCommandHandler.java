package fithou.edu.vn.DoAnTotNghiep.order.commands.createOrder;

import fithou.edu.vn.DoAnTotNghiep.common.BusinessLogicException;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CreateOrderCommandHandler implements IRequestHandler<CreateOrderCommand, String> {
    @Override
    @Transactional(rollbackFor = {BusinessLogicException.class, Throwable.class})
    public HandleResponse<String> handle(CreateOrderCommand createOrderCommand) throws Exception {
        return null;
    }
}
