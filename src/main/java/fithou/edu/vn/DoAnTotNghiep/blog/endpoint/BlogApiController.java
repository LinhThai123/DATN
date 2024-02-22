package fithou.edu.vn.DoAnTotNghiep.blog.endpoint;

import fithou.edu.vn.DoAnTotNghiep.blog.commands.createBlog.CreateBlogCommand;
import fithou.edu.vn.DoAnTotNghiep.blog.commands.deleteBlog.DeleteBlogCommand;
import fithou.edu.vn.DoAnTotNghiep.blog.commands.updateBlog.UpdateBlogCommand;
import fithou.edu.vn.DoAnTotNghiep.category.commands.deleteCategory.DeleteCategoryCommand;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.ISender;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/blog")
public class BlogApiController {
    @Autowired
    private ISender sender;

    @PostMapping("/add")
    public ResponseEntity<String> createBlog (@Valid @RequestBody CreateBlogCommand command) {
        var result = sender.send(command);
        return ResponseEntity.ok(result.orThrow());
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateBlog (@Valid @RequestBody UpdateBlogCommand command) {
        var result = sender.send(command);
        return ResponseEntity.ok(result.orThrow());
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteBlog(@PathVariable String id) {
        if (id.isBlank()) throw new IllegalArgumentException("id is null");
        sender.send(new DeleteBlogCommand(id));
        return ResponseEntity.ok("Xóa bài viết thành công");
    }
}
