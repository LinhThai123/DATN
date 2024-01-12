package fithou.edu.vn.DoAnTotNghiep.blog.query.getAllBlog;

import fithou.edu.vn.DoAnTotNghiep.blog.dto.BlogDto;
import fithou.edu.vn.DoAnTotNghiep.blog.repository.BlogRepository;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.common.dto.Paginated;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class GetAllBlogQueriesHandler implements IRequestHandler<GetAllBlogQueries, Paginated<BlogDto>> {

    @Autowired
    private  BlogRepository blogRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public HandleResponse<Paginated<BlogDto>> handle(GetAllBlogQueries queries) throws Exception {
        var blogs = blogRepository.findAllByTitleContainingIgnoreCase(queries.getKeyword(),
                queries.getPageable("id"));
        return HandleResponse.ok(Paginated.of(blogs.map(blog -> modelMapper.map(blog, BlogDto.class))));
    }
}
