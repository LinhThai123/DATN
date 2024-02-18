package fithou.edu.vn.DoAnTotNghiep.product.entity;

import fithou.edu.vn.DoAnTotNghiep.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@SQLDelete(sql = "UPDATE color SET deleted_date = NOW() WHERE id=?")
@Where(clause = "deleted_date is null")
@Table(name = "COLOR")
public class Color extends BaseEntity implements Serializable {
    @GenericGenerator(name = "random_id", strategy = "fithou.edu.vn.DoAnTotNghiep.common.custom.RandomIdGenerator")
    @Id
    @GeneratedValue(generator = "random_id")
    private String id;

    @Column(name = "COLOR_NAME")
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DELETED_DATE")
    private Timestamp deleteDate = null;

    @OneToMany(mappedBy = "color", fetch = FetchType.LAZY)
    private List<ProductOption> productOptions;
}
