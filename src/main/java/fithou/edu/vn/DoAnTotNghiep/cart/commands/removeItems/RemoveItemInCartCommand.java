package fithou.edu.vn.DoAnTotNghiep.cart.commands.removeItems;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RemoveItemInCartCommand implements IRequest<Void> {
    public List<String> productOptionIds = new ArrayList<>();
}
