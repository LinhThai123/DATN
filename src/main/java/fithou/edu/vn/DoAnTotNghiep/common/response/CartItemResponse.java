package fithou.edu.vn.DoAnTotNghiep.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponse {

    private String id;

    private String productOptionId;

    private String productOptionThumbnail;

    private String productName;

    private String productSlug;

    private String productColor;

    private Double productPrice;

    private Integer quantityInStock;

    private Integer quantity;
}
