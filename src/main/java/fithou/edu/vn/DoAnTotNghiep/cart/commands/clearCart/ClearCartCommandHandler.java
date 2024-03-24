package fithou.edu.vn.DoAnTotNghiep.cart.commands.clearCart;

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
public class ClearCartCommandHandler implements IRequestHandler<ClearCartCommand, String> {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserService userService;


    @Override
    @Transactional
    public HandleResponse<String> handle(ClearCartCommand clearCartCommand) {
        var currentUserId = userService.getCurrentUserId();
        if (currentUserId.isEmpty()) {
            return HandleResponse.error("Bạn chưa đăng nhập");
        }
        return HandleResponse.ok(cartRepository.deleteAllByUserId(currentUserId.get()));
    }
}

