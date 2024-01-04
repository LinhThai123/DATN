package fithou.edu.vn.DoAnTotNghiep.auth.repository;

import fithou.edu.vn.DoAnTotNghiep.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail (String email) ;

    Optional<User> findByNumberPhone (String numberPhone);


}
