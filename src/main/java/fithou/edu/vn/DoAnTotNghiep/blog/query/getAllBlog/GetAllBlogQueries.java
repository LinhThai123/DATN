package fithou.edu.vn.DoAnTotNghiep.blog.query.getAllBlog;

import fithou.edu.vn.DoAnTotNghiep.blog.dto.BlogDto;
import fithou.edu.vn.DoAnTotNghiep.category.dto.CategoryDto;
import fithou.edu.vn.DoAnTotNghiep.common.PaginationRequest;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import fithou.edu.vn.DoAnTotNghiep.common.dto.Paginated;
import lombok.Getter;

@Getter
public class GetAllBlogQueries extends PaginationRequest implements IRequest<Paginated<BlogDto>> {
}
