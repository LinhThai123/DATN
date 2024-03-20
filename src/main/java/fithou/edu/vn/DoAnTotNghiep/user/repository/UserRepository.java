package fithou.edu.vn.DoAnTotNghiep.user.repository;

import fithou.edu.vn.DoAnTotNghiep.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.normalizedName = 'ROLE_EMPLOYEE' AND (u.name LIKE %:name%)")
    Page<User> findAllEmployees(@Param("name") String name, Pageable pageable);

    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.normalizedName = 'ROLE_CUSTOMER' AND (u.name LIKE %:name%)")
    Page<User> findAllCustomers(@Param("name") String name, Pageable pageable);
    Optional<User> findByEmail (String email);
    Optional<User> findByNumberPhone (String numberPhone);

    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.normalizedName = 'ROLE_EMPLOYEE'")
    List<User> findAllEmployees();
    Optional<User> findByName (String name) ;

}
