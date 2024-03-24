package fithou.edu.vn.DoAnTotNghiep.user.service;

import fithou.edu.vn.DoAnTotNghiep.user.entity.User;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    Page<User> adminGetListEmployee(String name, Integer page);
    Page<User> adminGetListCustomer(String name, Integer page);
    public User getEmployeeById (String id) throws NotFoundException;

    public List<User> getAllEmployess ();

    public Optional<User> getUserInfo (String name) ;

    public Optional<UserDetails> getCurrentUser();
    public Optional<String> getCurrentUserId();
}
