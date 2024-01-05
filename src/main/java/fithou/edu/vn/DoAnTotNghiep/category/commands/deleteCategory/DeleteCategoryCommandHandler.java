package fithou.edu.vn.DoAnTotNghiep.category.commands.deleteCategory;

import fithou.edu.vn.DoAnTotNghiep.category.entity.Category;
import fithou.edu.vn.DoAnTotNghiep.category.repository.CategoryRepository;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DeleteCategoryCommandHandler implements IRequestHandler<DeleteCategoryCommand, String> {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public HandleResponse<String> handle(DeleteCategoryCommand command) throws Exception {
        Optional<Category> exitsCategory = categoryRepository.findById(command.getId());
        if (exitsCategory.isEmpty()) {
            return HandleResponse.error("Danh mục không tồn tại");
        }
        categoryRepository.delete(exitsCategory.get());
        return HandleResponse.ok("Xóa danh mục thành công");
    }
}
