package fithou.edu.vn.DoAnTotNghiep.product.commands.updateCapacity;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCapacityCommand implements IRequest<String> {

    private String id;

    @NotEmpty(message = "Tên dung lượng không được để trống")
    @Length(min = 3, max = 50, message = "Tên dung lượng phải từ 3 đến 50 ký tự")
    private String name;
}
