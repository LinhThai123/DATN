package fithou.edu.vn.DoAnTotNghiep.product.repository;

import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductOption;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SpecificationRepository extends JpaRepository<Specification , String> {

    List<Specification> findByProductId(String productId);

    @Modifying
    @Transactional
    @Query("delete from Specification po where po.product.id = :productId")
    void deleteByProductId(String productId);
}
