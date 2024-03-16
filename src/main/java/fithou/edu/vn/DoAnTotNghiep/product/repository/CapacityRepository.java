package fithou.edu.vn.DoAnTotNghiep.product.repository;

import fithou.edu.vn.DoAnTotNghiep.product.entity.Capacity;
import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductColor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CapacityRepository extends JpaRepository<Capacity, String> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE capacity SET deleted_date = NOW() WHERE id=?", nativeQuery = true)
    void hardDeleteById(String id);
    Optional<Capacity> findByName (String id);

    Page<Capacity> findAllByNameContainingIgnoreCase(String name, org.springframework.data.domain.Pageable pageable);
}
