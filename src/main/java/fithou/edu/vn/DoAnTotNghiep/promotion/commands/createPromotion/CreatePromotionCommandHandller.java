package fithou.edu.vn.DoAnTotNghiep.promotion.commands.createPromotion;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.promotion.entity.Promotion;
import fithou.edu.vn.DoAnTotNghiep.promotion.repository.PromotionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
@AllArgsConstructor
public class CreatePromotionCommandHandller implements IRequestHandler<CreatePromotionCommand, String> {

    @Autowired
    private PromotionRepository promotionRepository;
    @Override
    @Transactional
    public HandleResponse<String> handle(CreatePromotionCommand command) throws Exception {
        var exitsPromotion = promotionRepository.findByCodeContainingIgnoreCase(command.getCode());
        if (exitsPromotion.isPresent()) {
            return HandleResponse.error("Code " + command.getCode() + "đã tồn tại");
        }
        Promotion promotion = new Promotion();
        promotion.setActive(command.isActive());
        promotion.setCode(command.getCode());
        promotion.setEndDate(command.getEndDate());
        promotion.setDescription(command.getDescription());
        promotion.setName(command.getName());
        promotion.setStock(command.getStock());
        promotion.setStartDate(command.getStartDate());
        promotion.setDiscount(command.getDiscount());
        promotion.setMinOrderAmount(command.getMinOrderAmount());
        promotion.setMaxValue(command.getMaxValue());
        promotion.setPromotionType(command.getType());
        promotion.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        promotionRepository.save(promotion);
        return HandleResponse.ok(promotion.getId());
    }
}
