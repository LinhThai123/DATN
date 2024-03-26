package fithou.edu.vn.DoAnTotNghiep.promotion.entity;

import fithou.edu.vn.DoAnTotNghiep.common.entity.BaseEntity;
import fithou.edu.vn.DoAnTotNghiep.order.entity.Order;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "PROMOTION")
public class Promotion extends BaseEntity implements Serializable {
    @GenericGenerator(name = "random_id", strategy = "fithou.edu.vn.DoAnTotNghiep.common.custom.RandomIdGenerator")
    @Id
    @GeneratedValue(generator = "random_id")
    private String id;

    @Column(name = "CODE", unique = true, nullable = false, length = 100)
    private String code;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "DESCRIPTION", length = 255)
    private String description;

    @Column(name = "DISCOUNT")
    private int discount;

    @Enumerated(EnumType.STRING)
    @Column(name = "PROMOTION_TYPE")
    private PromotionType promotionType;

    @Column(name = "MIN_ORDER_AMOUNT")
    private Integer minOrderAmount;

    @Column(name = "MAX_VALUE", nullable = true)
    private Integer maxValue;

    @Column(name = "START_DATE", nullable = false)
    private Date startDate;

    @Column(name = "END_DATE", nullable = false)
    private Date endDate;

    @Column(name = "ACTIVE", nullable = false)
    private boolean active;

    @Column(nullable = false)
    private int stock;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "promotion")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private java.util.List<Order> orders;

    public int getFinalDiscount(int totalPrice) {
        if (promotionType == PromotionType.PERCENTAGE) {
            int reduce = totalPrice * discount / 100;
            if (reduce > maxValue) {
                return maxValue;
            } else {
                return reduce;
            }

        } else {
            if (discount > totalPrice) {
                return totalPrice;
            } else {
                return discount;
            }
        }
    }

}
