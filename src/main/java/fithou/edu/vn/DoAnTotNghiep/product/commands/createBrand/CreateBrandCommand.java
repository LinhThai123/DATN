package fithou.edu.vn.DoAnTotNghiep.product.commands.createBrand;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateBrandCommand implements IRequest<String> {

    @NotNull(message = "Tên thương hiệu không được để trống")
    @NotEmpty(message = "Tên thương hiệu không được để trống")
    private String name;

    private String imageUrl;
}
