package fithou.edu.vn.DoAnTotNghiep.product.repository;

import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductColor;
import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductOptionRepository extends JpaRepository<ProductOption, String> {

    @Modifying()
    @Query(value = "DELETE FROM  product_option  WHERE product_product_id = ?1", nativeQuery = true)
    void hardDeleteById(String id);

    List<ProductOption> findByProductId(String productId);

    @Modifying
    @Transactional
    @Query("delete from ProductOption po where po.product.id = :productId")
    void deleteProductOptionProductId(String productId);
}
