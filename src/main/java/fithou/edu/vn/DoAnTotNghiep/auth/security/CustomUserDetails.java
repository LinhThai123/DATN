package fithou.edu.vn.DoAnTotNghiep.auth.security;

import fithou.edu.vn.DoAnTotNghiep.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class CustomUserDetails implements UserDetails {

    private User user ;
    public CustomUserDetails(User user) {
        this.user = user;
    }

    private List<GrantedAuthority> getGrantedAuthorities (List<String> permisstions) {
        List<GrantedAuthority> authorities = new ArrayList<>() ;
        for (String permission : permisstions) {
            authorities.add(new SimpleGrantedAuthority(permission)) ;
        }
        return authorities;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getGrantedAuthorities(user.getPermissions());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
