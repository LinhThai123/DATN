package fithou.edu.vn.DoAnTotNghiep.auth.commands.register;

import fithou.edu.vn.DoAnTotNghiep.auth.entity.Role;
import fithou.edu.vn.DoAnTotNghiep.auth.repository.IUserRepository;
import fithou.edu.vn.DoAnTotNghiep.auth.repository.RoleRepository;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class RegisterCommandHandler implements IRequestHandler<RegisterCommand, User> {
    @Autowired
    private IUserRepository IUserRepository;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;
    @Override
    @Transactional
    public HandleResponse<User> handle(RegisterCommand registerCommand) throws Exception {
        var exitsUser = IUserRepository.findByEmail(registerCommand.getEmail());
        if (exitsUser.isPresent()) {
            return HandleResponse.error("Email already exists");
        }
        var user = User.builder()
                .name(registerCommand.getName())
                .accountName(registerCommand.getAccountName())
                .email(registerCommand.getEmail())
                .isCustomer(true)
                .isAccountEnabled(true)
                .password(passwordEncoder.encode(registerCommand.getPassword()))
                .roles(List.of(roleRepository.findByName("ROLE_CUSTOMER").orElseThrow()))
                .build();

        IUserRepository.save(user);
        return HandleResponse.ok(user);

    }
}
