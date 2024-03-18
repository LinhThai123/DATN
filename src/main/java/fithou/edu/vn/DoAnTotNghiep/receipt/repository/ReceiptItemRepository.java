package fithou.edu.vn.DoAnTotNghiep.receipt.repository;

import fithou.edu.vn.DoAnTotNghiep.receipt.entity.ReceiptItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptItemRepository extends JpaRepository<ReceiptItem , String> {
    boolean existsByProductOptionId(String productOptionId);
}
