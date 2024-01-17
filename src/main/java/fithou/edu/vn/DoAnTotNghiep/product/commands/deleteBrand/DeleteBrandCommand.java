package fithou.edu.vn.DoAnTotNghiep.product.commands.deleteBrand;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DeleteBrandCommand implements IRequest<String> {
    private String id;
}
