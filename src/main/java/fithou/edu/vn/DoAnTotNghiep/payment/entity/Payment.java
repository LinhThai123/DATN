package fithou.edu.vn.DoAnTotNghiep.payment.entity;

import fithou.edu.vn.DoAnTotNghiep.common.entity.BaseEntity;
import fithou.edu.vn.DoAnTotNghiep.order.entity.Order;
import fithou.edu.vn.DoAnTotNghiep.payment.entity.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Collate;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table (name = "PAYMETN")
public class Payment extends BaseEntity implements Serializable {
    @GenericGenerator(name = "random_id", strategy = "fithou.edu.vn.DoAnTotNghiep.common.custom.RandomIdGenerator")
    @Id
    @GeneratedValue(generator = "random_id")
    private String id;
    
    @Column( name = "STATUS", nullable = false)
    @Builder.Default
    private PaymentStatus status = PaymentStatus.PENDING;

    @Column(name = "AMOUNT")
    private int amount;

    @Column(name = "COMPLETED_DATE")
    private LocalDateTime completedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;
}
