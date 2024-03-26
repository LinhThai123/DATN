package fithou.edu.vn.DoAnTotNghiep.promotion.service.impl;

import fithou.edu.vn.DoAnTotNghiep.config.Contant;
import fithou.edu.vn.DoAnTotNghiep.promotion.entity.Promotion;
import fithou.edu.vn.DoAnTotNghiep.promotion.repository.PromotionRepository;
import fithou.edu.vn.DoAnTotNghiep.promotion.service.PromotionService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    @Override
    public Page<Promotion> adminGetListPromotion(String code, String name, String active, int page) {
        page--;
        if (page <= 0) {
            page = 0;
        }
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page, Contant.LIMIT_PROMOTION);
        return promotionRepository.adminGetListPromotion(code, name, active,  pageable);
    }

    @Override
    public Promotion getPromotonByCode(String code) throws NotFoundException {
        Optional<Promotion> promotion = promotionRepository.findByCodeContainingIgnoreCase(code);
        if (!promotion.isPresent()) {
            throw new NotFoundException("Không tìm thấy khuyến mãi");
        }
        return promotion.get();
    }
}
