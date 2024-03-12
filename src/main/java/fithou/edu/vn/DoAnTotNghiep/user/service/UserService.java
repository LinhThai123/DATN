package fithou.edu.vn.DoAnTotNghiep.user.service;

import fithou.edu.vn.DoAnTotNghiep.supplier.entity.Supplier;
import fithou.edu.vn.DoAnTotNghiep.user.entity.User;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    Page<User> adminGetListEmployee(String name, Integer page);
    Page<User> adminGetListCustomer(String name, Integer page);

    public User getEmployeeById (String id) throws NotFoundException;
}
