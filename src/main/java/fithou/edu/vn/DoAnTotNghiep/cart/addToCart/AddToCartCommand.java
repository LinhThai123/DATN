package fithou.edu.vn.DoAnTotNghiep.cart.addToCart;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddToCartCommand implements IRequest<Void> {


    private String productOptionId;

    @Min(value = 1, message = "Số lượng phải lớn hơn 0")
    private int quantity;

}
