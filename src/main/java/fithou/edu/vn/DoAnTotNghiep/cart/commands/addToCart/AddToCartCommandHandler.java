package fithou.edu.vn.DoAnTotNghiep.cart.commands.addToCart;

import fithou.edu.vn.DoAnTotNghiep.cart.entity.CartItem;
import fithou.edu.vn.DoAnTotNghiep.cart.repository.CartRepository;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.product.repository.ProductOptionRepository;
import fithou.edu.vn.DoAnTotNghiep.user.repository.UserRepository;
import fithou.edu.vn.DoAnTotNghiep.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AddToCartCommandHandler implements IRequestHandler<AddToCartCommand , Void> {

    @Autowired
    private ProductOptionRepository productOptionRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService ;
    @Override
    @Transactional
    public HandleResponse<Void> handle(AddToCartCommand addToCartCommand) throws Exception {
        var productOption = productOptionRepository.findById(addToCartCommand.getProductOptionId());
        if (productOption.isEmpty()) {
            return HandleResponse.error("Sản phẩm không tồn tại");
        }
        if (productOption.get().getQuantity() < addToCartCommand.getQuantity()) {
            return HandleResponse.error("Số lượng sản phẩm không đủ");
        }
        var currentUserId = userService.getCurrentUserId();
        if (currentUserId.isEmpty()) {
            return HandleResponse.error("Bạn chưa đăng nhập");
        }
        var cartItem = CartItem.builder()
                .quantity(addToCartCommand.getQuantity())
                .userId(currentUserId.get())
                .productOptionId(addToCartCommand.getProductOptionId())
                .build();
        cartRepository.save(cartItem);
        return HandleResponse.ok();
    }

}
