package fithou.edu.vn.DoAnTotNghiep.supplier.repository;

import fithou.edu.vn.DoAnTotNghiep.supplier.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SuppilerRepository extends JpaRepository<Supplier, String> {
    Optional<Supplier> findByNameIgnoreCase (String name);
}
