package fithou.edu.vn.DoAnTotNghiep.product.commands.updateCapacity;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class UpdateCapacityCommand implements IRequest<String> {

    private String id ;

    @NotNull(message = "Tên dung lượng được để trống")
    @Length(min = 4, max = 10, message = "Tên dung lượng phải từ 4 đến 10 ký tự")
    private String name;
}
