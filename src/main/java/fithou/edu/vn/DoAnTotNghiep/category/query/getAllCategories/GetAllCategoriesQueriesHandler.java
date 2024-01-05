package fithou.edu.vn.DoAnTotNghiep.category.query.getAllCategories;

import fithou.edu.vn.DoAnTotNghiep.category.dto.CategoryDto;
import fithou.edu.vn.DoAnTotNghiep.category.query.getAllCategories.GetAllCategoriesQueries;
import fithou.edu.vn.DoAnTotNghiep.category.repository.CategoryRepository;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.common.dto.Paginated;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class GetAllCategoriesQueriesHandler implements IRequestHandler<GetAllCategoriesQueries, Paginated<CategoryDto>> {

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public HandleResponse<Paginated<CategoryDto>> handle(GetAllCategoriesQueries query) {
        var categories = categoryRepository.findAllByNameContainingIgnoreCase(query.getKeyword(),
                query.getPageable("id"));
        return HandleResponse.ok(Paginated.of(categories.map(category -> modelMapper.map(category, CategoryDto.class))));
    }

}