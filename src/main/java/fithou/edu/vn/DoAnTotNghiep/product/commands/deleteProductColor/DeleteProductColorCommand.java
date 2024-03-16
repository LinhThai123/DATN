package fithou.edu.vn.DoAnTotNghiep.product.commands.deleteProductColor;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteProductColorCommand implements IRequest<String> {

    private String id;

}
