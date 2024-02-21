package fithou.edu.vn.DoAnTotNghiep.order.entity;

import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductOption;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Objects;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "ORDER_ITEM")
@IdClass(OrderItem.OrderItemKey.class)
public class OrderItem {
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderItemKey implements Serializable {

        private String orderId;

        private String productOptionId;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            OrderItemKey that = (OrderItemKey) o;
            return productOptionId == that.productOptionId && Objects.equals(orderId, that.orderId);
        }
        @Override
        public int hashCode() {
            return Objects.hash(orderId, productOptionId);
        }
    }

    @GenericGenerator(name = "random_id", strategy = "fithou.edu.vn.DoAnTotNghiep.common.custom.RandomIdGenerator")
    @Id
    @GeneratedValue(generator = "random_id")
    @Column(updatable = false, nullable = false,name = "order_id")
    private String orderId;

    @GenericGenerator(name = "random_id", strategy = "fithou.edu.vn.DoAnTotNghiep.common.custom.RandomIdGenerator")
    @Id
    @GeneratedValue(generator = "random_id")
    @Column(updatable = false, nullable = false,name = "product_option_id")
    private String productOptionId;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "PRICE")
    private int price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_option_id", insertable = false, updatable = false)
    private ProductOption productOption;

}
