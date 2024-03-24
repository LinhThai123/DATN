package fithou.edu.vn.DoAnTotNghiep.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse implements Serializable {

    private String id;

    private Integer totalProduct;

    private List<CartItemResponse> cartItem;
}
