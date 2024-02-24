package fithou.edu.vn.DoAnTotNghiep.supplier.service;

import fithou.edu.vn.DoAnTotNghiep.supplier.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface SupplierService {
    Page<Supplier> adminGetListSuppiler(String name, int page);
}
