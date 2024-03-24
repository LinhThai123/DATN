package fithou.edu.vn.DoAnTotNghiep.cart.entity;

import fithou.edu.vn.DoAnTotNghiep.common.entity.BaseEntity;
import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductOption;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table()
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@IdClass(CartItem.CartItemKey.class)
public class CartItem {
    @AllArgsConstructor
    @NoArgsConstructor

    public static class CartItemKey implements java.io.Serializable{
        private String userId;
        private String productOptionId;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CartItemKey that = (CartItemKey) o;
            return productOptionId == that.productOptionId && Objects.equals(userId, that.userId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(userId, productOptionId);
        }
    }

    @Id
    @Column(updatable = false, nullable = false, name = "user_id")
    private String userId;

    @Id
    @Column(updatable = false, nullable = false, name = "product_option_id")
    private String productOptionId;

    private int quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_option_id", insertable = false, updatable = false)
    private ProductOption productOption;

}
