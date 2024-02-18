package fithou.edu.vn.DoAnTotNghiep.product.commands.updateColor;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateColorCommand implements IRequest<String> {

    @NotNull
    private String id;

    @NotNull(message = "Tên màu sắc  không được để trống")
    @NotEmpty(message = "Tên màu sắc không được để trống")
    private String name;
}
