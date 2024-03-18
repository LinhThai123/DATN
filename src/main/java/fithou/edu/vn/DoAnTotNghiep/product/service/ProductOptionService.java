package fithou.edu.vn.DoAnTotNghiep.product.service;

import fithou.edu.vn.DoAnTotNghiep.product.entity.Capacity;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Product;
import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductColor;
import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductOption;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductOptionService {
    public ProductOption getProductOptionById (String id) throws NotFoundException;

    public List<ProductOption> getProductOptionsByProductId(String productId);

    List<ProductColor> getProductColors(String productId);

    List<Capacity> getCapacity(String productId);
    public List<ProductOption> getListProductOption() ;

}
