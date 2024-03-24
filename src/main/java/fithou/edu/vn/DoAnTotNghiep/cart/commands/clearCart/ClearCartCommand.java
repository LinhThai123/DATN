package fithou.edu.vn.DoAnTotNghiep.cart.commands.clearCart;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class ClearCartCommand implements IRequest<String> {

    public ClearCartCommand execute() {
        return new ClearCartCommand();
    }
}
