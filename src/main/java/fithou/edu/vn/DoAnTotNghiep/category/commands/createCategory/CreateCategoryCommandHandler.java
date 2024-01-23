package fithou.edu.vn.DoAnTotNghiep.category.commands.createCategory;

import fithou.edu.vn.DoAnTotNghiep.category.entity.Category;
import fithou.edu.vn.DoAnTotNghiep.category.repository.CategoryRepository;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@AllArgsConstructor
@Service
public class CreateCategoryCommandHandler implements IRequestHandler<CreateCategoryCommand, String> {

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    @Transactional
    public HandleResponse<String> handle(CreateCategoryCommand request) throws Exception {
        var category = new Category() ;
        var parent = categoryRepository.findById(request.getParentId());
        if (request.getParentId().isEmpty() && parent.isEmpty()) {
            return HandleResponse.error("Danh mục cha không tồn tại");
        }
        var categoryName = categoryRepository.findByName(request.getName());
        if(categoryName.isPresent()) {
            return HandleResponse.error("Danh mục đã tồn tại");
        }
        category.setName(request.getName());
        category.setParent(parent.orElse(null));
        category.setDeleteDate(null);
        category.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        category.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        categoryRepository.save(category);
        return HandleResponse.ok(category.getId());
    }
}
