package fithou.edu.vn.DoAnTotNghiep.promotion.commands.deletePromotion;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeletePromotionCommand implements IRequest<String> {
    private String id;
}
