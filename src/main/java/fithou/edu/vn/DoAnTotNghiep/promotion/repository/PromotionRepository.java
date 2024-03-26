package fithou.edu.vn.DoAnTotNghiep.promotion.repository;

import fithou.edu.vn.DoAnTotNghiep.promotion.entity.Promotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, String> {
    Optional<Promotion> findByCodeContainingIgnoreCase (String code);

    @Query(nativeQuery = true, value = "SELECT * FROM promotion " +
            "WHERE code LIKE CONCAT('%',:code,'%') AND name LIKE CONCAT('%',:name,'%') " +
            "AND active LIKE CONCAT('%',:active,'%')")
    public Page<Promotion> adminGetListPromotion(@Param("code") String code, @Param("name") String name, @Param("active") String active, Pageable pageable);

}
