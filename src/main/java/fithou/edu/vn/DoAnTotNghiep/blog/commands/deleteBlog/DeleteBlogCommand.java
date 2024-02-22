package fithou.edu.vn.DoAnTotNghiep.blog.commands.deleteBlog;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DeleteBlogCommand implements IRequest<String> {

    private String id ;

}