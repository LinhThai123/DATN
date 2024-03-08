package fithou.edu.vn.DoAnTotNghiep.blog.commands.createBlog;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import fithou.edu.vn.DoAnTotNghiep.user.entity.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateBlogCommand implements IRequest<String> {

    private String id ;

    @NotEmpty(message = "Tiêu đề không được để trống")
    @Length(min = 1, max = 300, message = "Tiêu đề phải từ 1 đến 300 ký tự")
    private String title;

    @NotEmpty(message = "Tiêu đề không được để trống")
    private String content;

    @NotEmpty(message = "Tiêu đề không được để trống")
    private String description;

    private Integer status;

    private String imageUrl;


}
