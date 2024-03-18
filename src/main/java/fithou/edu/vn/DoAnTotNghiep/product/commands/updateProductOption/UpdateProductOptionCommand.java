package fithou.edu.vn.DoAnTotNghiep.product.commands.updateProductOption;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateProductOptionCommand implements IRequest<String> {

    private String id ;

    @NotNull(message = "Màu sắc không được để trống")
    private String productColorId ;

    @NotNull(message = "Dung lượng không được để trống")
    private String capacityId;

    @NotNull(message = "Sản phẩm không được để trống")
    private String productId;


}
