package fithou.edu.vn.DoAnTotNghiep.user.service.impl;

import fithou.edu.vn.DoAnTotNghiep.auth.security.CustomUserDetails;
import fithou.edu.vn.DoAnTotNghiep.config.Contant;
import fithou.edu.vn.DoAnTotNghiep.user.entity.User;
import fithou.edu.vn.DoAnTotNghiep.user.repository.UserRepository;
import fithou.edu.vn.DoAnTotNghiep.user.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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

    @Override
    public User getEmployeeById(String id) throws NotFoundException {
        Optional<User> rs = userRepository.findById(id);
        if (!rs.isPresent()) {
            throw new NotFoundException("Không tìm thấy người dùng");
        }
        return rs.get();
    }

    @Override
    public List<User> getAllEmployess() {
        return userRepository.findAllEmployees();
    }

    @Override
    public Optional<User> getUserInfo(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public Optional<UserDetails> getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }
        return Optional.ofNullable((UserDetails) authentication.getPrincipal());
    }

    @Override
    public Optional<String> getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }
        Object principal = authentication.getPrincipal();

        if (principal instanceof CustomUserDetails) {
            return Optional.ofNullable(((CustomUserDetails) principal).getUser().getId());
        }
        return Optional.empty();
    }


}
