package fithou.edu.vn.DoAnTotNghiep.blog.commands.deleteBlog;

import fithou.edu.vn.DoAnTotNghiep.blog.entity.Blog;
import fithou.edu.vn.DoAnTotNghiep.blog.repository.BlogRepository;
import fithou.edu.vn.DoAnTotNghiep.category.entity.Category;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@AllArgsConstructor
@Service
public class DeleteBlogCommandHandler implements IRequestHandler<DeleteBlogCommand, String>{

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public HandleResponse<String> handle(DeleteBlogCommand deleteBlogCommand) throws Exception {

        Optional<Blog> exitsBlog = blogRepository.findById(deleteBlogCommand.getId());
        if (exitsBlog.isEmpty()) {
            return HandleResponse.error("Bài viết không tồn tại");
        }
        blogRepository.delete(exitsBlog.get());
        return HandleResponse.ok("Xóa bài viết thành công");
    }
}