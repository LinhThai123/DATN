package fithou.edu.vn.DoAnTotNghiep.product.commands.updateProductColor;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateProductColorCommand implements IRequest<String> {

    private String id;

    @NotEmpty(message = "Tên màu sắc không được để trống")
    @Length(min = 3, max = 50, message = "Tên màu sắc phải từ 3 đến 50 ký tự")
    private String name;
}
