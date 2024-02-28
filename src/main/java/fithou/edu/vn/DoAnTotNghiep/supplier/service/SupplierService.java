package fithou.edu.vn.DoAnTotNghiep.supplier.service;

import fithou.edu.vn.DoAnTotNghiep.blog.entity.Blog;
import fithou.edu.vn.DoAnTotNghiep.supplier.entity.Supplier;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface SupplierService {
    Page<Supplier> adminGetListSuppiler(String name, int page);

    public Supplier getSupplierById (String id) throws NotFoundException;

}
