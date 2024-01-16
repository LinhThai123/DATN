package fithou.edu.vn.DoAnTotNghiep.receipt.entity;

import fithou.edu.vn.DoAnTotNghiep.common.entity.BaseEntity;
import fithou.edu.vn.DoAnTotNghiep.supplier.entity.Supplier;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@SQLDelete(sql = "UPDATE category SET deleted_date = NOW() WHERE id=?")
@Where(clause = "deleted_date is null")
@Table(name = "RECEIPT")
public class Receipt extends BaseEntity implements Serializable {

    @GenericGenerator(name = "random_id", strategy = "fithou.edu.vn.DoAnTotNghiep.common.custom.RandomIdGenerator")
    @Id
    @GeneratedValue(generator = "random_id")
    private String id;

    @Column(name = "TOTAL" , nullable = false)
    private Integer total = 0;

    @Column(name = "NOTE", length = 255)
    private String note;

    @ManyToOne(fetch = FetchType.EAGER)
    private Supplier supplier;



}
