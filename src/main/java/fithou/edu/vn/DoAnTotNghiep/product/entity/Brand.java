package fithou.edu.vn.DoAnTotNghiep.product.entity;

import fithou.edu.vn.DoAnTotNghiep.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@SQLDelete(sql = "UPDATE brand SET deleted_date = NOW() WHERE id=?")
@Where(clause = "deleted_date is null")
@Table(name = "BRAND")
public class Brand extends BaseEntity implements Serializable {

    @GenericGenerator(name = "random_id", strategy = "fithou.edu.vn.DoAnTotNghiep.common.custom.RandomIdGenerator")
    @Id
    @GeneratedValue(generator = "random_id")
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted_date")
    private Timestamp deletedDate = null;

}
