package fithou.edu.vn.DoAnTotNghiep.category.repository;

import fithou.edu.vn.DoAnTotNghiep.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    Optional<Category> findByName (String name) ;

    @Modifying
    @Query(value = "DELETE FROM category WHERE category_id = ?1", nativeQuery = true)
    void hardDeleteById(String id);
}
