package fithou.edu.vn.DoAnTotNghiep.auth.security;

import fithou.edu.vn.DoAnTotNghiep.auth.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service("userDetailService")
@Transactional
public class UserDetailServiceImpl implements UserDetailsService , UserDetailsPasswordService {

    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder ;

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        var userEntity = iUserRepository.findByEmail(user.getUsername());
        if (userEntity.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        userEntity.get().setPassword(passwordEncoder.encode(newPassword));
        iUserRepository.save(userEntity.get());
        return new CustomUserDetails(userEntity.get());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var useByEmail = iUserRepository.findByEmail(username);
        if (useByEmail.isPresent()) {
            return new CustomUserDetails(useByEmail.get()) ;
        }
        var userByPhoneNumber = iUserRepository.findByNumberPhone(username);
        if (userByPhoneNumber.isPresent()) {
            return new CustomUserDetails (userByPhoneNumber.get()) ;
        }
        var userById = iUserRepository.findById(username);
        if (userById.isPresent()) {
            return new CustomUserDetails (userById.get());
        }
        throw new UsernameNotFoundException("User not found");
    }
}
