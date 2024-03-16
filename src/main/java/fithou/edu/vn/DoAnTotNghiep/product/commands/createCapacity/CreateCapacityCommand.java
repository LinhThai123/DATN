package fithou.edu.vn.DoAnTotNghiep.product.commands.createCapacity;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCapacityCommand implements IRequest<String> {

    private String id ;

    @NotEmpty(message = "Tên dung lượng không được để trống")
    @Length(min = 1, max = 50, message = "Tên dung lượng phải từ 1 đến 50 ký tự")
    private String name;
}
