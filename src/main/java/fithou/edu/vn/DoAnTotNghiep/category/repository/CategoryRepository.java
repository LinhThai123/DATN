package fithou.edu.vn.DoAnTotNghiep.category.repository;

import fithou.edu.vn.DoAnTotNghiep.category.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    Optional<Category> findByName (String name) ;

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM category WHERE id = ?1", nativeQuery = true)
    void hardDeleteById(String id);

    Page<Category> findByNameContaining (String name, Pageable pageable);

    Page<Category> findAllByNameContainingIgnoreCase(String name, org.springframework.data.domain.Pageable pageable);
}
