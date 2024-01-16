package fithou.edu.vn.DoAnTotNghiep.product.repository;

import fithou.edu.vn.DoAnTotNghiep.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Optional<Product> findBySlug(String slug);

    @Modifying
    @Query(value = "update product p set p.deleted_date = null where p.id = ?1", nativeQuery = true)
    void recoveryByProductId(String Id);
}
