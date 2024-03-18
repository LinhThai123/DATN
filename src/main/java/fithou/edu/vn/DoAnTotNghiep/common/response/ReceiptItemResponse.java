package fithou.edu.vn.DoAnTotNghiep.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReceiptItemResponse {
    private String id;
    private Integer quantity;
    private Integer price;
    private ProductOptionResponse productOptionResponse;
    private String productId;
    private String productName;
    private String productColor;
    private String productCapacity;
}
