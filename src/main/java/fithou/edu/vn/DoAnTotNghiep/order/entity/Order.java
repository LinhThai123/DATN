package fithou.edu.vn.DoAnTotNghiep.order.entity;

import fithou.edu.vn.DoAnTotNghiep.common.entity.BaseEntity;
import fithou.edu.vn.DoAnTotNghiep.payment.entity.Payment;
import fithou.edu.vn.DoAnTotNghiep.payment.entity.enums.PaymentMethod;
import fithou.edu.vn.DoAnTotNghiep.promotion.entity.Promotion;
import fithou.edu.vn.DoAnTotNghiep.user.entity.User;
import fithou.edu.vn.DoAnTotNghiep.order.entity.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table
public class Order extends BaseEntity implements Serializable {
    @GenericGenerator(name = "random_id", strategy = "fithou.edu.vn.DoAnTotNghiep.common.custom.RandomIdGenerator")
    @Id
    @GeneratedValue(generator = "random_id")
    private String id;

    @Column(name = "CUSTOMER_NAME", length = 100)
    private String customerName;

    @Column (name = "ADDRESS", length = 500)
    private String address;

    @Column(name = "PAYMENT_METHOD", nullable = false)
    private PaymentMethod paymentMethod;

    @Column(name = "NUMBER_PHONE", length = 12)
    private String numberPhone;

    @Column (name = "EMAIL", length = 100)
    private String email;

    @Column (name = "TOTAL_AMOUNT")
    private int totalAmount;

    @Column (name = "NOTE", length = 500)
    private String note ;

    @Column (name = "DELIVERY_FEE")
    private double deliveryFee;

    @Column (name = "CANCEL_REASON", length = 500)
    private String cancelReason;

    @Column()
    @Builder.Default
    private OrderStatus status = OrderStatus.PENDING;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Payment> payments;

    @ManyToOne(fetch = FetchType.LAZY)
    private Promotion promotion;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "completed_date")
    private java.time.LocalDateTime completedDate;
}
