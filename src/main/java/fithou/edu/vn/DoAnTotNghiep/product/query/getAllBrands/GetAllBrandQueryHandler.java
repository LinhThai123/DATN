package fithou.edu.vn.DoAnTotNghiep.product.query.getAllBrands;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.product.dto.BrandDto;
import fithou.edu.vn.DoAnTotNghiep.product.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
@AllArgsConstructor
public class GetAllBrandQueryHandler implements IRequestHandler<GetAllBrandQuery, Collection<BrandDto>> {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public HandleResponse<Collection<BrandDto>> handle(GetAllBrandQuery getAllBrandQuery) throws Exception {
        var brands = brandRepository.findAll();
        var brandDto = brands.stream().map(color -> modelMapper.map(color, BrandDto.class)).toList();
        return HandleResponse.ok(brandDto);
    }
}
