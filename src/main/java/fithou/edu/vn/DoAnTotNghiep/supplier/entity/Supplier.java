package fithou.edu.vn.DoAnTotNghiep.supplier.entity;

import fithou.edu.vn.DoAnTotNghiep.common.entity.BaseEntity;
import fithou.edu.vn.DoAnTotNghiep.receipt.entity.Receipt;
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
@SQLDelete(sql = "UPDATE suppiler SET deleted_date = NOW() WHERE id=?")
@Where(clause = "deleted_date is null")
@Table(name = "SUPPILER")
public class Supplier extends BaseEntity implements Serializable {
    @GenericGenerator(name = "random_id", strategy = "fithou.edu.vn.DoAnTotNghiep.common.custom.RandomIdGenerator")
    @Id
    @GeneratedValue(generator = "random_id")
    private String id;

    @Column(name = "NAME" , length = 255)
    private String name;

    @Column(name = "EMAIL" , unique = true)
    private String email;

    @Column(name = "NUMBER_PHONE" , length = 12)
    private String numberPhone;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "BANK")
    private String bank;

    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DELETED_DATE")
    private Timestamp deleteDate = null;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "supplier")
    private List<Receipt> receips;
}
