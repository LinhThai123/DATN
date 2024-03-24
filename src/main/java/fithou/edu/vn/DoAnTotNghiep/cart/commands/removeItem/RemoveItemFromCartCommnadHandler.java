package fithou.edu.vn.DoAnTotNghiep.cart.commands.removeItem;

import fithou.edu.vn.DoAnTotNghiep.cart.repository.CartRepository;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class RemoveItemFromCartCommnadHandler implements IRequestHandler<RemoveItemFromCartCommand, String> {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserService userService;
    @Override
    @Transactional
    public HandleResponse<String> handle(RemoveItemFromCartCommand removeItemFromCartCommand) {
        var currentUserId = userService.getCurrentUserId();
        if (currentUserId.isEmpty()) {
            return HandleResponse.error("Bạn chưa đăng nhập");
        }
        var cartItem = cartRepository.findByUserIdAndProductOptionId(currentUserId.get(), removeItemFromCartCommand.getProductOptionId());
        if (cartItem.isEmpty()) {
            return HandleResponse.error("Sản phẩm không tồn tại trong giỏ hàng");
        }
        cartRepository.delete(cartItem.get());
        return HandleResponse.ok();
    }
}
