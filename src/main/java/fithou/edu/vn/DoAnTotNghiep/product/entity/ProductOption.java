package fithou.edu.vn.DoAnTotNghiep.product.entity;

import fithou.edu.vn.DoAnTotNghiep.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.time.LocalDateTime;

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
    @Column(updatable = false, nullable = false, name = "product_option_id")
    @GeneratedValue(generator = "random_id")
    private String id;

    private String screen_size;

    private String resolution_screen;

    private String rom;

    private String cpu;

    private String size;

    private String camera_after;

    private String camera_before;

    private String pin;

    private String charging_port;

    private String os;

    private String bluetooth;

    private Double weight;

    private String chip;

    private String ram;

    private String color;

    private int stock = 0 ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted_date")
    private LocalDateTime deletedDate = null;

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

}
