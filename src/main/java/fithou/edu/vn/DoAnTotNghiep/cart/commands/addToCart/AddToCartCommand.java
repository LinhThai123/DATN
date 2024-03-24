package fithou.edu.vn.DoAnTotNghiep.cart.commands.addToCart;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import fithou.edu.vn.DoAnTotNghiep.common.response.CartResponse;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AddToCartCommand implements IRequest<Void> {

    private String id;

    @NotNull(message = "Giá sản phẩm không được để trống")
    @DecimalMin(value = "0", inclusive = false, message = "Giá phải lớn hơn 0")
    private Integer quantity;

    @NotNull(message = "Mã sản phẩm không được để trống")
    private String productOptionId;

}
