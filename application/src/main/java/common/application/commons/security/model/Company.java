package common.application.commons.security.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import common.application.job.dto.CompanyVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class Company implements UserDetails{
    
    private CompanyVO company;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        String authority = company.getAuthority();

        if(authority != null){
            roles.add(new SimpleGrantedAuthority(authority));
        }

        return roles;
    }

    @Override
    public String getPassword() {
        return company.getPwd();
    }

    @Override
    public String getUsername() {
        return company.getCno();
    }

    @Override
    public boolean isAccountNonExpired() {
        return company.getEnabled() != 4;
    }

    @Override
    public boolean isAccountNonLocked() {
        return company.getEnabled() != 3;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return company.getEnabled() != 2;
    }

    @Override
    public boolean isEnabled() {
        return company.getEnabled() != 0;
    }
    
    public CompanyVO getCompanyVO(){
        return this.company;
    }
}
