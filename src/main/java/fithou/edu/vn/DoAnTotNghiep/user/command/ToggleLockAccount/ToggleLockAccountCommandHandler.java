package fithou.edu.vn.DoAnTotNghiep.user.command.ToggleLockAccount;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ToggleLockAccountCommandHandler implements IRequestHandler<ToggleLockAccountCommand , String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public HandleResponse<String> handle(ToggleLockAccountCommand toggleLockAccountCommand) throws Exception {
        var user = userRepository.findById(toggleLockAccountCommand.getUserId());
        if (user.isEmpty()) {
            return HandleResponse.error("Không tìm thấy người dùng");
        }
        user.get().setAccountEnabled(!user.get().isAccountEnabled());
        user.get().setStatus(0);
        userRepository.save(user.get());
        return HandleResponse.ok(user.get().getId());
    }
}
