package fithou.edu.vn.DoAnTotNghiep.promotion.commands.deletePromotion;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.promotion.repository.PromotionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeletePromotionCommandHandler implements IRequestHandler<DeletePromotionCommand, String> {
    @Autowired
    private PromotionRepository promotionRepository;
    @Override
    public HandleResponse<String> handle(DeletePromotionCommand command) throws Exception {
        var promotion = promotionRepository.findById(command.getId());
        if (promotion.isEmpty()) {
            return HandleResponse.error("Không tìm thấy mã giảm giá");
        }
        if (promotion.get().getOrders().isEmpty()) {
            promotionRepository.delete(promotion.get());
            return HandleResponse.ok();
        }
        return HandleResponse.error("Không thể xóa mã giảm giá đã được sử dụng");
    }
}
