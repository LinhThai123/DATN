package fithou.edu.vn.DoAnTotNghiep.auth.entity;

import fithou.edu.vn.DoAnTotNghiep.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table (name = "ROLE")
public class Role extends BaseEntity implements Serializable {

    @GenericGenerator(name = "random_id", strategy = "fithou.edu.vn.DoAnTotNghiep.common.custom.RandomIdGenerator")
    @Id
    @GeneratedValue(generator = "random_id")
    private String id;

    @Column(name = "NORMALIZED_NAME", length = 50,nullable = false , unique = true)
    private String normalizedName;

    @Column(name = "DISPLAY_NAME", length = 50,nullable = false)
    private String displayName;

    @Column(name = "DESCRIPTION", length = 100)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER,cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.ALL })
    @JoinTable(
            name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_name", referencedColumnName = "NORMALIZED_NAME"),
            inverseJoinColumns = @JoinColumn(name = "permission_name", referencedColumnName = "NORMALIZED_NAME")
    )

    private java.util.List<Permission> permissions;
}
