package fithou.edu.vn.DoAnTotNghiep.category.endpoint;

import fithou.edu.vn.DoAnTotNghiep.category.commands.createCategory.CreateCategoryCommand;
import fithou.edu.vn.DoAnTotNghiep.category.commands.deleteCategory.DeleteCategoryCommand;
import fithou.edu.vn.DoAnTotNghiep.category.commands.updateCategory.UpdateCategoryCommand;
import fithou.edu.vn.DoAnTotNghiep.category.dto.CategoryDto;
import fithou.edu.vn.DoAnTotNghiep.category.query.getAllCategories.GetAllCategoriesQueries;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.ISender;
import fithou.edu.vn.DoAnTotNghiep.common.dto.Paginated;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/category")
public class CategoryApiController {

    @Autowired
    private ISender sender;

    @GetMapping()
    public Paginated<CategoryDto> getCategories(@Valid @ParameterObject GetAllCategoriesQueries paginationRequest) {
        return sender.send(paginationRequest).get();
    }
    @PostMapping("/add")
    @PostAuthorize("hasAuthority('CATEGORY_MANAGEMENT')")
    public ResponseEntity<String> createCategory (@Valid @RequestBody CreateCategoryCommand command) {
        var result = sender.send(command);
        return ResponseEntity.ok(result.orThrow());
    }

    @PutMapping("/update")
    @PostAuthorize("hasAuthority('CATEGORY_MANAGEMENT')")
    @Secured("CATEGORY_MANAGEMENT")
    public ResponseEntity<String> updateCategory(@Valid @RequestBody UpdateCategoryCommand command) {
        var result = sender.send(command);
        return ResponseEntity.ok(result.orThrow());

    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    @PostAuthorize("hasAuthority('CATEGORY_MANAGEMENT')")
    public ResponseEntity<String> deleteCategory(@PathVariable String id) {
        if (id.isBlank()) throw new IllegalArgumentException("id is null");
        sender.send(new DeleteCategoryCommand(id));
        return ResponseEntity.ok("Xóa danh mục thành công");
    }
}
