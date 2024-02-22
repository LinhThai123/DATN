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
import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "PRODUCT")
public class Product extends BaseEntity implements Serializable {
    @GenericGenerator(name = "random_id", strategy = "fithou.edu.vn.DoAnTotNghiep.common.custom.RandomIdGenerator")
    @Id
    @GeneratedValue(generator = "random_id")
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SLUG")
    private String slug;

    @Column(name = "MA_SERIAL" , unique = true)
    private String maSerial;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "DISCOUNT")
    private int discount;

    @Column(name = "PRICE")
    private int price;

//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "deleted_date")
//    private Timestamp deletedDate = null;

    private int totalSold = 0 ;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    @JsonBackReference
    private Category category;

    @ManyToOne
    @JoinColumn(name = "BRAND_ID", nullable = false)
    @JsonBackReference
    private Brand brand;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<ProductOption> productOptions;

}
