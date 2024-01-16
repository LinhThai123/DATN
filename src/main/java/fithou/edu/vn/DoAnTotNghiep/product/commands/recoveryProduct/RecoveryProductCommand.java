package fithou.edu.vn.DoAnTotNghiep.product.commands.recoveryProduct;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RecoveryProductCommand implements IRequest<String> {
    @NotNull
    private String id;
}
