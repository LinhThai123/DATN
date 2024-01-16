package fithou.edu.vn.DoAnTotNghiep.product.commands.deleteProduct;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DeleteProductCommand implements IRequest<String> {
    @NotNull
    private String id;
}
