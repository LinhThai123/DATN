package fithou.edu.vn.DoAnTotNghiep.product.service.impl;

import fithou.edu.vn.DoAnTotNghiep.config.Contant;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Capacity;
import fithou.edu.vn.DoAnTotNghiep.product.repository.CapacityRepository;
import fithou.edu.vn.DoAnTotNghiep.product.service.CapacityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CapacityServiceImpl implements CapacityService {

    @Autowired
    private CapacityRepository capacityRepository;
    @Override
    public Page<Capacity> adminGetListProductColor(String name, int page) {
        page--;
        if (page <= 0) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, Contant.LIMIT_CATEGORY , Sort.by("createdDate").descending());
        return capacityRepository.findAllByNameContainingIgnoreCase( name,  pageable);
    }

    @Override
    public List<Capacity> getListCapacity() {
        return capacityRepository.findAll();
    }
}
