package fithou.edu.vn.DoAnTotNghiep.product.commands.updateBrand;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateBrandCommand implements IRequest<String> {

    @NotNull
    private String id;

    @NotEmpty(message = "Tên thương hiệu không được để trống")
    private String name;
}
