package fithou.edu.vn.DoAnTotNghiep.category.commands.updateCategory;

import fithou.edu.vn.DoAnTotNghiep.category.entity.Category;
import fithou.edu.vn.DoAnTotNghiep.category.repository.CategoryRepository;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateCategoryCommandHandler implements IRequestHandler<UpdateCategoryCommand, String> {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Transactional
    public HandleResponse<String> handle(UpdateCategoryCommand updateCategoryCommand) {
        var exist = categoryRepository.findById(updateCategoryCommand.getId());
        if (exist.isEmpty()) {
            return HandleResponse.error("Danh mục không tồn tại");
        }
        var isUpdateName = exist.get().getName().equals(updateCategoryCommand.getName());
        var isUpdateParent = exist.get().getParent() == null && updateCategoryCommand.getParentId().isEmpty() || exist.get().getParent() != null && exist.get().getParent().getId() == updateCategoryCommand.getParentId();
        if (isUpdateName && isUpdateParent) {
            return HandleResponse.error("Không có gì thay đổi");
        }

        if (!isUpdateName) {
            var existWithName = categoryRepository.findByName(updateCategoryCommand.getName());
            if (existWithName.isPresent()) {
                return HandleResponse.error("Tên danh mục đã tồn tại");
            }
            exist.get().setName(updateCategoryCommand.getName());
        }
        if (!isUpdateParent) {
            var parentCategory = categoryRepository.findById(updateCategoryCommand.getParentId());
            if (updateCategoryCommand.getParentId() != null && parentCategory.isEmpty()) {
                return HandleResponse.error("Danh mục cha không tồn tại");
            }
            exist.get().setParent(parentCategory.orElse(null));
        }
        categoryRepository.save(exist.get());
        return HandleResponse.ok("Sửa danh mục thành công");
    }
}
