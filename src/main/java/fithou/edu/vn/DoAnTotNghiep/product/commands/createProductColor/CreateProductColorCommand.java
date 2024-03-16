package fithou.edu.vn.DoAnTotNghiep.product.commands.createProductColor;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class CreateProductColorCommand implements IRequest<String> {
    private String id ;

    @NotEmpty(message = "Tên màu sắc không được để trống")
    @Length(min = 1, max = 50, message = "Tên màu sắc phải từ 1 đến 50 ký tự")
    private String name;
}
