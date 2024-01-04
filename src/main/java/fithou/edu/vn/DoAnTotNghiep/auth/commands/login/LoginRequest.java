package fithou.edu.vn.DoAnTotNghiep.auth.commands.login;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest implements IRequest<Integer> {

    private String email ;

    private String password;

}
