package fithou.edu.vn.DoAnTotNghiep.category.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fithou.edu.vn.DoAnTotNghiep.common.entity.BaseEntity;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Product;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jdk.jfr.Enabled;
import lombok.*;
import org.hibernate.annotations.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@SQLDelete(sql = "UPDATE category SET deleted_date = NOW() WHERE id=?")
@Where(clause = "deleted_date is null")
@Table(name = "CATEGORY")
public class Category extends BaseEntity implements Serializable {
    @GenericGenerator(name = "random_id", strategy = "fithou.edu.vn.DoAnTotNghiep.common.custom.RandomIdGenerator")
    @Id
    @GeneratedValue(generator = "random_id")
    private String id;

    @Column(name = "NAME", unique = true, nullable = false, length = 100)
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DELETED_DATE")
    private Timestamp deleteDate = null;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<Product> products;

}
