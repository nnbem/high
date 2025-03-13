package common.application.commons.security.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import common.application.cla.dto.MemberVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class Member implements UserDetails{
    
    private MemberVO member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        List<String> authorities = member.getAuthorities();
        if(authorities != null){
            for(String authority : authorities){
                roles.add(new SimpleGrantedAuthority(authority));
            }
        }

        return roles;
    }

    @Override
    public String getPassword() {
        return member.getPwd();
    }

    @Override
    public String getUsername() {
        return member.getMid();
    }

    @Override
    public boolean isAccountNonExpired() {
        return member.getEnabled() != 4;
    }

    @Override
    public boolean isAccountNonLocked() {
        return member.getEnabled() != 3;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return member.getEnabled() != 2;
    }

    @Override
    public boolean isEnabled() {
        return member.getEnabled() != 0;
    }
    
    public MemberVO getMemberVO(){
        return this.member;
    }
}
