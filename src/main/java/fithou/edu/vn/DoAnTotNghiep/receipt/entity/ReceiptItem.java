package fithou.edu.vn.DoAnTotNghiep.receipt.entity;

import fithou.edu.vn.DoAnTotNghiep.common.entity.BaseEntity;
import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductOption;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "RECEIPT_ITEM")
public class ReceiptItem extends BaseEntity implements Serializable {

    public static class ReceiptItemKey implements Serializable {
        public String stockReceiptId;
        public String productOptionId;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ReceiptItemKey that = (ReceiptItemKey) o;
            return stockReceiptId.equals(that.stockReceiptId) && productOptionId.equals(that.productOptionId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(stockReceiptId, productOptionId);
        }
    }

    @GenericGenerator(name = "random_id", strategy = "fithou.edu.vn.DoAnTotNghiep.common.custom.RandomIdGenerator")
    @Id
    @GeneratedValue(generator = "random_id")
    @Column(updatable = false, nullable = false, name = "stock_receipt_id")
    private String stockReceiptId;

    @GenericGenerator(name = "random_id", strategy = "fithou.edu.vn.DoAnTotNghiep.common.custom.RandomIdGenerator")
    @Id
    @GeneratedValue(generator = "random_id")
    @Column(updatable = false, nullable = false, name = "product_option_id")
    private String productOptionId;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "PRICE")
    private int price = 0;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_option_id", insertable = false, updatable = false)
    private ProductOption productOption;
}
