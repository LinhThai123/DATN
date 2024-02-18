package fithou.edu.vn.DoAnTotNghiep.product.commands.deleteColor;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DeleteColorCommand implements IRequest<String> {
    private String id;
}
