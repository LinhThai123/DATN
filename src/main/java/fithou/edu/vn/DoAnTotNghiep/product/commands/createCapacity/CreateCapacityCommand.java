package fithou.edu.vn.DoAnTotNghiep.product.commands.createCapacity;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateCapacityCommand implements IRequest<String> {
    @NotNull(message = "Tên dung lượng không được để trống")
    @NotEmpty(message = "Tên dung lượng không được để trống")
    private String name;
}
