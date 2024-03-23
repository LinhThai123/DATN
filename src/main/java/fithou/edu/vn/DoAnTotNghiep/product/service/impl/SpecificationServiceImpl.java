package fithou.edu.vn.DoAnTotNghiep.product.service.impl;

import fithou.edu.vn.DoAnTotNghiep.product.entity.Product;
import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductOption;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Specification;
import fithou.edu.vn.DoAnTotNghiep.product.repository.SpecificationRepository;
import fithou.edu.vn.DoAnTotNghiep.product.service.SpecificationService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private SpecificationRepository specificationRepository;
    @Override
    public List<Specification> getSpecificationByProductId(String productId) {
        return specificationRepository.findByProductId(productId);
    }

    @Override
    public Specification getSpecificationById(String id) throws NotFoundException {
        Optional<Specification> rs = specificationRepository.findById(id);
        if (!rs.isPresent()) {
            throw new NotFoundException("Không tìm thấy sản phẩm");
        }
        return rs.get();
    }
}
