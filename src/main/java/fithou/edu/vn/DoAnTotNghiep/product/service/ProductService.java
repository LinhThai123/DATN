package fithou.edu.vn.DoAnTotNghiep.product.service;

import fithou.edu.vn.DoAnTotNghiep.common.dto.PaginatedDto;
import fithou.edu.vn.DoAnTotNghiep.product.dto.ProductDTO;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Brand;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Product;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public PaginatedDto adminGetListProduct(String id, String name, String category, String brand, String order, String direction, int page);

    public Product getProductBySlug (String slug) throws NotFoundException;

    public Product getProductById (String id) throws NotFoundException;

    public List<Product> getListProduct() ;

    public List<Product> getListProductByCategoryId (String id);

    public List<ProductDTO> getProductsWithProductOptionIds();

    List<Product> findByCategoryId(String id);
}


