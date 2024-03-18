package fithou.edu.vn.DoAnTotNghiep.user.entity;

import fithou.edu.vn.DoAnTotNghiep.auth.entity.Permission;
import fithou.edu.vn.DoAnTotNghiep.auth.entity.Role;
import fithou.edu.vn.DoAnTotNghiep.common.entity.BaseEntity;
import fithou.edu.vn.DoAnTotNghiep.order.entity.Order;
import fithou.edu.vn.DoAnTotNghiep.receipt.entity.Receipt;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "USER")
public class User extends BaseEntity implements Serializable {
    @GenericGenerator(name = "random_id", strategy = "fithou.edu.vn.DoAnTotNghiep.common.custom.RandomIdGenerator")
    @Id
    @GeneratedValue(generator = "random_id")
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "BIRTH_DAY")
    private Date birthDay;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "NUMBER_PHONE")
    private String numberPhone;

    @Column(name = "ACCOUNT_NAME")
    private String accountName ;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "AVARTAR")
    private String avartar;

    private boolean isCustomer = true;

    private boolean isAccountEnabled = true ;

    @Column(name = "STATUS")
    private Integer status;

    @ManyToMany(fetch = FetchType.EAGER,cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employee")
    private List<Receipt> receips;

    public List<String> getPermissions() {

        List<String> permissions = new ArrayList<>();
        List<Permission> collection = new ArrayList<>();
        for (Role role : this.getRoles()) {
            permissions.add(role.getNormalizedName());
            collection.addAll(role.getPermissions());
        }
        for (Permission item : collection) {
            permissions.add(item.getNormalizedName());
        } if(!this.isCustomer){
            permissions.add("ADMIN_DASHBOARD");
        }
        return permissions;
    }

}
