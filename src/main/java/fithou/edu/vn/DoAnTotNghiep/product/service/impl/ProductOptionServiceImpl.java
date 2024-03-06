package fithou.edu.vn.DoAnTotNghiep.product.service.impl;

import fithou.edu.vn.DoAnTotNghiep.product.entity.Product;
import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductOption;
import fithou.edu.vn.DoAnTotNghiep.product.repository.ProductOptionRepository;
import fithou.edu.vn.DoAnTotNghiep.product.service.ProductOptionService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductOptionServiceImpl implements ProductOptionService {
    @Autowired
    private ProductOptionRepository productOptionRepository;
    @Override
    public ProductOption getProductOptionById(String id) throws NotFoundException {
        Optional<ProductOption> rs = productOptionRepository.findById(id);
        if (!rs.isPresent()) {
            throw new NotFoundException("Không tìm thấy sản phẩm");
        }
        return rs.get();
    }

    public List<ProductOption> getProductOptionsByProductId(String productId) {
        return productOptionRepository.findByProductId(productId);
    }
}
