package fithou.edu.vn.DoAnTotNghiep.supplier.service.impl;

import fithou.edu.vn.DoAnTotNghiep.blog.entity.Blog;
import fithou.edu.vn.DoAnTotNghiep.config.Contant;
import fithou.edu.vn.DoAnTotNghiep.supplier.entity.Supplier;
import fithou.edu.vn.DoAnTotNghiep.supplier.repository.SuppilerRepository;
import fithou.edu.vn.DoAnTotNghiep.supplier.service.SupplierService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SupplierServiceimpl implements SupplierService {
    @Autowired
    private SuppilerRepository suppilerRepository;
    @Override
    public Page<Supplier> adminGetListSuppiler(String name, int page) {
        page--;
        if (page <= 0) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, Contant.LIMIT_SUPPILER , Sort.by("createdDate").descending());
        return suppilerRepository.findAllByNameContainingIgnoreCase( name,  pageable);
    }

    @Override
    public Supplier getSupplierById(String id) throws NotFoundException {
        Optional<Supplier> rs = suppilerRepository.findById(id);
        if (!rs.isPresent()) {
            throw new NotFoundException("Không tìm thấy nhà cung cấp");
        }
        return rs.get();
    }
}
