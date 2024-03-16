package fithou.edu.vn.DoAnTotNghiep.product.service.impl;

import fithou.edu.vn.DoAnTotNghiep.config.Contant;
import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductColor;
import fithou.edu.vn.DoAnTotNghiep.product.repository.ProductColorRepository;
import fithou.edu.vn.DoAnTotNghiep.product.service.ProductColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductColorServiceImpl implements ProductColorService {

    @Autowired
    private ProductColorRepository productColorRepository;
    @Override
    public Page<ProductColor> adminGetListProductColor(String name, int page) {
        page--;
        if (page <= 0) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, Contant.LIMIT_CATEGORY , Sort.by("createdDate").descending());
        return productColorRepository.findAllByNameContainingIgnoreCase( name,  pageable);
    }

    @Override
    public List<ProductColor> getListProductColor() {
        return productColorRepository.findAll();
    }
}
