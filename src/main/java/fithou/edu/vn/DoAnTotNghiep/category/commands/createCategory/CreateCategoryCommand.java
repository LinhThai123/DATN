package fithou.edu.vn.DoAnTotNghiep.category.commands.createCategory;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateCategoryCommand implements IRequest<String> {

    private String id ;

    @NotEmpty(message = "Tên danh mục không được để trống")
    @Length(min = 1, max = 50, message = "Tên danh mục phải từ 1 đến 50 ký tự")
    private String name;

    private String parentId = "0";
}
