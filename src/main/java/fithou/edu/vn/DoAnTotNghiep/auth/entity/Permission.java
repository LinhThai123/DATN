package fithou.edu.vn.DoAnTotNghiep.auth.entity;

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
@Table (name = "PERMISSION")
public class Permission implements Serializable {

    @GenericGenerator(name = "random_id", strategy = "fithou.edu.vn.DoAnTotNghiep.common.custom.RandomIdGenerator")
    @Id
    @GeneratedValue(generator = "random_id")
    private String id;

    @Column(name = "NORMALIZED_NAME", length = 50, nullable = false, unique = true)
    private String normalizedName;

    @Column(name = "DISPLAY_NAME", length = 50, nullable = false)
    private String displayName;

    @Column(name = "DESCRIPTION", length = 100)
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "role_permissions",
            joinColumns = @JoinColumn(name = "permission_name", referencedColumnName = "NORMALIZED_NAME"),
            inverseJoinColumns = @JoinColumn(name = "role_name", referencedColumnName = "NORMALIZED_NAME")
    )
    private java.util.List<Role> roles;
}
