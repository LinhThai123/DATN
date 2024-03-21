package fithou.edu.vn.DoAnTotNghiep.product.service.impl;

import fithou.edu.vn.DoAnTotNghiep.product.entity.Capacity;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Product;
import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductColor;
import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductOption;
import fithou.edu.vn.DoAnTotNghiep.product.repository.ProductOptionRepository;
import fithou.edu.vn.DoAnTotNghiep.product.service.ProductOptionService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public Product getProductByProductOptionId(String productOptionId) throws NotFoundException {
        ProductOption productOption = productOptionRepository.findById(productOptionId).orElseThrow(() -> new NotFoundException("ProductOption not found with id: " + productOptionId));
        return productOption.getProduct();
    }

    @Override
    public Set<ProductColor> getProductColorByProductOptionId(String productOptionId) throws NotFoundException {
        ProductOption productOption = productOptionRepository.findById(productOptionId).orElseThrow(() -> new NotFoundException("Không tìm thấy phân loại của sản phẩm qua id : " + productOptionId));
        ProductColor productColor = productOption.getProduct_color();
        return productColor != null ? Collections.singleton(productColor) : Collections.emptySet();
    }

    @Override
    public Set<Capacity> getCapacityByProductOptionId(String productOptionId) throws NotFoundException {
        ProductOption productOption = productOptionRepository.findById(productOptionId).orElseThrow(() -> new NotFoundException("Không tìm thấy phân loại của sản phẩm qua id : " + productOptionId));
        Capacity capacity = productOption.getCapacity();
        return capacity != null ? Collections.singleton(capacity) : Collections.emptySet();
    }

    @Override
    public List<ProductColor> getProductColors(String productId) {
        List<ProductOption> productOptions = productOptionRepository.findByProductId(productId);
        // Lấy danh sách màu sắc từ danh sách ProductOption
        List<ProductColor> productColors = productOptions.stream()
                .map(ProductOption::getProduct_color)
                .collect(Collectors.toList());
        return productColors;
    }

    @Override
    public List<Capacity> getCapacity(String productId) {
        List<ProductOption> productOptions = productOptionRepository.findByProductId(productId);
        // Lấy danh sách dung lượng từ danh sách ProductOption
        List<Capacity> capacities = productOptions.stream()
                .map(ProductOption::getCapacity)
                .collect(Collectors.toList());
        return capacities;
    }

    @Override
    public List<ProductOption> getListProductOption() {
        return productOptionRepository.findAll();
    }

}
