package fithou.edu.vn.DoAnTotNghiep.blog.commands.createBlog;

import com.github.slugify.Slugify;
import fithou.edu.vn.DoAnTotNghiep.auth.security.CustomUserDetails;
import fithou.edu.vn.DoAnTotNghiep.blog.entity.Blog;
import fithou.edu.vn.DoAnTotNghiep.blog.repository.BlogRepository;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.config.Contant;
import fithou.edu.vn.DoAnTotNghiep.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@AllArgsConstructor
public class CreateBlogCommandHandler implements IRequestHandler<CreateBlogCommand, String> {
    @Autowired
    private BlogRepository blogRepository;
    @Override
    public HandleResponse<String> handle(CreateBlogCommand command) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails details = (CustomUserDetails) authentication.getPrincipal();
            User user = details.getUser();

            if (blogRepository.existsByTitle(command.getTitle())) {
                return HandleResponse.error("Tiêu đề bài viết đã tồn tại, vui lòng chọn tiêu đề khác");
            }
            Blog blog = new Blog() ;
            blog.setTitle(command.getTitle());
            blog.setContent(command.getContent().replaceAll("<[^>]*>", ""));

            Slugify slg = new Slugify() ;
            blog.setSlug(slg.slugify(command.getTitle()));
            blog.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            blog.setModifiedDate(new Timestamp(System.currentTimeMillis()));
            if (command.getStatus() == Contant.PUBLIC_BLOG) {
                if (command.getDescription().isEmpty()) {
                    return HandleResponse.error("Để công khai bài viết vui lòng thêm mô tả cho bài viết");
                }
                blog.setPublibDate(new Timestamp(System.currentTimeMillis()));
            }
            blog.setDescription(command.getDescription().replaceAll("<[^>]*>", ""));
            blog.setStatus(command.getStatus());
            blog.setImageUrl(command.getImageUrl());
            blog.setCreatedBy(user);
            blogRepository.save(blog);
            return HandleResponse.ok("Thêm bài viết thành công");
        }
        else {
            return HandleResponse.error("Bạn không có quyền");
        }
    }
}
