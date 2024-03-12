package fithou.edu.vn.DoAnTotNghiep.user.command.ToggleLockAccount;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ToggleLockAccountCommand implements IRequest<String> {
    private String userId;
}
