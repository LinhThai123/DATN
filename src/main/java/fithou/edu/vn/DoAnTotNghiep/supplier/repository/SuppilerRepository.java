package fithou.edu.vn.DoAnTotNghiep.supplier.repository;

import fithou.edu.vn.DoAnTotNghiep.blog.entity.Blog;
import fithou.edu.vn.DoAnTotNghiep.supplier.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SuppilerRepository extends JpaRepository<Supplier, String> {
    Optional<Supplier> findByNameIgnoreCase (String name);

    Page<Supplier> findAllByNameContainingIgnoreCase(String name, org.springframework.data.domain.Pageable pageable);
}
