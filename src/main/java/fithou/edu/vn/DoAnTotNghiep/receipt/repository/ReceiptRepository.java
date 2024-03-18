package fithou.edu.vn.DoAnTotNghiep.receipt.repository;

import fithou.edu.vn.DoAnTotNghiep.receipt.entity.Receipt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt , String> {
    boolean existsBySupplierId (String id) ;

    @Query("SELECT r FROM Receipt r JOIN r.employee u JOIN r.supplier s WHERE (u.name LIKE %:employeeName%) OR (s.name LIKE %:supplierName%)")
    Page<Receipt> findByEmployeeNameAndSupplierName (@Param("employeeName") String employeeName, @Param("supplierName") String supplierName, Pageable pageable) ;
}
