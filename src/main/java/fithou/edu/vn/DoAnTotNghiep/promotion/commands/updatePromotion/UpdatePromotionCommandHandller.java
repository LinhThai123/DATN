package fithou.edu.vn.DoAnTotNghiep.promotion.commands.updatePromotion;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.promotion.repository.PromotionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;

@Service
@AllArgsConstructor
public class UpdatePromotionCommandHandller implements IRequestHandler<UpdatePromotionCommand,String> {

    @Autowired
    private PromotionRepository promotionRepository;
    @Override
    @Transactional
    public HandleResponse<String> handle(UpdatePromotionCommand command) throws Exception {
        var exitsPromotion = promotionRepository.findByCodeContainingIgnoreCase(command.getCode());
        if (!exitsPromotion.isPresent() && exitsPromotion.get().getId() != command.getPromotionId()) {
            return HandleResponse.error("Code " + command.getCode() + " không tồn tại");
        }
        var promotion = exitsPromotion.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Không tìm thấy mã giảm giá"));
        promotion.setActive(command.isActive());
        promotion.setCode(command.getCode());
        promotion.setEndDate(command.getEndDate());
        promotion.setDescription(command.getDescription());
        promotion.setName(command.getName());
        promotion.setStock(command.getStock());
        promotion.setStartDate(command.getStartDate());
        promotion.setDiscount(command.getDiscount());
        promotion.setMaxValue(command.getMaxValue());
        promotion.setMinOrderAmount(command.getMinOrderAmount());
        promotion.setPromotionType(command.getType());
        promotion.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        promotionRepository.save(promotion);
        return HandleResponse.ok();
    }
}
