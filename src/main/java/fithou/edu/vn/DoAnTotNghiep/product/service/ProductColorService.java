package fithou.edu.vn.DoAnTotNghiep.product.service;

import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductColor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductColorService {
    Page<ProductColor> adminGetListProductColor(String name, int page);

    List<ProductColor> getListProductColor();
}
