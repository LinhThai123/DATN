package fithou.edu.vn.DoAnTotNghiep.product.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import fithou.edu.vn.DoAnTotNghiep.common.entity.BaseEntity;
import fithou.edu.vn.DoAnTotNghiep.receipt.entity.ReceiptItem;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@SQLDelete(sql = "UPDATE product_option SET deleted_date = NOW() WHERE  product_option_id=?")
@Where(clause = "deleted_date is null")
@Table(name = "PRODUCT_OPTION")
public class ProductOption extends BaseEntity implements Serializable {

    @GenericGenerator(name = "random_id", strategy = "fithou.edu.vn.DoAnTotNghiep.common.custom.RandomIdGenerator")
    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue(generator = "random_id")
    private String id;

    private int quantity;

    private int stock = 0 ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted_date")
    private LocalDateTime deletedDate = null;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productOption")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<ReceiptItem> receiptItems;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Product product;

    @ManyToOne
    @JoinColumn(name = "product_color_id")
    @JsonBackReference
    private ProductColor product_color;

    @ManyToOne
    @JoinColumn(name = "capacity_id")
    @JsonBackReference
    private Capacity capacity;

}
