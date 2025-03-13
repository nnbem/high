package common.application.commons.security.provider;

import java.sql.SQLException;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import common.application.cla.dto.MemberVO;
import common.application.cla.service.MemberService;
import common.application.commons.security.model.Member;

@Component
public class MemberCustomAuthenticationProvider implements AuthenticationProvider{

    private MemberService memberService;
    public MemberCustomAuthenticationProvider(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        
        String login_id = (String)authentication.getPrincipal();
        String login_pwd = (String)authentication.getCredentials();
        MemberVO member = null;
        try{
            member = memberService.memberByMid(login_id);
        }catch(SQLException e){
            e.printStackTrace();
            throw new AuthenticationServiceException("서버 장애로 서비스가 불가합니다.");
        }
        if(member==null) throw new UsernameNotFoundException("존재하지 않는 아이디입니다.");
        if(!member.getPwd().equals(login_pwd)) throw new BadCredentialsException("패스워드가 일치하지 않습니다.");

        UserDetails authUser = new Member(member);
        
        boolean invalidCheck = authUser.isAccountNonExpired()
                        && authUser.isAccountNonLocked()
                        && authUser.isCredentialsNonExpired()
                        && authUser.isEnabled();
        if(!invalidCheck) throw new InsufficientAuthenticationException("유효하지 않는 계정입니다.");

        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(authUser.getUsername(), authUser.getPassword(), authUser.getAuthorities());

        result.setDetails(authUser);
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
}

