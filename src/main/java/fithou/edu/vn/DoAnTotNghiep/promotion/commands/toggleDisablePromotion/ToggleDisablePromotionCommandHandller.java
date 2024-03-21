package fithou.edu.vn.DoAnTotNghiep.promotion.commands.toggleDisablePromotion;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.promotion.repository.PromotionRepository;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ToggleDisablePromotionCommandHandller implements IRequestHandler<ToggleDisablePromotion, String> {

    @Autowired
    private PromotionRepository promotionRepository;
    @Override
    public HandleResponse<String> handle(ToggleDisablePromotion command) throws Exception {
        var promotion = promotionRepository.findById(command.getId());
        if (promotion.isEmpty()) {
            return HandleResponse.error("Không tìm thấy mã giảm giá");
        }
        promotion.get().setActive(!promotion.get().isActive());
        return HandleResponse.ok();
    }
}
