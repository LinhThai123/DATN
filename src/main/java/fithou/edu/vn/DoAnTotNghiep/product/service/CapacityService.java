package fithou.edu.vn.DoAnTotNghiep.product.service;

import fithou.edu.vn.DoAnTotNghiep.product.entity.Capacity;
import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductColor;
import fithou.edu.vn.DoAnTotNghiep.supplier.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CapacityService {
    Page<Capacity> adminGetListProductColor(String name, int page);

    List<Capacity> getListCapacity();
}
