package fithou.edu.vn.DoAnTotNghiep.supplier.query.getAllSupplier;

import fithou.edu.vn.DoAnTotNghiep.common.PaginationRequest;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import fithou.edu.vn.DoAnTotNghiep.common.dto.Paginated;
import fithou.edu.vn.DoAnTotNghiep.supplier.dto.SupplierDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetAllSuppliersQuery extends PaginationRequest implements IRequest<Paginated<SupplierDto>> {
}
