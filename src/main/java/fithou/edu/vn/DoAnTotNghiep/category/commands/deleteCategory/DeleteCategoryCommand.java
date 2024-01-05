package fithou.edu.vn.DoAnTotNghiep.category.commands.deleteCategory;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DeleteCategoryCommand implements IRequest<String> {
    private String id ;
}
