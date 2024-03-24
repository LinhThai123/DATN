package fithou.edu.vn.DoAnTotNghiep.cart.commands.updateCartItemQuantity;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCartItemQuantityCommand implements IRequest<Void> {

    private String productOptionId;

    @Min(value = 1, message = "Số lượng phải lớn hơn 0")
    private int newQuantity;
}
