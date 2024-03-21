package fithou.edu.vn.DoAnTotNghiep.promotion.dto;

import fithou.edu.vn.DoAnTotNghiep.promotion.entity.PromotionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PromotionDto {

    private int promotionId;

    private String code;

    private String name;

    private String description;

    private int discount;

    private PromotionType type;

    private int minOrderAmount;

    private int maxValue;

    private Date startDate;

    private Date endDate;

    private boolean active;

    private int stock;
}
