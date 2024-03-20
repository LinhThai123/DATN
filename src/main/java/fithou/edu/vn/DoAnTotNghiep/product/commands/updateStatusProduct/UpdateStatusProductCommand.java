package fithou.edu.vn.DoAnTotNghiep.product.commands.updateStatusProduct;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStatusProductCommand implements IRequest<String> {
    private String id;
    private Integer status;
}
