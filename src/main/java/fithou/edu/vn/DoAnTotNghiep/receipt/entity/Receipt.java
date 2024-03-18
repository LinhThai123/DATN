package fithou.edu.vn.DoAnTotNghiep.receipt.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import fithou.edu.vn.DoAnTotNghiep.common.entity.BaseEntity;
import fithou.edu.vn.DoAnTotNghiep.supplier.entity.Supplier;
import fithou.edu.vn.DoAnTotNghiep.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@SQLDelete(sql = "UPDATE receipt SET deleted_date = NOW() WHERE id=?")
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DELETED_DATE")
    private Timestamp deleteDate = null;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Supplier supplier;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private User employee;

    @OneToMany(mappedBy = "receipt")
    @JsonIgnore
    private List<ReceiptItem> receiptItems;

}
