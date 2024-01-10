package fithou.edu.vn.DoAnTotNghiep.supplier.commands.deleteSupplier;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeleteSuppilerCommand implements IRequest<String> {
    private String id;
}
