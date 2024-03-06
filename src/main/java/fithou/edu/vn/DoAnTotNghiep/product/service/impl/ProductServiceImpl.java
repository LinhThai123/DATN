package fithou.edu.vn.DoAnTotNghiep.product.service.impl;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.dto.PaginatedDto;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Product;
import fithou.edu.vn.DoAnTotNghiep.product.repository.ProductRepository;
import fithou.edu.vn.DoAnTotNghiep.product.service.ProductService;
import fithou.edu.vn.DoAnTotNghiep.util.PageUtil;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public PaginatedDto adminGetListProduct(String id, String name, String category, String brand, String order, String direction, int page) {
        int limit = 15;
        PageUtil pageInfo  = new PageUtil(limit, page);

        List<Product> products = productRepository.adminGetListProduct(id, name, category, brand, order, direction, limit , pageInfo.calculateOffset());
        int totalItems = productRepository.countAdminGetListProduct(id, name, category, brand);

        int totalPages = pageInfo.calculateTotalPage(totalItems);

        return new PaginatedDto(products, totalPages, pageInfo.getPage());
    }

    @Override
    public Product getProductBySlug(String slug) throws NotFoundException {
        Optional<Product> rs = productRepository.findBySlug(slug);
        if (!rs.isPresent()) {
            throw new NotFoundException("Không tìm thấy sản phẩm");
        }
        return rs.get();
    }

    @Override
    public Product getProductById(String id) throws NotFoundException {
        Optional<Product> rs = productRepository.findById(id);
        if (!rs.isPresent()) {
            throw new NotFoundException("Không tìm thấy sản phẩm");
        }
        return rs.get();
    }

    @Override
    public List<Product> getListProduct() {
        return productRepository.findAll();
    }
}
