package fithou.edu.vn.DoAnTotNghiep.cart.commands.removeItems;

import fithou.edu.vn.DoAnTotNghiep.cart.repository.CartRepository;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RemoveItemsInCartCommnadHandler implements IRequestHandler<RemoveItemInCartCommand, Void> {

    @Autowired
    private CartRepository cartRepository;
    @Override
    public HandleResponse<Void> handle(RemoveItemInCartCommand removeItemInCartCommand) throws Exception {
        cartRepository.deleteAllByProductOptionIdIn(removeItemInCartCommand.getProductOptionIds());
        return HandleResponse.ok();
    }
}
