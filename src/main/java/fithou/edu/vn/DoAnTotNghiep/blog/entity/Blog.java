package fithou.edu.vn.DoAnTotNghiep.blog.entity;

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
@Entity
@Builder
@SQLDelete(sql = "UPDATE blog SET deleted_date = NOW() WHERE id=?")
@Where(clause = "deleted_date is null")
@Table(name = "BLOG")
public class Blog extends BaseEntity implements Serializable {
    @GenericGenerator(name = "random_id", strategy = "fithou.edu.vn.DoAnTotNghiep.common.custom.RandomIdGenerator")
    @Id
    @GeneratedValue(generator = "random_id")
    private String id;

    @Column(name = "TITLE", nullable = false, length = 300)
    private String title;

    @Column(name = "SLUG")
    private String slug;

    @Column(name = "DESCRIPTION" , columnDefinition = "TEXT")
    private String description;

    @Column(name = "CONTENT" , columnDefinition = "TEXT")
    private String content;

    @Column(name = "STATUS", columnDefinition = "int default 0")
    private Integer status;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @Column(name = "VIEW", columnDefinition = "int default 0")
    private Integer view;

    @Column(name = "TOTAL", columnDefinition = "int default 0")
    private Integer total;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name ="PUBLIC DATE")
    private Timestamp publibDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DELETED_DATE")
    private Timestamp deleteDate = null;

}
