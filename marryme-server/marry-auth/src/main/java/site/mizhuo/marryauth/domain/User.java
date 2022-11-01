package site.mizhuo.marryauth.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @ClassName User
 * @Description:
 * @Author: MiZhuo
 * @Create: 2022-11-01 15:36
 * @Version 1.0
 **/
public class User implements UserDetails {

    public User(String username, String password, List<GrantedAuthority> admin) {
        this.username = username;
        this.password = password;
        this.admin = admin;
    }

    private String username;

    private String password;

    List<GrantedAuthority> admin;

    public String getUsername() {
        return username;
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

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<GrantedAuthority> getAdmin() {
        return admin;
    }

    public void setAdmin(List<GrantedAuthority> admin) {
        this.admin = admin;
    }
}
