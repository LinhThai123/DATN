package fithou.edu.vn.DoAnTotNghiep.category.service;

import fithou.edu.vn.DoAnTotNghiep.category.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    Page<Category> adminGetListCategory(String name, int page);
}
