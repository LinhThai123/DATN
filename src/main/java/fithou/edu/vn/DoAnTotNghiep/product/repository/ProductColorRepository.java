package fithou.edu.vn.DoAnTotNghiep.product.repository;

import fithou.edu.vn.DoAnTotNghiep.category.entity.Category;
import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductColor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ProductColorRepository extends JpaRepository<ProductColor, String> {
    Optional<ProductColor> findByName (String name);

    @Modifying
    @Transactional
    @Query(value = "UPDATE product_color SET deleted_date = NOW() WHERE id=?", nativeQuery = true)
    void hardDeleteById(String id);

    Page<ProductColor> findAllByNameContainingIgnoreCase(String name, org.springframework.data.domain.Pageable pageable);
}
