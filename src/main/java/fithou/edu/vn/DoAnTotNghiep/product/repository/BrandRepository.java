package fithou.edu.vn.DoAnTotNghiep.product.repository;

import fithou.edu.vn.DoAnTotNghiep.product.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, String> {
    Optional<Brand> findByNameIgnoreCase (String name) ;
}
