package fithou.edu.vn.DoAnTotNghiep.category.service.impl;

import fithou.edu.vn.DoAnTotNghiep.category.entity.Category;
import fithou.edu.vn.DoAnTotNghiep.category.repository.CategoryRepository;
import fithou.edu.vn.DoAnTotNghiep.category.service.CategoryService;
import fithou.edu.vn.DoAnTotNghiep.config.Contant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getListCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Page<Category> adminGetListCategory(String name, int page) {
        page--;
        if (page <= 0) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, Contant.LIMIT_CATEGORY , Sort.by("createdDate").descending());
        return categoryRepository.findByNameContaining( name,  pageable);
    }
}
