package fithou.edu.vn.DoAnTotNghiep.product.commands.deleteCapacity;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DeleteCapacityCommand implements IRequest<String> {
    private String id;
}
