package fithou.edu.vn.DoAnTotNghiep.category.query.getAllCategories;

import fithou.edu.vn.DoAnTotNghiep.category.dto.CategoryDto;
import fithou.edu.vn.DoAnTotNghiep.common.PaginationRequest;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import fithou.edu.vn.DoAnTotNghiep.common.dto.Paginated;
import lombok.Getter;

@Getter
public class GetAllCategoriesQueries extends PaginationRequest implements IRequest<Paginated<CategoryDto>> {

}
