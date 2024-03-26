package fithou.edu.vn.DoAnTotNghiep.promotion.service;

import fithou.edu.vn.DoAnTotNghiep.promotion.entity.Promotion;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface PromotionService {
    public Page<Promotion> adminGetListPromotion(String code, String name, String active, int page);

    public Promotion getPromotonByCode(String code) throws NotFoundException;
}
