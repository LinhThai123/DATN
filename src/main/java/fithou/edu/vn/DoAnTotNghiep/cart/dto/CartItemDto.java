package fithou.edu.vn.DoAnTotNghiep.cart.dto;

import fithou.edu.vn.DoAnTotNghiep.product.dto.ProductOptionDetailDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {

    private String userId;

    private String productOptionId;

    private int quantity;

    private ProductOptionDetailDto productOption;
}
