package fithou.edu.vn.DoAnTotNghiep.product.service;

import fithou.edu.vn.DoAnTotNghiep.category.entity.Category;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrandService {

    List<Brand> getListBrand() ;
    Page<Brand> adminGetListBrand(String name, int page);
}
