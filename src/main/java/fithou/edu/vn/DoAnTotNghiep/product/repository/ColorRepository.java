package fithou.edu.vn.DoAnTotNghiep.product.repository;

import fithou.edu.vn.DoAnTotNghiep.product.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColorRepository extends JpaRepository<Color, String> {
    Optional<Color> findByNameIgnoreCase (String name) ;
}
