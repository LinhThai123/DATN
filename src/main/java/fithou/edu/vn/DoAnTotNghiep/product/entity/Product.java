package fithou.edu.vn.DoAnTotNghiep.product.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import fithou.edu.vn.DoAnTotNghiep.category.entity.Category;
import fithou.edu.vn.DoAnTotNghiep.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@SQLDelete(sql = "UPDATE product SET deleted_date = NOW() WHERE product_id=?")
@Where(clause = "deleted_date is null")
@Table(name = "PRODUCT")
public class Product extends BaseEntity implements Serializable {
    @GenericGenerator(name = "random_id", strategy = "fithou.edu.vn.DoAnTotNghiep.common.custom.RandomIdGenerator")
    @Id
    @GeneratedValue(generator = "random_id")
    private String id;


    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    @JsonBackReference
    private Category category;

}
