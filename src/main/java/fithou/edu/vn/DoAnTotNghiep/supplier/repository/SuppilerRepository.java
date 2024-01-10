package fithou.edu.vn.DoAnTotNghiep.supplier.repository;

import fithou.edu.vn.DoAnTotNghiep.supplier.entity.Suppiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SuppilerRepository extends JpaRepository<Suppiler, String> {
    Optional<Suppiler> findByNameIgnoreCase (String name);
}
