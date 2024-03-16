package fithou.edu.vn.DoAnTotNghiep.product.service;

import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductOption;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SpecificationService {
    public List<Specification> getSpecificationByProductId(String productId);
}
