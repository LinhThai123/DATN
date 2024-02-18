package fithou.edu.vn.DoAnTotNghiep.product.service.impl;

import fithou.edu.vn.DoAnTotNghiep.config.Contant;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Color;
import fithou.edu.vn.DoAnTotNghiep.product.repository.ColorRepository;
import fithou.edu.vn.DoAnTotNghiep.product.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ColorServiceImpl implements ColorService {

    @Autowired
    private ColorRepository colorRepository;
    @Override
    public List<Color> getListColor() {
        return colorRepository.findAll();
    }

    @Override
    public Page<Color> adminGetListColor(String name, int page) {
        page--;
        if (page <= 0) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, Contant.LIMIT_COLOR , Sort.by("createdDate").descending());
        return colorRepository.findByNameContaining( name,  pageable);
    }
}
