package fithou.edu.vn.DoAnTotNghiep.product.commands.createProductOption;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductOptionCommand implements IRequest<String> {

    private String id;

    @NotNull(message = "Sản phẩm không được để trống")
    private String productColorId;

    @NotNull(message = "Sản phẩm không được để trống")
    private String capacityId;

    private Integer quantity;

    @NotNull(message = "Sản phẩm không được để trống")
    private String productId;
}
