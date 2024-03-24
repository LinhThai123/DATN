package fithou.edu.vn.DoAnTotNghiep.cart.commands.removeItem;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RemoveItemFromCartCommand implements IRequest<Void> {
    private String productOptionId;
}
