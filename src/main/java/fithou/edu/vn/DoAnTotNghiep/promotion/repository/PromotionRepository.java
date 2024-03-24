package fithou.edu.vn.DoAnTotNghiep.promotion.repository;

import fithou.edu.vn.DoAnTotNghiep.promotion.entity.Promotion;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, String> {
    Optional<Promotion> findByCodeContainingIgnoreCase (String code);

}
