package fithou.edu.vn.DoAnTotNghiep.user.service.impl;

import fithou.edu.vn.DoAnTotNghiep.config.Contant;
import fithou.edu.vn.DoAnTotNghiep.user.entity.User;
import fithou.edu.vn.DoAnTotNghiep.user.repository.UserRepository;
import fithou.edu.vn.DoAnTotNghiep.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public Page<User> adminGetListEmployee(String name, Integer page) {
        page--;
        if (page <= 0) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, Contant.LIMIT_EMPLOYEE , Sort.by("createdDate").descending());
        return userRepository.findAllEmployees(name, pageable);
    }

    @Override
    public Page<User> adminGetListCustomer(String name, Integer page) {
        page--;
        if (page <= 0) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, Contant.LIMIT_EMPLOYEE , Sort.by("createdDate").descending());
        return userRepository.findAllCustomers( name, pageable);
    }

}
