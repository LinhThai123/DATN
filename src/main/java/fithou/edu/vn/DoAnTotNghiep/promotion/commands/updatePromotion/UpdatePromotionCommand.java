package fithou.edu.vn.DoAnTotNghiep.promotion.commands.updatePromotion;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import fithou.edu.vn.DoAnTotNghiep.promotion.entity.PromotionType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePromotionCommand implements IRequest<String> {

    @NotEmpty(message = "Mã khuyến mãi không hợp lệ")
    private String promotionId;

    @NotEmpty(message = "Mã khuyến mãi không được để trống")
    @Length(max = 20, min = 4, message = "Mã khuyến mãi không được quá 20 ký tự và ít nhất 4 ký tự")
    private String code;

    @NotEmpty(message = "Tên khuyến mãi không được để trống")
    private String name;

    private String description;

    @Min(value = 0, message = "Giá trị giảm giá không hợp lệ")
    private int discount;

    @NotNull(message = "Loại khuyến mãi không được để trống")
    private PromotionType type;

    @Min(value = 0, message = "Giá trị đơn hàng tối thiểu không hợp lệ")
    private int minOrderAmount;

    private Integer maxValue;

    @NotNull(message = "Ngày bắt đầu không được để trống")
    private Date startDate;

    @NotNull(message = "Ngày kết thúc không được để trống")
    private Date endDate;

    private boolean active = true;

    private int stock = 0;
}
