package fithou.edu.vn.DoAnTotNghiep.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String name;
    private String email;
    private List<String> roles;

    public JwtResponse(String token, String name, String email, List<String> roles) {
        this.token = token;
        this.name = name;
        this.email = email;
        this.roles = roles;
    }
}
