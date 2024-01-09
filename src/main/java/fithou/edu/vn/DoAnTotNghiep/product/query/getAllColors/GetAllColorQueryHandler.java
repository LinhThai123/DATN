package fithou.edu.vn.DoAnTotNghiep.product.query.getAllColors;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.product.dto.BrandDto;
import fithou.edu.vn.DoAnTotNghiep.product.dto.ColorDto;
import fithou.edu.vn.DoAnTotNghiep.product.repository.ColorRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class GetAllColorQueryHandler implements IRequestHandler<GetAllColorQuery, Collection<ColorDto>> {

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public HandleResponse<Collection<ColorDto>> handle(GetAllColorQuery getAllColorQuery) throws Exception {
        var colors = colorRepository.findAll();
        var colorDto = colors.stream().map(color -> modelMapper.map(color, ColorDto.class)).toList();
        return HandleResponse.ok(colorDto);
    }
}
