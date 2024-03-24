package fithou.edu.vn.DoAnTotNghiep.cart.commands.updateCartItemQuantity;

import fithou.edu.vn.DoAnTotNghiep.cart.commands.addToCart.AddToCartCommand;
import fithou.edu.vn.DoAnTotNghiep.cart.repository.CartRepository;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.ISender;
import fithou.edu.vn.DoAnTotNghiep.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class UpdateCartItemQuantityCommandHandller implements IRequestHandler<UpdateCartItemQuantityCommand, Void> {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ISender sender;
    @Override
    @Transactional
    public HandleResponse<Void> handle(UpdateCartItemQuantityCommand updateCartItemQuantityCommand) throws Exception {
        var currentUserId = userService.getCurrentUserId();
        if (currentUserId.isEmpty()) {
            return HandleResponse.error("Bạn chưa đăng nhập");
        }
        var cartItem = cartRepository.findByUserIdAndProductOptionId(currentUserId.get(), updateCartItemQuantityCommand.getProductOptionId());
        if (cartItem.isEmpty()) {
            return sender.send( AddToCartCommand.builder().productOptionId(updateCartItemQuantityCommand.getProductOptionId()).quantity( updateCartItemQuantityCommand.getNewQuantity()).build());
        }
        var currentStock = cartItem.get().getProductOption().getQuantity();
        if (currentStock < updateCartItemQuantityCommand.getNewQuantity()) {
            return HandleResponse.error("Số lượng sản phẩm không đủ");
        }
        cartItem.get().setQuantity(updateCartItemQuantityCommand.getNewQuantity());
        cartRepository.save(cartItem.get());
        return HandleResponse.ok();
    }
}
