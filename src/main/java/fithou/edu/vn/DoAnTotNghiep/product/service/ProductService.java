package fithou.edu.vn.DoAnTotNghiep.product.service;

import fithou.edu.vn.DoAnTotNghiep.common.dto.PaginatedDto;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Product;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    public PaginatedDto adminGetListProduct(String id, String name, String category, String brand, String order, String direction, int page);

    public Product getProductBySlug (String slug) throws NotFoundException;
}


