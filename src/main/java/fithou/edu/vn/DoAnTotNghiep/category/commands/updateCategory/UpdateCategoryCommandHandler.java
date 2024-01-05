package fithou.edu.vn.DoAnTotNghiep.category.commands.updateCategory;

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
public class UpdateCategoryCommandHandler implements IRequestHandler<UpdateCategoryCommand, String> {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public HandleResponse<String> handle(UpdateCategoryCommand command) throws Exception {
        Optional<Category> exitsCategory = categoryRepository.findById(command.getId());
        if (exitsCategory.isEmpty()) {
            return HandleResponse.error("Danh mục không tồn tại");
        }
        boolean isUpdateName = exitsCategory.get().getName().equals(command.getName());

        if (isUpdateName) {
            var existWithName = categoryRepository.findByName(command.getName());
            if (existWithName.isPresent()) {
                return HandleResponse.error("Tên danh mục đã tồn tại");
            }
            exitsCategory.get().setName(command.getName());
        }
        categoryRepository.save(exitsCategory.get());
        return HandleResponse.ok("Sửa danh mục thành công");
    }
}
