package fithou.edu.vn.DoAnTotNghiep.blog.commands.updateBlog;

import com.github.slugify.Slugify;
import fithou.edu.vn.DoAnTotNghiep.blog.entity.Blog;
import fithou.edu.vn.DoAnTotNghiep.blog.repository.BlogRepository;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.config.Contant;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateBlogCommandHandler implements IRequestHandler<UpdateBlogCommand, String> {

    @Autowired
    private BlogRepository blogRepository;
    @Override
    public HandleResponse<String> handle(UpdateBlogCommand command) throws Exception {
        Blog blog;
        Optional<Blog> exitsBlog = blogRepository.findById(command.getId());
        blog = exitsBlog.get();
        if (!exitsBlog.isPresent()) {
            return HandleResponse.error("Bài viết không tồn tại");
        }
        blog.setTitle(command.getTitle());
        blog.setContent(command.getContent());

        Slugify slg = new Slugify() ;
        blog.setSlug(slg.slugify(command.getTitle()));
        blog.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        blog.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        if (command.getStatus() == Contant.PUBLIC_BLOG) {
            if (command.getDescription().isEmpty()) {
                return HandleResponse.error("Để công khai bài viết vui lòng thêm mô tả cho bài viết");
            }
            if (command.getImageUrl().isEmpty()) {
                return HandleResponse.error("Thêm hình ảnh cho bài viết");
            }
            blog.setPublibDate(new Timestamp(System.currentTimeMillis()));
        }
        blog.setDescription(command.getDescription());
        blog.setStatus(command.getStatus());
        blog.setImageUrl(command.getImageUrl());
        blogRepository.save(blog);
        return HandleResponse.ok("Sửa bài viết thành công");
    }
}
