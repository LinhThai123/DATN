package fithou.edu.vn.DoAnTotNghiep.product.service;

import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductOption;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductOptionService {
    public ProductOption getProductOptionById (String id) throws NotFoundException;

    public List<ProductOption> getProductOptionsByProductId(String productId);
}
