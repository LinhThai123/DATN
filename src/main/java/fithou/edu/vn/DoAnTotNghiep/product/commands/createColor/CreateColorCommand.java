package fithou.edu.vn.DoAnTotNghiep.product.commands.createColor;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateColorCommand implements IRequest<String> {

    @NotNull(message = "Tên màu không được để trống")
    @NotEmpty(message = "Tên màu không được để trống")
    private String name;

}
